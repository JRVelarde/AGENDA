package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactoAgenda {
    private List<Contacto> contactos;

    public void guardarEnCSV(String nombreArchivo) throws IOException{
        try (RandomAccessFile ra = new RandomAccessFile(nombreArchivo,"rw")){
            for (Contacto contacto: contactos){
                String csvString = contacto.toCSVString() + "\n";
                ra.writeBytes(csvString);
            }
            System.out.println("Agenda guardada en " + nombreArchivo);
        }
    }

    public ContactoAgenda(){
        this.contactos = new ArrayList<>();
    }

    public void ContactoNuevo(Contacto contacto){
        contactos.add(contacto);
    }

    public void eliminarContacto(final UUID userID){
        contactos.removeIf(contacto -> contacto.getUserId().equals(userID));
    }

    public void mostrarContactos(){
        for (Contacto contacto : contactos){
            System.out.println(contacto);
        }
    }

    public List<Contacto> buscarPorUserId(UUID userId){
        List<Contacto> encontrarContacto = new ArrayList<>();
        for(Contacto contacto : contactos){
            if (contacto.getUserId().equals(userId)){
                encontrarContacto.add(contacto);
            }
        }
        return encontrarContacto;
    }

    public List<Contacto> getContactos(){
        return contactos;
    }
    public List<Contacto> buscarPorNombre(String iniciales){
        List<Contacto> encontrarContacto = new ArrayList<>();
        for(Contacto contacto : contactos){
            if (contacto.getNombre().toLowerCase().startsWith(iniciales.toLowerCase())){
                encontrarContacto.add(contacto);
            }
        }
        return encontrarContacto;
    }
}
