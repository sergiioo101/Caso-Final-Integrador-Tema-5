package gestioninformacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorFechas {
    private List<LocalDate> fechas;

    public GestorFechas() {
        this.fechas = new ArrayList<>();
    }

    public void agregarFecha(LocalDate fecha) {
        fechas.add(fecha);
        Collections.sort(fechas);
    }

    public List<LocalDate> obtenerFechas() {
        return fechas;
    }
}

