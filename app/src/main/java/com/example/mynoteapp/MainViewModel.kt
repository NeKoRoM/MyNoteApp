package com.example.mynoteapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.utils.TYPE_FIREBASE
import com.example.mynoteapp.utils.TYPE_ROOM
import java.lang.IllegalArgumentException

class MainViewModel(application: Application):AndroidViewModel(application) {

    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }
    init {
        readTest.value =
            when(dbType.value){
                TYPE_ROOM->{
                    listOf(


                        Note(title = "title 1", subtitle = "subtitle 1"),
                        Note(title = "title 2", subtitle = "subtitle 1"),
                        Note(title = "title 3", subtitle = "subtitle 1"),
                        Note(title = "title 4", subtitle = "subtitle 1"),
                        Note(title = "title 5", subtitle = "subtitle 1"),

                    )
                }
                TYPE_FIREBASE->{
                    listOf<Note>()
                }
                else ->{
                    listOf<Note>()
                }
            }
    }


    fun initDatabase(type: String){
        dbType.value = type
        Log.d("checkData","MainViewModel initDatabase with type: $type")

    }
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
        return MainViewModel(application = application) as T
        throw IllegalArgumentException("Unknown viewModel class")

    }
}