package com.example.withu.service;

import com.example.withu.dto.CommunityPostDTO;

import java.util.List;

public interface CommunityPostService {
    List<CommunityPostDTO> selectPostAll(int boardno);
    void insertpost(CommunityPostDTO postDTO);
    void updatepost(CommunityPostDTO postDTO);
}
