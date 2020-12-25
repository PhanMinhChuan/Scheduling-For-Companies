package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (TextUtils.isEmpty(username.getText().toString())) {
//                    Toast.makeText(MainActivity.this, "Invalid Inputs", Toast.LENGTH_LONG).show();
//                } else if (username.getText().toString().equals("admin") && password.getText().toString().equals("123")){
//                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
//                    openFormDataTask();
//                }

                login(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void login (String username, String password) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "http://192.168.1.31:8080/users/loginMobile";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);

            //Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_LONG).show();

            Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Toast.makeText(MainActivity.this, response.getString("token"), Toast.LENGTH_LONG).show();
                        openFormDataTask(response.getString("token"));
                    } catch (Exception e) {
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    //Log.e("Rest Response", error.toString());
                }
            };

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
            mRequestQueue.add(request);
        } catch (JSONException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void openFormDataTask(String token) {
        Intent intent = new Intent(this, FormDataTask.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }
}