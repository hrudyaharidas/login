package com.example.hrudya.volleyphp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity {
    EditText username,password;
    RequestQueue requestQueue;
    String name,status=null;
    JSONObject object=null;
    String url="http://192.168.1.14/androidapi/login.php";
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username=(EditText)findViewById(R.id.etusername);
        password=(EditText)findViewById(R.id.etpassword);
        session = new Session(this);
        if (session.loggedin()){
            Intent i = new Intent(home.this,display.class);
            startActivity(i);
            finish();
        }

    }
    public void login(View view)
    {
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    dialog.dismiss();
                try {
                     object=new JSONObject(response);
                     name=object.getString("uname");
                     status=object.getString("status");
                    System.out.println(status);

                    MDToast.makeText(home.this,"Success",Toast.LENGTH_SHORT,MDToast.TYPE_SUCCESS).show();
                } catch (JSONException e) {
                    try {
                        status=object.getString("status");
                    }catch (JSONException e1){
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
                if(status.equals("success")) {
                    session.setLoggedin(true);
                    Intent i=new Intent(home.this,display.class);
                    i.putExtra("username",name);
                    startActivity(i);

                }
                else {
                    MDToast.makeText(home.this,"Login Error!!!",Toast.LENGTH_SHORT,MDToast.TYPE_ERROR).show();
                }


                //System.out.println(pass);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                MDToast.makeText(home.this,"Error",Toast.LENGTH_SHORT,MDToast.TYPE_ERROR).show();
                error.printStackTrace();
            }
        }
        ) {


            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", username.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}


