/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiTienda.datos;

import com.apiTienda.modelo.Producto;
import com.apiTienda.modelo.Review;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rubén
 */
public class JDBC {
    private Connection conn;
    
    public JDBC() throws SQLException{
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaAngularJS", "root", "root");
    }
    
    public List<Producto> obtenerProductos() throws SQLException{
    List<Producto> listaProductos = new ArrayList<>();
    // obtener productos
                String sqlProductos = "SELECT * FROM producto";
                PreparedStatement psProductos = conn.prepareStatement(sqlProductos);
                ResultSet resProducto = psProductos.executeQuery();
                while (resProducto.next()) {

                    // creando producto
                    Producto p = new Producto(Integer.parseInt(resProducto.getString("id")),
                            resProducto.getString("nombre"),
                            resProducto.getString("descripcion"),
                            resProducto.getString("imagen"),
                            Integer.parseInt(resProducto.getString("stock")),
                            Double.parseDouble(resProducto.getString("precio")),
                            Double.parseDouble(resProducto.getString("precioAntes")));

                    //añadir sus reviews al producto
                    String sqlReviews = "SELECT * FROM review where idProducto=?";
                    PreparedStatement psReviews = conn.prepareStatement(sqlReviews);
                    psReviews.setInt(1, Integer.parseInt(resProducto.getString("id"))/* AQUI LA VARIABLE idProducto */);
                    ResultSet resReviews = psReviews.executeQuery();
                    ArrayList<Review> listaReviews = new ArrayList<>();
                    while (resReviews.next()) {

                        //añadir usuario a la review
                        String sqlUsuarios = "SELECT * FROM usuario where id=?";
                        PreparedStatement psUsuario = conn.prepareStatement(sqlUsuarios);
                        psUsuario.setInt(1, Integer.parseInt(resReviews.getString("idusuario")));
                        ResultSet resUsuarios = psUsuario.executeQuery();
                        resUsuarios.next();
                        Review r = new Review(Integer.parseInt(resReviews.getString("id")),
                                resUsuarios.getString("nombre"),
                                resUsuarios.getString("imagen"),
                                resReviews.getString("comentario"),
                                Integer.parseInt(resReviews.getString("estrellas")),
                                resReviews.getDate("fecha")
                        );
                        // fin añadir usuario
                        listaReviews.add(r);
                    }
                    p.setReviews(listaReviews);
                    // fin añadir reviews
                    listaProductos.add(p);
                }
                    // fin obtener productos
    return listaProductos;}
    
}

