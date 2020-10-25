package com.ana.dd_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declarar variaveis que sao componentes graficos
    TextView tvraceName; //variavel tvraceName que é do tipo TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dar conteudo a variavel tvracNeme
        tvraceName = findViewById(R.id.tvraceName);

    }//Fecha a funcçao oncreate
    @Override
    protected void onResume() {
        super.onResume();
        if( getIntent().getExtras()!= null){
            Bundle bundle = getIntent().getExtras();
            String youChose = bundle.getString("chosen");

            tvraceName.setText("Your Race is: " + youChose);
        }
    }

    //Definir o menu e seu selementos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Choose Race" );
        menu.add(0, 2, 1, "Choose Class");
        return super.onCreateOptionsMenu(menu);
    }

    //Definir a funçao de cada element do menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case 1:
                chooseRace();
                break;
            case 2:
                chooseClass();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Funcao de cada elemento do menu (neste caso pedi a cada um deles para mudarem de activity/ecra para a chooseRace)
    private void chooseRace(){
        Intent intent = new Intent(this, ChooseRaceActivity.class);
        startActivity(intent);
    }

    //funçao k leva a mudanca de pagina/actividade/ecra = new intent()
    private void chooseClass(){
        Intent intent = new Intent(this, ChooseClassActivity.class);
        startActivity(intent);
    }


}//fecha a class MainActivity