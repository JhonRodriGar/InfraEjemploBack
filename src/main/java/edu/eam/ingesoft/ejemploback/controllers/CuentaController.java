package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.*;
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

/*
    @PutMapping("/accounts/consignar")
    public Cuenta consignar(@RequestBody Cuenta cuenta) {

        return cuentaService.consignarMonto(cuenta);
    }
*/




    @PutMapping("/accounts/{cuenta}/consignar")
    public void consignar(@PathVariable String cuenta, @RequestBody MontoConsigRet montoConsigRet) {
        System.out.println("El monto a consignar es  " + montoConsigRet.getMontoCT());
        System.out.println("La cuenta a la que se va a consignar es " + cuenta);
        cuentaService.consignarMonto(cuenta, montoConsigRet);
    }


    @PutMapping("/accounts/{cuenta}/retirar")
    public void retirar(@PathVariable String cuenta, @RequestBody MontoConsigRet montoConsigRet) {
        System.out.println("El monto a retirar es  " + montoConsigRet.getMontoCT());
        System.out.println("La cuenta a la que se va a consignar es " + cuenta);
        cuentaService.retirarMonto(cuenta, montoConsigRet);
    }



/*
    @PutMapping("/accounts/retirar")
    public Cuenta retirar(@RequestBody Cuenta cuenta) {

        return cuentaService.retirarMonto(cuenta);
    }
*/

    @PutMapping("/accounts/transferir")
    public DatosTransferencia transferir(@RequestBody DatosTransferencia datosTransferencia) {

        return cuentaService.transferirMonto(datosTransferencia);
    }


    @DeleteMapping("/accounts/{id}") //No le pone nombre a @pathVariable porque id = id
    public void cancelarCuenta(@PathVariable String id) {

        cuentaService.cancelarCuenta(id);
    }

}
