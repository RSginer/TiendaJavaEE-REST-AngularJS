package com.apiTienda.modelo;


import java.sql.Timestamp;
/**
 *
 * @author ruben
 */
public class Review{
    private int id;
    private int idProducto;
    private int idUsuario;
    String autor;
    String imagen;
    private String comentario;
    private int estrellas;
    private Timestamp fecha;

    public Review() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Review(int id,int idUsuario,int idProducto, String autor, String imagen, String comentario, int estrellas, Timestamp fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.autor = autor;
        this.idProducto = idProducto;
        this.imagen = imagen;
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
}
