package com.example.mynotes.ui.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mynotes.Model.Notes
import com.example.mynotes.R
import com.example.mynotes.ViewModel.NotesViewModel
import com.example.mynotes.databinding.FragmentEditNotesBinding
import java.util.*


class EditNotesFragment : Fragment() {
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding:FragmentEditNotesBinding
    val viewModel : NotesViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        binding.edttitle.setText(oldNotes.data.title)
        binding.edtnotes.setText(oldNotes.data.notes)

        binding.imgBtn1.setOnClickListener {
            capturePhoto()
        }

        binding.edtsavenotes.setOnClickListener {
            updateNotes(it)
        }



        return binding.root
    }




    private fun updateNotes(it: View?) {
        val title=binding.edttitle.text.toString()
        val notes= binding.edtnotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data= Notes(oldNotes.data.id,title=title,notes=notes,date=notesDate.toString())

        viewModel.updateNotes(data)

        Toast.makeText(requireContext(),"Notes Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }
    private fun capturePhoto()
    {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        getPhotoResult.launch(cameraIntent)

    }
    private val getPhotoResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            binding.img1.visibility= View.VISIBLE
            if (it.resultCode == Activity.RESULT_OK) {
                val imageBitmap = it.data?.extras?.get("data") as Bitmap
                binding.img1.setImageBitmap(imageBitmap)


            }

        }


}