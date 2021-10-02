package ru.geekbrains.appdictionary.view.history

import android.os.Bundle
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import ru.geekbrains.appdictionary.view.BaseActivity
import ru.geekbrains.appdictionary.viewmodel.history.HistoryInteractor
import ru.geekbrains.appdictionary.viewmodel.history.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import ru.geekbrains.appdictionary.databinding.ActivityHistoryBinding

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setData(data: List<ItemOfDictionary>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.getAppState().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}