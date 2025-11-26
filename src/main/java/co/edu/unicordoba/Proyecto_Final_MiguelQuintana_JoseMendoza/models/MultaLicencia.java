package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("LICENCIA")
public class MultaLicencia extends MultaConductor {

    @Override
    public double calcularMonto() {
        return 700_000;
    }
}