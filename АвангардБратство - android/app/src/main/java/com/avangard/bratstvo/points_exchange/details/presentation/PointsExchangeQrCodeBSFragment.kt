package com.avangard.bratstvo.points_exchange.details.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import coil.load
import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.PointsExchangeQrCodeBsFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.WriterException

class PointsExchangeQrCodeBSFragment : BottomSheetDialogFragment() {

    private var binding: PointsExchangeQrCodeBsFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PointsExchangeQrCodeBsFragmentBinding.inflate(inflater)

        val qrCodeData = if (arguments != null) {
            PointsExchangeQrCodeBSFragmentArgs.fromBundle(requireArguments()).qrCodeData
        } else {
            null
        }

        val qrgEncoder = QRGEncoder(qrCodeData, null, QRGContents.Type.TEXT, 260)
        qrgEncoder.colorBlack = Color.BLACK
        qrgEncoder.colorWhite = Color.WHITE

        try {
            val bitmap = qrgEncoder.bitmap
            binding?.qrCodeIv?.load(bitmap)
        } catch (e: WriterException) {
        }

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

        }
    }
}