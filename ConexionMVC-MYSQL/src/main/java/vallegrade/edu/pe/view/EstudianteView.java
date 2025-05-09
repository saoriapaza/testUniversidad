package vallegrade.edu.pe.view;

import vallegrade.edu.pe.controller.EstudianteController;
import vallegrade.edu.pe.model.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EstudianteView extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JButton btnInsertar;
    private JButton btnModificar;
    private JButton btnEliminarLogico;
    private JButton btnListarActivos;
    private JButton btnMostrarEliminados;
    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;
    private EstudianteController controller;
    private Estudiante estudianteSeleccionado; // Para la modificación

    public EstudianteView() {
        controller = new EstudianteController();
        estudianteSeleccionado = null;
        setTitle("Gestión de Estudiantes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarEstudiantesActivos(); // Llamada inicial para cargar los activos
    }

    private void initComponents() {
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField();
        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField();
        // El ID no se edita directamente

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblApellido);
        panelFormulario.add(txtApellido);
        panelFormulario.add(lblCorreo);
        panelFormulario.add(txtCorreo);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnInsertar = new JButton("Insertar");
        btnModificar = new JButton("Modificar");
        btnEliminarLogico = new JButton("Eliminar Lógico");
        btnListarActivos = new JButton("Listar Activos");
        btnMostrarEliminados = new JButton("Mostrar Eliminados");

        panelBotones.add(btnInsertar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminarLogico);
        panelBotones.add(btnListarActivos);
        panelBotones.add(btnMostrarEliminados);

        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellido", "Correo", "Estado"}, 0);
        tablaEstudiantes = new JTable(modeloTabla);
        JScrollPane scrollPaneTabla = new JScrollPane(tablaEstudiantes);

        setLayout(new BorderLayout(10, 10));
        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPaneTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Listeners de los botones
        btnInsertar.addActionListener(e -> insertarEstudiante());
        btnModificar.addActionListener(e -> seleccionarEstudianteParaModificar());
        btnEliminarLogico.addActionListener(e -> eliminarEstudianteLogico());
        btnListarActivos.addActionListener(e -> cargarEstudiantesActivos());
        btnMostrarEliminados.addActionListener(e -> cargarEstudiantesEliminados());

        // Listener para seleccionar un estudiante de la tabla para modificar
        tablaEstudiantes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaEstudiantes.getSelectedRow() != -1) {
                int filaSeleccionada = tablaEstudiantes.getSelectedRow();
                int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
                estudianteSeleccionado = controller.obtenerEstudiante(id);
                if (estudianteSeleccionado != null) {
                    txtNombre.setText(estudianteSeleccionado.getNombre());
                    txtApellido.setText(estudianteSeleccionado.getApellido());
                    txtCorreo.setText(estudianteSeleccionado.getCorreo());
                    btnModificar.setText("Guardar Cambios");
                }
            } else {
                // Deseleccionar, resetear el botón si no hay selección
                btnModificar.setText("Modificar");
                estudianteSeleccionado = null;
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
    }

    private void insertarEstudiante() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();

        if (!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty()) {
            Estudiante nuevoEstudiante = new Estudiante(nombre, apellido, correo, true);
            controller.insertarEstudiante(nuevoEstudiante);
            cargarEstudiantesActivos();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Estudiante insertado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void seleccionarEstudianteParaModificar() {
        if (estudianteSeleccionado != null) {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();

            if (!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty()) {
                estudianteSeleccionado.setNombre(nombre);
                estudianteSeleccionado.setApellido(apellido);
                estudianteSeleccionado.setCorreo(correo);
                controller.modificarEstudiante(estudianteSeleccionado);
                cargarEstudiantesActivos();
                limpiarCampos();
                btnModificar.setText("Modificar"); // Resetear el texto del botón
                estudianteSeleccionado = null; // Resetear el estudiante seleccionado
                JOptionPane.showMessageDialog(this, "Estudiante modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un estudiante de la tabla para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarEstudianteLogico() {
        int filaSeleccionada = tablaEstudiantes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idEstudiante = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar lógicamente este estudiante?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                controller.eliminarEstudianteLogico(idEstudiante);
                cargarEstudiantesActivos();
                JOptionPane.showMessageDialog(this, "Estudiante eliminado lógicamente.");
                limpiarCampos();
                estudianteSeleccionado = null;
                btnModificar.setText("Modificar");
                tablaEstudiantes.clearSelection();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un estudiante para eliminar lógicamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cargarEstudiantesActivos() {
        modeloTabla.setRowCount(0); // Limpiar la tabla
        List<Estudiante> estudiantes = controller.listarEstudiantesActivos();
        for (Estudiante estudiante : estudiantes) {
            modeloTabla.addRow(new Object[]{estudiante.getId(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getCorreo(), estudiante.isEstado() ? "Activo" : "Inactivo"});
        }
    }

    private void cargarEstudiantesEliminados() {
        modeloTabla.setRowCount(0); // Limpiar la tabla
        List<Estudiante> estudiantes = controller.listarEstudiantesEliminados();
        for (Estudiante estudiante : estudiantes) {
            modeloTabla.addRow(new Object[]{estudiante.getId(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getCorreo(), estudiante.isEstado() ? "Activo" : "Inactivo"});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EstudianteView().setVisible(true);
        });
    }
}