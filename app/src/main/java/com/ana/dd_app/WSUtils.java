package com.ana.dd_app;

//O WSUtil permite centralisar netse documento todas as funçoes que possam vir a ser repetidamente usadas.
// Assim as funçoes estao todas juntas aqui, so precisam de ser escritas uma vez aqui em vez de escritas na atividade onde estou
// a trabalhar ou no main thread.

//e uma classse que pertence ao model e gera as futuras requetes

import com.google.gson.Gson;

import java.util.ArrayList;

public class WSUtils {



    public ArrayList<RaceBean> alimentarModeloRaces(){
        String resultRaces= null;

        try {
            resultRaces = OkhttpUtils.getRequest("https://www.dnd5eapi.co/api/races"); //resultRaces é o resultado do pedido races à api, uma cadeia de caracteres / string que tem la dentro um json
            Gson gson = new Gson(); //instanciar a class gson. Criada pela google p trabalhar com json
            RacesAPIBean racesOfAPI = gson.fromJson(resultRaces, RacesAPIBean.class); //Gson nao instancia o objeto do contrutor, faz reflexion.
            // E como se nao precisasse sequer do construtor. o gson vai pegar no resultado da api e cola-lo no nosso modelo RacesApiBean(count e results)
            //COM O GSON PEGA-SE NO RESULTADO DO PEDIDO à api (string dentro da qual esta um json) para se pode usa-lo de forma a cola-lo no nosso modelo e alimentar o modelo

            final ArrayList<RaceBean> raceBeanArrayList = new ArrayList<>(); //instanciar um objeto do tipo arrayLisr mas ainda vazio
            raceBeanArrayList.clear();
             raceBeanArrayList.addAll(racesOfAPI.results);//meter as racas na lista
            //para ir buscara  descriçao a cada uma das racas. se fosse toda a descrçao seria dentro do for: race.fillDetails();

            //para ir buscar so as subraces
            for(RaceBean race : raceBeanArrayList){
                race.racesSubraces();
            }
            return racesOfAPI.results;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }//fecha alimentarModeloRaces






}//fecha a class Utils
