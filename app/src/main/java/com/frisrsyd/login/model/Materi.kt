package com.frisrsyd.login.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Materi (
    val title: String,
    val status: String,
    val level: String,
    val chapters: String,
    //    val img: Int
    val img: String,
    val desc: String
): Parcelable