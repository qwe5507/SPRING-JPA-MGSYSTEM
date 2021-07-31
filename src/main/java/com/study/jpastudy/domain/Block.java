package com.study.jpastudy.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Block {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String Reason;
    private LocalDate startDate;
    private LocalDate endDate;

}
