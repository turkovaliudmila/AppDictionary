package ru.geekbrains.appdictionary.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import ru.geekbrains.appdictionary.databinding.ActivityMainBinding
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.viewmodel.MainInteractor
import ru.geekbrains.appdictionary.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainViewModel
    private val observer = Observer<AppState> { renderData(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = viewModelFactory.create(MainViewModel::class.java)
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })

        binding.buttonGet.setOnClickListener {
            val searchWord = binding.word.text.toString()
            Toast.makeText(this, searchWord, Toast.LENGTH_SHORT).show()
            model.getData(searchWord)
        }

    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val description = appState.data
                if (description != null && description.isNotEmpty()) {
                    binding.meanings.text = description.first().meanings?.first()?.definitions?.first()?.definition
                }
            }
            is AppState.Error -> {
                Toast.makeText(this, appState.error.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}