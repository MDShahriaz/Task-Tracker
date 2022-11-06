package com.example.login.ui.home

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.Constant.DATE_FORMAT_PATTERN
import com.example.login.TokenManager
import com.example.login.base.BaseFragment
import com.example.login.base.BaseViewModel
import com.example.login.databinding.FragmentAddTaskBinding
import com.example.login.taskcontrollernetwork.TaskData
import com.example.login.taskcontrollernetwork.taskpostmodel.TaskPostModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_task.*
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddTaskFragment : BaseFragment<AddTaskViewModel,FragmentAddTaskBinding>() {
    override val mViewModel: AddTaskViewModel by viewModels()
    override fun getViewBinding(): FragmentAddTaskBinding = FragmentAddTaskBinding.inflate(layoutInflater)
    private lateinit var tokenManager: TokenManager
    val taskDataList = mutableListOf<TaskData>()
    var taskAdapter = TaskAdapter(taskDataList)
//    val bottomSheetFragment = BottomSheetFragment(this::onItem)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenManager = TokenManager(requireContext())
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentTime = Calendar.getInstance().time
        val selectedDate = SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.ENGLISH).format(currentTime)
        mViewBinding.tvSelectedDate.text = selectedDate
        mViewBinding.apply {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager
            supportFragmentManager.setFragmentResultListener("REQUEST_KEY",viewLifecycleOwner) {
                    resultKey,bundle -> if(resultKey == "REQUEST_KEY"){
                        val date = bundle.getString("SELECTED_DATE")
                        tv_selected_date.text = date
                    }
            }
            btn_select_date.setOnClickListener{
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }

            taskList.adapter = taskAdapter
            taskList.layoutManager = LinearLayoutManager(requireContext())
            taskList.setHasFixedSize(true)
            addBtn.setOnClickListener{
                val bottomSheetFragment = BottomSheetFragment{
                    taskDataList.add(it)
                    taskAdapter.notifyDataSetChanged()
                }
                bottomSheetFragment.show(supportFragmentManager,"BottomSheetDialogue")
            }
            taskSubmitButton.setOnClickListener {
                val token = tokenManager.getToken()
                val obj = TaskPostModel(tvSelectedDate.text.toString(), taskDataList)
                token?.let { it1 -> mViewModel.addTask(it1,obj) }
            }
            mViewModel.addTaskResponse.observe(viewLifecycleOwner){
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            }

        }
    }

}