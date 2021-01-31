package com.example.demo;

import function.data_factory;
import function.jdbc;
import function.oder;
import function.*;

public class spring_function {
    public int find_user(int user_id,String pw){
        jdbc dm = new data_factory().createDataManipulation();
        int cont = dm.findUser(user_id,pw);
        Boolean is_ad = false;
        if (cont==0){
            return 0;
        }else {
            is_ad = dm.is_administrator(user_id);
        }
        if (is_ad){
            return 2;
        }
        return 1;
    }

    public String[] find_order(int user_id){
        jdbc dm = new data_factory().createDataManipulation();
        int x = dm.findOderNum(user_id);
        oder[]oders = new oder[x];
        oders = dm.findOder(user_id,x);
        String[]str_order = new String[7*x];
        for (int i=0;i<x;i++){
            str_order[i*7] = String.valueOf(oders[i].getOder_id());
            str_order[i*7+1] = String.valueOf(oders[i].getPrice());
            str_order[i*7+2] = String.valueOf(oders[i].getDate());
            str_order[i*7+3] = String.valueOf(oders[i].getTrain_num());
            str_order[i*7+4] = String.valueOf(oders[i].getStart_station());
            str_order[i*7+5] = String.valueOf(oders[i].getStop_station());
            str_order[i*7+6] = String.valueOf(oders[i].getSeat_type());

        }
        return str_order;
    }

    public void delete_order(int order_id){
        jdbc dm = new data_factory().createDataManipulation();
        dm.delete_order_fully(order_id);
    }

    public String[] find_train(String start_station,String stop_station,int way){
        jdbc dm = new data_factory().createDataManipulation();
        return dm.gettrain_string(start_station,stop_station,way);
    }

    public String[] train_detail(String train_num,String station_depart,String staion_arrival){
        jdbc dm = new data_factory().createDataManipulation();
        return dm.detail_string(train_num,station_depart,staion_arrival);
    }

    public void buy_ticket(int user_id,String train_num,int type,String station_depart,String staion_arrival){
        jdbc dm = new data_factory().createDataManipulation();
         dm.buy_ticket(user_id,type,train_num,station_depart,staion_arrival);
    }
    public String[] list_user(){
        jdbc dm = new data_factory().createDataManipulation();
        return dm.return_user();
    }
    public void switch_user(int user_id){
        jdbc dm = new data_factory().createDataManipulation();
        dm.switch_privilege(user_id);
    }

    public void delete_user(int user_id){
        jdbc dm = new data_factory().createDataManipulation();
        dm.deleteUser(user_id);
    }

    public String[] list_order(){
        jdbc dm = new data_factory().createDataManipulation();
        return dm.return_order();
    }

    public void delete_train(String train_name){
        jdbc dm = new data_factory().createDataManipulation();
        dm.delete_train(train_name);
    }

    public void add_train(String train_name,
                          String depart_station,
                          String arrival_station,
                          String depart_time,
                          String arrival_time,
                          String train_type,
                          int service_type){
        jdbc dm = new data_factory().createDataManipulation();
        dm.add_train(train_name,depart_station,arrival_station,depart_time,arrival_time,train_type,service_type);
    }

    public void add_station(int station_id,
                            String station_name,
                            String station_code,
                            String station_pinyin,
                            String station_letter,
                            String station_pinyin_code,
                            double station_longitude,
                            double station_latitude){
        jdbc dm = new data_factory().createDataManipulation();
        dm.add_station(station_id,station_name,station_code,station_pinyin,station_letter,station_pinyin_code,station_longitude,station_latitude);
    }

    public void delete_station(String station_name){
        jdbc dm = new data_factory().createDataManipulation();
        dm.delete_station(station_name);
    }

    public String fix(String station_name,String train_name){
        jdbc dm = new data_factory().createDataManipulation();
        return dm.station_name_fix(station_name,train_name);
    }
    public void add_user(int a,String b,String c,String d){
        jdbc dm = new data_factory().createDataManipulation();
        dm.addUser(a,b,c,d);
    }






}
