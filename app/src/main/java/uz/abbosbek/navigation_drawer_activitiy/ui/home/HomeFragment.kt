package uz.abbosbek.navigation_drawer_activitiy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.abbosbek.navigation_drawer_activitiy.adapters.ContactAdapter
import uz.abbosbek.navigation_drawer_activitiy.adapters.RvClick
import uz.abbosbek.navigation_drawer_activitiy.databinding.FragmentHomeBinding
import uz.abbosbek.navigation_drawer_activitiy.db.MyDbHelper
import uz.abbosbek.navigation_drawer_activitiy.models.MyContact

class HomeFragment : Fragment(), RvClick {

//    private var _binding: FragmentHomeBinding? = null
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<MyContact>


    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getDb()

        return binding.root
    }

    private fun getDb(){
        list = ArrayList()
        myDbHelper = MyDbHelper.getInstense()
        list = myDbHelper.getSortName()
        contactAdapter = ContactAdapter(list, this)
        binding.rv.adapter = contactAdapter
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding.root
//    }

    override fun deleteContact(deleteContact: MyContact, position: Int) {

    }
}