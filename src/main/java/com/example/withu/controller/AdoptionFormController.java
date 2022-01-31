package com.example.withu.controller;

import com.example.withu.dto.AdoptionFromDTO;
import com.example.withu.dto.CommunityPostDTO;
import com.example.withu.service.AdoptionFormService;
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
import java.util.List;

@Controller
public class AdoptionFormController {

    @Autowired
    AdoptionFormService adoptionFormService;

    @RequestMapping(value = "/selectformlist/{sheltername}", produces="application/json;charset=utf-8")
    public @ResponseBody JSONArray selectformlist(@PathVariable String sheltername, HttpServletRequest request){

        System.out.println(sheltername+" 입양확인서 목록 연결");
        List<AdoptionFromDTO> adoptionFormlist =adoptionFormService.selectformlistAll(sheltername);
        JSONArray jArray =new JSONArray();

        for(int i=0; i<adoptionFormlist.size(); i++){
            AdoptionFromDTO dto = adoptionFormlist.get(i);
            JSONObject row =new JSONObject();
            row.put("email", dto.getEmail());
            row.put("protectdogno", dto.getProtectdogno());
            row.put("sheltername", dto.getSheltername());
            row.put("adoptiontime", dto.getFormwritetime());
            //row.put("timestamp", dto.getPostdate());
            // 배열에 추가
            // json배열.add(인덱스,json객체)
            jArray.add(i,row);
        }
        return jArray;
    }

    @RequestMapping(value = "/writeAdoptionForm", method = RequestMethod.POST)
    public ModelAndView writeAdoptionForm(HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("입양확인서 작성 연결");
        request.setCharacterEncoding("UTF-8");
        AdoptionFromDTO adoptionFromDTO=new AdoptionFromDTO();
        adoptionFromDTO.setEmail(request.getParameter("email"));
        adoptionFromDTO.setProtectdogno(Integer.parseInt(request.getParameter("protectdogno")));
        adoptionFromDTO.setSheltername(request.getParameter("sheltername"));

        adoptionFormService.insertform(adoptionFromDTO);

        ModelAndView result = new ModelAndView("redirect:/selectformlist/"+request.getParameter("sheltername"));

        return result;
    }
}
