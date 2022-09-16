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
import com.example.mynotes.Model.Notes
import com.example.mynotes.R
import com.example.mynotes.ViewModel.NotesViewModel
import com.example.mynotes.databinding.FragmentCreateNotesBinding
import kotlinx.android.synthetic.main.fragment_create_notes.*
import java.io.ByteArrayOutputStream
import java.util.*

class CreateNotesFragment : Fragment() {
    lateinit var binding:FragmentCreateNotesBinding
    //var PICK_CODE: Int =100
    val viewModel :NotesViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding=FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        binding.savenotes.setOnClickListener {
            createNotes(it)
        }

/*  binding.imgBtn1.setOnClickListener {

                selectImage()
            }*/

        binding.imgBtn.setOnClickListener {
            capturePhoto()
        }


        return binding.root
    }



    private fun createNotes(it: View?) {
        val title=binding.edttitle.text.toString()
        val notes= binding.edtnotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data=Notes(null,title=title,notes=notes,  date=notesDate.toString())

        viewModel.addNotes(data)

       Toast.makeText(requireContext(),"Notes Created",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }





   private fun capturePhoto()
    {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        getPhotoResult.launch(cameraIntent)

    }
   private val getPhotoResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            binding.img.visibility= View.VISIBLE
            if (it.resultCode == Activity.RESULT_OK) {
                val imageBitmap = it.data?.extras?.get("data") as Bitmap
                binding.img.setImageBitmap(imageBitmap)


            }

        }

}

/*   private fun selectImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent, PICK_CODE)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==PICK_CODE && resultCode== Activity.RESULT_OK)
        {

            binding.img1.setImageURI(data?.data)



        }
    }  */