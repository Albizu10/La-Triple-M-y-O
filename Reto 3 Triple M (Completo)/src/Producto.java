import java.util.Date;

public class Producto {

    private String nombre;
    private String descripcion;
    private Double precio;
    private int stock;
    private Date fecha;
    private int id;
    private String imagen;
    private int categoria;  

    public Producto(String nombre, String descripcion, Double precio, int stock, Date fecha, String imagen, int categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.fecha = fecha;
        this.imagen = imagen;
        this.categoria = categoria;  
    }

    
    public Producto(String nombre, String descripcion, Double precio, int stock, Date fecha, int id, String imagen, int categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.fecha = fecha;
        this.id = id;
        this.imagen = imagen;
        this.categoria = categoria;  
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    
}
