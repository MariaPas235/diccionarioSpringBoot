package org.maria.diccionario.Repository;

import org.maria.diccionario.Model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PalabraRepository extends JpaRepository<Palabra, Integer> {
    @Query("SELECT p FROM Palabra p WHERE p.termino = ?1")
    Palabra existePalabra(String termino);

    List<Palabra> findByCategoriaGramatical(String categoria);

    List<Palabra> findByTerminoStartingWith(String letra);
}
