/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

import java.awt.Image;
import java.net.URI;

/**
 *
 * @author oujia
 */
public class Pizza {

    private String name;
    private String beschreibung;
    private URI link;
    private float preis;

    public Pizza(String name, String beschreibung, URI link, float preis) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.link = link;
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public URI getLink() {
        return link;
    }

    public void setLink(URI link) {
        this.link = link;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }
    
    

}
