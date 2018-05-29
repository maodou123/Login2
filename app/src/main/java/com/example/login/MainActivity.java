package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton=(Button)findViewById(R.id.loginButton);
        Button registerButton=(Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameText=(EditText)findViewById(R.id.usernames);
                EditText passwordText=(EditText)findViewById(R.id.passwords);
               String usernames=nameText.getText().toString();
               String passwords=passwordText.getText().toString();
                Cursor c=DataSupport.findBySQL("select * from User where username=? and password=?",usernames,passwords);
                if(c.getCount()>0) {
                    Intent intent = new Intent(MainActivity.this, ExtraActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
                else if(usernames.isEmpty()||passwords.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}
