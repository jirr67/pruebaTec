package com.example.pruebatec.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private UUID id;
     private String name;
     private String password;
     private String email;

     @ElementCollection
     private List<Phone> phones;

    private String created;
    private String modified;
    private String last_login;
    private String token;
    private boolean isactive;
}
