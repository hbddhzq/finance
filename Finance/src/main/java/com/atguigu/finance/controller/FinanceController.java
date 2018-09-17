package com.atguigu.finance.controller;

import com.atguigu.finance.bean.NplmLoanContract;
import com.atguigu.finance.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FinanceController {

    @Autowired
    private ManageService manageService;

    @RequestMapping("index")
    public String index(){
        //System.out.println("hello");
        //testService.testssm();
        return "smp";
    }

    @RequestMapping("contractList")
    public String contractList(HttpServletRequest request){
        List<NplmLoanContract> loanContractList = manageService.contractList();
        request.setAttribute("loanContractList",loanContractList);
        return "contractList";
    }




}
