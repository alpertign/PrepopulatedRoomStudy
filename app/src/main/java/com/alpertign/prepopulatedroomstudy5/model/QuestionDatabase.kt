package com.alpertign.prepopulatedroomstudy5.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alpertign.prepopulatedroomstudy5.util.ioThread

@Database(entities = arrayOf(Question::class),version = 1,exportSchema = false)
abstract class QuestionDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    companion object{

        @Volatile private var instance : QuestionDatabase? = null

        private val lock = Any()

        operator fun invoke(context : Context) : QuestionDatabase =
            instance ?: synchronized(lock) {
                instance ?: makeDatabase(context).also {
                    instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            QuestionDatabase::class.java,
            "questiondatabase"
        )
            .addCallback(object : Callback(){

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                ioThread {
                    invoke(context).questionDao().insertQuestionList(PREPOPULATED_QUESTION_DATA)
                }
            }

        })

            .build()

        val PREPOPULATED_QUESTION_DATA = listOf(
            Question(
                "My name is Alperen",
                "What is my name?",
                "Alperen",
                "George",
                "Michael",
                "Melih",
                "Alperen"
            ),
            Question(
                "Sultan 2nd Mehmet conquered Istanbul.",
                "Who conquered Istanbul?",
                "2nd Mehmet",
                "Young Osman",
                "Magnificent Sulejman",
                "Ertugrul Ghazi",
                "2nd Mehmet"
            ),
            Question(
                "Istanbul conquered at 1453",
                "When did Istanbul conquered?",
                "2020",
                "1962",
                "1453",
                "1071",
                "1453"
            ),
            Question(
                "Istanbul conquered at 1453",
                "When did Istanbul conquered?",
                "2020",
                "1962",
                "1453",
                "1071",
                "1453"
            )




        )
    }
}