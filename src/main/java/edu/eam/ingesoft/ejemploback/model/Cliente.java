package edu.eam.ingesoft.ejemploback.model;
//1. Lo primero que se debe crear es el modelo
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity //Indica que esta clase está representando una entidad de la base de datos
@Table(name = "customers") //Indica que la entidad que esta clase representa es la tabala customers
public class Cliente implements Serializable { //Se tiene que serializar
    @Id //Indica cual es la llave primaria
    @Column(name = "id") //Indica que esta cédula representa la columna id de la base de datos, esto se llama mapeo
    private String cedula;
//Lo que hace el repositorio es transformar los resultados del "resultset" a una clase cliente
    @Column(name = "name")
    private String nombre;

    @Column(name = "lastname")
    private String apellido;

    @Column(name = "email")
    private String correo;

    public Cliente() { //Constructor vacío
    }

    public Cliente(String id, String name, String lastName, String email) { //Constructor lleno
        this.cedula = id;
        this.nombre = name;
        this.apellido = lastName;
        this.correo = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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
}
