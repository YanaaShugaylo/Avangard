package com.avangard.bratstvo.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.avangard.bratstvo.BuildConfig
import com.avangard.bratstvo.NavMainDirections
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.HomeFragmentBinding
import com.avangard.bratstvo.home.presentation.adapter.HomeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: HomeFragmentBinding? = null
    private val viewModel by viewModel<HomeViewModel>()

    private val adapter: HomeAdapter by lazy {
        HomeAdapter(viewModel::onItemClick)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            "${getString(R.string.home_title)} ",
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

            viewModel.homeUi.observe(viewLifecycleOwner) {
                adapter.items = it
            }

            viewModel.navigateToType.observe(viewLifecycleOwner) {
                it?.let {
                    navigateToType(it, viewModel.itemId)
                }
            }

            viewModel.error.observe(viewLifecycleOwner) {
                if (it?.second == true) {
                    findNavController().navigate(NavMainDirections.actionGlobalStartFragment())
                    viewModel.logoutComplete()
                }

                if (!it?.first.isNullOrEmpty()) {
                    MainActivityInteractionsHelper.showMessage(it?.first!!)
                }
            }

            viewModel.isOnline.observe(viewLifecycleOwner) {
                this.offlineTv.isVisible = !it
            }
        }
    }

    private fun navigateToType(navigationType: HomeNavigationTypes, id: Int) {
        val action = when(navigationType) {
            HomeNavigationTypes.POINTS_EXCHANGE -> HomeFragmentDirections.actionHomeFragmentToPointsExchangeFragment()
            HomeNavigationTypes.TASK -> HomeFragmentDirections.actionHomeFragmentToTaskDetailsFragment(id)
            HomeNavigationTypes.LESSON -> HomeFragmentDirections.actionHomeFragmentToTestFragment()
            HomeNavigationTypes.SUPPORT -> HomeFragmentDirections.actionHomeFragmentToTestFragment()
        }

        binding?.root?.findNavController()?.navigate(action)
        viewModel.navigationComplete()
    }
}