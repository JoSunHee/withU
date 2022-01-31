package com.example.withu.service.impl;

import com.example.withu.dao.AdoptionFormDAO;
import com.example.withu.dao.ProtectDogDAO;
import com.example.withu.dto.AdoptionFromDTO;
import com.example.withu.service.AdoptionFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdoptionFormServiceImpl implements AdoptionFormService {

    @Autowired
    AdoptionFormDAO adoptionFormDAO;

    @Override
    public List<AdoptionFromDTO> selectformlistAll(String sheltername) {
        return adoptionFormDAO.selectformlistAll(sheltername);
    }
}
