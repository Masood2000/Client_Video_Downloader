package com.example.apiproject.ui.fragments

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.BuildConfig
import com.example.apiproject.R
import com.example.apiproject.databinding.DialogRateUsBinding
import com.example.apiproject.databinding.FragmentSettingsBinding
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.ui.base.BaseFragment

class SettingsFragment : BaseFragment() {

    private var dialogRateUs: Dialog? = null
    private val binding by lazy { FragmentSettingsBinding.inflate(layoutInflater) }

    private var backPressedCallback: OnBackPressedCallback? = null

    private fun configureBackPress() {
        backPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                    findNavController().popBackStack()
            }

        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, backPressedCallback!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backPressedCallback?.remove()
        backPressedCallback = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureBackPress()

        return super.onCreateView(inflater, container, savedInstanceState)



    }

    override fun setViewBinding(): View {
        return binding.root
    }

    override fun initView() {


        binding.feedback.setOnClickListener(){

        }

        binding.privacyPolicy.setOnClickListener {
            // open privacy policy link
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://doc-hosting.flycricket.io/video-downloader-privacy-policy/ae6eb97a-912b-459c-8769-c5516b78115d/privacy "))
                startActivity(browserIntent)
            } catch (e: Exception){
                Log.e(TAG, "initView: $e", )
            }

        }

        activity?.let {
            if (it is MainActivity){
                /*it.onBackPressedDispatcher.addCallback (viewLifecycleOwner){
                    it.moveToFirstItem()
                }*/
            }
        }



    }

    override fun lazyLoad() {
    }




    companion object {
        private const val TAG = "SettingsFragment"
    }

}