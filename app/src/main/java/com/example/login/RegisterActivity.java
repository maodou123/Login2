package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button sureButton=(Button)findViewById(R.id.sure);
        Button returnButton=(Button)findViewById(R.id.returnButton);

        sureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                 EditText nameText=(EditText)findViewById(R.id.username);
                EditText passwordText=(EditText)findViewById(R.id.password);
                EditText againText=(EditText)findViewById(R.id.again);
                String username=nameText.getText().toString();
                String password=passwordText.getText().toString();
                String again=againText.getText().toString();
                Cursor cursor= DataSupport.findBySQL("select * from User where username=?",username);
                if(cursor.getCount()>0)
                {
                    Toast.makeText(RegisterActivity.this, "该用户名已被注册", Toast.LENGTH_SHORT).show();
                }
               else if(again.equals(password)&&password.length()>0&&username.length()>0)
                {
                user.setPassword(again);
                user.setUsername(username);
                user.save();
                    Toast.makeText(RegisterActivity.this, "恭喜您注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                    }
                else if(!password.equals(again))
                {
                    Toast.makeText(RegisterActivity.this, "两次输入的密码不同,请确认", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            }
        });
       returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
