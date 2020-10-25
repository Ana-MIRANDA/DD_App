package com.ana.dd_app;
//api usada: https://www.dnd5eapi.co/
//racesApi e uma classe q tem duas propriedades: um int e uma arrayList
//este objeto esta a servir de modelo para receber o resultado da api. A API retorna um Json com um int chamado "count" e um
// arrayList chamado "results"

//assim, e uma classe que pertence ao Model do MVC pk recebe o resultado/dados  neste caso de uma api podia ate ser d uma base de dados

import java.util.ArrayList;

public class RacesAPIBean {
    public int count; //count é tb o nome que esta na api
    public ArrayList<RaceBean> results; // results é tb o nome k a api deu a sua arrayList

}
