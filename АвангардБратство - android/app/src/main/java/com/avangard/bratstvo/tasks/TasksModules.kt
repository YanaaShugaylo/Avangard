package com.avangard.bratstvo.tasks

import com.avangard.bratstvo.tasks.details.tasksDetailsModule
import com.avangard.bratstvo.tasks.done.tasksDoneModule
import com.avangard.bratstvo.tasks.root.tasksRootModule

fun tasksModules() = listOf(
    tasksRootModule(),
    tasksDetailsModule(),
    tasksDoneModule()
)