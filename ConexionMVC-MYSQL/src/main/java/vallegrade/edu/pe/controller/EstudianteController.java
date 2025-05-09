package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Estudiante;
import vallegrade.edu.pe.service.EstudianteService;

import java.util.List;

public class EstudianteController {
    private EstudianteService estudianteService;

    public EstudianteController() {
        this.estudianteService = new EstudianteService();
    }

    public void insertarEstudiante(Estudiante estudiante) {
        estudianteService.insertarEstudiante(estudiante);
    }

    public Estudiante obtenerEstudiante(int id) {
        return estudianteService.obtenerEstudiante(id);
    }

    public void modificarEstudiante(Estudiante estudiante) {
        estudianteService.modificarEstudiante(estudiante);
    }

    public void eliminarEstudianteLogico(int id) {
        estudianteService.eliminarEstudianteLogico(id);
    }

    public List<Estudiante> listarEstudiantesActivos() {
        return estudianteService.listarEstudiantesActivos();
    }

    public List<Estudiante> listarEstudiantesEliminados() {
        return estudianteService.listarEstudiantesEliminados();
    }
}