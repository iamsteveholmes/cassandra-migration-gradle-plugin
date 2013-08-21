package com.streamsend.migration

import org.gradle.api.DefaultTask
import java.io.File
import org.gradle.api.tasks.{TaskAction, InputFiles}

class CassandraInitializationTask extends DefaultTask {
  var migrationSource: java.lang.Iterable[File] = _

  @InputFiles
  def getMigrationSource = migrationSource

  @TaskAction
  def initialize() {

  }
}