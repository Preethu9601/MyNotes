package com.example.mynotes.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.Model.Notes
import com.example.mynotes.R
import com.example.mynotes.SwipeToDelete
import com.example.mynotes.ViewModel.NotesViewModel
import com.example.mynotes.databinding.FragmentHomeBinding
import com.example.mynotes.ui.adapter.NotesAdapter
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)

viewModel.getNotes().observe(viewLifecycleOwner){  notesList ->

   // binding.rcvallnotes.layoutManager=GridLayoutManager(requireContext(),2)
    binding.rcvallnotes.layoutManager=LinearLayoutManager(requireContext())
    binding.rcvallnotes.adapter= NotesAdapter(requireContext(),notesList)
    val swipeDelete = object: SwipeToDelete()
    {
        @SuppressLint("NotifyDataSetChanged")
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
        {

            val position=viewHolder.adapterPosition


            notesList[position].id?.let { viewModel.deleteNotes(it) }
            Toast.makeText(requireContext(),"Notes Deleted", Toast.LENGTH_SHORT).show()


        }
    }

    val touchhelper = ItemTouchHelper(swipeDelete)
    touchhelper.attachToRecyclerView(binding.rcvallnotes)
}


        binding.addnotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }



}