package metier;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thbrumter on 10/02/17.
 */

public class Utilisateur {
    private String pseudo;
    private String couleur;
    private String prenom;
    private String nom;
    private List groupesCrees;

    public Utilisateur(String pseudo, String couleur/*, String prenom, String nom*/) {
        this.pseudo = pseudo;
        this.couleur = couleur; // /!!!\   faudrai me stocker ça en BDD s'il te plait :)
        //this.prenom = prenom;
        //this.nom = nom;
        this.groupesCrees = new ArrayList<String>();
    }

    public void addGroupe(Groupe g) {
        groupesCrees.add(g);
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getCouleur() {
        return couleur;
    }
/*
    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }
*/
    public List getGroupesCrees() {
        return groupesCrees;
    }

    public static float getMarkerColor(String value) {
        if(value.equals("Azure"))
            return BitmapDescriptorFactory.HUE_AZURE;
        if(value.equals("Bleu") || value.equals("Blue"))
            return BitmapDescriptorFactory.HUE_BLUE;
        if (value.equals("Cyan"))
            return BitmapDescriptorFactory.HUE_CYAN;
        if (value.equals("Vert") || value.equals("Green"))
            return BitmapDescriptorFactory.HUE_GREEN;
        if (value.equals("Magenta"))
            return BitmapDescriptorFactory.HUE_MAGENTA;
        if (value.equals("Orange"))
            return BitmapDescriptorFactory.HUE_ORANGE;
        if (value.equals("Rouge") || value.equals("Red"))
            return BitmapDescriptorFactory.HUE_RED;
        if (value.equals("Rose"))
            return BitmapDescriptorFactory.HUE_ROSE;
        if (value.equals("Violet"))
            return BitmapDescriptorFactory.HUE_VIOLET;
        return BitmapDescriptorFactory.HUE_YELLOW;
    }
}
