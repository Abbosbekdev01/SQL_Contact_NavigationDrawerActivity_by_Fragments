package uz.abbosbek.navigation_drawer_activitiy.db

import android.app.Application

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        MyDbHelper.init(this)
    }
}