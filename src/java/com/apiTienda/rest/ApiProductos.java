/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiTienda.rest;

import com.apiTienda.datos.JDBC;
import com.apiTienda.modelo.Producto;
import com.apiTienda.modelo.TransformadorJson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("Error al conectar con la base de datos " + ex.getMessage());
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
            System.out.println("error al rellenar la lista " + ex.getMessage());
        }
    return TJson.toJson(listaProductos);}
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String obtenerPorId(@PathParam("id") Integer id){
          TransformadorJson TJson = new TransformadorJson();
          Producto p= new Producto();
        try {
           p = this.dataBase.obtenerProductoPorId(id);
        } catch (SQLException ex) {
            System.out.println("error al obtener el producto " + ex.getMessage());
        }
    return TJson.toJson(p);}
    

}
