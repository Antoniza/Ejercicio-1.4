package com.example.ejercicio14;

public class Contactos_Construc {
    private Integer id;
    private String nombres;
    private String descripcion;
    private String imagen;

    public Contactos_Construc() {
        //Constructor Vacio
    }

    public Contactos_Construc(Integer id, String nombres,String descripcion, String imagen) {
        this.id = id;
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setCorreo(String imagen) {
        this.imagen = imagen;
    }

}
