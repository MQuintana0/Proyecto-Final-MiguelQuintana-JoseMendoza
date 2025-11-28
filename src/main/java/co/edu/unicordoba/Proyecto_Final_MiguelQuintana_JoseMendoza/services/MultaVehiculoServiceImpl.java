package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.services;

import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.dao.IMultaVehiculoDao;
import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models.MultaVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MultaVehiculoServiceImpl implements IMultaVehiculoService {

    @Autowired
    private IMultaVehiculoDao multaDao;

    @Override
    @Transactional(readOnly = true)
    public List<MultaVehiculo> buscarTodos() {
        return (List<MultaVehiculo>) multaDao.findAll();
    }

    @Override
    @Transactional
    public MultaVehiculo guardar(MultaVehiculo multa) {
        return multaDao.save(multa);
    }

    @Override
    @Transactional(readOnly = true)
    public MultaVehiculo buscarPorId(Long id) {
        return multaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        multaDao.deleteById(id);
    }
}