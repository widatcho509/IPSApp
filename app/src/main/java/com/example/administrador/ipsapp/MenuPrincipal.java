package com.example.administrador.ipsapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {
String codigo;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    GlobalClass globalClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         globalClass= (GlobalClass) getApplication();
        mSectionsPagerAdapter=  new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager= (ViewPager) findViewById(R.id.container);
        setmViewPager(mViewPager);
        TabLayout layout = (TabLayout) findViewById(R.id.tabs);
        layout.setupWithViewPager(mViewPager);

        final Intent intent = getIntent();
        codigo = intent.getStringExtra("UserEmailTAG");
        globalClass.setDni(codigo);
//       Log.i("DNI",codigo);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        final Context context = this;

            int id = item.getItemId();


            if (id== R.id.action_settings) {
               // String code=globalClass.getId();
                    Intent intent = new Intent(MenuPrincipal.this,Editar.class);
                //intent.putExtra("CODE",code);
                startActivity(intent);
                //Toast.makeText(MenuPrincipal.this, "hola", Toast.LENGTH_LONG).show();
                return  true;
            }


        return super.onOptionsItemSelected(item);
    }

    private void setmViewPager(ViewPager mViewPager) {
        SectionsPagerAdapter adapter= new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Informacion(),"Info Poli" );
        adapter.addFragment(new Anotarse() ,"Mesas" );

        mViewPager.setAdapter(adapter);
    }

}



