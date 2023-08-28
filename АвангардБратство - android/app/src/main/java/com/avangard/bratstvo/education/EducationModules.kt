package com.avangard.bratstvo.education

import com.avangard.bratstvo.education.details.educationDetailsModule
import com.avangard.bratstvo.education.root.educationRootModule

fun educationModules() = listOf(
    educationRootModule(),
    educationDetailsModule()
)