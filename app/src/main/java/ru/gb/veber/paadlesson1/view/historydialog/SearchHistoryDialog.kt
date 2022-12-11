package ru.gb.veber.paadlesson1.view.historydialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gb.veber.paadlesson1.core.utils.convertMeaningsToString
import ru.gb.veber.paadlesson1.core.utils.getEmptyString
import ru.gb.veber.paadlesson1.databinding.SearchDialogFragmentBinding
import ru.gb.veber.paadlesson1.model.data.AppState
import ru.gb.veber.paadlesson1.view.main.DescriptionActivity

class SearchHistoryDialog : BottomSheetDialogFragment() {

    private lateinit var searchEditText: TextInputEditText
    private lateinit var clearTextImageView: ImageView
    private lateinit var searchButton: TextView
    private var onSearchClickListener: OnSearchClickListener? = null

    private lateinit var binding: SearchDialogFragmentBinding

    lateinit var model: SearchHistoryDialogViewModel

    private val textWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (searchEditText.text != null && !searchEditText.text.toString().isEmpty()) {
                searchButton.isEnabled = true
                clearTextImageView.visibility = View.VISIBLE
            } else {
                searchButton.isEnabled = false
                clearTextImageView.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = SearchDialogFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText = binding.searchEditText
        clearTextImageView = binding.clearTextImageview
        searchButton = binding.searchButtonTextview

        searchButton.setOnClickListener {
            model.getData(binding.searchEditText.text.toString(), true)
        }
        searchEditText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener() {
        clearTextImageView.setOnClickListener {
            searchEditText.setText(String.getEmptyString())
            searchButton.isEnabled = false
        }
    }

    private fun iniViewModel() {
        val viewModel: SearchHistoryDialogViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this) { renderData(it) }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessKeyWord -> {
                startActivity(
                    DescriptionActivity.getIntent(
                        requireContext(),
                        appState.data.text!!,
                        convertMeaningsToString(appState.data.meanings!!), null
                    )
                )
            }
            is AppState.Success -> {
                Log.d("renderData", "Success() called with: appState = $appState")
            }
            is AppState.Loading -> {
                Log.d("renderData", "Loading() called with: appState = $appState")
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), "no records in the database", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance(): SearchHistoryDialog {
            return SearchHistoryDialog()
        }
    }
}
