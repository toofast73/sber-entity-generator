package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
class ChunkDao {

    private final JdbcTemplate jt;

    @Autowired
    public ChunkDao(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }


}
