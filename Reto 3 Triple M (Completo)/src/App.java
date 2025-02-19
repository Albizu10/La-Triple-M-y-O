import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        GestionProductos gestion = new GestionProductos();
        gestion.cargarProductos();
        Scanner sc = new Scanner(System.in);
       bucle: while (true) {
         System.out.println("-----------------------");
        System.out.println("Bienvenido a USURBILTEX");
        System.out.println("-----------------------");
        System.out.println("1-Agregar nuevos productos");
        System.out.println("2-Carga de productos desde fichero.csv");
        System.out.println("3-Actualizar producto");
        System.out.println("4-Eliminar Producto");
        System.out.println("5-Exportar informacion");
        System.out.println("6-Lista productos");
        System.out.println("7-Buscar productos");
        System.out.println("0-Salir del programa");
        System.out.println("-----------------------");
        int Opcion = sc.nextInt();
        System.out.println("-----------------------");


        switch (Opcion) {
            case 1:
                gestion.agregarProducto();
                break;
            case 2:
                gestion.cargarfichero();
                break;

            case 3:
                gestion.ActualizarProducto();
                break;

            case 4:
                gestion.eliminarProducto();
                break;

            case 5:
                gestion.exportar();
                
                break;

            case 6:
                gestion.listaProductos();
                break;

            case 7:
                gestion.buscarProductos();
                break;

            case 0:
                System.out.println("Saliendo de la aplicacion...");
                break bucle;

            default:
                System.out.println("Numero incorrecto");
                break;
        }
       }

    }

}
