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

    private String workWhere;

    private int date;

    @Column(columnDefinition = "INTEGER defaut 0")
    private int warning;

    @Column(columnDefinition = "TINYINT(1) default false")
    private Boolean accept;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String accountId, String userName, String password, String department, String position,
                Role role, int date, String coreTimeStart, String coreTimeEnd) {
        this.email = email;
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.position = position;
        this.date = date;
        this.coreTimeStart = coreTimeStart;
        this.accept = false;
        this.coreTimeEnd = coreTimeEnd;
        this.warning = 0;
        this.workWhere = null;
        this.role = role;
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

    public User changeAccountId(String newAccountId) {
        this.accountId = newAccountId;
        return this;
    }

    public User changeAccept(Boolean accept) {
        this.accept = accept;
        return this;
    }
}
