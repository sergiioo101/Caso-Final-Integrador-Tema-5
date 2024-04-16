package interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;

import analisisnumerico.ListadorNumeros;
import analisisnumerico.CalculadorPotencias;
import analisisnumerico.BuscadorMaximo;
import gestioninformacion.GestorFechas;
import analisisgenomico.ContadorGenes;
import gestioninformacion.OrganizadorDocumentos;
import gestioninformacion.BuscadorTexto;

public class AplicacionPrincipal {

    private static ContadorGenes contadorGenes = new ContadorGenes();
    private static GestorFechas gestorFechas = new GestorFechas();
    private static OrganizadorDocumentos organizadorDocumentos = new OrganizadorDocumentos();
    private static BuscadorTexto buscadorTexto = new BuscadorTexto();
    private static ListadorNumeros listadorNumeros = new ListadorNumeros();
    private static CalculadorPotencias calculadorPotencias = new CalculadorPotencias();
    private static BuscadorMaximo buscadorMaximo = new BuscadorMaximo();
    private static DefaultListModel<LocalDate> dateListModel = new DefaultListModel<>();
    private static JList<LocalDate> dateList = new JList<>(dateListModel);

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

        JButton btnGestionInformacion = new JButton("Gestión de Información");
        btnGestionInformacion.addActionListener(e -> gestionarInformacion());

        JButton btnGestionFechas = new JButton("Gestión de Fechas");
        btnGestionFechas.addActionListener(e -> gestionarFechas());

        frame.add(btnAnalisisGenomico);
        frame.add(btnAnalisisNumerico);
        frame.add(btnGestionInformacion);
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

    private static void gestionarInformacion() {
        JFrame infoFrame = new JFrame("Gestión de Información");
        infoFrame.setLayout(new FlowLayout());
        infoFrame.setSize(500, 300);

        JButton btnOrdenarDocumentos = new JButton("Ordenar Documentos");
        btnOrdenarDocumentos.addActionListener(e -> ordenarDocumentos());

        JButton btnBuscarTexto = new JButton("Buscar en Textos");
        btnBuscarTexto.addActionListener(e -> buscarEnTextos());

        infoFrame.add(btnOrdenarDocumentos);
        infoFrame.add(btnBuscarTexto);

        infoFrame.setVisible(true);
    }

    private static void ordenarDocumentos() {
        JFileChooser fileChooser = new JFileChooser("./documentos");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            Path file = fileChooser.getSelectedFile().toPath();
            try {
                List<String> lines = Files.readAllLines(file);
                Collections.sort(lines);
                Files.write(file, lines);
                JOptionPane.showMessageDialog(null, "El archivo ha sido ordenado correctamente.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer o escribir el archivo: " + e.getMessage());
            }
        }
    }

    private static void buscarEnTextos() {
        JFileChooser fileChooser = new JFileChooser("./documentos");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            Path file = fileChooser.getSelectedFile().toPath();
            String palabra = JOptionPane.showInputDialog("Ingrese la palabra a buscar:");
            try {
                List<String> lines = Files.readAllLines(file);
                Collections.sort(lines);  // Ensure lines are sorted for binary search
                boolean encontrado = buscadorTexto.buscarBinaria(lines, palabra);
                String mensaje = encontrado ? "Palabra encontrada." : "Palabra no encontrada.";
                JOptionPane.showMessageDialog(null, mensaje);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
            }
        }
    }

    private static void gestionarFechas() {
        JFrame fechaFrame = new JFrame("Gestión de Fechas");
        fechaFrame.setLayout(new BorderLayout());
        fechaFrame.setSize(500, 300);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JComboBox<Integer> dayComboBox = new JComboBox<>(generateNumbers(1, 31));
        JComboBox<Integer> monthComboBox = new JComboBox<>(generateNumbers(1, 12));
        JComboBox<Integer> yearComboBox = new JComboBox<>(generateNumbers(1900, 2100));
        JButton addButton = new JButton("Agregar Fecha");
        inputPanel.add(dayComboBox);
        inputPanel.add(monthComboBox);
        inputPanel.add(yearComboBox);
        inputPanel.add(addButton);

        // Add JList and JScrollPane to show dates
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







