package com.avangard.bratstvo.education.root.presentation.model

open class EducationItemUiModel {

    internal class Category(
        val id: Int,
        val title: String,
        val backgroundUrl: String?
    ) : EducationItemUiModel()

    internal class Common(
        val id: Int,
        val title: String
    ) : EducationItemUiModel()
}