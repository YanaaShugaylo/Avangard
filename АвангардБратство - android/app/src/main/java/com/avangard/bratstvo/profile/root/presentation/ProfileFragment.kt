package com.avangard.bratstvo.profile.root.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.avangard.bratstvo.NavMainDirections
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.extensions.presentation.fragment.getColor
import com.avangard.bratstvo.base.extensions.presentation.fragment.getDrawable
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.ProfileFragmentBinding
import com.avangard.bratstvo.profile.root.presentation.adapter.ProfileSkillsAdapter
import com.avangard.bratstvo.profile.root.presentation.model.UserSkillUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: ProfileFragmentBinding? = null
    private val viewModel by viewModel<ProfileViewModel>()

    private val adapter: ProfileSkillsAdapter = ProfileSkillsAdapter()

    private val onClickListener = View.OnClickListener {
        onButtonClicked(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ProfileFragmentBinding.inflate(inflater)
        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.home_title),
            ToolbarButtonsStates.COMMON_STATE,
            View.VISIBLE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            profileTodataButtonBackgroundView.setOnClickListener(onClickListener)
            profilePointsBannerBackgroundView.setOnClickListener(onClickListener)
            profileRatingBannerBackgroundView.setOnClickListener(onClickListener)
            profileSkillsListButtonIv.setOnClickListener(onClickListener)
            profileSkillsDiagramButtonIv.setOnClickListener(onClickListener)

                this?.button?.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.pointsExchangeFragment2, null)
                )


            profileSkillsListRv.adapter = adapter

            viewModel.newBalanceUi.observe(viewLifecycleOwner)
            {
               // binding?.profilePointsBannerCountTv?.text = getString(R.string.profile_rating_pattern, it.totalSpent, it.totalEarned)
            }

            viewModel.balanceUi.observe(viewLifecycleOwner) {
                baseFragmentDelegate = BaseFragmentDelegate(
                    getString(R.string.profile_title),
                    ToolbarButtonsStates.ONLY_BACK_STATE,
                    View.VISIBLE
                )

               //profilePointsBannerCountTv.text = getString(R.string.profile_balance_count_pattern, it)
                var spannable = SpannableString(getString(R.string.profile_rating_pattern, it, it))
                spannable.setSpan(
                    AbsoluteSizeSpan(24, true),
                    7, 10,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                profileRatingBannerCountTv.text = spannable
            }

            viewModel.ratingUi.observe(viewLifecycleOwner) {
                var spannable = SpannableString(getString(R.string.profile_rating_pattern, it.position, it.total))
//
                spannable.setSpan(
                    AbsoluteSizeSpan(15, true),
                    2, 3,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannable.setSpan(
                    AbsoluteSizeSpan(22, true),
                    0, 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


                binding?.profilePointsBannerCountTv?.text = spannable
               // profilePointsBannerCountTv.text = getString(R.string.profile_rating_pattern, it.position, it.total)
            }

            viewModel.skillsUi.observe(viewLifecycleOwner) {
               adapter.items = it

                setRadarView(it)
            }

            viewModel.logoutOnFailure.observe(viewLifecycleOwner) {
                if (it == true) {
                    findNavController().navigate(NavMainDirections.actionGlobalStartFragment())
                    viewModel.logoutComplete()
                }
            }
        }
    }

    private fun onButtonClicked(view: View) {
        when (view.id) {
            binding?.profileTodataButtonBackgroundView?.id,
            binding?.profilePointsBannerBackgroundView?.id,
            binding?.profileRatingBannerBackgroundView?.id -> navigate(view.id)
            binding?.profileSkillsListButtonIv?.id -> toggleSkillsView(showList = true)
            binding?.profileSkillsDiagramButtonIv?.id -> toggleSkillsView(showList = false)
            binding?.button?.id -> navigate(id)
        }
    }

    private fun setRadarView(list: List<UserSkillUiModel>) {
        binding?.run {
            profileSkillsDiagramRadarView.skills = list
            profileSkillsDiagramRadarView.invalidate()

            toggleSkillsView(true)
        }
    }

    private fun toggleSkillsView(showList: Boolean) {
        binding?.run {
            profileSkillsListRv.isVisible = showList
            profileSkillsDiagramRadarView.isVisible = !showList
            profileDiagramTopLableTv.isVisible = !showList

            if (showList) {
                profileSkillsListButtonIv.background = getDrawable(requireContext(), R.drawable.skills_buttonbg_left_on)
                profileSkillsListButtonIv.imageTintList =
                    ColorStateList.valueOf(getColor(requireContext(), R.color.white))
                profileSkillsDiagramButtonIv.background =
                    getDrawable(requireContext(), R.drawable.skills_buttonbg_right_off)
                profileSkillsDiagramButtonIv.imageTintList =
                    ColorStateList.valueOf(getColor(requireContext(), R.color.profile_skills_buttons_icon_default))
            } else {
                profileSkillsListButtonIv.background =
                    getDrawable(requireContext(), R.drawable.skills_buttonbg_left_off)
                profileSkillsListButtonIv.imageTintList =
                    ColorStateList.valueOf(getColor(requireContext(), R.color.profile_skills_buttons_icon_default))
                profileSkillsDiagramButtonIv.background =
                    getDrawable(requireContext(), R.drawable.skills_buttonbg_right_on)
                profileSkillsDiagramButtonIv.imageTintList =
                    ColorStateList.valueOf(getColor(requireContext(), R.color.white))
            }
        }
    }

    private fun navigate(viewId: Int) {
        val action = when (viewId) {
            binding?.profileTodataButtonBackgroundView?.id -> {
                    ProfileFragmentDirections.actionProfileFragmentToPersonalDataFragment()
            }
            binding?.profilePointsBannerBackgroundView?.id -> {

                ProfileFragmentDirections.actionProfileFragmentToRatingInfoFragment()
            }
            binding?.profileRatingBannerBackgroundView?.id -> {
                   ProfileFragmentDirections.actionProfileFragmentToPointsExchangeFragment()
            }
            binding?.button?.id -> {
                ProfileFragmentDirections.actionProfileFragmentToPointsExchangeFragment()
            }
            else -> null
        }

        if (action != null) {
            binding?.root?.findNavController()?.navigate(action)
        }
    }
}