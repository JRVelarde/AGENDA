package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) {
        if(args.length != 1){
            System.out.println("Uso: java App <nombre_archivo.csv");
            return;
        }
        String csvArchivo = args[0];
        ContactoAgenda agenda = new ContactoAgenda();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("1.Buscar por código " +
                    "2.Buscar por inicio de nombre " +
                    "3.Mostrar Agenda " +
                    "4.Añadir contacto " +
                    "5.Borrar contacto " +
                    "0.Salir " +
                    "Seleccione una opción:");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion){
                case 1:
                    System.out.println("Escribe el código del usuario");
                    UUID codigo = UUID.fromString(sc.nextLine());
                    List<Contacto> buscarPorUserId = agenda.buscarPorUserId(codigo);
                    for (Contacto contacto : buscarPorUserId){
                        System.out.println(contacto);
                    }
                    break;
                case 2:
                    System.out.println("Escribe las iniciales");
                    String iniciales = sc.nextLine();
                    List<Contacto> buscarPorNombre = agenda.buscarPorNombre(iniciales);
                    for (Contacto contacto : buscarPorNombre){
                        System.out.println(contacto);
                    }
                    break;
                case 3:
                    System.out.println("Agenda Completa:");
                    agenda.mostrarContactos();
                    break;
                case 4:
                    System.out.println("Escribe los datos del contacto");
                    System.out.println("nombre?:");
                    String nombre = sc.nextLine();
                    System.out.println("tlf?");
                    String tlf = sc.nextLine();
                    System.out.println("Edad?:");
                    int edad = sc.nextInt();
                    sc.nextLine();
                    Contacto nuevoContacto = new Contacto(nombre, tlf, edad);
                    agenda.ContactoNuevo(nuevoContacto);
                    System.out.println("Contacto añadido");
                    break;
                case 5:
                    System.out.println("Escribe el UUID del contacto que quieres eliminar");

                    try {
                        UUID EliminarUsuario = UUID.fromString(sc.nextLine());
                        Boolean existe = false;
                        for (Contacto contacto : agenda.getContactos())
                            if (contacto.getUserId().equals(EliminarUsuario)) {
                                existe = true;
                                break;
                            }
                        if (existe) {
                            agenda.eliminarContacto(EliminarUsuario);
                            System.out.println("Contacto borrado con éxito");

                        } else {
                            System.out.println("No se ha encontrado el contacto");
                        }
                    }catch (IllegalArgumentException e){
                        System.out.println("El formato del uuid es incorrecto");
                    }

                    break;
                case 0:
                    try {
                        agenda.guardarEnCSV(csvArchivo);
                        System.out.println("Agenda guardada en "+ csvArchivo);
                    }catch (IOException e){
                        System.out.println("Error en guardar la agenda en "+csvArchivo);
                        e.printStackTrace();
                    }
                    System.out.println("Saliendo del Programa");
                    System.exit(0);
                default:
                    System.out.println("opción no válida");
            }
        }
    }
}
