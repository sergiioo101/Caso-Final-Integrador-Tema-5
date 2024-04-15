package gestioninformacion;

import java.util.Arrays;
import java.util.List;

public class OrganizadorDocumentos {
    public void ordenarDocumentos(List<String> lineas) {
        lineas.sort(String::compareTo);
    }
}
