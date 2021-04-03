package edu.eam.ingesoft.ejemploback.repositories;

import edu.eam.ingesoft.ejemploback.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, String> {

    @Query("SELECT o FROM Transaccion o  WHERE o.idCuenta = :cuenta") //La consulta no se hace en terminos de la base de datos sino de la entidad en el modelo, idCuenta y no accountid como está en la BD. o es como un alias de la entidad
    List<Transaccion> buscarTransaccionesCuentas(String cuenta); //El parámetro es cuenta porque es como está la sentencia anterior

}



