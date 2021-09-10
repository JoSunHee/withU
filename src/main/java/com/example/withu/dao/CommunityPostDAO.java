package com.example.withu.dao;

import com.example.withu.dto.CommunityPostDTO;

import java.util.List;

public interface CommunityPostDAO {
    List<CommunityPostDTO> selectPostAll(int boardno);
    void insertpost(CommunityPostDTO postDTO);
    void updatepost(CommunityPostDTO postDTO);
}
