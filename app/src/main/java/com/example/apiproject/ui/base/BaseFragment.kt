package com.example.apiproject.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apiproject.R
import com.example.apiproject.ui.activity.MainActivity

import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {
    private var isViewPrepare = false
    private var hasLoadData = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setViewBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        initView()
        lazyLoadDataIfPrepared()
    }

    abstract fun setViewBinding(): View

    abstract fun initView()

    abstract fun lazyLoad()

    private fun lazyLoadDataIfPrepared() {
        if (isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    fun createAndShowBottomSheet(view: View, style: Int = R.style.SheetDialog): BottomSheetDialog {
        var bottomSheetDialog = BottomSheetDialog(requireContext(), style)
        bottomSheetDialog.setContentView(view)

        bottomSheetDialog.show()
        return bottomSheetDialog
    }


    fun showSnackBar(message: String, view: View) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }


    override fun onResume() {
        super.onResume()

    }


}