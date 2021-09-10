package com.example.withu.dao;

import com.example.withu.model.MemberModel;

import java.util.List;

public interface MemberDao {
    List<MemberModel> getMember();
    void setMember(MemberModel member);
    void deleteMember(int id);
    void updateMember(int id, String name);
}
