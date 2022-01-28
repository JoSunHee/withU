package com.example.withu.service;

import com.example.withu.dto.GenMemberDTO;
import com.example.withu.dto.MemberDTO;
import com.example.withu.dto.ShelterMemberDTO;

import java.util.List;

public interface MemberService {
    void registergenMember(MemberDTO memberDTO, GenMemberDTO genMemberDTO);
    void registershelterMember(MemberDTO memberDTO, ShelterMemberDTO shelterMemberDTO);
    int memberlogin(MemberDTO memberDTO);    //로그인
    int membertypecheck(String email);
    GenMemberDTO getGenMemberInfo(String email);
    List<ShelterMemberDTO> showshleterAll();
/*
    테스트
    List<MemberModel> printMember();
    void setMember(MemberModel member);
    void deleteMember(int id);
    void updateMember(int id, String name);*/
}
