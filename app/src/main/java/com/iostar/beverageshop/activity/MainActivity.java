package com.iostar.beverageshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.iostar.beverageshop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static ImageView imginfor;
    private ImageView imgGioHang;
    ArrayList<TaiKhoan> TaiKhoanArrayList;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imginfor = findViewById(R.id.imginfor);
        imgGioHang = findViewById(R.id.imgGioHang);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
        imginfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });


        setTenKH();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectfrag=null;
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        selectfrag=new HomeFragment();
                        break;
                    case R.id.navigation_account:
                        selectfrag=new AccountFragment();
                        break;
                    case R.id.navigation_seach:
                        selectfrag=new SeachFragment();
                        break;
                    case R.id.navigation_favorite:
                        selectfrag=new FavoriteFragment();
                        break;
                    case R.id.navigation_menu:
                        selectfrag=new MenuFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,selectfrag).commit();
                return true;
            }

        });
    }
    private void setTenKH(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("user_file", Context.MODE_PRIVATE);
        String user = preferences.getString("gmail", "");
        String matkhau = preferences.getString("matkhau", "");
        StringRequest request = new StringRequest(Request.Method.POST, "https://website1812.000webhostapp.com/Coffee/gettaikhoan.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    TaiKhoanArrayList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if(result.equals("thanh cong")){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject object=jsonArray.getJSONObject(i);
                            TaiKhoanArrayList.add(new TaiKhoan(
                                    object.getString("MaKH"),
                                    object.getString("TenKH"),
                                    object.getString("HinhAnhKH"),
                                    object.getString("DiaChi"),
                                    object.getString("NamSinh"),
                                    object.getString("SDT"),
                                    object.getString("Gmail"),
                                    object.getString("MatKhau")
                            ));
                            TaiKhoan kh=TaiKhoanArrayList.get(0);
                            Log.e("ssssssssss",kh.getGmail());
                            Picasso.get().load(kh.getHinhAnhKH())
                                    .into(imginfor);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "xảy ra lỗi!", Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Gmail", user);
                params.put("MatKhau", matkhau);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

}