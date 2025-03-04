package org.maria.diccionario.Service;

import org.maria.diccionario.Exception.RecordNotFoundException;
import org.maria.diccionario.Model.Definicion;
import org.maria.diccionario.Model.Palabra;
import org.maria.diccionario.Repository.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PalabraService {
    @Autowired
    private PalabraRepository palabraRepository;

    public List<Palabra> getAllPalabras() {
        List<Palabra> palabras = palabraRepository.findAll();
        if (palabras.isEmpty()) {
            System.out.println("No se han encontrado palabras");
            return new ArrayList<>();
        }else{
            return palabras;
        }
    }

    public Palabra getPalabraPorId(int id) throws RecordNotFoundException {
        Optional<Palabra> palabra = palabraRepository.findById(id);  //si no se encuentra el objeto, el optional controla el null si lo devuelve
        if (palabra.isPresent()) {
            return palabra.get();
        }else {
            throw new RecordNotFoundException("No existe la palabra para el id: " , id);
        }
    }

    public Palabra addPalabra(Palabra palabra) {

        if (existePalabra(palabra.getTermino())){
            throw new RuntimeException("La palabra ya existe");
        }
        return palabraRepository.save(palabra);
    }

    public boolean existePalabra(String termino) {
        if (palabraRepository.existePalabra(termino)!=null) {
            return true;
        }else return false;
    }

    public Palabra updatePalabra(int id, Palabra palabra) throws RecordNotFoundException {
        if (palabra.getId()>0){
            Optional<Palabra> palabraOptional = palabraRepository.findById(id);
            if (palabraOptional.isPresent()){
                Palabra newPalabra = palabraOptional.get();
                if (existePalabra(palabra.getTermino())){
                    throw new RuntimeException("La palabra ya existe");
                }else {
                    newPalabra.setTermino(palabra.getTermino());
                }

                newPalabra.setCategoriaGramatical(palabra.getCategoriaGramatical());
                newPalabra=palabraRepository.save(newPalabra);
                return newPalabra;
            }else{
                throw new RecordNotFoundException("No existe palabra para el id: ",palabra.getId());
            }
        }else{
            throw new RecordNotFoundException("No hay id en la palabra a actualizar ",id);
        }
    }

    public void deletePalabra(int id) throws RecordNotFoundException {
        Optional<Palabra> palabraOptional = palabraRepository.findById(id);
        if (palabraOptional.isPresent()){
            palabraRepository.delete(palabraOptional.get());
        }else{
            throw new RecordNotFoundException("No existe la palabra para el id: ",id);
        }
    }


   public Palabra agregarDefinicion(int id, Definicion definicion) throws RecordNotFoundException {
        Optional<Palabra> palabraOptional = palabraRepository.findById(id);
        if (!palabraOptional.isPresent()){
            throw new RecordNotFoundException("No existe la palabra para el id: ",id);
        }
        Palabra palabra = palabraOptional.get();
        definicion.setPalabra(palabra);
        if (palabra.getDefiniciones()==null){
            palabra.setDefiniciones(new ArrayList<>());
        }
        palabra.getDefiniciones().add(definicion);
        return palabraRepository.save(palabra);
   }

    public List<Palabra> getPalabrasByCategoria(String categoria) {
        return palabraRepository.findByCategoriaGramatical(categoria);
    }

    public List<Palabra> getPalabrasByInicial(String letra) {
        return palabraRepository.findByTerminoStartingWith(letra);
    }



}
