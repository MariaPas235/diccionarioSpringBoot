package org.maria.diccionario.Controller;


import org.maria.diccionario.Exception.RecordNotFoundException;
import org.maria.diccionario.Service.DefinicionService;
import org.maria.diccionario.Service.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/definicion")
public class DefinicionController {
    @Autowired
    private PalabraService palabraService;
    @Autowired
    private DefinicionService definicionService;

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deleteDefinicionById(@PathVariable int id) throws RecordNotFoundException {
        definicionService.deleteDefinicion(id);
        return HttpStatus.ACCEPTED;
    }
} 

