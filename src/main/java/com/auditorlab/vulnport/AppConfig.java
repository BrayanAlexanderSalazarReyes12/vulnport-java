package com.auditorlab.vulnport;

public final class AppConfig {

    private AppConfig() {}

    // LAB-SECRET-001: credenciales intencionalmente hardcodeadas.
    public static final String INTERNAL_API_USER = "lab_service";
    public static final String INTERNAL_API_PASSWORD = "LAB_ONLY_PASSWORD_123";
    public static final String BACKUP_KEY = "vulnport-backup-key-lab";
}
