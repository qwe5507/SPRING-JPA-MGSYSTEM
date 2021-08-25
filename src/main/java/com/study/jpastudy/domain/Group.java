package com.study.jpastudy.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"personList"})
@Data
@Access(AccessType.FIELD)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "group")
    private List<Person> personList;
}
