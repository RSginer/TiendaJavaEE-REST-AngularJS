package com.apiTienda.rest;

import com.apiTienda.dao.JDBC;
import com.apiTienda.modelo.Review;
import com.apiTienda.modelo.TransformadorJson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Api RESTful de reviews
 * @author ruben
 */
@Path("/reviews")
public class ApiReviews {

    private TransformadorJson TJson = new TransformadorJson();
    private JDBC dataBase;
    
    public ApiReviews() throws SQLException{
        this.dataBase = new JDBC();
    }

    @GET
    @Path("/{idProducto}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getReviewsPorProducto(@PathParam("idProducto") Integer idProducto) {
        List<Review> listaReviews = new ArrayList<>();
        try {
            listaReviews = this.dataBase.getReviewsDeProducto(idProducto);
        } catch (SQLException ex) {
            System.out.println("Error al obtener las reviews del productto " + idProducto + "." + ex.getMessage());
        }
        return this.TJson.toJson(listaReviews.toArray());
    }
    
    @POST
    public void setReview(String JSonReview){
       Review r = (Review) this.TJson.fromJson(JSonReview, Review.class);
        try {
            this.dataBase.setReview(r);
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar review");
        }
    }
}
