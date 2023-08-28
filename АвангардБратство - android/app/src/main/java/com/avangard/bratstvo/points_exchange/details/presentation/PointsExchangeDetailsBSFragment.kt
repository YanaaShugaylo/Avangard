package com.avangard.bratstvo.points_exchange.details.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.activity.OnBackPressedCallback
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.navigation.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.databinding.PointsExchangeDetailsBsFragmentBinding
import com.avangard.bratstvo.points_exchange.details.presentation.adapter.PointsExchangeDetailsPromocodesAdapter
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeDetailsUiModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.WriterException
import org.koin.androidx.viewmodel.ext.android.viewModel

class PointsExchangeDetailsBSFragment : BottomSheetDialogFragment() {

    private var binding: PointsExchangeDetailsBsFragmentBinding? = null
    private val viewModel by viewModel<PointsExchangeDetailsViewModel>()

    private val adapter: PointsExchangeDetailsPromocodesAdapter by lazy {
        PointsExchangeDetailsPromocodesAdapter(::onItemClick)
    }

    private val onClickListener: View.OnClickListener by lazy {
        View.OnClickListener {
            onButtonClick(it.id)
        }
    }

    private val onBackPressedCallback: OnBackPressedCallback by lazy {
        object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                viewModel.onBackClick()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PointsExchangeDetailsBsFragmentBinding.inflate(inflater)

        val prizeId = if (arguments != null) {
            PointsExchangeDetailsBSFragmentArgs.fromBundle(requireArguments()).prizeId
        } else {
            null
        }

        Log.i("myLog", "prizeId = $prizeId")

        viewModel.loadPrize(prizeId)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            (root.parent as View).run {
                updateLayoutParams<CoordinatorLayout.LayoutParams> {
                    height = CoordinatorLayout.LayoutParams.MATCH_PARENT
                }

                (parent as View).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.transparent))
                (parent.parent as View).setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.transparent
                    )
                )
            }

            exchangeDetailsPromocodesRv.adapter = adapter

            exchangeDetailsActionButton.setOnClickListener(onClickListener)
            exchangeDetailsPromocodesButton.setOnClickListener(onClickListener)
            exchangeDetailsCloseButtonIv.setOnClickListener(onClickListener)
            exchangeDetailsBackButtonIv.setOnClickListener(onClickListener)
        }

        viewModel.screenState.observe(viewLifecycleOwner) {
            onBackPressedCallback.isEnabled = it == ScreenState.LIST || it == ScreenState.QR

            viewModel.prizeUi.value?.let { prizeUi ->
                setUiVisibility(prizeUi, it)
            }
        }

        viewModel.prizeUi.observe(viewLifecycleOwner) {
            if (it != null && viewModel.screenState.value != null) {
                setUi(it, viewModel.screenState.value!!)

                adapter.items = it.rewards
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding?.loaderBackgroundView?.isVisible = it
            binding?.loaderPb?.isVisible = it
        }

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        onBackPressedCallback.isEnabled = true
    }

    private fun setUi(uiModel: PrizeDetailsUiModel, screenState: ScreenState) {
        binding?.run {
            val cornersRadius = resources.getDimension(R.dimen.offset_8)

            exchangeDetailsBackgroundIv.load(uiModel.backgroundImageLink)
            exchangeDetailsIconIv.load(uiModel.imageLink) {
                transformations(
                    RoundedCornersTransformation(
                        cornersRadius,
                        cornersRadius,
                        cornersRadius,
                        cornersRadius
                    )
                )
            }
            exchangeDetailsTitleTv.text = uiModel.title
            exchangeDetailsDescriptionTv.text = uiModel.description
            exchangeDetailsActionButton.text = getString(R.string.points_exchange_action_button_pattern, uiModel.price)

            exchangeDetailsPromocodesButton.text =
                getString(R.string.points_exchange_promocodes_button_pattern, uiModel.rewards.size - 1)

            setUiVisibility(uiModel, screenState)
        }
    }

    private fun setUiVisibility(uiModel: PrizeDetailsUiModel, screenState: ScreenState) {
        binding?.run {
            exchangeDetailsTitleTv.isVisible = isStateMain(screenState)
            exchangeDetailsDescriptionTv.isVisible = isStateMain(screenState)
            exchangeDetailsActionButton.isVisible = isStateMain(screenState)
            exchangeDetailsPromocodesButton.isVisible = isStateMain(screenState)
            exchangeDetailsBackgroundIv.isVisible = isStateMain(screenState)
            exchangeDetailsIconIv.isVisible = isStateMain(screenState)
            exchangeDetailsCloseButtonIv.isVisible = isStateMain(screenState)

            exchangeDetailsBackButtonIv.isVisible = !isStateMain(screenState)
            exchangeDetailsPromocodesCountTv.isVisible = !isStateMain(screenState)
            exchangeDetailsPromocodesRv.isVisible = screenState == ScreenState.LIST

            exhangeDetailsQrIv.isVisible = screenState == ScreenState.QR

            exchangeDetailsPromocodesButton.isVisible = uiModel.rewards.isNotEmpty() && isStateMain(screenState)
        }
    }

    private fun onButtonClick(viewId: Int) {
        when (viewId) {
            binding?.exchangeDetailsCloseButtonIv?.id -> requireActivity().onBackPressed()
            binding?.exchangeDetailsActionButton?.id -> viewModel.onBuyPrizeClick()
            binding?.exchangeDetailsPromocodesButton?.id -> viewModel.onPromocodesClick()
            binding?.exchangeDetailsBackButtonIv?.id -> requireActivity().onBackPressed()
        }
    }

    private fun onItemClick(isQrCode: Boolean, code: String) {
        if (isQrCode) {
            val qrgEncoder = QRGEncoder(code, null, QRGContents.Type.TEXT, 360)
            qrgEncoder.colorBlack = Color.BLACK
            qrgEncoder.colorWhite = Color.WHITE

            try {
                val bitmap = qrgEncoder.bitmap
                binding?.exhangeDetailsQrIv?.load(bitmap)
            } catch (e: WriterException) {
            }

            viewModel.onQrClick()
        } else {
            val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Promocode", code)
            clipboard.setPrimaryClip(clip)

            MainActivityInteractionsHelper.showMessage("Промокод скопирован в буфер обмена")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.isEnabled = false
    }

    private fun isStateMain(screenState: ScreenState) = screenState == ScreenState.MAIN
}