package com.ana.dd_app;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class ChooseRaceActivity extends AppCompatActivity {


//_____ ELEMENTOS GRAFICOS E ARRAYS _________________

    private WSUtils wsutils  = new WSUtils(); // instanciar a classe para se poder usar a funçao que esta dentro dessa classe em WSUtils.java
        // se nao quisessemos instanciar poderiamos escrever do new WSUtils().alimentarModeloRaces(); no sitio onde suisessemos usar a funçao alimentarModeloRaces()

    //Criar lista de races
    private final ArrayList<RaceBean> raceBeanArrayList = new ArrayList<>();

    //Outils: adapter instanciar o adapter faz  correr o construtor de adapter, a funçao que esta em RaceAdpter:
    // public RaceAdapter(ArrayList<RaceBean> r) {this.raceBeans = r;}
    private RaceAdapter adapter = new RaceAdapter(raceBeanArrayList);//esta lista é a q e passada ao adapter para ele mostrar no textview como eu lhe peco


    RecyclerView rv ; //ir buscar o recyclerView
    Button btnRandom;
    ImageView diceImage;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_race);

        // elementos graficos para rolar dados para escolher ao acaso uma personagem. Com animacao: o dado roda e mostra o resultado de 1 a 6
        diceImage = findViewById(R.id.dice_image);
        btnRandom = findViewById(R.id.btnRandom);

//_____ RACAS _________________________
    Thread thread = new Thread(){
        @Override
        public void run() {
            super.run();
            System.out.println("-------------------a lista racebeanArrayList tem la dentro : " + raceBeanArrayList );
            try {
             ArrayList<RaceBean> listaDeRaças = wsutils.alimentarModeloRaces(); //chamar a funçao que esta dentro da classse WSUtils instanciada acima e retorna a lista de racas a guardar em listaDeRacas
             raceBeanArrayList.addAll(listaDeRaças);

//______ MUDAR COMPONENTES GRAFICOS: runInOnThread____________

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter.notifyDataSetChanged();//avisar  o adapter de que a lista mudou

//_______________________ ANIMACAO RODAR DADO RANDOM  ______

                        btnRandom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rotateDice();
                                int i = random.nextInt(raceBeanArrayList.size());
                                String n = raceBeanArrayList.get(i).getName();
                                String indexRaca = raceBeanArrayList.get(i).getIndex();

//_____________________________________  TOAST  ______________

                                Toast.makeText(view.getContext(), "Your random race is:  " + n, Toast.LENGTH_LONG).show();

//_______ RANDOM: MUDAR ECRA COM DELAY DE 1.5 SEGUNDOS P SE VER A ANIMACAO DO DADO ____

     // em chooseRace so eesta o onclick do botao random pk esse botao faz parte do xml de chooseRace_activity enquanto q cada linha de raca
        // faz parte do adapter logo o seu evento onclick nao pode aprecer neste documento

                                new Handler().postDelayed(new Runnable(){ //o ahandeler e uma cena do java
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                                        Bundle bundle = new Bundle(); //bundle e um tipo de variavel k recebe todos os tipos de variaveis
                                        bundle.putString("chosen", n);
                                        bundle.putString("chosenIndex",   indexRaca);

                                        intent.putExtras(bundle);

                                        view.getContext().startActivity(intent);
                                    }
                                }, 1500); //fecha o handler //1500 é o tempo de espera para mudar de activity

                            }
                        });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
        thread.start();

        //RecyclerView
        rv = findViewById(R.id.rv);

        //o adapter a controlar a rv
        rv.setAdapter(adapter);
        //a forma como se vai apresentar:linhas ou colonas.Neste caso linhas
        rv.setLayoutManager(new GridLayoutManager(this,1));

    }//fecha a oncreacte


//______________________________________________________________ ANIMACAO RANDOM RACE ____________________________________________

    //Animacao para rodar o dado: com imagem k acompanha o rolar do dado e apresenta o resultado k saiu de 1 a 6
    //Ha 9 racas mas o dado so vai ate 6. Ver se posso usar 1d10-1 k é = a 9 o num d racas desta api
    private void rotateDice() {
        int i = random.nextInt(5)+1;
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        diceImage.startAnimation(anim);
        switch (i){
            case 1:
                diceImage.setImageResource(R.drawable.dice1); //dice1 é o nome da imagem guardada em drawable
                break;
            case 2:
                diceImage.setImageResource(R.drawable.dice2);
                break;
            case 3:
                diceImage.setImageResource(R.drawable.dice3);
                break;
            case 4:
                diceImage.setImageResource(R.drawable.dice4);
                break;
            case 5:
                diceImage.setImageResource(R.drawable.dice5);
                break;
            case 6:
                diceImage.setImageResource(R.drawable.dice6);
                break;
        }
    }
}//fecha a class