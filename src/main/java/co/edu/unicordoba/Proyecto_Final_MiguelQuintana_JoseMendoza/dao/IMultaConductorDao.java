package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.dao;

import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models.MultaConductor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMultaConductorDao extends CrudRepository<MultaConductor, Long> {
    
}