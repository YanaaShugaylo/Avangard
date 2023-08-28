package com.avangard.bratstvo.points_exchange.details.presentation.adapter

import android.graphics.Color
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import coil.load
import com.avangard.bratstvo.databinding.PointsExchangeDetailsQrcodeItemBinding
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeRewardUiModel
import com.google.zxing.WriterException
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun pointsExchangeDetailsQrCodeAD(
) = adapterDelegateViewBinding<PrizeRewardUiModel.QrCode, Any, PointsExchangeDetailsQrcodeItemBinding>(
    { inflater, parent ->
        PointsExchangeDetailsQrcodeItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        val qrgEncoder = QRGEncoder(item.qrCode, null, QRGContents.Type.TEXT, 160)
        qrgEncoder.colorBlack = Color.BLACK
        qrgEncoder.colorWhite = Color.WHITE

        try {
            val bitmap = qrgEncoder.bitmap
            binding.qrIv.load(bitmap)
        } catch (e: WriterException) {
        }
    }
}