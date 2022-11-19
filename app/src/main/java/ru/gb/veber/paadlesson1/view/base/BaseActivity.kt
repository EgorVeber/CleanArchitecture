package ru.gb.veber.paadlesson1.view.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import geekbrains.ru.translator.utils.ui.AlertDialogFragment
import ru.gb.veber.paadlesson1.R
import ru.gb.veber.paadlesson1.model.AppState
import ru.gb.veber.paadlesson1.model.Interactor
import ru.gb.veber.paadlesson1.utils.network.isOnline
import ru.gb.veber.paadlesson1.viewmodel.BaseViewModel


abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable =
            isOnline(getConnectivityManager())
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable =
            isOnline(getConnectivityManager())
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    private fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    protected fun getConnectivityManager(): ConnectivityManager {
       return applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    abstract fun renderData(dataModel: T)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}
