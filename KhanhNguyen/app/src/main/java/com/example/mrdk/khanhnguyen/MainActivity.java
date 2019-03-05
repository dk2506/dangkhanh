package com.example.mrdk.khanhnguyen;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    RecyclerView rcvKeyWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvKeyWords = findViewById(R.id.rcvKeyWords);

        ArrayList<Model_KeyWord> arrKW = new ArrayList<>();
        String [] a = new String[]{"xiaomi", "bitis hunter", "bts", "balo", "bitis hunter x", "tai nghe", "harry potter", "anker", "iphone", "balo nữ", "nguyễn nhật ánh", "đắc nhân tâm", "ipad", "senka", "tai nghe bluetooth", "son", "maybelline", "laneige", "kem chống nắng", "anh chính là thanh xuân của em"};

        List< String > lstItem = Arrays.asList(a);

        for(String item:lstItem)
            arrKW.add(new Model_KeyWord(item));
        Adapter_KeyWords adtKW = new Adapter_KeyWords(getApplicationContext(),arrKW);

        rcvKeyWords.setAdapter(adtKW);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvKeyWords.setLayoutManager(linearLayoutManager);
    }


}
