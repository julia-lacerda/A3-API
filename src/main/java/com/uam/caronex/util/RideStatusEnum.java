package com.uam.caronex.util;

public enum RideStatusEnum {

    AVAILABLE("available"),
    CANCELED("canceled"),
    CLOSED("closed"),
    FINISHED("finished");

    private final String description;

    RideStatusEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}