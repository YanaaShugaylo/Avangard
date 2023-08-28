package com.avangard.bratstvo.points_exchange.root.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.PointsExchangeFragmentBinding
import com.avangard.bratstvo.points_exchange.root.presentation.adapter.PointsExchangeCategoriesAdapter
import com.avangard.bratstvo.points_exchange.root.presentation.adapter.PointsExchangePartnersAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PointsExchangeFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: PointsExchangeFragmentBinding? = null
    private val viewModel by viewModel<PointsExchangeViewModel>()

    private val categoriesAdapter: PointsExchangeCategoriesAdapter by lazy {
        PointsExchangeCategoriesAdapter(viewModel::onCategoryItemClick)
    }
    private val partnersAdapter: PointsExchangePartnersAdapter by lazy {
        PointsExchangePartnersAdapter(::onItemClick)
    }

    private val partnersRvOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding?.pointsExchangePartnersRv?.layoutManager?.let { layoutManager ->
                    val visibleItemCount: Int = layoutManager.childCount
                    val totalItemCount: Int = layoutManager.itemCount
                    val firstVisibleItemPosition: Int =
                        (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (viewModel.loadingStatus != LoadingStatus.LOADING && !viewModel.lastPage) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE
                        ) {
                            viewModel.loadMorePrizes()
                        }
                    }
                }
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PointsExchangeFragmentBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.points_exchange_title),
            ToolbarButtonsStates.ONLY_BACK_STATE,
            View.GONE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.offset_10)

        binding?.run {
            pointsExchangeCategoriesRv.adapter = categoriesAdapter
            pointsExchangePartnersRv.layoutManager = GridLayoutManager(requireContext(), 2)
            pointsExchangePartnersRv.addItemDecoration(
                GridSpacingItemDecoration(2, spacingInPixels, false)
            )
            pointsExchangePartnersRv.adapter = partnersAdapter
            pointsExchangePartnersRv.addOnScrollListener(partnersRvOnScrollListener)

            viewModel.categoriesUi.observe(viewLifecycleOwner) {
                categoriesAdapter.items = it
            }

            viewModel.prizesUi.observe(viewLifecycleOwner) {
                partnersAdapter.items = it
            }
        }
    }

    private fun onItemClick(prizeId: Int) {
        val action =
            PointsExchangeFragmentDirections
                .actionPointsExchangeFragmentToPointsExchangeDetailsBSFragment(prizeId)
        binding?.root?.findNavController()?.navigate(action)
    }

    private companion object {
        const val PAGE_SIZE = 10
    }
}