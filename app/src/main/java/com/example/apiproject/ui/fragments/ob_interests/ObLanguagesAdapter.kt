package com.example.apiproject.ui.fragments.ob_interests


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.databinding.InterestLayoutBinding
import com.example.apiproject.domain.models.InterestModel

import javax.inject.Inject

class ObInterestAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mAllInterests: MutableList<InterestModel> = mutableListOf()
    var onInterestClickListener: OnInterestClickListener? = null

    companion object {
        var selected=false

    }

    class InterestViewHolder(val binding: InterestLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            InterestLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InterestViewHolder(binding)


    }


    override fun getItemCount(): Int {
        return mAllInterests.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val interest = mAllInterests[position]
        when (holder) {

            is InterestViewHolder -> {
                with(holder.binding) {

                    interestText.text = interest.interestName

                    if (interest.isSelected) {
                        radioButtonInterest.isChecked = true
                        languageCard.isSelected = true
                    } else {
                        radioButtonInterest.isChecked = false
                        languageCard.isSelected = false
                    }

                    languageCard.setOnClickListener {

                        selected=true

                        Log.d("adp_tag", "onBindViewHolder: cliccked")

                        mAllInterests[position].isSelected = !mAllInterests[position].isSelected

                        // Notify adapter of data change
                        notifyDataSetChanged()
                    }
                }
            }

        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setAllInterests(allLanguages: List<InterestModel>) {
        mAllInterests.clear()
        mAllInterests.addAll(allLanguages)
        notifyDataSetChanged()
    }

    interface OnInterestClickListener {
        fun onInterestClick(interest: InterestModel)
    }
}
