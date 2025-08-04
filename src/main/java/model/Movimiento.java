package model;

import java.time.LocalDate;

public class Movimiento {
    private int id;
    private String tipo; // "Ingreso" o "Egreso"
    private double cantidad;
    private Categoria categoria;
    private String descripcion;
    private LocalDate fecha;

    public Movimiento(int id, String tipo, double cantidad, Categoria categoria, String descripcion, LocalDate fecha) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Movimiento(String tipo, double cantidad, Categoria categoria, String descripcion, LocalDate fecha) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
