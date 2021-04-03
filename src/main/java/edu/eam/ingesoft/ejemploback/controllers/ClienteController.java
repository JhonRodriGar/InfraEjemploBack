package edu.eam.ingesoft.ejemploback.controllers;
//4. Lo cuarto que se debe crear es el controller
import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//api rest....
@RestController
public class ClienteController {

    @Autowired //Esta anotación sirve porque en el ClienteService se puso @Service
    private ClienteService clienteService; //El cliente se crea a través del service, por eso se debe declarar acá

    @PostMapping("/customers") //La información llega por post con el path /customers, se definió en el excel
    public void crear(@RequestBody Cliente cliente) { //Crea el método crear que recibe el cliente (que se pasó por el body en el postman) con un parámetro bodyparams. cliente ya es un objeto manipulable

        clienteService.crearCliente(cliente); //Le dice al ClienteService que crea el cliente
    }


    @GetMapping("/customers/{id}") //Si en el path se pone un nombre diferente a lo que recibe el método, se debe poner en el @pathVariable si fuera cédula en el path entonces no era necesario poner nada en el @pathVariable
    public Cliente buscarCliente(@PathVariable("id") String cedula) { //Crea un método que devuelve un cliente, recibe la cédula

        return clienteService.buscarCliente(cedula); //Desde el punto de vista del controlador quien hace le trabajo es el service
    }


    @GetMapping("/customers") //Esto mismo no puede estar en otra funcionalidad
    public List<Cliente> listarClientes() { //Crea un método que retorna una lista de clientes. Para listar los clientes no es necesario enviar parámetros

        return clienteService.listarClientes();
    }


    @PutMapping("/customers")
    public Cliente editar(@RequestBody Cliente cliente) { //Los datos llegan como bodyparams

        return clienteService.editarCliente(cliente); //Le dice al service que edite el cliente
    }


    @DeleteMapping("/customers/{cedula}") //No le pone nombre a path variable porque cedula = cedula
    public void borrarCliente(@PathVariable String cedula) {

        clienteService.borrarCliente(cedula);
    }
}
