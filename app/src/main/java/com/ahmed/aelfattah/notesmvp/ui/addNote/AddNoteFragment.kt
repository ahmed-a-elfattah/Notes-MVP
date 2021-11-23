package com.ahmed.aelfattah.notesmvp.ui.addNote

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ahmed.aelfattah.notesmvp.data.domain.Note
import com.ahmed.aelfattah.notesmvp.databinding.FragmentAddNoteBinding
import com.ahmed.aelfattah.notesmvp.utils.hideKeyboard
import com.ahmed.aelfattah.notesmvp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AddNoteFragment : BottomSheetDialogFragment(), View.OnClickListener, AddNoteContract.View {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding: FragmentAddNoteBinding get() = _binding!!

    private val presenter: AddNotePresenter by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivDone.setOnClickListener(this)
        binding.ivCancel.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivCancel -> this.dismiss()
            R.id.ivDone -> {
                hideKeyboard()
                lifecycleScope.launch {
                    presenter.onSaveNoteBtn()
                }
            }
        }
    }

    override fun getNote(): Note =
        Note(binding.tietTitle.text.toString(), binding.tietDescription.text.toString())

    override fun invalidNote(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

    override fun noteAdded() {
        this.dismiss()
        Toast.makeText(requireContext(), getString(R.string.added_successfully), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}