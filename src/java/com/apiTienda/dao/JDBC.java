package com.apiTienda.dao;

import com.apiTienda.modelo.Producto;
import com.apiTienda.modelo.Review;
import com.apiTienda.modelo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para el manejo de datos con la base de datos
 *
 * @author Rubén
 *
 */
public class JDBC {

    private Connection conn;

    public JDBC() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaAngularJS", "root", "root");
    }

    /**
     * Busca en la base de datos todos los productos y los retorna en una lista
     *
     * @return devuelve una lista de objetos producto
     * @throws SQLException
     */
    public List<Producto> getProductos() throws SQLException {
        List<Producto> listaProductos = new ArrayList<>();
        String sqlProductos = "SELECT * FROM producto";
        PreparedStatement psProductos = this.conn.prepareStatement(sqlProductos);
        ResultSet resProducto = psProductos.executeQuery();
        while (resProducto.next()) {
            Producto p = new Producto(Integer.parseInt(resProducto.getString("id")),
                    resProducto.getString("nombre"),
                    resProducto.getString("descripcion"),
                    resProducto.getString("imagen"),
                    Integer.parseInt(resProducto.getString("stock")),
                    Double.parseDouble(resProducto.getString("precio")),
                    Double.parseDouble(resProducto.getString("precioAntes")));
            p.setReviews(this.getReviewsDeProducto(Integer.parseInt(resProducto.getString("id"))));
            listaProductos.add(p);
        }
        return listaProductos;
    }

    /**
     * Dado un id de producto devuelve una lista con todas sus reviews que
     * obtiene de la base de datos
     *
     * @param idProducto
     * @return devuelve un List de reviews
     * @throws SQLException
     */
    public List<Review> getReviewsDeProducto(int idProducto) throws SQLException {
        List<Review> listaReviews = new ArrayList<>();
        String sqlReviews = "SELECT * FROM review where idproducto=?";
        PreparedStatement psReviews = this.conn.prepareStatement(sqlReviews);
        psReviews.setInt(1, idProducto);
        ResultSet resReviews = psReviews.executeQuery();
        while (resReviews.next()) {
            int idUsuario =  Integer.parseInt(resReviews.getString("idusuario"));
            Usuario user = this.getUsuarioPorId(idUsuario);
            Review r = new Review(Integer.parseInt(resReviews.getString("id")),
                    idUsuario,
                    idProducto,
                    user.getNombre(),
                    user.getImagen(),
                    resReviews.getString("comentario"),
                    Integer.parseInt(resReviews.getString("estrellas")),
                    resReviews.getTimestamp("fecha")
            );
            listaReviews.add(r);
        }
        return listaReviews;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * Obtiene un usuario dado un id
     *
     * @param idUsuario
     * @return devuelve un objeto Usuario
     * @throws SQLException
     */
    public Usuario getUsuarioPorId(int idUsuario) throws SQLException {
        Usuario user = new Usuario();
        String sqlUsuarios = "SELECT * FROM usuario where id=?";
        PreparedStatement psUsuario = this.conn.prepareStatement(sqlUsuarios);
        psUsuario.setInt(1, idUsuario);
        ResultSet resUsuarios = psUsuario.executeQuery();
        resUsuarios.next();
        user.setImagen(resUsuarios.getString("imagen"));
        user.setNombre(resUsuarios.getString("nombre"));
        return user;
    }

    /**
     * Busca un producto en la base de datos dado un id
     *
     * @param idProducto
     * @return devuelve un objeto Producto
     * @throws SQLException
     */
    public Producto getProductoPorId(int idProducto) throws SQLException {
        Producto product;
        String sqlProducto = "SELECT * FROM producto WHERE id = ?";
        PreparedStatement psProducto = this.conn.prepareStatement(sqlProducto);
        psProducto.setInt(1, idProducto);
        ResultSet resProducto = psProducto.executeQuery();
        resProducto.next();
        product = new Producto(Integer.parseInt(resProducto.getString("id")),
                resProducto.getString("nombre"),
                resProducto.getString("descripcion"),
                resProducto.getString("imagen"),
                Integer.parseInt(resProducto.getString("stock")),
                Double.parseDouble(resProducto.getString("precio")),
                Double.parseDouble(resProducto.getString("precioAntes")));
        product.setReviews(this.getReviewsDeProducto(Integer.parseInt(resProducto.getString("id"))));
        return product;
    }
    
    public void setReview(Review r) throws SQLException{
    String sql = "INSERT INTO review VALUES(?,?,?,?,?,?)";
    PreparedStatement ps = this.conn.prepareStatement(sql);
    ps.setInt(1, r.getIdProducto());
    ps.setInt(2,r.getIdUsuario());
    ps.setInt(3, r.getEstrellas());
    ps.setString(4, r.getComentario());
    ps.setInt(5, r.getId());
    ps.setTimestamp(6, r.getFecha());
    ps.executeUpdate();
    }

}
