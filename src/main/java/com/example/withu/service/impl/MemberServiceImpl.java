package com.example.withu.service.impl;

import com.example.withu.dao.MemberDao;
import com.example.withu.model.MemberModel;
import com.example.withu.dto.GenMemberDTO;
import com.example.withu.dto.MemberDTO;
import com.example.withu.dto.ShelterMemberDTO;
import com.example.withu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    //일반사용자 회원가입
    @Override
    public void registergenMember(MemberDTO memberDTO, GenMemberDTO genMemberDTO) {
        memberDao.registMember(memberDTO);
        memberDao.registergenMember(genMemberDTO);
    }

    //보호소사용자 회원가입
    @Override
    public void registershelterMember(MemberDTO memberDTO, ShelterMemberDTO shelterMemberDTO) {
        memberDao.registMember(memberDTO);
        memberDao.registershelterMember(shelterMemberDTO);
    }

    @Override
    public int memberlogin(MemberDTO memberDTO) {
        return memberDao.memberlogin(memberDTO);
    }

    @Override
    public int membertypecheck(String email) {
        return memberDao.membertypecheck(email);
    }

    @Override
    public GenMemberDTO getGenMemberInfo(String email) {
        return memberDao.getGenMemberInfo(email);
    }

    @Override
    public ShelterMemberDTO getShelterMemberInfo(String email) {
        return memberDao.getShelterMemberInfo(email);
    }

    @Override
    public List<ShelterMemberDTO> showshleterAll() {
        return memberDao.showshleterAll();
    }


    /*
    테스트
    @Autowired
    private MemberDao dao;

    @Override
    public List<MemberModel> printMember(){
        List<MemberModel> member=dao.getMember();
        return member;
    }

    @Override
    public void setMember(MemberModel member) {
        dao.setMember(member);
    }

    @Override
    public void deleteMember(int id) {
        dao.deleteMember(id);
    }

    @Override
    public void updateMember(int id, String name) {
        dao.updateMember(id, name);
    }
    }*/
}
