package com.example.todoapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todoapplication.MainActivity
import com.example.todoapplication.R
import com.example.todoapplication.databinding.FragmentEditBinding
import com.example.todoapplication.model.Notes
import com.example.todoapplication.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.Date

@Suppress("DEPRECATION")
class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private var priority: String = "1"
    private var id: Int? = null
    private var favorite: String = "False"
    private val viewModel: NotesViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding= FragmentEditBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).changeTitle("Update List")
        val title = requireArguments().getString("Title")
        val subtitle = requireArguments().getString("Subtitle")
        val notes = requireArguments().getString("Notes")
        favorite = requireArguments().getString("favorite")!!
        id = requireArguments().getInt("Id")
        priority = requireArguments().getString("priority").toString()
        binding.editTitle.setText(title)
        binding.editsubtitle.setText(subtitle)
        binding.editNotes.setText(notes)


        binding.btnSaveNotes.setOnClickListener {
            updateNotes()
        }


        return binding.root
    }

    private fun updateNotes() {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editsubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notesDate: String = DateFormat.format("MMMM d, yyyy ", d.time).toString()
        if (title == "") {
            Snackbar.make(binding.frameLayout, "Title Field Is Empty,Please give a title", Snackbar.LENGTH_SHORT).show()
        }
        else if (subtitle == "")
        {
            Snackbar.make(binding.frameLayout, "Title Field Is Empty,Please give a sub title", Snackbar.LENGTH_SHORT).show()
        }
        else if (notes == "") {
            Snackbar.make(binding.frameLayout, "Notes Field Is Empty,Enter some Note", Snackbar.LENGTH_SHORT).show()
        }
        else {
            val data = Notes(
                id = id,
                title = title,
                subtitle = subtitle,
                notes = notes,
                date = notesDate,
                priority = priority,
                favorite = favorite
            )
            viewModel.updateNotes(data)
            Toast.makeText(requireContext(),"Change's Has Been Saved",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editFragment_to_homeFragment)
            findNavController(requireView()).popBackStack(
                R.id.editFragment, true
            )
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete) {
            val bottomSheet = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delete)

            bottomSheet.findViewById<TextView>(R.id.textview_yes)?.setOnClickListener {
                viewModel.deleteNotes(id!!)
                bottomSheet.dismiss()
                findNavController().navigate(R.id.action_editFragment_to_homeFragment)
                findNavController(requireView()).popBackStack(
                    R.id.editFragment, true
                )

            }

            bottomSheet.findViewById<TextView>(R.id.textview_no)?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()

        }

        return super.onOptionsItemSelected(item)
    }

}