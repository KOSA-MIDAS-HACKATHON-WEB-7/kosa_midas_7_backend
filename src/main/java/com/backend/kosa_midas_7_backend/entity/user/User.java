package com.backend.kosa_midas_7_backend.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String password;

    private String department;

    private String position;

    private String coreTimeStart;

    private String coreTimeEnd;

    private int date;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
<<<<<<< HEAD
    public User(String email, String accountId, String userName, String password, String department, String position,
                Role role, int date, String coreTimeStart, String coreTimeEnd) {
=======
    public User(String email, String accountId, String userName, String password, String department, String position, int date, String coreTimeStart, String coreTimeEnd) {
>>>>>>> aa10220e93163d133f1fcdd10bcd241b50da68d3
        this.email = email;
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.position = position;
        this.date = date;
        this.coreTimeStart = coreTimeStart;
        this.coreTimeEnd = coreTimeEnd;
<<<<<<< HEAD
        this.role = role;
=======
        this.role = Role.USER;
>>>>>>> aa10220e93163d133f1fcdd10bcd241b50da68d3
    }



    public User changePassword(String password) {
        this.password = password;
        return this;
    }

    public User changeDepartment(String department) {
        this.department = department;
        return this;
    }

    public User changePosition(String position) {
        this.position = position;
        return this;
    }
}
