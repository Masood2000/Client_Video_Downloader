package com.example.apiproject.ui.fragments

import android.app.Dialog
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.R
import com.example.apiproject.core.ads.admob.AdLoadListener
import com.example.apiproject.core.ads.admob.InterstitialHelper
import com.example.apiproject.core.ads.admob.NativePreLoadAdManager
import com.example.apiproject.core.remoteconfig.RemoteConfig
import com.example.apiproject.data.preferences.SharedPreference
import com.example.apiproject.databinding.ExitSheetBinding
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.databinding.FragmentHomeBinding
import com.example.apiproject.ui.activity.BrowserCastingActivity

import com.example.apiproject.ui.activity.MainActivity.Companion
import com.example.apiproject.ui.base.BaseFragment
import com.example.apiproject.util.ApplicationConstants.isSplash
import com.example.apiproject.util.Helper.setOnOneClickListener
import com.example.apiproject.util.NetworkHelper
import com.example.apiproject.util.SocialMediaType
import com.example.videodownloader.core.ads.NativeLayout
import com.google.android.gms.ads.nativead.NativeAd
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.descriptors.StructureKind
import kotlin.system.exitProcess

@AndroidEntryPoint
class HomeFragment : BaseFragment() {



    var TAG = "HomeFragment"

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private var dialogExit: Dialog? = null

    lateinit var preferences: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            if (it is MainActivity) {
                it.loadAndShowBannerAd()

                if(it.linkFromDeepLink.isNotEmpty()){

                    it.getDownloadMetaData(it.linkFromDeepLink)
                    it.linkFromDeepLink = ""

                }
                else{

                    it.handleResume()
                }

            }
        }

        //loading exit native ad
        if(RemoteConfig.show_exit_native_ad){
            activity?.let {
                NativePreLoadAdManager.loadExitAd(
                    it,
                    RemoteConfig.admob_exit_native_id,
                    "Exit"
                )
            }
        }

        preferences = SharedPreference(requireContext())


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences.setIsNewUser(false)

        isSplash = false

        Log.d(TAG, "onViewCreated: ${preferences.isNewUser()}")

        loadAndShowHomeNativeAd()


        initListeners()

    }

    override fun setViewBinding(): View {
        return binding.root
    }



    override fun initView() {


        activity.let{
            if(it is MainActivity){
                it.showTopAndBottomBar()
            }
        }
        with(binding){
            downloadbutton.setOnOneClickListener {
                if(linkfield.text.toString().isEmpty()){
                    Toast.makeText(requireContext(), "Please enter a link", Toast.LENGTH_SHORT).show()
                    return@setOnOneClickListener
                }
                else {
                    try {
                        val imm =
                            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                        imm!!.hideSoftInputFromWindow(
                            requireActivity().currentFocus?.getWindowToken(),
                            0
                        )

                        if (NetworkHelper.isInternetConnectionAvailable(requireContext())) {
                            activity?.let {
                                if (it is MainActivity) {
                                    it.getDownloadMetaData(linkfield.text.toString())
                                }
                            }

                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Please check your internet connection",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } catch (e: java.lang.Exception) {
                    } catch (e: Exception) {
                    }

                }
            }
            pasteText.setOnClickListener {
                Log.d(TAG, "initView: paste clicked")
                activity.let {
                    if (it is MainActivity) {
                        if (it.clipboardManager != null) {
                            if (it.clipboardManager?.hasPrimaryClip() == true && it.clipboardManager?.primaryClipDescription?.hasMimeType(
                                    "text/*"
                                ) == true
                            ) {
                                val link = it.clipboardManager?.primaryClip?.getItemAt(0)?.text
                                Log.d(TAG, "initListeners: $link ")
                                binding.linkfield.setText(link)

                            }
                        }
                    }
                }

                pasteText.visibility=View.GONE
                clear.isVisible=true
            }
            clear.setOnClickListener {
                Log.d(TAG, "initView: clear clicked")
                linkfield.setText("")
                pasteText.visibility=View.VISIBLE
                clear.isVisible=false
            }
            linkfield.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    pasteText.visibility=View.GONE
                    clear.isVisible=true
                }

                override fun afterTextChanged(s: Editable?) {
                    if(s?.toString()?.isEmpty() == true){
                        pasteText.visibility=View.VISIBLE
                        clear.isVisible=false
                    }

                }

            })

/*            btnBrowser.setOnClickListener {
                //startActivity(Intent(requireContext(), BrowserCastingActivity::class.java))
                findNavController().navigate(R.id.action_homeFragment_to_watchReelsFragment)


            }
           */



        }


    }

    fun initListeners(){

        activity?.let {
            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

                if(RemoteConfig.show_exit_Interstitial_ad){
                    InterstitialHelper.showAndLoadInterstitial(
                        it,
                        it.getString(R.string.interstitial_inner),
                        true,
                        useCapping = RemoteConfig.admob_exit_interstitial_capping,
                        "exit",
                        object : InterstitialHelper.InterstitialAdShowListener {
                            override fun onInterstitialAdImpression() {
                                super.onInterstitialAdImpression()
                                showExitDialog()
                            }

                            override fun onInterstitialAdNull() {
                                //postAnalytic("exit_interstitial_null")
                                showExitDialog()
                            }
                        }
                    )

                }
                else{
                    showExitDialog()
                }



            }
        }

    }

    override fun lazyLoad() {
    }

    override fun onResume() {
        super.onResume()

    }

    /***
     * Ads Related Functions
     */
    private fun loadAndShowHomeNativeAd() {

        //Remote config need to be fixed
        if(/*RemoteConfig.show_home_native_ad*/true){
            Log.d(TAG, "loadAndShowHomeNativeAd: ")
            activity?.let {
                if (it is MainActivity) {

                    val myAdLoadListener = object : AdLoadListener {

                        override fun onHomeAdLoaded(nativeAd: NativeAd?) {
                            super.onHomeAdLoaded(nativeAd)

                            NativePreLoadAdManager.populateUnifiedNativeAdView(
                                NativePreLoadAdManager.getHomeAd(),
                                it,
                                binding.admobParentContainer,
                                binding.admobNativeContainer,
                                NativeLayout.NATIVE_7B,
                                "Home",
                                RemoteConfig.admob_native_home_cta_round,
                                RemoteConfig.admob_native_home_cta_color,
                                RemoteConfig.admob_native_home_cta_text_color,
                            )

                        }

                        override fun onHomeAdFailedToLoad(error: String) {
                            super.onHomeAdFailedToLoad(error)

                            binding.admobParentContainer.visibility = View.GONE

                        }
                    }

                    if (NativePreLoadAdManager.getHomeAd() != null) {
                        NativePreLoadAdManager.populateUnifiedNativeAdView(
                            NativePreLoadAdManager.nativeHomeAd,
                            it,
                            binding.admobParentContainer,
                            binding.admobNativeContainer,
                            NativeLayout.NATIVE_7B,
                            "Home",
                            RemoteConfig.admob_native_home_cta_round,
                            RemoteConfig.admob_native_home_cta_color,
                            RemoteConfig.admob_native_home_cta_text_color,
                        )
                    } else if (NativePreLoadAdManager.isHomeAdLoading()) {
                        NativePreLoadAdManager.adLoadListener = myAdLoadListener
                    } else {
                        binding.admobParentContainer.visibility = View.GONE
                    }
                }
            }
        }
        else{
            binding.admobParentContainer.isVisible = false
        }



    }


    private fun showExitDialog() {
        activity?.let {
            if (dialogExit == null)
                dialogExit = Dialog(it)
            val dialogConfirmationBinding = ExitSheetBinding.inflate(layoutInflater)

            dialogExit?.let { dialog ->
                dialog.window?.setLayout(
                    RecyclerView.LayoutParams.WRAP_CONTENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
                )
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setContentView(dialogConfirmationBinding.root)

                with(dialogConfirmationBinding) {
                    cardExit.setOnOneClickListener {
                        dialogExit?.dismiss()
                        //postAnalytic("tap_to_exit_clicked")
                        it.finish()
                        exitProcess(0)
                    }
                    cardCancel.setOnOneClickListener {
                        dialogExit?.dismiss()
                       // postAnalytic("exit_dlg_cncld")
                    }
                    dialog.show()


                    if(RemoteConfig.show_exit_native_ad){
                        if (NativePreLoadAdManager.getExitAd() != null) {
                            NativePreLoadAdManager.populateUnifiedNativeAdView(
                                NativePreLoadAdManager.getExitAd(),
                                it,
                                layoutNativeContainer.parentNativeContainer,
                                layoutNativeContainer.admobNativeContainer,
                                NativeLayout.NATIVE_6A,
                                "Exit",
                                RemoteConfig.admob_native_exit_cta_round,
                                RemoteConfig.admob_native_exit_cta_color,
                                RemoteConfig.admob_native_exit_cta_text_color,
                            )
                        }
                        else if (NativePreLoadAdManager.isExitAdLoading()) {
                            NativePreLoadAdManager.adLoadListener = object : AdLoadListener {
                                override fun onExitAdFailedToLoad(error: String) {
                                    super.onExitAdFailedToLoad(error)
                                    layoutNativeContainer.root.isVisible = false
                                }

                                override fun onExitAdLoaded(nativeAd: NativeAd?) {
                                    super.onExitAdLoaded(nativeAd)
                                    NativePreLoadAdManager.populateUnifiedNativeAdView(
                                        NativePreLoadAdManager.getExitAd(),
                                        it,
                                        layoutNativeContainer.parentNativeContainer,
                                        layoutNativeContainer.admobNativeContainer,
                                        NativeLayout.NATIVE_6A,
                                        "Exit",
                                        RemoteConfig.admob_native_exit_cta_round,
                                        RemoteConfig.admob_native_exit_cta_color,
                                        RemoteConfig.admob_native_exit_cta_text_color,
                                    )
                                }
                            }
                        }
                        else {
                            NativePreLoadAdManager.adLoadListener = object : AdLoadListener {
                                override fun onExitAdFailedToLoad(error: String) {
                                    super.onExitAdFailedToLoad(error)
                                    layoutNativeContainer.root.isVisible = false
                                }

                                override fun onExitAdLoaded(nativeAd: NativeAd?) {
                                    super.onExitAdLoaded(nativeAd)
                                    NativePreLoadAdManager.populateUnifiedNativeAdView(
                                        NativePreLoadAdManager.getExitAd(),
                                        it,
                                        layoutNativeContainer.parentNativeContainer,
                                        layoutNativeContainer.admobNativeContainer,
                                        NativeLayout.NATIVE_6A,
                                        "Exit",
                                        RemoteConfig.admob_native_exit_cta_round,
                                        RemoteConfig.admob_native_exit_cta_color,
                                        RemoteConfig.admob_native_exit_cta_text_color,
                                    )
                                }
                            }
                            NativePreLoadAdManager.loadExitAd(
                                it,
                                RemoteConfig.admob_exit_native_id,
                                "Exit"
                            )
                        }
                    }
                    else{
                        layoutNativeContainer.parentNativeContainer.isVisible = false
                    }



                }

            }
        }
    }

    companion object {
        private const val TAG = "HOME_FRAGMENT"
    }

}


