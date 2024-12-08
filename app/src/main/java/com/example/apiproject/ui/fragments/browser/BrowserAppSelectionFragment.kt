package com.example.universaltvremote.presentation.fragment.bowserCasting

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.webkit.URLUtil.isValidUrl
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apiproject.R
import com.example.apiproject.databinding.FragmentBroswerAppSelectionBinding
import com.example.apiproject.ui.activity.BrowserActivity
import com.example.apiproject.ui.fragments.browser.WebViewFragment
import com.example.apiproject.util.Helper.setOnOneClickListener

import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BrowserAppSelectionFragment : Fragment() {

    val binding by lazy {
        FragmentBroswerAppSelectionBinding.inflate(layoutInflater)
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


        binding.searchTextField.setOnEditorActionListener(object : OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    var url = binding.searchTextField.text.toString()

                    val newQuery = if (isValidUrl(url)) {
                        //formatUrl(url)
                        url
                    } else {
                        //constructSearchUrl(url)
                        url
                    }


                    WebViewFragment.websiteToLoad = newQuery

                    binding.searchTextField.text?.clear()

                    val bundle = Bundle()
                    bundle.putString("website_type", "general")
                    findNavController().navigate(R.id.fragment_web_view, bundle)


                    Toast.makeText(
                        requireContext(),
                        binding.searchTextField.text.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                return false
            }
        })

        /* binding.icBack.setOnClickListener() {
             try {

                 try {

                     activity.let {
                         if (it is BrowserActivity) {
                             if (findNavController().currentDestination?.id == R.id.fragment_browser_app_selection) {
                                 it.finish()
                             }
                         }
                     }
                     *//*findNavController().popBackStack()*//*

                } catch (_: java.lang.IllegalStateException) {
                } catch (_: Exception) {
                }

            } catch (_: java.lang.IllegalStateException) {
            } catch (_: Exception) {
            }
        }
*/


        binding.clYoutube.setOnOneClickListener {

            val bundle = Bundle()
            bundle.putString("website_type", "youtube")
            findNavController().navigate(R.id.fragment_web_view, bundle)
        }

        binding.clPin.setOnOneClickListener {

            val bundle = Bundle()
            bundle.putString("website_type", "pinterest")
            findNavController().navigate(R.id.fragment_web_view, bundle)
        }

        binding.clVimeo.setOnOneClickListener {

            val bundle = Bundle()
            bundle.putString("website_type", "vimeo")
            findNavController().navigate(R.id.fragment_web_view, bundle)
        }

        binding.clTwitch.setOnOneClickListener {

            val bundle = Bundle()
            bundle.putString("website_type", "twitch")
            findNavController().navigate(R.id.fragment_web_view, bundle)
        }

        binding.clDailymotion.setOnOneClickListener {

            val bundle = Bundle()
            bundle.putString("website_type", "dailymotion")
            findNavController().navigate(R.id.fragment_web_view, bundle)
        }

        binding.clFb.setOnOneClickListener {

            val bundle = Bundle()
            bundle.putString("website_type", "facebook")
            findNavController().navigate(R.id.fragment_web_view, bundle)
        }

        binding.icGoogle.setOnOneClickListener {

            val bundle = Bundle()
            bundle.putString("website_type", "google")
            findNavController().navigate(R.id.fragment_web_view, bundle)
        }

        binding.icReddit.setOnOneClickListener {

            val bundle = Bundle()
            bundle.putString("website_type", "reddit")
            findNavController().navigate(R.id.fragment_web_view, bundle)
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
                        if (it is BrowserActivity) {
                            if (findNavController().currentDestination?.id == R.id.fragment_browser_app_selection)
                                it.finish()
                        }
                    }
                }
                /*findNavController().popBackStack()*/
                catch (_: java.lang.IllegalStateException) {
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