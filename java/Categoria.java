public class Categoria {
    
    private int Cod_Categoria;
    private String nombreCategoria;


    public Categoria(int Cod_Categoria,String nombreCategoria){
        this.Cod_Categoria=Cod_Categoria;
        this.nombreCategoria=nombreCategoria;
    }


    public int getCod_Categoria() {
        return Cod_Categoria;
    }


    public void setCod_Categoria(int cod_Categoria) {
        Cod_Categoria = cod_Categoria;
    }


    public String getNombreCategoria() {
        return nombreCategoria;
    }


    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }


    


 
    

    
}
