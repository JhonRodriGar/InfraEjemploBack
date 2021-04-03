package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.DatosTransferencia;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.repositories.ClienteRepository;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import edu.eam.ingesoft.ejemploback.repositories.TransaccionRepository;
import net.bytebuddy.matcher.FilterableList;
import net.bytebuddy.pool.TypePool;
import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public Cuenta consignarMonto(Cuenta cuenta){
        Cuenta cuentaBD = cuentaRepository.findById(cuenta.getId()).orElse(null); //Trae todos los datos que hay en la BD de la cuenta que se haya ingresado

        if (cuentaBD == null){
            throw new RuntimeException("No existe la cuenta a la que desea consignar");
        }

        if ((cuenta.getAmount() == 0) || (cuenta.getAmount() < 0)){
            throw new RuntimeException("El monto a consignar no puede ser 0 ni negativo");
        }

//A continuación se guarda la transacción
        Transaccion transaccionBD = new Transaccion(); //Creo un objeto transacción

        transaccionBD.setNumero("consig" +Math.random()); //El el campo número le asigno un aleatorio
        transaccionBD.setIdCuenta(cuenta.getId()); //Al atributo idCuenta le asigno el valor que tenga el atributo id en el objeto cuenta
        transaccionBD.setTipo("Consignación");
        transaccionBD.setMonto(cuenta.getAmount());

        transaccionRepository.save(transaccionBD); //Guardo la transacción
//Transacción guardada ///////////////////

        double montoTotal = cuentaBD.getAmount() + cuenta.getAmount(); //Al valor que haya en la base de datos le suma lo que va a consignar

        System.out.println("El monto total de la cuenta es " + montoTotal);

        cuenta.setAmount(montoTotal); //El atributo amount del objeto cuenta toma el valor del montoTotal para posteriormente guardarlo en la BD

        cuenta.setCedulaCliente(cuentaBD.getCedulaCliente()); //Como el customerid es una columna obligatoria en la BD, le paso los mismos datos en la edición
        cuenta.setFechaApertura(cuentaBD.getFechaApertura()); //Como la fecha apertura se actualiza automaticamente desde el constructor, le paso los mismos datos en la edición

        cuentaRepository.save(cuenta);
        return cuenta;
    }


    public Cuenta retirarMonto(Cuenta cuenta){
        Cuenta cuentaBD = cuentaRepository.findById(cuenta.getId()).orElse(null); //Trae todos los datos de la cuenta que se haya ingresado

        if (cuentaBD == null){
            throw new RuntimeException("No existe la cuenta de la que desea retirar");
        }

        if ((cuenta.getAmount() == 0) || (cuenta.getAmount() < 0)){
            throw new RuntimeException("El monto a retirar no puede ser 0 ni negativo");
        }

        if (cuentaBD.getAmount() < cuenta.getAmount()){
            throw new RuntimeException("No tiene fondos suficientes, tiene disponible " + cuentaBD.getAmount());
        }

//A continuación se guarda la transacción
        Transaccion transaccionBD = new Transaccion(); //Creo un objeto transacción

        transaccionBD.setNumero("ret" +Math.random());
        transaccionBD.setIdCuenta(cuenta.getId());
        transaccionBD.setTipo("Retiro");
        transaccionBD.setMonto(cuenta.getAmount());

        transaccionRepository.save(transaccionBD); //Guardo la transacción
//Transacción guardada ///////////////////

        double saldoCuenta = cuentaBD.getAmount() - cuenta.getAmount(); //Al valor que haya en la base de datos le resta lo que va a retirar

        System.out.println("El saldo que le queda en la cuenta es " + saldoCuenta);

        cuenta.setAmount(saldoCuenta); //El atributo amount del objeto cuenta toma el valor del montoTotal para posteriormente guardarlo en la BD

        cuenta.setCedulaCliente(cuentaBD.getCedulaCliente()); //Como el customerid es una columna obligatoria en la BD, le paso los mismos datos en la edición
        cuenta.setFechaApertura(cuentaBD.getFechaApertura()); //Como la fecha apertura se actualiza automaticamente desde el constructor, le paso los mismos datos en la edición

        cuentaRepository.save(cuenta);
        return cuenta;
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

//A continuación se guarda la transacción en cuenta origen
        Transaccion transaccionCtaOrigen = new Transaccion(); //Creo un objeto transacción

        transaccionCtaOrigen.setNumero("transf" +Math.random());
        transaccionCtaOrigen.setIdCuenta(datosTransferencia.getCtaOrigen());
        transaccionCtaOrigen.setTipo("Transferencia realizada");
        transaccionCtaOrigen.setMonto(datosTransferencia.getMonto());

        transaccionRepository.save(transaccionCtaOrigen); //Guardo la transacción
//Transacción cuenta origen guardada ///////////////////

//A continuación se guarda la transacción en cuenta destino
        Transaccion transaccionCtaDestino = new Transaccion(); //Creo un objeto transacción

        transaccionCtaDestino.setNumero("transf" +Math.random());
        transaccionCtaDestino.setIdCuenta(datosTransferencia.getCtaDestino());
        transaccionCtaDestino.setTipo("Transferencia recibida");
        transaccionCtaDestino.setMonto(datosTransferencia.getMonto());

        transaccionRepository.save(transaccionCtaDestino); //Guardo la transacción
//Transacción cuenta destino guardada ///////////////////

        double saldoCuentaOrigen = cuentaOrigenBD.getAmount() - datosTransferencia.getMonto(); //Al valor que haya en la cuenta origen en la base de datos le resta lo que va a transferir
        double saldoCuentaDestino = cuentaDestinoBD.getAmount() + datosTransferencia.getMonto(); //Al valor que haya en la cuenta de destino en la base de datos le suma lo que le va a recibir por transferencia

        System.out.println("El saldo que le queda en la cuenta origen es " + saldoCuentaOrigen); //Informativo, verifica si va bien
        System.out.println("El saldo que le queda en la cuenta destino es " + saldoCuentaDestino);

        Cuenta cuentaOrigen = cuentaRepository.getOne(datosTransferencia.getCtaOrigen()); //Crea un objeto cuentaOrigen que es el que voy a enviar al repository con todos los datos, inicialmente toma los mismos datos que hay en la BD ya que no pude crer el objeto vacío, tal vez porque en el modelo tieene la fecha
        Cuenta cuentaDestino = cuentaRepository.getOne(datosTransferencia.getCtaDestino()); //Crea un objeto cuentaDestino que es el que voy a enviar al repository con todos los datos, inicialmente toma los mismos datos que hay en la BD

        cuentaOrigen.setId(cuentaOrigen.getId()); //Le envía el mismo número de cuenta
        cuentaOrigen.setAmount(saldoCuentaOrigen); //Le envía el nuevo saldo que va a tener después de realizar la transferencia
        cuentaOrigen.setCedulaCliente(cuentaOrigen.getCedulaCliente()); //Le envía el mismo cliente
        cuentaOrigen.setFechaApertura(cuentaOrigen.getFechaApertura()); //Le envía la misma fecha de apertura

        cuentaDestino.setId(cuentaDestino.getId()); //Le envía el mismo número de cuenta
        cuentaDestino.setAmount(saldoCuentaDestino); //Le envía el nuevo saldo que va a tener al recibir la transferencia
        cuentaDestino.setCedulaCliente(cuentaDestino.getCedulaCliente()); //Le envía el mismo cliente
        cuentaDestino.setFechaApertura(cuentaDestino.getFechaApertura()); //Le envía la misma fecha de apertura

        cuentaRepository.save(cuentaOrigen); //Actualiza los datos de cuentaOrigen
        cuentaRepository.save(cuentaDestino); //Actualiza los datos de cuentaDestino

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
