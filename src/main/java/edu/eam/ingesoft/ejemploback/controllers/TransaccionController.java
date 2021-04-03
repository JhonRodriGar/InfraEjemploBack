package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;

    @GetMapping("/transaction/{cuenta}")
    public List<Transaccion> listaTransaccionesCuenta(@PathVariable String cuenta) {
        return transaccionService.listaTransaccionesCuenta(cuenta);
    }

    @PostMapping("/transaction")
    public Transaccion crearTransaccion(@RequestBody Transaccion transaccion){

        return transaccionService.crearTransaccion(transaccion);
    }
}
