package ru.geekbrains.appdictionary.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Definition(
    val definition: String?,
    val example: String?
) : Parcelable