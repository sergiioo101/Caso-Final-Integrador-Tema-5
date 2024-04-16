package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

// Asumimos que las clases están en sus respectivos paquetes y se han importado correctamente
import analisisgenomico.CombinadorGenetico;
import analisisgenomico.ContadorGenes;
import gestioninformacion.GestorFechas;
import gestioninformacion.OrganizadorDocumentos;

public class AplicacionPrincipal {

    private static ContadorGenes contadorGenes = new ContadorGenes();
    private static GestorFechas gestorFechas = new GestorFechas();
    private static OrganizadorDocumentos organizadorDocumentos = new OrganizadorDocumentos();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AplicacionPrincipal::crearYMostrarGUI);
    }

    private static void crearYMostrarGUI() {
        JFrame frame = new JFrame("Sistema Interactivo de Análisis Genómico y Organización de Datos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.setSize(500, 300);

        JButton btnAnalisisGenomico = new JButton("Análisis Genómico");
        btnAnalisisGenomico.addActionListener(e -> realizarAnalisisGenomico());

        JButton btnGestionDocumentos = new JButton("Gestión de Documentos");
        btnGestionDocumentos.addActionListener(e -> gestionarDocumentos());

        JButton btnGestionFechas = new JButton("Gestión de Fechas");
        btnGestionFechas.addActionListener(e -> gestionarFechas());

        frame.add(btnAnalisisGenomico);
        frame.add(btnGestionDocumentos);
        frame.add(btnGestionFechas);

        frame.setVisible(true);
    }

    private static void realizarAnalisisGenomico() {
        String dna = JOptionPane.showInputDialog("Ingrese la secuencia de DNA:");
        int numGenes = contadorGenes.contarGenes(dna);
        JOptionPane.showMessageDialog(null, "Número de genes encontrados: " + numGenes);
    }
    private static void realizarCombinacionesGeneticas() {
        String genes = JOptionPane.showInputDialog("Ingrese la secuencia de genes:");
        CombinadorGenetico combinador  = new CombinadorGenetico();
        int numCombinaciones = combinador.calcularCombinaciones(genes);
        JOptionPane.showMessageDialog(null, "Número total de combinaciones posibles: " + numCombinaciones);
    }


    private static void gestionarDocumentos() {
        String texto = JOptionPane.showInputDialog("Ingrese texto para organizar (separado por comas):");
        String[] lineas = texto.split(",");
        organizadorDocumentos.ordenarDocumentos(Arrays.asList(lineas));
        JOptionPane.showMessageDialog(null, "Documentos Organizados: \n" + String.join("\n", lineas));
    }

    private static void gestionarFechas() {
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
            gestorFechas.agregarFecha(day, month, year);  // Asume que GestorFechas tiene un método para agregar fechas
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




