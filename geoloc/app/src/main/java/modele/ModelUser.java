package modele;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import metier.Utilisateur;

import static modele.Model.mDatabase;

/**
 * Created by thbrumter on 25/03/17.
 */

public class ModelUser {
    private static Utilisateur user = null;
    private static final String TAG = "ModelUser";
    private static DatabaseReference myRef = mDatabase.child("Utilisateur");

    public static void pushUser(Utilisateur u) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(u.getPseudo(), u);
        myRef.updateChildren(childUpdates);
    }

    public static void deleteUser(Utilisateur u) {
        myRef.child(u.getPseudo()).removeValue();
    }

    public static DatabaseReference getReferenceForUser(String pseudo) {
        return myRef.child(pseudo);
    }

    public static Utilisateur getUser(String pseudo) {
        DatabaseReference ref = myRef.child(pseudo);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map m = (HashMap<String, Object>)dataSnapshot.getValue();
                user = new Utilisateur((String)m.get("pseudo"), (String)m.get("couleur")
                        /*(String)m.get("prenom"), (String)m.get("nom")*/);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value. ", databaseError.toException());
            }
        });
        Utilisateur u = user;
        user = null;
        return u;
    }

    public static DatabaseReference getReference() {
        return myRef;
    }
}
