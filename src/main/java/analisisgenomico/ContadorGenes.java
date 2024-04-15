package analisisgenomico;

public class ContadorGenes {
    public int contarGenes(String dna) {
        return contarGenesRecursivo(dna, 0);
    }

    private int contarGenesRecursivo(String dna, int start) {
        int startIdx = dna.indexOf("ATG", start);
        if (startIdx == -1) {
            return 0; // No se encontraron más secuencias "ATG"
        }
        int endIdx = encontrarCodonFinal(dna, startIdx + 3);
        if (endIdx != -1) {
            // Cuenta este gen y sigue buscando más genes
            return 1 + contarGenesRecursivo(dna, endIdx + 3);
        } else {
            // Sigue buscando más "ATG" si no se encuentra un codón final válido
            return contarGenesRecursivo(dna, startIdx + 3);
        }
    }

    private int encontrarCodonFinal(String dna, int start) {
        int[] indicesFin = {buscarCodon(dna, "TAA", start), buscarCodon(dna, "TAG", start), buscarCodon(dna, "TGA", start)};
        int minIdx = Integer.MAX_VALUE;
        for (int idx : indicesFin) {
            if (idx != -1 && (idx - start) % 3 == 0 && idx < minIdx) {
                minIdx = idx;
            }
        }
        return (minIdx == Integer.MAX_VALUE) ? -1 : minIdx;
    }

    private int buscarCodon(String dna, String codon, int start) {
        int idx = dna.indexOf(codon, start);
        while (idx != -1) {
            if ((idx - start) % 3 == 0) {
                return idx;
            }
            idx = dna.indexOf(codon, idx + 1);
        }
        return -1;
    }
}
