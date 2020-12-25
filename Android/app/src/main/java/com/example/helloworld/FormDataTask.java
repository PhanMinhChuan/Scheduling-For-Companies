package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormDataTask extends AppCompatActivity {

    public Button btnLogout;
    public Button btnReport;
    public TextView tokenView, taskInfoView, idTaskView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data_task);

        this.setTitle("Task Today");

        String token = getIntent().getStringExtra("token");
        //tokenView = findViewById(R.id.outputToken);
        //Toast.makeText(FormDataTask.this, token, Toast.LENGTH_LONG).show();
        //tokenView.setText(token);

        taskInfoView = findViewById(R.id.taskInfo);
        showInfoUser (token, taskInfoView);

        btnLogout = findViewById(R.id.logout);
        idTaskView = findViewById(R.id.idTask);


        btnReport = findViewById(R.id.button2);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idTaskView.getText().toString().equals("")) {
                    try {
                        int id = Integer.parseInt(idTaskView.getText().toString());
                        reportAction(id, token);
                    } catch (Exception e ) {
                        Toast.makeText(FormDataTask.this, "Error: Vui Lòng Nhập Đúng Kiểu!", Toast.LENGTH_LONG).show();
                        recreate();
                    }
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    public void reportAction(int id, String token) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "http://192.168.1.31:8080/jwt";
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            reportActionChange(Integer.parseInt(response.toString()), id, token);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", token);
                    return params;
                }
            };
            queue.add(getRequest);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void reportActionChange (int idUser, int idTask, String token) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "http://192.168.1.31:8080/jobs/" + idTask + "/" + idUser;
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest getRequest = new StringRequest(Request.Method.PUT, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            if (response.toString().equals("1")) {
                                Toast.makeText(FormDataTask.this, "Báo cáo thành công!", Toast.LENGTH_LONG).show();
                                recreate();
                            } else if (response.toString().equals("2")) {
                                Toast.makeText(FormDataTask.this, "Bạn đã báo cáo công việc này!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(FormDataTask.this, "Error: Không Tìm Thấy ID bạn cần báo cáo!", Toast.LENGTH_LONG).show();
                            }

                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", token);
                    return params;
                }
            };
            queue.add(getRequest);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }



    public void showInfoUser (String token, TextView textView) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "http://192.168.1.31:8080/jwt";
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            showInfo(token, Integer.parseInt(response.toString()), textView);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", token);
                    return params;
                }
            };
            queue.add(getRequest);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public String showInfo (String token, int id, TextView textView) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "http://192.168.1.31:8080/jobs/" + id;

        JSONObject jsonObject = new JSONObject();
        try {
            Response.Listener<JSONArray> successListener = new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        String answerString = "";

                        for (int i = 0; i < response.length(); i++) {
                            if (!response.getJSONObject(i).getString("status").equals("DA_HOAN_THANH")) {
                                answerString = answerString.concat("STT: " + (i+1) + ", Task ID: "+ response.getJSONObject(i).getString("id") +"\n");
                                answerString = answerString.concat("Công việc: " + response.getJSONObject(i).getString("content") + "\n");
                                answerString = answerString.concat("Deadline: " + response.getJSONObject(i).getString("deadline") + "\n");
                                answerString = answerString.concat("Comment:  " + response.getJSONObject(i).getString("comment"));
                                answerString = answerString.concat("\n");
                                answerString = answerString.concat("\n");
                            }

                        }
                        textView.setText(answerString);
                        textView.setMovementMethod(new ScrollingMovementMethod());
                        //textView.setText("asdqweqwe");
                    } catch (Exception e) {
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(FormDataTask.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            };

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, successListener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", token);
                    return params;
                }
            };
            mRequestQueue.add(request);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}