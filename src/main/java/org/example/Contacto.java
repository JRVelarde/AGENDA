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

    public String toCSVString(){
        return userId.toString() + "," + Nombre + "," + Telefono + "," + edad;
    }
}