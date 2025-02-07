import java.util.Date;
public class Producto extends Categoria{

    private  String Nombre;
    private String Descripcion;
    private Double Precio;
    private int Stock;
    private Date Fecha;
    private int ID;
    private String imagen;
    private Categoria categoria;
    


    public Producto (String Nombre,String Descripcion,Double Precio,int Stock,Date Fecha,int ID,int Cod_Categoria,String nombreCategoria,String categoria,String imagen){
        super(Cod_Categoria,nombreCategoria);
        this.Nombre=Nombre;
        this.Descripcion=Descripcion;    
        this.Precio=Precio;
        this.Stock=Stock;
        this.Fecha=Fecha;
        this.ID=ID;
        this.imagen=imagen;

    }

    
    


    public String getNombre() {
        return Nombre;
    }


    public void setNombre(String nombre) {
        Nombre = nombre;
    }


    public String getDescripcion() {
        return Descripcion;
    }


    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }


    public double getPrecio() {
        return Precio;
    }


    public void setPrecio(double precio) {
        Precio = precio;
    }


    public int getStock() {
        return Stock;
    }


    public void setStock(int stock) {
        Stock = stock;
    }


    public Date getFecha() {
        return Fecha;
    }


    public void setFecha(Date fecha) {
        Fecha = fecha;
    }


    public int getID() {
        return ID;
    }


    public void setID(int iD) {
        ID = iD;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }





    public static void add(String eliminar) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
    
    
    
    
}