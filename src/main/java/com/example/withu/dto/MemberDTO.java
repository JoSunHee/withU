package com.example.withu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private String email;
    private String password;
    private int mileage;
    private String registrant;
    private  String registdate;
    private String updater;
    private String updatedate;
    private String userdelete;
    private int type;
}
