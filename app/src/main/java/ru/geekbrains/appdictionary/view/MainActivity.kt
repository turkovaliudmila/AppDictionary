package ru.geekbrains.appdictionary.view

import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.appdictionary.databinding.ActivityMainBinding
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.viewmodel.MainInteractor
import ru.geekbrains.appdictionary.viewmodel.MainViewModel

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        binding.buttonGet.setOnClickListener {
            val searchWord = binding.word.text.toString()
            Toast.makeText(this, searchWord, Toast.LENGTH_SHORT).show()
            model.getData(searchWord)
        }

    }

    private fun initViewModel() {
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.getAppState().observe(this@MainActivity, { renderData(it) })
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val description = appState.data
                if (description != null && description.isNotEmpty()) {
                    binding.meanings.text =
                        description.first().meanings?.first()?.definitions?.first()?.definition
                }
            }
            is AppState.Error -> {
                Toast.makeText(this, appState.error.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}