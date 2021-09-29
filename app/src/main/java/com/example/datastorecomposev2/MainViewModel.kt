package com.example.datastorecomposev2

import androidx.lifecycle.*
import com.example.datastorecomposev2.repository.DatastoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val datastoreRepository: DatastoreRepository) : ViewModel() {

    val readUserName = datastoreRepository.getUserName.asLiveData()

    fun setUserName(myName: String) = viewModelScope.launch(Dispatchers.IO) {
        datastoreRepository.setUserName(myName)
    }

    ///

    val readCounter = datastoreRepository.counterFlow.asLiveData()

    fun incrementCounter() = viewModelScope.launch(Dispatchers.IO) {
        datastoreRepository.incrementCounter()
    }

}