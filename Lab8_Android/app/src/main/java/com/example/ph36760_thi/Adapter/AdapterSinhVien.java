package com.example.ph36760_thi.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ph36760_thi.Handle.Handle_SinhVien;
import com.example.ph36760_thi.Model.SinhVien;
import com.example.ph36760_thi.R;
import com.example.ph36760_thi.Update_SinhVien;

import java.util.ArrayList;

public class AdapterSinhVien extends RecyclerView.Adapter<AdapterSinhVien.viewHolep> {

    private Context context;
    private ArrayList<SinhVien> list;
    private Handle_SinhVien handleSinhVien;

    public AdapterSinhVien(Context context, ArrayList<SinhVien> list, Handle_SinhVien handleSinhVien) {
        this.context = context;
        this.list = list;
        this.handleSinhVien = handleSinhVien;
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sinhvien, parent, false);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        SinhVien sinhVien = list.get(position);

        Glide.with(context).load(sinhVien.getHinhAnh_PH36760()).into(holder.imgSV);
        holder.tvName.setText(sinhVien.getHoTen_PH36760());
        holder.tvQue.setText(sinhVien.getQueQuan_PH36760());
        holder.tvDiem.setText(String.valueOf(sinhVien.getDiem_PH36760()));

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xoá không")
                        .setCancelable(false)
                        .setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                handleSinhVien.Delete(sinhVien.get_id());
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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), Update_SinhVien.class);
                intent.putExtra("name", sinhVien.getHoTen_PH36760());
                intent.putExtra("que", sinhVien.getQueQuan_PH36760());
                intent.putExtra("diem", sinhVien.getDiem_PH36760());
                intent.putExtra("id", sinhVien.get_id());
                intent.putExtra("anh", sinhVien.getHinhAnh_PH36760());

                holder.itemView.getContext().startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewHolep extends RecyclerView.ViewHolder {
        ImageView imgSV, imgDelete;
        TextView tvName, tvQue, tvDiem;

        public viewHolep(@NonNull View itemView) {
            super(itemView);
            imgSV = itemView.findViewById(R.id.img_sinhvien);
            imgDelete = itemView.findViewById(R.id.img_delete_sv);
            tvName = itemView.findViewById(R.id.tv_name_sinhvien);
            tvDiem = itemView.findViewById(R.id.tv_diem);
            tvQue = itemView.findViewById(R.id.tv_quequan);
        }
    }
}
