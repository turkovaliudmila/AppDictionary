package ru.geekbrains.appdictionary.utils

import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import ru.geekbrains.appdictionary.model.Meanings

fun parseOnlineSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, true))
}

fun parseLocalSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, false))
}

private fun mapResult(
    appState: AppState,
    isOnline: Boolean
): List<ItemOfDictionary> {
    val newSearchResults = arrayListOf<ItemOfDictionary>()
    when (appState) {
        is AppState.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(
    appState: AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<ItemOfDictionary>
) {
    val dataModels: List<ItemOfDictionary> = appState.data as List<ItemOfDictionary>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(
                    ItemOfDictionary(
                        searchResult.word,
                        searchResult.phonetic,
                        arrayListOf()
                    )
                )
            }
        }
    }
}

private fun parseOnlineResult(
    dataModel: ItemOfDictionary,
    newDataModels: ArrayList<ItemOfDictionary>
) {
    if (!dataModel.word.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.partOfSpeech != null) {
                newMeanings.add(Meanings(meaning.partOfSpeech, meaning.definitions))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(ItemOfDictionary(dataModel.word, dataModel.phonetic, newMeanings))
        }
    }
}

//fun convertMeaningsToString(meanings: List<Meanings>): String {
//    var meaningsSeparatedByComma = String()
//    for ((index, meaning) in meanings.withIndex()) {
//        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
//            String.format("%s%s", meaning.translation?.translation, ", ")
//        } else {
//            meaning.translation?.translation
//        }
//    }
//    return meaningsSeparatedByComma
//}
