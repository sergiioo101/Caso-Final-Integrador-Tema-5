package gestioninformacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorFechas {
    private List<LocalDate> fechas;

    public GestorFechas() {
        this.fechas = new ArrayList<>();
    }

    public void agregarFecha(LocalDate fecha) {
        if (fecha != null) {
            fechas.add(fecha);
            System.out.println("Fecha agregada exitosamente: " + fecha);
        } else {
            System.out.println("No se puede agregar una fecha nula.");
        }
    }

    public List<LocalDate> obtenerFechas() {
        return new ArrayList<>(fechas);
    }

    public void eliminarFecha(LocalDate fecha) {
        if (fechas.remove(fecha)) {
            System.out.println("Fecha eliminada exitosamente: " + fecha);
        } else {
            System.out.println("Fecha no encontrada.");
        }
    }

}



