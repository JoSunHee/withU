package com.example.withu.service.impl;

import com.example.withu.dao.MemberDao;
import com.example.withu.model.MemberModel;
import com.example.withu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
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


}
