package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.services;

import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.dao.IMultaConductorDao;
import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models.MultaConductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MultaConductorServiceImpl implements IMultaConductorService {

    @Autowired
    private IMultaConductorDao multaDao;

    @Override
    @Transactional(readOnly = true)
    public List<MultaConductor> buscarTodos() {
        return (List<MultaConductor>) multaDao.findAll();
    }

    @Override
    @Transactional
    public MultaConductor guardar(MultaConductor multa) {
        return multaDao.save(multa);
    }

    @Override
    @Transactional(readOnly = true)
    public MultaConductor buscarPorId(Long id) {
        return multaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        multaDao.deleteById(id);
    }
}