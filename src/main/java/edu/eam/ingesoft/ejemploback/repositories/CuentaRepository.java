package edu.eam.ingesoft.ejemploback.repositories;

import edu.eam.ingesoft.ejemploback.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> { //String porque en el modelo cuenta el @id está en id que es un String

    @Query("SELECT o FROM Cuenta o  WHERE o.cedulaCliente = :cedula") //La consulta no se hace en terminos de la base de datos sino de la entidad en el modelo, por eso busca cédula cliente y no id como está en la BD. o es como un alias de la entidad
    List<Cuenta> buscarCuentasCliente(String cedula); //El parámetro es cedula porque es como está la sentencia anterior

}
