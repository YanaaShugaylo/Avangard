package com.avangard.bratstvo.tasks.root.data.mapper

import com.avangard.bratstvo.base.extensions.domain.string.getDateFromUtc
import com.avangard.bratstvo.base.extensions.domain.string.getFormatter
import com.avangard.bratstvo.tasks.details.data.model.TaskDetailsDto
import com.avangard.bratstvo.tasks.root.domain.mapper.ImageAttachmentDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TasksCategoryDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskDetailsDtoMapper
import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskUserDataDtoMapper
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TaskDetailsDtoMapperImpl(
    private val categoryMapper: TasksCategoryDtoMapper,
    private val taskUserDataDtoMapper: TaskUserDataDtoMapper,
    private val attachmentMapper: ImageAttachmentDtoMapper
) : TaskDetailsDtoMapper {

    override fun map(dto: TaskDetailsDto) = TaskDetails(
        dto.id,
        dto.type,
        dto.name,
        getEndDate(dto.endDate),
        dto.descriptionLink ?: "",
        dto.pointsReward,
        categoryMapper.map(dto.category),
        taskUserDataDtoMapper.map(dto.user),
        attachmentMapper.map(dto.attachment)
    )

    private fun getEndDate(endDate: String?): String = endDate?.let { date ->
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        calendar.time = getFormatter().parse(date)
        if (currentYear < calendar.get(Calendar.YEAR)) {
            date.getDateFromUtc("до dd MMMM yyyy'г'")
        } else {
            date.getDateFromUtc("до dd MMMM")
        }
    } ?: ""
}