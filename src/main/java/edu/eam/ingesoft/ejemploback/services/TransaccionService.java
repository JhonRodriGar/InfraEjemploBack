package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import edu.eam.ingesoft.ejemploback.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Transaccion> listaTransaccionesCuenta(String cuenta) { //Crea método que retorna una lista de transacciones de acuerdo a la cuenta

        Cuenta cuentaBD = cuentaRepository.findById(cuenta).orElse(null);

        if (cuentaBD == null) {
            throw new RuntimeException("No existe la cuenta");
        }

        List<Transaccion> transaccionesCuenta = transaccionRepository.buscarTransaccionesCuentas(cuenta); //Le manda la cédula del cliente para consultar sus cuentas

        if (transaccionesCuenta.size() == 0) { //No ha realizado transacciones
            throw new RuntimeException("Aún no ha realizado transacciones");
        }

        return transaccionRepository.buscarTransaccionesCuentas(cuenta); //Le regunta la información al repositorio, lo busca por cédula
    }

    public  Transaccion crearTransaccion(Transaccion transaccion) {

        transaccionRepository.save(transaccion);

        return transaccion; //Retorna la cuenta
    }

}
