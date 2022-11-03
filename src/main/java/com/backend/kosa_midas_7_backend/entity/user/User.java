package com.backend.kosa_midas_7_backend.entity.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String accountId, String userName, String password, String department, String position) {
        this.email = email;
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.position = position;
        this.role = Role.USER;
    }


}
