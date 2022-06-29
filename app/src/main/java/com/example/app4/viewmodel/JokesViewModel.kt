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

    fun getJokes(uniqueId: Int = 20,firstName: String? = "David", lastName: String? = "Alexis"){

        CoroutineScope(Dispatchers.IO).launch{

            delay(500)

            try {
                Log.d(TAG, "ALEXIS getJokes:try")

                val response = jokesRepository.getJoke(uniqueId,firstName,lastName)

                Log.d(TAG, "ALEXIS getJokes:response"+response)


                if(response.isSuccessful){
                    response.body()?.let {


                        withContext(Dispatchers.Main){

                            _jokes.value = UIState.SUCCESS(it)
                            Log.d(TAG, "ALEXIS getJokes:"+_jokes.value)

                        }

                    } ?: throw Exception("ALEXIS DATA IS NULL")
                }
                else{
                    throw Exception(response.errorBody()?.string())
                }
            }
            catch (e: Exception){

                Log.d(TAG, "ALEXIS getJokes:catch"+e)


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