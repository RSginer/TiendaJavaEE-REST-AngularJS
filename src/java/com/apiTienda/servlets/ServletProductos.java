/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiTienda.servlets;

import com.apiTienda.javaClass.Producto;
import com.apiTienda.javaClass.Review;
import com.apiTienda.javaClass.TransformadorJson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    // <editor-fold defaon if a servlultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection con;
        TransformadorJson TJson = new TransformadorJson();
        ArrayList<Producto> listaProductos = new ArrayList<>();
        try {
            response.setContentType("application/json;charset=UTF-8");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaAngularJS", "root", "root");
            if (con != null) {

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
                        listaReviews.add(r);
                    }
                    p.setReviews(listaReviews);
                    // fin añadir reviews
                    listaProductos.add(p);
                }
                try (PrintWriter out = response.getWriter()) {
                    out.printf(TJson.toJson(listaProductos.toArray()));
                }
            } else {
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }

        processRequest(request, response);
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
