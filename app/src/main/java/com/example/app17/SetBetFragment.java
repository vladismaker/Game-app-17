package com.example.app17;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.NumberFormat;

public class SetBetFragment extends Fragment {
    View view;
    FrameLayout buttonSetBet;
    ImageView imageButtonDownBet, imageButtonUpBet;
    TextView textViewSetBet;
    GameActivity ga;
    NumberFormat numberFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_set_bet, container, false);
        buttonSetBet = (FrameLayout) view.findViewById(R.id.buttonSetBet);
        imageButtonDownBet = (ImageView)view.findViewById(R.id.betDown);
        imageButtonUpBet = (ImageView)view.findViewById(R.id.betUp);
        textViewSetBet = (TextView) view.findViewById(R.id.textViewSetBet);

        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setGroupingUsed(true);

        if(getActivity()!=null){
            ga = (GameActivity)getActivity();
        }

        imageButtonDownBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double minBet = 100.0;
                if(ga.bet >= (minBet+50.0)){
                    ga.bet = ga.bet - 50.0;
                    String stringBet = String.valueOf( numberFormat.format(ga.bet));
                    textViewSetBet.setText(stringBet);
                    ga.textViewBet.setText(stringBet);
                }
            }
        });

        imageButtonUpBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double maxBet = 1000.0;
                if(ga.bet <= (maxBet-50.0)){
                    ga.bet = ga.bet + 50.0;
                    String stringBet = String.valueOf(numberFormat.format(ga.bet));
                    textViewSetBet.setText(stringBet);
                    ga.textViewBet.setText(stringBet);
                }
            }
        });

        buttonSetBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ga.start();
                getActivity().getSupportFragmentManager().beginTransaction().remove(SetBetFragment.this).commit();
            }
        });

        return view;
    }
}