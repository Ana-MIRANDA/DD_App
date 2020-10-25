package com.ana.dd_app;
//Criar a classe de race cujo conteudo vai ser recuperado a api de d&d

import com.google.gson.Gson;

public class RaceBean {
  private String index ;
  private String name;
  private String url;
  //private String descriptionRace;

  //para ir buscar as subracas----------------:
    private String asSubraces;
    SubracesAPIBean objetSubraces;

    //o construtor cria-se automaticamnte: pousar o rato em cima do nome da variavel + add parameter constructor
    public RaceBean(String index, String name, String url) {
        this.index = index;
        this.name = name;
        this.url = url;
    }//fecha a funçao construtora Race


    //------Getters: para poder usar a info uma vez k as variaveis sao private
    public String getUrl() {
        return url;
    }
    public String getName() {
        return name;
    }
    public String getIndex() {
        return index;
    }

    //para ir buscar cada subraca: Ex: urlDaApi/races/dwarf/subraces
    public void racesSubraces() {
        try {
            //ir buscar as subraces + transformar em objeto(subracesapibean e subracebean)
            asSubraces = OkhttpUtils.getRequest("https://www.dnd5eapi.co/api/races/" + getIndex() + "/subraces"); //resultado de pedido de subraces
            Gson gson = new Gson(); //sp k s cria um gson temos de criar um modelo tipo subraceapibean p colar o resultado + com esta api tb criar o modelo do "filho" do modelo neste caso subracesbean k contem o k esta dentro da api p subraces
            objetSubraces = gson.fromJson(asSubraces, SubracesAPIBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }// fecha subraces

}//fecha a classe
