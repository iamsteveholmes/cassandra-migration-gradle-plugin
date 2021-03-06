package com.streamsend.migration

import org.gradle.api.tasks.TaskAction

class CassandraInitializationTask extends CassandraTask {

    @TaskAction
    def initialize() {

        val migrator: Migrator = Migrator(getConfigurationFile, getMigrationDirectory, getMigrationName, getEnvironment, getLogger)
        migrator.initialize()
    }
}
