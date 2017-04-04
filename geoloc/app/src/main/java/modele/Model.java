package modele;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by thbrumter on 24/03/17.
 */

public class Model {
    protected static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
}
