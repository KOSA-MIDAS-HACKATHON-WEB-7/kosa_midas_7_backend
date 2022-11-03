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
    public User(String email, String accountId, String userName, String password, String department, String position, int date, String coreTimeStart, String coreTimeEnd) {
        this.email = email;
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.position = position;
        this.date = date;
        this.coreTimeStart = coreTimeStart;
        this.coreTimeEnd = coreTimeEnd;
        this.role = Role.USER;
    }

    public User changePassword(String password) {
        this.password = password;
        return this;
    }

}
