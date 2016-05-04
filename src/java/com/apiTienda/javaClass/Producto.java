/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiTienda.javaClass;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ruben
 */
@Entity
public class Producto implements Serializable{
    @Id
    private int id;
    private String nombre;
    private String descripcion;
    private String imagen;
    @OneToMany(mappedBy = "producto")
    private ArrayList<Review> reviews;
    private int stock;
    private double precio;
    private double precioAntes;

    public Producto() {
    }
    public Producto(int id, String nombre, String descripcion, String imagen, int stock, double precio) {
     this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.stock = stock;
        this.precio = precio;
    }
    public Producto(int id, String nombre, String descripcion, String imagen, int stock, double precio, double precioAntes) {
     this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.stock = stock;
        this.precio = precio;
        this.precioAntes = precioAntes;
    }

    public Producto(int id, String nombre, String descripcion, String imagen, ArrayList<Review> reviews, int stock, double precio, double precioAntes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.reviews = reviews;
        this.stock = stock;
        this.precio = precio;
        this.precioAntes = precioAntes;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioAntes() {
        return precioAntes;
    }

    public void setPrecioAntes(double precioAntes) {
        this.precioAntes = precioAntes;
    }
    
    
}
