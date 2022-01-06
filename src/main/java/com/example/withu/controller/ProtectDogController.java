package com.example.withu.controller;

import com.example.withu.dto.CommunityPostDTO;
import com.example.withu.dto.ProtectDogDTO;
import com.example.withu.service.ProtectDogService;
import org.apache.tomcat.jni.Local;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ProtectDogController {

    @Autowired
    ProtectDogService protectDogService;

    //보호동물 목록 메인페이지
    @RequestMapping(value = "/showDogAll", produces="application/json;charset=utf-8")
    public @ResponseBody JSONArray selectpostAll(HttpServletRequest request){
        JSONObject jsonMain =new JSONObject();
        //List<CommunityPostDTO> postAll=postService.selectPostAll(boardno);
        System.out.println("동물 목록 접속중");

        //date ,place, dogkind, gender
        List<ProtectDogDTO> dogDTO=protectDogService.showDogAll();

        JSONArray jArray =new JSONArray();

        for(int i=0; i<dogDTO.size(); i++){
            ProtectDogDTO dto=dogDTO.get(i);
            JSONObject row =new JSONObject();
            int dogno=dto.getProtectdogno();
            String email=dto.getUseremail();
            String sheltername=protectDogService.findshelternamebyemail(email);


            // json객체.put("변수명",값)
            row.put("noticedate", dto.getNoticedate());     //공고날짜 시작일
            row.put("noticeend", dto.getNoticeend());       //공고날짜 종료일
            row.put("dogage",dto.getDogage());              //나이
            row.put("dogspecific",dto.getDogspecific());    //특이사항
            row.put("sheltername",sheltername);
            row.put("adoptionstate",dto.getAdoptionstate());//공고 상태(입양완료, 안락사, 공고중)
            row.put("place", dto.getPlace());               //발견장소
            row.put("dogkind", dto.getDogkind());           //품종
            row.put("gender", dto.getGender());             //성별
            row.put("protectdogno",dto.getProtectdogno());  //공고번호
            // 배열에 추가
            // json배열.add(인덱스,json객체)
            jArray.add(i,row);
        }
        // json객체에 배열을 넣음
        jsonMain.put("AnimalItem", jArray);
        return jArray;

    }

    //보호동물 등록
    @RequestMapping(value = "/registdog", method = RequestMethod.POST)
    public String registdog(HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("동물 등록중");

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formatedNow = now.format(formatter);

        LocalDate enddate=now.plusDays(14);
        String noticend= enddate.format(formatter);

        request.setCharacterEncoding("UTF-8");
        ProtectDogDTO protectDogDTO=new ProtectDogDTO();
        protectDogDTO.setNoticedate(formatedNow);
        protectDogDTO.setNoticeend(noticend);
        protectDogDTO.setPlace(request.getParameter("place"));
        protectDogDTO.setUseremail("한마음보호소");
        protectDogDTO.setDogkind(request.getParameter("dogkind"));
        protectDogDTO.setDogage(request.getParameter("dogage")==null?"1개월":request.getParameter("dogage"));
        protectDogDTO.setGender(request.getParameter("gender"));
        protectDogDTO.setNeutralization(request.getParameter("neutralization")==null?"":request.getParameter("neutralization"));;
        protectDogDTO.setDogspecific(request.getParameter("specific")==null?"":request.getParameter("specific"));

        System.out.println("date :"+protectDogDTO.getNoticedate());
        System.out.println("dogkind :"+protectDogDTO.getDogkind());
        System.out.println("gender :"+protectDogDTO.getGender());
        System.out.println("place :"+protectDogDTO.getPlace());
        System.out.println("age :"+protectDogDTO.getDogage());
        protectDogService.registdog(protectDogDTO);

        return "redirect:/showDogAll";
    }


    //보호동물 수정
    @RequestMapping(value = "/updatedog/{dogno}", method = RequestMethod.POST)
    public String updatedog(@PathVariable int dogno, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("보호동물 수정");

        request.setCharacterEncoding("UTF-8");
        ProtectDogDTO protectDogDTO=new ProtectDogDTO();
        protectDogDTO.setDogage(request.getParameter("dogage")==null?"1개월":request.getParameter("dogage"));
        protectDogDTO.setGender(request.getParameter("gender"));
        protectDogDTO.setPlace(request.getParameter("place"));
        protectDogDTO.setUseremail("한마음보호소");
        protectDogDTO.setDogspecific(request.getParameter("specific")==null?"":request.getParameter("specific"));
        protectDogDTO.setNeutralization(request.getParameter("neutralization")==null?"":request.getParameter("neutralization"));;
        protectDogDTO.setAdoptionstate(request.getParameter("adoptionstate")==null?"1개월":request.getParameter("adoptionstate"));
        protectDogDTO.setDogkind(request.getParameter("dogkind"));

        protectDogService.updatedog(protectDogDTO);

        return "redirect:/showdogdetail/"+dogno;
    }


    //보호동물 세부 페이지
    @RequestMapping(value = "/showdogdetail/{dogno}", method = RequestMethod.POST)
    public @ResponseBody JSONArray showdogdetail(@PathVariable int dogno, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("동물 세부 페이지");

        JSONObject jsonMain =new JSONObject();
        ProtectDogDTO dogDTO=protectDogService.showdogdetail(dogno);

        JSONArray jArray =new JSONArray();
        JSONObject row =new JSONObject();
        row.put("num",dogDTO.getProtectdogno());
        row.put("useremail",dogDTO.getUseremail());
        row.put("adoptionstate",dogDTO.getAdoptionstate());
        row.put("date", dogDTO.getNoticedate());
        row.put("dateend", dogDTO.getNoticeend());
        row.put("place", dogDTO.getPlace());
        row.put("dogkind", dogDTO.getDogkind());
        row.put("dogage", dogDTO.getDogage());
        row.put("gender", dogDTO.getGender());
        row.put("dogspecific", dogDTO.getDogspecific());
        row.put("neutralization", dogDTO.getNeutralization());

        jArray.add(0,row);
        // json객체에 배열을 넣음
        jsonMain.put("dogdetail", jArray);
        return jArray;
    }

    //보호동물 건강일지 작성
    @RequestMapping(value = "/writehealthcare", method = RequestMethod.POST)
    public String writehealthcare(HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("동물 건강일지 작성");

        String vaccinedate= request.getParameter("vaccinationdate")==null?"접종x":request.getParameter("vaccinationdate");
        String vaccinename=request.getParameter("vaccinationname")==null?"접종x2":request.getParameter("vaccinationname");
        String operationdate=request.getParameter("operationdate")==null?"수술x":request.getParameter("operationdate");
        String operationname=request.getParameter("operationname")==null?"수술x2":request.getParameter("operationname");
        String medicationdate=request.getParameter("medicationdate")==null?"투약x":request.getParameter("medicationdate");
        String medicationname=request.getParameter("medicationname")==null?"투약x2":request.getParameter("medicationname");
        String dogno=request.getParameter("protectdogno");
        System.out.println("vaccinedate: "+vaccinedate);        //접종일
        System.out.println("vaccinationname :"+vaccinename);    //접종명
        System.out.println("operationdate :"+operationdate);    //수술일
        System.out.println("operationname :"+operationname);    //수술명
        System.out.println("medicationdate :"+medicationdate);  //투약일
        System.out.println("medicationname :"+medicationname);  //약품명
        System.out.println("protectdogno :"+dogno);             //강아지 공고번호

        return "redirect:/showdogdetail/"+dogno;
    }
}