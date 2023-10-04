package org.example;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Contact {
    private UUID userId;
    private String name;
    private String phoneNumber;
    private int age;



    public String toCSVString() {
        return userId.toString() + "," + name + "," + phoneNumber + "," + age;
    }
}