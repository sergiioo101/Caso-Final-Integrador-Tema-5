package analisisgenomico;

public class CombinadorGenetico {
    public int calcularCombinaciones(String genes) {
        if (genes == null || genes.length() == 0) {
            return 0;
        }
        return calcularCombinacionesRecursivo(genes, 0);
    }

    private int calcularCombinacionesRecursivo(String genes, int index) {
        if (index == genes.length()) {
            return 1; // Base case: la combinación vacía
        }
        // Recursivamente, contar las combinaciones incluyendo el gen actual y excluyéndolo
        int incluirGenActual = calcularCombinacionesRecursivo(genes, index + 1);
        int excluirGenActual = calcularCombinacionesRecursivo(genes, index + 1);
        return incluirGenActual + excluirGenActual;
    }
}
