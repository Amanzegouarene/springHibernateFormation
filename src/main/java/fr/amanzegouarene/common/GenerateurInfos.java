package fr.amanzegouarene.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by amanzego on 31/08/2017.
 */
public class GenerateurInfos {
    private InfosInterface generateur;

    public void setGenerateur(InfosInterface generateur) {
        this.generateur = generateur;
    }

    public String fournirLesDonnees(){
        return generateur.genererInfos();
    }

}
