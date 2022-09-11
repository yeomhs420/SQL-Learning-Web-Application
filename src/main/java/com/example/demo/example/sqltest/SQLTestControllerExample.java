package com.example.demo.example.sqltest;

import com.example.demo.service.test.SQLResultService;
import com.example.demo.validator.SQLValidator;
import com.example.demo.vo.SQLData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


public class SQLTestControllerExample {

    @Autowired SQLResultService sqlResultService;

    @GetMapping("/test")
    public String testForm() {
        return "test/test_form";
    }

    @PostMapping("/test")
    public String testResult(@Valid SQLData sqlData, BindingResult result, Model model){
        System.out.println("SQL : "+ sqlData.getSql());

        if(!sqlResultService.checkKeywords(result, model)) return "error/sql_error";
        List<Map<String, Object>> resultList = sqlResultService.getResult(sqlData.getSql());
        if(!sqlResultService.checkSyntax(resultList, model)) return "error/sql_error";

        System.out.println("---------------Result----------------");
        for(int i=0;i<resultList.size();i++) System.out.println(resultList.get(i));
        return "test/test_form";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new SQLValidator());
    }
}