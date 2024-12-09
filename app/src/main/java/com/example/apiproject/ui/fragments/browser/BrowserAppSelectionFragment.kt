package com.example.apiproject.ui.fragments.browser

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apiproject.R
import com.example.apiproject.data.models.NavigationBundleKeys.DAILYMOTION
import com.example.apiproject.data.models.NavigationBundleKeys.FB
import com.example.apiproject.data.models.NavigationBundleKeys.GENERAL
import com.example.apiproject.data.models.NavigationBundleKeys.GOOGLE
import com.example.apiproject.data.models.NavigationBundleKeys.PINTEREST
import com.example.apiproject.data.models.NavigationBundleKeys.REDDIT
import com.example.apiproject.data.models.NavigationBundleKeys.TWITCH
import com.example.apiproject.data.models.NavigationBundleKeys.VIMEO
import com.example.apiproject.data.models.NavigationBundleKeys.WEBSITE_TYPE
import com.example.apiproject.data.models.NavigationBundleKeys.YOUTUBE
import com.example.apiproject.databinding.FragmentBrowserAppSelectionBinding
import com.example.apiproject.ui.activity.BrowserCastingActivity
import com.example.apiproject.util.Helper.setOnOneClickListener
import com.example.apiproject.util.pushdown.PushDownAnim

import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BrowserAppSelectionFragment : Fragment() {

    val binding by lazy {
        FragmentBrowserAppSelectionBinding.inflate(layoutInflater)
    }

    private var backPressedCallback: OnBackPressedCallback? = null

    var devicesBottomSheetDialog: BottomSheetDialog? = null




    /***
     * LifeCycle Related Functions
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        configureBackPress()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: called...")


        initViews();
        initListeners();
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.searchTextField.text?.clear()

        Log.d(TAG, "onDestroyView: called...")

        backPressedCallback?.remove()
        backPressedCallback = null

    }

    /***
     * /LifeCycle Related Functions
     */


    /***
     * Views Handling Functions...
     */

    private fun initViews() {

        Log.d(TAG, "initViews")

    }

    private fun initListeners() {


        binding.searchTextField.setOnEditorActionListener(object :OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){

                    var url = binding.searchTextField.text.toString()

                    val newQuery = url/*if (isValidUrl(url)) {
                        formatUrl(url)
                    }
                    else {
                        constructSearchUrl(url)
                    }*/


                    WebViewFragment.websiteToLoad = newQuery

                    binding.searchTextField.text?.clear()

                    val bundle = Bundle()
                    bundle.putString(WEBSITE_TYPE, GENERAL)
                    findNavController().navigate(R.id.webViewFragment,bundle)


                    Toast.makeText(requireContext(),binding.searchTextField.text.toString(),Toast.LENGTH_LONG).show()
                }
               return  false
            }
        })

        binding.icBack.setOnClickListener() {
            try {

                try {

                    activity.let {
                        if (it is BrowserCastingActivity) {
                            if (findNavController().currentDestination?.id == R.id.browserAppSelectionFragment) {
                                it.finish()
                            }
                        }
                    }
                    /*findNavController().popBackStack()*/

                } catch (_: java.lang.IllegalStateException) {
                } catch (_: Exception) {
                }

            } catch (_: java.lang.IllegalStateException) {
            } catch (_: Exception) {
            }
        }



        binding.icMore.setOnOneClickListener {
            showMenu(binding.icMore, "hello", 1.toFloat(), 1.toFloat())
        }


        PushDownAnim.setPushDownAnimTo(binding.clYoutube).setOnClickListener {

            val bundle = Bundle()
            bundle.putString(WEBSITE_TYPE, YOUTUBE)
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        PushDownAnim.setPushDownAnimTo(binding.clPin).setOnClickListener {

            val bundle = Bundle()
            bundle.putString(WEBSITE_TYPE, PINTEREST)
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        PushDownAnim.setPushDownAnimTo(binding.clVimeo).setOnClickListener {

            val bundle = Bundle()
            bundle.putString(WEBSITE_TYPE, VIMEO)
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        PushDownAnim.setPushDownAnimTo(binding.clTwitch).setOnClickListener {

            val bundle = Bundle()
            bundle.putString(WEBSITE_TYPE, TWITCH)
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        PushDownAnim.setPushDownAnimTo(binding.clDailymotion).setOnClickListener {

            val bundle = Bundle()
            bundle.putString(WEBSITE_TYPE, DAILYMOTION)
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        PushDownAnim.setPushDownAnimTo(binding.clFb).setOnClickListener {

            val bundle = Bundle()
            bundle.putString(WEBSITE_TYPE, FB)
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        PushDownAnim.setPushDownAnimTo(binding.icGoogle).setOnClickListener {

            val bundle = Bundle()
            bundle.putString(WEBSITE_TYPE, GOOGLE)
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        PushDownAnim.setPushDownAnimTo(binding.icReddit).setOnClickListener {

            val bundle = Bundle()
            bundle.putString(WEBSITE_TYPE, REDDIT)
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        /*binding.searchTextField.setOnFocusChangeListener { view, b ->

            if(b){
                binding.icSearchIcon.visibility = View.GONE
            }
            else{
                if(binding.searchTextField.text.toString().isEmpty())
                    binding.icSearchIcon.visibility = View.VISIBLE
            }

        }*/


    }



    fun showMenu(itemview: View, url: String, x: Float, y: Float) {

       /* val customView = BrowserPopupMenuBinding.inflate(layoutInflater)
        var moreDetailsPopUp = PopupWindow(requireContext())
        moreDetailsPopUp.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        moreDetailsPopUp.isOutsideTouchable = true
        moreDetailsPopUp.elevation = 5.0f
        moreDetailsPopUp.contentView = customView.root


        customView.icResolution.visibility = View.GONE

        customView.adToBookmarks.visibility = View.GONE

        customView.refresh.setOnOneClickListener(){

            moreDetailsPopUp.dismiss()
            Toast.makeText(requireContext(),"Refreshed",Toast.LENGTH_LONG).show()
        }

        customView.icHistory.setOnOneClickListener(){

            moreDetailsPopUp.dismiss()
            findNavController().navigate(R.id.action_browserAppSelectionFragment_to_historyFragment)
        }

        customView.bookmarks.setOnOneClickListener(){

            moreDetailsPopUp.dismiss()
            findNavController().navigate(R.id.action_browserAppSelectionFragment_to_bookmarksFragment)
        }


        moreDetailsPopUp.showAsDropDown(itemview)*/
    }

    /***
     * / Views Handling Functions...
     */


    /***
     * Other Functionality...
     */

    private fun configureBackPress() {
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                try {

                    activity.let {
                        if (it is BrowserCastingActivity) {
                            if (findNavController().currentDestination?.id == R.id.browserAppSelectionFragment) {
                                it.finish()
                            }
                        }
                    }
                    /*findNavController().popBackStack()*/

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

        var isDevicesDialOpen = false
        private const val TAG = "BROWSER_APP_SELECTION_FRAGMENT"


    }

}