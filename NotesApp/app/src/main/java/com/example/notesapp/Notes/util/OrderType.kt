package com.example.notesapp.Notes.util

sealed class OrderType {
    object Ascending:OrderType()
    object Descending:OrderType()

}