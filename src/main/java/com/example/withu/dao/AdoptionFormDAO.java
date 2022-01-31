package com.example.withu.dao;

import com.example.withu.dto.AdoptionFromDTO;

import java.util.List;

public interface AdoptionFormDAO {
    List<AdoptionFromDTO> selectformlistAll(String sheltername);
    void insertform(AdoptionFromDTO adoptionFromDTO);
}
