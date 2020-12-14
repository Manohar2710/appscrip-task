package com.appscrip.tiviaapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appscrip.tiviaapp.R
import com.appscrip.tiviaapp.adapters.SurveyDataAdapter

class MainActivity : AppCompatActivity() {
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
	}
}
