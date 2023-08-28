package com.avangard.bratstvo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.FragmentOfferBinding
import com.avangard.bratstvo.databinding.PointsExchangeFragmentBinding
import com.avangard.bratstvo.databinding.ProfileFragmentBinding
import com.avangard.bratstvo.education.root.presentation.adapter.EducationAdapter


class OfferFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: FragmentOfferBinding? = null
    private val adapter: OfferAdapter by lazy {
      OfferAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOfferBinding.inflate(inflater)
        with(binding)
        {
            this?.rvOffers?.adapter = adapter
            this?.rvOffers?.layoutManager = LinearLayoutManager(context)
        }

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.points_exchange_title),
            ToolbarButtonsStates.ONLY_BACK_STATE,
            View.GONE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }


}