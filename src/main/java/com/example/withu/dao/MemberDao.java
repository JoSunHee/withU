package com.example.withu.dao;

import com.example.withu.model.MemberModel;
import com.example.withu.dto.GenMemberDTO;
import com.example.withu.dto.MemberDTO;
import com.example.withu.dto.ShelterMemberDTO;

import java.util.List;

public interface MemberDao {
    void registMember(MemberDTO memberDTO);     //기본 사용자 정보
    void registergenMember(GenMemberDTO genMemberDTO);     //일반사용자 회원가입
    void registershelterMember(ShelterMemberDTO shelterMemberDTO); //보호소사용자 회원가입
    int memberlogin(MemberDTO memberDTO);    //로그인
    int membertypecheck(String email);
    GenMemberDTO getGenMemberInfo(String email);
}
