package uz.abbosbek.navigation_drawer_activitiy.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.abbosbek.navigation_drawer_activitiy.databinding.FragmentSlideshowBinding
import uz.abbosbek.navigation_drawer_activitiy.db.MyDbHelper
import uz.abbosbek.navigation_drawer_activitiy.models.MyContact

class SlideshowFragment : Fragment() {
    private val binding by lazy { FragmentSlideshowBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        myDbHelper = MyDbHelper.getInstense()
        binding.btnSave.setOnClickListener {
            saveContact()
        }
        return binding.root
    }

    /** bu yerda saveContact ma'lumotlarni Database ga saqlab beradi*/
    private fun saveContact() {
        if (binding.edtName.text!!.isNotEmpty() && binding.edtNumber.text!!.isNotEmpty()) {
            myDbHelper.addContact(
                MyContact(
                    name = binding.edtName.text.toString(),
                    number = binding.edtNumber.text.toString()
                )
            )
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
            binding.edtName.text!!.clear()
            binding.edtNumber.text!!.clear()
        }else{
            Toast.makeText(requireContext(), "Ma'lumot to'liq kiritilmadi", Toast.LENGTH_SHORT).show()
        }
    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding.root
//    }
}

