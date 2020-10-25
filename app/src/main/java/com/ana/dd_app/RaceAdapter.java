package com.ana.dd_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

//RecyclerView + Row + Adapter: O recyclerview(so pode ter um filho mas pode ter mts netos)contem a row, ou seja o que vai aparecer de design
// O adapter é o que permite dar conteudo "escrito" a cd elemento design de row


public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.ViewHolder>{

    //Listas de races que vai ser alimentada pelo construtor raceBean
    private ArrayList<RaceBean> raceBeans;

    //constructor do raceAdapter quando o adapter e instanciado corre esta funçao que mete em r uma lista de raceBeans
    public RaceAdapter(ArrayList<RaceBean> r) {
        this.raceBeans = r;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_race, parent, false);
        return new RaceAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RaceBean theRaceBean = raceBeans.get(position); //neste textview apresenta-se o nome de cada raça

        holder.tv.setText(theRaceBean.getName());
       // System.out.println("O nome------------------" + theRace.getName());
        //neste textview apresenta-se a subrace da raca



            if(theRaceBean.objetSubraces.results.size() == 0){ //se o arraylist de subraces nao tiver nenhum index/nenhuma subrace
                holder.tvSubRaces.setText("");
            } else{
                for ( SubRaceBean subRaca : theRaceBean.objetSubraces.results)
                {
                    holder.tvSubRaces.append(subRaca.getName()); //indicar a propriedade nome da subrace
                    //se houvesse mais do que uma raca fariamos: holder.tvSubRaces.append(" " + subRaca.getName() + ","); para ficar com , entre as racas + if p verificar se era o ultimo p n ficar uma , no fim
                }
            }



        // aqui e o tv que aparece antes da foto para indicar por escrito a raca selecionada
        //o root e o linearout onde se clica p selecionar a raca
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("entrou no onclick" + v.toString());
                TextView chosenRace = v.findViewById(R.id.tv);
                TextView raceDescription = v.findViewById(R.id.tvSubRaces);


             //o toast q apresenta embaixo a raca escolhida
                Toast.makeText(v.getContext(), "You chose wisely. " + chosenRace.getText(), Toast.LENGTH_LONG).show();

            //mudar de ecra/activity apos o toast
                Intent i = new Intent(v.getContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("chosen", chosenRace.getText().toString());
                i.putExtras(bundle);
                v.getContext().startActivity(i);
            }
        });
    }

    //definir aqui os eleementos xml no qual o adapter deve incluir o conteudo que se quer
    @Override
    public int getItemCount() {
        return raceBeans.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv; // para apresentar o nome da raca
        TextView tvSubRaces;//para apreentar a descriçao da raca
        LinearLayout root; //o layout dentro do qual estao os textviews q sao netos do recyclerview
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           tv = itemView.findViewById(R.id.tv);
           root = itemView.findViewById(R.id.root);
           tvSubRaces = itemView.findViewById(R.id.tvSubRaces);
       }
   }
}//fecha a classe RaceAdapter
