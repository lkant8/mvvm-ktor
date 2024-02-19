package com.lucky.mycomposable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.lucky.mycomposable.repository.PhotoRepository
import com.lucky.mycomposable.utils.Result
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PhotoRepository
) :ViewModel(){

    fun getPhoto(){
        viewModelScope.launch {
            when(val photolist = repository.photos()){
                is Result.Success->{
                    println("$photolist")
                }
                is Result.Error ->{
                    println(photolist.message.toString())
                }
            }
        }
    }

    init {

    }

    companion object{

      /*  val factory:ViewModelProvider.Factory = object :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>*//*,extras: CreationExtras*//*): T {
//                return super.create(modelClass)
//                val application = checkNotNull(extras[APPLICATION_KEY])
//                val savedStateHandle = extras.createSavedStateHandle()

                return MainViewModel(
                    repository = repository
                ) as T

            }

        }*/
    }
}