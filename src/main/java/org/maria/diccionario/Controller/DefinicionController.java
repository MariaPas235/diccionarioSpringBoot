package org.maria.diccionario.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.maria.diccionario.Exception.RecordNotFoundException;
import org.maria.diccionario.Service.DefinicionService;
import org.maria.diccionario.Service.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/definicion")

@Tag(name = "Definicion ")
public class DefinicionController {
    @Autowired
    private PalabraService palabraService;
    @Autowired
    private DefinicionService definicionService;

    @Operation(summary = "borrar definición por id definición")

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deleteDefinicionById(@PathVariable int id) throws RecordNotFoundException {
        definicionService.deleteDefinicion(id);
        return HttpStatus.ACCEPTED;
    }
} 

