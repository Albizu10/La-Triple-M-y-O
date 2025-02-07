public class Pedido extends Usuario{

    private int Cod_Pedido;
    private int Cantidad;
    private double Precio;

    public Pedido(String nombre,String apellido1,String apellido2,String DNI,int Cod_usuario,int telf, String mail,int Cod_Pedido,int Cantidad,double Precio){
           super(nombre, apellido1, apellido2, DNI, Cod_usuario, telf, mail);
           this.Cod_Pedido=Cod_Pedido;
           this.Cantidad=Cantidad;
           this.Precio=Precio;
    }

    public int getCod_Pedido() {
        return Cod_Pedido;
    }

    public void setCod_Pedido(int cod_Pedido) {
        Cod_Pedido = cod_Pedido;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }
    
    
}
