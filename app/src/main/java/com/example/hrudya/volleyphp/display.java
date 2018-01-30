package com.example.hrudya.volleyphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class display extends AppCompatActivity {

    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        session=new Session(this);
        TextView textname=(TextView)findViewById(R.id.txtname);
        Intent i=getIntent();
        String username=i.getStringExtra("username");
        textname.setText(username);
        if(!session.loggedin()){
            session.setLoggedin(false);
            startActivity(new Intent(display.this,home.class));
            finish();


        }
    }

    public void logout(View view) {

        session.setLoggedin(false);
        startActivity(new Intent(display.this,home.class));
        finish();
    }
}
