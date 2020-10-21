/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

import java.util.Objects;

/**
 *
 * @author oujia
 */
public class OrderSummaryItem {
    private float preis;
    private String bezeichnung;
    private int stueckanzahl;

    public OrderSummaryItem(float preis, String bezeichnung, int stueckanzahl) {
        this.preis = preis;
        this.bezeichnung = bezeichnung;
        this.stueckanzahl = stueckanzahl;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getStueckanzahl() {
        return stueckanzahl;
    }

    public void setStueckanzahl(int stueckanzahl) {
        this.stueckanzahl = stueckanzahl;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Float.floatToIntBits(this.preis);
        hash = 89 * hash + Objects.hashCode(this.bezeichnung);
        hash = 89 * hash + this.stueckanzahl;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderSummaryItem other = (OrderSummaryItem) obj;
        return true;
    }
    
    
}
