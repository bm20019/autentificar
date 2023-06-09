package com.mabmw.autentificar.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "public", catalog = "dbemprendedora")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iduser", nullable = false)
    private int iduser;
    @Basic
    @Column(name = "nombre", nullable = false, length = -1)
    private String nombre;
    @Basic
    @Column(name = "apellido", nullable = false, length = -1)
    private String apellido;
    @Basic
    @Column(name = "pass", nullable = false, length = -1)
    private String pass;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return iduser == that.iduser && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(pass, that.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, nombre, apellido, pass);
    }
}
