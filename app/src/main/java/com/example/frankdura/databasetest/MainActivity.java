package com.example.frankdura.databasetest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {
private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "72a609a57ed24a631416740e95d4418e");

        dbHelper= new MyDatabaseHelper(this,"BookStore.db",null,2);
        Button button = (Button)findViewById(R.id.create_database);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createData();
                showToast("您点击了创建数据");
//                dbHelper.getWritableDatabase();//这里是真正得到了数据库对象&实现了操作
//            //ps 如果没有这个数据库就是会创建这个数据库 第二次有了数据库 就不会再创建这个库
            }
        });
        Button button2 = (Button)findViewById(R.id.add_data);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData("大豆豆","201600301245","SDU");
            }
        });
        Button button3 = (Button)findViewById(R.id.show_data);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findData();
            }

        });
    }

    public void createData(){

        Users p2 = new Users();
        p2.setName("刘安");
        p2.setUniversity("SDU");
        p2.setId("1");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this, "添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void findData(){
        BmobQuery<Users> query = new BmobQuery<Users>();
//查询playerName叫“比目”的数据
        query.addWhereEqualTo("name", "刘安");
//返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(1);
//执行查询方法
        query.findObjects(new FindListener<Users>() {
            public void done(List<Users> object, BmobException e) {
                if(e==null){
                    showToast("查询成功：共"+object.size()+"条数据。");
                    for (Users users : object) {
                        //获得playerName的信息
                        users.getUniversity();
                        //获得数据的objectId信息
                        users.getName();
                        //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                        users.getId();

                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
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
                    String university =object.getUniversity();
                    showToast(name+" --" + ID +"--" + university);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }

        });
    }
    public void insertData(String name , String  id, String University){
    Users p2 = new Users();
        p2.setName(name);
        p2.setUniversity(University);
        p2.setId(id);
        p2.save(new SaveListener<String>() {
        @Override
        public void done(String objectId, BmobException e) {
            if (e == null) {
                Toast.makeText(MainActivity.this, "添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();

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

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
