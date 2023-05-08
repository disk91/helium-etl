package com.disk91.etl.helium.legacy.model;

public class EtlState {
    protected boolean isOnPrimary;
    protected boolean isPrimaryResponding;
    protected boolean isBackupResponding;
    protected long primaryAgeMs;
    protected long primaryTopBlock;
    protected long backupAgeMs;
    protected long backupTopBlock;
    protected long primaryResponseTimeMs;
    protected long backupResponseTimeMs;

    public boolean isOnPrimary() {
        return isOnPrimary;
    }

    public void setOnPrimary(boolean onPrimary) {
        isOnPrimary = onPrimary;
    }

    public boolean isPrimaryResponding() {
        return isPrimaryResponding;
    }

    public void setPrimaryResponding(boolean primaryResponding) {
        isPrimaryResponding = primaryResponding;
    }

    public boolean isBackupResponding() {
        return isBackupResponding;
    }

    public void setBackupResponding(boolean backupResponding) {
        isBackupResponding = backupResponding;
    }

    public long getPrimaryAgeMs() {
        return primaryAgeMs;
    }

    public void setPrimaryAgeMs(long primaryAgeMs) {
        this.primaryAgeMs = primaryAgeMs;
    }

    public long getPrimaryTopBlock() {
        return primaryTopBlock;
    }

    public void setPrimaryTopBlock(long primaryTopBlock) {
        this.primaryTopBlock = primaryTopBlock;
    }

    public long getBackupAgeMs() {
        return backupAgeMs;
    }

    public void setBackupAgeMs(long backupAgeMs) {
        this.backupAgeMs = backupAgeMs;
    }

    public long getBackupTopBlock() {
        return backupTopBlock;
    }

    public void setBackupTopBlock(long backupTopBlock) {
        this.backupTopBlock = backupTopBlock;
    }

    public long getPrimaryResponseTimeMs() {
        return primaryResponseTimeMs;
    }

    public void setPrimaryResponseTimeMs(long primaryResponseTimeMs) {
        this.primaryResponseTimeMs = primaryResponseTimeMs;
    }

    public long getBackupResponseTimeMs() {
        return backupResponseTimeMs;
    }

    public void setBackupResponseTimeMs(long backupResponseTimeMs) {
        this.backupResponseTimeMs = backupResponseTimeMs;
    }
}
