package modele;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import metier.PositionUtilisateur;
import metier.Utilisateur;

/**
 * Created by thbrumter on 24/03/17.
 */


public class ModelLocation extends Model {
    private static DatabaseReference myRef = mDatabase.child("/PositionUtilisateur/");
    public static void pushLocation(PositionUtilisateur p, Utilisateur u) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + u.getPseudo() + "/", p);
        myRef.updateChildren(childUpdates);
    }

    public static DatabaseReference getReference() {
        return myRef;
    }

    public static DatabaseReference getReferenceForKey(String pseudo) {
        return myRef.child(pseudo);
    }

    public static void deleteReference(Utilisateur u) {
        myRef.child(u.getPseudo()).removeValue();
    }
}
