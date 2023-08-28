package com.avangard.bratstvo.tasks.root.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.TasksFragmentBinding
import com.avangard.bratstvo.tasks.root.presentation.adapter.TasksAdapter
import com.avangard.bratstvo.tasks.root.presentation.model.TasksItemUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: TasksFragmentBinding? = null
    private val viewModel by viewModel<TasksViewModel>()

    private val adapter: TasksAdapter by lazy {
        TasksAdapter(viewModel::onItemClick)
    }

    private val onBackPressedCallback: OnBackPressedCallback by lazy {
        object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                viewModel.onBackClick()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TasksFragmentBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.tasks_title),
            ToolbarButtonsStates.MAIN_STATE,
            View.VISIBLE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            contentRv.adapter = adapter

            viewModel.tasksUi.observe(viewLifecycleOwner) {
                adapter.items = it
            }

            viewModel.navigateToTask.observe(viewLifecycleOwner) {
                if (it > 0) {
                    navigateToTask(it)
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
            onBackPressedCallback.isEnabled = false

            viewModel.isCategoryScreen.observe(viewLifecycleOwner) {
                if (it) {
                    baseFragmentDelegate.screenToolbarButtonsState = ToolbarButtonsStates.COMMON_STATE
                    onBackPressedCallback.isEnabled = true
                } else {
                    baseFragmentDelegate.screenToolbarButtonsState = ToolbarButtonsStates.MAIN_STATE
                    onBackPressedCallback.isEnabled = false
                }

                baseFragmentDelegate.notifyDataSetChanged()
            }

            viewModel.isOnline.observe(viewLifecycleOwner) {
                this.offlineTv.isVisible = !it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.isEnabled = false
    }

    private fun navigateToTask(taskId: Int) {
        val action =
            TasksFragmentDirections.actionTasksFragmentToTaskDetailsFragment(taskId)
        binding?.root?.findNavController()?.navigate(action)
        onBackPressedCallback.isEnabled = false
        viewModel.navigationComplete()
    }
}