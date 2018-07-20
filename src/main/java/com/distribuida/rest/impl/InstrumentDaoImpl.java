package com.distribuida.rest.impl;

import com.distribuida.rest.entidades.Instrument;
import com.distribuida.rest.interfaces.InstrumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class InstrumentDaoImpl implements InstrumentDao {
    private final String LISTAR = "SELECT * FROM INSTRUMENT";
    private final String BUSCAR_POR_ID = "SELECT * FROM INSTRUMENT WHERE instrument_id=?";
    private final String INSERTAR = "INSERT INTO INSTRUMENT (INSTRUMENT_ID) values (?)";
    private final String UPDATE = "UPDATE INSTRUMENT SET INSTRUMENT_ID=? WHERE instrument_id=?";
    private final String DELETE = "DELETE FROM INSTRUMENT WHERE instrument_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Instrument> listar ( ) {
        return jdbcTemplate.query(LISTAR, new InstrumentMapper());
    }

    @Override
    public Instrument findById (String id) {
        return jdbcTemplate.queryForObject(BUSCAR_POR_ID, new Object[]{id}, new InstrumentMapper());
    }

    @Override
    public Instrument addInstrument (Instrument instrument) {
        Object[] datos = new Object[] { instrument.getInstrumentId()};
        int[] tipos = new int[]{Types.VARCHAR};
        int registro = jdbcTemplate.update(INSERTAR,datos,tipos);
        System.out.println("Se inserto: "+instrument.getInstrumentId());
        return instrument;
    }

    @Override
    public Instrument updateInstrument (Instrument instrument) {
        Object[] datos = new Object[] { instrument.getInstrumentId(), instrument.getInstrumentId()};
        int[] tipos = new int[]{Types.VARCHAR, Types.VARCHAR};
        int registro = jdbcTemplate.update(UPDATE,datos,tipos);
        System.out.println("Se actualizo: "+instrument.getInstrumentId());
        return instrument;
    }

    @Override
    public Integer deleteInstrument (Instrument instrument) {
        String id = instrument.getInstrumentId();
        return jdbcTemplate.update(DELETE, new Object[]{String.valueOf(id)});

    }
}
class InstrumentMapper implements RowMapper<Instrument> {

    @Override
    public Instrument mapRow (ResultSet rs, int rowNum) throws SQLException {
        Instrument instrument = new Instrument();
        instrument.setInstrumentId(rs.getString("instrument_id"));
        return instrument;
    }
}