package interfaz;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::crearYMostrarGUI);
    }

    private static void crearYMostrarGUI() {
        JFrame frame = new JFrame("Sistema Interactivo de Análisis Genómico y Organización de Datos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.setSize(400, 200);

        JButton btnAnalisisGenomico = new JButton("Análisis Genómico");
        btnAnalisisGenomico.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Análisis Genómico en proceso..."));

        JButton btnGestionDocumentos = new JButton("Gestión de Documentos");
        btnGestionDocumentos.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Gestión de Documentos activada..."));

        JButton btnGestionFechas = new JButton("Gestión de Fechas");
        btnGestionFechas.addActionListener(e -> gestionarFechas(frame));

        frame.add(btnAnalisisGenomico);
        frame.add(btnGestionDocumentos);
        frame.add(btnGestionFechas);

        frame.setVisible(true);
    }

    private static void gestionarFechas(JFrame parentFrame) {
        JFrame fechaFrame = new JFrame("Gestión de Fechas");
        fechaFrame.setLayout(new FlowLayout());
        fechaFrame.setSize(350, 200);

        JComboBox<Integer> dayComboBox = new JComboBox<>(generateNumbers(1, 31));
        JComboBox<Integer> monthComboBox = new JComboBox<>(generateNumbers(1, 12));
        JComboBox<Integer> yearComboBox = new JComboBox<>(generateNumbers(1900, 2100));
        JButton addButton = new JButton("Agregar Fecha");
        JLabel feedbackLabel = new JLabel();

        addButton.addActionListener(e -> {
            Integer day = (Integer) dayComboBox.getSelectedItem();
            Integer month = (Integer) monthComboBox.getSelectedItem();
            Integer year = (Integer) yearComboBox.getSelectedItem();
            feedbackLabel.setText("Fecha agregada: " + day + "/" + month + "/" + year);
        });

        fechaFrame.add(dayComboBox);
        fechaFrame.add(monthComboBox);
        fechaFrame.add(yearComboBox);
        fechaFrame.add(addButton);
        fechaFrame.add(feedbackLabel);

        fechaFrame.setVisible(true);
    }

    private static Integer[] generateNumbers(int start, int end) {
        Integer[] numbers = new Integer[end - start + 1];
        for (int i = start; i <= end; i++) {
            numbers[i - start] = i;
        }
        return numbers;
    }
}



