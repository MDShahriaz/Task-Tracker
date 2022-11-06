package com.example.login.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import com.example.login.R
import com.example.login.databinding.FragmentAddTaskBinding
import com.example.login.databinding.FragmentBottomSheetBinding
import com.example.login.taskcontrollernetwork.TaskData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class BottomSheetFragment(private var onItem:((taskData:TaskData)->Unit)):BottomSheetDialogFragment(){

    private lateinit var binding: FragmentBottomSheetBinding
    lateinit var task:TaskData
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBtn.setOnClickListener{
            val des = binding.description.text.toString()
            val spentH = binding.spentHour.text.toString().toInt()
            val spentM = binding.spentMinute.text.toString().toInt()
            task = TaskData(des,spentH,spentM)
            onItem(task)
            dismiss()
        }
    }
}