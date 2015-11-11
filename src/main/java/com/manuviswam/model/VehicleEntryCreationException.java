package com.manuviswam.model;

public class VehicleEntryCreationException extends Exception {
    public VehicleEntryCreationException() {
    }

    public VehicleEntryCreationException(String message) {
        super(message);
    }

    public VehicleEntryCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleEntryCreationException(Throwable cause) {
        super(cause);
    }

    public VehicleEntryCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
