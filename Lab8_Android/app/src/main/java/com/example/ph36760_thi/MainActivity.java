package com.example.ph36760_thi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph36760_thi.Adapter.AdapterSinhVien;
import com.example.ph36760_thi.Handle.Handle_SinhVien;
import com.example.ph36760_thi.Model.Response_Model;
import com.example.ph36760_thi.Model.SinhVien;
import com.example.ph36760_thi.Services.HttpRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Handle_SinhVien {
    private HttpRequest httpRequest;
    private AdapterSinhVien adapterSinhVien;
    private RecyclerView rcvSinhVien;
    FloatingActionButton btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvSinhVien = findViewById(R.id.rcv_sinhVien);
        btn_add = findViewById(R.id.btn_add);

        httpRequest = new HttpRequest();
        httpRequest.callAPI().getListSinhVien().enqueue(getSinhVienAPI);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Add_SinhVien.class));
            }
        });
    }

    private void getData(ArrayList<SinhVien> list) {
        adapterSinhVien = new AdapterSinhVien(this, list, this);
        rcvSinhVien.setLayoutManager(new LinearLayoutManager(this));
        rcvSinhVien.setAdapter(adapterSinhVien);

    }

    Callback<Response_Model<ArrayList<SinhVien>>> getSinhVienAPI = new Callback<Response_Model<ArrayList<SinhVien>>>() {
        @Override
        public void onResponse(Call<Response_Model<ArrayList<SinhVien>>> call, Response<Response_Model<ArrayList<SinhVien>>> response) {
            // khi call thành công sẽ chạy vào hàm này
            if (response.isSuccessful()) {
                // check status
                if (response.body().getStatus() == 200) {
                    // lấy data
                    ArrayList<SinhVien> list = response.body().getData();
                    Log.d("List", "onResponse: " + list);
                    // set dữ liệu lên rcv
                    getData(list);
                    // Thông báo
//                    Toast.makeText(MainActivity.this, response.body().getMessenger(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response_Model<ArrayList<SinhVien>>> call, Throwable t) {
            Log.d(">>>GetListDistributor", "onFailure: " + t.getMessage());
        }
    };
    Callback<Response_Model<SinhVien>> responseSinhVienAPI = new Callback<Response_Model<SinhVien>>() {
        @Override
        public void onResponse(Call<Response_Model<SinhVien>> call, Response<Response_Model<SinhVien>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    //gọi callback load lại dữ liệu
                    httpRequest.callAPI().getListSinhVien().enqueue(getSinhVienAPI);
                }
            }
        }

        @Override
        public void onFailure(Call<Response_Model<SinhVien>> call, Throwable t) {
            Log.d(">>>GetListDistributor", "onFailure: " + t.getMessage());
        }
    };
    @Override
    public void Delete(String id) {
        httpRequest.callAPI().deleteSinhVien(id).enqueue(responseSinhVienAPI);
    }

    @Override
    public void Update(String id, SinhVien sinhVien) {

    }
}