package com.alpertign.prepopulatedroomstudy5.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreferences {

    companion object {

        private val PREFERENCES_RECORD = "preferences_record"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance: CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context) : CustomSharedPreferences = instance ?: synchronized(lock) {
            instance ?: makeCustomSharedPreferences(context).also{
                instance = it
            }
        }

        private fun makeCustomSharedPreferences(context: Context): CustomSharedPreferences {

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }

    }

    fun saveRecord(record: Int){
        sharedPreferences?.edit(commit = true){
            putInt(PREFERENCES_RECORD,record)
        }
    }

    fun getRecord() = sharedPreferences?.getInt(PREFERENCES_RECORD,0)


}