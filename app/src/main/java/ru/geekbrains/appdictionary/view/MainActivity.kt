package ru.geekbrains.appdictionary.view

import android.os.Bundle
import android.widget.Toast
import ru.geekbrains.appdictionary.databinding.ActivityMainBinding
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.presenter.IPresenter
import ru.geekbrains.appdictionary.presenter.MainPresenterImpl

class MainActivity : BaseActivity<AppState>() {

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.buttonGet?.setOnClickListener {
            val txt = vb?.word?.text.toString()
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
            presenter.getData(vb?.word?.text.toString())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }

    override fun createPresenter(): IPresenter<AppState, IView> {
        return MainPresenterImpl()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val description = appState.data
                if (description != null && !description.isEmpty()) {
                    vb?.meanings?.text = description.first().meanings?.first()?.definitions?.first()?.definition
                }
            }
            is AppState.Error -> {
                Toast.makeText(this, appState.error.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}