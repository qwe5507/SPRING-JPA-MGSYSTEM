package com.study.jpastudy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.jpastudy.controller.dto.PersonDto;
import com.study.jpastudy.domain.Person;
import com.study.jpastudy.domain.dto.Birthday;
import com.study.jpastudy.exception.handler.GlobalExceptionHandler;
import com.study.jpastudy.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Slf4j
@Transactional
class PersonControllerTest {
    @Autowired PersonController personController;
    private MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebApplicationContext wac;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
    }
    @Test
    void getAll() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person")
                     .param("page", "1")
                     .param("size", "2"))
                .andExpect(status().isOk())  //isOK 는 200응답의미
                .andExpect(jsonPath("$.totalPages").value(3))
                .andExpect(jsonPath("$.totalElements").value(6))
                .andExpect(jsonPath("$.numberOfElements").value(2))
                .andExpect(jsonPath("$.content.[0].name").value("dennis"))
                .andExpect(jsonPath("$.content.[1].name").value("sophia"));

    }
    @Test
    void getPerson( ) throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andExpect(status().isOk())  //isOK 는 200응답의미
                .andExpect(jsonPath("$.namesa").value("martin"))
                .andExpect(jsonPath("$.hobby").isEmpty())
                .andExpect(jsonPath("$.address").isEmpty())
                .andExpect(jsonPath("$.birthday").value("1991-08-15"))
                .andExpect(jsonPath("$.job").isEmpty())
                .andExpect(jsonPath("$.phoneNumber").isEmpty())
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(jsonPath("$.age").isNumber())
                .andExpect(jsonPath("$.birthdayToday").isBoolean());
    }
    @Test
    void postPerson() throws Exception{
        PersonDto dto = PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-1111-2222");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJsonString(dto)) )
                .andExpect(status().isCreated());

        Person result = personRepository.findAll(Sort.by(Sort.Direction.DESC,"id")).get(0);

        assertAll(
                () ->  assertThat(result.getNamesa()).isEqualTo("martin"),
                ()-> assertThat(result.getHobby()).isEqualTo("programming"),
                ()-> assertThat(result.getAddress()).isEqualTo("판교"),
                ()->assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())) ,
                ()-> assertThat(result.getJob()).isEqualTo("programmer"),
                ()->assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222")
        );
    }
    @Test
    void postPersonIfNameIsNull() throws Exception{
        PersonDto dto = new PersonDto();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJsonString(dto)) )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수 값입니다."));

    }
    @Test
    void postPersonIfNameIsEmpty()throws Exception{
        PersonDto dto = new PersonDto();
        dto.setNamesa("");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJsonString(dto)) )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수 값입니다."));

    }
    @Test
    void postPersonIfNameIsBlankString() throws Exception{
        PersonDto dto = new PersonDto();
        dto.setNamesa(" ");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJsonString(dto)) )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수 값입니다."));

    }


//    @Test
//    void modifyPerson() throws Exception{
//        PersonDto dto = PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-2755-4444");
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/api/person/1")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(toJsonString(dto)) )
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.code").value(400))
//                .andExpect(jsonPath("$.message").value("이름을 변경 허용하지 않습니다."));
//
//        Person result = personRepository.findById(1L).get();
//
//        assertAll(
//                () ->  assertThat(result.getNamesa()).isEqualTo("martin"),
//                ()-> assertThat(result.getHobby()).isEqualTo("programming"),
//                ()-> assertThat(result.getAddress()).isEqualTo("판교"),
//                ()->assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())) ,
//                ()-> assertThat(result.getJob()).isEqualTo("programmer"),
//                ()->assertThat(result.getPhoneNumber()).isEqualTo("010-2755-4444")
//                );
//    }

    @Test
    void modifyPersonIfNameIsDifferent() throws Exception{
        PersonDto dto = PersonDto.of("james","programming","판교",LocalDate.now(),"programmer","010-2755-4444");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJsonString(dto)) )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름을 변경 허용하지 않습니다."));
    }
    @Test
    void modifyPersonIfPersonNotFound() throws Exception{
        PersonDto dto = PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-1111-2222");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/10")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJsonString(dto)) )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("person 못찾음"));
    }


    @Test
    void modifyName() throws Exception{
         mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                        .param("namesa","martinModified"))
                .andExpect(status().isOk());

         assertThat(personRepository.findById(1L).get().getNamesa()).isEqualTo("martinModified");
    }
    @Test
//    @Disabled
    void deletePerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andExpect(status().isOk());

        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(1L)));

//                .andExpect(content().string("true"));

//        System.out.println("people deleted : {}"+personRepository.findPeopleDeleted());
    }
    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }
}