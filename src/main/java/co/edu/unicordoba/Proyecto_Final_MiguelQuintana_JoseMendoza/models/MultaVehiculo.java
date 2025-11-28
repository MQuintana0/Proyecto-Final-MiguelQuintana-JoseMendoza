package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Data
@Entity
@Table(name = "multas_vehiculo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_infraccion", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo_infraccion"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = MultaSoat.class, name = "SOAT"),
    @JsonSubTypes.Type(value = MultaAccidente.class, name = "ACCIDENTE"),
    @JsonSubTypes.Type(value = MultaTecnomecanica.class, name = "TECNOMECANICA")
})
public abstract class MultaVehiculo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String matricula;

    @Column(nullable = false)
    private String marca;

    @Transient
    public abstract double calcularMonto();
}