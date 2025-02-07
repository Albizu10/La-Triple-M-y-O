import java.util.*;
import java.io.*;

public class GestionProductos {

    private ArrayList <Producto> productos=new ArrayList<>();
    
    


    public   void AgregarProducto() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del Producto:");
        String nombre=sc.nextLine();
        System.out.println("Descripcion del Producto");
        String desc=sc.nextLine();
        System.out.println("Precio");
        Double precio=sc.nextDouble();
        System.out.println("Stock disponible");
        int stock=sc.nextInt();
        Date fecha=new Date();
        System.out.println("Categoria");
        String categoria=sc.nextLine();
        System.out.println("Imagen del producto");
        String img=sc.nextLine();
        Producto producto = new Producto(nombre, desc, precio, stock, fecha, stock, stock, categoria, categoria, img);
        productos.add(producto);
        System.out.println("Producto subido con exito");

    }


public  void cargarfichero() throws Exception {
    File productoscsv = new File("productos.csv");
    BufferedReader rest = new BufferedReader(new FileReader(productoscsv));
    BufferedWriter writer = new BufferedWriter(new FileWriter(productoscsv, true));
    
}

public  void ActulizarProducto() throws Exception {
    Scanner sc = new Scanner(System.in);
    File productoscsv = new File("productos.csv");
    BufferedReader rest = new BufferedReader(new FileReader(productoscsv));
    BufferedWriter writer = new BufferedWriter(new FileWriter(productoscsv, true));
    System.out.println("Que producto deseas actualizar?");
    String actualizar = sc.nextLine();
    System.out.println("Que desea actualizar?");
    System.out.println("1-Nombre del Producto:");
    System.out.println("2-Descripcion del Producto");
    System.out.println("3-Precio");
    System.out.println("4-Stock disponible");
    System.out.println("5-Imagen");
    int opcion = sc.nextInt();
    String linea;
    int i=0;
    for (int index = 0; index < productos.size(); index++) {
           if(productos.get(index).getNombre().equals(actualizar)){
            i=index;
        }
    }
    while ((linea = rest.readLine()) != null) {
        String[]datos = linea.split(",");
        if (datos[0].equals(actualizar)) {
            switch (opcion) {
                case 1:
                    System.out.println(datos[0]);
                    datos[0] = sc.nextLine();
                    productos.get(i).setNombre(datos[0]);
                    break;

                case 2:
                    System.out.println(datos[1]);
                    datos[1] = sc.nextLine();
                    productos.get(i).setNombre(datos[1]);
                    break;

                case 3:
                    System.out.println(datos[2]);
                    datos[2] = sc.nextLine();
                    productos.get(i).setNombre(datos[2]);
                    break;

                case 4:
                    System.out.println(datos[3]);
                    datos[3] = sc.nextLine();
                    productos.get(i).setNombre(datos[3]);
                    break;

                case 5:
                    System.out.println(datos[4]);
                    datos[4] = sc.nextLine();
                    productos.get(i).setNombre(datos[4]);
                    break;

                case 6:
                    System.out.println(datos[5]);
                    datos[5] = sc.nextLine();
                    productos.get(i).setNombre(datos[5]);
                    break;

                default:
                    break;
                    
            }
        }
    }

}

public void eliminarProducto() throws Exception {
    Scanner sc = new Scanner(System.in);
    File productoscsv= new File("productos.csv");
    BufferedReader rest = new BufferedReader(new FileReader(productoscsv));
    BufferedWriter writer = new BufferedWriter(new FileWriter(productoscsv, true));
    System.out.println("Que producto desea eliminar?");
    String eliminar = sc.nextLine();
    String linea;
    int i=0;
    for (int index = 0; index < productos.size(); index++) {
           if(productos.get(index).getNombre().equals(eliminar)){
            productos.remove(index);
        }
    }
    while ((linea = rest.readLine()) != null) {
        String[]datos = linea.split(",");
        if (!datos[0].equals(eliminar)){
            Producto.add(eliminar);
        }
}}

public void exportar(){

}
public void listaProductos() throws Exception {
    File productoscsv= new File("productos.csv");
    BufferedReader rest = new BufferedReader(new FileReader(productoscsv));
    BufferedWriter writer = new BufferedWriter(new FileWriter(productoscsv, true));
    String linea;
    while ((linea = rest.readLine()) != null) {
        System.out.println(linea);
}
}}s