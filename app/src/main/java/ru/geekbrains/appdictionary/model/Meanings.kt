package ru.geekbrains.appdictionary.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meanings (
    val partOfSpeech: String?,
    val definitions: List<Definition>?
) : Parcelable