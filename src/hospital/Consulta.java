package hospital;

import java.util.Arrays;

public class Consulta {
    private Cita cita;
    private String notas;
    private int duracion;
    private String diagnostico;

    public Consulta(Cita cita) {
        this.cita = cita;
        this.notas = "";
        this.duracion = 0;
    }

    public void registrarNotas(String notas) {
        this.notas = notas;
    }

    public void emitirReceta(String medicamento, String dosis) {
        Receta receta = new Receta(Arrays.asList(medicamento), dosis);
        cita.getPaciente().getExpediente().agregarReceta(receta);
        receta.imprimir();
    }

    public void cerrarConsulta(String diagnostico) {
        this.diagnostico = diagnostico;
        cita.getPaciente().getExpediente().agregarDiagnostico(diagnostico);
        System.out.println("Consulta cerrada. Diagnóstico: " + diagnostico);
    }
}
