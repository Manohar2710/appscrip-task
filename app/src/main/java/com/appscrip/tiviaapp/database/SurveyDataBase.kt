package com.appscrip.tiviaapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appscrip.tiviaapp.models.SurveyData
import com.appscrip.tiviaapp.models.dao.SurveyDataDAO

@Database(entities = [SurveyData::class], version = 1, exportSchema = false)
abstract class SurveyDataBase  : RoomDatabase(){
	abstract fun surveyDataDAO(): SurveyDataDAO
	companion object {
		// Singleton prevents multiple instances of database opening at the
		// same time.
		@Volatile
		private var INSTANCE: SurveyDataBase? = null

		fun getDatabase(context: Context): SurveyDataBase {
			// if the INSTANCE is not null, then return it,
			// if it is, then create the database
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					SurveyDataBase::class.java,
					"survey_database"
				).build()
				INSTANCE = instance
				// return instance
				instance
			}
		}
	}
}
