package com.avangard.bratstvo.points_exchange.details.data

import android.util.Log
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.exceptions.domain.ClientException
import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.points_exchange.details.domain.PointsExchangeDetailsRepository
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeDetailsDtoMapper
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeDetails
import kotlinx.coroutines.withContext
import java.lang.Exception

class PointsExchangeDetailsRepositoryImpl(
    private val api: PointsExchangeDetailsApi,
    private val prizeDetailsDtoMapper: PrizeDetailsDtoMapper,
    private val appDispatchers: AppDispatchers
) : PointsExchangeDetailsRepository {

    override suspend fun getPrizeDetails(prizeId: Int) = withContext(appDispatchers.network) {
        var prizeDetails: PrizeDetails? = null

        try {
            val prizeDto = api.getPrizeDetails(prizeId).data
            Log.i("myLog", "prizeDto = $prizeDto")
            prizeDetails = prizeDetailsDtoMapper.map(prizeDto)
        } catch (e: Exception) {
            Log.i("myLog", "exception = $e")
        }

        prizeDetails
    }

    override suspend fun buyPrize(prizeId: Int) = withContext(appDispatchers.network) {
        var result: BaseBackendResponse<*>? = null

        try {
            result = api.buyPrize(prizeId)

            MainActivityInteractionsHelper.showMessage("Приз успешно куплен.")
        } catch (e: Exception) {
            if (e is ClientException) {
                MainActivityInteractionsHelper.showMessage(
                    "Нет доступных наград."
                )
            }
        }

        result != null
    }
}