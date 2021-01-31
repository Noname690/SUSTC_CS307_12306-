package function;

import java.io.*;
import java.util.*;
public class client {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);

        int mode = 0;
        int userid = 0;
        String password = "";

        while (true) {
            while (mode != 1) {
                System.out.println("请选择你想进行的操作（1.登录|2.注册|3.退出程序）：");
                mode = in.nextInt();
                if (mode == 1) {
                    System.out.println("输入您的账号：");
                    String id = in.next();
                    System.out.println("输入您的密码：");
                    String pw = in.next();
                    jdbc dm = new data_factory().createDataManipulation();

                    if (dm.findUser(Integer.parseInt(id), pw) == 0) {
                        System.out.println("输入错误或账号不存在");
                        mode = 0;
                    } else {
                        userid = Integer.parseInt(id);
                        password = pw;
                        System.out.println("登陆成功");
                    }
                } else if (mode == 2) {
                    System.out.println("输入您的账号：");
                    String id = in.next();
                    System.out.println("输入您的密码：");
                    String pw = in.next();
                    System.out.println("输入您的身份证号码：");
                    String id_card = in.next();
                    System.out.println("输入您的电话：");
                    String phone = in.next();
                    jdbc dm = new data_factory().createDataManipulation();
                    if (dm.findUser2(Integer.parseInt(id)) > 0) {
                        System.out.println("输入错误或账号已存在");
                    } else {
                        dm.addUser(Integer.parseInt(id), pw, id_card, phone);

                    }
                } else if (mode == 3) {
                    break;
                }else {
                    System.out.println("请输入正确的指令");
                }
            }//登录和注册流程
            if (mode==3){
                break;
            }
            jdbc dm = new data_factory().createDataManipulation();
            Boolean is_administrator = false;
            is_administrator = dm.is_administrator(userid);
            if (is_administrator) {
                System.out.println("你的账户类型为：管理员");
                while (true) {
                    System.out.println("请输入你想进行的操作(1.用户管理|2.订单管理|3.车次管理|4.车站管理|5.退出登陆)：");
                    int mode2 = in.nextInt();
                    if (mode2 == 5) {
                        break;
                    } else if (mode2 == 1) {
                        System.out.println(dm.listuser());
                        System.out.println("请输入你要操作的账号，以及操作类型（user_id + 1.权限变更|2.删除账户）");
                        int id_in=in.nextInt();
                        int type=in.nextInt();
                        if(dm.findUser2(id_in)>0){
                            if(type==1) {
                                if (dm.is_administrator(id_in)) {
                                    System.out.println("该账户类型为：管理员，是否切换为：用户 ?（1.是|0.否）");
                                } else System.out.println("该账户类型为：用户，是否切换为：管理员 ?（1.是|0.否）");
                                if (in.nextInt() == 1) {
                                    dm.switch_privilege(id_in);
                                    System.out.println("修改成功");
                                } else System.out.println("取消修改");
                            }
                            else if(type==2) {
                                System.out.println("确认删除账户"+id_in+"? （1.是|0.否）");
                                if (in.nextInt() == 1) {
                                    System.out.println("删除成功");
                                    dm.deleteUser(id_in);
                                } else System.out.println("取消删除");
                            }
                        }
                        else System.out.println("不存在该账户");
                    } else if (mode2 == 2) {
                        System.out.println(dm.listorder());
                        System.out.println("选择要操作的订单号");
                        int id_in=in.nextInt();
                        System.out.println("确认删除订单"+id_in+"? （1.是|0.否）");
                        if (in.nextInt() == 1) {
                            System.out.println("删除成功");
                            dm.deleteorder(id_in);
                        } else System.out.println("取消删除");

                    } else if (mode2 == 3) {
                        System.out.println(dm.listtrain());
                        System.out.println("选择操作类型（1.添加车次|2.删除车次）");

                        if(in.nextInt()==1){
                            System.out.println("请依次输入" +
                                    "train_name,\n" +
                                    "depart_station,\n" +
                                    "arrival_station,\n" +
                                    "depart_time,\n" +
                                    "arrival_time,\n" +
                                    "train_type,\n" +
                                    "service_type");
                            dm.add_train(in.next(),in.next(),in.next(),in.next(),in.next(),in.next(),in.nextInt());
                        }else {
                            System.out.println("输入删除车次号");
                            String name_in=in.next();
                            System.out.println("确认删除车次"+name_in+"? （1.是|0.否）");
                            if (in.nextInt() == 1) {
                                System.out.println("删除成功");
                                dm.delete_train(name_in);
                            } else System.out.println("取消删除");
                        }
                    } else if (mode2 == 4) {
                        System.out.println(dm.liststation());
                        System.out.println("选择操作类型（1.添加车站|2.删除车站）");

                        if (in.nextInt() == 1) {
                            System.out.println("请依次输入" +
                                    "station_id,\n" +
                                    "station_name,\n" +
                                    "station_code,\n" +
                                    "station_pinyin,\n" +
                                    "station_letter,\n" +
                                    "station_pinyin_code,\n" +
                                    "station_longitude,\n" +
                                    "station_latitude");
                            dm.add_station(in.nextInt(), in.next(), in.next(), in.next(), in.next(), in.next(), in.nextDouble(), in.nextDouble());
                        } else {
                            System.out.println("输入删除车站名");
                            String name_in = in.next();
                            System.out.println("确认删除车站" + name_in + "? （1.是|0.否）");
                            if (in.nextInt() == 1) {
                                System.out.println("删除成功");
                                dm.delete_station(name_in);
                            } else System.out.println("取消删除");
                        }
                    }else System.out.println("请输入正确的指令");
                }
            } else {
                System.out.println("你的账户类型为：用户");
                while (true) {
                    System.out.println("请输入你想进行的操作(1.查询车次|2.查询订单|3.退出登录)：");
                    int mode2 = in.nextInt();
                    if (mode2 == 3){
                        break;
                    }else if(mode2==1){
                        System.out.println("请输入出发地和目的地以及结果排序依据:（出发城市 到达城市 1：列车号｜2：出发时间｜3：到达时间｜4：出发站名｜5：到达站名 负数为降序）");
                        try {
                            String depart = in.next();
                            String arrival = in.next();
                            int order=in.nextInt();
                            dm = new data_factory().createDataManipulation();
                            System.out.println(dm.gettraininfo(depart, arrival,order));
                            if (dm.gettraininfo(depart, arrival,order).equals("没有列车连接两地")){
                                continue;
                            }
                            System.out.println("请输入你想察看详情的车次的列车号:");
                            String name = in.next();
                            depart = dm.station_name_fix(depart,name);
                            arrival = dm.station_name_fix(arrival,name);
                            System.out.println(dm.gettraindetail(name, depart, arrival));
                            if (dm.gettraindetail(name, depart, arrival).equals("没有此列车")){
                                continue;
                            }
                            System.out.println("请输入你想购买的座位类型:(1.商务座|2.一等座|3.二等座|4.放弃购买)");
                            int mode3 = in.nextInt();
                            if (mode3==1||mode3==2||mode3==3){
                                dm.buy_ticket(userid,mode3,name,depart,arrival);
                            }
                        } catch (IllegalArgumentException e) {
                            System.err.println(e.getMessage());
                        }
                    }else if (mode2 == 2){
                        System.out.println("您的订单有:");
                        dm.showMyOder(userid);
                        System.out.println("是否要退票（0.不退|其他数字.退掉该订单号对应订单）：");
                        int mode4 = in.nextInt();
                        if (mode4 != 0){
                            dm.delete_order_fully(mode4);
                        }
                    }
                }

            }



            userid = 0;
            password = "";
            mode = 0; //重置账号密码
        }
    }
}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }
    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
    public boolean hasNext() {
        try {
            String string = reader.readLine();
            if (string == null) {
                return false;
            }
            tokenizer = new StringTokenizer(string);
            return tokenizer.hasMoreTokens();
        } catch (IOException e) {
            return false;
        }
    }

}

