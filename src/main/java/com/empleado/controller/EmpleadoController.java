package com.empleado.controller;

import com.empleado.entity.Empleado;
import com.empleado.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleado") // Define la ruta base para las solicitudes
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	// Maneja solicitudes GET para recuperar todos los empleados, si los recupera corrctamente mandará 200 OK
	@GetMapping
	public ResponseEntity<List<Empleado>> getAllEmpleados() {
		List<Empleado> empleados = empleadoService.getAllEmpleados();
		if (empleados.isEmpty()) {
			return ResponseEntity.noContent().build(); // 204 No Content si no hay empleados
		}
		return ResponseEntity.ok(empleados); // 200 OK
	}
	// Recupera un empleado por su ID (200 OK o 404 Not Found)
	@GetMapping("/{id}")
	public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
		return empleadoService.getEmpleadoById(id)
				.map(ResponseEntity::ok) // 200 OK si el empleado existe
				.orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found si no existe
	}
	// Crea un nuevo empleado (201 Creado)
	@PostMapping
	public ResponseEntity<Empleado> createEmpleado(@Validated @RequestBody Empleado empleado) {
		Empleado savedEmpleado = empleadoService.saveEmpleado(empleado);
		return new ResponseEntity<>(savedEmpleado, HttpStatus.CREATED); // 201 Creado correctamente
	}

	// Actualiza un empleado existente (200 OK o 404 Not Found)
	@PutMapping("/{id}")
	public ResponseEntity<Empleado> updateEmpleado(
			@PathVariable Long id, @Validated @RequestBody Empleado empleado) {
		if (!empleadoService.getEmpleadoById(id).isPresent()) {
			return ResponseEntity.notFound().build(); // 404 Not Found si no existe
		}
		empleado.setId(id);
		Empleado updatedEmpleado = empleadoService.saveEmpleado(empleado);
		return ResponseEntity.ok(updatedEmpleado); // 200 OK con el empleado actualizado
	}

	// Elimina un empleado por su ID (204 No Content o 404 Not Found)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
		if (!empleadoService.getEmpleadoById(id).isPresent()) {
			return ResponseEntity.notFound().build(); // 404 Not Found si no existe
		}
		empleadoService.deleteEmpleado(id);
		return ResponseEntity.noContent().build(); // 204 No Content al eliminar
	}
}
