package com.appscrip.tiviaapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appscrip.tiviaapp.R
import com.appscrip.tiviaapp.adapters.SurveyDataAdapter
import com.appscrip.tiviaapp.applications.SurveyDataApplication
import com.appscrip.tiviaapp.viewmodels.HomeActivityViewModel
import com.appscrip.tiviaapp.viewmodels.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
	private val newWordActivityRequestCode = 1
	private val wordViewModel: HomeActivityViewModel by viewModels {
		HomeViewModelFactory((application as SurveyDataApplication).repository)
	}
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setTheme(R.style.mainAppTheme);
		initUI()
	}

	private fun initUI() {
		setContentView(R.layout.activity_main)
		val recyclerView = findViewById<RecyclerView>(R.id.rv_survey_data)
		val adapter = SurveyDataAdapter()
		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(this)

		// Add an observer on the LiveData returned by getAlphabetizedWords.
		// The onChanged() method fires when the observed data changes and the activity is
		// in the foreground.
		wordViewModel.allWords.observe(owner = this) { words ->
			// Update the cached copy of the words in the adapter.
			words.let { adapter.submitList(it) }
		}

	}
}
