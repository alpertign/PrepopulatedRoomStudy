package com.alpertign.prepopulatedroomstudy5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question (
    @ColumnInfo(name = "info")
    val info : String?,
    @ColumnInfo(name = "queString")
    val queString : String?,
    @ColumnInfo(name = "optA")
    val optA : String?,
    @ColumnInfo(name = "optB")
    val optB : String?,
    @ColumnInfo(name = "optC")
    val optC : String?,
    @ColumnInfo(name = "optD")
    val optD : String?,
    @ColumnInfo(name = "correctAns")
    val correctAns : String?
        ){
        @PrimaryKey(autoGenerate = true)
        var rowId : Int = 0
}