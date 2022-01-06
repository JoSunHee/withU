package com.example.withu.dao;

import com.example.withu.dto.CommunityPostDTO;
import com.example.withu.dto.ProtectDogDTO;

import java.util.List;

public interface ProtectDogDAO {
    void registdog(ProtectDogDTO protectDogDTO);
    List<ProtectDogDTO> showDogAll();
    ProtectDogDTO showdogdetail(int dogno);
    void updatedog(ProtectDogDTO protectDogDTO);
    String findshelternamebyemail(String email);
}
