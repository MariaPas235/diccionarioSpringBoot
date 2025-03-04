package org.maria.diccionario.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "palabra")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String termino;

    private String categoriaGramatical;

    @OneToMany (mappedBy = "palabra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Definicion> definiciones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getCategoriaGramatical() {
        return categoriaGramatical;
    }

    public void setCategoriaGramatical(String categoriaGramatical) {
        this.categoriaGramatical = categoriaGramatical;
    }

    public List<Definicion> getDefiniciones() {
        return definiciones;
    }

    public void setDefiniciones(List<Definicion> definiciones) {
        this.definiciones = definiciones;
    }
}
