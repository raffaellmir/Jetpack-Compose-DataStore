package com.example.datastorecomposev2

import androidx.lifecycle.*
import com.example.datastorecomposev2.repository.DatastoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val datastoreRepository: DatastoreRepository) : ViewModel() {

    val getUserName = datastoreRepository.getUserName.asLiveData()

    fun setUserName(myName: String) = viewModelScope.launch(Dispatchers.IO) {
        datastoreRepository.setUserName(myName)
    }
}