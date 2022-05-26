package com.example.app17;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

public class GameOverFragment extends Fragment {
    View view;
    GameActivity ga;
    FrameLayout btnExit, btnPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game_over, container, false);

        btnExit = view.findViewById(R.id.buttonExit);
        btnPlay = view.findViewById(R.id.buttonPlay);

        if(getActivity()!=null){
            ga = (GameActivity)getActivity();
        }

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StartActivity.class);
                startActivity(intent);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(GameOverFragment.this).commit();
                ga.level=0;
                ga.start();
            }
        });

        return view;
    }
}