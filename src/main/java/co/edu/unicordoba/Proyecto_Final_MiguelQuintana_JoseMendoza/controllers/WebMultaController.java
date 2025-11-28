package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.controllers;

import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models.*;
import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.services.IMultaConductorService;
import co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.services.IMultaVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/multas")
public class WebMultaController {

    @Autowired private IMultaVehiculoService multaVehiculoService;
    @Autowired private IMultaConductorService multaConductorService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/listado")
    public String listarMultas(Model model) {
        model.addAttribute("multasVehiculo", multaVehiculoService.buscarTodos());
        model.addAttribute("multasConductor", multaConductorService.buscarTodos());
        return "multas_listado";
    }

    @GetMapping("/vehiculo/nueva")
    public String formularioVehiculo(Model model) {
        model.addAttribute("multa", new MultaSoat()); 
        return "vehiculo_nueva";
    }

    @PostMapping("/vehiculo/guardar")
    public String guardarVehiculo(@ModelAttribute MultaSoat multa, @RequestParam String tipo_multa, @RequestParam(required = false) String gravedad) {
        MultaVehiculo nuevaMulta = null;
        if ("soat".equals(tipo_multa)) nuevaMulta = new MultaSoat();
        else if ("tecnomecanica".equals(tipo_multa)) nuevaMulta = new MultaTecnomecanica();
        else if ("accidente".equals(tipo_multa)) {
            MultaAccidente acc = new MultaAccidente();
            acc.setGravedad(gravedad);
            nuevaMulta = acc;
        }

        if (nuevaMulta != null) {
            nuevaMulta.setMatricula(multa.getMatricula());
            nuevaMulta.setMarca(multa.getMarca());
            multaVehiculoService.guardar(nuevaMulta);
        }
        return "redirect:/web/multas/listado";
    }

    @GetMapping("/conductor/nueva")
    public String formularioConductor(Model model) {
        model.addAttribute("multa", new MultaCinturon());
        return "conductor_nueva";
    }

    @PostMapping("/conductor/guardar")
    public String guardarConductor(@ModelAttribute MultaCinturon multaDatos, @RequestParam String tipo_multa, @RequestParam(required = false) Integer velocidad_registrada) {
        MultaConductor nuevaMulta = null;
        
        if ("cinturon".equals(tipo_multa)) nuevaMulta = new MultaCinturon();
        else if ("licencia".equals(tipo_multa)) nuevaMulta = new MultaLicencia();
        else if ("velocidad".equals(tipo_multa)) {
            MultaVelocidad vel = new MultaVelocidad();
            vel.setVelocidadRegistrada(velocidad_registrada);
            nuevaMulta = vel;
        }

        if (nuevaMulta != null) {
            nuevaMulta.setNombre(multaDatos.getNombre());
            nuevaMulta.setLicencia(multaDatos.getLicencia());
            multaConductorService.guardar(nuevaMulta);
        }
        return "redirect:/web/multas/listado";
    }

    @GetMapping("/detalle/{tipo}/{id}")
    public String verDetalle(@PathVariable String tipo, @PathVariable Long id, Model model) {
        Object multaEncontrada = null;

        if ("vehiculo".equals(tipo)) {
            multaEncontrada = multaVehiculoService.buscarPorId(id);
        } else if ("conductor".equals(tipo)) {
            multaEncontrada = multaConductorService.buscarPorId(id);
        }

        if (multaEncontrada == null) {
            return "redirect:/web/multas/listado";
        }

        model.addAttribute("multa", multaEncontrada);
        model.addAttribute("tipo", tipo);
        return "multas_detalle";
    }

    @GetMapping("/eliminar/{tipo}/{id}")
    public String eliminarMulta(@PathVariable String tipo, @PathVariable Long id) {
        if ("vehiculo".equals(tipo)) {
            multaVehiculoService.eliminar(id);
        } else if ("conductor".equals(tipo)) {
            multaConductorService.eliminar(id);
        }
        return "redirect:/web/multas/listado";
    }
}