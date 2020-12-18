package com.appscrip.tiviaapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "survey_data_table")
data class SurveyData (
	@PrimaryKey(autoGenerate = true) val id: Int,
	@ColumnInfo(name = "name") val name:String = "",
	@ColumnInfo(name = "questions") val questions:String = "",
	@ColumnInfo(name = "answer") val answer : ArrayList<String> = arrayListOf(),
	@ColumnInfo(name = "createdAt") val createdAt :String = "",
	@ColumnInfo(name = "updatedAt")val updatedAt :String = ""
)
