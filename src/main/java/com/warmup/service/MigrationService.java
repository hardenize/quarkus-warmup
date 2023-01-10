package com.warmup.service;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class MigrationService {

    private final Flyway flyway;

    public void checkMigration() {
        System.out.println(flyway.info().current().getVersion().toString());
    }
}