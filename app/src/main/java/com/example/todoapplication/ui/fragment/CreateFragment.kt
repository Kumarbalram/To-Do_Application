package com.example.todoapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.todoapplication.MainActivity
import com.example.todoapplication.R
import com.example.todoapplication.databinding.FragmentCreateBinding
import com.example.todoapplication.model.Notes
import com.example.todoapplication.viewModel.NotesViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.Date


class CreateFragment : Fragment() {

    private var priority: String = "1"
    private var favorite: String = "False"
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var binding: FragmentCreateBinding

    @SuppressLint("UseRequireInsteadOfGet", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding= FragmentCreateBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).changeTitle("Create List")

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editsubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notesDate: String = DateFormat.format("MMMM d, yyyy ", d.time).toString()
        if (title == "") {
            binding.frameLayout.let { it1 -> Snackbar.make(it1,"Title Field Is Empty,Please give a title",Snackbar.LENGTH_SHORT).show() }
        }
         else if (subtitle == "") {
            binding.frameLayout.let { it1 -> Snackbar.make(it1,"Title Field Is Empty,Please give a sub title",Snackbar.LENGTH_SHORT).show() }
        }
        else if (notes == "") {
            binding.frameLayout.let { it1 -> Snackbar.make(it1,"Notes Field Is Empty,Enter some Note",Snackbar.LENGTH_SHORT).show() }
        }
        else {

            val data = Notes(
                null,
                title = title,
                subtitle = subtitle,
                notes = notes,
                date = notesDate,
                priority = priority,
                favorite = favorite
            )
            viewModel.addNotes(data)
            Navigation.findNavController(it!!).navigate(R.id.action_createFragment_to_homeFragment)
            Navigation.findNavController(requireView()).popBackStack(
                R.id.createFragment, true
            )
        }
    }

}