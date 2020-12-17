package com.appscrip.tiviaapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appscrip.tiviaapp.R
import com.appscrip.tiviaapp.models.SurveyData

class SurveyDataAdapter: ListAdapter<SurveyData, SurveyDataAdapter.SurveyViewHolder>(SurveyComparator()) {


	class SurveyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val wordItemView: TextView = itemView.findViewById(R.id.tv_id)

		fun bind(SurveyData: SurveyData) {

			wordItemView.text = SurveyData.id.toString()
		}

		companion object {
			fun create(parent: ViewGroup): SurveyViewHolder {
				val view: View = LayoutInflater.from(parent.context)
					.inflate(R.layout.rv_survey_data, parent, false)
				return SurveyViewHolder(view)
			}
		}
	}

	class SurveyComparator : DiffUtil.ItemCallback<SurveyData>() {
		override fun areItemsTheSame(oldItem: SurveyData, newItem: SurveyData): Boolean {
			return oldItem === newItem
		}

		override fun areContentsTheSame(oldItem: SurveyData, newItem: SurveyData): Boolean {
			return oldItem.id == newItem.id
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyViewHolder {
		return SurveyViewHolder.create(parent)
	}

	override fun onBindViewHolder(holder: SurveyViewHolder, position: Int) {
		Log.i("onBindViewHolder",position.toString())
		val current = getItem(position)
		holder.bind(current)
	}
}
