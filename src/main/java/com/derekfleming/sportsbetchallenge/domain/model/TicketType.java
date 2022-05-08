package com.derekfleming.sportsbetchallenge.domain.model;

public enum TicketType {
    CHILD("Children"),
    TEEN("Teen"),
    ADULT("Adult"),
    SENIOR("Senior");

    private final String value;

    TicketType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
