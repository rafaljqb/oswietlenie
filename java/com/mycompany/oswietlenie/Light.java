/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oswietlenie;

/**
 *
 * @author rjaku
 */
public class Light {
    private boolean state;

    public Light() {
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Light{" + "state=" + state + '}';
    }
}
