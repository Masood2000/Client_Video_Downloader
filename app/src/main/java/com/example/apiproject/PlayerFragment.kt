package com.example.apiproject

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import com.example.apiproject.databinding.FragmentPlayerBinding
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.ui.base.BaseFragment
import com.example.apiproject.util.Helper
import com.example.apiproject.util.Helper.setOnOneClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlayerFragment : BaseFragment() {
    val binding by lazy { FragmentPlayerBinding.inflate(layoutInflater) }
    val player: Player by lazy { ExoPlayer.Builder(requireContext()).build() }

    override fun setViewBinding(): View {
        return binding.root
    }

    @OptIn(UnstableApi::class)
    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(requireContext(), "exoplayer-codelab")
        return HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(uri))
    }

    @SuppressLint("SetJavaScriptEnabled")
    @OptIn(UnstableApi::class)
    override fun initView() {

        // Get bundle data
        val bundle = arguments
        val videoUrl = bundle?.getString("videoUrl")
        val videoTitle = bundle?.getString("videoTitle")
        val videoId = bundle?.getString("videoId")

        activity?.let {
            if (it is MainActivity) {
                it.binding.tvTitle.text = videoTitle?.capitalize()
            }
        }
        if (!videoUrl.isNullOrEmpty()) {
            binding.playerView.loadUrl("https://www.dailymotion.com/embed/video/${videoId}?queue-enable=0&ui-start-screen-info=0&endscreen-enable=0&fs=0&autoplay=1&queue-autoplay-next=0&ui-logo=0&sharing-enable=0&loop=0&ui-highlight=fff")
            binding.playerView.visibility = View.VISIBLE

            binding.playerView.settings.javaScriptEnabled = true
            binding.playerView.settings.javaScriptCanOpenWindowsAutomatically = true
            binding.playerView.settings.mediaPlaybackRequiresUserGesture = false
            binding.playerView.settings.domStorageEnabled = true
            binding.playerView.settings.allowFileAccess = true
            binding.playerView.settings.allowContentAccess = true
            binding.playerView.addJavascriptInterface(object {
                @JavascriptInterface
                fun logMessage(message: String) {
                    Log.d("JS_LOG", message)
                }
            }, "Android")



            binding.playerView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    binding.playerView.loadUrl(
                        """
             javascript:(function() {
               
                function hideSvgElements() {
                             document.querySelectorAll('.brand_signature_svg').forEach(function(element) { 
                                 element.style.display = 'none'; 
                             }); 
                         }
                         
                            hideSvgElements();
                            
                     Android.logMessage('Script injected and running.');
                     
                    
                     var observer = new MutationObserver(function(mutations) {
                             mutations.forEach(function(mutation) {
                                 hideSvgElements();
                                 Android.logMessage('Mutation observed, elements hidden.');
                             });
                         });
 
                        observer.observe(document.body, { childList: true, subtree: true });
 
        
                     Android.logMessage('Elements hidden.');
                
               
             })()
             """.trimIndent()
                    )


                }
            }






            binding.downloadNow.setOnOneClickListener {
                //Get Current Url of Webview
                activity?.let {
                    //Check if activity is MainActivity
                    if (it is MainActivity) {
                        //Call downloadVideo function from MainActivity
                        Log.d("VideoUrl", videoUrl.toString())
                        it.getDownloadReel(
                            "https://www.dailymotion.com/video/${videoId}/",
                            videoId ?: ""
                        )

                    }
                }
            }

//        } else {

        }
    }

    override fun lazyLoad() {

    }

    override fun onDestroyView() {
        super.onDestroyView()


    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onPause() {
        super.onPause()
    }

}