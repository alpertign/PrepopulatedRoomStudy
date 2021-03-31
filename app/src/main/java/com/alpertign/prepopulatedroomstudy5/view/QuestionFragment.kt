package com.alpertign.prepopulatedroomstudy5.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.alpertign.prepopulatedroomstudy5.R
import com.alpertign.prepopulatedroomstudy5.databinding.FragmentQuestionBinding
import com.alpertign.prepopulatedroomstudy5.viewmodel.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_question.*


class QuestionFragment : Fragment() {

    private val viewModel :QuestionViewModel by activityViewModels()
    private lateinit var dataBinding : FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_question,container,false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)

        btn_details.setOnClickListener {
            findNavController().navigate(R.id.action_questionFragment_to_detailsFragment)
        }

        btn_random_question.setOnClickListener {
            viewModel.getRandomQuestionFromRoom()
            observeLiveData()
        }

    }

    private fun observeLiveData() {
        viewModel.questionLiveData.observe(viewLifecycleOwner, Observer { question ->
            question?.let {
                dataBinding.question = question
            }
        })
    }




}