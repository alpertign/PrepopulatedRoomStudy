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
import com.alpertign.prepopulatedroomstudy5.databinding.FragmentDetailsBinding
import com.alpertign.prepopulatedroomstudy5.viewmodel.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_question.*


class DetailsFragment : Fragment() {

    private val viewModel :QuestionViewModel by activityViewModels()
    private lateinit var dataBinding : FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        observeLiveData()




        btn_back_to_question_fragment.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_questionFragment)
        }

        btn_new_question.setOnClickListener {
            viewModel.getRandomQuestionFromRoom()
            findNavController().navigate(R.id.action_detailsFragment_to_questionFragment)
        }
    }


    private fun observeLiveData(){
        viewModel.questionLiveData.observe(viewLifecycleOwner, Observer { question ->
            question?.let {
                dataBinding.question = question
            }
        })
    }



}