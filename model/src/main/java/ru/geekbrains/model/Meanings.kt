package ru.geekbrains.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meanings (
    val partOfSpeech: String?,
    val definitions: List<ru.geekbrains.model.Definition>?
) : Parcelable