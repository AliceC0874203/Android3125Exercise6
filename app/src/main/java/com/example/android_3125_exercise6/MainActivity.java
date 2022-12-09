package com.example.android_3125_exercise6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.example.android_3125_exercise6.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    int primeNumber = Math.abs(new Random().nextInt(1000));
    CountDownTimer timer;
    int seconds = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnPrime.setOnClickListener(this);
        binding.btnNotPrime.setOnClickListener(this);
        binding.image.setVisibility(View.GONE);

        timer = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (seconds > 0) {
                    seconds -= 1;
                } else {
                    resetTimer();
                }

                binding.time.setText(""+seconds);
            }

            public void onFinish() {
                resetTimer();
                timer.start();
            }
        }.start();
    }

    private void resetTimer() {
        seconds = 3;
        primeNumber = Math.abs(new Random().nextInt(1000));
        binding.text.setText(""+primeNumber);
    }

    private Boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnNotPrime) {
            checkTheAnswer(false);
        } else {
            checkTheAnswer(true);
        }
    }

    private void checkTheAnswer(Boolean isTrue) {
        binding.image.setVisibility(View.VISIBLE);
        if (isPrime(primeNumber) == isTrue) {
            binding.image.setImageResource(R.drawable.correct_icon);
        } else {
            binding.image.setImageResource(R.drawable.wrong_icon);
        }

        resetTimer();
    }
}