package com.example.apiproject.ui.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiproject.data.PostRepository
import com.example.apiproject.data.models.ApiResponse
import com.example.apiproject.data.models.gag.VideoShorts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WatchReelsViewModel : ViewModel() {
    private val repository = PostRepository()

    private val _postsState = MutableStateFlow<VideoShorts?>(null)
    val postsState: StateFlow<VideoShorts?> = _postsState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    // handle loading
    private val _loadingState = MutableStateFlow<Boolean>(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    fun fetchPosts(nextApiHit: String) {
        _loadingState.value = true
        viewModelScope.launch {
            val result = repository.fetchFreshPosts(nextApiHit)
            result.onSuccess { response ->
                _loadingState.value = false
                _postsState.value = response
            }.onFailure { exception ->
                _loadingState.value = false
                Log.i("WatchReelsFragment","error")
                _errorState.value = exception.message ?: "Unknown Error"
            }
        }
    }
}