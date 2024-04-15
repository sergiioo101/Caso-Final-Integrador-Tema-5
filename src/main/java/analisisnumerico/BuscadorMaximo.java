package analisisnumerico;

public class BuscadorMaximo {
    public int encontrarMaximo(int[] datos) {
        return encontrarMaximoRecursivo(datos, 0, datos[0]);
    }

    private int encontrarMaximoRecursivo(int[] datos, int indice, int maximoActual) {
        if (indice == datos.length) {
            return maximoActual;
        }
        if (datos[indice] > maximoActual) {
            maximoActual = datos[indice];
        }
        return encontrarMaximoRecursivo(datos, indice + 1, maximoActual);
    }
}

