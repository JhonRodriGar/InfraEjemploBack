package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.DatosTransferencia;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/accounts")
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta){

        return cuentaService.crearCuenta(cuenta);
    }


    @GetMapping("/customers/{cedula}/accounts")
    public List<Cuenta> listarCuentasCliente(@PathVariable String cedula) {

        return cuentaService.listarCuentasCliente(cedula);
    }


    @PutMapping("/accounts/consignar")
    public Cuenta consignar(@RequestBody Cuenta cuenta) {

        return cuentaService.consignarMonto(cuenta);
    }


    @PutMapping("/accounts/retirar")
    public Cuenta retirar(@RequestBody Cuenta cuenta) {

        return cuentaService.retirarMonto(cuenta);
    }


    @PutMapping("/accounts/transferir")
    public DatosTransferencia transferir(@RequestBody DatosTransferencia datosTransferencia) {

        return cuentaService.transferirMonto(datosTransferencia);
    }


    @DeleteMapping("/accounts/{id}") //No le pone nombre a @pathVariable porque id = id
    public void cancelarCuenta(@PathVariable String id) {

        cuentaService.cancelarCuenta(id);
    }

}
