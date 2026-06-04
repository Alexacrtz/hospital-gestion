package hospital;

public class Doctor extends Persona implements Notificable {
    private String especialidad;
    private String cedulaProfesional;
    private boolean disponibilidad;

    public Doctor(String nombre, int edad, String especialidad, String cedulaProfesional) {
        super(nombre, edad);
        this.especialidad = especialidad;
        this.cedulaProfesional = cedulaProfesional;
        this.disponibilidad = true;
    }

    public boolean verificarDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void asignarCita(Cita cita) {
        if (this.disponibilidad) {
            this.disponibilidad = false;
            cita.confirmar();
        } else {
            System.out.println("El doctor no está disponible en ese horario.");
        }
    }

    @Override
    public String getDatos() {
        return "Dr. " + getNombre() + " | Especialidad: " + especialidad;
    }

    @Override
    public void enviarNotificacion(String Mensaje) {
        System.out.println("Notificación para " + getNombre() + ": " + Mensaje);
    }
}
