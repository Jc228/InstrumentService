package com.distribuida.rest.entidades;

import java.io.Serializable;

public class Instrument implements Serializable {
    private String instrumentId;

    public Instrument ( ) {
    }

    public Instrument (String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrumentId() {
        return this.instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    @Override
    public String toString() {
        return "Instrument :" + getInstrumentId();
    }
}
