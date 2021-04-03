package edu.eam.ingesoft.ejemploback.services;
//3. Lo tercero que se debe crear es el service
import ch.qos.logback.core.net.server.Client;
import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.repositories.ClienteRepository;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Para que spring boot sepa que esta es una clase de servicio
public class ClienteService {

    //Sigueinte dos líneas se les llama inyeccción de dependencias
    @Autowired //Esta anotación sirve porque en el ClienteRepository se puso @Repository
    private ClienteRepository clienteRepository; //El cliente se crea a través del repositorio, por eso se debe declarar acá

    @Autowired
    private CuentaRepository cuentaRepository;

    public void crearCliente(Cliente cliente) { //Crea un método que recibe por parámetro un cliente proveniente de controller
        //Buscando el cliente en la BD para validar si ya está o si no para crearlo
        Cliente clienteBD = clienteRepository.findById(cliente.getCedula()).orElse(null); //Busca cliente por ID el cual está en el objeto cliente. Guarda el objeto resultado de la consulta en . Retorna el resultado o null si no hay resultado (es como el resultset)

        //Si es diferente de null es porque ya existe....
        if (clienteBD != null) {
            throw new RuntimeException("Ya existe este cliente");
        }

        clienteRepository.save(cliente); //Si el cliente no existe entonces lo guarda
    }


    public Cliente buscarCliente(String cedula) { //Crea un método que retorna un objeto Cliente, recibe por parámetro la cédula

        Cliente clienteBD = clienteRepository.findById(cedula).orElse(null); //Busca cliente por ID el cual está en el objeto cliente. Guarda el objeto resultado de la consulta en clienteBD. Retorna el resultado o null si no hay resultado (es como el resultset)

        if (clienteBD == null) { //Si es diferente de null es porque ya existe....
            throw new RuntimeException("No existe este cliente");
        }

        return clienteRepository.findById(cedula).orElse(null); //orElse retorna el resultado o null si no hay resultado...

    }


    public List<Cliente> listarClientes(){ //Crea un objeto que devuelve una lista de clientes, no requiere nada por parámetros

        return clienteRepository.findAll(); //Le dice al repositorio que necesita toodo
    }


    public Cliente editarCliente(Cliente cliente) {  //Método que retorna un cliente y recibe por parámetro un cliente
        Cliente clieteBD = clienteRepository.findById(cliente.getCedula()).orElse(null); //La cédula está en el objeto cliente

        if (clieteBD == null) {
            throw new RuntimeException("No existe el cliente");
        }

        clienteRepository.save(cliente); //Si el cliente existe entonces lo guarda con los cambios que se haya realizado

        return cliente; //Al final retorna el cliente actualizado
    }


    public void borrarCliente(String cedula) { //Crea método que no retorna nada (void), reibe la cédula
        Cliente clieteBD = clienteRepository.findById(cedula).orElse(null);
        List<Cuenta> cuentasCliente = cuentaRepository.buscarCuentasCliente(cedula); //Le manda la cédula del cliente para consultar sus cuentas

        if (clieteBD == null) {
            throw new RuntimeException("No existe el cliente");
        }

        if (cuentasCliente.size() > 0) { //Sólo se puede borrar el cliente si no tiene cuentas
            throw new RuntimeException("El cliente tiene cuentas");
        }

        clienteRepository.deleteById(cedula);
    }

}
