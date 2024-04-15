package analisisnumerico;

import java.util.ArrayList;
import java.util.List;

public class ListadorNumeros {
    public List<Integer> listarNumeros(int inicio, int fin) {
        List<Integer> resultado = new ArrayList<>();
        listarNumerosRecursivo(inicio, fin, resultado);
        return resultado;
    }

    private void listarNumerosRecursivo(int actual, int fin, List<Integer> resultado) {
        if (actual > fin) {
            return;
        }
        resultado.add(actual);
        listarNumerosRecursivo(actual + 1, fin, resultado);
    }

    public int sumatoriaNumeros(int n) {
        if (n <= 0) {
            return 0;
        }
        return n + sumatoriaNumeros(n - 1);
    }
}

