package com.example.stoper_minutnik;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.ToneGenerator;

public class MainActivity extends AppCompatActivity {

    TextView stoper, minutnik;
    EditText sekundy;

    LinearLayout layoutStoper, layoutMinutnik;

    Handler handler = new Handler();

    long start, zapis;
    boolean dziala = false;

    long czas = 0;
    CountDownTimer timer;
    boolean timerDziala = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stoper = findViewById(R.id.stoper);
        minutnik = findViewById(R.id.minutnik);
        sekundy = findViewById(R.id.sekundy);

        layoutStoper = findViewById(R.id.layoutStoper);
        layoutMinutnik = findViewById(R.id.layoutMinutnik);

        findViewById(R.id.btnStoper).setOnClickListener(v -> {
            layoutStoper.setVisibility(View.VISIBLE);
            layoutMinutnik.setVisibility(View.GONE);
        });

        findViewById(R.id.btnMinutnik).setOnClickListener(v -> {
            layoutStoper.setVisibility(View.GONE);
            layoutMinutnik.setVisibility(View.VISIBLE);
        });


        findViewById(R.id.startStoper).setOnClickListener(v -> {
            if (!dziala) {
                start = SystemClock.uptimeMillis();
                handler.post(run);
                dziala = true;
            }
        });

        findViewById(R.id.pauzaStoper).setOnClickListener(v -> {
            if (dziala) {
                zapis += SystemClock.uptimeMillis() - start;
                handler.removeCallbacks(run);
                dziala = false;
            }
        });

        findViewById(R.id.resetStoper).setOnClickListener(v -> {
            handler.removeCallbacks(run);
            start = 0;
            zapis = 0;
            dziala = false;
            stoper.setText("00:00:00");
        });


        findViewById(R.id.startMinutnik).setOnClickListener(v -> {

            if (!timerDziala) {

                if (czas == 0)
                    czas = Integer.parseInt(sekundy.getText().toString()) * 1000L;

                timer = new CountDownTimer(czas, 1000) {

                    @Override
                    public void onTick(long l) {

                        czas = l;

                        int s = (int) Math.ceil(l / 1000.0);
                        int m = s / 60;

                        s %= 60;

                        minutnik.setText(String.format("%02d:%02d", m, s));
                    }

                    @Override
                    public void onFinish() {

                        minutnik.setText("00:00");

                        timerDziala = false;
                        czas = 0;

                        String text = "Czas minął!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(MainActivity.this, text, duration);

                        toast.show();

                        ToneGenerator toneGen =
                                new ToneGenerator(AudioManager.STREAM_MUSIC, 100);

                        toneGen.startTone(
                                ToneGenerator.TONE_CDMA_ALERT_INCALL_LITE, 10);
                    }
                }.start();

                timerDziala = true;
            }
        });

        findViewById(R.id.pauzaMinutnik).setOnClickListener(v -> {
            if (timerDziala) {
                timer.cancel();
                timerDziala = false;
            }
        });

        findViewById(R.id.resetMinutnik).setOnClickListener(v -> {
            if (timer != null)
                timer.cancel();

            czas = 0;
            timerDziala = false;
            minutnik.setText("00:00");
            sekundy.setText("");
        });
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {

            long ms = SystemClock.uptimeMillis() - start + zapis;

            int s = (int) (ms / 1000);
            int m = s / 60;
            int setne = (int) (ms % 1000) / 10;

            s %= 60;

            stoper.setText(String.format("%02d:%02d:%02d", m, s, setne));

            handler.postDelayed(this, 10);
        }
    };
}