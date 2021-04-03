package edu.eam.ingesoft.ejemploback.repositories;
//2. Lo segundo que se debe crear es el repositorio
import edu.eam.ingesoft.ejemploback.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Para que el spring boot pueda tener todas sus potencializades debe poner esta anotación, con esto spring boot sabe que esta es una clase repository que se encarga de acceder a ala base de datos
public interface ClienteRepository extends JpaRepository<Cliente, String> { //La interfaz ClienteRepository hereda de otra interfaz jpaRepository que tiene implementado el CRUD y listartodo , por lo tanto no es necesario escribir consultas SQL, lo que se debe indicar es cual es la entidad que va a manejar este repository y cual es el tipo de la llave primaria de la misma
}
