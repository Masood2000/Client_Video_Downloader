package com.example.apiproject.ui.fragments

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiproject.R
import com.example.apiproject.data.database.entity.DownloadedVideo
import com.example.apiproject.data.interfaces.ClickBundleHandler
import com.example.apiproject.data.interfaces.ClickHandler
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.databinding.FragmentVideoCompletedBinding
import com.example.apiproject.databinding.SheetVideoOptionsBinding
import com.example.apiproject.domain.events.VideoLoaded
import com.example.apiproject.ui.adapters.DownloadedAdapter
import com.example.apiproject.ui.base.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File


@AndroidEntryPoint

class VideoCompletedFragment constructor() : BaseFragment() {


    private val binding by lazy { FragmentVideoCompletedBinding.inflate(layoutInflater) }

    private val adapter by lazy { DownloadedAdapter() }

    private var optionsBottomSheetDialog: BottomSheetDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())



        activity.let {
            if (it is MainActivity) {

                it.viewModel.getDownloadedVideos()
            }
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activity?.let {
                    if (it is MainActivity) {
                        it.viewModel.downloadedVideos.collect { state ->
                            Log.d("VideoCompletedFragment", "State: $state")
                            when (state) {
                                is VideoLoaded.Success -> {
                                    if (state.data.isEmpty()) {
                                        Log.d("VideoCompletedFragment", "Empty")
                                        binding.noVid.visibility = View.VISIBLE
                                        adapter.setData(state.data)
                                    } else {
                                        Log.d("VideoCompletedFragment", "Data: ${state.data}")
                                        binding.noVid.visibility = View.GONE

                                        adapter.setData(state.data)
                                    }
                                }

                                is VideoLoaded.Empty -> {
                                    Log.d("VideoCompletedFragment", "Empty")
                                }

                                is VideoLoaded.Error -> {}
                                VideoLoaded.Loading -> {
                                }

                            }
                        }
                    }
                }
            }
        }

    }

    override fun setViewBinding(): View {
        return binding.root
    }

    override fun initView() {

        adapter.onVideoSelectedListener = object : DownloadedAdapter.OnVideoSelectedListener {
            override fun onOptionsClicked(view: View, position: Int, download: DownloadedVideo) {
                showOptions(download, position)
            }

            override fun toggleSelectionMode(isSelection: Boolean, allSelected: Boolean) {
                //TODO
            }

            override fun onItemClicked(position: Int, media: DownloadedVideo) {
                //TODO
            }

            override fun dummyItemClicked(url: String) {
                //TODO
            }
        }



        binding.recycleView.adapter = adapter
        adapter.clickHandler = object : ClickBundleHandler {
            override fun onClickPressed(bundle: Bundle) {
                if (findNavController().currentDestination?.id == R.id.videoCompletedFragment) {
                    findNavController().navigate(
                        R.id.action_videoCompletedFragment_to_completedVideoPlayerFragment,
                        bundle
                    )
                }
            }

        }

        activity?.let {
            if (it is MainActivity){
                it.onBackPressedDispatcher.addCallback (viewLifecycleOwner){
                    it.moveToFirstItem()
                }
            }
        }


    }

    override fun lazyLoad() {

    }


    private fun showOptions(download: DownloadedVideo, position: Int) {
        activity?.let { context ->
            val bottomSheetBinding by lazy { SheetVideoOptionsBinding.inflate(layoutInflater) }
            optionsBottomSheetDialog = BottomSheetDialog(context, R.style.SheetDialog)
            optionsBottomSheetDialog?.setContentView(bottomSheetBinding.root)
            optionsBottomSheetDialog?.setCanceledOnTouchOutside(true)
            optionsBottomSheetDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            optionsBottomSheetDialog?.show()

            with(bottomSheetBinding) {

                actionPlay.setOnClickListener() {

                    optionsBottomSheetDialog?.dismiss()

                    var bundle = Bundle()
                    bundle.putString("videoUrl", download.path)
                    bundle.putString("videoTitle", download.title)
                    bundle.putBoolean(
                        "isAudio",
                        download.audioPath == download.path
                    )

                    if (findNavController().currentDestination?.id == R.id.videoCompletedFragment) {
                        findNavController().navigate(
                            R.id.action_videoCompletedFragment_to_completedVideoPlayerFragment,
                            bundle
                        )
                    }

                }

                actionDelete.setOnClickListener {
                    optionsBottomSheetDialog?.dismiss()
                    deleteDownload(download, position)
                }
                actionCopy.setOnClickListener {
                    optionsBottomSheetDialog?.dismiss()
                    copyToClipboard(
                        download.downloadUrl ?: ""
                    )
                }
                actionShare.setOnClickListener {

                    optionsBottomSheetDialog?.dismiss()
                    if (context is MainActivity) {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        // Set the type of content to be shared
                        shareIntent.setType("video/mp4")
                        // Set the path of the video to be shared

                        insertToMediaStore(File(download.path))?.let { shareableContent ->

                            shareIntent.putExtra(Intent.EXTRA_STREAM, shareableContent.contentUri)

                        }


                        //shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(download.path))
                        Log.d(TAG, "showOptions: ${download.path}")
                        // Start the activity with the share intent
                        startActivity(Intent.createChooser(shareIntent, "Share Video using"))
                        optionsBottomSheetDialog?.dismiss()
                    }
                }
                actionRename.setOnClickListener {
                    optionsBottomSheetDialog?.dismiss()
                    // showRenameDialog(download)
                }


            }
        }
    }

    private fun copyToClipboard(text: String) {

        val clipboard =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)

        showSnackBar("Url Copied to Clipboard", binding.root)

    }

    private fun deleteDownload(download: DownloadedVideo, position: Int) {

        activity?.let {

            val title = if (download.path.endsWith(".mp4")) {
                "${getString(R.string.delete)} ${getString(R.string.video)}?"
            } else if (download.path.endsWith(".mp3")) {
                "${getString(R.string.delete)} ${getString(R.string.audio)}?"
            } else {
                "${getString(R.string.delete)} ${getString(R.string.image)}?"
            }

            val supportingText = if (download.path.endsWith(".mp4")) {
                getString(R.string.video_deleted_permanantly)
            } else if (download.path.endsWith(".mp3")) {
                getString(R.string.audio_deleted_permanantly)
            } else {
                getString(R.string.image_deleted_permanantly)
            }

            val confirmationText = if (download.path.endsWith(".mp4")) {
                getString(R.string.video_deleted)
            } else if (download.path.endsWith(".mp3")) {
                getString(R.string.audio_deleted)
            } else {
                getString(R.string.image_deleted)
            }

            MaterialAlertDialogBuilder(it)
                .setTitle(title)
                .setMessage(supportingText)
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }
                .setPositiveButton("Delete") { dialog, which ->
                    if (File(download.path).delete()) {

                        activity.let {
                            if (it is MainActivity) {
                                it.viewModel.deleteDownloadedVideo(download.path)
                                adapter.notifyItemChanged(position)
                            }
                        }

                        showSnackBar(confirmationText, binding.root)
                    } else {
                        showSnackBar("Unexpected Error", binding.root)
                    }
                }
                .show()
        }

    }


    /***
     * <---------------- Share Video Functions  ------------------->
     *
     * Extra function for sharing the video to other apps ....
     */
    fun insertToMediaStore(file: File): ShareableContent? {

        val mimeTypeString = "video/mp4"

        val uri = insertVideoToMediaStore(
            file,
            "Movies/Your Video Subdirectory",
            mimeTypeString
        )

        uri?.let {
            return ShareableContent(contentUri = uri, mimeType = mimeTypeString)
        } ?: throw RuntimeException("Unable to insert to media store")
    }

    @SuppressLint("InlinedApi")
    fun insertVideoToMediaStore(file: File, relativePath: String, mimeType: String): Uri? {

        val values = ContentValues().apply {

            put(MediaStore.Video.Media.DISPLAY_NAME, file.name)
            put(MediaStore.Video.Media.MIME_TYPE, mimeType)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            }
        }

        val collection = when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            true -> MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            false -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }

        insertFileToMediaStore(requireActivity().contentResolver, collection, values, file)?.let {
            return it
        } ?: run {
            return null
        }
    }

    private fun insertFileToMediaStore(
        contentResolver: ContentResolver,
        collection: Uri,
        values: ContentValues,
        file: File
    ): Uri? {

        val uri = contentResolver.insert(collection, values)

        uri?.let {
            contentResolver.openOutputStream(uri)?.use { outputStream ->
                try {
                    outputStream.write(file.readBytes())
                    outputStream.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            values.clear()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                values.put(MediaStore.Images.Media.IS_PENDING, 0)
                contentResolver.update(uri, values, null, null)
            }

        } ?: throw RuntimeException("Unable to write file to MediaStore")

        return uri
    }


    /***
     * <--------------- /Share Video Functions  ------------------->
     */


    companion object {
        private const val TAG = "VideoCompletedFragment"
    }


}

data class ShareableContent(val contentUri: Uri, val mimeType: String)