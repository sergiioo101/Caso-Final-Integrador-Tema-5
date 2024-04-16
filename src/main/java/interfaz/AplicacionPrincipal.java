package interfaz;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import analisisnumerico.ListadorNumeros;
import analisisnumerico.CalculadorPotencias;
import analisisnumerico.BuscadorMaximo;
import gestioninformacion.GestorFechas;
import analisisgenomico.ContadorGenes;
import analisisgenomico.CombinadorGenetico;
import gestioninformacion.OrganizadorDocumentos;

public class AplicacionPrincipal {

    private static ContadorGenes contadorGenes = new ContadorGenes();
    private static GestorFechas gestorFechas = new GestorFechas();
    private static OrganizadorDocumentos organizadorDocumentos = new OrganizadorDocumentos();
    private static ListadorNumeros listadorNumeros = new ListadorNumeros();
    private static CalculadorPotencias calculadorPotencias = new CalculadorPotencias();
    private static BuscadorMaximo buscadorMaximo = new BuscadorMaximo();
    private static DefaultListModel<LocalDate> dateListModel = new DefaultListModel<>(); // Modelo de lista para fechas
    private static JList<LocalDate> dateList = new JList<>(dateListModel); // JList que usa el modelo

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AplicacionPrincipal::crearYMostrarGUI);
    }

    private static void crearYMostrarGUI() {
        JFrame frame = new JFrame("Sistema Interactivo de Análisis Genómico y Organización de Datos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.setSize(600, 400);

        JButton btnAnalisisGenomico = new JButton("Análisis Genómico");
        btnAnalisisGenomico.addActionListener(e -> realizarAnalisisGenomico());

        JButton btnAnalisisNumerico = new JButton("Análisis Numérico");
        btnAnalisisNumerico.addActionListener(e -> realizarAnalisisNumerico());

        JButton btnGestionDocumentos = new JButton("Gestión de Documentos");
        btnGestionDocumentos.addActionListener(e -> gestionarDocumentos());

        JButton btnGestionFechas = new JButton("Gestión de Fechas");
        btnGestionFechas.addActionListener(e -> gestionarFechas());

        frame.add(btnAnalisisGenomico);
        frame.add(btnAnalisisNumerico);
        frame.add(btnGestionDocumentos);
        frame.add(btnGestionFechas);

        frame.setVisible(true);
    }

    private static void realizarAnalisisGenomico() {
        String dna = JOptionPane.showInputDialog("Ingrese la secuencia de DNA:");
        int numGenes = contadorGenes.contarGenes(dna);
        JOptionPane.showMessageDialog(null, "Número de genes encontrados: " + numGenes);
    }

    private static void realizarAnalisisNumerico() {
        JFrame numericFrame = new JFrame("Análisis Numérico");
        numericFrame.setLayout(new FlowLayout());
        numericFrame.setSize(500, 200);

        JButton listButton = new JButton("Listar Números");
        listButton.addActionListener(e -> {
            int inicio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número inicial:"));
            int fin = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número final:"));
            List<Integer> numeros = listadorNumeros.listarNumeros(inicio, fin);
            JOptionPane.showMessageDialog(null, "Números listados: " + numeros);
        });

        JButton powerButton = new JButton("Calcular Potencia");
        powerButton.addActionListener(e -> {
            int base = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la base:"));
            int exponente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el exponente:"));
            int resultado = calculadorPotencias.calcularPotencia(base, exponente);
            JOptionPane.showMessageDialog(null, base + "^" + exponente + " = " + resultado);
        });

        JButton maxButton = new JButton("Encontrar Máximo");
        maxButton.addActionListener(e -> {
            int[] datos = Arrays.stream(JOptionPane.showInputDialog("Ingrese números separados por comas:").split(",")).mapToInt(Integer::parseInt).toArray();
            int maximo = buscadorMaximo.encontrarMaximo(datos);
            JOptionPane.showMessageDialog(null, "El máximo es: " + maximo);
        });

        numericFrame.add(listButton);
        numericFrame.add(powerButton);
        numericFrame.add(maxButton);

        numericFrame.setVisible(true);
    }

    private static void gestionarDocumentos() {
        String texto = JOptionPane.showInputDialog("Ingrese texto para organizar (separado por comas):");
        String[] lineas = texto.split(",");
        organizadorDocumentos.ordenarDocumentos(Arrays.asList(lineas));
        JOptionPane.showMessageDialog(null, "Documentos Organizados: \n" + String.join("\n", lineas));
    }

    private static void gestionarFechas() {
        JFrame fechaFrame = new JFrame("Gestión de Fechas");
        fechaFrame.setLayout(new BorderLayout());
        fechaFrame.setSize(500, 300);

        JPanel inputPanel = new JPanel();
        JComboBox<Integer> dayComboBox = new JComboBox<>(generateNumbers(1, 31));
        JComboBox<Integer> monthComboBox = new JComboBox<>(generateNumbers(1, 12));
        JComboBox<Integer> yearComboBox = new JComboBox<>(generateNumbers(1900, 2100));
        JButton addButton = new JButton("Agregar Fecha");
        inputPanel.add(dayComboBox);
        inputPanel.add(monthComboBox);
        inputPanel.add(yearComboBox);
        inputPanel.add(addButton);

        // Agregar JList y JScrollPane para mostrar fechas
        fechaFrame.add(new JScrollPane(dateList), BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            Integer day = (Integer) dayComboBox.getSelectedItem();
            Integer month = (Integer) monthComboBox.getSelectedItem();
            Integer year = (Integer) yearComboBox.getSelectedItem();
            LocalDate date = LocalDate.of(year, month, day);
            gestorFechas.agregarFecha(date);
            dateListModel.addElement(date);
        });

        JButton deleteButton = new JButton("Eliminar Fecha");
        deleteButton.addActionListener(e -> {
            LocalDate selectedDate = dateList.getSelectedValue();
            if (selectedDate != null) {
                gestorFechas.eliminarFecha(selectedDate);
                dateListModel.removeElement(selectedDate);
            }
        });
        inputPanel.add(deleteButton);

        fechaFrame.add(inputPanel, BorderLayout.SOUTH);
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





