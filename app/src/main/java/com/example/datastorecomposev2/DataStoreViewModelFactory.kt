package com.example.datastorecomposev2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datastorecomposev2.repository.DatastoreRepository

class DataStoreViewModelFactory(private val datastoreRepository: DatastoreRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(datastoreRepository) as T
        }
        throw IllegalStateException()
    }
}