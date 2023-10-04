package org.example;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contacto {
    private UUID userId;
    private String Nombre;
    private String Telefono;
    private int edad;

    public Contacto(String nombre, String Telefono, int edad){

        this.userId = UUID.randomUUID();
        this.Nombre = nombre;
        this.Telefono = Telefono;
        this.edad = edad;

    }
    public static Contacto datosDesdeCSV (String csvString){
        String[] partes = csvString.split(",");
        UUID userID = UUID.fromString(partes[0]);
        String nombre = partes[1];
        String tlf = partes[2];
        int edad = Integer.parseInt(partes[3]);
        return new Contacto(userID, nombre, tlf, edad);
    }

    public String toCSVString(){
        return userId.toString() + "," + Nombre + "," + Telefono + "," + edad;
    }
}