package com.hngy.zp.notepad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngy.zp.notepad.tabel.MyLog;

import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter {
    List<MyLog> myLogs = null;

    public MyAdapter1(List<MyLog> myLogs) {
        this.myLogs = myLogs;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tittle, date, operation, operationTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.tv_tittle);
            date = itemView.findViewById(R.id.tv_date);
            operation = itemView.findViewById(R.id.tv_operation);
            operationTime = itemView.findViewById(R.id.tv_operation_time);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.operation_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        MyLog myLog = myLogs.get(position);
        holder1.tittle.setText(myLog.getTittle_zp());
        holder1.operationTime.setText(myLog.getOperationTime());
        holder1.date.setText(myLog.getDate_zp());
        holder1.operation.setText(myLog.getOperation());
    }

    @Override
    public int getItemCount() {
        return myLogs != null ? myLogs.size() : 0;
    }
}
