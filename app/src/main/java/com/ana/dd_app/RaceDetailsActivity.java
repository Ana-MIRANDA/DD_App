package com.ana.dd_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class RaceDetailsActivity extends AppCompatActivity {

    //para poder usar funcao que esta em WSUtils:
    private WSUtils wsutils  = new WSUtils();

    //declarar variaveis/propeidades que sao componentes graficos
    TextView tvraceName; //variavel tvraceName que é do tipo TextView
    TextView tvSubRaces;
    TextView tvSpeed;
    TextView tvSize;
    TextView tvAbilityBonuses;
    TextView  tvStartingProficienciesOptions;
    TextView tvTraits;



//______ Funçao que ao correr apresenta o xml de RaceDetailsActivity __________

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_details);

        //ir buscar os TextViews acima definidos/criados por id
        tvraceName = findViewById(R.id.tvraceName);
        tvSubRaces = findViewById(R.id.tvSubRaces);
        tvSpeed = findViewById(R.id.tvSpeed);
        tvSize = findViewById(R.id.tvSize);
        tvAbilityBonuses = findViewById(R.id.tvAbilityBonuses);
        tvStartingProficienciesOptions = findViewById(R.id. tvStartingProficienciesOptions);
        tvTraits = findViewById(R.id.tvTraits);

        }//Fecha a funçao onCreate


//________ Indicar raca escolhida no textview depois de clicada uma raça _________

    //quando se volta a um ecra usa-se onResume ver grafico dos slides do prof
    @Override
    protected void onResume() {
        super.onResume();
        if( getIntent().getExtras()!= null){
            Bundle bundle = getIntent().getExtras();
            String youChose = bundle.getString("chosen"); //esta chave vem de raceadapter
            String youChoseIndex = bundle.getString("chosenIndex");

            tvraceName.setText("Your Race is: " + youChose);

            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();

            //vai buscar os detalhes todos
                    RaceDetailsAPIBean detalhes = wsutils.alimentarModeloDetails(youChoseIndex); //chamar a funçao que esta dentro da classse WSUtils instanciada acima e retorna a lista de racas a guardar em listaDeRacas

              runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //setText subracas
                            if(detalhes.getSubraces().size() > 0){
                                for (SubRaceBean raca : detalhes.getSubraces()) {
                                    tvSubRaces.append(raca.getName()+" ");
                                }
                            }


                            //SetText speed
                            tvSpeed.append(Integer.toString(detalhes.getSpeed()));

                            //setText ability bonuses
                            if (detalhes.getAbility_bonuses() != null){
                                for (AbilityBonusesBean ability : detalhes.getAbility_bonuses()) {
                                    tvAbilityBonuses.append(ability.getName() + ": " + ability.getBonus() + "; " );
                                }

                            }


                            //setText Size:
                            tvSize.append(detalhes.getSize());


                            //setText Starting Proficiencies
                            if ( detalhes.getStarting_proficiency_options() != null) {
                                String s= ""; //s de string
                                String proficNames = "";
                                s= "You can choose : " + detalhes.getStarting_proficiency_options().getChoose()+ " of ";
                                for ( DetailsModelBean profic :  detalhes.getStarting_proficiency_options().getFrom()) {
                                    proficNames += profic.getName() + ", ";
                                }
                                tvStartingProficienciesOptions.append(s + proficNames );
                            }



                            //setText Traits
                            if (detalhes.getTraits() != null){
                                for ( DetailsModelBean tra :  detalhes.getTraits()) {
                                    tvTraits.append(tra.getName()+", ");
                                }
                            }

                        }
                    });




                }
            }//fecha thread
                    ;thread.start();
        }
    }//fecha onResume

//_______










}//fecha a classe RaceDetailsActivity

