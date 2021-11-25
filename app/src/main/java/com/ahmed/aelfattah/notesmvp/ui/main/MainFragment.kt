package com.ahmed.aelfattah.notesmvp.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmed.aelfattah.notesmvp.R
import com.ahmed.aelfattah.notesmvp.data.domain.Note
import com.ahmed.aelfattah.notesmvp.databinding.FragmentMainBinding
import com.ahmed.aelfattah.notesmvp.ui.addNote.AddNoteFragment
import com.ahmed.aelfattah.notesmvp.ui.main.adapter.NoteAdapter
import com.ahmed.aelfattah.notesmvp.utils.showToast
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainFragment : Fragment(), View.OnClickListener, MainContract.View {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val presenter: MainPresenter by inject { parametersOf(this) }

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            presenter.loadAllNotes()
        }

        noteAdapter = NoteAdapter()
        binding.rvNotes.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }

        binding.fabAddNote.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fabAddNote -> presenter.onAddNoteBtn()
            R.id.btnAddNote -> presenter.onAddNoteBtn()
        }
    }

    @SuppressLint("RestrictedApi")
    override fun openAddNoteBottomSheet() {
        if (isOpened()) return // avoid fab double click
        val action = MainFragmentDirections.actionMainFragmentToAddNoteFragment()
        findNavController().navigate(action)
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun emptyState() {
        toggleViewsVisibility(binding.llEmptyState.root)
        binding.llEmptyState.btnAddNote.setOnClickListener(this)
    }

    override fun contentState(noteList: ArrayList<Note>) {
        if (binding.llEmptyState.root.visibility == View.VISIBLE)
            toggleViewsVisibility(binding.llEmptyState.root)
        if (binding.flContentState.visibility == View.GONE)
            toggleViewsVisibility(binding.flContentState, binding.fabAddNote)
        noteAdapter.setNoteList(noteList)
    }

    override fun errorState(error: String) = showToast(error)

    private fun toggleViewsVisibility(vararg views: View) {
        for (view in views) {
            when (view.visibility) {
                View.VISIBLE -> {
                    view.visibility = View.GONE
                    continue
                }
                View.GONE -> {
                    view.visibility = View.VISIBLE
                    continue
                }
                View.INVISIBLE -> Unit
            }
        }
    }

    private fun isOpened(): Boolean {
        val destination = parentFragmentManager.fragments.last()
        if (destination.javaClass.simpleName.equals(AddNoteFragment::class.java.simpleName))
            return true
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}