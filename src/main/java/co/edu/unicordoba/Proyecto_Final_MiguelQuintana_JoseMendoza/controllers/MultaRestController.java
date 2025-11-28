package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.controllers;

import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models.MultaConductor;
import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models.MultaVehiculo;
import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.services.IMultaConductorService;
import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.services.IMultaVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
public class MultaRestController {

    @Autowired
    private IMultaVehiculoService multaVehiculoService;

    @Autowired
    private IMultaConductorService multaConductorService;

    @GetMapping("/vehiculos")
    public List<MultaVehiculo> listarMultasVehiculo() {
        return multaVehiculoService.buscarTodos();
    }

    @PostMapping("/vehiculos")
    public MultaVehiculo crearMultaVehiculo(@RequestBody MultaVehiculo multa) {
        return multaVehiculoService.guardar(multa);
    }

    @GetMapping("/vehiculos/{id}")
    public MultaVehiculo buscarVehiculoPorId(@PathVariable Long id) {
        return multaVehiculoService.buscarPorId(id);
    }

    @DeleteMapping("/vehiculos/{id}")
    public void eliminarMultaVehiculo(@PathVariable Long id) {
        multaVehiculoService.eliminar(id);
    }

    @GetMapping("/conductores")
    public List<MultaConductor> listarMultasConductor() {
        return multaConductorService.buscarTodos();
    }

    @PostMapping("/conductores")
    public MultaConductor crearMultaConductor(@RequestBody MultaConductor multa) {
        return multaConductorService.guardar(multa);
    }

    @GetMapping("/conductores/{id}")
    public MultaConductor buscarConductorPorId(@PathVariable Long id) {
        return multaConductorService.buscarPorId(id);
    }

    @DeleteMapping("/conductores/{id}")
    public void eliminarMultaConductor(@PathVariable Long id) {
        multaConductorService.eliminar(id);
    }
}
