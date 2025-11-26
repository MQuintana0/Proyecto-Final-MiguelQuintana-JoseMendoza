package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ACCIDENTE")
public class MultaAccidente extends MultaVehiculo {

    private String gravedad;

    @Override
    public double calcularMonto() {
        if ("grave".equalsIgnoreCase(gravedad)) return 1_000_000;
        if ("moderada".equalsIgnoreCase(gravedad)) return 500_000;
        return 200_000;
    }
}
