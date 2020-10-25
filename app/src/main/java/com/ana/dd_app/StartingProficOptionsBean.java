package com.ana.dd_app;

import java.util.ArrayList;

public class StartingProficOptionsBean {
    private int choose;
    private String type;
    private ArrayList<DetailsModelBean> from;

//________ GETTER: so vou usar a info em choose, logo so getter de choose
    public int getChoose() {
        return choose;
    }

    public ArrayList<DetailsModelBean> getFrom() {
        return from;
    }
}
