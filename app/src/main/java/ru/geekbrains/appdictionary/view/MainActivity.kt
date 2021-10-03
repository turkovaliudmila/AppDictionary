package ru.geekbrains.appdictionary.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.appdictionary.R
import ru.geekbrains.appdictionary.databinding.ActivityMainBinding
import ru.geekbrains.appdictionary.viewmodel.MainInteractor
import ru.geekbrains.appdictionary.viewmodel.MainViewModel
import ru.geekbrains.core.BaseActivity
import ru.geekbrains.history.view.HistoryActivity
import ru.geekbrains.model.AppState

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun iniViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.getLiveDataAppState()
            .observe(this@MainActivity, Observer<ru.geekbrains.model.AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.mainActivityRecyclerview.adapter = adapter
        binding.buttonGet.setOnClickListener {
            val searchWord = binding.word.text.toString()
            model.getData(searchWord, true)
        }
    }

    override fun setData(data: List<ru.geekbrains.model.ItemOfDictionary>) {
        data.first()?.also { itemOfDictionary ->
            itemOfDictionary.meanings?.let { adapter.setData(it) }
            binding.phonetic.text = itemOfDictionary.phonetic.toString()
        }
    }
}