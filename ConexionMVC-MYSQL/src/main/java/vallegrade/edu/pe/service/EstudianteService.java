package vallegrade.edu.pe.service;

import vallegrade.edu.pe.model.Estudiante;
import vallegrade.edu.pe.model.EstudianteDAO;

import java.util.List;

public class EstudianteService {
    private EstudianteDAO estudianteDAO;

    public EstudianteService() {
        this.estudianteDAO = new EstudianteDAO();
    }

    public void insertarEstudiante(Estudiante estudiante) {
        estudianteDAO.insertarEstudiante(estudiante);
    }

    public Estudiante obtenerEstudiante(int id) {
        return estudianteDAO.obtenerEstudiante(id);
    }

    public void modificarEstudiante(Estudiante estudiante) {
        estudianteDAO.modificarEstudiante(estudiante);
    }

    public void eliminarEstudianteLogico(int id) {
        estudianteDAO.eliminarEstudianteLogico(id);
    }

    public List<Estudiante> listarEstudiantesActivos() {
        return estudianteDAO.listarEstudiantesActivos();
    }

    public List<Estudiante> listarEstudiantesEliminados() {
        return estudianteDAO.listarEstudiantesEliminados();
    }
}