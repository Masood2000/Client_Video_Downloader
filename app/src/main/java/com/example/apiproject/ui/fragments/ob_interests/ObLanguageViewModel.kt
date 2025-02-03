
package com.example.apiproject.ui.fragments.ob_interests

import androidx.lifecycle.ViewModel
import com.example.apiproject.domain.models.InterestModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ObLanguageViewModel : ViewModel() {

    private var _allLanguages: MutableStateFlow<MutableList<InterestModel>> =
        MutableStateFlow(mutableListOf())
    val allLanguages = _allLanguages.asStateFlow()

    var selectedLanguages = mutableListOf<InterestModel>()

    fun getLanguages() {
        // get languages
        val interestList = mutableListOf<InterestModel>()
        interestList.addAll(InterestModel.interestModelList)

        _allLanguages.update {
            interestList
        }
    }

}
