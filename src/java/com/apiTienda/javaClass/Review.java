/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiTienda.javaClass;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ruben
 */
@Entity
public class Review implements Serializable{
    @Id
    private int id;
    private int idProducto;
    private int idUsuario;
    String imagenUsuario;
    private String comentario;
    private int estrellas;
    @ManyToOne
    private Producto producto;

    public Review() {
    }

    public Review(int id, int idProducto, int idUsuario, String comentario, int estrellas, String imagenUsuario) {
        this.id = id;
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.imagenUsuario=imagenUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }
    
    
}
