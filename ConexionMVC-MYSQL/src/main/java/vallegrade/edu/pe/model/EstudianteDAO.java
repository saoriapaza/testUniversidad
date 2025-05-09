package vallegrade.edu.pe.model;

import vallegrade.edu.pe.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    private Connection connection;

    public EstudianteDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insertar estudiante
    public void insertarEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setString(3, estudiante.getCorreo());
            stmt.setBoolean(4, estudiante.isEstado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener estudiante por ID
    public Estudiante obtenerEstudiante(int id) {
        String sql = "SELECT * FROM estudiantes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setCorreo(rs.getString("correo"));
                estudiante.setEstado(rs.getBoolean("estado"));
                return estudiante;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Modificar estudiante
    public void modificarEstudiante(Estudiante estudiante) {
        String sql = "UPDATE estudiantes SET nombre = ?, apellido = ?, correo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setString(3, estudiante.getCorreo());
            stmt.setInt(4, estudiante.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar lógicamente estudiante
    public void eliminarEstudianteLogico(int id) {
        String sql = "UPDATE estudiantes SET estado = FALSE WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar estudiantes activos
    public List<Estudiante> listarEstudiantesActivos() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes WHERE estado = TRUE";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setCorreo(rs.getString("correo"));
                estudiante.setEstado(rs.getBoolean("estado"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    // Listar estudiantes eliminados lógicamente
    public List<Estudiante> listarEstudiantesEliminados() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes WHERE estado = FALSE";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setCorreo(rs.getString("correo"));
                estudiante.setEstado(rs.getBoolean("estado"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }
}