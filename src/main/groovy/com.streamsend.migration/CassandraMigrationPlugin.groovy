package com.streamsend.migration

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.scala.ScalaPlugin
import org.gradle.api.tasks.testing.Test

class CassandraMigrationPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.create("cassandraMigration", CassandraMigrationPluginExtension)

        def configuration = {
            conventionMapping.configurationFile = {project.extensions.cassandraMigration.configurationFile}
            conventionMapping.migrationDirectory = {project.extensions.cassandraMigration.migrationDirectory}
            conventionMapping.migrationName = {project.extensions.cassandraMigration.migrationName}
            conventionMapping.environment = {project.hasProperty("environment") ? project.environment : project.extensions.cassandraMigration.environment}
        }

        project.task(type: CassandraInitializationTask, "initializeCassandra", configuration)
        project.task(type: CassandraMigrationTask, "migrateCassandra", configuration)
        project.task(type: CassandraDestroyTask, "destroyCassandra", configuration)

        project.plugins.withType(ScalaPlugin) {
            project.sourceSets {
                itest{
                    java.srcDir new File('src/itest/java')
                    scala.srcDir new File('src/itest/scala')
                }
            }

            project.configurations {
                itestCompile.extendsFrom testCompile
                itestRuntime.extendsFrom testRuntime
            }

            project.task("itest", type: Test, dependsOn: "assemble") {Test test ->

                test.testClassesDir = project.sourceSets.itest.output.classesDir
                test.classpath = project.sourceSets.itest.runtimeClasspath
                test.testSrcDirs = project.sourceSets.itest.java.srcDirs + project.sourceSets.itest.scala.srcDirs as List<File>
            }
        }
    }
}
