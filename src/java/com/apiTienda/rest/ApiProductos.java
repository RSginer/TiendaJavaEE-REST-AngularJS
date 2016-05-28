/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiTienda.rest;

import com.apiTienda.datos.JDBC;
import com.apiTienda.modelo.Producto;
import com.apiTienda.modelo.Review;
import com.apiTienda.modelo.TransformadorJson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ruben
 */
@Path("/productos")
public class ApiProductos {
    private JDBC dataBase;
    public ApiProductos()  {
        try {
            this.dataBase = new JDBC();
        } catch (SQLException ex) {
            Logger.getLogger(ApiProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String obtenerTodos(){
        TransformadorJson TJson = new TransformadorJson();
        List<Producto> listaProductos = new ArrayList<>();
        try {
            listaProductos = this.dataBase.obtenerProductos();
        } catch (SQLException ex) {
            System.out.println("error al rellenar la lista");
        }
    return TJson.toJson(listaProductos);}
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String obtenerPorId(@PathParam("id") Integer id){
          TransformadorJson TJson = new TransformadorJson();
          Producto p= new Producto();
        try {
            Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaAngularJS", "root", "root");
            String sqlProductos = "SELECT * FROM producto WHERE id= ?";
            PreparedStatement psProductos = con.prepareStatement(sqlProductos);
            psProductos.setInt(1, id);
            ResultSet resProducto = psProductos.executeQuery();
            resProducto.next();
             p = new Producto(Integer.parseInt(resProducto.getString("id")),
                    resProducto.getString("nombre"),
                    resProducto.getString("descripcion"),
                    resProducto.getString("imagen"),
                    Integer.parseInt(resProducto.getString("stock")),
                    Double.parseDouble(resProducto.getString("precio")),
                    Double.parseDouble(resProducto.getString("precioAntes")));
            
            //añadir sus reviews al producto
            String sqlReviews = "SELECT * FROM review where idProducto=?";
            PreparedStatement psReviews = con.prepareStatement(sqlReviews);
            psReviews.setInt(1, Integer.parseInt(resProducto.getString("id"))/* AQUI LA VARIABLE idProducto */);
            ResultSet resReviews = psReviews.executeQuery();
            ArrayList<Review> listaReviews = new ArrayList<>();
            while (resReviews.next()) {
                
                //añadir usuario a la review
                String sqlUsuarios = "SELECT * FROM usuario where id=?";
                PreparedStatement psUsuario = con.prepareStatement(sqlUsuarios);
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
          
        } catch (SQLException ex) {
            Logger.getLogger(ApiProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    return TJson.toJson(p);}
    

}
