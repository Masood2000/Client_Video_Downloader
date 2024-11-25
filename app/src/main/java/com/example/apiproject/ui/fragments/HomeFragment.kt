package com.example.apiproject.ui.fragments

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.databinding.FragmentHomeBinding
import com.example.apiproject.ui.activity.BrowserActivity

import com.example.apiproject.ui.activity.MainActivity.Companion
import com.example.apiproject.ui.base.BaseFragment
import com.example.apiproject.util.Helper.setOnOneClickListener
import com.example.apiproject.util.NetworkHelper
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

    override fun setViewBinding(): View {
        return binding.root
    }

    override fun initView() {


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

            btnMasood.setOnClickListener {
                startActivity(Intent(requireContext(), BrowserActivity::class.java))

            }
        }

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

    }
    
}


