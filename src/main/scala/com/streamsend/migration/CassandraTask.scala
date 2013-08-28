package com.streamsend.migration

import java.io.File
import org.gradle.api.tasks.{Input, InputDirectory, InputFile}
import org.gradle.api.internal.ConventionTask

abstract class CassandraTask extends ConventionTask {

    var configurationFile: File = _
    var migrationDirectory: File = _
    var migrationName: String = _
    var environment: String = _

    @InputFile
    def getConfigurationFile = configurationFile

    def setConfigurationFile(source: File) {
        configurationFile = source
    }

    @InputDirectory
    def getMigrationDirectory: File = migrationDirectory

    def setMigrationDirectory(migrationDirectory: File){
        this.migrationDirectory = migrationDirectory
    }

    @Input
    def getMigrationName = migrationName

    def setMigrationName(name: String) {
        this.migrationName = name
    }

    @Input
    def getEnvironment = environment

    def setEnvironment(environment: String) {
        this.environment = environment
    }
}
