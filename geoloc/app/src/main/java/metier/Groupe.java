package metier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thbrumter on 03/03/17.
 */

public class Groupe {
    private String idGroupe;
    private String pseudoCreateur;
    private String libelle;
    private List<String> utilisateurs;

    public Groupe(String idGroupe, String pseudoCreateur, String libelle) {
        this.idGroupe = idGroupe;
        this.pseudoCreateur = pseudoCreateur;
        this.libelle = libelle;
        this.utilisateurs = new ArrayList<>();
    }

    public void addUser(Utilisateur u) {
        utilisateurs.add(u.getPseudo());
    }

    public void delUser(Utilisateur u) {
        utilisateurs.remove(u.getPseudo());
    }

    public String getIdGroupe() {
        return idGroupe;
    }

    public String getPseudoCreateur() {
        return pseudoCreateur;
    }

    public String getLibelle() {
        return libelle;
    }

    public List<String> getUtilisateurs() {
        return utilisateurs;
    }
}
