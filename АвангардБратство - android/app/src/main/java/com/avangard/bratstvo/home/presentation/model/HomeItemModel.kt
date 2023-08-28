package com.avangard.bratstvo.home.presentation.model

open class HomeItemModel {

    class Title(
        val text: String
    ) : HomeItemModel()

    class Task(
        val id: Int,
        val title: String,
        val points: Int,
        val categoryTitle: String
    ) : HomeItemModel()

    class Points(
        val count: Int
    ) : HomeItemModel()

    class CardWithBackground(
        val id: Int,
        val title: String? = null,
        val text: String? = null,
        val backgroundImageUrl: String? = null,
        val date: String? = null,
        val buttonText: String? = null,
        val iconUrl: String? = null
    ) : HomeItemModel()
}