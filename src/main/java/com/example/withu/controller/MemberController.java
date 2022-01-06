package com.example.withu.controller;

import com.example.withu.dto.CommunityPostDTO;
import com.example.withu.dto.GenMemberDTO;
import com.example.withu.dto.MemberDTO;
import com.example.withu.dto.ShelterMemberDTO;
import com.example.withu.service.MemberService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    //회원가입
/*    @RequestMapping(value = "/registmember", method = RequestMethod.POST)
    public String memberregister(HttpServletRequest request) throws UnsupportedEncodingException{
        //type으로 일반/보호소 구별 일반은 1, 보호소는 2
        //일반 받아야 하는 값: email(S), password(S), nickname useraddress, userphone
        //보호소 받아야 하는 값: email(S), password(S), sheltername, userphone, shelterlocation, perationtime, profile
        System.out.println("회원가입 시도중");
        request.setCharacterEncoding("UTF-8");
        int type=Integer.parseInt(request.getParameter("type"));
        MemberDTO memberDTO=new MemberDTO();

        memberDTO.setType(type);
        memberDTO.setEmail(request.getParameter("email"));  //아이디 중복검사x
        memberDTO.setPassword(request.getParameter("password"));

        if(type==1){        //일반사용자
            GenMemberDTO genMemberDTO=new GenMemberDTO();
            genMemberDTO.setNickname(request.getParameter("nickname"));
            genMemberDTO.setUserphone(request.getParameter("userphone")==null?"":request.getParameter("userphone"));
            genMemberDTO.setUseraddress(request.getParameter("useraddress")==null?"":request.getParameter("useraddress"));
            genMemberDTO.setEmail(request.getParameter("email"));
            memberService.registergenMember(memberDTO, genMemberDTO);
        } else{             //보호소 사용자
            ShelterMemberDTO shelterMemberDTO=new ShelterMemberDTO();
            shelterMemberDTO.setSheltername(request.getParameter("sheltername"));
            shelterMemberDTO.setUserphone(request.getParameter("userphone")==null?"":request.getParameter("userphone"));
            shelterMemberDTO.setShelterlocation(request.getParameter("shelterlocation"));
            shelterMemberDTO.setOperationtime(request.getParameter("operationtime")==null?"":request.getParameter("operationtime"));
            shelterMemberDTO.setProfile(request.getParameter("profile")==null?"":request.getParameter("profile"));
            shelterMemberDTO.setEmail(request.getParameter("email"));
            memberService.registershelterMember(memberDTO, shelterMemberDTO);
        }

        return "1";

    }*/

    @RequestMapping(value = "/registmember", method = RequestMethod.POST)
    public @ResponseBody JSONObject memberregister(HttpServletRequest request) throws UnsupportedEncodingException{
        //type으로 일반/보호소 구별 일반은 1, 보호소는 2
        //일반 받아야 하는 값: email(S), password(S), nickname useraddress, userphone
        //보호소 받아야 하는 값: email(S), password(S), sheltername, userphone, shelterlocation, perationtime, profile
        System.out.println("회원가입 시도중");
        request.setCharacterEncoding("UTF-8");
        int type=Integer.parseInt(request.getParameter("type"));
        MemberDTO memberDTO=new MemberDTO();

        memberDTO.setType(type);
        memberDTO.setEmail(request.getParameter("email"));  //아이디 중복검사x
        memberDTO.setPassword(request.getParameter("password"));

        int joincheck=-100;

        if(type==1){        //일반사용자
            GenMemberDTO genMemberDTO=new GenMemberDTO();
            genMemberDTO.setNickname(request.getParameter("nickname"));
            genMemberDTO.setUserphone(request.getParameter("userphone")==null?"":request.getParameter("userphone"));
            genMemberDTO.setUseraddress(request.getParameter("useraddress")==null?"":request.getParameter("useraddress"));
            genMemberDTO.setEmail(request.getParameter("email"));
            memberService.registergenMember(memberDTO, genMemberDTO);
            joincheck=1;
        } else{             //보호소 사용자
            ShelterMemberDTO shelterMemberDTO=new ShelterMemberDTO();
            shelterMemberDTO.setSheltername(request.getParameter("sheltername"));
            shelterMemberDTO.setUserphone(request.getParameter("userphone")==null?"":request.getParameter("userphone"));
            shelterMemberDTO.setShelterlocation(request.getParameter("shelterlocation"));
            shelterMemberDTO.setOperationtime(request.getParameter("operationtime")==null?"":request.getParameter("operationtime"));
            shelterMemberDTO.setProfile(request.getParameter("profile")==null?"":request.getParameter("profile"));
            shelterMemberDTO.setEmail(request.getParameter("email"));
            memberService.registershelterMember(memberDTO, shelterMemberDTO);
            joincheck=1;
        }
        JSONObject jsonMain =new JSONObject();
        JSONArray jArray =new JSONArray();

            JSONObject row =new JSONObject();
            // json객체.put("변수명",값)
            row.put("check", joincheck);
            //row.put("timestamp", dto.getPostdate());
            // 배열에 추가
            // json배열.add(인덱스,json객체)
            jArray.add(0,row);

        // json객체에 배열을 넣음
        jsonMain.put("joincheck", jArray);
        return jsonMain;
    }

    //로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody JSONObject memberlogin(HttpServletRequest request) throws UnsupportedEncodingException{
        System.out.println("로그인 접속");
        request.setCharacterEncoding("UTF-8");
        String email=request.getParameter("email");
        String password=request.getParameter("password");

        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setEmail(email);
        memberDTO.setPassword(password);
        int check= memberService.memberlogin(memberDTO);
        JSONObject jsonMain =new JSONObject();
        if(check==1){   //로그인 성공시
            int type=memberService.membertypecheck(email);
            if(type==1){    //일반사용자가 로그인 했을 시 클라에게 보내줄 값
                GenMemberDTO genMemberDTO=memberService.getGenMemberInfo(email);
                jsonMain.put("nickname",genMemberDTO.getNickname());
                jsonMain.put("userphone",genMemberDTO.getUserphone());
                jsonMain.put("useraddress",genMemberDTO.getUseraddress());
                jsonMain.put("email",genMemberDTO.getEmail());
            } else {        //보호소 사용자가 로그인 했을 시 클라에게 보내줄 값

            }
            System.out.println("type: "+type);
            jsonMain.put("check", 1);
            return jsonMain;
        } else{
            return null;
            //jsonMain.put("check", -100);
        }

        // json객체에 배열을 넣음
    }





    /*//테스트
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "addMember";
    }

    //테스트
    @RequestMapping("/list")
    public String list(Model model){

        List<MemberModel> member=memberService.printMember();
        model.addAttribute("memberList",member);
        return "list";
    }

    //테스트
    @RequestMapping("/addMember")
    public String addMember(Model model) {
        return "addMember";
    }

    //테스트
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView insert(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        MemberModel member = new MemberModel();
        member.setId(Integer.parseInt(request.getParameter("id")));
        member.setName((String)request.getParameter("name"));
        member.setNickname((String)request.getParameter("nickname"));
        member.setPosition((String)request.getParameter("position"));

        memberService.setMember(member);
        ModelAndView result = new ModelAndView("redirect:/list");

        return result;
    }

    //테스트
    @RequestMapping(value="/deleteMember")
    public String deleteMember(){
        return "deleteMember";
    }

    //테스트
    @RequestMapping(value = "delete")
    public ModelAndView delete(HttpServletRequest request) throws UnsupportedEncodingException{
        MemberModel member = new MemberModel();
        member.setId(Integer.parseInt(request.getParameter("id")));

        memberService.deleteMember(member.getId());
        ModelAndView result = new ModelAndView("redirect:/list");

        return result;
    }

    //테스트
    @RequestMapping(value="/updateMember")
    public String updateMember(){
        return "updateMember";
    }

    //테스트
    @RequestMapping(value = "update")
    public ModelAndView update(HttpServletRequest request) throws UnsupportedEncodingException{
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("aftername");

        memberService.updateMember(id, name);
        System.out.println(name);
        System.out.println(id);
        ModelAndView result = new ModelAndView("redirect:/list");

        return result;
    }*/


}
