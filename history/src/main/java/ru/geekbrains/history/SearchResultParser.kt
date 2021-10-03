package ru.geekbrains.appdictionary.utils

fun parseOnlineSearchResults(appState: ru.geekbrains.model.AppState): ru.geekbrains.model.AppState {
    return ru.geekbrains.model.AppState.Success(mapResult(appState, true))
}

fun parseLocalSearchResults(appState: ru.geekbrains.model.AppState): ru.geekbrains.model.AppState {
    return ru.geekbrains.model.AppState.Success(mapResult(appState, false))
}

private fun mapResult(
    appState: ru.geekbrains.model.AppState,
    isOnline: Boolean
): List<ru.geekbrains.model.ItemOfDictionary> {
    val newSearchResults = arrayListOf<ru.geekbrains.model.ItemOfDictionary>()
    when (appState) {
        is ru.geekbrains.model.AppState.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(
    appState: ru.geekbrains.model.AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<ru.geekbrains.model.ItemOfDictionary>
) {
    val dataModels: List<ru.geekbrains.model.ItemOfDictionary> =
        appState.data as List<ru.geekbrains.model.ItemOfDictionary>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(
                    ru.geekbrains.model.ItemOfDictionary(
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
    dataModel: ru.geekbrains.model.ItemOfDictionary,
    newDataModels: ArrayList<ru.geekbrains.model.ItemOfDictionary>
) {
    if (!dataModel.word.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<ru.geekbrains.model.Meanings>()
        for (meaning in dataModel.meanings!!) {
            if (meaning.partOfSpeech != null) {
                newMeanings.add(
                    ru.geekbrains.model.Meanings(
                        meaning.partOfSpeech,
                        meaning.definitions
                    )
                )
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(
                ru.geekbrains.model.ItemOfDictionary(
                    dataModel.word,
                    dataModel.phonetic,
                    newMeanings
                )
            )
        }
    }
}
