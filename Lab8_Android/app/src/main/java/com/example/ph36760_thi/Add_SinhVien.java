package com.example.ph36760_thi;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ph36760_thi.Model.Response_Model;
import com.example.ph36760_thi.Model.SinhVien;
import com.example.ph36760_thi.Services.HttpRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_SinhVien extends AppCompatActivity {
    private ImageView imgAdd;
    private EditText edtName, edtQue, edtDiem;
    private Button btnAdd;

    private File file;

    private HttpRequest httpRequest;
    private ArrayList<SinhVien> listSinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);
        imgAdd = findViewById(R.id.img_add_sv);
        edtName = findViewById(R.id.edt_name_sv);
        edtQue = findViewById(R.id.edt_que);
        edtDiem = findViewById(R.id.edt_diem);
        btnAdd = findViewById(R.id.btn_add_sv);
        listSinhVien = new ArrayList<>();
        httpRequest = new HttpRequest();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _name = edtName.getText().toString();
                String queQuan = edtQue.getText().toString();
                String diemStr = edtDiem.getText().toString();

                if (TextUtils.isEmpty(_name) || TextUtils.isEmpty(queQuan) || TextUtils.isEmpty(diemStr)) {
                    Toast.makeText(Add_SinhVien.this, "Vui lòng nhập lại dữ liệu", Toast.LENGTH_SHORT).show();
                    return;
                }

                double diem;
                try {
                    diem = Double.parseDouble(diemStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(Add_SinhVien.this, "Điểm không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, RequestBody> bodyMap = new HashMap<>();

                //Put request body
                bodyMap.put("hoTen_PH36760", getRequestBody(_name));
                bodyMap.put("queQuan_PH36760", getRequestBody(queQuan));
                bodyMap.put("diem_PH36760", getRequestBody(String.valueOf(diem)));

                MultipartBody.Part muPart;
                if (file != null) {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                    muPart = MultipartBody.Part.createFormData("hinhAnh_PH36760", file.getName(), requestFile);
                    //"avatar" là cùng với key trong mutipart

                } else {
                    muPart = null;
                }
                httpRequest.callAPI().addSinhVien(bodyMap, muPart).enqueue(responseSinhVien);
            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }

    private void chooseImage() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            getImage.launch(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    ActivityResultLauncher<Intent> getImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == Activity.RESULT_OK) {
                Intent data = o.getData();
                Uri imagePath = data.getData();

                file = createFileFromUri(imagePath, "hinhAnh_PH36760");
                //gilde để load hinh
                Glide.with(Add_SinhVien.this).load(file)
                        .thumbnail(Glide.with(Add_SinhVien.this).load(R.mipmap.ic_launcher))
                        .centerCrop()
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(imgAdd);
            }
        }
    });

    private File createFileFromUri(Uri path, String name) {
        File _file = new File(Add_SinhVien.this.getCacheDir(), name + ".png");
        try {
            InputStream in = Add_SinhVien.this.getContentResolver().openInputStream(path);
            OutputStream out = new FileOutputStream(_file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
            return _file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RequestBody getRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }

    Callback<Response_Model<SinhVien>> responseSinhVien = new Callback<Response_Model<SinhVien>>() {
        @Override
        public void onResponse(Call<Response_Model<SinhVien>> call, Response<Response_Model<SinhVien>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    Toast.makeText(Add_SinhVien.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Add_SinhVien.this, MainActivity.class));
                    finish();
                }
            }
        }

        @Override
        public void onFailure(Call<Response_Model<SinhVien>> call, Throwable t) {
            Toast.makeText(Add_SinhVien.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
            Log.d("AddSinhVien", "onFailure: " + t.getMessage());
        }
    };
}