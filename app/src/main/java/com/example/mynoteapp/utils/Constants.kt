package com.example.mynoteapp.utils

import com.example.mynoteapp.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

object Constants {
    object Keys {
        const val NONE = "none"
        const val ID = "id"
        const val ROOM_DATABASE = "Room database"
        const val NOTE_DATABASE = "note_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Add new note"
        const val NOTE_TITLE = "new title"
        const val NOTE_SUBTITLE = "new subtitle"
        const val ADD_NOTE = "add note"
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
        const val WHAT_WE_WILL_USE = "What we will use"
        const val FIREBASE = "firebase"
        const val UPDATE = "UPDATE"
        const val DELETE = "DELETE"
        const val NAW_BACK = "NAV_BACK"
        const val UPDATE_NOTE = "update note"


    }

    object Screens {
        const val START_SCREEN = "start_screen"
        const val ADD_SCREEN = "add_screen"
        const val MAIN_SCREEN = "main_screen"
        const val NOTE_SCREEN = "start_screen"


    }
}