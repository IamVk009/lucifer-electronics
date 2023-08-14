package com.lucifer.electronic.store.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "users")
public class User {

    @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String userId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "user_address")
    private String address;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_image_name")
    private String imageName;
}
