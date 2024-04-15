package analisisnumerico;

public class CalculadorPotencias {
    public int calcularPotencia(int base, int exponente) {
        if (exponente == 0) {
            return 1; // Cualquier número elevado a la potencia de 0 es 1
        }
        if (exponente < 0) {
            return 1 / calcularPotencia(base, -exponente); // Manejo de exponentes negativos
        }
        return base * calcularPotencia(base, exponente - 1);
    }
}
