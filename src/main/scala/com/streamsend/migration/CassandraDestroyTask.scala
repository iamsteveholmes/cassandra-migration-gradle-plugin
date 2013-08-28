package com.streamsend.migration

import org.gradle.api.tasks.TaskAction

class CassandraDestroyTask extends CassandraTask {

    @TaskAction
    def destroy() {
        val migrator: Migrator = Migrator(getConfigurationFile, getMigrationDirectory, getMigrationName, getEnvironment, getLogger)
        migrator.destory()
    }
}
