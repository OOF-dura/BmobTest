package com.example.frankdura.databasetest.Service;

/**
 * Created by frankdura on 2017/7/20.
 */
import android.util.Log;
import android.widget.Toast;

import com.example.frankdura.databasetest.MainActivity;
import com.example.frankdura.databasetest.POJO.Users;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class DBmanager {
    private DBmanager(){

    }
    String name;
    private static final DBmanager instance=new DBmanager();
    public static DBmanager getinstance(){
        return instance;
    }


    public synchronized  void  logIn(final String id ,final String password){

        BmobQuery<Users> query = new BmobQuery<Users>();
        String res = id;
        query.addWhereEqualTo("id", res);
        query.setLimit(1);
        query.findObjects(new FindListener<Users>() {
            public void done(List<Users> object, BmobException e) {
                if(e==null){
                    Log.i("bmob","查询成功：共"+object.size()+"条数据。");
                    for (Users user:object) {
                        Log.i("bmob", user.getPassword() + user.getCreatedAt());  //如果有该用户id打印他的姓名和创建日期
                        //获得playerName密码信息
                        //这显示验证密码的方法
                        BmobQuery<Users> query = new BmobQuery<Users>();
                        query.addWhereEqualTo("password", password);
                        query.setLimit(1);
                        query.findObjects(new FindListener<Users>() {
                            public void done(List<Users> object, BmobException e) {
                                if (e == null) {
                                    Log.i("bmob", "查询成功：共" + object.size() + "条数据。");
                                    for (Users user : object) {
                                        Log.i("bmob", user.getPassword() + user.getCreatedAt());  //如果有该用户id打印他的姓名和创建日期
                                        Log.d("bmob", "密码正确 调起新的活动");
                                        //这里显示主界面
                                    }

                                } else {

                                    //这里显示登录失败因为密码不正确

                                }


                            }
                        });


                    }
                }else{
                    Log.i("bmob","没有该数据，该用户账号不正确"+e.getMessage()+","+e.getErrorCode());

                    // 这里显示登录失败因为 账号不正确


                }
            }
        });

    }


    public synchronized void signIn(final String id, final String  password, final String name, final String realname, final String  email, final String work ){
        BmobQuery<Users> query = new BmobQuery<Users>();
        String res = id;
        query.addWhereEqualTo("id", res);
        query.setLimit(1);
        query.findObjects(new FindListener<Users>() {
            public void done(List<Users> object, BmobException e) {
                if(e==null){
                    Log.i("bmob","查询成功：共"+object.size()+"条数据。");
                    for (Users user:object) {
                        Log.i("bmob", user.getName() + user.getCreatedAt());  //如果有该用户id打印他的姓名和创建日期
                        //获得playerName的信息
                        //这可以显示 注册不成功的消息 例如toast






                    }
                }else{
                    Log.i("bmob","没有该数据，准许插入"+e.getMessage()+","+e.getErrorCode());


                    insertData(id,password,name,realname,email,work);

                }
            }
        });
    }


    public void showData(){
        BmobQuery<Users> query = new BmobQuery<Users>();
        query.getObject("06c6387ebb", new QueryListener<Users>() {
            public void done(Users object, BmobException e) {
                if(e==null){
                    //获得Name的信息
                    String name = object.getName();
                    //获得数据的objectId信息
                    String  ID = object.getId();
                    //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                    String createdAt =object.getCreatedAt();
                    Log.d("bmob",name+" --" + ID +"--" + createdAt);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }

        });
    }
    public void insertData(String id,String  password, String name, String realname,String  email,String work){
        Users p2 = new Users();
        p2.setPassword(password);
        p2.setName(name);
        p2.setRealname(realname);
        p2.setId(id);
        p2.setEmail(email);
        p2.setWork(work);
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Log.d("bmob", "添加数据成功，返回objectId为：" + objectId);
                } else {
                    Log.d("bmob", "创建数据失败：" + e.getMessage());

                }

            }
        });
    }
    public void update(){
        Users gameScore = new Users();
        gameScore.setValue("id","201600301567");

        gameScore.update("0c6db13c", new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","更新成功");
                }else{
                    Log.i("bmob","更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

}
