package com.example.withu.service.impl;

import com.example.withu.dao.CommunityPostDAO;
import com.example.withu.dto.CommunityPostDTO;
import com.example.withu.service.CommunityPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityPostServiceImpl implements CommunityPostService {

    @Autowired
    private CommunityPostDAO postDAO;

    @Override
    public List<CommunityPostDTO> selectPostAll(int boardno) {
        List<CommunityPostDTO> postAll=postDAO.selectPostAll(boardno);
        return postAll;
    }

    @Override
    public void insertpost(CommunityPostDTO postDTO) {
        postDAO.insertpost(postDTO);
    }

    @Override
    public void updatepost(CommunityPostDTO postDTO) {
        postDAO.updatepost(postDTO);
    }
}
