package com.example.apiproject.ui.fragments.browser

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import com.example.apiproject.data.models.NavigationBundleKeys
import com.example.apiproject.data.models.NavigationBundleKeys.GENERAL
import com.example.apiproject.data.models.NavigationBundleKeys.INSTA
import com.example.apiproject.data.models.NavigationBundleKeys.WEBSITE_TYPE
import com.example.apiproject.data.models.NavigationBundleKeys.YOUTUBE


import com.google.android.material.bottomsheet.BottomSheetDialog

import com.example.apiproject.R
import com.example.apiproject.databinding.FragmentWebViewBinding

import com.example.apiproject.data.models.NavigationBundleKeys.DAILYMOTION
import com.example.apiproject.data.models.NavigationBundleKeys.FB
import com.example.apiproject.data.models.NavigationBundleKeys.GOOGLE
import com.example.apiproject.data.models.NavigationBundleKeys.NONE
import com.example.apiproject.data.models.NavigationBundleKeys.PINTEREST
import com.example.apiproject.data.models.NavigationBundleKeys.REDDIT
import com.example.apiproject.data.models.NavigationBundleKeys.TWITCH
import com.example.apiproject.data.models.NavigationBundleKeys.VIMEO

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



    private var websiteType = GENERAL

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
            if (it?.getString(WEBSITE_TYPE) != null) {
                websiteType = it.getString(WEBSITE_TYPE).toString()
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

                GENERAL -> {
                    if(websiteToLoad !=null )
                        binding.webView.loadUrl(websiteToLoad!!)
                    else
                        binding.webView.loadUrl("https://www.google.com")
                    websiteToLoad = null
                }

                INSTA -> {
                    binding.webView.loadUrl("https://www.instagram.com/explore/")
                }

                YOUTUBE -> {
                    binding.webView.loadUrl(" https://www.youtube.com/")
                }

                GOOGLE -> {
                    binding.webView.loadUrl("https://www.google.com")
                }

                PINTEREST -> {
                    binding.webView.loadUrl("https://www.pinterest.com/videos/")
                }

                VIMEO -> {
                    binding.webView.loadUrl("https://vimeo.com/watch")
                }

                TWITCH -> {
                    binding.webView.loadUrl("https://www.twitch.tv/")
                }

                FB -> {
                    binding.webView.loadUrl("https://www.facebook.com/watch/")
                }

                REDDIT -> {
                    binding.webView.loadUrl("https://www.reddit.com/")
                }

                DAILYMOTION -> {
                    binding.webView.loadUrl("https://www.dailymotion.com/")
                }

                NONE -> {

                }

                else -> {
                    //binding.webView.loadUrl("https://www.google.com")
                    Log.d(TAG, "onViewCreated: not loading any website")
                }


            }

            websiteType = NavigationBundleKeys.NONE
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


                    //getLinks(prevLink)
                    Log.d(TAG, "onLoadResource: ${binding.webView.url}")
                    Log.d(TAG, "onLoadResource: ${binding.webView.title}")


                    binding.searchTextField.setText(prevLink)

                }

               //binding.searchTextField.setText(prevLink)


            }


        }




    }

    private fun initListeners() {

        binding.icBack.setOnClickListener() {
            try {

                findNavController().popBackStack(R.id.browserAppSelectionFragment,false)

            } catch (_: java.lang.IllegalStateException) {
            } catch (_: Exception) {
            }

        }


        binding.icMore.setOnClickListener {
            showMenu(binding.icMore, "hello", 1.toFloat(), 1.toFloat())
        }

    }

 /*   private fun showDialog(list:MutableList<DlpLinks>){

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


    }*/

   /* private fun showDialogForDevices(){


        isResolutionDialOpen = false

        Log.d(TAG, "showDialogForDevices: opening")
        viewModel.getDevices()

        activity?.let { context ->


            val bottomSheetConnectableDevicesBinding by lazy { FragmentConnectableDevicesForBrowserBinding.inflate(layoutInflater) }

            devicesBottomSheetDialog = BottomSheetDialog(context)
            devicesBottomSheetDialog?.setContentView(bottomSheetConnectableDevicesBinding.root)
            devicesBottomSheetDialog?.setCanceledOnTouchOutside(true)
            devicesBottomSheetDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

           

            bottomSheetConnectableDevicesBinding.icRefresh.setOnClickListener {

                viewModel.getDevices()
                Log.d("CDF_TAG", "onViewCreated: clicked not place holder ")
                activity?.let {
                    if (it is MainActivity) {
                        it.postAnalytic("refreshing_remote")
                    }
                }
                lifecycleScope.launch {
                   adapter.setData(listOf())
                    delay(500)


                    withContext(Dispatchers.Main){
                        adapter.setData(
                            viewModel.devices.value)

                        bottomSheetConnectableDevicesBinding.tvCnt.text = "Found ${viewModel.devices.value.size} Device(s)."
                    }

//                viewModel.getDevices()
//                adapter.setData(DiscoveryManager.getInstance().allDevices.values.toList())

                }
            }
            bottomSheetConnectableDevicesBinding.icClose.setOnClickListener(){

                isDevicesDialOpen = false
                devicesBottomSheetDialog?.dismiss()

            }

            Log.d(TAG, "showDialogForDevices: ${isDevicesDialOpen}")
            if (!isDevicesDialOpen && !isResolutionDialOpen) {
                devicesBottomSheetDialog?.show()


                isDevicesDialOpen = true
            }

            devicesBottomSheetDialog?.setOnDismissListener {
                isDevicesDialOpen = false

            }
            bottomSheetConnectableDevicesBinding.tvCnt.text = "Found ${viewModel.devices.value.size} Device(s)."
            adapter.setData(viewModel.devices.value)
            *//***
             * NEW_FLOW_WORK [/1] TODO
             * Date 14 Oct 2024 Monday
             *//*

            //Log.d("LastFragment", "onViewCreated: "+findNavController().previousBackStackEntry?.destination?.displayName)
            if (findNavController().previousBackStackEntry?.destination == null || findNavController().previousBackStackEntry?.destination?.id == R.id.splashFragment || findNavController().previousBackStackEntry?.destination?.id == R.id.tvBrandsFragment || findNavController().previousBackStackEntry?.destination?.id == R.id.loadingAnimationFragment || (UserSelection.openedHomeScreenBrands == true)) {

                adapter.clickListener = object : DeviceClickListener {
                    override fun successfullyConnected(device: ConnectableDevice, serviceName: String) {
                        UserSelection.openedSavedRemote = false
                        ConnectedDevice.device = device
                        Log.d("Connection", "successfullyConnected: 1 ")

                        activity?.let {
                            if (it is BrowserCastingActivity) {

                                if (findNavController().currentDestination?.id == R.id.connectableDevicesForBrowserFragment) {

                                    device.addListener(object : ConnectableDeviceListener {
                                        override fun onDeviceReady(device: ConnectableDevice?) {
                                            Toast.makeText(
                                                requireContext(),
                                                getString(R.string.connection_successful4),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            ConnectedDevice.device = device
                                            *//* if(mediaURL!=""){
                                                 playVideo(mediaURL)
                                             }*//*

                                            //findNavController().popBackStack()
                                        }

                                        override fun onDeviceDisconnected(device: ConnectableDevice?) {
                                        }

                                        override fun onPairingRequired(
                                            device: ConnectableDevice?,
                                            service: DeviceService?,
                                            pairingType: DeviceService.PairingType?
                                        ) {

                                        }

                                        override fun onCapabilityUpdated(
                                            device: ConnectableDevice?,
                                            added: MutableList<String>?,
                                            removed: MutableList<String>?
                                        ) {

                                        }

                                        override fun onConnectionFailed(
                                            device: ConnectableDevice?,
                                            error: ServiceCommandError?
                                        ) {
                                            Toast.makeText(
                                                requireContext(),
                                                getString(R.string.connection_failed4),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                    })
                                    try {
                                        device.connect()
                                    } catch (e: Exception) {
                                        Toast.makeText(
                                            requireContext(),
                                            getString(R.string.error_while_connecting4),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }


                                }


                            }
                        }


                    }
                }
            }
            else {

                var clickListener = (object : DeviceClickListener {
                    override fun successfullyConnected(device: ConnectableDevice, serviceName: String) {
                        ConnectedDevice.device = device
                        Log.d("Connection", "successfullyConnected: 2 ")

                        activity?.let {
                            if (it is BrowserCastingActivity) {

                                if (findNavController().currentDestination?.id == R.id.webViewFragment) {

                                    device.addListener(object : ConnectableDeviceListener {
                                        override fun onDeviceReady(device: ConnectableDevice?) {
                                            Toast.makeText(
                                                requireContext(),
                                                getString(R.string.connection_successful4),
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            Log.d("Connection", "on device ready ")
                                            ConnectedDevice.device = device
                                            if(mediaURL!=""){
                                                playVideo(mediaURL)
                                            }

                                            devicesBottomSheetDialog?.dismiss()



                                        }

                                        override fun onDeviceDisconnected(device: ConnectableDevice?) {
                                            Log.d("Connection", "on device disconnected ")
                                        }

                                        override fun onPairingRequired(
                                            device: ConnectableDevice?,
                                            service: DeviceService?,
                                            pairingType: DeviceService.PairingType?
                                        ) {

                                        }

                                        override fun onCapabilityUpdated(
                                            device: ConnectableDevice?,
                                            added: MutableList<String>?,
                                            removed: MutableList<String>?
                                        ) {

                                        }

                                        override fun onConnectionFailed(
                                            device: ConnectableDevice?,
                                            error: ServiceCommandError?
                                        ) {
                                            Toast.makeText(
                                                requireContext(),
                                                getString(R.string.connection_failed4),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                    })
                                    try {
                                        device.connect()
                                    } catch (e: Exception) {
                                        Toast.makeText(
                                            requireContext(),
                                            getString(R.string.error_while_connecting4),
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        Log.d("Connection", "on error ")
                                    }


                                }


                            }
                        }



                    }
                })
                adapter.clickListener = clickListener
            }
            bottomSheetConnectableDevicesBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            bottomSheetConnectableDevicesBinding.recyclerView.adapter = adapter
            if (findNavController().previousBackStackEntry?.destination?.id == R.id.tvBrandsFragment || findNavController().previousBackStackEntry?.destination?.id == R.id.loadingAnimationFragment || (UserSelection.openedHomeScreenBrands == true)) {
                adapter.showTvDevices = true
            }






        }


    }*/

    fun showMenu(itemview: View, url: String, x: Float, y: Float) {
    /*
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

        moreDetailsPopUp.showAsDropDown(itemview)*/
    }

    /***
     * / Views Handling Functions...
     */


    /***
     * Other Functionality...
     */


    /*fun getLinks(link: String) {

        var retrofit = Retrofit.Builder()
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

        }

    }*/







    private fun configureBackPress() {
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                try {


                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {

                        findNavController().popBackStack(R.id.browserAppSelectionFragment,false)

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