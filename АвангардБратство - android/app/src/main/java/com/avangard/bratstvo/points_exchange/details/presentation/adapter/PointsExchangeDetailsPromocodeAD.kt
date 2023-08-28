package com.avangard.bratstvo.points_exchange.details.presentation.adapter

import android.graphics.Color
import android.view.View
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.view.isVisible
import coil.load
import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.PointsExchangeDetailsPromocodeItemBinding
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeRewardUiModel
import com.google.zxing.WriterException
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun pointsExchangeDetailsPromocodeAD(
    onItemClick: (Boolean, String) -> Unit
) = adapterDelegateViewBinding<PrizeRewardUiModel.Promocode, Any, PointsExchangeDetailsPromocodeItemBinding>(
    { inflater, parent ->
        PointsExchangeDetailsPromocodeItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item.qrCode != null, item.qrCode ?: item.code!!)
    }

    bind {
        when {
            item.code != null -> {
                binding.exchangePromocodeItemPrimaryTv.visibility = View.VISIBLE
                binding.exchangePromocodeItemPrimaryTv.text = item.code
                binding.exchangePromocodeItemPrimaryTv.textSize =
                    context.resources.getDimension(R.dimen.points_exchange_promocode_text_size)
                binding.exchangePromocodeItemSecondaryTv.text = item.date
                binding.exchangePromocodeItemQrcodeIv.visibility = View.GONE
                binding.exchangePromocodeItemCopyButtonIv.visibility = View.VISIBLE
            }
            item.qrCode != null -> {
                binding.exchangePromocodeItemQrcodeIv.visibility = View.VISIBLE
                binding.exchangePromocodeItemPrimaryTv.visibility = View.INVISIBLE
                binding.exchangePromocodeItemSecondaryTv.text = item.date
                binding.exchangePromocodeItemCopyButtonIv.visibility = View.GONE

                val qrgEncoder = QRGEncoder(item.qrCode, null, QRGContents.Type.TEXT, 128)
                qrgEncoder.colorBlack = Color.BLACK
                qrgEncoder.colorWhite = Color.WHITE

                try {
                    val bitmap = qrgEncoder.bitmap
                    binding.exchangePromocodeItemQrcodeIv.load(bitmap)
                } catch (e: WriterException) {
                }
            }
            else -> binding
        }
    }
}