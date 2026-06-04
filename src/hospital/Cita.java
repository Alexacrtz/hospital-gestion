package hospital;

public class Cita implements Cancelable {
    private int idCita;
    private String fechaHora;
    private EstadoCita estado;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(int idCita, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
        this.estado = EstadoCita.PENDIENTE;
    }
    public int getIdCita() {
        return idCita;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void confirmar() {
        this.estado = EstadoCita.CONFIRMADA;
        paciente.enviarNotificacion("Cita confirmada para " + fechaHora);
        doctor.enviarNotificacion("Nueva cita con: " + paciente.getDatos());
    }

    @Override
    public boolean cancelar() {
        if (estado != EstadoCita.CANCELADA) {
            this.estado = EstadoCita.CANCELADA;
            doctor.setDisponibilidad(true);
            paciente.enviarNotificacion("Cita cancelada: " + fechaHora);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + idCita + " | Fecha: " + fechaHora +
                " | Estado: " + estado +
                " | Doctor: " + doctor.getNombre() +
                " | Paciente: " + paciente.getNombre();
    }
}
