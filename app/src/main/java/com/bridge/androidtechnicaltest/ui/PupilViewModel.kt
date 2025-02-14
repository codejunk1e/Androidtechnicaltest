package com.bridge.androidtechnicaltest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bridge.androidtechnicaltest.domain.IPupilRepository
import com.bridge.androidtechnicaltest.domain.Pupil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PupilViewModel @Inject constructor(
    private val pupilRepository: IPupilRepository
): ViewModel() {

    val pupils = pupilRepository.getOrFetchPupils()
        .cachedIn(viewModelScope)

    private val _pupil = MutableStateFlow<Pupil?>(null)
    val pupil: StateFlow<Pupil?> = _pupil

    fun setSelectPupil(pupil: Pupil?) {
        _pupil.value = pupil
    }

    fun savePupil(newPupil: Pupil) {
        viewModelScope.launch {
            pupilRepository.savePupil(newPupil)
        }
    }

    fun updatePupil(newPupil: Pupil) {
        viewModelScope.launch {
            pupilRepository.updatePupil(newPupil)
        }
    }
}
