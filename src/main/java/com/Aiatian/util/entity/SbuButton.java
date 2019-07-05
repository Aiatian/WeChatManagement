package com.Aiatian.util.entity;

import java.util.ArrayList;
import java.util.List;

public class SbuButton extends  AbstractButton{

    private List<AbstractButton> sub_button = new ArrayList<>();

    public SbuButton(String name) {
        super(name);
    }

    public List<AbstractButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AbstractButton> sub_button) {
        this.sub_button = sub_button;
    }
}
