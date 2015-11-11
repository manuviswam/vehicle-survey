package com.manuviswam.model;

public class VehiclEntryCreationException extends Exception {
    public VehiclEntryCreationException() {
    }

    public VehiclEntryCreationException(String message) {
        super(message);
    }

    public VehiclEntryCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehiclEntryCreationException(Throwable cause) {
        super(cause);
    }

    public VehiclEntryCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
