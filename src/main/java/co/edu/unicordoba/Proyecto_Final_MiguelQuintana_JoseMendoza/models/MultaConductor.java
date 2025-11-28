package co.edu.unicordoba.Proyecto_Final_MiguelQuintana_JoseMendoza.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Data
@Entity
@Table(name = "multas_conductor")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_infraccion", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo_infraccion"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = MultaCinturon.class, name = "CINTURON"),
    @JsonSubTypes.Type(value = MultaLicencia.class, name = "LICENCIA"),
    @JsonSubTypes.Type(value = MultaVelocidad.class, name = "VELOCIDAD")
})
public abstract class MultaConductor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String licencia;

    @Transient
    public abstract double calcularMonto();
}