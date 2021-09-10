package com.example.withu.controller;

import com.example.withu.model.MemberModel;
import com.example.withu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    //테스트
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
    }


}
