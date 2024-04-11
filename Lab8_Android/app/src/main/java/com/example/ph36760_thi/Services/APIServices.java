package com.example.ph36760_thi.Services;

import com.example.ph36760_thi.Model.Response_Model;
import com.example.ph36760_thi.Model.SinhVien;
import com.example.ph36760_thi.Model.Teacher;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServices {
    public static String BASE_URL = "http://10.0.2.2:3000/api/";

    @Multipart
    @POST("add-sinhvien")
    Call<Response_Model<SinhVien>> addSinhVien(@PartMap Map<String, RequestBody> requestBodyMap,
                                         @Part MultipartBody.Part image);

    //Param url sẽ bỏ vào {}
    @DELETE("delete-sinhvien/{id}")
    Call<Response_Model<SinhVien>> deleteSinhVien(@Path("id") String id);

    @Multipart
    @PUT("update-sinhvien/{id}")
    Call<Response_Model<SinhVien>> updateSinhVien(@Path("id") String id, @PartMap Map<String,RequestBody> requestBodyMap,
                                                  @Part MultipartBody.Part image);

    @GET("get-list-sinhvien")
    Call<Response_Model<ArrayList<SinhVien>>> getListSinhVien ();

    //teacher
    @Multipart
    @POST("add-teacher")
    Call<Response_Model<Teacher>> addTeacher(@PartMap Map<String, RequestBody> requestBodyMap,
                                              @Part MultipartBody.Part image);
    @DELETE("delete-teacher/{id}")
    Call<Response_Model<Teacher>> deleteTeacher(@Path("id") String id);

    @Multipart
    @PUT("update-teacher/{id}")
    Call<Response_Model<Teacher>> updateTeacher(@Path("id") String id, @PartMap Map<String,RequestBody> requestBodyMap,
                                                  @Part MultipartBody.Part image);


    @GET("get-list-teacher")
    Call<Response_Model<ArrayList<Teacher>>> getListTeacher();

    @GET("search")
    Call<Response_Model<ArrayList<Teacher>>> searchTeacher(@Query("key") String key);
}
