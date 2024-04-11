package com.example.ph36760_thi.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ph36760_thi.Handle.Handle_Teacher;
import com.example.ph36760_thi.Model.Teacher;
import com.example.ph36760_thi.R;
import com.example.ph36760_thi.Update_Teacher;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterTeacher extends RecyclerView.Adapter<AdapterTeacher.viewHolep> {
    private Context context;
    private ArrayList<Teacher> list;
    private Handle_Teacher handleTeacher;

    public AdapterTeacher(Context context, ArrayList<Teacher> list, Handle_Teacher handleTeacher) {
        this.context = context;
        this.list = list;
        this.handleTeacher = handleTeacher;
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher, parent, false);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {

        Teacher teacher = list.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###.###");
        Glide.with(context).load(teacher.getHinhAnh_PH36760()).into(holder.imgTeacher);
        holder.tvName.setText(teacher.getHoTen_PH36760());
        holder.tvQue.setText(teacher.getQueQuan_PH36760());
        holder.tvLuong.setText(decimalFormat.format(teacher.getLuong()));
        Log.d("Luong", "onBindViewHolder: " + teacher.getLuong());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xoá không")
                        .setCancelable(false)
                        .setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                handleTeacher.Delete(teacher.getTeacherID());
                                Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();

            }
        });

        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), Update_Teacher.class);
                intent.putExtra("name", teacher.getHoTen_PH36760());
                intent.putExtra("que", teacher.getQueQuan_PH36760());
                intent.putExtra("luong", teacher.getLuong());
                intent.putExtra("id", teacher.getTeacherID());
                intent.putExtra("anh", teacher.getHinhAnh_PH36760());
                intent.putExtra("nganh", teacher.getChuyenNganh_PH36760());

                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_details, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                TextView tvName = view.findViewById(R.id.tv_name_dg);
                TextView tvQue = view.findViewById(R.id.tv_que_dg);
                TextView tvLuong = view.findViewById(R.id.tv_luong_dg);
                TextView tvNganh = view.findViewById(R.id.tv_nganh_dg);
                ImageView imgAnh = view.findViewById(R.id.img_dg);

                tvName.setText(teacher.getHoTen_PH36760());
                tvQue.setText(teacher.getQueQuan_PH36760());
                tvLuong.setText(String.valueOf(teacher.getLuong()));
                tvNganh.setText(teacher.getChuyenNganh_PH36760());
                Glide.with(context).load(teacher.getHinhAnh_PH36760()).into(imgAnh);

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewHolep extends RecyclerView.ViewHolder {
        ImageView imgTeacher, imgDelete, imgUpdate;
        TextView tvName, tvQue, tvLuong;

        public viewHolep(@NonNull View itemView) {
            super(itemView);
            imgTeacher = itemView.findViewById(R.id.img_teacher);
            imgDelete = itemView.findViewById(R.id.img_delete_teacher);
            tvName = itemView.findViewById(R.id.tv_name_teacher);
            tvLuong = itemView.findViewById(R.id.tv_luong);
            tvQue = itemView.findViewById(R.id.tv_quequan_teacher);
            imgUpdate = itemView.findViewById(R.id.img_update_teacher);
        }
    }
}
