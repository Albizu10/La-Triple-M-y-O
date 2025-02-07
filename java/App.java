import java.util.*;
import java.util.Date;
import java.io.*;
import java.sql.*;


public class App {

    
    public static void main(String[] args) throws Exception {
        GestionProductos gestion= new GestionProductos();
         Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------");
        System.out.println("Bienvenido a USURBILTEX");
        System.out.println("-----------------------");
        System.out.println("1-Agregar nuevos productos");
        System.out.println("2-Carga de productos desde fichero.csv");
        System.out.println("3-Actualizar producto");
        System.out.println("4-Eliminar Producto");
        System.out.println("5-Exportar informacion");
        System.out.println("6-Lista de Producto");
        System.out.println("7-Buscar productos");
        System.out.println("0-Salir del programa");
        System.out.println("-----------------------");
        int Opcion=sc.nextInt();


        switch (Opcion) {
            case 1:
                gestion.AgregarProducto();
                break;
            case 2:
                gestion.cargarfichero();
                break;

            case 3:
                gestion.ActulizarProducto();
                break;

            case 4:

                break;

            case 5:

                break;

            case 6:

                break;

            case 7:

                break;

            case 0:

                break;
            
            default:
                System.out.println("Numero incorrecto");
                break;
        }

    }

}

