package com.appscrip.tiviaapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "survey_data_table")
data class SurveyData (
    @PrimaryKey(autoGenerate = true) var id: Int = 0  ,
    @ColumnInfo(name = "name") var name:String = "",
    @ColumnInfo(name = "question_one") var questionOne:String = "",
    @ColumnInfo(name = "question_two") var questionTwo:String = "",
    @ColumnInfo(name = "answer_one") var answerOne : String = "",
    @ColumnInfo(name = "answer_two") var answerTwo : String = "",
    @ColumnInfo(name = "createdAt") var createdAt :String = "",
    @ColumnInfo(name = "updatedAt") var updatedAt :String = ""
)
