package com.appscrip.tiviaapp.repositories

import androidx.annotation.WorkerThread
import com.appscrip.tiviaapp.models.SurveyData
import com.appscrip.tiviaapp.models.dao.SurveyDataDAO
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class SurveyRepository(private val surveyDataDAO: SurveyDataDAO) {

	// Room executes all queries on a separate thread.
	// Observed Flow will notify the observer when the data has changed.
	val allData: Flow<List<SurveyData>> = surveyDataDAO.getAllSurveyData()

	// By default Room runs suspend queries off the main thread, therefore, we don't need to
	// implement anything else to ensure we're not doing long running database work
	// off the main thread.
	@Suppress("RedundantSuspendModifier")
	@WorkerThread
	suspend fun insert(surveyData: SurveyData) {
		surveyDataDAO.insert(surveyData)
	}
}
