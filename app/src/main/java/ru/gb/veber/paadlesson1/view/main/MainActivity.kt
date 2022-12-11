package ru.gb.veber.paadlesson1.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gb.veber.model.data.AppState
import ru.gb.veber.model.data.DataModel
import ru.gb.veber.paadlesson1.R
import ru.gb.veber.paadlesson1.core.utils.convertMeaningsToString
import ru.gb.veber.paadlesson1.databinding.ActivityMainBinding
import ru.gb.veber.paadlesson1.model.interactor.MainInteractor
import ru.gb.veber.paadlesson1.view.base.BaseActivity
import ru.gb.veber.paadlesson1.view.history.HistoryActivity
import ru.gb.veber.paadlesson1.view.historydialog.SearchHistoryDialog
import ru.gb.veber.utils.isOnline

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }


    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchHistoryDialog = SearchHistoryDialog.newInstance()
            searchHistoryDialog.setOnSearchClickListener(onSearchClickListener)
            searchHistoryDialog.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings!![0].imageUrl
                    )
                )
            }
        }

    private val onSearchClickListener: SearchHistoryDialog.OnSearchClickListener =
        object : SearchHistoryDialog.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(getConnectivityManager())
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    private val searchViewListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            isNetworkAvailable = isOnline(getConnectivityManager())
            query?.let { keyWord ->
                if (isNetworkAvailable) {
                    model.getData(keyWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
            binding.searchView.clearFocus();
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        data.forEach {
            Log.d("TAG", it.text.toString())
        }
        adapter.setData(data)
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
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity) { renderData(it) }
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.searchView.setOnQueryTextListener(searchViewListener)
        binding.mainActivityRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
    }
}
