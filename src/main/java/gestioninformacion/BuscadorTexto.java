package gestioninformacion;

import java.util.Collections;
import java.util.List;

public class BuscadorTexto {
    // Búsqueda lineal
    public boolean buscarLineal(List<String> textos, String objetivo) {
        for (String texto : textos) {
            if (texto.contains(objetivo)) {
                return true;
            }
        }
        return false;
    }

    // Búsqueda binaria (asume que las líneas están ordenadas)
    public boolean buscarBinaria(List<String> textos, String objetivo) {
        int low = 0;
        int high = textos.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            String midVal = textos.get(mid);

            if (midVal.compareTo(objetivo) < 0) {
                low = mid + 1;
            } else if (midVal.compareTo(objetivo) > 0) {
                high = mid - 1;
            } else {
                return true; // Palabra encontrada
            }
        }
        return false; // Palabra no encontrada
    }
}

