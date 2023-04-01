package uz.abbosbek.navigation_drawer_activitiy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.navigation_drawer_activitiy.databinding.ItemRvBinding
import uz.abbosbek.navigation_drawer_activitiy.models.MyContact

class ContactAdapter(val list: ArrayList<MyContact>, val rvClick: RvClick) :
    RecyclerView.Adapter<ContactAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(myContact: MyContact, position: Int) {
            itemRvBinding.tvName.text = myContact.name
            itemRvBinding.tvNumber.text = myContact.number

            itemRvBinding.root.setOnLongClickListener {
                rvClick.deleteContact(myContact, position)
                true
            }

            itemRvBinding.root.setOnClickListener {
                rvClick.updateContact(myContact, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}

interface RvClick {
    fun deleteContact(deleteContact: MyContact, position: Int)
    fun updateContact(updateContact: MyContact, position: Int)
}

