package com.example.todoapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapplication.MainActivity
import com.example.todoapplication.R
import com.example.todoapplication.databinding.FragmentHomeBinding
import com.example.todoapplication.model.Notes
import com.example.todoapplication.ui.adapter.RecycleAdaptor
import com.example.todoapplication.viewModel.NotesViewModel


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerAdaptor: RecycleAdaptor
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var noteList: ArrayList<Notes>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).changeTitle("To Do List")
        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            noteList = notesList as ArrayList<Notes>
            binding.rccAllNotes.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
            recyclerAdaptor = RecycleAdaptor(notesList)
            binding.rccAllNotes.adapter = recyclerAdaptor
            recyclerAdaptor.setOnItemClickListener = {
                findNavController().navigate(
                    R.id.action_homeFragment_to_editFragment,
                    Bundle().apply {
                        putInt("Id", it.id!!)
                        putString("Title", it.title)
                        putString("Subtitle", it.subtitle)
                        putString("Notes", it.notes)
                        putString("priority", it.priority)
                        putString("favorite", it.favorite)
                    })
            }

        }

        binding.btnaddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }

        return binding.root
    }


}


