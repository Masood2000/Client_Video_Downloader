package com.example.apiproject.core.ads.admob

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.apiproject.R


object LoadingDialog {

    var dialog: Dialog? = null

    fun showLoadingDialog(activity: Activity) {
        if (dialog == null) {
            dialog = Dialog(activity)
            dialog?.setContentView(R.layout.dialog_ad_loading)
            dialog?.setCancelable(false)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        if (!activity.isFinishing && !activity.isDestroyed) {
            dialog?.show()
        }

    }

    fun hideLoadingDialog(activity: Activity) {
        try {
            if (!activity.isFinishing && !activity.isDestroyed ) {
                dialog?.dismiss()
            }
            dialog = null
        } catch (e: IllegalArgumentException) {

        }
    }
}