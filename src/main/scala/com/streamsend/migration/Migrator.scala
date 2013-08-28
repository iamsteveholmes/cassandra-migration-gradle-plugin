package com.streamsend.migration

import com.streamsend.pillar._
import com.typesafe.config.{ConfigFactory, Config}
import java.io.File
import org.gradle.api.logging.Logger

object Migrator {
    def apply(
                     configurationFile: File,
                     migrationDirectory: File,
                     migrationName: String,
                     environment: String,
                     logger: Logger): Migrator = {

        val config: Config = ConfigFactory.parseFile(configurationFile)

        val dataStore: DataStore = DataStore.fromConfiguration(migrationName, environment, config)

        val registry: Registry = Registry.fromDirectory(migrationDirectory)

        val reporter: Reporter = new GradleTaskReporter(logger)

        val migrator = com.streamsend.pillar.Migrator(registry, reporter)

        new Migrator(migrator, dataStore)
    }
}

class Migrator(migrator: com.streamsend.pillar.Migrator, dataStore: DataStore) {

    def initialize(){
        migrator.initialize(dataStore)
    }

    def migrate(){
        migrator.migrate(dataStore)
    }

    def destory() {
        migrator.destroy(dataStore)
    }
}
