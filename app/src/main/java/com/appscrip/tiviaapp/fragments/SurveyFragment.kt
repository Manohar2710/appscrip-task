package com.appscrip.tiviaapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.*
import androidx.fragment.app.Fragment
import com.appscrip.tiviaapp.R
import com.appscrip.tiviaapp.activities.MainActivity
import com.appscrip.tiviaapp.models.SurveyData
import com.google.android.material.textfield.TextInputEditText
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val pageNo = "pageNo"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SurveyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SurveyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var context : MainActivity
	private lateinit var viewStub : ViewStub
    lateinit var btnNext : Button
    lateinit var btnFinish : Button
    lateinit var btnHistory : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(pageNo)
            param1?.let { it1 -> Log.i("param1", it1) }
            param2 = it.getString(ARG_PARAM2)
        }

    }
    private fun inflateLoyout(layoutResource: Int) {
        viewStub.layoutResource = layoutResource
        viewStub.inflate()
    }

    private fun initNextbtn(frameLayout: View) {

        when(context.pageNo){
            1 -> {
                btnNext = frameLayout.findViewById(R.id.btn_next)

                val questionOne: TextView = frameLayout.findViewById(R.id.tv_question_one)
                val rgAnswerOne: RadioGroup = frameLayout.findViewById(R.id.rg_answer_one)

                btnNext.setOnClickListener {
                    val valAnswer =
                        (frameLayout.findViewById(rgAnswerOne.checkedRadioButtonId) as RadioButton).text
                    Log.i("valAnswer", valAnswer.toString() + "---" + questionOne.text.toString())

                    if (valAnswer.isNullOrEmpty()) {
                        Toast.makeText(context, "Select at least one", Toast.LENGTH_LONG).show()
                    } else {
                        context.surveyData.answerOne = valAnswer.toString()
                        context.surveyData.questionOne = questionOne.text.toString()
                        Log.i("context.surveyData", context.surveyData.toString())
                        context.getNextPage(2)
                    }
                }
            }
            2 -> {
                btnNext = frameLayout.findViewById(R.id.btn_next)

                val questionTwo: TextView = frameLayout.findViewById(R.id.tv_question_two)
                val items: List<Int> = arrayListOf(
                    R.id.cb_option_1,
                    R.id.cb_option_2,
                    R.id.cb_option_3,
                    R.id.cb_option_4
                )
                var answerTwo = ""

                btnNext.setOnClickListener {
                    for (item in items) {
                        val cbView = (frameLayout.findViewById(item) as CheckBox)
                        Log.i(
                            "cbView",
                            cbView.id.toString() + "---" + cbView.text + "---" + cbView.isChecked
                        )
                        if (cbView.isChecked) {
                            answerTwo += "${cbView.text}, "
                        }
                    }
                    if (answerTwo.isEmpty()) {
                        Toast.makeText(context, "Select at least one", Toast.LENGTH_LONG).show()
                    } else {
                        context.surveyData.answerTwo = answerTwo
                        context.surveyData.questionTwo = questionTwo.text.toString()
                        Log.i("context.surveyData", context.surveyData.toString())
                        context.getNextPage(3)
                        context.insertSurveyData()
                    }
                }
            }
            0 -> {
                btnNext = frameLayout.findViewById(R.id.btn_next)
                val etName: TextInputEditText = frameLayout.findViewById(R.id.et_name)
                btnNext.setOnClickListener {
                    val valName = etName.text
                    if (valName.isNullOrEmpty()) {
                        Toast.makeText(activity, "Enter Valid Name", Toast.LENGTH_LONG).show()
                    } else {
                        context.surveyData = SurveyData()
                        context.surveyData.name = valName.toString()
                        context.getNextPage(1)


                    }
                }
            }
            else ->{
                btnFinish = frameLayout.findViewById(R.id.btn_finish)
                btnHistory = frameLayout.findViewById(R.id.btn_history)
                val tvFirstLine:TextView = frameLayout.findViewById(R.id.tv_first_line)
                val tvQAFirst:TextView = frameLayout.findViewById(R.id.tv_qa_one)
                val tvQASecond:TextView = frameLayout.findViewById(R.id.tv_qa_two)

                val res = resources
                val headLine: String = java.lang.String.format(res.getString(R.string.summary_first_line),context.surveyData.name)
                val qaFirst: String = java.lang.String.format(res.getString(R.string.qa_first),context.surveyData.questionOne,context.surveyData.answerOne)

                val qaSecond: String = java.lang.String.format(res.getString(R.string.qa_second),context.surveyData.questionTwo,context.surveyData.answerTwo)

                tvFirstLine.text = headLine
                tvQAFirst.text = qaFirst
                tvQASecond.text = qaSecond

                btnFinish.setOnClickListener {
                    context.getNextPage(0)
                }
                btnHistory.setOnClickListener {
                    Log.i("btnHistory", context.surveyData.toString())
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        context = (activity as MainActivity)
        Log.i("MainActivity().pageNo", context.pageNo.toString())

        val frameLayout =  inflater.inflate(R.layout.fragment_survey, container, false)
        viewStub = frameLayout.findViewById(R.id.id_include_layout)
        when(context.pageNo){
            1 -> inflateLoyout(R.layout.first_question_layout)
            2 -> inflateLoyout(R.layout.second_question_layout)
            3 -> inflateLoyout(R.layout.survey_summary)
            else ->inflateLoyout(R.layout.ask_name_layout)
        }
        initNextbtn(frameLayout)

        return frameLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SurveyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SurveyFragment().apply {
                arguments = Bundle().apply {
                    putString(pageNo, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}