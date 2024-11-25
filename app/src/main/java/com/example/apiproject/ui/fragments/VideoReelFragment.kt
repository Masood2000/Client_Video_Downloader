package com.example.apiproject.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.R
import com.example.apiproject.data.interfaces.ClickHandler
import com.example.apiproject.data.interfaces.ReelClickHandler
import com.example.apiproject.data.models.ReelVideo
import com.example.apiproject.databinding.FragmentVideoReelBinding
import com.example.apiproject.ui.adapters.ReelsAdapter
import com.example.apiproject.ui.adapters.TabsAdapter
import com.example.apiproject.ui.base.BaseFragment

 class VideoReelFragment :BaseFragment(){
     private val binding by lazy { FragmentVideoReelBinding.inflate(layoutInflater) }

     val adapter by lazy { ReelsAdapter() }
     val tabsAdapter by lazy { TabsAdapter() }

     override fun setViewBinding(): View {
         return binding.root
     }

     override fun initView() {
         binding.recycleView.adapter=adapter
         binding.tabLayout.layoutManager=LinearLayoutManager(context,
             LinearLayoutManager.HORIZONTAL,false)
         binding.tabLayout.adapter=tabsAdapter

         activity?.let {
             if(it is MainActivity){
                 adapter.setData(it.reelsData)
                 var list=it.reelsData.map { it.channel }.toSet().toMutableList()
                 list.add(0,"All")
                 tabsAdapter.setData(list)

                    tabsAdapter.clickHandler=object : ClickHandler {
                        override fun onClickPressed() {
                            var list=it.reelsData.map { it.channel }.toSet().toMutableList()
                            list.add(0,"All")
                            if(tabsAdapter.selectedTab==0){
                                adapter.setData(it.reelsData)
                            }else{
                                adapter.setData(it.reelsData.filter { it.channel==list[tabsAdapter.selectedTab] })
                            }

                        }
                    }

             }
         }
         adapter.clickHandler=object : ReelClickHandler {
             override fun onClickPressed(reelVideo: ReelVideo) {
                 val bundle=Bundle()
                 bundle.putString("videoUrl",reelVideo.url)
                 bundle.putString("videoTitle",reelVideo.title)
                 bundle.putString("videoId",reelVideo.id)
/*                 if(findNavController().currentDestination?.id==R.id.videoReelFragment){
                     findNavController().navigate(R.id.action_videoReelFragment_to_playerFragment,bundle)
                 }*/ //todo: remove it later when added to navigation graph
             }
         }

     }

     override fun lazyLoad() {

     }

 }