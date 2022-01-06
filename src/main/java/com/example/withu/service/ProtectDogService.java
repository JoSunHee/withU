package com.example.withu.service;

import com.example.withu.dao.ProtectDogDAO;
import com.example.withu.dto.CommunityPostDTO;
import com.example.withu.dto.ProtectDogDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProtectDogService {
    void registdog(ProtectDogDTO protectDogDTO);
    List<ProtectDogDTO> showDogAll();
    ProtectDogDTO showdogdetail(int dogno);
    void updatedog(ProtectDogDTO protectDogDTO);
    String findshelternamebyemail(String email);
}
