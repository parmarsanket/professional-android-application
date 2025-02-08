package com.example.studysmart.domain.model

import kotlin.time.Duration

data class Session(
    val sessionSubjectID:Int,
    val relatedToSubject:String,
    val data :Long,
    val duration :Long,
    val sessionID:Int

)
