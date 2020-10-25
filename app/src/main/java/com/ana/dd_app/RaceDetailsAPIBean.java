package com.ana.dd_app;

//modelo que vai receber toda a info vinda da API (pelas funcoes em WSUtils) por isso temos de ter as mesmas propriedades q a api tem
// dentro deste modelo para que a info possa ser "colada corretamente". Quando sao arraysLists criei beans
//ex:  private ArrayList<AbilityBonusesBean> ability_bonuses; a variavel ability_bonuses é do tipo ArrayList de AbilityBonusesBean

import java.util.ArrayList;

public class RaceDetailsAPIBean {
    private String index;
    private String name;
    private int speed;
    private ArrayList<AbilityBonusesBean> ability_bonuses; // vai criar uma lista com a nossa classe/modelo AbilityBonusesBean
    private String alignment;
    private String age;
    private String size;
    private String size_description;
    private ArrayList<DetailsModelBean>starting_proficiencies; //para nao ter de criar um model para todos os arrays e como tem todos os mesmos campos crio um q posso usar p todos:ver class/documento DetailsModelBean
    private StartingProficOptionsBean starting_proficiency_options;
    private ArrayList<DetailsModelBean>languages;
    private String language_desc;
    private ArrayList<DetailsModelBean>traits;
    private ArrayList<SubRaceBean>subraces; //neste ja tinha criado o SubRaceBean por isso nao usei o detailsModelBean
    private String url;

    public RaceDetailsAPIBean() {
    }

    public ArrayList<SubRaceBean> getSubraces() {
        return subraces;
    }

    public int getSpeed() {
        return speed;
    }

    public ArrayList<AbilityBonusesBean> getAbility_bonuses() {
        return ability_bonuses;
    }

    public String getSize() {
        return size;
    }

    public ArrayList<DetailsModelBean> getStarting_proficiencies() {
        return starting_proficiencies;
    }

    public ArrayList<DetailsModelBean> getTraits() {
        return traits;
    }

    public StartingProficOptionsBean getStarting_proficiency_options() {
        return starting_proficiency_options;
    }


    //agora no WSUtils crio as funçoes para pedir as infos para preencher cd campo dos textviews. O WSUtils é k e o repsonsavel por fazer pedidos à net pk foi nele k pus a funçao request
}
