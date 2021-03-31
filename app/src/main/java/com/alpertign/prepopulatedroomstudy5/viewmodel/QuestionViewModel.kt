package com.alpertign.prepopulatedroomstudy5.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpertign.prepopulatedroomstudy5.model.Question
import com.alpertign.prepopulatedroomstudy5.model.QuestionDatabase
import com.alpertign.prepopulatedroomstudy5.util.CustomSharedPreferences
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application) : BaseViewModel(application) {

    private var questionLiveData = MutableLiveData<Question>(Question("You are the best!","Who is the best?","I","They","He","She","I"))
    private var customPreferences = CustomSharedPreferences(getApplication())
    val savedRecord = getRecordFromSharedPreferences()
    var score : Int? = 0
    var randomGeneratedId: Int = 1

    val currentQuestion: LiveData<Question> = questionLiveData







    fun getRandomQuestionFromRoom(){
        launch {
            val maxId = QuestionDatabase(getApplication()).questionDao().getMaxId()
            randomGeneratedId = (1..maxId).random()
            val question = QuestionDatabase(getApplication()).questionDao().getQuestion(randomGeneratedId)
            questionLiveData.value = question
        }

    }

    fun getQuestionById(id: Int){
        launch {
            val question = QuestionDatabase(getApplication()).questionDao().getQuestion(id)
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