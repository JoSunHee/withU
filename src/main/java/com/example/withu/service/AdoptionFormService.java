package com.example.withu.service;

import com.example.withu.dto.AdoptionFromDTO;

import java.util.List;

public interface AdoptionFormService {
    List<AdoptionFromDTO> selectformlistAll(String sheltername);
}
