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

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime startOfficeHours;

    @NotNull
    private LocalDateTime finishOfficeHours;

    @NotNull
    private String officeHoursType;

    @OneToOne
    private WorkHome workHome;

}
