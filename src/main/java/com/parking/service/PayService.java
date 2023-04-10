package com.parking.service;

import com.parking.entity.CarBill;
import com.parking.mapper.CarBillMapper;
import com.parking.mapper.PayMapper;
import com.parking.mapper.userInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class PayService {
@Autowired
    private PayMapper payMapper;
@Autowired
    private CarBillMapper carBillMapper;
@Autowired
private  CarBillService carBillService;
@Autowired
private userInfoMapper userInfoMapper;

    //支付
    public void pay(String phone,int id,String pay_money,String point) throws ParseException {
        CarBill IdCar_bill =  carBillMapper.getIdCarBill(id);
        String pay_time=carBillService.getCurrentTime();
        String over_time=String.valueOf(carBillService.countTime(IdCar_bill.getEnter_time(),pay_time));
        int zhongjian_point=Integer.parseInt(point)+Integer.parseInt(userInfoMapper.getUserPoint(phone));
        String fin_point=String.valueOf(zhongjian_point);
        if (IdCar_bill.getOwner().equals(phone)){
        payMapper.pay(phone,pay_time,over_time,1,id,pay_money);
            userInfoMapper.updatePoint(phone,fin_point);}
        else{
            payMapper.pay(phone, pay_time, over_time, 2, id, pay_money);
            userInfoMapper.updatePoint(phone, fin_point);
        }
    }
    //找出超时订单
    public CarBill getTimeOutCarBill(int id){
        return carBillMapper.getTimeOutCarBill(id);
    }

    //用积分支付
    public void PayForPoint(String phone,int id,String pay_money,String point) throws ParseException {
        CarBill IdCar_bill =  carBillMapper.getIdCarBill(id);
        String pay_time=carBillService.getCurrentTime();
        String over_time=String.valueOf(carBillService.countTime(IdCar_bill.getEnter_time(),pay_time));
        int zhongjian_point=(int) (Integer.parseInt(point)/50*5-Float.parseFloat(pay_money))+Integer.parseInt(userInfoMapper.getUserPoint(phone));
        String fin_point=String.valueOf(zhongjian_point);
        if (IdCar_bill.getOwner().equals(phone)){
            payMapper.pay(phone,pay_time,over_time,1,id,"0");
            userInfoMapper.updatePoint(phone,fin_point);}
        else{
            payMapper.pay(phone, pay_time, over_time, 2, id, "0");
            userInfoMapper.updatePoint(phone, fin_point);
        }
    }
}
