import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.*;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class GestionProductosTest {

    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/usurbiltex";
    private String user = "root";
    private String pass = "password";
    private java.util.Date fecha;

    @Test
    public void testAgregarProducto() throws Exception {
        conn = DriverManager.getConnection(url, user, pass);
        fecha = new java.util.Date();

        String nombre = "ProductoTest";
        String descripcion = "Descripción de prueba";
        Double precio = 100.0;
        int stock = 50;
        int categoria = 1;
        String imagen = "imagen.jpg";
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());

        GestionProductos gestionProductos = new GestionProductos();
        gestionProductos.AgregarProductoBD(nombre, descripcion, precio, stock, fecha, imagen, categoria, sqlDate);

        String query = "SELECT * FROM producto WHERE Nombre = ?";
        PreparedStatement psm = conn.prepareStatement(query);
        psm.setString(1, nombre);
        ResultSet rs = psm.executeQuery();

        assertTrue(rs.next());
        assertEquals(nombre, rs.getString("Nombre"));
        assertEquals(descripcion, rs.getString("Descripcion"));
        assertEquals(precio, rs.getDouble("Precio"), 0.01);
        assertEquals(stock, rs.getInt("Stock"));
        assertEquals(categoria, rs.getInt("ID_Categoria"));
        assertEquals(imagen, rs.getString("img"));
        assertEquals(sqlDate.getDate(), rs.getDate("fecha_creacion").getDate());
    }

    @Test
    public void testEliminarProductoBD() throws Exception {
        conn = DriverManager.getConnection(url, user, pass);
        fecha = new java.util.Date();

        String insertQuery = "INSERT INTO producto (Nombre, Descripcion, Precio, Stock, ID_Categoria, img, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
            insertPs.setString(1, "Mikel");
            insertPs.setString(2, "Descripción de prueba");
            insertPs.setDouble(3, 100.0);
            insertPs.setInt(4, 10);
            insertPs.setInt(5, 1);
            insertPs.setString(6, "imagen.jpg");
            insertPs.setDate(7, new java.sql.Date(fecha.getTime()));
            insertPs.executeUpdate();
        }

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM producto WHERE Nombre = ?")) {
            ps.setString(1, "Mikel");
            try (ResultSet rs = ps.executeQuery()) {
                assertTrue(rs.next());
            }
        }
        GestionProductos gestionProductos = new GestionProductos();
        gestionProductos.eliminarProductoBD("Mikel");

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM producto WHERE Nombre = ?")) {
            ps.setString(1, "Mikel");
            try (ResultSet rs = ps.executeQuery()) {
                assertFalse(rs.next());
            }
        }
    }

    @Test
    public void testExportarProductos() throws Exception {
        File productosArch = new File("..\\..\\..\\..\\..\\Apache24\\htdocs\\Productos.json");
        Scanner productos = new Scanner(productosArch);
        String i = "";
        while (productos.hasNext()) {
            i += productos.nextLine();
        }
        String productosSeparados[] = i.split("},");
        for (int index = 0; index < productosSeparados.length; index++) {
            assertTrue(productosSeparados[index].contains("id"));
            assertTrue(productosSeparados[index].contains("title"));
            assertTrue(productosSeparados[index].contains("price"));
            assertTrue(productosSeparados[index].contains("description"));
            assertTrue(productosSeparados[index].contains("category"));
            assertTrue(productosSeparados[index].contains("image"));
        }
        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "select count(*) as totalProductos FROM producto";
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        rs.next();
        assertEquals(rs.getInt("totalProductos"), productosSeparados.length);
        
    }

    @Test
    public void testExportarDestacados() throws Exception {
        File destacadosArch = new File("..\\..\\..\\..\\..\\Apache24\\htdocs\\destacados.json");
        Scanner destacados = new Scanner(destacadosArch);
        String i = "";
        while (destacados.hasNext()) {
            i += destacados.nextLine();
        }
        String productosSeparados[] = i.split("},");
        for (int index = 0; index < productosSeparados.length; index++) {
            assertTrue(productosSeparados[index].contains("id"));
            assertTrue(productosSeparados[index].contains("title"));
            assertTrue(productosSeparados[index].contains("price"));
            assertTrue(productosSeparados[index].contains("description"));
            assertTrue(productosSeparados[index].contains("category"));
            assertTrue(productosSeparados[index].contains("image"));
        }

        assertEquals(10, productosSeparados.length);

    }

    @Test
    public void testExportarEstadisticas() throws Exception {
        File estadisticasArch = new File("..\\..\\..\\..\\..\\Apache24\\htdocs\\estadisticas.json");
        Scanner estadisticas = new Scanner(estadisticasArch);
        String i = "";
        while (estadisticas.hasNext()) {
            i += estadisticas.nextLine();
        }
        assertTrue(i.contains("\"gananciasTotales\""));
        assertTrue(i.contains("\"stockBajo\""));
        assertTrue(i.contains("\"usuariosConMasPedidos\""));
        assertTrue(i.contains("\"GananciasPorMes\""));
        assertTrue(i.contains("\"productosNuncaCompr\""));
        assertTrue(i.contains("\"gananciasMasDeQuinientos\""));
        estadisticas.close();
    }

    @Test
    public void testActualizarProducto() throws Exception {
        Connection conn = DriverManager.getConnection(url, user, pass);

        String insertSql = "INSERT INTO producto (Nombre, Descripcion, Precio, Stock, img) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertPsm = conn.prepareStatement(insertSql);
        insertPsm.setString(1, "ProductoPruebaStock");
        insertPsm.setString(2, "Descripción de stock");
        insertPsm.setDouble(3, 15.99);
        insertPsm.setInt(4, 10);
        insertPsm.setString(5, "imagen.jpg");
        insertPsm.executeUpdate();

        GestionProductos gestionProductos = new GestionProductos();
        gestionProductos.ActualizarProductoBD("ProductoPruebaStock", 4, "", 0, 20);

        String selectSql = "SELECT Stock FROM producto WHERE Nombre=?";
        PreparedStatement selectPsm = conn.prepareStatement(selectSql);
        selectPsm.setString(1, "ProductoPruebaStock");
        ResultSet rs = selectPsm.executeQuery();

        assertTrue(rs.next());
        assertEquals(20, rs.getInt("Stock"));

    }

}
