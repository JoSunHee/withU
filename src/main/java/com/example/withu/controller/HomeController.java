package com.example.withu.controller;

import com.example.withu.dao.CommunityPostDAO;
import com.example.withu.dto.CommunityPostDTO;
import com.example.withu.service.CommunityPostService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private SqlSession session;
    private CommunityPostDAO dao;

    @Autowired
    CommunityPostService postService;

    @RequestMapping(value= "/vision")
    @ResponseBody
    public Map<String, String> androidTest(HttpServletRequest request){
        System.out.println("서버에서 안드로이드 접속 요청함");

        CommunityPostDTO postDTO=new CommunityPostDTO();
        //postDTO.setPosttitle("안녕");
        postDTO.setBoardno(1);
        postDTO.setUseremail("ddd@naver.com"); //나중에 email값 세션에서 받아서 넣기
        postDTO.setPostcontent(request.getParameter("content"));

        postService.insertpost(postDTO);

        String day="", content="";

        List<CommunityPostDTO> commudto =postService.selectPostAll(1);

        Map<String,String> result=new HashMap<String,String>();

        for(int i=0;i<commudto.size();i++) {
            day += commudto.get(i).getPostdate()+"__!__";
            content += commudto.get(i).getPostcontent()+"__!__";
        }

        System.out.println("날짜"+day);
        System.out.println("내용"+content);

        result.put("day",day);
        result.put("content",content);

        return result;
    }

    @RequestMapping(value= "/android", method = {RequestMethod.POST})
    public String androidPage(HttpServletRequest request, Model model) {
        System.out.println("서버에서 안드로이드 접속 요청함");
        try{
            String androidID = request.getParameter("id");
            String androidPW = request.getParameter("pw");
            System.out.println("안드로이드에서 받아온 id : " + androidID);
            System.out.println("안드로이드에서 받아온 pw : " + androidPW);
            model.addAttribute("android", androidID);
            return "android";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
