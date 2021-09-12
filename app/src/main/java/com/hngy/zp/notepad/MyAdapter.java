package com.hngy.zp.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hngy.zp.notepad.tabel.NotePad;

import org.litepal.LitePal;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter {
    List<NotePad> notePadList = null;
    Context context;

    public MyAdapter(List<NotePad> notePadList, Context context) {
        this.notePadList = notePadList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tittle, date, aid;
        CheckBox selected;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.tv_tittle);
            date = itemView.findViewById(R.id.tv_date);
            aid = itemView.findViewById(R.id.tv_id);
            selected = itemView.findViewById(R.id.cb_checked);
            view = itemView;

        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notepad_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), UpdateNotePad.class);
                String tittle = viewHolder.tittle.getText().toString().trim();
                String date = viewHolder.date.getText().toString().trim();
                String aid = viewHolder.aid.getText().toString().trim();
                intent.putExtra("tittle", tittle);
                intent.putExtra("date", date);
                intent.putExtra("id", aid);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
//                关于Activity跳转，Context中有一个startActivity方法，
//                Activity继承自Context，重载了startActivity方法。
//                如果使用Activity的startActivity方法，不会有任何限制，
//                而如果使用Context的startActivity方法的話，就需要开启一个新的的task，
//                遇到这个异常，是因为使用了Context的startActivity方法。解决办法是，加一个flag。
            }
        });
        viewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                viewHolder.selected.setVisibility(View.VISIBLE);
                return true;
            }
        });
        viewHolder.selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("MyAdapter", String.valueOf(b));
                if (b) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("isSelected", 1);
                    Log.d("MyAdapter", viewHolder.aid.getText().toString().trim());
                    LitePal.updateAll(NotePad.class, contentValues, "id=?", viewHolder.aid.getText().toString().trim());
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("isSelected", 2);
                    LitePal.updateAll(NotePad.class, contentValues, "id=?", viewHolder.aid.getText().toString().trim());
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        NotePad notePad = notePadList.get(position);
        String date_zp = notePad.getDate_zp();
        String tittle_zp = notePad.getTittle_zp();
        String id_zp = String.valueOf(notePad.getId());
        viewHolder.date.setText(date_zp);
        viewHolder.tittle.setText(tittle_zp);
        viewHolder.aid.setText(id_zp);
    }

    @Override
    public int getItemCount() {
        return notePadList.size();
    }
}
