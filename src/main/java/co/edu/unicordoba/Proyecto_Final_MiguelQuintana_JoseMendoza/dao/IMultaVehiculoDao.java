package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.dao;

import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models.MultaVehiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMultaVehiculoDao extends CrudRepository<MultaVehiculo, Long> {
    
}