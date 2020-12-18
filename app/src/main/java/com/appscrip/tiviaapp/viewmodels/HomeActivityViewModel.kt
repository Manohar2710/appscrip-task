package com.appscrip.tiviaapp.viewmodels

import androidx.lifecycle.*
import com.appscrip.tiviaapp.models.SurveyData
import com.appscrip.tiviaapp.repositories.SurveyRepository
import kotlinx.coroutines.launch

// Using LiveData and caching what allData returns has several benefits:
// - We can put an observer on the data (instead of polling for changes) and only update the
//   the UI when the data actually changes.
// - Repository is completely separated from the UI through the ViewModel.
class HomeActivityViewModel(private val repository: SurveyRepository) :ViewModel(){
	val allWords: LiveData<List<SurveyData>> = repository.allData.asLiveData()


	/**
	 * Launching a new coroutine to insert the data in a non-blocking way
	 */
	fun insert(surveyData: SurveyData) = viewModelScope.launch {
		repository.insert(surveyData)
	}
}
class HomeViewModelFactory(private val repository: SurveyRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(HomeActivityViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return HomeActivityViewModel(repository) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
