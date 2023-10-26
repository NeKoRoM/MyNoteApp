package com.example.mynoteapp.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mynoteapp.database.DatabaseRepository
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.utils.Constants
import com.example.mynoteapp.utils.FIREBASE_ID
import com.example.mynoteapp.utils.LOGIN
import com.example.mynoteapp.utils.PASSWORD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AppFirebaseRepository : DatabaseRepository {
    private val mAuth = FirebaseAuth.getInstance()

    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    override val readAll: LiveData<List<Note>> = AllNotesLiveData()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteID = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()
        mapNotes[FIREBASE_ID] = noteID
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.SUBTITLE] = note.subtitle

        database.child(noteID)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failure to add new note") }

    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        val noteID = note.firebaseID
        val mapNotes = hashMapOf<String, Any>()
        mapNotes[FIREBASE_ID] = noteID
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.SUBTITLE] = note.subtitle

        database.child(noteID)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failure to update note") }

    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        database.child(note.firebaseID).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failure to delete note") }


    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }

            }


    }
}