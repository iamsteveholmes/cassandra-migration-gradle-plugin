package com.streamsend.migration

class CassandraMigrationPluginExtension {
    String environment = "development"
    File configurationFile = new File("src/main/resources/application.conf")
    File migrationDirectory = new File("src/main/resources/migration")
    String migrationName
}
