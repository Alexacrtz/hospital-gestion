package hospital;

import java.util.ArrayList;
import java.util.List;

public class Expediente {
    private int idExpediente;
    private List<String> diagnosticos;
    private List<Receta> recetas;

    public Expediente(int idExpediente) {
        this.idExpediente = idExpediente;
        this.diagnosticos = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }

    public void agregarDiagnostico(String diagnostico) {
        diagnosticos.add(diagnostico);
    }

    public void agregarReceta(Receta receta) {
        recetas.add(receta);
    }

    public String obtenerHistorial() {
        if (diagnosticos.isEmpty()) {
            return "Sin diagnósticos registrados.";
        }

        StringBuilder historial = new StringBuilder("=== Historial Médico ===\n");
        for (String diag : diagnosticos) {
            historial.append("- ").append(diag).append("\n");
        }
        return historial.toString();
    }
}
