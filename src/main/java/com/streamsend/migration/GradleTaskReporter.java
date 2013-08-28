package com.streamsend.migration;

import com.streamsend.pillar.DataStore;
import com.streamsend.pillar.ReplicationOptions;
import com.streamsend.pillar.Reporter;
import com.streamsend.pillar.Migration;
import org.gradle.api.logging.Logger;
import scala.Option;

import java.util.Date;

public class GradleTaskReporter implements Reporter {
    
    private final Logger logger;

    public GradleTaskReporter(Logger logger) {
        this.logger = logger;
    }

    public void initializing(DataStore datastore,  ReplicationOptions replicationOptions) {
        logger.info("Initializing " + datastore.name() + " data store");
    }

    public void migrating(DataStore datastore, Option<Date> dateRestriction) {
        logger.info("Migrating " + datastore.name() + " data store");
    }

    public void applying(Migration migration) {
        logger.info("Applying " + migration.authoredAt().getTime() + ": " + migration.description() + "");
    }

    public void reversing(Migration migration) {
        logger.info("Reversing " + migration.authoredAt().getTime() + ": " + migration.description() + "");
    }

    public void destroying(DataStore datastore) {
        logger.info("Destroying " + datastore.name() + " data store");
    }
}
