package com.backend.kosa_midas_7_backend.entity.workhome;

import com.backend.kosa_midas_7_backend.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkHome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean recruitment;

    private String reason;

    private String response;

    public WorkHome updateRecruitment(Boolean b) {
        this.recruitment = b;
        return this;
    }

    public void updateResponse(String response) {
        this.response = response;
    }

}
