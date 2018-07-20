package com.distribuida.rest.interfaces;


import com.distribuida.rest.entidades.Instrument;

import java.util.List;

public interface InstrumentDao {
    public List<Instrument> listar();
    public Instrument findById(String id);
    public Instrument addInstrument(Instrument instrument);
    public Instrument updateInstrument(Instrument instrument);
    public Integer deleteInstrument(Instrument instrument);
}
