package com.sabeeldev.myapplication.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sabeeldev.myapplication.data.repositories.UserRepository

class ProductsViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CryptoViewModel(repository) as T
    }
}