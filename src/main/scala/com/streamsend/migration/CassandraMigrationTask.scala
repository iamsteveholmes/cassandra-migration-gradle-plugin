package com.streamsend.migration

import org.gradle.api.tasks.TaskAction

class CassandraMigrationTask extends CassandraTask {

    @TaskAction
    def migrate() {
        val migrator: Migrator = Migrator(getConfigurationFile, getMigrationDirectory, getMigrationName, getEnvironment, getLogger)
        migrator.migrate()
    }
}
