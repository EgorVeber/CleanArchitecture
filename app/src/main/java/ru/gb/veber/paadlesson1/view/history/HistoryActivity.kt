package ru.gb.veber.paadlesson1.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gb.veber.paadlesson1.databinding.ActivityHistoryBinding
import ru.gb.veber.paadlesson1.model.data.DataModel
import ru.gb.veber.paadlesson1.model.data.AppState
import ru.gb.veber.paadlesson1.model.interactor.HistoryInteractor
import ru.gb.veber.paadlesson1.view.base.BaseActivity

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

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}
