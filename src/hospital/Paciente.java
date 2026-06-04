package hospital;

public class Paciente extends Persona implements Notificable {

    private int idPaciente;
    private String historialMedico;
    private String email;
    private String telefono;
    private Expediente expediente;

    public Paciente(int idPaciente, String nombre, int edad, String email, String telefono) {
        super(nombre, edad);
        this.idPaciente = idPaciente;
        this.email = email;
        this.telefono = telefono;
        this.historialMedico = "";
        this.expediente = new Expediente(idPaciente);
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    @Override
    public String getDatos() {
        return "Paciente: " + getNombre() + " | ID: " + idPaciente;
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Notificacion para " + getNombre() + " (" + email + "): " + mensaje);
    }
}