package com.study.jpastudy.domain;

import antlr.StringUtils;
import com.study.jpastudy.controller.dto.PersonDto;
import com.study.jpastudy.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = false")
@Access(AccessType.FIELD)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String namesa;

    private String hobby;
    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String bloodType;
    private String address;
    @Valid
    @Embedded
    private Birthday birthday;
    private String job;
    @ToString.Exclude
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private Block block;

    @ColumnDefault("0")
    private boolean deleted;

    public void set(PersonDto personDto){
        if(personDto.getHobby() != null){
            this.setHobby(personDto.getHobby());
        }
        if(personDto.getBloodType() != null){
            this.setBloodType(personDto.getBloodType());
        }
        if(personDto.getAddress()!= null){
            this.setAddress(personDto.getAddress());
        }
        if(personDto.getJob() != null){
            this.setJob(personDto.getJob());
        }
        if(personDto.getPhoneNumber() != null){
            this.setPhoneNumber(personDto.getPhoneNumber());
        }
    }
    public Integer getAge(){
        if(this.birthday != null){
            return LocalDate.now().getYear() - this.birthday.getYearOfBirthday()+1;
        }else{
            return null;
        }

    }

    public boolean isBirthdayToday(){
        if(this.birthday != null) {
            return LocalDate.now().equals(LocalDate.of(this.birthday.getYearOfBirthday(), this.birthday.getMonthOfBirthday(),
                    this.birthday.getDayOfBirthday()));
        }else {
            return false;
        }
    }
}
