package com.alpertign.prepopulatedroomstudy5.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alpertign.prepopulatedroomstudy5.R
import com.alpertign.prepopulatedroomstudy5.viewmodel.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_question.*


class QuestionFragment : Fragment() {

    private lateinit var viewModel :QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        viewModel.getDataFromRoom()

        btn_random_question.setOnClickListener {
            viewModel.getRandomQuestionFromRoom()
            observeLiveData()
        }

    }



    private fun observeLiveData() {
        viewModel.questionLiveData.observe(viewLifecycleOwner, Observer { question ->
            question?.let {
                tv_question.text = question.queString
            }
        })
    }




}