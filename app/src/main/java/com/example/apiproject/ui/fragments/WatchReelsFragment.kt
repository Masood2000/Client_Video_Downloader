package com.example.apiproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.apiproject.R
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.api.Video
import com.example.apiproject.data.models.gag.Comment
import com.example.apiproject.data.models.gag.Creator
import com.example.apiproject.data.models.gag.Images
import com.example.apiproject.data.models.gag.Post
import com.example.apiproject.data.models.gag.PostUrl
import com.example.apiproject.databinding.FragmentWatchReelsBinding
import com.example.apiproject.ui.activity.MainActivity
import kotlinx.coroutines.launch
import kotlin.random.Random


class WatchReelsFragment : Fragment() {

    private val TAG = "WatchReelsFragment"

    private val binding by lazy {
        FragmentWatchReelsBinding.inflate(layoutInflater)
    }

    private val shortReelsAdapter by lazy {
        ShortReelsAdapter()
    }

    private val viewModel: WatchReelsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nextApiHit = "after=apRpXPB%2CagmLb8g%2Ca5QbAGV&c=20"
        viewModel.fetchPosts(nextApiHit)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {

                launch {
                    viewModel.postsState.collect {
                        Log.d(TAG, "onCreate: posts state is $it")
                        when (it) {
                            null -> {}
                            else -> {
                                // show data

                                Log.d(TAG, "onCreate: ${it.data.posts.size}")
                                shortReelsAdapter.setNewData(
                                    if (it.data.posts.isEmpty()) {
                                        dummyDataList.toMutableList()
                                    }
                                    else{

                                        var postUrls = mutableListOf<PostUrl>()

                                        it.data.posts.forEach { postUrl->
                                            postUrls.add(PostUrl(postUrl.images.image460sv.url))
                                        }
                                        postUrls
                                    }
                                )
                            }
                        }
                    }
                }

                launch {
                    viewModel.loadingState.collect { isLoading ->
                        Log.d(TAG, "onCreate: loading state is $isLoading")
                        // show loading
                        binding.shortsPlayerProgress.isVisible = isLoading
                    }
                }

                launch {
                    viewModel.errorState.collect {
                        shortReelsAdapter.setNewData(dummyDataList.toMutableList())
                    }
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.let{
            if(it is MainActivity){
                it.hideTopAndBottomBar()
            }
        }

        binding.vpShorts.adapter = shortReelsAdapter

        shortReelsAdapter.onShortSelectListener = object : ShortReelsAdapter.OnShortSelectListener {
            override fun downloadShort(item: PostUrl) {
                Toast.makeText(requireContext(), "Downloading Started", Toast.LENGTH_LONG).show()

                Log.d(TAG, "downloadShort: ${item.url}")

                activity.let {
                    if (it is MainActivity) {
                        it.downloadOptionSheet(
                            ExtractedData(

                                listOf(
                                    Video(
                                        200,
                                        "Short Video",
                                        2,
                                        item.url,
                                        200,
                                        item.url
                                    )
                                ),
                                title = "Short Video_"+ (1..59600).random(),
                            ),
                            item.url
                        )
                    }
                }

            }
        }

    }

    override fun onResume() {
        super.onResume()
        try {
            shortReelsAdapter.exoPlayer?.play()
        } catch (_: Exception) {
        } catch (_: java.lang.Exception) {
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            shortReelsAdapter.exoPlayer?.pause()
        } catch (_: Exception) {
        } catch (_: java.lang.Exception) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            activity.let{
                if(it is MainActivity){
                    it.showTopAndBottomBar()
                }
            }
            shortReelsAdapter.exoPlayer?.stop()
            shortReelsAdapter.exoPlayer?.release()
        } catch (_: java.lang.Exception) {
        } catch (_: java.lang.IllegalStateException) {
        }
    }



    var dummyDataList = listOf<PostUrl>(
        PostUrl("https://img-9gag-fun.9cache.com/photo/aGyzGBX_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/ayt9sYO_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aD2DbY7_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aW4go6n_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/azxeGDB_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a871e5Z_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aPAZ49w_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/awyzOK4_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aeYrRgw_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aYQrPKq_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/ae9gVz5_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a2v1y3D_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aGyzLoG_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a871e5Z_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aD2DbY7_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a4P1AKm_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a2v1y3D_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/awyzOK4_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aW4go6n_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aGyzLoG_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aPAZ49w_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aYQrPKq_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a871e5Z_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aGyzGBX_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/ae9gVz5_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aGyzLoG_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a4P1ANZ_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/azxeGDB_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a871e5Z_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/awyzOK4_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aeYrRgw_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aD2D7nx_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/ae9gVz5_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a4P1AKm_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/awyzOK4_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a2v1y3D_460sv.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a4P1AVp_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a2v1y3D_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aPAZ49w_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aeYrRgw_460svav1.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/aD2D7nx_460svh265.mp4"),
        PostUrl("https://img-9gag-fun.9cache.com/photo/a871e5Z_460svh265.mp4")
    )



}