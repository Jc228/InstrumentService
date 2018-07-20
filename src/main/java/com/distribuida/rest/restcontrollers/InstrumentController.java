package com.distribuida.rest.restcontrollers;

import com.distribuida.rest.entidades.Instrument;
import com.distribuida.rest.interfaces.InstrumentDao;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.distribuida.rest.rabbit.Productor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/instrument")
public class InstrumentController {
    @Autowired
    private InstrumentDao instrumentRepository;
    @Autowired
    private Productor productor;

    @RequestMapping(value = "/listar", method = RequestMethod.GET )
    public List<Instrument> listar(){
        return instrumentRepository.listar();
    }

    @RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
    public Instrument buscarInstr(@PathVariable ("id") String id){
        return instrumentRepository.findById(id);
    }

    @RequestMapping(value = "/actualizar/{id}" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE )
    public Instrument actualizarInstrument(@PathVariable ("id") String id, @RequestBody Instrument actIstr){
        Instrument instrument = instrumentRepository.findById(id);
        instrument.setInstrumentId(actIstr.getInstrumentId());
        Instrument act = instrumentRepository.updateInstrument(instrument);
        return act;
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Instrument crearInst(@Valid @RequestBody Instrument instrument){
        productor.enviarMensaje(instrument);
        return instrumentRepository.addInstrument(instrument); }

    @RequestMapping(value = "/eliminar/{id}",method = RequestMethod.DELETE)
    public boolean eliminarInstrument(@PathVariable String id){
        boolean eliminar = false;
        Instrument eliminarIns = instrumentRepository.findById(id);
        if (eliminarIns!=null){
            instrumentRepository.deleteInstrument(eliminarIns);
            eliminar = true;
        }else{
            eliminar = false;
        }
        return eliminar;
    }

//    @HystrixCommand(fallbackMethod = "error")
//    @GetMapping("/test")
//    public String kk(){
//        System.out.println("Servido no disponible...");
//        return "error";
//    }
}
