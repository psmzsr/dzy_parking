package com.parking.controller;

import com.parking.entity.CarBill;
import com.parking.service.CarBillService;
import com.parking.service.PayService;
import com.parking.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private userInfoService userInfoService;
    @Autowired
    private CarBillService carBillService;
    @RequestMapping("to_pay")
    public String pay(HttpServletRequest request, Model model){
        String couldUsePoint=null;
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        String car_number=request.getParameter("car_number");
        String pay_money=request.getParameter("pay_money");
        String id=request.getParameter("id");
        String pay_flag=request.getParameter("pay_flag");
        String user_point=userInfoService.getUserInfo(phone).getPoint();
//        System.out.println("-----------------");
//        System.out.println(pay_money);

        System.out.println("phone:"+phone+"car_number:"+car_number+"pay_money:"+pay_money+"id:"+id);
//        float f1 = 0.1f;
        float point =Float.parseFloat(pay_money);
        int fin_point=(int)point;
        //判断用户积分是否能够成功扣款。
        if (Integer.parseInt(user_point)/50*5>Float.parseFloat(pay_money)){
            couldUsePoint="true";
        }
        model.addAttribute("id",id);
        model.addAttribute("phone",phone);
        model.addAttribute("car_number",car_number);
        model.addAttribute("pay_money",pay_money);
        model.addAttribute("point",fin_point);
        model.addAttribute("password",password);
        model.addAttribute("pay_flag",pay_flag);
        model.addAttribute("user_point",user_point);
        model.addAttribute("couldUsePoint",couldUsePoint);
        return "pay";
    }
    @RequestMapping("pay_caozuo")
    public String payCaoZuo(HttpServletRequest request,Model model) throws ParseException {
        int id=Integer.parseInt(request.getParameter("id"));
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
//        String car_number=request.getParameter("car_number");
        String pay_money=request.getParameter("pay_money");
        String point=request.getParameter("point");
        int pay_flag=Integer. parseInt(request.getParameter("pay_flag"));
        System.out.println("phone:"+phone+"password:"+password);
        //积分付款
        String pay_point =request.getParameter("pay_point");
        if (pay_point!=null){
            payService.PayForPoint(phone,id,pay_money,point);
            return "redirect:user_check_login"+"?phone="+phone+"&"+"password="+password;
        }
        if (pay_flag==0) {
            payService.pay(phone, id, pay_money, point);
        }else if (pay_flag==3){
            CarBill carBill=payService.getTimeOutCarBill(id);
            System.out.println();
            String fin_money=String.valueOf(Float.parseFloat(pay_money)+Float.parseFloat(carBill.getPay_money()));

            payService.pay(phone,id,fin_money,point);
        }

        return "redirect:user_check_login"+"?phone="+phone+"&"+"password="+password;
    }
    @RequestMapping("jump_pay_other")
    public String PayForOther(HttpServletRequest request,Model model){
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        model.addAttribute("phone",phone);
        model.addAttribute("password",password);
        return "payforother";
    }
    //代付
    @RequestMapping("pay_other_to_pay")
    public String pay_other_to_pay(HttpServletRequest request,Model model){
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        String car_number=request.getParameter("car_number");
        System.out.println("phone:"+phone+"password:"+password+"car_number:"+car_number);
//        int point = Integer.parseInt(userInfoService.getUserInfo(phone).getPoint());
        List<CarBill> carBills=carBillService.getCar_number_List(car_number);
        for (int i=0;i<carBills.size();i++){
            if (carBills.get(i).getLeave_time()==null ||carBills.get(i).getPay_flag()==0){
                System.out.println(carBills.get(i).getEnter_time());
                int id=carBills.get(i).getId();
                String pay_money;
                int pay_flag=carBills.get(i).getPay_flag();
                if (pay_flag==3){
                    pay_money=carBills.get(i).getOver_money();
                }else {
                    pay_money = carBills.get(i).getPay_money();
                }
                return "redirect:to_pay?"+"phone="+phone+"&"+"password="+password+"&"+"id="+id+"&"+"pay_money="+pay_money+"&"+"pay_flag="+pay_flag+"&"+"car_number="+car_number;
            }
        }
        return "redirect:jump_pay_other?"+"phone="+phone+"&"+"password="+password;
    }
}
