public class Usuario {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String DNI;
    private int Cod_usuario;
    private int telf;
    private String mail;

    public Usuario (String nombre,String apellido1,String apellido2,String DNI,int Cod_usuario,int telf, String mail){
        this.nombre=nombre;
        this.apellido1=apellido1;
        this.apellido2=apellido2;
        this.DNI=DNI;
        this.Cod_usuario=Cod_usuario;
        this.telf=telf;
        this.mail=mail;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        DNI = DNI;
    }

    public int getCod_usuario() {
        return Cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        Cod_usuario = cod_usuario;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    } 
    
}
