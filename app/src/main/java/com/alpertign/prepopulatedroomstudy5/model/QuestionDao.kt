package com.alpertign.prepopulatedroomstudy5.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionDao {

    @Insert
    fun insertQuestionList(questionList: List<Question>)

    @Insert
    suspend fun insertAll(vararg questions: Question) : List<Long>

    @Query("SELECT * FROM question_table")
    suspend fun getAllQuestions(): List<Question>

    @Query("SELECT * FROM question_table WHERE rowId = :questionId")
    suspend fun getQuestion(questionId : Int) : Question

    @Query("DELETE FROM question_table")
    suspend fun deleteAllQuestions()

    @Query("SELECT MAX(rowId) FROM question_table")
    suspend fun getMaxId(): Int
}