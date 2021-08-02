package com.study.jpastudy.repository;

import com.study.jpastudy.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlockRepositoryTest {

    @Autowired
    BlockRepository blockRepository;

    @Test
    void crud(){
        Block block = new Block();
        block.setNamess("이진약");
        block.setReason("친하지 않아서");
        block.setStartDate(LocalDate.now());
        block.setEndDate(LocalDate.now());

        blockRepository.save(block);

        List<Block> blocks = blockRepository.findAll();

        assertThat(blocks.size()).isEqualTo(3);
        assertThat(blocks.get(0).getNamess()).isEqualTo("dennis");
        assertThat(blocks.get(1).getNamess()).isEqualTo("sophia");
        assertThat(blocks.get(2).getNamess()).isEqualTo("이진약");

    }
}