package hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    static Scanner scanner = new Scanner(System.in);
    static List<Paciente> pacientes = new ArrayList<>();
    static List<Doctor> doctores = new ArrayList<>();
    static List<Cita> citas = new ArrayList<>();
    static int contadorId = 1;

    public static void main(String[] args) {
        int opcion = 0;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1: registrarPaciente(); break;
                case 2: registrarDoctor(); break;
                case 3: agendarCita(); break;
                case 4: verCitas(); break;
                case 5: cancelarCita(); break;
                case 6: verExpediente(); break;
                case 7: System.out.println("Hasta luego!"); break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 7);
    }

    static void mostrarMenu() {
        System.out.println("\n==== Sistema de Gestion Hospitalaria ====");
        System.out.println("1. Registrar nuevo paciente");
        System.out.println("2. Registrar doctor");
        System.out.println("3. Agendar cita");
        System.out.println("4. Ver citas programadas");
        System.out.println("5. Cancelar cita");
        System.out.println("6. Ver expediente de paciente");
        System.out.println("7. Salir");
        System.out.print("Elige una opcion: ");
    }

    static void registrarPaciente() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefono: ");
        String telefono = scanner.nextLine();
        Paciente p = new Paciente(contadorId++, nombre, edad, email, telefono);
        pacientes.add(p);
        System.out.println("Paciente registrado: " + p.getDatos());
    }

    static void registrarDoctor() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();
        System.out.print("Cedula profesional: ");
        String cedula = scanner.nextLine();
        Doctor d = new Doctor(nombre, edad, especialidad, cedula);
        doctores.add(d);
        System.out.println("Doctor registrado: " + d.getDatos());
    }

    static void agendarCita() {
        if (pacientes.isEmpty() || doctores.isEmpty()) {
            System.out.println("Registra al menos un paciente y un doctor primero.");
            return;
        }
        System.out.println("=== Pacientes ===");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(i + ". " + pacientes.get(i).getDatos());
        }
        System.out.print("Selecciona paciente (numero): ");
        int idxP = Integer.parseInt(scanner.nextLine().trim());
        if (idxP < 0 || idxP >= pacientes.size()) {
            System.out.println("Numero de paciente invalido. ");
            return;
        }

        List<Doctor> disponibles = new ArrayList<>();
        for (Doctor d : doctores) {
            if (d.verificarDisponibilidad()) disponibles.add(d);
        }
        if (disponibles.isEmpty()) {
            System.out.println("No hay doctores disponibles.");
            return;
        }
        System.out.println("=== Doctores disponibles ===");
        for (int i = 0; i < disponibles.size(); i++) {
            System.out.println(i + ". " + disponibles.get(i).getDatos());
        }
        System.out.print("Selecciona doctor (numero): ");
        int idxD = Integer.parseInt(scanner.nextLine().trim());
        if (idxD < 0 || idxD >= disponibles.size()) {
            System.out.println("Numero de doctor invalido.");
            return;
        }

        System.out.print("Fecha y hora (YYYY-MM-DD HH:MM): ");
        String fecha = scanner.nextLine();
        System.out.print("Motivo: ");
        String motivo = scanner.nextLine().trim();

        Cita cita = new Cita(contadorId++, fecha, motivo, disponibles.get(idxD), pacientes.get(idxP));
        disponibles.get(idxD).asignarCita(cita);
        citas.add(cita);
        System.out.println("Cita agendada con ID: " + cita.getIdCita());
    }

    static void verCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }
        for (Cita c : citas) {
            System.out.println(c);
        }
    }

    static void cancelarCita() {
        System.out.print("ID de la cita a cancelar: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Cita c : citas) {
            if (c.getIdCita() == id) {
                boolean resultado = c.cancelar();
                System.out.println(resultado ? "Cita cancelada." : "La cita ya estaba cancelada.");
                return;
            }
        }
        System.out.println("Cita no encontrada.");
    }

    static void verExpediente() {
        System.out.print("ID del paciente: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Paciente p : pacientes) {
            if (p.getIdPaciente() == id) {
                System.out.println(p.getExpediente().obtenerHistorial());
                return;
            }
        }
        System.out.println("Paciente no encontrado.");
    }
}