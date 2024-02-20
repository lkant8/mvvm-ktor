package com.lucky.mycomposable.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.lucky.mycomposable.data.PhotoDTO
import com.lucky.mycomposable.repository.PhotoRepository
import com.lucky.mycomposable.utils.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PhotoRepository
) :ViewModel(){

    private val _event = MutableSharedFlow<String>()
    val eventData = _event.asSharedFlow()

    private val _photos = MutableLiveData<List<PhotoDTO>>()
    val user: LiveData<List<PhotoDTO>> get() = _photos

    fun getPhoto(){
        viewModelScope.launch {
            when(val photolist = repository.photos()){
                is Result.Success->{
                    _photos.postValue(photolist.data)
                }
                is Result.Error ->{
                    println(photolist.message.toString())
                    _event.emit(photolist.message.toString())
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