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
    override fun setViewBinding(): View {
        return binding.root
    }

    override fun initView() {

        binding.rateUs.setOnClickListener(){
            showRateUsDialog()
        }

        binding.feedback.setOnClickListener(){
            findNavController().navigate(R.id.action_settingsFragment_to_feedbackFragment)
        }

        binding.privacyPolicy.setOnClickListener {
            // open privacy policy link
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/onetapdownloader/home"))
                startActivity(browserIntent)
            } catch (e: Exception){
                Log.e(TAG, "initView: $e", )
            }

        }

        activity?.let {
            if (it is MainActivity){
                it.onBackPressedDispatcher.addCallback (viewLifecycleOwner){
                    it.moveToFirstItem()
                }
            }
        }



    }

    override fun lazyLoad() {
    }


    private fun showRateUsDialog() {
        activity?.let { context ->
            var currentRating = 4
            if (dialogRateUs == null)
                dialogRateUs = Dialog(context)
            val dialogConfirmationBinding = DialogRateUsBinding.inflate(layoutInflater)

            dialogRateUs?.let { dialog ->
                dialog.setContentView(dialogConfirmationBinding.root)
                dialog.window?.setLayout(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
                )

                with(dialogConfirmationBinding) {

                    fun unselectAll() {
                        start1.isSelected = false
                        start2.isSelected = false
                        start4.isSelected = false
                        start3.isSelected = false
                        start5.isSelected = false
                    }

                    start1.isSelected = true
                    start2.isSelected = true
                    start4.isSelected = true
                    start3.isSelected = true
//                    start5.isSelected = true

                    start1.setOnClickListener {
                        unselectAll()
                        start1.isSelected = true
                        currentRating = 1
                    }

                    start2.setOnClickListener {
                        unselectAll()
                        start1.isSelected = true
                        start2.isSelected = true
                        currentRating = 2
                    }

                    start3.setOnClickListener {
                        unselectAll()
                        start1.isSelected = true
                        start2.isSelected = true
                        start3.isSelected = true
                        currentRating = 3
                    }

                    start4.setOnClickListener {
                        unselectAll()
                        start1.isSelected = true
                        start2.isSelected = true
                        start3.isSelected = true
                        start4.isSelected = true
                        currentRating = 4
                    }



                    start5.setOnClickListener {
                        unselectAll()
                        start1.isSelected = true
                        start2.isSelected = true
                        start3.isSelected = true
                        start4.isSelected = true
                        start5.isSelected = true
                        currentRating = 5
                    }

                    btMaybeLater.setOnClickListener {
                        dialog.dismiss()
                    }

                    close.setOnClickListener {
                        dialog.dismiss()
                    }

                    btSubmit.setOnClickListener {
                        rateUs(context)
                    }

                    dialog.setOnDismissListener {
                        dialogRateUs = null
                    }
                }

                dialog.show()
            }
        }
    }

    private fun rateUs(context: Context) {
        val configString = "to be changed"
        val uri = Uri.parse("market://details?id=$configString")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            context.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$configString")
                )
            )
        }
    }


    companion object {
        private const val TAG = "SettingsFragment"
    }

}