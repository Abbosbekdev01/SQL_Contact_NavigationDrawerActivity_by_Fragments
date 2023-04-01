package uz.abbosbek.navigation_drawer_activitiy.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.abbosbek.navigation_drawer_activitiy.adapters.ContactAdapter
import uz.abbosbek.navigation_drawer_activitiy.adapters.RvClick
import uz.abbosbek.navigation_drawer_activitiy.databinding.FragmentHomeBinding
import uz.abbosbek.navigation_drawer_activitiy.db.MyDbHelper
import uz.abbosbek.navigation_drawer_activitiy.models.MyContact

class GalleryFragment : Fragment(),RvClick {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<MyContact>
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getDbId()

        return binding.root
    }

    private fun getDbId(){
        list= ArrayList()
        myDbHelper = MyDbHelper.getInstense()
        list = myDbHelper.getSortId()
        contactAdapter = ContactAdapter(list,this)
        binding.rv.adapter = contactAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.root
    }

    override fun deleteContact(deleteContact: MyContact, position: Int) {
        myDbHelper.deleteContact(deleteContact)
        list.remove(deleteContact)
        contactAdapter.notifyItemRemoved(position)

        Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()

    }
}