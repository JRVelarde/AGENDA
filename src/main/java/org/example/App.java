package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        ContactoAgenda agenda = new ContactoAgenda();
        Scanner sc = new Scanner(System.in);

    public String Menu(){

        int opcion = 0;
        while(true){
            System.out.println("1.Buscar por código " +
                    "2.Buscar por inicio de nombre " +
                    "3.Mostrar Agenda " +
                    "4.Añadir contacto " +
                    "5.Borrar contacto " +
                    "0.Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion){
                case 1:
                    System.out.println("Escribe el código del usuario");
                    UUID codigo = UUID.fromString(sc.nextLine());
                    List<Contacto> buscarContacto =
                    break;

            }
        }
    }


    }
}
