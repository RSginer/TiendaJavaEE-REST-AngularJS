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
 * Api RESTful de productos
 * @author ruben
 */
@Path("/productos")
public class ApiProductos {
    private JDBC dataBase;
    private TransformadorJson TJson = new TransformadorJson();
    
    public ApiProductos()  {
        try {
            this.dataBase = new JDBC();
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos " + ex.getMessage());
        }
    }
    
    /**
     * Obtiene un array Json de todos los productos existentes en la base de datos
     * @return Array Json de productos
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String obtenerTodos(){
        List<Producto> listaProductos = new ArrayList<>();
        try {
            listaProductos = this.dataBase.getProductos();
        } catch (SQLException ex) {
            System.out.println("Error al rellenar la lista " + ex.getMessage());
        }
    return this.TJson.toJson(listaProductos);}
    
    /**
     * Dado un id de producto obtiene un objeto Json
     * @param id
     * @return 
     */
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String obtenerPorId(@PathParam("id") Integer id){
        Producto p= new Producto();
        try {
           p = this.dataBase.getProductoPorId(id);
        } catch (SQLException ex) {
            System.out.println("Error al obtener el producto " + ex.getMessage());
        }
    return this.TJson.toJson(p);}
    
}
