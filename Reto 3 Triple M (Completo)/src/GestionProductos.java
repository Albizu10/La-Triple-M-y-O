    import java.util.*;
import java.io.*;
import java.sql.*;
import java.util.Date;

public class GestionProductos {

    private String url = "jdbc:mysql://localhost:3306/usurbiltex";
    private String user = "root";
    private String pass = "password";

    private ArrayList<Producto> productos = new ArrayList<>();

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void cargarProductos() throws Exception {// Cargamos los productos pasamos todos los productos de la BBDD al ArrayList
        Connection conn = DriverManager.getConnection(url, user, pass);// Conexion a la BBDD
        String sql = "select * FROM producto";// Seleccion de todas las columnas de los productos
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        while (rs.next()) {// Recorre linea a linea cada producto y los mete en el ArrayList

            productos.add(new Producto(rs.getString("Nombre"), rs.getString("Descripcion"), rs.getDouble("Precio"),
                    rs.getInt("Stock"), rs.getDate("fecha_creacion"), rs.getInt("ID"), rs.getString("img"),
                    rs.getInt("ID_Categoria")));
        }
        rs.close();
        psm.close();
        conn.close();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void agregarProducto() throws Exception {// Metodo para agragar producto desde el programa a la BBDD
        Scanner sc = new Scanner(System.in);
        // Pedidos todos los parametros del producto
        System.out.println("Nombre del Producto:");
        String nombre = sc.nextLine();
        System.out.println("Descripcion del Producto");
        String descripcion = sc.nextLine();
        System.out.println("Precio");
        double precio = sc.nextDouble();
        System.out.println("Stock disponible");
        int stock = sc.nextInt();
        Date fecha = new Date();
        int categoria;
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        while (true) {// Para que no tengamos errores con los numeros lo metemos en el while si mete
                      // un numero erroneo
            System.out.println("Categoria");
            System.out.println("1.Pantalones\n2.Sudaderas\n3.Camisetas\n4.Camisas\n5.Chaquetas\n");
            categoria = sc.nextInt();
            if (categoria < 1 || categoria > 5) {
                System.out.println("Numero incorrecto");
                continue;
            }
            break;

        }

        sc.nextLine();
        System.out.println("Imagen del producto");
        String imagen = sc.nextLine();
        AgregarProductoBD(nombre, descripcion, precio, stock, fecha, imagen, categoria, sqlDate);

    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void AgregarProductoBD(String nombre, String descripcion, Double precio, int stock, Date fecha,
            String imagen, int categoria, java.sql.Date sqlDate) throws Exception {// Tras recoger toda la informacion lo ingresamos a la BBDD

        Producto nuevoProducto = new Producto(nombre, descripcion, precio, stock, fecha, imagen, categoria);
        productos.add(nuevoProducto);// Lo metemos tambien en el ArrayList

        System.out.println("Producto subido con exito");
        Connection conn = DriverManager.getConnection(url, user, pass);
        int id = (productos.size() - 1);
        String actID = ("ALTER TABLE producto AUTO_INCREMENT = ") + id;// Con esta linea conseguimos que el ID siempre tenga el ID en orden
        PreparedStatement psm = conn.prepareStatement(actID);
        psm.executeUpdate(actID);
        String sql = "INSERT INTO producto(Nombre,Descripcion,Precio,Stock,ID_Categoria,img,fecha_creacion) values (?,?,?,?,?,?,?)";// Inserccion de los productos a la BBDD
        psm = conn.prepareStatement(sql);
        psm.setString(1, nombre);
        psm.setString(2, descripcion);
        psm.setDouble(3, precio);
        psm.setInt(4, stock);
        psm.setInt(5, categoria);
        psm.setString(6, imagen);
        psm.setDate(7, sqlDate);
        psm.executeUpdate();
        psm.close();
        conn.close();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void cargarfichero() throws Exception {// Con esta funcion cargamos lo que tenemos en el csv en la BBDD
        Connection conn = DriverManager.getConnection(url, user, pass);
        File prod = new File("src\\productos.csv");
        BufferedReader leer = new BufferedReader(new FileReader(prod));
        String sql = "INSERT INTO producto (Nombre, Descripcion, Precio, Stock, ID_Categoria, img, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?)";// Seleccionamos en que columnas queremos meter la informacion
        PreparedStatement psm = conn.prepareStatement(sql);

        String linea;
        leer.readLine();
        while ((linea = leer.readLine()) != null) { // Metemos cada dato en su columna correspondiente en la BBDD
            String[] Datos = linea.split(",");
            psm.setString(1, Datos[0]);
            psm.setString(2, Datos[1]);
            psm.setDouble(3, Double.parseDouble(Datos[2]));
            psm.setInt(4, Integer.parseInt(Datos[3]));
            psm.setString(5, Datos[4]);
            psm.setString(6, Datos[5]);
            psm.setDate(7, java.sql.Date.valueOf(Datos[6]));
            psm.addBatch();
        }
        psm.executeBatch();
        psm.close();
        leer.close();
        conn.close();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void ActualizarProducto() throws Exception {// String actualizar, int opcion
        Connection conn = DriverManager.getConnection(url, user, pass);
        Scanner sc = new Scanner(System.in);
        System.out.println("Que producto deseas actualizar?");//Esperamos a que el usuario ingrese el nombre del producto
        String actualizar = sc.nextLine();
        String sql="select * from producto where Nombre=\""+actualizar+"\"";//Hacemos la consulta
        PreparedStatement psa = conn.prepareStatement(sql);
        ResultSet rs = psa.executeQuery();
        String dato = "";
        double Precio = 0;
        int Stock = 0;
        bucle: while (true) {//Entramos en el bucle para si el usuario quiere cambiar mas de una cosa

            System.out.println("Que desea actualizar?");
            System.out.println("1-Nombre del Producto");
            System.out.println("2-Descripcion del Producto");
            System.out.println("3-Precio");
            System.out.println("4-Stock disponible");
            System.out.println("5-Imagen");
            System.out.println("0-Salir");
            System.out.println("-----------------------");
            int opcion = sc.nextInt();
            sc.nextLine();
            System.out.println("-----------------------");

            
            
                    
            switch (opcion) {
                case 0:

                    break bucle;
                case 1://Actualizacion del nombre
                    System.out.println("Nombre del Producto");
                    while (rs.next()) {
                        System.out.println(rs.getString("Nombre"));
                    }
                    System.out.println("Nombre que desea añadir");
                    dato = sc.nextLine();
                    break;
                case 2://Actualizacion de la descripcion del producto
                    System.out.println("Descripcion del Producto incial");
                    while (rs.next()) {
                        System.out.println(rs.getString("Descripcion"));
                    }
                    System.out.println("Descripcion que desea añadir");
                    dato = sc.nextLine();
                    break;
                case 3://Actualizando en precio
                    System.out.println("Precio inicial");
                    while (rs.next()) {
                        System.out.println(rs.getBoolean("Precio"));
                    }
                    System.out.println("Precio que desea añadir");
                    Precio = sc.nextDouble();
                    sc.nextLine();
                    break;
                case 4://Actualizando el Stock del producto
                    System.out.println("Stock inicial");
                    while (rs.next()) {
                        System.out.println(rs.getInt("Stock"));
                    }
                    System.out.println("Stock que desea añadir");
                    Stock = sc.nextInt();
                    sc.nextLine();
                    break;
                case 5://Actualizacion del link de la imagen
                    System.out.println("Imagen Inicial");
                    while (rs.next()) {
                        System.out.println(rs.getString("img"));
                    }
                    System.out.println("Imagen que desea añadir");
                    dato = sc.nextLine();
                    break;

                default:
                    break;
            }

            ActualizarProductoBD(actualizar, opcion, dato, Precio, Stock);

        }
        rs.close();
        psa.close();
        conn.close();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void ActualizarProductoBD(String actualizar, int opcion, String dato, double Precio, int Stock)//Toda la informacion metida anteriomente la pasamos todo a la BBDD
            throws Exception {
        Connection conn = DriverManager.getConnection(url, user, pass);//Conexion a la BBDD
        String sql = "";
        PreparedStatement psm = null;
        switch (opcion) {
            case 1:
                sql = "UPDATE producto SET Nombre=? where Nombre=?";
                psm = conn.prepareStatement(sql);
                psm.setString(1, dato);
                psm.setString(2, actualizar);
                break;

            case 2:
                sql = "UPDATE producto SET Descripcion=? where Nombre=?";
                psm = conn.prepareStatement(sql);
                psm.setString(1, dato);
                psm.setString(2, actualizar);
                break;

            case 3:
                sql = "UPDATE producto SET Precio=? where Nombre=?";
                psm = conn.prepareStatement(sql);
                psm.setDouble(1, Precio);
                psm.setString(2, actualizar);
                break;

            case 4:
                sql = "UPDATE producto SET Stock=? where Nombre=?";
                psm = conn.prepareStatement(sql);
                psm.setInt(1, Stock);
                psm.setString(2, actualizar);
                break;

            case 5:
                sql = "UPDATE producto SET img=? where Nombre=?";
                psm = conn.prepareStatement(sql);
                psm.setString(1, dato);
                psm.setString(2, actualizar);
                break;

            default:
                break;

        }
        psm.executeUpdate();//Ejecucion de las consultas
        System.out.println("Producto actualizado");
        conn.close();
        psm.close();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void eliminarProducto() throws Exception {// Con este metodo eliminamos un producto de la BBDD
        Scanner sc = new Scanner(System.in);
        System.out.println("Que producto desea eliminar?");// Le pedimos el nombre del producto que desea eliminar
        String eliminar = sc.nextLine();
        eliminarProductoBD(eliminar);
    }
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void eliminarProductoBD(String eliminar) throws Exception {

        Connection conn = DriverManager.getConnection(url, user, pass);

        String sql = "DELETE FROM producto WHERE nombre = ?";// Le decimos a la BBDD que desamos eliminar un producto
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setString(1,  eliminar );
        psm.executeUpdate();// Ejecuta el borrado
        boolean elim=true;

        for (int index = 0; index < productos.size(); index++) { // Aqui le quitamos del ArrayList
            if (productos.get(index).getNombre().equals(eliminar)) {
                productos.remove(index);
                System.out.println("Producto Eliminado");
                
            } 
        } 
        
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void exportar() throws Exception { // Exportamos toda la informacion de la BBDD en un formato JSON

        File arch = new File("..\\..\\..\\..\\..\\Apache24\\htdocs\\Productos.json");// Seleccionamos el archivo en el que deseamos ingresar esa informacion

        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "select * FROM producto";// Metemos la consulta a la BBDD
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery(sql);
        FileWriter json = new FileWriter(arch);
        String product = "{\n\"products\":[ ";
        while (rs.next()) { // Extraemos la informacion en el formato JSON
            product += "{\"id\":\"" + rs.getString("ID") + "\",\n"
                    + "\"title\":\"" + rs.getString("Nombre") + "\",\n"
                    + "\"price\":" + rs.getDouble("Precio") + ",\n"
                    + "\"description\":\"" + rs.getString("Descripcion") + "\",\n"
                    + "\"category\":\"" + rs.getInt("ID_Categoria") + "\",\n"
                    + "\"image\":\"" + rs.getString("img") + "\"\n"
                    + "\n},\n";
        }
        product = product.substring(0, product.length() - 2);// Quitamos la ultima coma para que no salte ningun error en la lectura del JSON
        product += "]}";
        json.write(product);
        json.close();
        rs.close();
        psm.close();
        conn.close();
        String estadisticas = "{\n"; // Extraemos todas la estidisticas para escribirlas en el JSON
        estadisticas += totalGanancias()
                + "\n" + stockBajo()
                + "\n" + clientMasPed()
                + "\n" + gananciasPorMes()
                + "\n" + productosNuncaCompr()
                + "\n" + ganaciasMasDeQuinientos() + "\n}";
        File est = new File("..\\..\\..\\..\\..\\Apache24\\htdocs\\estadisticas.json");// Ingresamos las estidisticas en su archivo correspondiente
        FileWriter jsonEst = new FileWriter(est);
        jsonEst.write(estadisticas);
        jsonEst.close();
        destacados();

    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void destacados()throws Exception{
        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "select * from producto as p inner join detalle_pedido as d on p.ID = d.ID_producto inner join pedido as pe on d.ID_pedido = pe.ID order by d.cant desc limit 10;";// Hacemos la consulta para sacar las cantidades
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        File arch = new File("..\\..\\..\\..\\..\\Apache24\\htdocs\\destacados.json");
        FileWriter json= new FileWriter(arch);
         String i = "{\n\"products\":[  ";
        while (rs.next()) {// Lo metemos en el formato JSON
            i += "{\"id\":\"" + rs.getString("ID") + "\",\n"
                    + "\"title\":\"" + rs.getString("Nombre") + "\",\n"
                    + "\"price\":" + rs.getDouble("Precio") + ",\n"
                    + "\"description\":\"" + rs.getString("Descripcion") + "\",\n"
                    + "\"category\":\"" + rs.getInt("ID_Categoria") + "\",\n"
                    + "\"image\":\"" + rs.getString("img") + "\"\n"
                    + "\n},\n";
        }
        i = i.substring(0, i.length() - 2);// quitando la ultima coma

        i += "]}";
        json.write(i);
        json.close();
        conn.close();
        psm.close();
        rs.close();
    }
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    private String totalGanancias() throws Exception {// Sacamos toda las ganancias atraves de este metodo
        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "select sum(Precio_Total) as TotalGanancias from Pedido;";// Hacemos la consulta para sacar las cantidades
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        String i = "";
        while (rs.next()) {// Lo metemos en el formato JSON
            i = "\"gananciasTotales\": " + rs.getDouble("TotalGanancias") + ",";
        }

        return i;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private String stockBajo() throws Exception {// Atraves de este metodo sacamos los producto de stock mas bajo que son seran los que tinen 5 o menos
        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "select* from producto where Stock < 5;";// Hacemos la consulta necesaria para sacar los resultados necesarios
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        String i = "\n\"stockBajo\":[  ";
        while (rs.next()) {// Lo metemos en el formato JSON
            i += "{\"id\":\"" + rs.getString("ID") + "\",\n"
                    + "\"title\":\"" + rs.getString("Nombre") + "\",\n"
                    + "\"price\":" + rs.getDouble("Precio") + ",\n"
                    + "\"description\":\"" + rs.getString("Descripcion") + "\",\n"
                    + "\"category\":\"" + rs.getInt("ID_Categoria") + "\",\n"
                    + "\"image\":\"" + rs.getString("img") + "\"\n"
                    + "\n},\n";
        }
        i = i.substring(0, i.length() - 2);// quitando la ultima coma

        i += "],";
        return i;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private String clientMasPed() throws Exception {// Con este metodo sacamos los cliente con mas pedidos
        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "SELECT u.Nombre_usuario, u.Nombre, sum(d.cant) as ProductosComprados FROM usuario AS u INNER JOIN pedido AS p ON u.ID = p.ID_usuario INNER JOIN detalle_pedido AS d ON p.ID = d.ID_pedido GROUP BY u.Nombre_usuario, u.Nombre order by ProductosComprados  desc LIMIT 10;";// Hacemos la consulta necesaria
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery(sql);
        String i = "\n\"usuariosConMasPedidos\":[  ";
        while (rs.next()) { // Metemos todo en el formato JSON
            i += "{\"nombreUsuario\":\"" + rs.getString("Nombre_usuario") + "\",\n"
                    + "\"nombrePersona\":\"" + rs.getString("Nombre") + "\",\n"
                    + "\"cantidad\":" + rs.getInt("ProductosComprados") + "\n},\n";
        }
        i = i.substring(0, i.length() - 2);// quitando la ultima coma
        i += "],";
        return i;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private String gananciasPorMes() throws Exception {// Sacamos las ganancias por mes atraves de este Metodo
        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "SELECT DATE_FORMAT(fecha, '%Y-%m') AS Mes, SUM(Precio_Total) AS GananciasPorMes FROM pedido GROUP BY Mes ORDER BY Mes;";// Hacemos ls consulta
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery(sql);
        String i = "\n\"GananciasPorMes\":[  ";
        while (rs.next()) {// Lo pasamos al formato JSON
            i += "{\"mes\":\"" + rs.getString("Mes") + "\",\n"
                    + "\"ganancia\":" + rs.getDouble("GananciasPorMes") + "\n"
                    + "\n},\n";
        }
        i = i.substring(0, i.length() - 2);// quitando la ultima coma
        i += "],";
        return i;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private String productosNuncaCompr() throws Exception {// Sacamos la informacion de los productos que nunca han sido
                                                           // comprados
        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "select* from producto as pr left join detalle_pedido as d on pr.ID = d.ID_producto where d.ID_pedido is null;";// Consulta para el SQL
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery(sql);
        String i = "\n\"productosNuncaCompr\":[  ";
        while (rs.next()) {// Pasamos todo al formato JSON
            i += "{\"id\":\"" + rs.getString("ID") + "\",\n"
                    + "\"title\":\"" + rs.getString("Nombre") + "\",\n"
                    + "\"price\":" + rs.getDouble("Precio") + ",\n"
                    + "\"description\":\"" + rs.getString("Descripcion") + "\",\n"
                    + "\"category\":\"" + rs.getInt("ID_Categoria") + "\",\n"
                    + "\"image\":\"" + rs.getString("img") + "\"\n"
                    + "\n},\n";
        }
        i = i.substring(0, i.length() - 2);// quitando la ultima coma
        i += "],";
        return i;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private String ganaciasMasDeQuinientos() throws Exception {// Sacamos las ganacias de los producto que generan mas de 500€
        Connection conn = DriverManager.getConnection(url, user, pass);
        String sql = "select* from producto as p inner join detalle_pedido as d on P.ID= d.ID_producto inner join pedido as pe on d.ID_pedido = pe.ID where pe.Precio_Total> 500;";// Consulta SQL
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery(sql);
        String i = "\n\"gananciasMasDeQuinientos\":[  ";
        while (rs.next()) {// Metemos todos en formato JSON
            i += "{\"id\":\"" + rs.getString("ID") + "\",\n"
                    + "\"title\":\"" + rs.getString("Nombre") + "\",\n"
                    + "\"price\":" + rs.getDouble("Precio") + ",\n"
                    + "\"description\":\"" + rs.getString("Descripcion") + "\",\n"
                    + "\"category\":\"" + rs.getInt("ID_Categoria") + "\",\n"
                    + "\"image\":\"" + rs.getString("img") + "\"\n"
                    + "\n},\n";
        }
        i = i.substring(0, i.length() - 2);// quitando la ultima coma
        i += "]";
        return i;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void listaProductos() throws Exception {// Sacamos un lista de los producto de dos maneras
        Connection conn = DriverManager.getConnection(url, user, pass);
        Scanner sc = new Scanner(System.in);

        System.out.println("Elija como quieren que se muestre los productos");// El usuario elige como sacar la lista 1-Para que salgan un tras otro o 2-Categoria en Categoria
        System.out.println("1-Todos los productos a la vez");
        System.out.println("2-Dividido por categorias");
        int eleccion = -1;

        while (eleccion != 1 && eleccion != 2) {// Le metos un bucle para que solo ingrese los numeros deseados para que  no haya ningun error
            if (sc.hasNextInt()) {
                eleccion = sc.nextInt();
                if (eleccion != 1 && eleccion != 2) {
                    System.out.println("Opción no válida, por favor ingrese 1 o 2.");
                }
            } else {
                System.out.println("Entrada inválida. Debe ingresar un número.");
                sc.next();
            }
        }

        switch (eleccion) {
            case 1:// Saca todos los producto en orden del ID
                String sql = "SELECT * FROM producto ORDER BY ID";
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String nombre = rs.getString("Nombre");
                    String descripcion = rs.getString("Descripcion");
                    double precio = rs.getDouble("Precio");
                    int stock = rs.getInt("Stock");
                    int id_categoria = rs.getInt("ID_Categoria");
                    String img = rs.getString("img");
                    Date fecha = rs.getDate("fecha_creacion");
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Descripcion: " + descripcion);
                    System.out.println("Precio: " + precio);
                    System.out.println("Stock " + stock);
                    System.out.println("Id_Categoria " + id_categoria);
                    System.out.println("Link Imangen: " + img);
                    System.out.println("Fecha de Creacion: " + fecha);
                    System.out.println("ID " + id);
                    System.out.println("-------------------------------------------------------------");
                }
                break;

            case 2:// Sacamos todos los productos atraves de su categoria
                String sql2 = "SELECT p.ID, p.Nombre, p.Descripcion, p.Precio, p.Stock, p.Img, p.fecha_creacion, p.ID_Categoria, c.ID AS idCategoria, c.Nombre AS Categoria FROM producto p INNER JOIN categoria c ON p.ID_Categoria = c.ID ORDER BY p.ID_Categoria ASC, p.ID ASC";
                PreparedStatement psm = conn.prepareStatement(sql2);
                ResultSet rs2 = psm.executeQuery();

                String categoriaAnterior = null;

                while (rs2.next()) {
                    String categoria = rs2.getString("Categoria");

                    if (categoriaAnterior == null || !categoriaAnterior.equals(categoria)) {// Hacemos una comprobacion  para que todo salga en  orden de las categorias
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Categoría: " + categoria);
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Nombre/Descripcion/Precio/Stock/Link/Fecha de Creacion/ID");
                        System.out.println("-------------------------------------------------------------");
                        categoriaAnterior = categoria;
                    }

                    int ID = rs2.getInt("ID");
                    String nombre = rs2.getString("Nombre");
                    String descripcion = rs2.getString("Descripcion");
                    double precio = rs2.getDouble("Precio");
                    int stock = rs2.getInt("Stock");
                    String img = rs2.getString("Img");
                    Date fecha = rs2.getDate("fecha_creacion");

                    System.out.println(nombre + "/" + descripcion + "/" + precio + "/" + stock + "/" + img + "/" + fecha
                            + "/" + ID);// Imprimimos todos los productos
                }

                rs2.close();
                psm.close();

        }
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void buscarProductos() throws Exception {// Hace una busqueda en la BBDD del producto que deseas sacar
        Scanner sc = new Scanner(System.in);
        Connection conn = DriverManager.getConnection(url, user, pass);
        System.out.println("Inserte el nombre del producto que desea buscar");// El usuario ingresa el nombre del  producto
        String buscar = sc.nextLine();
        String sql = "SELECT * FROM producto where nombre like ?";// Hacemos la consulta
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, "%" + buscar + "%");
        ResultSet rs = stm.executeQuery();// Ejectamos la busqueda
        boolean encontrado = false;
        while (rs.next()) {// Sacamos toda la infromacion en pantalla
            int id = rs.getInt("ID");
            String nombre = rs.getString("Nombre");
            String desc = rs.getString("Descripcion");
            double precio = rs.getDouble("Precio");
            int stock = rs.getInt("Stock");
            int id_categoria = rs.getInt("ID_Categoria");
            String img = rs.getString("img");
            String fecha = rs.getString("fecha_creacion");

            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Descripcion: " + desc);
            System.out.println("Precio: " + precio);
            System.out.println("Stock: " + stock);
            System.out.println("ID_categoria: " + id_categoria);
            System.out.println("Imagen: " + img);
            System.out.println("Fecha de Creacion: " + fecha);
            encontrado = true;
        }
        if (!encontrado) {// Hacemos el if si el producto deseado no si no fue encontrado
            System.out.println("No se encontraron productos que coincidan con el nombre buscado.");
        }

    }
}
