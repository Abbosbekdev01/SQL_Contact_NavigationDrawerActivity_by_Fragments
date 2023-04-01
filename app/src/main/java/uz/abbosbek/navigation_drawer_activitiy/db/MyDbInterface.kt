package uz.abbosbek.navigation_drawer_activitiy.db

import uz.abbosbek.navigation_drawer_activitiy.models.MyContact

interface MyDbInterface {

    fun addContact(myContact: MyContact)

    fun getAllContact():ArrayList<MyContact>

    fun deleteContact(myContact: MyContact)

    fun updateContact(myContact: MyContact)

}