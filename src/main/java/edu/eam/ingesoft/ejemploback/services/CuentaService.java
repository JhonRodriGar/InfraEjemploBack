package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.*;
import edu.eam.ingesoft.ejemploback.repositories.ClienteRepository;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import edu.eam.ingesoft.ejemploback.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private ClienteRepository clienteRepository; //Declara que va a utilizar el clienteRepository

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;


    public  Cuenta crearCuenta(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getCedulaCliente()).orElse(null); //Primero debe mirar si el cliente existe

        if (cliente == null) {
            throw new RuntimeException("No existe el cliente");
        }

        List<Cuenta> cuentasCliente = cuentaRepository.buscarCuentasCliente(cuenta.getCedulaCliente()); //Le manda la cédula del cliente para consultar sus cuentas

        if (cuentasCliente.size() == 3) { //Solo puede crear 3 cuentas
            throw new RuntimeException("El cliente ya tiene 3 cuentas, no puede crear más");
        }

        cuentaRepository.save(cuenta); //Si el cliente existe y tiene menos de tres cuentas entonces guarda

        return cuenta; //Retorna la cuenta
    }


    public List<Cuenta> listarCuentasCliente(String cedula) { //Crea método que retorna una lista de cuentas, recibe como parámetro una cédula que llega del controller

        Cliente cliente = clienteRepository.findById(cedula).orElse(null); //Primero debe mirar si el cliente existe

        if (cliente == null) {
            throw new RuntimeException("No existe el cliente");
        }

        List<Cuenta> cuentasCliente = cuentaRepository.buscarCuentasCliente(cedula); //Le manda la cédula del cliente para consultar sus cuentas

        if (cuentasCliente.size() == 0) { //Revisa si no tiene cuentas
            throw new RuntimeException("El cliente no tiene cuentas");
        }

        return cuentaRepository.buscarCuentasCliente(cedula); //Le regunta la información al repositorio, lo busca por cédula
    }



    public void consignarMonto(String cuenta, MontoConsigRet montoConsigRet){
        Cuenta cuentaBD = cuentaRepository.findById(cuenta).orElse(null); //Trae todos los datos que hay en la BD de la cuenta que se haya ingresado

        if (cuentaBD == null){
            throw new RuntimeException("No existe la cuenta a la que desea consignar");
        }

        if ((montoConsigRet.getMontoCT() == 0) || (montoConsigRet.getMontoCT() < 0)){
            throw new RuntimeException("El monto a consignar no puede ser 0 ni negativo");
        }

//A continuación se guarda la transacción
        Transaccion transaccionBD = new Transaccion(null, cuenta, "Consignación", montoConsigRet.getMontoCT(), new Date()); //Creo un objeto llamado transacionBD de la clase transacción y lo lleno con los datos que necesito enviar a la BD
        transaccionRepository.save(transaccionBD); //Envío el objeto anterior a la BD
//Transacción guardada ///////////////////

        double montoTotal = cuentaBD.getAmount() + montoConsigRet.getMontoCT(); //Al valor que haya en la base de datos le suma lo que va a consignar

        cuentaBD.setAmount(montoTotal);

        cuentaRepository.save(cuentaBD);
    }


    public void retirarMonto(String cuenta, MontoConsigRet montoConsigRet){
        Cuenta cuentaBD = cuentaRepository.findById(cuenta).orElse(null); //Trae todos los datos de la cuenta que se haya ingresado

        if (cuentaBD == null){
            throw new RuntimeException("No existe la cuenta de la que desea retirar");
        }

        if ((montoConsigRet.getMontoCT() == 0) || (montoConsigRet.getMontoCT() < 0)){
            throw new RuntimeException("El monto a retirar no puede ser 0 ni negativo");
        }

        if (cuentaBD.getAmount() < montoConsigRet.getMontoCT()){
            throw new RuntimeException("No tiene fondos suficientes, tiene disponible " + cuentaBD.getAmount());
        }

//A continuación se guarda la transacción
        Transaccion transaccionBD = new Transaccion(null, cuenta, "Retiro", montoConsigRet.getMontoCT(), new Date());
        transaccionRepository.save(transaccionBD); //Envío el objeto anterior a la BD
//Transacción guardada ///////////////////

        double saldoCuenta = cuentaBD.getAmount() - montoConsigRet.getMontoCT();

        cuentaBD.setAmount(saldoCuenta);

        cuentaRepository.save(cuentaBD);
    }


    public DatosTransferencia transferirMonto(DatosTransferencia datosTransferencia){
        Cuenta cuentaOrigenBD = cuentaRepository.findById(datosTransferencia.getCtaOrigen()).orElse(null); //Trae todos los datos de la cuenta de origen que se haya ingresado, si no la encuentra devuelve null
        Cuenta cuentaDestinoBD = cuentaRepository.findById(datosTransferencia.getCtaDestino()).orElse(null); //Trae todos los datos de la cuenta de destino que se haya ingresado, si no la encuentra devuelve null

        if (cuentaOrigenBD == null){
            throw new RuntimeException("No existe la cuenta de origen para transferir");
        }

        if ((datosTransferencia.getMonto() == 0) || (datosTransferencia.getMonto() < 0)){
            throw new RuntimeException("El monto a transferir no puede ser 0 ni negativo");
        }

        if (cuentaOrigenBD.getAmount() < datosTransferencia.getMonto()){
            throw new RuntimeException("No tiene fondos suficientes, tiene disponible " + cuentaOrigenBD.getAmount());
        }

        if (cuentaDestinoBD == null){
            throw new RuntimeException("No existe la cuenta de destino para transferir");
        }

//A continuación se guarda la transacción
        Transaccion transaccionCtaOrigen = new Transaccion(null, datosTransferencia.getCtaOrigen(), "Transferencia realizada", datosTransferencia.getMonto(), new Date());
        transaccionRepository.save(transaccionCtaOrigen); //Envío el objeto anterior a la BD
//Transacción guardada ///////////////////

//A continuación se guarda la transacción
        Transaccion transaccionCtaDestino = new Transaccion(null, datosTransferencia.getCtaDestino(), "Transferencia recibida", datosTransferencia.getMonto(), new Date());
        transaccionRepository.save(transaccionCtaDestino); //Envío el objeto anterior a la BD
//Transacción guardada ///////////////////

        double saldoCuentaOrigen = cuentaOrigenBD.getAmount() - datosTransferencia.getMonto(); //Al valor que haya en la cuenta origen en la base de datos le resta lo que va a transferir
        double saldoCuentaDestino = cuentaDestinoBD.getAmount() + datosTransferencia.getMonto(); //Al valor que haya en la cuenta de destino en la base de datos le suma lo que le va a recibir por transferencia

        cuentaOrigenBD.setAmount(saldoCuentaOrigen); //A la cuenta de origen le asigno el nuevo monto
        cuentaDestinoBD.setAmount(saldoCuentaDestino);

        cuentaRepository.save(cuentaOrigenBD); //Actualiza los datos de cuentaOrigen
        cuentaRepository.save(cuentaDestinoBD); //Actualiza los datos de cuentaDestino

        return datosTransferencia;
    }


    public void cancelarCuenta(String id) { //Crea método que no retorna nada (void), reibe el id
        Cuenta cuentaBD = cuentaRepository.findById(id).orElse(null);

        if (cuentaBD == null) {
            throw new RuntimeException("No existe la cuenta");
        }

        if (cuentaBD.getAmount() > 0) {
            throw new RuntimeException("La cuenta tiene fondos");
        }

        cuentaRepository.deleteById(id);
    }
}
