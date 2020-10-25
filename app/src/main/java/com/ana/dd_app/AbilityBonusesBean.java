package com.ana.dd_app;
//modelo para as ability bonuses tipico de cada raça. mais uma vez, as propriedades sao as mesmas que estao na api p serem bem "coladas" no nosso modelo
public class AbilityBonusesBean {
    private String index;
    private String name;
    private int bonus;
    private String url;

//_____ GETTERS: como so vou usar o name e o bn=onus so faço getter desses dois
    public String getName() {
        return name;
    }

    public int getBonus() {
        return bonus;
    }
}
