package com.example.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ui.database.greenDao.db.DaoSession;
import com.example.ui.dbflow.User2Model;
import com.example.ui.dbflow.User2Model_Table;
import com.example.ui.greendao.Student;
import com.orhanobut.logger.Logger;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;


public class DBFlowDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbflow_demo);

        //Logger.d("onCreate ");


        // DBFlow
        operateBFlow();

        // Greendao
        operateGreendao();
    }

    private void operateGreendao() {
        //插入数据
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setStudentNo(i);
            int age = i;
            student.setAge(age);
            student.setName("name "+i);
            //daoSession.insert(student);
            daoSession.insertOrReplace(student);//存在则替换，数据不存在则插入
        }

        //查询数据
        List<Student> students = daoSession.queryRaw(Student.class, " where age = ?", "2");

        //删除数据
        if(students != null & students.size()>0)
        {
            Logger.d("delete "+students.get(0).getName());
            daoSession.delete(students.get(0));
        }

        //更新数据
        ///  Cannot update entity without key - was it inserted before?  异常的原因：在调用update()时传入的主键为 null
        Student student = new Student();
        student.setStudentNo(3);
        student.setId(new Long(3));
        student.setName("angel");
        daoSession.update(student);

        students = daoSession.queryRaw(Student.class, " where age = ?", "3");
        if(students != null & students.size()>0)
        {
            students.get(0).setName("angel");
            daoSession.update(students.get(0));
            Logger.d("update "+students.get(0).getName());
        }
    }

    private void operateBFlow() {
        //插入数据  id 必须唯一
        User2Model userModel=new User2Model();
        userModel.setName("UserModel");
        userModel.setAge(1);
        userModel.insert();

        userModel=new User2Model();
        userModel.setName("UserModel");
        userModel.setAge(2);
        userModel.insert();

        userModel=new User2Model();
        userModel.setName("UserModel");
        userModel.setAge(3);
        userModel.insert();

        userModel=new User2Model();
        userModel.setName("UserModel");
        userModel.setAge(4);
        userModel.insert();

        userModel=new User2Model();
        userModel.setName("UserModel");
        userModel.setAge(5);
        userModel.insert();

        userModel=new User2Model();
        userModel.setName("UserModel");
        userModel.setAge(6);
        userModel.insert();

        SQLite.insert(User2Model.class)
                .columns(User2Model_Table.name,User2Model_Table.age)
                .values("angel",27)
                .execute();

        //删除数据
        userModel = SQLite.select()
                .from(User2Model.class)
                .where(User2Model_Table.id.is(3))
                .querySingle();
        if (userModel != null){
            userModel.delete();
        }

        SQLite.delete(User2Model.class)
                .where(User2Model_Table.name.is("UserModel"))
                .and(User2Model_Table.id.is(4))
                .async()
                .execute();

        //更新数据
        userModel = SQLite.select()
        .from(User2Model.class)
        .where(User2Model_Table.id.is(1))
        .querySingle();
        if (userModel != null){
            userModel.setName("update 1 ");
            userModel.update();
        }

        SQLite.update(User2Model.class)
                .set(User2Model_Table.name.eq("update 2"))
                .where(User2Model_Table.name.is("UserModel"))
                .and(User2Model_Table.id.is(5))
                .async()
                .execute();


        //查询全部
        List<User2Model> noteBeans = SQLite.select()
                .from(User2Model.class)
                .queryList();

        //条件查询
        userModel = SQLite.select()
                .from(User2Model.class)
                .where(User2Model_Table.name.eq("update 2"))
                .querySingle();

        Logger.d("name "+userModel.getName());
    }

}
