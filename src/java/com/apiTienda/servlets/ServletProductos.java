/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiTienda.servlets;

import com.apiTienda.javaClass.Producto;
import com.apiTienda.javaClass.Review;
import com.apiTienda.javaClass.TransformadorJson;
import com.apiTienda.javaClass.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author ruben
 */
public class ServletProductos extends HttpServlet {

    Connection con;
    TransformadorJson TJson = new TransformadorJson();
    ArrayList<Producto> listaProductos = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        

//Producto p = new Producto(1,"Zapatillas","Esto son unas zapatillas", "img/imagenProducto.jpg",100,40,80);
//Usuario u = new Usuario(1,"Rubén","img/user.jpg");
//Review r1 = new Review(1,1,u.getId(),"este es el comentario",3,u.getImagen());
//Review r2 = new Review(2,2,u.getId(),"este es el comentario 2",3,u.getImagen());
//Review r[] = {r1,r2};
//p.setReviews(r);
// try (PrintWriter out = res.getWriter()) {
//                    out.print(this.TJson.toJson(p));
//                }
//    }


        try {
            res.setContentType("application/json;charset=UTF-8");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaAngularJS", "root", "root");
            if (this.con != null) {

                // obtener productos
                String sqlProductos = "SELECT * FROM producto";
                PreparedStatement psProductos = con.prepareStatement(sqlProductos);
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
                    PreparedStatement psReviews = con.prepareStatement(sqlReviews);
                    psReviews.setInt(1, Integer.parseInt(resProducto.getString("id"))/* AQUI LA VARIABLE idProducto */);
                    ResultSet resReviews = psReviews.executeQuery();
                    ArrayList<Review> listaReviews = null;
                    while (resReviews.next()) {

                        //añadir usuario a la review
                        String sqlUsuarios = "SELECT * FROM usuario where id=?";
                        PreparedStatement psUsuario = con.prepareStatement(sqlUsuarios);
                        psUsuario.setInt(1, Integer.parseInt(resReviews.getString("idusuario")));
                        ResultSet resUsuarios = psUsuario.executeQuery();
                        String imagenUsuario = null;
                        int idUsuario = 0;
                        if (resUsuarios.next()) {
                            imagenUsuario = resUsuarios.getString("imagen");
                            idUsuario = Integer.parseInt(resUsuarios.getString("id"));
                        }
                        Review r = new Review(Integer.parseInt(resReviews.getString("id")),
                                Integer.parseInt(resReviews.getString("idproducto")),
                                idUsuario, resReviews.getString("comentario"), Integer.parseInt(resReviews.getString("estrellas")), imagenUsuario);
//                        listaReviews.add(r);
                    }
                    p.setReviews(listaReviews);

                    // fin añadir reviews
//                    this.listaProductos.add(p);
                }

                try (PrintWriter out = res.getWriter()) {
                    out.print(this.TJson.toJson(this.listaProductos.toArray()));
                }
            } else {

            }

        } catch (SQLException ex) {
            System.out.print(ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
