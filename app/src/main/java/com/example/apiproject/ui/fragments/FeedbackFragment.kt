package com.example.apiproject.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.apiproject.R
import com.example.apiproject.databinding.FragmentFeedbackBinding
import com.example.apiproject.ui.activity.MainActivity

import com.example.apiproject.ui.base.BaseFragment
import com.google.android.material.chip.Chip


class FeedbackFragment : BaseFragment() {


    private val binding by lazy {
        FragmentFeedbackBinding.inflate(layoutInflater)
    }
    private val checkedList = mutableListOf<String>()


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
        initListeners()
    }

    override fun lazyLoad() {
        //TODO
    }

    private fun initListeners() {


        binding.problemsChipGroup.setOnCheckedStateChangeListener { chipGroup, ints ->
            Log.d("CHIP_TAG", "onCheckedStateChangeListener: $ints")
            ints.forEach { id ->
                val chip: Chip? = chipGroup.findViewById(id)
                chip?.let { chip ->
                    Log.d("CHIP_TAG", "chip ${chip.text} is selected: ${chip.isSelected}")
                    if (chip.isSelected) {
                        chip.isSelected = false
                        chip.setChipBackgroundColorResource(R.color.subtitle_color)
                        checkedList.remove(chip.text.toString())
                    } else {
                        chip.isSelected = true
                        activity?.let {
                            if (it is MainActivity) {
                                chip.setChipBackgroundColorResource(R.color.primary)


                            }
                        }

                        checkedList.add(chip.text.toString())
                    }
                }
            }
        }

        binding.submitButton.setOnClickListener {
            if (checkedList.isEmpty()) {
                Toast.makeText(requireContext(), "Please select a problem", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val description = binding.problemsDescription.text.toString()
                if (description.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please enter a description",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    checkedList.add(description)
                    sendEmail(checkedList.joinToString())
                }
            }

        }

    }

    private fun feedBackShare(context: Context, feedBacksString: String) {

        try {
            val shareIntent = Intent(Intent.ACTION_SEND_MULTIPLE)
            shareIntent.type = "plain/text/images"
            shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("need to be changed"))
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Downloader App")
            shareIntent.putExtra(Intent.EXTRA_TEXT, feedBacksString)
            //shareIntent.setPackage("com.google.android.gm")
            context.startActivity(shareIntent)
        } catch (ex: Exception) {
            print(ex.toString())
        }

    }

    private fun sendEmail(description: String) {
        feedBackShare(requireContext(), description)
    }


    companion object {
        private const val TAG = "FEEDBACK_FRAGMENT"
    }


}

//todo the email is not valid at this point so we have to change it