package vue;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.maps.SupportMapFragment;

import fr.uca.force_brumter.grouplocate.R;
import vue.fragment.GroupeFragment;
import vue.fragment.MapFragment;
import vue.fragment.ProfilFragment;
import vue.fragment.laMapActivity;


public class MainActivity extends FragmentActivity {
    private SupportMapFragment map = null;
    private GroupeFragment groupe = null;
    private ProfilFragment profil = null;
    private FragmentManager leFragmentManager;
    private Button button_profil = null;
    private Button button_map = null;
    private Button button_groupe = null;
    public static Boolean isCome = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        leFragmentManager = getSupportFragmentManager();
        button_profil = (Button) findViewById(R.id.buttonProfil);
        button_map = (Button) findViewById(R.id.buttonMap);
        button_groupe = (Button) findViewById(R.id.buttonGroupe);

        button_profil.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 clickProfil();
                                             }
                                         }
        );
        button_map.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              clickMap();
                                          }
                                      }
        );
        button_groupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGroupe();
            }
        });

        this.clickProfil();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause(){
        isCome = true;
        super.onPause();
    }

    // debut code gestion des fragments

    public void clickMap() {
        if(ProfilFragment.getCurrent_user() == null){
            Toast.makeText(MainActivity.this,"Veuillez d'abord valider.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (findViewById(R.id.fragment_map) != null) {

            cacherFragment(profil);
            cacherFragment(groupe);
            if (leFragmentManager.findFragmentById(R.id.fragment_map) instanceof MapFragment) {
                map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
                revelerFragment(map);
            }
            if (map == null) {
                // map = new MapFragment();
                //setContentView(R.layout.lamap);
                map = (SupportMapFragment) leFragmentManager.findFragmentById(R.id.testmap);

                //leFragmentManager.beginTransaction().add(R.id.fragment_map, map).commit();
                Intent intent = new Intent(this, laMapActivity.class);
                startActivity(intent);

            }
            // map.getMapAsync(this);
        }
    }

    public void clickProfil() {
        if (findViewById(R.id.fragment_profil) != null) {
            cacherFragment(map);
            cacherFragment(groupe);
            if (leFragmentManager.findFragmentById(R.id.fragment_profil) instanceof ProfilFragment) {
                profil = (ProfilFragment) leFragmentManager.findFragmentById(R.id.fragment_profil);
                revelerFragment(profil);
            }
            if (profil == null) {
                profil = new ProfilFragment();

                leFragmentManager.beginTransaction().add(R.id.fragment_profil, profil).commit();
            }
        }
    }

    public void clickGroupe() {
        if(ProfilFragment.getCurrent_user() == null){
            Toast.makeText(MainActivity.this,"Veuillez d'abord valider.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (findViewById(R.id.fragment_groupe) != null) {
            cacherFragment(map);
            cacherFragment(profil);
            if (leFragmentManager.findFragmentById(R.id.fragment_groupe) instanceof GroupeFragment) {
                groupe = (GroupeFragment) leFragmentManager.findFragmentById(R.id.fragment_groupe);
                revelerFragment(groupe);
            }
            if (groupe == null) {
                groupe = new GroupeFragment();

                leFragmentManager.beginTransaction().add(R.id.fragment_groupe, groupe).commit();
            }
        }
    }

    public void cacherFragment(Fragment frag) {
        if (frag != null)
            getSupportFragmentManager().beginTransaction().hide(frag).commit();
    }

    public void revelerFragment(Fragment frag) {
        if (frag.isHidden())
            getSupportFragmentManager().beginTransaction().show(frag).commit();
    }

    @Override
    public void onResume() {
        if(isCome == true) {
            this.clickProfil();
            super.onResume();
        }
        else{
            super.onResume();
        }
    }
}