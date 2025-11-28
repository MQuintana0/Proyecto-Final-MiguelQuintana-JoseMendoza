package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.services;

import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models.MultaVehiculo;
import java.util.List;

public interface IMultaVehiculoService {
    List<MultaVehiculo> buscarTodos();
    MultaVehiculo guardar(MultaVehiculo multa);
    MultaVehiculo buscarPorId(Long id);
    void eliminar(Long id);
}
