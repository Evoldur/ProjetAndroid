package vue.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import fr.uca.force_brumter.grouplocate.R;
import metier.Utilisateur;
import modele.ModelLocation;


public class ProfilFragment extends Fragment {
    private static final String TAG = "ProfilFragment";
    private EditText editPseudo = null;
    //private EditText editCourriel = null;
    private Spinner spinnerCouleur = null;
    //private EditText editPrenom = null;
    //private EditText editNom = null;
    private Button btn_valider = null;
    private static Utilisateur current_user;

    public static Utilisateur getCurrent_user() {
        return current_user;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil_activity, container, false);

        editPseudo = (EditText) view.findViewById(R.id.editTextPseudo);
        //editCourriel = (EditText) view.findViewById(R.id.editTextCourriel);
        spinnerCouleur = (Spinner) view.findViewById(R.id.spinnerCouleur);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.list_couleur, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCouleur.setAdapter(adapter);

        //editPrenom = (EditText) view.findViewById(R.id.editTextPrenom);
        //editNom = (EditText) view.findViewById(R.id.editTextNom);

        btn_valider = (Button)view.findViewById(R.id.btnValider);
        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pseudo = editPseudo.getText().toString();
                final String couleur = spinnerCouleur.getSelectedItem().toString();
                //final String prenom = editPrenom.getText().toString();
                //final String nom = editNom.getText().toString();

                if (pseudo.isEmpty()){
                    Toast.makeText(getContext(),"Veuillez compléter tous les champs SVP.",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    ModelLocation.getReferenceForKey(pseudo).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (!dataSnapshot.exists()) {
                                        current_user = new Utilisateur(pseudo,couleur);
                                        editPseudo.setEnabled(false);
                                        spinnerCouleur.setEnabled(false);
                                       // editPrenom.setEnabled(false);
                                       // editNom.setEnabled(false);
                                        btn_valider.setClickable(false);
                                        btn_valider.setVisibility(View.INVISIBLE);

                                    }
                                    else {
                                        Toast.makeText(getContext(),"Identifiant déjà utilisé.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.w(TAG, databaseError.toException());
                                }
                            });
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        editPseudo.setEnabled(true);
        spinnerCouleur.setEnabled(true);
        // editPrenom.setEnabled(true);
        // editNom.setEnabled(true);
        btn_valider.setClickable(true);
        btn_valider.setVisibility(View.VISIBLE);
        current_user = null;
        super.onResume();
    }
}
