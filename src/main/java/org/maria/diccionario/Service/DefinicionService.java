package org.maria.diccionario.Service;

import org.maria.diccionario.Exception.RecordNotFoundException;
import org.maria.diccionario.Model.Definicion;
import org.maria.diccionario.Model.Palabra;
import org.maria.diccionario.Repository.DefinicionRepository;
import org.maria.diccionario.Repository.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefinicionService {
    @Autowired
    private DefinicionRepository definicionRepository;
    @Autowired
    private PalabraRepository palabraRepository;

    public List<Definicion> getDefinicionesByIdPalabra(int idPalabra){
        return definicionRepository.getDefinicionesByIdPalabra(idPalabra);
    }

    public void deleteDefinicion(int id) throws RecordNotFoundException {
        Optional<Definicion> definicionOptional = definicionRepository.findById(id);
        if (definicionOptional.isPresent()){
            definicionRepository.delete(definicionOptional.get());
        }else{
            throw new RecordNotFoundException("No existe la definición para el id: ",id);
        }
    }

    public Definicion addDefinicion(Definicion definicion) throws RecordNotFoundException {
        return definicionRepository.save(definicion);
    }
}
