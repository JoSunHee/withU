package com.example.withu.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommunityPostDTO {
    private int postno;
    private String useremail;
    private int boardno;
    private String posttitle;
    private String nickname;
    //private String posttitle;
    private String postcontent;
    private String postdate;
    private String postdelete;

}
