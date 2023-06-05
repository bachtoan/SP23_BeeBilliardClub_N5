package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mob104.fpoly.myapplication.R;

public class Activity_banchoi extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    TextView tvTimeCur;
    LinearLayout btnTime;
    TextView tvTime;
    ImageView img;
    boolean isPlaying = false;


    TextView tvshowTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_qlgiochoi_ql);
        TextView textView = findViewById(R.id.tv_batdauchoi_qlgiochoi_ql);
        TextView textView2 = findViewById(R.id.tv_thoigianchoi_qlgiochoi_ql);
        Button btnHuy = findViewById(R.id.btn_Cancel_qlgiochoi_ql);
        Button btnHoantat = findViewById(R.id.btn_hoantat_qlgiochoi_ql);

        Intent intent = getIntent();
        String stringStartTime = intent.getStringExtra("start");
        String price = intent.getStringExtra("price");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date startTime = dateFormat.parse(stringStartTime);
            Date endDate = new Date(); // Lấy thời điểm hiện tại
            long duration = endDate.getTime() - startTime.getTime();
            long hours = duration / (60 * 60 * 1000);
            long minutes = (duration % (60 * 60 * 1000)) / (60 * 1000);
            // long seconds = (duration % (60 * 1000)) / 1000;

            textView2.setText("Thời gian chơi : " + hours + " giờ " + minutes + " phút ");

        } catch (ParseException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
            // aaa
        }

        textView.setText("Bắt đầu chơi : " + stringStartTime);


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnHoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateTime = dateFormat.format(date);
                //SaveEndTime(position, currentDateTime);

                Intent intent1 = new Intent(Activity_banchoi.this, Activity_hoadon_nhanvien.class);
                intent1.putExtra("start", stringStartTime);
                intent1.putExtra("end",currentDateTime);
                intent1.putExtra("price", price);
                startActivity(intent1);

            }
        });
    }

    private void SaveEndTime(String position, String endTime){
        String path ="Table/" + position;
        DatabaseReference ref = firebaseDatabase.getReference(path);
        Log.d("zzzzz", "SaveEndTime: " + ref);

        ref.child("end").setValue(endTime);

    }
}