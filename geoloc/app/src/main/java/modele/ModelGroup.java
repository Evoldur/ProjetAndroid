package modele;

import java.util.HashMap;
import java.util.Map;

import metier.Groupe;

import static modele.Model.mDatabase;

/**
 * Created by thbrumter on 25/03/17.
 */

public class ModelGroup {
    public static void pushGroup(Groupe g) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("Groupe", g);
        mDatabase.updateChildren(childUpdates);
    }
}
