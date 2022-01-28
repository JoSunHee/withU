package com.example.withu.controller;

import com.example.withu.dto.CommunityPostDTO;
import com.example.withu.service.CommunityPostService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    CommunityPostService postService;

    @RequestMapping(value = "/selectpostAll/{boardno}", produces="application/json;charset=utf-8")
    public @ResponseBody JSONArray selectpostAll(@PathVariable int boardno, HttpServletRequest request){
        JSONObject jsonMain =new JSONObject();
        //List<CommunityPostDTO> postAll=postService.selectPostAll(boardno);
        System.out.println("커뮤니티 게시글 목록 연결됨");
        List<CommunityPostDTO> commudto =postService.selectPostAll(1);
        JSONArray jArray =new JSONArray();

        for(int i=0; i<commudto.size(); i++){
            CommunityPostDTO dto = commudto.get(i);
            JSONObject row =new JSONObject();
            // json객체.put("변수명",값)
            row.put("content", dto.getPostcontent());
            //row.put("timestamp", dto.getPostdate());
            // 배열에 추가
            // json배열.add(인덱스,json객체)
            jArray.add(i,row);
        }
        // json객체에 배열을 넣음
        jsonMain.put("MyItem", jArray);
        return jArray;

    }


    //모든 게시글 보기
    /*@RequestMapping(value = "/selectpostAll/{boardno}")
    public Map<String, String> selectpostAll(@PathVariable int boardno, HttpServletRequest request){
        List<CommunityPostDTO> postAll=postService.selectPostAll(boardno);

        List<CommunityPostDTO> commudto =postService.selectPostAll(1);

        Map<String,String> result=new HashMap<String,String>();
        String day="", content="";

        for(int i=0;i<commudto.size();i++) {
            day += commudto.get(i).getPostdate()+"__!__";
            content += commudto.get(i).getPostcontent()+"__!__";
        }

        System.out.println("날짜"+day);
        System.out.println("내용"+content);

        result.put("day",day);
        result.put("content",content);

        return result;
    }*/

    //게시글 작성
    @RequestMapping(value = "/insertpost", method = RequestMethod.POST)
    public ModelAndView insert(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        CommunityPostDTO postDTO=new CommunityPostDTO();
        //postDTO.setPosttitle("안녕"); //title받기
        postDTO.setBoardno(1);  //boardno받기
        postDTO.setUseremail("ddd@naver.com"); //나중에 email값 세션에서 받아서 넣기
        postDTO.setPostcontent(request.getParameter("content"));

        //작성 게시글 db에 넣기
        postService.insertpost(postDTO);

        //boardno에 따른 모든 게시글 받아오기
        ModelAndView result = new ModelAndView("redirect:/selectpostAll/1");

        return result;
    }

    //게시글 수정
    @RequestMapping(value = "/updatepost")
    public ModelAndView updatepost(HttpServletRequest request) throws UnsupportedEncodingException{
        CommunityPostDTO postDTO=new CommunityPostDTO();
        postDTO.setPostno(Integer.parseInt(request.getParameter("postno")));
        //postDTO.setPosttitle(request.getParameter("posttitle"));
        postDTO.setPostcontent(request.getParameter("postcontent"));

        postService.updatepost(postDTO);
        ModelAndView result = new ModelAndView("redirect:/list");   //jsp를 위한 것. 다음 페이지 어디로 갈지 상의

        return result;
    }

    //게시글 삭제
    @RequestMapping(value = "/deletepost/{postno}")
    public String deletepost(@PathVariable int postno) {

        return "list";
    }
    
}
