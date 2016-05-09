/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiTienda.rest;

import com.apiTienda.objetos.Review;
import com.apiTienda.objetos.TransformadorJson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author ruben
 */
@Path("/reviews")
public class ApiReviews {

    TransformadorJson TJson = new TransformadorJson();
    Connection conn;

    @GET
    public String ObtenerTodas() {
        ArrayList<Review> lista = new ArrayList<>();
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaAngularJS", "root", "root");
            PreparedStatement ps = conn.prepareStatement("SELECT r.*,u.imagen,u.nombre FROM review r, usuario u WHERE r.idusuario = u.id");
            ResultSet res = ps.executeQuery();
            while(res.next()){
            Review r = new Review(Integer.parseInt(res.getString("id")),
                            res.getString("nombre"),
                            res.getString("imagen"),
                            res.getString("comentario"),
                            Integer.parseInt(res.getString("estrellas")),
                            res.getDate("fecha"));
            lista.add(r);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApiReviews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.TJson.toJson(lista.toArray());
    }

    @GET
    @Path("/{idProducto}")
    public String ObtenerReviewsPorId(@PathParam("idProducto") Integer idProducto) {
        ArrayList<Review> lista = new ArrayList<>();
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaAngularJS", "root", "root");
            PreparedStatement ps = conn.prepareStatement("SELECT r.*,u.imagen,u.nombre FROM review r, usuario u WHERE r.idusuario = u.id AND r.idproducto=?");
            ps.setInt(1, idProducto);
            ResultSet res = ps.executeQuery();
            while(res.next()){
            Review r = new Review(Integer.parseInt(res.getString("id")),
                            res.getString("nombre"),
                            res.getString("imagen"),
                            res.getString("comentario"),
                            Integer.parseInt(res.getString("estrellas")),
                            res.getDate("fecha"));
            lista.add(r);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApiReviews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.TJson.toJson(lista.toArray());
    }
}
