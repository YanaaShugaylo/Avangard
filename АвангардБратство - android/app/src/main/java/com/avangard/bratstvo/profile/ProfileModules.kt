package com.avangard.bratstvo.profile

import com.avangard.bratstvo.profile.personal_data.profilePersonalDataModule
import com.avangard.bratstvo.profile.root.profileRootModule

fun profileModules() = listOf(
    profileRootModule(),
    profilePersonalDataModule()
)