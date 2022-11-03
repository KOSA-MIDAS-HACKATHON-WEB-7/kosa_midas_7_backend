package com.backend.kosa_midas_7_backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String accountId;

    private String userName;

    private String password;

    private String department;

    private String position;

    @OneToOne
    private Role role;

}
