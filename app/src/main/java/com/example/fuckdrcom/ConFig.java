package com.example.fuckdrcom;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ConFig extends AppCompatActivity {

    private Button loginbtn, resetbtn;
    private TextView logintv, resettv;
    private EditText username, password, ip;
    private RadioButton cmcc,ctcc,cucc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_fig);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        loginbtn = (Button) findViewById(R.id.loginbtn);
        resetbtn = (Button) findViewById(R.id.resetbtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        ip = (EditText) findViewById(R.id.ip);
        cmcc = (RadioButton) findViewById(R.id.cmcc);
        cucc = (RadioButton) findViewById(R.id.cucc);
        ctcc = (RadioButton) findViewById(R.id.ctcc);


        new myClass().fullScreen(this);//设置透明状态栏




        SharedPreferences sPreferences=getSharedPreferences("config", MODE_PRIVATE);
        String username1=sPreferences.getString("username", "");
        String password1 =sPreferences.getString("password", "");
        String net1 =sPreferences.getString("net", "");
        String ip1 =sPreferences.getString("ip", "");

        username.setText(username1);
        password.setText(password1);
        ip.setText(ip1);

        if(net1.equals("cmcc")) cmcc.setChecked(true);
        else if(net1.equals("unicom")) cucc.setChecked(true);
        else if(net1.equals("telecom")) ctcc.setChecked(true);


        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                username.setText("");
                password.setText("");
                cmcc.setChecked(false);
                cucc.setChecked(false);
                ctcc.setChecked(false);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = username.getText().toString();
                String s2 = password.getText().toString();
                String s4 = ip.getText().toString();
                String s3 = "";
                if(cmcc.isChecked()) s3 = "cmcc";
                else if(cucc.isChecked()) s3 = "unicom";
                else if(ctcc.isChecked()) s3 = "telecom";
                //  String s3 = netred.getTag().toString();
                myClass m = new myClass();
                boolean i = m.saveUserInfo(ConFig.this,s1,s2,s3,s4);
                if(i) {
                    Toast.makeText(ConFig.this,"保存成功，密码为" +s2 ,Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(ConFig.this,"填完整再保存好吧？",Toast.LENGTH_LONG).show();
                }
            }
        });


    }





}
