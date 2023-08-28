package com.avangard.bratstvo

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.FragmentPointsDetailBinding
import com.avangard.bratstvo.databinding.ProfileFragmentBinding
import com.avangard.bratstvo.education.root.presentation.adapter.EducationAdapter
import com.avangard.bratstvo.profile.root.presentation.ProfileViewModel
import com.avangard.bratstvo.profile.root.presentation.adapter.ProfileSkillsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class PointsDetailFragment : Fragment() {
    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private val viewModel by viewModel<ProfileViewModel>()
    private var binding: FragmentPointsDetailBinding? = null
    private val adapter: PointsDetailAdapter by lazy {
       PointsDetailAdapter()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding?.button?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.pointsExchangeFragment2, null)
        )

        viewModel.historyUi.observe(viewLifecycleOwner)
        {

            binding?.rvBuy?.adapter = adapter
            binding?.rvBuy?.layoutManager = LinearLayoutManager(context)
            adapter.list = it.history
            adapter.notifyDataSetChanged()

        }

        viewModel.newBalanceUi.observe(viewLifecycleOwner)
        {
            binding?.tvEarned?.text = it.totalSpent.toString()
            binding?.tvSpent?.text = it.totalEarned.toString()
            var textView: TextView = requireView().findViewById(R.id.textView)
            var spannable = SpannableString(getString(R.string.profile_rating_pattern, it.balance, it.totalBalance))

            spannable.setSpan(
                ForegroundColorSpan(Color.BLACK),
                5, 11,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(
                AbsoluteSizeSpan(24, true),
                5, 11,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(
                AbsoluteSizeSpan(34, true),
                0, 5,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            spannable.setSpan(
                ForegroundColorSpan(Color.BLACK),
                5, 11,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


            textView.text = spannable
        }

//        with(binding){
//            this?.rvBuy?.adapter = adapter
//            this?.rvBuy?.layoutManager = LinearLayoutManager(context)
       // }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPointsDetailBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.home_title),
            ToolbarButtonsStates.ONLY_BACK_STATE,
            View.VISIBLE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




}