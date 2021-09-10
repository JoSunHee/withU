package com.example.withu.controller;

import com.example.withu.dto.CommunityPostDTO;
import com.example.withu.model.MemberModel;
import com.example.withu.service.CommunityPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    //모든 게시글 보기
    @RequestMapping(value = "/selectpostAll/{boardno}")
    public String selectpostAll(@PathVariable int boardno, Model model){
        List<CommunityPostDTO> postAll=postService.selectPostAll(boardno);
        model.addAttribute("postlist",postAll); //jsp를 위한 model. model에 저장하지 말고 안드로이드 스튜디오로 list를 넘겨야됨

        return "list";
    }

    //게시글 작성
    @RequestMapping(value = "/insertpost", method = RequestMethod.POST)
    public ModelAndView insert(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        CommunityPostDTO postDTO=new CommunityPostDTO();
        postDTO.setBoardno(Integer.parseInt(request.getParameter("boardno")));
        postDTO.setUseremail("ddd@naver.com"); //나중에 email값 세션에서 받아서 넣기
        postDTO.setPosttitle(request.getParameter("posttitle"));
        postDTO.setPostcontent(request.getParameter("postcontent"));

        postService.insertpost(postDTO);
        ModelAndView result = new ModelAndView("redirect:/list");   //jsp를 위한 것. 다음 페이지 어디로 갈지 상의

        return result;
    }

    //게시글 수정
    @RequestMapping(value = "/updatepost")
    public ModelAndView updatepost(HttpServletRequest request) throws UnsupportedEncodingException{
        CommunityPostDTO postDTO=new CommunityPostDTO();
        postDTO.setPostno(Integer.parseInt(request.getParameter("postno")));
        postDTO.setPosttitle(request.getParameter("posttitle"));
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
