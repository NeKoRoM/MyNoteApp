package com.example.mynoteapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynoteapp.database.room.AppRoomDatabase
import com.example.mynoteapp.database.room.repository.RoomRepository
import com.example.mynoteapp.utils.REPOSITORY
import com.example.mynoteapp.utils.TYPE_ROOM

class MainViewModel(application: Application):AndroidViewModel(application) {

    val context = application
    fun initDatabase(type: String, onSuccess: () -> Unit) {

        Log.d("checkData", "MainViewModel initDatabase with type: $type")
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }


    }
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
        return MainViewModel(application = application) as T
        throw IllegalArgumentException("Unknown viewModel class")

    }
}