package com.empleado.service;

import com.empleado.entity.Empleado;
import com.empleado.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired // Inyecta automáticamente una instancia de EmpleadoRepository
    private EmpleadoRepository empleadoRepository;

    // Recupera todos los empleados
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    // Recupera un empleado por su ID
    public Optional<Empleado> getEmpleadoById(Long id) {
        return empleadoRepository.findById(id);
    }

    // Guarda un nuevo empleado o actualiza uno existente
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Elimina un empleado por su ID
    public void deleteEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
