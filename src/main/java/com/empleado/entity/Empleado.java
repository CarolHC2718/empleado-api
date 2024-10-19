package com.empleado.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity // Indica que esta clase es una entidad JPA
@Data // Lombok genera automáticamente getters y setters por default
@Table(name = "empleadodb") // Aquí apuntamos hacia la base de datos en la que trabajaremos
public class Empleado {

	@Id // Marca este campo como la clave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el valor del ID automáticamente
	private Long id;

	@NotBlank(message = "El nombre no puede estar vacío") // Valida que el nombre no esté vacío
	@Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
	private String nombre;

	@NotBlank(message = "El puesto no puede estar vacío")
	private String puesto;

	@Min(value = 0, message = "El salario debe ser un número positivo") // Valida que el salario sea positivo
	private double salario;

	@PastOrPresent(message = "La fecha de contratación no puede ser futura")
	private LocalDate fechaContratacion; // Almacena la fecha de contratación del empleado
}
