package com.example.apiproject.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import com.example.apiproject.R
import com.example.apiproject.databinding.FragmentSplashBinding
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.ui.base.BaseFragment
import com.example.apiproject.util.ApplicationConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.exitProcess


class SplashFragment : BaseFragment() {
    private val binding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }
    private var counter = 0
    private var loadingDelay: Long = 60
    private var boolNewUser = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApplicationConstants.isSplash = true

        Log.d(TAG, "$TAG onCreate: Called")
    }

    private fun startFragment() {
        Log.d(TAG, "$TAG startFragment: Called")
        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                withContext(Dispatchers.Main) {
                    binding.progress.setProgress(counter, true)
                }
                counter += 1
                delay(loadingDelay)


                if (counter >= 100) {
                    withContext(Dispatchers.Main) {
                        navigateFragment()
                    }
                    break
                }
            }
        }
        binding.splashTitle.animation =
            android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        Log.d(TAG, "$TAG onCreateView: Called")



    }


    override fun setViewBinding(): View {
        return binding.root
    }

    override fun initView() {

        startFragment()

        activity?.let {
            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner){
                it.finish()
                exitProcess(0)
            }
        }

    }

    override fun lazyLoad() {

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        if (counter >= 100) {
            navigateFragment()
        }
    }


    private fun navigateFragment() {

        if (findNavController().currentDestination?.id == R.id.splashFragment) {

            activity?.let {
                if (it is MainActivity) {
                   if(it.onSplashLinkDetected){
                       findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                       it.handleResume()

                   }
                    else{
                          findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                    }
                }
            }

        }


    }


    companion object {
        private const val TAG = "SPLASH_FRAGMENT"
    }
}