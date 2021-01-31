package com.example.demo;

import function.oder;
import org.springframework.web.bind.annotation.*;
import function.*;

@RestController
public class HelloController {
    @CrossOrigin
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String[] hello() {
        String[]list = new String[4];
        list[0] = "q";
        list[1] = "w";
        list[2] = "e";
        list[3] = "r";
        return list;
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/req")
    public int login(int a, String pw) {
        spring_function sp = new spring_function();
        int k = sp.find_user(a,pw);
        return k;
    }

    @CrossOrigin
    @GetMapping(value = "/demo/param/order/{a}")
    public String[] find_order(@PathVariable int a) {
        spring_function sp = new spring_function();
        return sp.find_order(a);

    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/find_train")
    public String[] find_train(int a,String depart_station,String arrival_station) {
        spring_function sp = new spring_function();
        return sp.find_train(depart_station,arrival_station,a);
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/train_detail")
    public String[] train_detail(String train_num,String depart_station,String arrival_station) {
        spring_function sp = new spring_function();
        return sp.train_detail(train_num,depart_station,arrival_station);
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/delete_order/{a}")
    public void delete_order(@PathVariable int a) {
        spring_function sp = new spring_function();
        sp.delete_order(a);
    }
    @CrossOrigin
    @RequestMapping(value = "/demo/param/add_user")
    public void add_user(int a,String b,String c,String d) {
        spring_function sp = new spring_function();
        sp.add_user(a,b,c,d);
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/buy")
    public void buy_ticket(int user_id,String train_num,int type,String station_depart,String station_arrival) {
        spring_function sp = new spring_function();
        sp.buy_ticket(user_id,train_num,type,station_depart,station_arrival);
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/list_user")
    public String[] list_user() {
        spring_function sp = new spring_function();
        return sp.list_user();
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/switch_user/{a}")
    public void switch_user(@PathVariable int a) {
        spring_function sp = new spring_function();
        sp.switch_user(a);
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/delete_user/{a}")
    public void delete_user(@PathVariable int a) {
        spring_function sp = new spring_function();
        sp.delete_user(a);
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/list_order")
    public String[] list_order() {
        spring_function sp = new spring_function();
        return sp.list_order();
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/delete_train/{a}")
    public void delete_train(@PathVariable String a) {
        spring_function sp = new spring_function();
        sp.delete_train(a);
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/delete_station/{a}")
    public void delete_station(@PathVariable String a) {
        spring_function sp = new spring_function();
        sp.delete_station(a);
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/add_train")
    public void add_train(String train_name,
                          String depart_station,
                          String arrival_station,
                          String depart_time,
                          String arrival_time,
                          String train_type,
                          int service_type) {
        spring_function sp = new spring_function();
        sp.add_train(train_name,depart_station,arrival_station,depart_time,arrival_time,train_type,service_type);
    }

    @CrossOrigin
    @RequestMapping(value = "/demo/param/add_station")
    public void add_station(int station_id,
                            String station_name,
                            String station_code,
                            String station_pinyin,
                            String station_letter,
                            String station_pinyin_code,
                            double station_longitude,
                            double station_latitude) {
        spring_function sp = new spring_function();
        sp.add_station(station_id,station_name,station_code,station_pinyin,station_letter,station_pinyin_code,station_longitude,station_latitude);
    }

    @CrossOrigin
    @GetMapping(value = "/demo/param/fix")
    public String find_order( String a,String b) {
        spring_function sp = new spring_function();
        return sp.fix(a,b);

    }



}

