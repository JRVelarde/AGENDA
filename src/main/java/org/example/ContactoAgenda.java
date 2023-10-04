package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

class ContactAgenda {
    private String fileName;

    public ContactAgenda(String fileName) {
        this.fileName = fileName;
    }

    public void addContact(Contact contact) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
            String csvString = contact.toCSVString() + "\n";
            file.seek(file.length());
            file.writeBytes(csvString);
            System.out.println("Contacto añadido y guardado en " + fileName);
        }
    }

    public void cambiarUUIDToCero(UUID userId) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
            while (file.getFilePointer() < file.length()) {
                String line = file.readLine();
                if (line.startsWith(userId.toString())) {
                    String[] parts = line.split(",");
                    parts[0] = "00000000-0000-0000-0000-000000000000";
                    String newLine = String.join(",", parts);
                    file.seek(file.getFilePointer() - line.length() - 1);
                    file.writeBytes(newLine + "\n");
                    System.out.println("UUID cambiado a 00000000-0000-0000-0000-000000000000 en " + fileName);
                    break;
                }
            }
        }
    }
}