package org.maria.diccionario.Controller;

import org.maria.diccionario.Exception.RecordNotFoundException;
import org.maria.diccionario.Model.Definicion;
import org.maria.diccionario.Model.Palabra;
import org.maria.diccionario.Service.DefinicionService;
import org.maria.diccionario.Service.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabras")
public class PalabraController {
    @Autowired
    private PalabraService palabraService;
    @Autowired
    private DefinicionService definicionService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Palabra>> findAll() {
        List<Palabra> list = palabraService.getAllPalabras();
        return new ResponseEntity<List<Palabra>>(list, new HttpHeaders(),
                HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> getPalabraPorId(@PathVariable int id) throws RecordNotFoundException { //con la anotación @PathVariable le estoy diciendo que este argumento es el dato que va a poner cliente en la ruta (id)
        Palabra palabra = palabraService.getPalabraPorId(id);
        List<Definicion> definiciones = definicionService.getDefinicionesByIdPalabra(id);
        if (palabra == null) {
            throw new RecordNotFoundException("No se ha encontrado la palabra",id);
        }
        palabra.setDefiniciones(definiciones);
        return new ResponseEntity<Palabra>(palabra, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Palabra> añadirPalabra(@RequestBody Palabra palabra) {
        if (palabraService.existePalabra(palabra.getTermino())){
            throw new RuntimeException("Palabra ya existe");
        }
        Palabra p = palabraService.addPalabra(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updatePalabra(@PathVariable int id , @RequestBody Palabra palabra)
            throws RecordNotFoundException {
        Palabra palabraUpdated = palabraService.updatePalabra(id , palabra);
        return ResponseEntity.status(HttpStatus.OK).body(palabraUpdated);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deletePalabrabyId(@PathVariable int id) throws RecordNotFoundException {
        palabraService.deletePalabra(id);
        return HttpStatus.ACCEPTED;
    }


    @CrossOrigin
    @PostMapping("/{id}/definiciones")
    public ResponseEntity<Palabra> agregarDefinicion(@PathVariable int id, @RequestBody Definicion definicion)
            throws RecordNotFoundException {
        Palabra palabraActualizada = palabraService.agregarDefinicion(id, definicion);
        return ResponseEntity.status(HttpStatus.OK).body(palabraActualizada);
    }

    @CrossOrigin
    @PostMapping("/con-definiciones")
    public ResponseEntity<Palabra>  agregarPalabraConDefinicion (@RequestBody Palabra palabra) throws RecordNotFoundException {
        if (palabraService.existePalabra(palabra.getTermino())){
            throw new RecordNotFoundException("Palabra ya existe",palabra.getTermino());
        }
        Palabra p = palabraService.addPalabra(palabra);
        if (palabra.getDefiniciones()!=null && !palabra.getDefiniciones().isEmpty()){
            for (Definicion definicion : palabra.getDefiniciones()) {
                definicion.setPalabra(p);
                definicionService.addDefinicion(definicion);
            }

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @CrossOrigin
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getPalabrasByCategoria(@PathVariable String categoria) {
        List<Palabra> palabras = palabraService.getPalabrasByCategoria(categoria);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/inicial/{letra}")
    public ResponseEntity<List<Palabra>> getPalabrasByInicial(@PathVariable String letra) {
        List<Palabra> palabras = palabraService.getPalabrasByInicial(letra);
        return new ResponseEntity<>(palabras, new HttpHeaders(), HttpStatus.OK);
    }







}
