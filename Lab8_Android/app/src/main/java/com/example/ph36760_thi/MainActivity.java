package com.example.ph36760_thi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph36760_thi.Adapter.AdapterSinhVien;
import com.example.ph36760_thi.Adapter.AdapterTeacher;
import com.example.ph36760_thi.Handle.Handle_SinhVien;
import com.example.ph36760_thi.Handle.Handle_Teacher;
import com.example.ph36760_thi.Model.Response_Model;
import com.example.ph36760_thi.Model.SinhVien;
import com.example.ph36760_thi.Model.Teacher;
import com.example.ph36760_thi.Services.HttpRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Handle_Teacher {
    private HttpRequest httpRequest;
    private AdapterSinhVien adapterSinhVien;
    private AdapterTeacher adapterTeacher;
    private RecyclerView rcvMain;
    FloatingActionButton btn_add;
    EditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvMain = findViewById(R.id.rcv_main);
        btn_add = findViewById(R.id.btn_add);
        edtSearch = findViewById(R.id.edt_search);
        httpRequest = new HttpRequest();
        httpRequest.callAPI().getListTeacher().enqueue(getTeacherAPI);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Add_Teacher.class));
            }
        });
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //lấy từ khoá từ ô tìm kiếm
                    String key = edtSearch.getText().toString();

                    httpRequest.callAPI().searchTeacher(key)//phương thức api cần thực thi
                            .enqueue(getTeacherAPI);//xử lý bất đồng bộ
                    //vì giá trị trả về vẫ là một list distributor
                    //nên có thể sử dụng lại callback của getListDistributor
                    return true;
                }

                return false;
            }
        });
    }


    private void getData(ArrayList<Teacher> list) {
        adapterTeacher = new AdapterTeacher(this, list, this);
        rcvMain.setLayoutManager(new LinearLayoutManager(this));
        rcvMain.setAdapter(adapterTeacher);

    }

//    Callback<Response_Model<ArrayList<SinhVien>>> getSinhVienAPI = new Callback<Response_Model<ArrayList<SinhVien>>>() {
//        @Override
//        public void onResponse(Call<Response_Model<ArrayList<SinhVien>>> call, Response<Response_Model<ArrayList<SinhVien>>> response) {
//            // khi call thành công sẽ chạy vào hàm này
//            if (response.isSuccessful()) {
//                // check status
//                if (response.body().getStatus() == 200) {
//                    // lấy data
//                    ArrayList<> list = response.body().getData();
//                    Log.d("List", "onResponse: " + list);
//                    // set dữ liệu lên rcv
//                    getData(list);
//                    // Thông báo
////                    Toast.makeText(MainActivity.this, response.body().getMessenger(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//
//        @Override
//        public void onFailure(Call<Response_Model<ArrayList<SinhVien>>> call, Throwable t) {
//            Log.d(">>>GetListDistributor", "onFailure: " + t.getMessage());
//        }
//    };
//    Callback<Response_Model<SinhVien>> responseSinhVienAPI = new Callback<Response_Model<SinhVien>>() {
//        @Override
//        public void onResponse(Call<Response_Model<SinhVien>> call, Response<Response_Model<SinhVien>> response) {
//            if (response.isSuccessful()) {
//                if (response.body().getStatus() == 200) {
//                    //gọi callback load lại dữ liệu
//                    httpRequest.callAPI().getListSinhVien().enqueue(getSinhVienAPI);
//                }
//            }
//        }
//
//        @Override
//        public void onFailure(Call<Response_Model<SinhVien>> call, Throwable t) {
//            Log.d(">>>GetListDistributor", "onFailure: " + t.getMessage());
//        }
//    };


    Callback<Response_Model<ArrayList<Teacher>>> getTeacherAPI = new Callback<Response_Model<ArrayList<Teacher>>>() {
        @Override
        public void onResponse(Call<Response_Model<ArrayList<Teacher>>> call, Response<Response_Model<ArrayList<Teacher>>> response) {
            // khi call thành công sẽ chạy vào hàm này
            if (response.isSuccessful()) {
                // check status
                if (response.body().getStatus() == 200) {
                    // lấy data
                    ArrayList<Teacher> list = response.body().getData();
                    Log.d("List", "onResponse: " + list);
                    // set dữ liệu lên rcv
                    getData(list);
                    // Thông báo
//                    Toast.makeText(MainActivity.this, response.body().getMessenger(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response_Model<ArrayList<Teacher>>> call, Throwable t) {
            Log.d(">>>GetListDistributor", "onFailure: " + t.getMessage());
        }
    };

        Callback<Response_Model<Teacher>> responseTeacherAPI = new Callback<Response_Model<Teacher>>() {
        @Override
        public void onResponse(Call<Response_Model<Teacher>> call, Response<Response_Model<Teacher>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    //gọi callback load lại dữ liệu
                    httpRequest.callAPI().getListTeacher().enqueue(getTeacherAPI);
                }
            }
        }

        @Override
        public void onFailure(Call<Response_Model<Teacher>> call, Throwable t) {
            Log.d(">>>GetListDistributor", "onFailure: " + t.getMessage());
        }
    };

    @Override
    public void Delete(String id) {
        httpRequest.callAPI().deleteTeacher(id).enqueue(responseTeacherAPI);
    }

    @Override
    public void Update(String id, SinhVien sinhVien) {

    }
}