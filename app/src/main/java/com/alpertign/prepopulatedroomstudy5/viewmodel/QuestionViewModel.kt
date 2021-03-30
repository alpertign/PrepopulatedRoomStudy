package com.alpertign.prepopulatedroomstudy5.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpertign.prepopulatedroomstudy5.model.Question
import com.alpertign.prepopulatedroomstudy5.model.QuestionDatabase
import com.alpertign.prepopulatedroomstudy5.util.CustomSharedPreferences
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application) : BaseViewModel(application) {

    val questionLiveData = MutableLiveData<Question>()
    private var customPreferences = CustomSharedPreferences(getApplication())
    val savedRecord = getRecordFromSharedPreferences()
    var score : Int? = 0
    var randomGeneratedId: Int = 1

    fun getDataFromRoom(){
        val question = Question("Adım Alperen","Adım ne?","Alperen","George","John","Michael","Alperen")
        questionLiveData.value = question
    }

    fun getRandomQuestionFromRoom(){
        launch {
            val maxId = QuestionDatabase(getApplication()).questionDao().getMaxId()
            randomGeneratedId = (1..maxId).random()
            val question = QuestionDatabase(getApplication()).questionDao().getQuestion(randomGeneratedId)
            questionLiveData.value = question
        }

    }



    fun ifScoreBiggerThanRecordSaveNewRecord(score: Int, savedRecord: Int){
        if (savedRecord != null && savedRecord != 0 && score > savedRecord){
            saveRecordToSharedPreferences(score)
        }
    }

    fun saveRecordToSharedPreferences(record : Int){
        customPreferences.saveRecord(record)
    }

    fun getRecordFromSharedPreferences(): Int? {
        return customPreferences.getRecord()
    }
}