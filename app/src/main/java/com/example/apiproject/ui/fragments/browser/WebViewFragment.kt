package com.example.apiproject.ui.fragments.browser

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.PopupWindow
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController


import com.google.android.material.bottomsheet.BottomSheetDialog

import com.example.apiproject.R
import com.example.apiproject.databinding.FragmentWebViewBinding
import com.example.apiproject.ui.activity.BrowserActivity

import com.example.universaltvremote.presentation.viewmodel.BrowserCastingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {

    val binding by lazy {
        FragmentWebViewBinding.inflate(layoutInflater)
    }

    var youtubeIconUrl = "https://www.iconpacks.net/icons/2/free-youtube-logo-icon-2431-thumb.png"
    var generalIconUrl = "https://cdn4.iconfinder.com/data/icons/logos-brands-7/512/google_logo-google_icongoogle-512.png"
    var iconUrl = "https://cdn4.iconfinder.com/data/icons/logos-brands-7/512/google_logo-google_icongoogle-512.png"



    val ipAddressRegex = """\b(?:\d{1,3}\.){3}\d{1,3}\b""".toRegex()

    val viewModel:BrowserCastingViewModel by activityViewModels()



    private var websiteType = "general"

    private var backPressedCallback: OnBackPressedCallback? = null

    var prevLink: String = ""

    var resolutionBottomSheetDialog: BottomSheetDialog? = null
    var devicesBottomSheetDialog: BottomSheetDialog? = null

    var mediaURL =
        "https://www.youtube.com/watch?v=EvzB_Q1gSds"



    /***
     * LifeCycle Related Functions
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: called")
        arguments.let { it ->
            if (it?.getString("website_type") != null) {
                websiteType = it.getString("website_type").toString()
                Log.d(TAG, "onCreate: fetching nav args...")

            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "onCreateView: called")



        configureBackPress()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled", "UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: called...")








        Log.d(TAG, "onViewCreated: ${websiteType.toString()}")
        initViews();
        initListeners();



        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.allowContentAccess = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.builtInZoomControls = false
        binding.webView.settings.displayZoomControls = false
        binding.webView.settings.setSupportZoom(true)
        binding.webView.settings.mediaPlaybackRequiresUserGesture = true
        binding.webView.settings.javaScriptCanOpenWindowsAutomatically = false
        //binding.webView.addJavascriptInterface(this, "newJava")


        if(websiteToLoad != null){
            binding.webView.loadUrl(websiteToLoad!!)
            websiteToLoad = null
        }
        else{
            when (websiteType) {

                "general" -> {
                    if(websiteToLoad !=null )
                        binding.webView.loadUrl(websiteToLoad!!)
                    else
                        binding.webView.loadUrl("https://www.google.com")
                    websiteToLoad = null
                }

                "insta" -> {
                    binding.webView.loadUrl("https://www.instagram.com/explore/")
                }

                "youtube" -> {
                    binding.webView.loadUrl(" https://www.youtube.com/")
                }

                "google" -> {
                    binding.webView.loadUrl("https://www.google.com")
                }

                "pinterest" -> {
                    binding.webView.loadUrl("https://www.pinterest.com/videos/")
                }

                "vimeo" -> {
                    binding.webView.loadUrl("https://vimeo.com/watch")
                }

                "twitch" -> {
                    binding.webView.loadUrl("https://www.twitch.tv/")
                }

                "fb" -> {
                    binding.webView.loadUrl("https://www.facebook.com/watch/")
                }

                "reddit" -> {
                    binding.webView.loadUrl("https://www.reddit.com/")
                }

                "dailymotion" -> {
                    binding.webView.loadUrl("https://www.dailymotion.com/")
                }

                "none" -> {

                }

                else -> {
                    //binding.webView.loadUrl("https://www.google.com")
                    Log.d(TAG, "onViewCreated: not loading any website")
                }


            }

            websiteType = "none"
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d(TAG, "onDestroyView: called...")

        backPressedCallback?.remove()
        backPressedCallback = null


        binding.webView.clearCache(true)

        binding.webView.clearHistory()
        binding.webView.clearFormData()
        binding.webView.removeAllViews()


    }

    /***
     * /LifeCycle Related Functions
     */


    /***
     * Views Handling Functions...
     */

    private fun initViews() {


        binding.webView.webViewClient = object : WebViewClient() {


            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                binding.webView.loadUrl(url)
                Log.d(TAG, "shouldOverrideUrlLoading: ${url}")
                return false
            }


            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)


            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                view?.evaluateJavascript(
                    "(function() {" +
                            "var link = document.querySelector('link[rel~=\"icon\"]');" +
                            "return link ? link.href : null;" +
                            "})()"
                ) { faviconUrl ->
                    faviconUrl?.let {
                        if (faviconUrl != "null") {
                            // Process the favicon URL (remove extra quotes)
                            iconUrl = faviconUrl.replace("\"", "")

                        }
                    }
                }

                Log.d(TAG, "icon url : $iconUrl")



                //binding.searchTextField.setText(url.toString())

            }

            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)


                Log.d(TAG, "url -> ${url} == prevLink -> ${prevLink}")
                if (prevLink != binding.webView.url.toString()) {
                    



                    prevLink = binding.webView.url.toString()
                    getLinks(prevLink)
                    Log.d(TAG, "onLoadResource: ${binding.webView.url}")
                    Log.d(TAG, "onLoadResource: ${binding.webView.title}")


                    binding.searchTextField.setText(prevLink)

                }

               //binding.searchTextField.setText(prevLink)


            }


        }




    }

    private fun initListeners() {

/*        binding.icBack.setOnClickListener() {
            try {

                findNavController().popBackStack(R.id.browserAppSelectionFragment,false)

            } catch (_: java.lang.IllegalStateException) {
            } catch (_: Exception) {
            }

        }*/



    }

/*    private fun showDialog(list:MutableList<DlpLinks>){

        activity?.let { context ->


            val bottomSheetBinding by lazy { ResolutionSelectionBottomSheetBinding.inflate(layoutInflater) }
            resolutionBottomSheetDialog = BottomSheetDialog(context)
            resolutionBottomSheetDialog?.setContentView(bottomSheetBinding.root)
            resolutionBottomSheetDialog?.setCanceledOnTouchOutside(true)
            resolutionBottomSheetDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            Log.d(TAG, "showDialog: ${list.size}")
            if(list.isEmpty())
                return

            if (!isResolutionDialOpen) {
                resolutionBottomSheetDialog?.show()


                isResolutionDialOpen = true
            }

            resolutionBottomSheetDialog?.setOnDismissListener {
                isResolutionDialOpen = false

            }

            with(bottomSheetBinding) {

                val formatAdapter = ResolutionSelectionAdapter()


                ivClose.setOnClickListener {
                    resolutionBottomSheetDialog?.dismiss()
                }



                formatAdapter.setData(list)

                rv.adapter = formatAdapter

                formatAdapter.itemClicked = object : ResolutionSelectionAdapter.ResolutionClicked{
                    override fun resolutionClicked(data:DlpLinks) {
                        Log.d(TAG, "resolutionClicked: ${data.link.toString()}")
                        resolutionBottomSheetDialog?.dismiss()
                        playVideo(data.link)
                    }
                }




            }

        }


    }



    fun showMenu(itemview: View, url: String, x: Float, y: Float) {

        val customView = BrowserPopupMenuBinding.inflate(layoutInflater)
        var moreDetailsPopUp = PopupWindow(requireContext())
        moreDetailsPopUp.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        moreDetailsPopUp.isOutsideTouchable = true
        moreDetailsPopUp.elevation = 5.0f
        moreDetailsPopUp.contentView = customView.root


        customView.refresh.setOnOneClickListener(){

            moreDetailsPopUp.dismiss()
            binding.searchTextField.setText(binding.webView.url.toString())
            var link = binding.searchTextField.text.toString()

            prevLink = ""
            binding.webView.loadUrl(link)
            //Toast.makeText(requireContext(),"Refreshed",Toast.LENGTH_LONG).show()
        }

        customView.icHistory.setOnOneClickListener(){

            moreDetailsPopUp.dismiss()
            findNavController().navigate(R.id.action_webViewFragment_to_historyFragment)

        }

        customView.bookmarks.setOnOneClickListener(){

            moreDetailsPopUp.dismiss()
            findNavController().navigate(R.id.action_webViewFragment_to_bookmarksFragment)
        }

        customView.adToBookmarks.setOnClickListener(){

            viewModel.insertBookmark(BookmarksData(binding.webView.url.toString(),binding.webView.title?:"video", iconUrl,getCurrentTime()))
            moreDetailsPopUp.dismiss()

        }

        customView.icResolution.setOnClickListener(){

            getLinks(prevLink)
            *//*var resLink = binding.searchTextField.text.toString()
            if(resLink.isNotEmpty() && isValidUrl(resLink)){
                getLinks(resLink)
            }*//*

            moreDetailsPopUp.dismiss()

        }

        moreDetailsPopUp.showAsDropDown(itemview)
    }*/

    /***
     * / Views Handling Functions...
     */


    /***
     * Other Functionality...
     */


    fun getLinks(link: String) {

        Log.d(TAG, "getLinks: ${link.toString()}")
        /*var retrofit = Retrofit.Builder()
            .baseUrl("https://downloaderv2.funsol.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var api = retrofit.create(IrRemote::class.java)


        if (link.contains("youtube")) {

            var call =
                api.getLinkYoutube(RequestBody.create("text/plain".toMediaType(), link.toString()))


            Log.d(TAG, "getLinks: ${call.request().headers.toString()}")
            call.enqueue(object : Callback<YoutubeResponse> {
                @SuppressLint("ResourceType")
                override fun onResponse(
                    call: Call<YoutubeResponse>,
                    response: Response<YoutubeResponse>
                ) {
                    Log.d(TAG, "onResponse: ${response.code().toString()}")
                    Log.d(TAG, "onResponse: ${response.message().toString()}")
                    Log.d(TAG, "onResponse: ${response.body()?.data?.mp4_links.toString()}")



                    if (response.body()?.data?.mp4_links != null && response.body()?.data?.mp4_links!!.isNotEmpty()) {

                        var k = response.body()!!.data!!.mp4_links.filter {
                            it.endsWith(".mp4") || it.contains("rr")
                        }.toMutableList()

                        Log.d(TAG, "links ${k.toString()}")


                        showDialog(k.toDlpLinks())



                        //playVideo(response.body()?.data?.mp4_links!!.get(0))
                    }


                }

                override fun onFailure(call: Call<YoutubeResponse>, t: Throwable) {


                    Log.d(TAG, "onFailure: ${call.request().headers.toString()}")
                }
            })

        }*/
        
        activity.let{
            if (it is BrowserActivity){
                it.getDownloadMetaData(link)
            }
        }

    }


    





    private fun configureBackPress() {
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                try {


                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {

                        findNavController().popBackStack(R.id.fragment_browser_app_selection,false)

                    }


                } catch (_: java.lang.IllegalStateException) {
                } catch (_: Exception) {
                }

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, backPressedCallback!!
        )
    }

    /***
     * / Other Functionality...
     */

    companion object {

        var isResolutionDialOpen = false
        var isDevicesDialOpen = false
        var websiteToLoad:String? = null
        private const val TAG = "WEB_VIEW_FRAGMENT"
        private const val TAG_TIME = "WEB_VIEW_FRAGMENT_TIME"


    }
}