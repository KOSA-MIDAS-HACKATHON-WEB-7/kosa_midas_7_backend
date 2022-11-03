package com.backend.kosa_midas_7_backend.entity.officehour;

import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.workhome.WorkHome;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OfficeHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private User user;

    private LocalDateTime createdAt;

    private LocalDateTime startOfficeHours;

    private LocalDateTime finishOfficeHours;

    private String officeHoursType;

    @OneToOne
    private WorkHome workHome;

    private int date;

    public OfficeHour startWork(OfficeHour officeHour, User user) {
        officeHour.setUser(user);
        officeHour.setCreatedAt(LocalDateTime.now());
        officeHour.setStartOfficeHours(LocalDateTime.now());
        officeHour.setDate(user.getDate());
        return officeHour;
    }

}
