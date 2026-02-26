package com.pedrojvdv.jdbc.model;

import com.pedrojvdv.jdbc.queries.DiscountQueries;

public enum DiscountType {
    PERMANENT("PERMANENTE", null),
    TEMPORARY("TEMPORÁRIO", null),
    SCHEDULED("AGENDADO", null),
    FLASH_SALE("OFERTA RELÂMPAGO", 6);

    private final String status;
    private final Integer situation;

    DiscountType(String status, Integer situation) {
        this.status = status;
        this.situation = situation;
    }

    public String getStatus() {
        return status;
    }

    public Integer getSituation() {
        return situation;
    }
}