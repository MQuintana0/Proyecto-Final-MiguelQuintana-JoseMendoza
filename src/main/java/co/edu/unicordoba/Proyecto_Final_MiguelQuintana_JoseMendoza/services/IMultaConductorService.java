package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.services;

import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models.MultaConductor;
import java.util.List;

public interface IMultaConductorService {
    List<MultaConductor> buscarTodos();
    MultaConductor guardar(MultaConductor multa);
    MultaConductor buscarPorId(Long id);
    void eliminar(Long id);
}