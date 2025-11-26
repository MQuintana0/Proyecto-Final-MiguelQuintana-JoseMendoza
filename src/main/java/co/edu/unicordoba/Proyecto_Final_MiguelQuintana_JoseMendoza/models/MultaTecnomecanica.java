package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("TECNOMECANICA")
public class MultaTecnomecanica extends MultaVehiculo {

    @Override
    public double calcularMonto() {
        return 600_000;
    }
}
