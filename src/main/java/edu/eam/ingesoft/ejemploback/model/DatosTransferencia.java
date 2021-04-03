package edu.eam.ingesoft.ejemploback.model;

public class DatosTransferencia {

    private String ctaOrigen;
    private String ctaDestino;
    private double monto;

    public DatosTransferencia() {
    }

    public DatosTransferencia(String ctaOrigen, String ctaDestino, double monto) {
        this.ctaOrigen = ctaOrigen;
        this.ctaDestino = ctaDestino;
        this.monto = monto;
    }

    public String getCtaOrigen() {
        return ctaOrigen;
    }

    public void setCtaOrigen(String ctaOrigen) {
        this.ctaOrigen = ctaOrigen;
    }

    public String getCtaDestino() {
        return ctaDestino;
    }

    public void setCtaDestino(String ctaDestino) {
        this.ctaDestino = ctaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
