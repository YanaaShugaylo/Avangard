package com.avangard.bratstvo.profile.personal_data.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.avangard.bratstvo.NavMainDirections
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.ProfilePersonalDataFragmentBinding
import com.avangard.bratstvo.profile.personal_data.presentation.adapter.ProfilePersonalDataAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfilePersonalDataFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: ProfilePersonalDataFragmentBinding? = null
    private val viewModel by viewModel<ProfilePersonalDataViewModel>()

    private val adapter by lazy {
        ProfilePersonalDataAdapter(
            onActionButtonClick = viewModel::logoutButtonClicked
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ProfilePersonalDataFragmentBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.profile_personal_data_title),
            ToolbarButtonsStates.ONLY_BACK_STATE,
            View.VISIBLE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.root?.adapter = adapter

        viewModel.privateDataUi.observe(viewLifecycleOwner) {
            adapter.items = it
        }

        viewModel.navigateToLogin.observe(viewLifecycleOwner) {
            if (it == true) {
                findNavController().navigate(NavMainDirections.actionGlobalStartFragment())
                viewModel.logoutCompleted()
            }
        }
    }
}