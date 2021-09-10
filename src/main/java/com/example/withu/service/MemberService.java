package com.example.withu.service;

import com.example.withu.model.MemberModel;

import java.util.List;

public interface MemberService {
    List<MemberModel> printMember();
    void setMember(MemberModel member);
    void deleteMember(int id);
    void updateMember(int id, String name);
}
