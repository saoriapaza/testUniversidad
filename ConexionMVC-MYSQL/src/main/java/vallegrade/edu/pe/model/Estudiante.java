package vallegrade.edu.pe.model;

public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private boolean estado;

    // Constructor vacío
    public Estudiante() {
    }

    // Constructor con todos los campos (excepto ID)
    public Estudiante(String nombre, String apellido, String correo, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", estado=" + (estado ? "Activo" : "Inactivo") +
                '}';
    }
}