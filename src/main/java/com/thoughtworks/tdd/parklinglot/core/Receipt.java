package com.thoughtworks.tdd.parklinglot.core;

import java.util.Objects;
import java.util.UUID;

public class Receipt {
    private final UUID uuid;

    public Receipt() {
        this.uuid =  UUID.randomUUID();
    }

    public Receipt(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(uuid.toString(), receipt.uuid.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid.toString());
    }
}
