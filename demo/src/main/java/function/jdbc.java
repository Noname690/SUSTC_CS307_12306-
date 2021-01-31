package function;

import Util.ProxoolUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.*;

public class jdbc {
//    private Connection con = null;
//    private ResultSet resultSet;
//
//    private String host = "localhost";
//    private String dbname = "12306";
//    private String user = "checker";
//    private String pwd = "123456";
//    private String port = "5432";
//
//
//    private  void getConnection() {
//        try {
//            Class.forName("org.postgresql.Driver");
//
//        } catch (Exception e) {
//            System.err.println("Cannot find the PostgreSQL driver. Check CLASSPATH.");
//            System.exit(1);
//        }
//
//        try {
//            String url = "function.jdbc:postgresql://" + host + ":" + port + "/" + dbname;
//            con = DriverManager.getConnection(url, user, pwd);
//
//        } catch (SQLException e) {
//            System.err.println("Database connection failed");
//            System.err.println(e.getMessage());
//            System.exit(1);
//        }
//    }
//
//
//    private void closeConnection() {
//        if (con != null) {
//            try {
//                con.close();
//                con = null;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private Connection con;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;


    public void getConnection() {
        con = ProxoolUtil.getInstance().getConnection();
    }

    public void closeConnection() {
        ProxoolUtil.getInstance().closeConnection(con, preparedStatement, resultSet);
    }



    public String listuser() {
        boolean empty=true;
        getConnection();
        StringBuilder sb = new StringBuilder();
        String sql ="select * from users;";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            sb.append("user_id:\t");
            sb.append("administrator:\t");
            sb.append("id_card_number:\t");
            sb.append("phone_number:\t");
            sb.append(System.lineSeparator());
            while (resultSet.next()) {
                empty=false;
                sb.append(resultSet.getInt("user_id") + "\t\t\t");
                sb.append(resultSet.getBoolean("is_administrator")+"\t\t\t");
                sb.append(resultSet.getString("id_card_number")+ "\t\t");
                sb.append(resultSet.getString("phone_number")+"\t");
                sb.append(System.lineSeparator());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        if(empty)return ("用户为空");
        return sb.toString();
    }
    public String listorder() {
        boolean empty=true;
        getConnection();
        StringBuilder sb = new StringBuilder();
        String sql ="select * from orders;";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            sb.append("order_id:\t");
            sb.append("order_price: \t");
            sb.append("user_id: \t");
            sb.append("creat_date: \t\t\t");
            sb.append("train_name:\t");
            sb.append("depart_station: \t");
            sb.append("arrival_station: \t");
            sb.append("seat_type: \t");
            sb.append(System.lineSeparator());
            while (resultSet.next()) {
                empty=false;
                sb.append(resultSet.getInt("order_id") + "\t\t\t");
                sb.append(resultSet.getFloat("order_price")+"\t\t\t");
                sb.append(resultSet.getInt("user_id")+ "\t\t\t");
                sb.append(resultSet.getString("creat_date")+"\t");
                sb.append(resultSet.getString("train_name") + "\t\t");
                sb.append(resultSet.getString("depart_station")+"\t\t\t\t");
                sb.append(resultSet.getString("arrival_station")+ "\t\t\t\t");
                sb.append(resultSet.getString("seat_type")+"\t\t");
                sb.append(System.lineSeparator());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        if(empty)return ("订单为空");
        return sb.toString();
    }
    public String listtrain() {
        boolean empty=true;
        getConnection();
        StringBuilder sb = new StringBuilder();
        String sql ="select * from train;";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            sb.append("train_name:\t");
            sb.append("depart_station: \t");
            sb.append("arrival_station: \t");
            sb.append("depart_time: \t\t\t");
            sb.append("arrival_time:\t");
            sb.append("train_type: \t");
            sb.append("service_type: \t");
            sb.append(System.lineSeparator());

            while (resultSet.next()) {
                empty=false;
                sb.append(resultSet.getString(1) + "\t\t\t");
                sb.append(resultSet.getString(2)+"\t\t\t");
                sb.append(resultSet.getString(3)+ "\t\t\t");
                sb.append(resultSet.getString(4)+"\t");
                sb.append(resultSet.getString(5) + "\t\t");
                sb.append(resultSet.getString(6)+"\t\t\t\t");
                sb.append(resultSet.getInt(7)+ "\t\t\t\t");
                sb.append(System.lineSeparator());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        if(empty)return ("列车为空");
        return sb.toString();
    }

    public String liststation() {
        boolean empty=true;
        getConnection();
        StringBuilder sb = new StringBuilder();
        String sql ="select * from station;";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            sb.append("id:\t");
            sb.append("name: \t");
            sb.append("code: \t");
            sb.append("pinyin: \t\t\t");
            sb.append("letter:\t");
            sb.append("pinyin_code: \t");
            sb.append("lo: \t");
            sb.append("la: \t");
            sb.append(System.lineSeparator());

            while (resultSet.next()) {
                empty=false;
                sb.append(resultSet.getInt(1) + "\t\t\t");
                sb.append(resultSet.getString(2)+"\t\t\t");
                sb.append(resultSet.getString(3)+ "\t\t\t");
                sb.append(resultSet.getString(4)+"\t");
                sb.append(resultSet.getString(5) + "\t\t");
                sb.append(resultSet.getString(6)+"\t\t\t");
                sb.append(resultSet.getFloat(7)+"\t\t\t");
                sb.append(resultSet.getFloat(8)+ "\t\t\t\t");
                sb.append(System.lineSeparator());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        if(empty)return ("列车为空");
        return sb.toString();
    }

    public void deleteUser(int user_id){
        getConnection();
        int result = 0;
        String sql = "DELETE FROM public.users WHERE user_id = ?;";
        getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public int addUser(int user_id, String password,String id_card ,String phone) {
        getConnection();
        int result = 0;
        String sql = "insert into users (user_id, password,is_administrator,id_card_number,phone_number) " +
                "values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, password);
            preparedStatement.setBoolean(3,false);
            preparedStatement.setString(4, id_card);
            preparedStatement.setString(5, phone);
            //System.out.println(preparedStatement.toString());
            result = preparedStatement.executeUpdate();
            System.out.println("注册成功");
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("注册失败");
        } finally {
            closeConnection();
        }
        return result;
    }


    public int findUser(int user_id,String password) {
        getConnection();
        int cont = 0;
        StringBuilder sb = new StringBuilder();
        String sql = "select count(*) from users where user_id = ? and password = ? ;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cont = Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return cont;
    }

    public int findUser2(int user_id) {
        getConnection();
        int cont = 0;
        StringBuilder sb = new StringBuilder();
        String sql = "select count(*) from users where user_id = ?  ;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cont = Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return cont;
    }

    public void switch_privilege(int user_id) {

        String sql= "UPDATE public.users SET is_administrator = true WHERE user_id = ? ;";
        if(is_administrator(user_id))
            sql= "UPDATE public.users SET is_administrator = false WHERE user_id = ? ;";
        getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void deleteorder(int order_id){
        getConnection();
        int result = 0;
        String sql = "DELETE FROM public.orders WHERE order_id = ?;";
        getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, order_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void add_train(String train_name,
                          String depart_station,
                          String arrival_station,
                          String depart_time,
                          String arrival_time,
                          String train_type,
                          int service_type) {
        getConnection();

        String sql = "    INSERT INTO public.train (train_name, depart_station, arrival_station, depart_time, arrival_time, train_type, service_type) VALUES " +
                "( ?,?,?,?,?,?,?);";
        getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, train_name);
            preparedStatement.setString(2, depart_station);
            preparedStatement.setString(3, arrival_station);
            preparedStatement.setString(4, depart_time);
            preparedStatement.setString(5, arrival_time);
            preparedStatement.setString(6, train_type);
            preparedStatement.setInt(7, service_type);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void delete_train(String train_name) {
        getConnection();
        boolean is_ad = false;
        StringBuilder sb = new StringBuilder();
        delete_train_relation(train_name);
        delete_train_order(train_name);
        String sql = "DELETE FROM public.train WHERE train_name LIKE ? ;";
        getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, train_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void delete_train_relation(String train_name) {
        getConnection();
        boolean is_ad = false;
        StringBuilder sb = new StringBuilder();
        String sql = "DELETE FROM public.train_station WHERE train_name = ? ;";
        getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, train_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void delete_train_order(String train_name) {
        getConnection();
        boolean is_ad = false;
        StringBuilder sb = new StringBuilder();
        String sql = "DELETE FROM public.orders WHERE train_name = ? ;";
        getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, train_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public boolean is_administrator(int user_id) {
        getConnection();
        boolean is_ad = false;
        StringBuilder sb = new StringBuilder();
        String sql = "select * from users where user_id = ?  ;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                is_ad = resultSet.getBoolean("is_administrator");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return is_ad;
    }


    public void add_station(int station_id,
                            String station_name,
                            String station_code,
                            String station_pinyin,
                            String station_letter,
                            String station_pinyin_code,
                            double station_longitude,
                            double station_latitude) {
        getConnection();

        String sql = "    INSERT INTO public.station (station_id, station_name, station_code, station_pinyin, station_letter, station_pinyin_code, station_longitude, station_latitude) " +
                "VALUES (?,?,?,?,?,?,?,?);\n;";
        getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, station_id);
            preparedStatement.setString(2, station_name);
            preparedStatement.setString(3, station_code);
            preparedStatement.setString(4, station_pinyin);
            preparedStatement.setString(5, station_letter);
            preparedStatement.setString(6, station_pinyin_code);
            preparedStatement.setFloat(7, (float) station_longitude);
            preparedStatement.setFloat(8, (float) station_latitude);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }


    public void delete_station(String station_name) {
        getConnection();
        String sql = "DELETE FROM public.station WHERE station_name LIKE ? ;";
        getConnection();
        delete_station_relation(station_name);
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, station_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void delete_station_relation(String station_name) {
        getConnection();
        String sql = "DELETE FROM public.train_station WHERE station_name = ? ;";
        getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, station_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }



    public String gettraininfo(String station_depart,String station_arrival,int order) {
        boolean empty=true;
        getConnection();
        StringBuilder sb = new StringBuilder();
        String sql ="select depart.train_name,arrive.type ,depart.station_name as d_name,depart.depart_time,arrive.station_name as a_name,arrive.arrival_time "
                +"from train_station depart join train_station arrive on depart.train_name=arrive.train_name where depart.station_name like "
                +"? and arrive.station_name like "
                +"? and arrive.station_num>depart.station_num order by ";

        try{
            switch (Math.abs(order)){
                case 1:sql+="train_name ";
                    break;
                case 2:sql+="depart_time ";
                    break;
                case 3:sql+="arrival_time ";
                    break;
                case 4:sql+="d_name ";
                    break;
                case 5:sql+="a_name ";
                    break;
            }
            if (order<0)sql=sql+" desc;";
            else sql=sql+" asc;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%"+station_depart+"%");
            preparedStatement.setString(2, "%"+station_arrival+"%");

            resultSet = preparedStatement.executeQuery();
            sb.append("Name:\t");
            sb.append("Type:\t");
            sb.append("station_depart:\t");
            sb.append("Depart_time:\t");
            sb.append("Station_arrive:\t");
            sb.append("Arrival_time:\t");
            sb.append(System.lineSeparator());
            while (resultSet.next()) {
                empty=false;
                sb.append(resultSet.getString("train_name") + "\t");
                sb.append(resultSet.getString("type")+"\t\t");
                sb.append(resultSet.getString("d_name")+ "\t\t\t\t");
                sb.append(resultSet.getString("depart_time")+"\t\t\t\t");
                sb.append(resultSet.getString("a_name")+ "\t\t\t\t");
                sb.append(resultSet.getString("arrival_time")+"\t\t\t\t");
                sb.append(System.lineSeparator());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        if(empty)return ("没有列车连接两地");
        return sb.toString();
    }

    public String gettraindetail(String name,String station_depart,String station_arrival){
        boolean empty=true;
        getConnection();
        StringBuilder sb = new StringBuilder();
        String sql ="select * from train_station\n" +
                "where train_name=? order by station_num asc;";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            boolean include=false;
            int seat_type1=0x3f3f3f3f;
            int seat_type2=0x3f3f3f3f;
            int seat_type3=0x3f3f3f3f;
            int price1=0;
            int price2=0;
            int price3=0;
            sb.append(name+"\t"+station_depart+" -> "+station_arrival+"\n");
            while (resultSet.next()) {
                empty=false;
                if(resultSet.getString("station_name").contains(station_depart)){
                    include=true;
                }
                if(resultSet.getString("station_name").contains(station_arrival)){
                    include=false;
                    sb.append(resultSet.getString("station_name")+" ("+resultSet.getString("depart_time")+")\n");
                }
                if(include){
                    sb.append(resultSet.getString("station_name")+" ("+resultSet.getString("depart_time")+") -> ");
                    seat_type1=seat_type1<resultSet.getInt("seat_type1")?seat_type1:resultSet.getInt("seat_type1");
                    seat_type2=seat_type2<resultSet.getInt("seat_type2")?seat_type2:resultSet.getInt("seat_type2");
                    seat_type3=seat_type3<resultSet.getInt("seat_type3")?seat_type3:resultSet.getInt("seat_type3");
                    price1+=resultSet.getInt("price1");
                    price2+=resultSet.getInt("price2");
                    price3+=resultSet.getInt("price3");
                }
                if (resultSet.getString("station_name").equals(station_arrival)){
                    price1+=resultSet.getInt("price1");
                    price2+=resultSet.getInt("price2");
                    price3+=resultSet.getInt("price3");
                }
            }
            sb.append("商务座：¥"+price1+"（剩余 "+seat_type1+" 张)\n");
            sb.append("一等座：¥"+price2+"（剩余 "+seat_type2+" 张)\n");
            sb.append("二等座：¥"+price3+"（剩余 "+seat_type3+" 张)\n");

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        if(empty)return "没有此列车";
        return sb.toString();

    }

    public void buy_ticket(int user_id,int seat_type,String name,String station_depart,String station_arrival){
        getConnection();
        String sql ="select * from train_station\n" +
                "where train_name=? order by station_num asc;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            int start = -1;
            int stop = -1;
            boolean empty = true;
            int least = 100;
            double price = 0;
            while (resultSet.next()) {
                empty = false;
                int now = resultSet.getInt("station_num");
                if (resultSet.getString("station_name").equals(station_depart)){
                    start = resultSet.getInt("station_num");
                }
                if (resultSet.getString("station_name").equals(station_arrival)){
                    stop = resultSet.getInt("station_num");
                }
                if ((start!=-1&&stop==-1)||stop==now){
                    if (seat_type == 1){
                        price += resultSet.getInt("price1");
                        if (resultSet.getInt("seat_type1")<least){
                            least = resultSet.getInt("seat_type1");
                        }
                    }else if (seat_type == 2){
                        price += resultSet.getInt("price2");
                        if (resultSet.getInt("seat_type2")<least){
                            least = resultSet.getInt("seat_type2");
                        }
                    }else {
                        price += resultSet.getInt("price3");
                        if (resultSet.getInt("seat_type3")<least){
                            least = resultSet.getInt("seat_type3");
                        }
                    }
                }
            }
            if (least==0){
                System.out.println("当前类型票已卖完");
            }else {
                Date date = new Date();
                String st = new String(new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss ").format(date));
                addOder(price,user_id,st,name,station_depart,station_arrival,String.valueOf(seat_type));
                if (seat_type==1){
                    update_seat("seat_type1",name,start,stop);
                }else if (seat_type==2){
                    update_seat("seat_type2",name,start,stop);
                }
                else {
                    update_seat("seat_type3",name,start,stop);
                }
                System.out.println("购票成功！");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    public void addOder(double oder_price, int user_id,String create_date ,String train_name,String depart_station,String attival_station,String seat_type) {
        getConnection();
        String sql = "insert into orders (order_id, order_price, user_id, creat_date, train_name, depart_station, arrival_station,seat_type) " +
                "values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, findOderCont()+1);
            preparedStatement.setDouble(2, oder_price);
            preparedStatement.setInt(3,user_id);
            preparedStatement.setString(4, create_date);
            preparedStatement.setString(5, train_name);
            preparedStatement.setString(6, depart_station);
            preparedStatement.setString(7, attival_station);
            preparedStatement.setString(8, seat_type);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void DropOder(int order_id) {
        getConnection();
        String sql = "delete from orders where order_id = ?;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, order_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void delete_order_fully(int order_id) {
        getConnection();
        String sql = "select * from orders where order_id = ? ;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, order_id);
            resultSet = preparedStatement.executeQuery();
            String depart_station="";
            String arrival_station="";
            String train_name="";
            int seat_type=0;
            while (resultSet.next()) {
                depart_station = resultSet.getString("depart_station");
                arrival_station = resultSet.getString("arrival_station");
                train_name = resultSet.getString("train_name");
                seat_type = Integer.parseInt(resultSet.getString("seat_type"));
            }
            find_station_num(seat_type,depart_station,arrival_station,train_name);
            DropOder(order_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void find_station_num(int seat_type,String depart_station,String arrival_station,String train_name){
        getConnection();
        String sql ="select * from train_station\n" +
                "where train_name=? order by station_num asc;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, train_name);
            resultSet = preparedStatement.executeQuery();
            int start = -1;
            int stop = -1;
            while (resultSet.next()) {
                int now = resultSet.getInt("station_num");
                if (resultSet.getString("station_name").equals(depart_station)){
                    start = resultSet.getInt("station_num");
                }
                if (resultSet.getString("station_name").equals(arrival_station)){
                    stop = resultSet.getInt("station_num");
                }
            }
            {
                if (seat_type==1){
                    update_seat2("seat_type1",train_name,start,stop);
                }else if (seat_type==2){
                    update_seat2("seat_type2",train_name,start,stop);
                }
                else {
                    update_seat2("seat_type3",train_name,start,stop);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    public int findOderCont() {
        getConnection();
        int cont = 0;
        String sql = "select max(order_id) from orders;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("max")!=null) {
                    cont = Integer.parseInt(resultSet.getString("max"));
                }
                else {
                    cont = 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return cont;
    }


    public void update_seat(String seat_type,String train_name,int sart,int stop) {
        getConnection();
        try {
            if (seat_type.equals("seat_type1")){
                String sql = "update train_station set seat_type1 = seat_type1 - 1"+" where train_name = ? and station_num < ? and station_num > ?;";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,train_name);
                preparedStatement.setInt(2, stop+1);
                preparedStatement.setInt(3,sart-1);
                preparedStatement.executeQuery();
            }
            else if (seat_type.equals("seat_type2")){
                String sql = "update train_station set seat_type2 = seat_type2 - 1"+" where train_name = ? and station_num < ? and station_num > ?;";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,train_name);
                preparedStatement.setInt(2, stop+1);
                preparedStatement.setInt(3,sart-1);
                preparedStatement.executeQuery();
            }else {
                String sql = "update train_station set seat_type3 = seat_type3 - 1"+" where train_name = ? and station_num < ? and station_num > ?;";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,train_name);
                preparedStatement.setInt(2, stop+1);
                preparedStatement.setInt(3,sart-1);
                preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
        } finally {
            closeConnection();
        }
    }

    public void update_seat2(String seat_type,String train_name,int sart,int stop) {
        getConnection();
        try {
            if (seat_type.equals("seat_type1")){
                String sql = "update train_station set seat_type1 = seat_type1 + 1"+" where train_name = ? and station_num < ? and station_num > ?;";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,train_name);
                preparedStatement.setInt(2, stop+1);
                preparedStatement.setInt(3,sart-1);
                preparedStatement.executeQuery();
            }
            else if (seat_type.equals("seat_type2")){
                String sql = "update train_station set seat_type2 = seat_type2 + 1"+" where train_name = ? and station_num < ? and station_num > ?;";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,train_name);
                preparedStatement.setInt(2, stop+1);
                preparedStatement.setInt(3,sart-1);
                preparedStatement.executeQuery();
            }else {
                String sql = "update train_station set seat_type3 = seat_type3 + 1"+" where train_name = ? and station_num < ? and station_num > ?;";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,train_name);
                preparedStatement.setInt(2, stop+1);
                preparedStatement.setInt(3,sart-1);
                preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
        } finally {
            closeConnection();
        }
    }

    public void showMyOder(int user_id){
        getConnection();
        String sql = "select * from orders where user_id = ?;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder sb = new StringBuilder();
                sb.append("订单账号:"+ resultSet.getInt("order_id")+" ");
                sb.append("价格:"+ resultSet.getInt("order_price")+" ");
                sb.append("创建日期:"+ resultSet.getString("creat_date")+" ");
                sb.append("车号:"+ resultSet.getString("train_name")+" ");
                sb.append("出发站:"+ resultSet.getString("depart_station")+" ");
                sb.append("到达站:"+ resultSet.getString("arrival_station")+" ");
                if (resultSet.getString("seat_type").equals("1")){
                    sb.append("座位类型:商务座");
                }else if (resultSet.getString("seat_type").equals("2")){
                    sb.append("座位类型:一等座");
                }else {
                    sb.append("座位类型:二等座");
                }
                System.out.println(sb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public int findOderNum(int user_id) {
        getConnection();
        int cont = 0;
        String sql = "select count(*) from orders where user_id = ?  ;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cont = Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return cont;
    }

    public oder[] findOder(int user_id,int oder_num){
        getConnection();
        String sql = "select * from orders where user_id = ?;";
        oder[]orders = new oder[oder_num];
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();
            int x = 0;
            while (resultSet.next()) {
                orders[x] = new oder();
                orders[x].oder_id=resultSet.getInt("order_id");
                orders[x].price=resultSet.getInt("order_price");
                orders[x].date=resultSet.getString("creat_date");
                orders[x].train_num=resultSet.getString("train_name");
                orders[x].start_station=resultSet.getString("depart_station");
                orders[x].stop_station = resultSet.getString("arrival_station");
                if (resultSet.getString("seat_type").equals("1")){
                    orders[x].seat_type="商务座";
                }else if (resultSet.getString("seat_type").equals("2")){
                    orders[x].seat_type="一等座";
                }else {
                    orders[x].seat_type="二等座";
                }
                x++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return orders;
    }


    public String station_name_fix(String station_name,String train_name){
        String new_name = "";
        getConnection();
        String sql ="select * from train_station\n" +
                "where train_name=? order by station_num asc;";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, train_name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("station_name").contains(station_name)){
                    new_name = resultSet.getString("station_name");
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return new_name;
    }

    public String[] gettrain_string(String station_depart,String station_arrival,int order) {
        boolean empty=true;
        getConnection();
        ArrayList<String>list = new ArrayList<>();
        String sql ="select depart.train_name,arrive.type ,depart.station_name as d_name,depart.depart_time,arrive.station_name as a_name,arrive.arrival_time "
                +"from train_station depart join train_station arrive on depart.train_name=arrive.train_name where depart.station_name like "
                +"? and arrive.station_name like "
                +"? and arrive.station_num>depart.station_num order by ";

        try{
            switch (Math.abs(order)){
                case 1:sql+="train_name ";
                    break;
                case 2:sql+="depart_time ";
                    break;
                case 3:sql+="arrival_time ";
                    break;
                case 4:sql+="d_name ";
                    break;
                case 5:sql+="a_name ";
                    break;
            }
            if (order<0)sql=sql+" desc;";
            else sql=sql+" asc;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%"+station_depart+"%");
            preparedStatement.setString(2, "%"+station_arrival+"%");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                empty=false;
                list.add(resultSet.getString("train_name"));
                list.add(resultSet.getString("type"));
                list.add(resultSet.getString("d_name"));
                list.add(resultSet.getString("depart_time"));
                list.add(resultSet.getString("a_name"));
                list.add(resultSet.getString("arrival_time"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        String[]result = new String[list.size()];
        for (int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }
       return result;
    }

    public String[] detail_string(String name,String station_depart,String station_arrival){
        getConnection();
        String[]list = new String[13];
        StringBuilder sb = new StringBuilder();
        String sql ="select * from train_station\n" +
                "where train_name=? order by station_num asc;";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            boolean include=false;
            int seat_type1=0x3f3f3f3f;
            int seat_type2=0x3f3f3f3f;
            int seat_type3=0x3f3f3f3f;
            int price1=0;
            int price2=0;
            int price3=0;
            list[0] = name;
            list[1] = station_depart;
            list[2] = station_arrival;
            while (resultSet.next()) {
                if(resultSet.getString("station_name").contains(station_depart)){
                    include=true;
                }
                if(resultSet.getString("station_name").contains(station_arrival)){
                    include=false;
                    sb.append(resultSet.getString("station_name")+" ("+resultSet.getString("depart_time")+")\n");
                }
                if(include){
                    sb.append(resultSet.getString("station_name")+" ("+resultSet.getString("depart_time")+") -> ");
                    seat_type1=seat_type1<resultSet.getInt("seat_type1")?seat_type1:resultSet.getInt("seat_type1");
                    seat_type2=seat_type2<resultSet.getInt("seat_type2")?seat_type2:resultSet.getInt("seat_type2");
                    seat_type3=seat_type3<resultSet.getInt("seat_type3")?seat_type3:resultSet.getInt("seat_type3");
                    price1+=resultSet.getInt("price1");
                    price2+=resultSet.getInt("price2");
                    price3+=resultSet.getInt("price3");
                }
                if (resultSet.getString("station_name").equals(station_arrival)){
                    price1+=resultSet.getInt("price1");
                    price2+=resultSet.getInt("price2");
                    price3+=resultSet.getInt("price3");
                }
            }
            list[3] = sb.toString();
            list[4] = "商务座";
            list[5] = String.valueOf(price1);
            list[6] = String.valueOf(seat_type1);
            list[7] = "一等座";
            list[8] = String.valueOf(price2);
            list[9] = String.valueOf(seat_type2);
            list[10] = "二等座";
            list[11] = String.valueOf(price3);
            list[12] = String.valueOf(seat_type3);

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;

    }

    public String[] return_user() {
        getConnection();
        String sql ="select * from users;";
        ArrayList<String>list = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(String.valueOf(resultSet.getInt("user_id")));
                list.add(String.valueOf(resultSet.getBoolean("is_administrator")));
                list.add(resultSet.getString("id_card_number"));
                list.add(resultSet.getString("phone_number"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        String[]result = new String[list.size()];
        for (int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }

    public String[] return_order() {
        getConnection();
        ArrayList<String>list = new ArrayList<>();
        String sql ="select * from orders;";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(String.valueOf(resultSet.getInt("order_id")));
                list.add(String.valueOf(resultSet.getFloat("order_price")));
                list.add(String.valueOf(resultSet.getInt("user_id")));
                list.add(resultSet.getString("creat_date"));
                list.add(resultSet.getString("train_name")) ;
                list.add(resultSet.getString("depart_station"));
                list.add(resultSet.getString("arrival_station"));
                list.add(resultSet.getString("seat_type"));

            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        String[] result = new String[list.size()];
        for (int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }



}
