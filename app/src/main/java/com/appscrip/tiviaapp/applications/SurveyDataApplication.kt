package com.appscrip.tiviaapp.applications

import android.app.Application
import com.appscrip.tiviaapp.database.SurveyDataBase
import com.appscrip.tiviaapp.repositories.SurveyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SurveyDataApplication : Application() {
	// No need to cancel this scope as it'll be torn down with the process
	val applicationScope = CoroutineScope(SupervisorJob())

	// Using by lazy so the database and the repository are only created when they're needed
	// rather than when the application starts
	private val database by lazy { SurveyDataBase.getDatabase(this) }
	val repository by lazy { SurveyRepository(database.surveyDataDAO()) }
}
