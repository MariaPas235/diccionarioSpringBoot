package org.maria.diccionario.Repository;

import org.maria.diccionario.Model.Definicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DefinicionRepository extends JpaRepository<Definicion,Integer> {
    @Query("SELECT d FROM Definicion d WHERE d.palabra.id = ?1")
    List<Definicion> getDefinicionesByIdPalabra(int id);

}
