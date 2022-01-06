package com.example.withu.service.impl;

import com.example.withu.dao.ProtectDogDAO;
import com.example.withu.dto.HealthCareDTO;
import com.example.withu.dto.ProtectDogDTO;
import com.example.withu.service.ProtectDogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProtectDogServiceImpl implements ProtectDogService {

    @Autowired
    ProtectDogDAO protectDogDAO;

    @Override
    public void registdog(ProtectDogDTO protectDogDTO) {
        protectDogDAO.registdog(protectDogDTO);
    }

    @Override
    public List<ProtectDogDTO> showDogAll() {
        return protectDogDAO.showDogAll();
    }

    @Override
    public ProtectDogDTO showdogdetail(int dogno) {
        return protectDogDAO.showdogdetail(dogno);
    }

    @Override
    public void updatedog(ProtectDogDTO protectDogDTO) {
        protectDogDAO.updatedog(protectDogDTO);
    }

    @Override
    public String findshelternamebyemail(String email) {
        return protectDogDAO.findshelternamebyemail(email);
    }

    @Override
    public List<HealthCareDTO> showHealthCare(int dogno) {
        return protectDogDAO.showHealthCare(dogno);
    }

}
