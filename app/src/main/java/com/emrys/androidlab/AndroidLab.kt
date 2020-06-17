package com.emrys.androidlab

import android.app.Application

class AndroidLab: Application() {

    override fun onCreate() {
        super.onCreate()

        DarkModeHelper.getInstance(this)
    }
}