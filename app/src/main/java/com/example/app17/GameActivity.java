package com.example.app17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.text.NumberFormat;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity {
    LottieAnimationView lottie11, lottie12, lottie13, lottie14, lottie15, lottie21, lottie22, lottie23, lottie24, lottie25, lottie31, lottie32, lottie33, lottie34, lottie35, lottie41, lottie42, lottie43, lottie44, lottie45, lottie51, lottie52, lottie53, lottie54, lottie55;
    LottieAnimationView[] lottieArray = {lottie11, lottie12, lottie13, lottie14, lottie15, lottie21, lottie22, lottie23, lottie24, lottie25, lottie31, lottie32, lottie33, lottie34, lottie35, lottie41, lottie42, lottie43, lottie44, lottie45, lottie51, lottie52, lottie53, lottie54, lottie55};
    int[] lottieIdArray = {R.id.lottie_id_1_1, R.id.lottie_id_1_2, R.id.lottie_id_1_3, R.id.lottie_id_1_4, R.id.lottie_id_1_5, R.id.lottie_id_2_1, R.id.lottie_id_2_2, R.id.lottie_id_2_3, R.id.lottie_id_2_4, R.id.lottie_id_2_5, R.id.lottie_id_3_1, R.id.lottie_id_3_2, R.id.lottie_id_3_3, R.id.lottie_id_3_4, R.id.lottie_id_3_5, R.id.lottie_id_4_1, R.id.lottie_id_4_2, R.id.lottie_id_4_3, R.id.lottie_id_4_4, R.id.lottie_id_4_5, R.id.lottie_id_5_1, R.id.lottie_id_5_2, R.id.lottie_id_5_3, R.id.lottie_id_5_4, R.id.lottie_id_5_5};
    int level = 0;
    boolean[] programShots = {false, false, false, false, false};
    int programShot1, programShot2, myShot;
    boolean[] myShots = {false, false, false, false, false};
    TextView textViewCoefficient1, textViewCoefficient2, textViewCoefficient3, textViewCoefficient4, textViewCoefficient5;
    TextView[] textViewCoefficientArray = {textViewCoefficient1, textViewCoefficient2, textViewCoefficient3, textViewCoefficient4, textViewCoefficient5};
    int[] textViewIdCoefficient = {R.id.textViewCoefficient1, R.id.textViewCoefficient2, R.id.textViewCoefficient3, R.id.textViewCoefficient4, R.id.textViewCoefficient5};
    double allMoney=3000, bet=100, moneyInGame;
    double coefficient;
    TextView textViewAllMoney, textViewBet, textViewMoneyInGame;
    NumberFormat numberFormat;
    FrameLayout mainFrame;
    int[] drawableResource = {R.drawable.back1, R.drawable.back2, R.drawable.back3, R.drawable.back4, R.drawable.back5};
    int counter=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setGroupingUsed(true);

        init();

        textViewAllMoney.setText(String.valueOf(numberFormat.format(allMoney)));
        textViewBet.setText(String.valueOf(numberFormat.format(bet)));
        moneyInGame = bet;
        textViewMoneyInGame.setText(String.valueOf(numberFormat.format(moneyInGame)));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, SetBetFragment.class, null)
                .commit();

        handler();
/*        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 15000);*/
    }

    public void init() {
        for (int i = 0; i < lottieArray.length; i++) {
            lottieArray[i] = findViewById(lottieIdArray[i]);
        }

        for (int i = 0; i < textViewCoefficientArray.length; i++) {
            textViewCoefficientArray[i] = findViewById(textViewIdCoefficient[i]);
        }

        textViewAllMoney = findViewById(R.id.textViewAllMoney);
        textViewBet = findViewById(R.id.textViewBet);
        textViewMoneyInGame = findViewById(R.id.textViewMoneyInGame);
        mainFrame = findViewById(R.id.id_main_frame);
    }

    public void start() {

        //Заполнить водой
        if(level==0){
            mainFrame.setBackground(getDrawable(R.drawable.back1));
            allMoney-=bet;
            int count=0;
            for (LottieAnimationView lottieAnimationView : lottieArray) {
                if(count<5){
                    lottieAnimationView.setAnimation(R.raw.coin1);
                    lottieAnimationView.playAnimation();
                }else if(count >=5 && count<10){
                    lottieAnimationView.setAnimation(R.raw.coin2);
                    lottieAnimationView.playAnimation();
                }else if(count >=10 && count<15){
                    lottieAnimationView.setAnimation(R.raw.coin3);
                    lottieAnimationView.playAnimation();
                }else if(count >=15 && count<20){
                    lottieAnimationView.setAnimation(R.raw.coin4);
                    lottieAnimationView.playAnimation();
                }else if(count >=20 && count<25){
                    lottieAnimationView.setAnimation(R.raw.coin5);
                    lottieAnimationView.playAnimation();
                }
                count++;

            }
            //Задаем все кэфы белым цветом
            for (TextView textView : textViewCoefficientArray) {
                textView.setTextColor(getResources().getColor(R.color.white));
            }

            //Задаем allMoney и bet
            textViewAllMoney.setText(String.valueOf(numberFormat.format(allMoney)));
            textViewBet.setText(String.valueOf(numberFormat.format(bet)));
            moneyInGame = bet;
            textViewMoneyInGame.setText(String.valueOf(numberFormat.format(moneyInGame)));
        }
        for (int i = 0; i < programShots.length; i++) {
            programShots[i] = false;
            myShots[i] = false;
        }
        //Включить все клики
        for (LottieAnimationView lottieAnimationView : lottieArray) {
            lottieAnimationView.setClickable(true);
        }
        //Убрать кликабельность ненужних уровней
        switch (level){
            case 0:
                for (int i = 5; i < lottieArray.length; i++) {
                    lottieArray[i].setClickable(false);
                }
                coefficient = 1.8;
                break;
            case 1:
                for (int i = 0; i < 5; i++) {
                    lottieArray[i].setClickable(false);
                }
                for (int i = 10; i < lottieArray.length; i++) {
                    lottieArray[i].setClickable(false);
                }
                coefficient = 2.5;
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    lottieArray[i].setClickable(false);
                }
                for (int i = 15; i < lottieArray.length; i++) {
                    lottieArray[i].setClickable(false);
                }
                coefficient = 3.0;
                break;
            case 3:
                for (int i = 0; i < 15; i++) {
                    lottieArray[i].setClickable(false);
                }
                for (int i = 20; i < lottieArray.length; i++) {
                    lottieArray[i].setClickable(false);
                }
                coefficient = 4.0;
                break;
            case 4:
                for (int i = 0; i < 20; i++) {
                    lottieArray[i].setClickable(false);
                }
                coefficient = 5.0;
                break;
        }
    }

    public void play() {
        for (int i = 0; i < programShots.length; i++) {
            System.out.println(String.valueOf(programShots[i]));
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        for (int i = 0; i < myShots.length; i++) {
            System.out.println(String.valueOf(myShots[i]));
        }
        //Проверка
        int program1 = level*5+programShot1;
        int program2 = level*5+programShot2;
        int my = level*5+myShot;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(String.valueOf(my));
        System.out.println(String.valueOf(program1));
        System.out.println(String.valueOf(program2));
        checkResult(program1, program2, my);
        level++;

    }

    public void setBadStep(){
        do {
            Arrays.fill(programShots, false);
            programShot1 = random(0, 4);
            programShots[programShot1] = true;

            programShot2 = random(0, 4);
            programShots[programShot2] = true;
        }while (programShot1==programShot2);

        play();
    }

    public void checkResult(int program1, int program2, int my) {
        if(myShot != programShot1 && myShot != programShot2){
            //Показываем проигранные Лоти
            lottieArray[program1].setAnimation(R.raw.pirate_flag);
            lottieArray[program2].setAnimation(R.raw.pirate_flag);
            lottieArray[program1].playAnimation();
            lottieArray[program2].playAnimation();
            lottieArray[my].setAnimation(R.raw.location);
            lottieArray[my].playAnimation();

            textViewCoefficientArray[level].setTextColor(getResources().getColor(R.color.green));
            //Умножаем очки на коэф
            moneyInGame= bet * coefficient;
            textViewMoneyInGame.setText(String.valueOf(numberFormat.format(moneyInGame)));

        }else {
            lottieArray[program1].setAnimation(R.raw.pirate_flag);
            lottieArray[program2].setAnimation(R.raw.pirate_flag);
            lottieArray[program1].playAnimation();
            lottieArray[program2].playAnimation();
            moneyInGame=0;
            textViewMoneyInGame.setText(String.valueOf(numberFormat.format(moneyInGame)));
            level=0;
            //Предложить сыграть заново или выйти

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, GameOverFragment.class, null)
                    .commit();

        }
        if(level>5) {
            //Предложить сыграть заново или выйти
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, GameOverFragment.class, null)
                    .commit();
        }else {

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                }
            }, 1200);
        }
    }

    public int random(int min, int max) {
        return (int) (Math.random() * (max + 1 - min) + min);
    }

    public void onClick11(View view){
        click(0);
    }
    public void onClick12(View view){
        click(1);
    }
    public void onClick13(View view){
        click(2);
    }
    public void onClick14(View view){
        click(3);
    }
    public void onClick15(View view){
        click(4);
    }
    public void onClick21(View view){
        click(0);
    }
    public void onClick22(View view){
        click(1);
    }
    public void onClick23(View view){
        click(2);
    }
    public void onClick24(View view){
        click(3);
    }
    public void onClick25(View view){
        click(4);
    }
    public void onClick31(View view){
        click(0);
    }
    public void onClick32(View view){
        click(1);
    }
    public void onClick33(View view){
        click(2);
    }
    public void onClick34(View view){
        click(3);
    }
    public void onClick35(View view){
        click(4);
    }
    public void onClick41(View view){
        click(0);
    }
    public void onClick42(View view){
        click(1);
    }
    public void onClick43(View view){
        click(2);
    }
    public void onClick44(View view){
        click(3);
    }
    public void onClick45(View view){
        click(4);
    }
    public void onClick51(View view){
        click(0);
    }
    public void onClick52(View view){
        click(1);
    }
    public void onClick53(View view){
        click(2);
    }
    public void onClick54(View view){
        click(3);
    }
    public void onClick55(View view){
        click(4);
    }

    public void click(int number){
        myShots[number] = true;
        myShot=number;
        //Рандомно задать Плохой ход
        setBadStep();
    }

    public void onClickTakeMoney(View view){
        allMoney += moneyInGame;
        //textViewAllMoney.setText(String.valueOf(allMoney));
        textViewAllMoney.setText(String.valueOf(numberFormat.format(allMoney)));
        moneyInGame = bet;
        textViewMoneyInGame.setText(String.valueOf(numberFormat.format(moneyInGame)));
        level=0;
        //Предложить сыграть заново или выйти
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, GameOverFragment.class, null)
                .commit();
    }

    public void handler (){
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(counter>4){
                    counter=0;
                }
                mainFrame.setBackground(getDrawable(drawableResource[counter]));
                counter++;
                handler();
            }
        }, 25000);
    }
}