package com.avangard.bratstvo.tests.root.presentation.model

internal open class TestItemUiModel {

    internal class Header(
        val questionNumber: Int,
        val time: String
    ) : TestItemUiModel()

    internal class Title(
        val title: String
    ) : TestItemUiModel()

    internal class Answer(
        val text: String,
        val selected: Boolean
    ) : TestItemUiModel()
}