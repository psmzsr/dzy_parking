package com.parking.controller_H5;

import com.parking.entity.CarBill;
import com.parking.service.CarBillService;
import com.parking.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
public class PayController_H5 {
    @Autowired
    private PayService payService;
    @Autowired
    private CarBillService carBillService;
    @RequestMapping("pay_caozuo_H5")
    public void payCaoZuoH5(HttpServletRequest request) throws ParseException {
        System.out.println("进入payCaoZuoH5");
        int id=Integer.parseInt(request.getParameter("id"));
        String phone=request.getParameter("phone");
//        String password=request.getParameter("password");
//        String car_number=request.getParameter("car_number");
        String pay_money=request.getParameter("pay_money");
        String point=String.valueOf((int)Float.parseFloat(pay_money));
        int pay_flag=Integer. parseInt(request.getParameter("pay_flag"));
//        System.out.println("phone:"+phone+"password:"+password);
        //积分付款
        String pay_point =request.getParameter("pay_point");
        if (pay_point!=null){
            payService.PayForPoint(phone,id,pay_money,point);
//            return "redirect:user_check_login"+"?phone="+phone+"&"+"password="+password;
        }
        if (pay_flag==0) {
            payService.pay(phone, id, pay_money, point);
        }else if (pay_flag==3){
            CarBill carBill=payService.getTimeOutCarBill(id);
            System.out.println();
            String fin_money=String.valueOf(Float.parseFloat(pay_money)+Float.parseFloat(carBill.getPay_money()));

            payService.pay(phone,id,fin_money,point);
        }

//        return "redirect:user_check_login"+"?phone="+phone+"&"+"password="+password;
    }
    //代付时找到车牌的id
    @RequestMapping("find_car_bill_id_H5")
    public int find_car_bill_id_H5(HttpServletRequest request){
//        String phone =request.getParameter("phone");
        String city =request.getParameter("city");
        String area =request.getParameter("area");
        String number =request.getParameter("number");
        String point="·";
        System.out.println(city+area+point+number);
        String car_number=city+area+point+number;
        List<CarBill> carBills=carBillService.getCar_number_List(car_number);
        for (int i=0;i<carBills.size();i++){
            if (carBills.get(i).getLeave_time()==null ||carBills.get(i).getPay_flag()==0){
                System.out.println(carBills.get(i).getEnter_time());
                int id=carBills.get(i).getId();
                return id;
            }
        }
        return 0;
    }
}
