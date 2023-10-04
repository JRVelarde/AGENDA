package org.example;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;


public class ContactManager  {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ContactManager <nombre_archivo_csv>");
            return;
        }

        String csvFileName = args[0];

        ContactAgenda agenda = new ContactAgenda(csvFileName);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Buscar por código de usuario");
            System.out.println("2. Buscar por comienzo del nombre");
            System.out.println("3. Mostrar la agenda completa");
            System.out.println("4. Añadir un contacto");
            System.out.println("5. Cambiar UUID a cero");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String choiceInput = scanner.nextLine();

            try {
                int choice = Integer.parseInt(choiceInput);

                switch (choice) {

                    case 1:
                        System.out.print("Ingrese el código de usuario a buscar: ");
                        String userIdStringToSearch = scanner.nextLine();

                        boolean foundContact = false;
                        try (Scanner fileScanner = new Scanner(new java.io.File(csvFileName))) {
                            while (fileScanner.hasNextLine()) {
                                String line = fileScanner.nextLine();
                                String[] parts = line.split(",");
                                if (parts.length > 0 && parts[0].equals(userIdStringToSearch)) {
                                    System.out.println("Contacto encontrado:");
                                    System.out.println("UUID: " + parts[0]);
                                    System.out.println("Nombre: " + parts[1]);
                                    System.out.println("Teléfono: " + parts[2]);
                                    System.out.println("Edad: " + parts[3]);
                                    foundContact = true;
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            System.err.println("Error al buscar por código de usuario: " + e.getMessage());
                        }

                        if (!foundContact) {
                            System.out.println("No se encontró ningún contacto con el código de usuario: " + userIdStringToSearch);
                        }
                        break;

                    case 2:
                        System.out.print("Ingrese el comienzo del nombre a buscar: ");
                        String startOfName = scanner.nextLine().toLowerCase(); // Convertir a minúsculas

                        boolean found = false;
                        try (Scanner fileScanner = new Scanner(new java.io.File(csvFileName))) {
                            while (fileScanner.hasNextLine()) {
                                String line = fileScanner.nextLine().toLowerCase(); // Convertir a minúsculas
                                String[] parts = line.split(",");
                                if (parts.length >= 2 && parts[1].startsWith(startOfName)) {
                                    System.out.println("Contacto encontrado:");
                                    System.out.println("UUID: " + parts[0]);
                                    System.out.println("Nombre: " + parts[1]);
                                    System.out.println("Teléfono: " + parts[2]);
                                    System.out.println("Edad: " + parts[3]);
                                    found = true;
                                }
                            }
                        } catch (IOException e) {
                            System.err.println("Error al buscar por comienzo del nombre: " + e.getMessage());
                        }

                        if (!found) {
                            System.out.println("No se encontraron contactos que empiecen con: " + startOfName);
                        }
                        break;


                    case 3:
                        System.out.println("Agenda completa:");
                        // Mostrar el contenido del archivo CSV
                        try (Scanner fileScanner = new Scanner(new java.io.File(csvFileName))) {
                            while (fileScanner.hasNextLine()) {
                                System.out.println(fileScanner.nextLine());
                            }
                        } catch (IOException e) {
                            System.err.println("Error al mostrar la agenda: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese el nombre del contacto: ");
                        String name = scanner.nextLine();
                        System.out.print("Ingrese el teléfono del contacto: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Ingrese la edad del contacto: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        Contact newContact = new Contact(UUID.randomUUID(), name, phoneNumber, age);

                        try {
                            agenda.addContact(newContact);
                        } catch (IOException e) {
                            System.err.println("Error al añadir el contacto: " + e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.print("Ingrese el código de usuario del contacto a modificar: ");
                        String userIdStringToModify = scanner.nextLine();

                        try {
                            UUID userIdToModify = UUID.fromString(userIdStringToModify);
                            agenda.cambiarUUIDToCero(userIdToModify);
                        } catch (IllegalArgumentException | IOException e) {
                            System.err.println("Error al cambiar el UUID: " + e.getMessage());
                        }
                        break;



                    case 0:
                        System.out.println("Saliendo del programa.");
                        System.exit(0);

                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida");
            }
        }
    }
}