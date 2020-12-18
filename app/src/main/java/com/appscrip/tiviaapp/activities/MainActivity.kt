package com.appscrip.tiviaapp.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appscrip.tiviaapp.R
import com.appscrip.tiviaapp.adapters.SurveyDataAdapter
import com.appscrip.tiviaapp.applications.SurveyDataApplication
import com.appscrip.tiviaapp.fragments.SurveyFragment
import com.appscrip.tiviaapp.models.SurveyData
import com.appscrip.tiviaapp.viewmodels.HomeActivityViewModel
import com.appscrip.tiviaapp.viewmodels.HomeViewModelFactory
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
//	lateinit var viewStub :ViewStub
	lateinit var etName : TextInputEditText
	lateinit var btnNext : Button
	lateinit var surveyData : SurveyData
	var pageNo = 0
	private lateinit var fragmentManager: FragmentTransaction

	private val wordViewModel: HomeActivityViewModel by viewModels {
		HomeViewModelFactory((application as SurveyDataApplication).repository)
	}
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setTheme(R.style.mainAppTheme);
		initUI()
	}

	@SuppressLint("ResourceType")
	private fun initUI() {
		setContentView(R.layout.activity_main)
//		viewStub = findViewById(R.id.id_include_layout)
		loadFragment()
		val recyclerView = findViewById<RecyclerView>(R.id.rv_survey_data)
		val adapter = SurveyDataAdapter()
		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(this)

//		btnNext.setOnClickListener {
//			val valName = etName.text
//			if (valName.isNullOrEmpty()){
//				Toast.makeText(this, "Enter Valid Name", Toast.LENGTH_LONG).show()
//			}else{
//				SurveyData(name = valName.toString()).also { surveyData = it }
////				inflateLoyout(first_question_layout)
//			}
//		}
		// Add an observer on the LiveData returned by getAlphabetizedWords.
		// The onChanged() method fires when the observed data changes and the activity is
		// in the foreground.
		wordViewModel.allWords.observe(this) { surveyData ->
			// Update the cached copy of the words in the adapter.
			Log.i("surveyData", surveyData.toString())
			surveyData.let { adapter.submitList(it) }
		}


	}
	fun insertSurveyData(){
		val tsLong = System.currentTimeMillis() / 1000
		val ts = tsLong.toString()
		surveyData.createdAt = ts
		surveyData.updatedAt = ts

		wordViewModel.insert(surveyData)

	}

// create a FragmentManager
	private fun loadFragment() {
		supportFragmentManager.commit {
			setReorderingAllowed(true)
			add<SurveyFragment>(R.id.fragment_container_view)
		}

	}

	// Inflates the first question layout
	private fun inflateLoyout(layoutResource: Int) {

//		if (viewStub.getParent() != null) {
//			viewStub.layoutResource = layoutResource
//			viewStub.inflate();
//		} else {
//			viewStub.layoutResource = layoutResource
//		}
//		viewStub.inflate()
	}

	fun getNextPage(i: Int) {
		pageNo = i
		if(supportFragmentManager.isDestroyed || supportFragmentManager.fragments.isEmpty()){
			fragmentManager = supportFragmentManager.beginTransaction()
			fragmentManager.addToBackStack(null).add<SurveyFragment>(R.id.fragment_container_view).commit()
		}else{
			supportFragmentManager.commit {
				addToBackStack(null)
				replace<SurveyFragment>(R.id.fragment_container_view)
			}

		}
	}
}
