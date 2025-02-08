package com.example.studysmart.domain.model

data class Task(
    val title:String ,
    val description:String ,
    val duoDate:Long ,
    val priority:Int ,
    val relatedToSubject:String ,
    val isComplete:Boolean,
    val subID:Int,
    val TastID:Int
)
