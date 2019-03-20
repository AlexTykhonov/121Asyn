package com.tae.a121asyn;

import android.content.ClipData;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] integers=null;
    int clicks = 0;
    ProgressBar indicatorBar;
    TextView statusView;
    TextView clicksView;
    Button progressBtn;
    Button clicksBtn;
    Integer [] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        integers = new int[100];
        for(int i=0;i<100;i++) {
            integers[i] = i + 1;
        }
        indicatorBar = (ProgressBar) findViewById(R.id.indicator);
//        for (int i = 0; i<integers.length;i++) {
//            indicatorBar.setProgress(i+1);
//          //  statusView.setText("Статус: " + String.valueOf(i+1));
//            SystemClock.sleep(400);
//        }
        statusView = (TextView) findViewById(R.id.statusView);
        progressBtn = (Button) findViewById(R.id.progressBtn);
        progressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProgressTask().execute();
            }
        });

        clicksView = (TextView) findViewById(R.id.clicksView);
        clicksBtn = (Button) findViewById(R.id.clicksBtn);
        clicksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicks++;
                clicksView.setText("Clicks: " + clicks);
            }
        });
    }

    class ProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... unused) {
            for (int i = 0; i<integers.length;i++) {
//метод который передает в строчку прогресса значение увеличивающего счетчика i

                publishProgress(i);
                SystemClock.sleep(400);
            }
            return(null);
        }

        // этот метод принимает в себя результат работы метода publishprogress
        @Override
        protected void onProgressUpdate(Integer... items) {
           // items сюда передается значение в матрицу
            indicatorBar.setProgress(items[0]+1);
            statusView.setText("Статус: " + String.valueOf(items[0]+1));
        }
        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getApplicationContext(), "Задача завершена", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}