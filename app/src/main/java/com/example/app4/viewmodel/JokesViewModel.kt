package com.example.app4.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app4.network.JokesRepository
import com.example.app4.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(

    private val jokesRepository: JokesRepository
) : ViewModel() {

    private val _jokes:MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val jokes:LiveData<UIState> get() = _jokes

    fun getJokes(){
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val response = jokesRepository.getJoke()
                if(response.isSuccessful){
                    response.body()?.let {
                        withContext(Dispatchers.Main){
                            _jokes.value = UIState.SUCCESS(it)
                        }
                    } ?: throw Exception("ALEXIS DATA IS NULL")
                }
                else{
                    throw Exception(response.errorBody()?.string())
                }
            }
            catch (e: Exception){
                withContext(Dispatchers.Main){
                    _jokes.postValue(UIState.ERROR(e))
                }
            }

        }
    }


    fun getJokeNames(first_Name:String,last_Name:String){
        CoroutineScope(Dispatchers.IO).launch{
            try {

                val response = jokesRepository.getJokeName(first_Name, last_Name)
                if(response.isSuccessful){
                    response.body()?.let {
                        withContext(Dispatchers.Main){
                            _jokes.value = UIState.SUCCESS(it)
                        }
                    } ?: throw Exception("ALEXIS DATA IS NULL")
                }
                else{
                    throw Exception(response.errorBody()?.string())
                }
            }
            catch (e: Exception){
                withContext(Dispatchers.Main){
                    _jokes.postValue(UIState.ERROR(e))
                }
            }

        }
    }

    fun getJokesList20(){
        CoroutineScope(Dispatchers.IO).launch{
            try {

                val response = jokesRepository.getJokeList20()
                if(response.isSuccessful){
                    response.body()?.let {
                        withContext(Dispatchers.Main){
                            _jokes.value = UIState.SUCCESS(it)
                        }
                    } ?: throw Exception("ALEXIS DATA IS NULL")
                }
                else{
                    throw Exception(response.errorBody()?.string())
                }
            }
            catch (e: Exception){
                withContext(Dispatchers.Main){
                    _jokes.postValue(UIState.ERROR(e))
                }
            }

        }
    }

    override fun onCleared() {
        super.onCleared()

        // here you remove the view model
    }
}