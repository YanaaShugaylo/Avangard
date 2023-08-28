package com.avangard.bratstvo.tests.root.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.TestFragmentBinding
import com.avangard.bratstvo.tests.root.presentation.adapter.TestAdapter
import com.avangard.bratstvo.tests.root.presentation.model.TestItemUiModel

class TestFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: TestFragmentBinding? = null
    private val adapter: TestAdapter = TestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TestFragmentBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.test_title),
            ToolbarButtonsStates.MY_STATE,
            View.GONE,

        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            testActionButton.setOnClickListener {
                val action =
                    TestFragmentDirections
                        .actionTestFragmentToTestAnswerBSFragment()
                binding?.root?.findNavController()?.navigate(action)
            }

            testContentRv.adapter = adapter
            adapter.items = listOf(
                TestItemUiModel.Header(10, ""),
                TestItemUiModel.Title("Very big long and complicated question"),
                TestItemUiModel.Answer("Answer", false),
                TestItemUiModel.Answer("Answer 1", false),
                TestItemUiModel.Answer("Answer 2", false)
            )
        }
    }
}