package com.appscrip.tiviaapp.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appscrip.tiviaapp.models.SurveyData
import kotlinx.coroutines.flow.Flow


@Dao
interface SurveyDataDAO {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(surveyData: SurveyData)

	@Query("SELECT * FROM survey_data_table ORDER BY createdAt ASC")
	fun getAllSurveyData(): Flow<List<SurveyData>>
}
