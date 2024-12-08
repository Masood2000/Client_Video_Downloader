package com.example.apiproject.ui.activity

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.apiproject.R
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.interfaces.ClickHandler
import com.example.apiproject.databinding.ActivityBrowserCastingBinding
import com.example.apiproject.databinding.DownloadOptionSheetBinding
import com.example.apiproject.databinding.FetchingDownloadDialogBinding
import com.example.apiproject.domain.extractor.types.ExtractorManager
import com.example.apiproject.domain.viewmodels.MainActivityViewModel
import com.example.apiproject.ui.activity.BrowserActivity.Companion
import com.example.apiproject.ui.adapters.ResolutionAdapter
import com.example.apiproject.util.Converters.convertToExtractedData
import com.example.apiproject.util.Helper
import com.example.apiproject.util.NetworkHelper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class BrowserActivity : AppCompatActivity() {


    private val binding: ActivityBrowserCastingBinding by lazy {
        ActivityBrowserCastingBinding.inflate(layoutInflater)
    }

    val viewModel: MainActivityViewModel by viewModels()

    private var permissionStateListener: ClickHandler? = null

    private lateinit var navController: NavController

    /***
     * Life Cycle Related Functions
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().getDecorView();
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: called...")

        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_browser_container_view) as NavHostFragment
        navController = navHostFragment.navController



        initViews()
        initListeners()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: called...")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: called...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: called...")
    }

    /***
     * / Life Cycle Related Functions
     */


    /***
     * View Handling Functions
     */

    private fun initViews(){

    }

    private fun initListeners(){

    }




    /***
     * / View Handling Functions
     */


    /***
     * Other Functionality
     */

    fun getDownloadMetaData(link: String) {
/*
        if (link.contains("youtube", ignoreCase = true) ||
            link.contains("youtu.be", ignoreCase = true) ||
            link.contains("encrypted-vtbn0.gstatic", ignoreCase = true)
        ) {
            Toast.makeText(this, "YouTube links are not supported", Toast.LENGTH_SHORT).show()
            return
        }*/

        var dialogBinding = FetchingDownloadDialogBinding.inflate(layoutInflater)
        var dialog = createAndShowDialog(dialogBinding.root)
        lifecycleScope.launch(Dispatchers.IO) {


            try {
                var data = NetworkHelper.getData(link)
                if ((data != null && !data.isJsonNull && !link.contains("instagram"))) {
                    Log.d("ApiResponse", "${data}")
                    var extractedData = convertToExtractedData(data!!)
                    Log.d("ApiResponse", "Data is not Null ")
                    Log.d("ApiResponse", "ApiData  " + extractedData?.video?.size)
                    Log.d("ApiResponse", "ApiData  " + extractedData?.audio)
                    Log.d("ApiResponse", "ApiData  " + extractedData?.cookie)

                    if (extractedData != null) {
                        if (link.contains("tiktok")) {
                            var newExtractedData =
                                ExtractorManager.getVideo(this@BrowserActivity, link)
                            if (newExtractedData != null) {
                                extractedData.video = newExtractedData?.video
                                extractedData.cookie = newExtractedData?.cookie
                            }
                        }
                        else if(link.contains("dailymotion.com")  || link.contains("dai.ly")){
                            Log.d(BrowserActivity.TAG, "getDownloadMetaData: Dailymotion linked")
                            var newExtractedData =  ExtractorManager.getVideo(this@BrowserActivity, link)
                            if (newExtractedData != null) {
                                extractedData.video = newExtractedData?.video
                                extractedData.cookie = newExtractedData?.cookie
                            }
                        }
                        withContext(Dispatchers.Main) {
                            downloadOptionSheet(extractedData!!, link)
                            dialog.dismiss()

                        }
                    }
                    else {

                        Log.d("ApiResponse", "getDownloadMetaData: ${link}")
                        var secondExtractedData = ExtractorManager.getVideo(this@BrowserActivity, link)
                        if (secondExtractedData != null) {
                            withContext(Dispatchers.Main) {
                                downloadOptionSheet(secondExtractedData!!, link)
                                dialog.dismiss()
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(this@BrowserActivity, "No Video Found", Toast.LENGTH_SHORT)
                                    .show()
                                dialog.dismiss()
                            }
                        }


                        Log.d("ApiResponse", "Data is Null")

                    }

                    Log.d(BrowserActivity.TAG, "getDownloadMetaData: ${extractedData?.video.toString()}")

                }
                else {
                    Log.d("ApiResponse", "getDownloadMetaData: ${link}")
                    var secondExtractedData = ExtractorManager.getVideo(this@BrowserActivity, link)
                    if (secondExtractedData != null) {
                        withContext(Dispatchers.Main) {
                            downloadOptionSheet(secondExtractedData!!, link)
                            dialog.dismiss()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@BrowserActivity, "No Video Found", Toast.LENGTH_SHORT)
                                .show()
                            dialog.dismiss()
                        }
                    }

                    Log.d("ApiResponse", "Data is Null22")
                }
//                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@BrowserActivity, "Error Occured", Toast.LENGTH_SHORT).show()
                    Log.d("ApiResponse", "Error Occured ${e.message}")
                    dialog.dismiss()

                }
            }


        }
    }

    fun getDownloadReel(link: String, videoId: String) {

        if (link.contains("youtube", ignoreCase = true) ||
            link.contains("youtu.be", ignoreCase = true) ||
            link.contains("encrypted-vtbn0.gstatic", ignoreCase = true)
        ) {
            Toast.makeText(this, "YouTube links are not supported", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {

            try {
                var data = NetworkHelper.getData(link)
                if ((data != null && !data.isJsonNull && !link.contains("instagram"))) {
                    Log.d("ApiResponse", "${data}")
                    var extractedData = convertToExtractedData(data!!)
                    extractedData?.video =
                        extractedData?.video?.filter { !it.url.endsWith(".m3u8") }
                    if (extractedData != null) {
                        withContext(Dispatchers.Main) {
                            extractedData.imageUrl =
                                "https://www.dailymotion.com/thumbnail/video/${videoId}"
                            downloadOptionSheet(extractedData!!, link)
                        }
                    } else {
                        Log.d("ApiResponse", "Data is Null")

                    }

                } else {
                    var secondExtractedData = ExtractorManager.getVideo(this@BrowserActivity, link)
                    if (secondExtractedData != null) {
                        withContext(Dispatchers.Main) {
                            downloadOptionSheet(secondExtractedData!!, link)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@BrowserActivity, "No Video Found", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    Log.d("ApiResponse", "Data is Null")
                }
//                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@BrowserActivity, "Error Occured", Toast.LENGTH_SHORT).show()
                    Log.d("ApiResponse", "Error Occured ${e.message}")
                }
            }


        }
    }


    private fun createAndShowBottomSheet(view: View): BottomSheetDialog {
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED

        bottomSheetDialog.show()
        return bottomSheetDialog
    }

    private fun createAndShowDialog(view: View): Dialog {
        val dialog = Dialog(this, R.style.SheetDialog)
        dialog.setContentView(view)
        val width = (resources.displayMetrics.widthPixels * 0.70).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.32).toInt()

        dialog.getWindow()?.setLayout(width, height)

        dialog.show()
        return dialog
    }

    private fun formatSize(size: Long): String {
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        var newSize = size.toDouble()
        var unitIndex = 0
        while (newSize >= 1024 && unitIndex < units.size - 1) {
            newSize /= 1024
            unitIndex++
        }
        return String.format("%.2f %s", newSize, units[unitIndex])
    }

    private fun getTotalPhoneStorage(): Long {
        val path = Environment.getDataDirectory() // Get the internal storage path
        val stat = StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val totalBlocks = stat.blockCountLong
        return totalBlocks * blockSize
    }

    private fun getProgressPercentage(): Int {
        val consumedStorage = getConsumedPhoneStorage()
        val totalStorage = getTotalPhoneStorage()
        // Ensure consumedStorage and totalStorage are within the range of Long
        val maxLongValue = Long.MAX_VALUE
        val scaledTotalStorage = if (totalStorage <= maxLongValue) totalStorage else maxLongValue
        val scaledConsumedStorage =
            if (consumedStorage <= maxLongValue) consumedStorage else maxLongValue
        // Calculate the progress percentage and cap between 0 and 100
        val progress = ((scaledConsumedStorage * 100) / scaledTotalStorage).toInt()
        return progress.coerceIn(0, 100)
    }


    private fun getConsumedPhoneStorage(): Long {
        val path = Environment.getDataDirectory() // Get the internal storage path
        val stat = StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val availableBlocks = stat.availableBlocksLong
        return (stat.blockCountLong - availableBlocks) * blockSize
    }

    private fun downloadOptionSheet(extractedData: ExtractedData, url: String) {
        val dialogBinding = DownloadOptionSheetBinding.inflate(layoutInflater)
        dialogBinding.heading.text = extractedData.title
        val maxStorage = getTotalPhoneStorage()
        val consumedStorage = getConsumedPhoneStorage()
        dialogBinding.sheetTvStorage.text =
            "${formatSize(consumedStorage)} / ${formatSize(maxStorage)}"
        dialogBinding.sheetStorageProgress.max = 100
        dialogBinding.sheetStorageProgress.setProgress(getProgressPercentage(), true)
        val adapter = ResolutionAdapter()
        adapter.extractedData = extractedData
        dialogBinding.rc.adapter = adapter
        if (extractedData.video?.size!! < 4) {
            dialogBinding.rc.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 1)
        } else {
            dialogBinding.rc.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        }
        val bottomSheetDialog = createAndShowBottomSheet(dialogBinding.root)
        NetworkHelper.cookie = extractedData.cookie
        Glide.with(this).load(extractedData.imageUrl)
            .placeholder(R.drawable.video_svgrepo_com)
            .into(dialogBinding.imageFilterView)

        dialogBinding.download.setOnClickListener {
            var selectedCell = adapter.selectedCell


            permissionStateListener = object : ClickHandler {
                override fun onClickPressed() {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (selectedCell < extractedData?.video!!.size) {
                            var video = extractedData?.video!![selectedCell]
                            viewModel.downloadVideo(
                                this@BrowserActivity,
                                extractedData.title,
                                video.url,
                                Helper.getNewDownloadPath(),
                                extractedData.cookie,
                                false,
                                extractedData.imageUrl,
                                extractedData.title
                            )
                        } else {
                            viewModel.downloadAudio(
                                this@BrowserActivity, extractedData.title, url,
                                extractedData.cookie, false,
                                extractedData.imageUrl, extractedData.title,
                                extractedData.audio ?: "",
                                Helper.getNewAudioDownloadPath()
                            )
                        }
                    }
                }
            }

            bottomSheetDialog.dismiss()
            requestVideoAccessPermission()
        }
        dialogBinding.cancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
    }


    private fun openPermissionSettings() {

        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", this.packageName, null)
        intent.data = uri
        startActivity(intent)


    }

    private fun showRationalDialog() {

        val mDialogView =
            LayoutInflater.from(this).inflate(R.layout.layout_permissions_dialog, null)
        val mBuilder = android.app.AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.setCanceledOnTouchOutside(false)
        mDialogView.findViewById<TextView>(R.id.title_heading_tv)
        mDialogView.findViewById<TextView>(R.id.statement_tv).text
        mDialogView.findViewById<TextView>(R.id.cancel_btn).text
        mDialogView.findViewById<TextView>(R.id.confirm_btn).text

        mDialogView.findViewById<TextView>(R.id.cancel_btn).setOnClickListener {
            if (mAlertDialog != null && mAlertDialog.isShowing) {
                mAlertDialog.dismiss()

            }
        }
        mDialogView.findViewById<TextView>(R.id.confirm_btn).setOnClickListener {
            if (mAlertDialog != null && mAlertDialog.isShowing) {
                mAlertDialog.dismiss()
                openPermissionSettings()
            }
        }


    }

    private fun checkAskAgainForVideo() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            val rationalFlagREAD =
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
            val rationalFlagWRITE =
                shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)

            if (!rationalFlagREAD && !rationalFlagWRITE) {
                showRationalDialog()
            }
        } else {
            val rationalFlagREAD =
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_VIDEO)
            if (!rationalFlagREAD) {
                showRationalDialog()
            }
        }
    }

    private fun requestVideoAccessPermission() {
        val permissions = mutableListOf<String>()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            permissions.add(Manifest.permission.READ_MEDIA_VIDEO)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        activityResultLauncherForVideo.launch(permissions.toTypedArray())
    }

    private val activityResultLauncherForVideo = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            if (
                permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true &&
                permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
            ) {
                permissionStateListener?.onClickPressed()
            } else {
                checkAskAgainForVideo()
            }
        } else {
            val videoPermissionGranted = permissions[Manifest.permission.READ_MEDIA_VIDEO] == true
            val notificationPermissionGranted = permissions[Manifest.permission.POST_NOTIFICATIONS] == true

            if (videoPermissionGranted && notificationPermissionGranted) {
                permissionStateListener?.onClickPressed()
            } else {
                checkAskAgainForVideo()
            }
        }
    }



    /***
     * / Other Functionality
     */

    companion object{

        private const val TAG = "BROWSER_CASTING_ACTIVITY"

    }



}