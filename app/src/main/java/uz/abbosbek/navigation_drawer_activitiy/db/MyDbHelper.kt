package uz.abbosbek.navigation_drawer_activitiy.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.abbosbek.navigation_drawer_activitiy.db.MyConstanta.DB_NAME
import uz.abbosbek.navigation_drawer_activitiy.db.MyConstanta.ID
import uz.abbosbek.navigation_drawer_activitiy.db.MyConstanta.NAME
import uz.abbosbek.navigation_drawer_activitiy.db.MyConstanta.NUMBER
import uz.abbosbek.navigation_drawer_activitiy.db.MyConstanta.TABLE_NAME
import uz.abbosbek.navigation_drawer_activitiy.db.MyConstanta.VERSION
import uz.abbosbek.navigation_drawer_activitiy.models.MyContact

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION),
    MyDbInterface {

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table ${TABLE_NAME}(id integer not null primary key autoincrement unique, ${NAME} text not null, ${NUMBER} text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addContact(myContact: MyContact) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, myContact.name)
        contentValues.put(NUMBER, myContact.number)
        database.insert(TABLE_NAME, null, contentValues)

    }

    override fun getAllContact(): ArrayList<MyContact> {
        val list = ArrayList<MyContact>()

        val query = "select * from $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val myContact = MyContact(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(myContact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun getSortName():ArrayList<MyContact>{
        val list = ArrayList<MyContact>()
        /** select */
        val query = "select * from $TABLE_NAME ORDER BY $NAME COLLATE NOCASE ASC "
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val myContact = MyContact(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(myContact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun getSortId():ArrayList<MyContact>{
        val list = ArrayList<MyContact>()

        /** query Database ga so'rov jo'natiladi*/
        val query = "select * from $TABLE_NAME ORDER BY $ID  ASC "
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val myContact = MyContact(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(myContact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    override fun deleteContact(myContact: MyContact) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "id=?", arrayOf(myContact.id.toString()))
        database.close()
    }

    companion object{
        private var dbHelper:MyDbHelper? = null
        fun init(context: Context){
            if (dbHelper == null){
                dbHelper = MyDbHelper(context)
            }
        }

        fun getInstense() = dbHelper!!
    }

}