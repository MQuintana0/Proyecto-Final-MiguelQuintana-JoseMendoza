package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("VELOCIDAD")
public class MultaVelocidad extends MultaConductor {

    @Column(name = "velocidad_registrada")
    private Integer velocidadRegistrada;

    @Override
    public double calcularMonto() {
        if (velocidadRegistrada == null || velocidadRegistrada <= 80) return 0;
        if (velocidadRegistrada <= 100) return 400_000;
        if (velocidadRegistrada <= 120) return 700_000;
        return 1_200_000;
    }
}
