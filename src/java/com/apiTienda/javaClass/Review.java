/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiTienda.javaClass;

import java.io.Serializable;
import java.sql.Date;
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
    String autor;
    String imagen;
    private String comentario;
    private int estrellas;
    private Date fecha;

    public Review() {
    }

    public Review(int id, String autor, String imagen, String comentario, int estrellas, Date fecha) {
        this.id = id;
        this.autor = autor;
        this.imagen = imagen;
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagenUsuario() {
        return imagen;
    }

    public void setImagenUsuario(String imagenUsuario) {
        this.imagen = imagenUsuario;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }




    
    
}
