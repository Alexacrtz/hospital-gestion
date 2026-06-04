package hospital;

import java.util.List;
import java.util.ArrayList;

public class Receta {
    private List<String> medicamentos;
    private String dosis;
    private String fechaEmision;

    public Receta(List<String> medicamentos, String dosis) {
        this.medicamentos = medicamentos;
        this.dosis = dosis;
        this.fechaEmision = java.time.LocalDate.now().toString();
    }

    public void imprimir () {
        System.out.println("=== Receta Médica ===");
        for (String med : medicamentos) {
            System.out.println("- " + med + " | Dosis: " + dosis);
        }
        System.out.println("Fecha de emisión: " + fechaEmision);
    }
}
