package com.parking.service;

import com.parking.entity.CarBill;
import com.parking.entity.UserCar;
import com.parking.mapper.CarBillMapper;
import com.parking.mapper.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CarBillService {
    @Autowired
    private CarBillMapper carBillMapper;
    @Autowired
    private UserCarService userCarService;
    @Autowired
    private PayMapper payMapper;
    //车辆进入
    public void carEnter(String car_number,String date){
        String phone="";
//        System.out.println(userCarService.getCar_Phone(car_number));
        if (userCarService.getCar_Phone(car_number)!=null){
            phone =userCarService.getCar_Phone(car_number);
        }
//        System.out.println("car_phone:"+phone);
//        System.out.println("-------------------------");
        carBillMapper.carEnter(car_number,date,phone);
    }
    //车辆离场
    public void carLeave(String leave_time,String car_number) throws ParseException {
        String date_enter="";
        List<CarBill> carBills=carBillMapper.zhi_ding_numberInfo(car_number);
        for (int i=0;i<carBills.size();i++){
            if (carBills.get(i).getLeave_time()==null){
                date_enter=carBills.get(i).getEnter_time();
                break;
            }
        }
        String date_leave =leave_time;
        String count_time =String.valueOf(countTime(date_enter,date_leave));
        carBillMapper.carLeave(leave_time,count_time,car_number,date_enter);
        //计算缴费金额
        float time =countTime(date_enter,date_leave);
        float pay_money;
//        System.out.println("time:"+time);
        if(time<=0.5){
            pay_money=0;
        }else
        pay_money=Float.parseFloat(String.format("%.2f", time*5));
        carBillMapper.countPayMoney(car_number,date_enter,pay_money);
    }
    //计算用户现在当前时间所需缴费金额
    public void carNowShouldPayMoney(String phone) throws ParseException {
//        System.out.println("carNowShouldPayMoney进入");
        String date_enter="";
        List<CarBill> noPayCarbill=carBillMapper.noPayCarBill(phone);
        for (int i = 0 ;i<noPayCarbill.size();i++){
            if (noPayCarbill.get(i).getLeave_time()==null){
                String car_number=noPayCarbill.get(i).getCar_number();
                 date_enter=noPayCarbill.get(i).getEnter_time();
                String date_leave =getCurrentTime();
                //计算缴费金额
                float time =countTime(date_enter,date_leave);
                System.out.println(time);
                float pay_money;
//                System.out.println("time:"+time);
                if(time<=0.5){
                    pay_money=0;
                }else
                    pay_money=Float.parseFloat(String.format("%.2f", time*5));
                carBillMapper.countPayMoney(car_number,date_enter,pay_money);
//                System.out.println("更新成功:"+pay_money);
            }
        }
    }


    //获取用户下的车牌账单信息
    public List<CarBill> userAllCarInfo(String phone){
        List<CarBill> carBills = carBillMapper.userAllCarInfo(phone);
        return carBills;
    }


    //检测用户支付了但是没有离场，并且超过三十分钟
    public void CheckTimeOut(String Phone) throws ParseException {
        System.out.println("进入CheckTimeOut");
        String current_time=getCurrentTime();
        List<CarBill> carBills=carBillMapper.PayCarBill(Phone);
//        System.out.println(carBills.get(4).getEnter_time());
        for (int i=0;i<carBills.size();i++){
            if (carBills.get(i).getLeave_time()==null){
                int id=carBills.get(i).getId();
                System.out.println("是这个id:"+id);
                float over_time=Float.parseFloat(carBills.get(i).getOver_time());
                System.out.println("over_time:"+over_time);
                String enter_time=carBills.get(i).getEnter_time();
                System.out.println("enter_time"+enter_time);
                float now_time=countTime(enter_time,current_time);
//                System.out.println("now_time"+now_time);
                if (now_time-over_time>0.5){
                    System.out.println("车辆已超时");
                    double the_time=now_time-0.5;
                    String over_money=String.format("%.2f", the_time*5);
                    payMapper.timeOut(String.valueOf(now_time),over_money,id);
                }
            }
        }
    }

    public List<CarBill> getCar_number_List(String car_number){
        return carBillMapper.getCar_number_List(car_number);
    }


    //分页查询用户牌照账单
    public List<CarBill> getUserAllCarFenYe(String phone,int current_page,int page_size){
        int currentPos=(current_page-1)*page_size;
        return carBillMapper.getUserAllCarFenYe(phone,currentPos,page_size);
    }




    //获得当前时间
    public String getCurrentTime() {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
    //计算时长
    public float countTime(String enter_time,String leave_time) throws ParseException {
        Date date_enter=null;
        Date date_leave=null;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date_enter=format.parse(enter_time);
        date_leave=format.parse(leave_time);
        float time =date_leave.getTime()-date_enter.getTime();
        return time/(3600*1000);
    }
    //通过查找ID获取指定的账单
    public CarBill getIdCarBill(String id){
        return carBillMapper.getIdCarBill(Integer.parseInt(id));
    }
}
