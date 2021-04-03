package edu.eam.ingesoft.ejemploback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaccion implements Serializable {

    @Id
    @Column(name = "number")
    private String numero;

    @Column(name = "acountid")
    private String idCuenta;

    @Column(name ="type")
    private String tipo;

    @Column(name ="amount")
    private double monto;

    @Column(name = "date")
    private Date fecha;

    public Transaccion() {
        fecha = new Date();
    } //Es hoy, ahora

    public Transaccion(String numero, String idCuenta, String tipo, double monto, Date fecha) {
        this.numero = numero;
        this.idCuenta = idCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

