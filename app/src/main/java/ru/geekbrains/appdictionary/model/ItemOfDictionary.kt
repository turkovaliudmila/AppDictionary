package ru.geekbrains.appdictionary.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemOfDictionary(
    val word: String?,
    val phonetic: String?,
    val meanings: List<Meanings>?
) : Parcelable