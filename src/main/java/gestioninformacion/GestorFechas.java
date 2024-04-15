package gestioninformacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorFechas {
    private List<LocalDate> fechas;

    public GestorFechas() {
        this.fechas = new ArrayList<>();
    }

    /**
     * Agrega una fecha al gestor de fechas.
     * @param fecha La fecha a agregar.
     */
    public void agregarFecha(LocalDate fecha) {
        if (fecha != null) {
            fechas.add(fecha);
            System.out.println("Fecha agregada exitosamente: " + fecha);
        } else {
            System.out.println("No se puede agregar una fecha nula.");
        }
    }

    /**
     * Obtiene una lista de todas las fechas almacenadas.
     * @return Una lista de objetos LocalDate.
     */
    public List<LocalDate> obtenerFechas() {
        return new ArrayList<>(fechas);  // Devuelve una copia de la lista para evitar modificaciones externas.
    }

    /**
     * Busca fechas en el rango especificado.
     * @param desde La fecha de inicio del rango.
     * @param hasta La fecha de fin del rango.
     * @return Una lista de fechas dentro del rango especificado.
     */
    public List<LocalDate> buscarFechas(LocalDate desde, LocalDate hasta) {
        List<LocalDate> resultado = new ArrayList<>();
        for (LocalDate fecha : fechas) {
            if ((fecha.isEqual(desde) || fecha.isAfter(desde)) && (fecha.isEqual(hasta) || fecha.isBefore(hasta))) {
                resultado.add(fecha);
            }
        }
        return resultado;
    }

    public void agregarFecha(Integer day, Integer month, Integer year) {
        if (day == null || month == null || year == null) {
            System.out.println("No se puede agregar una fecha con valores nulos.");
            return;
        }
        try {
            LocalDate fecha = LocalDate.of(year, month, day);
            agregarFecha(fecha);
        } catch (Exception e) {
            System.out.println("Error al agregar la fecha: " + e.getMessage());
        }
    }
}


