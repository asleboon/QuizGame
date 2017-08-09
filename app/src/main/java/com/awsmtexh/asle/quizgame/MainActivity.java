package com.awsmtexh.asle.quizgame;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button mRettKnapp;
    private Button mFeilKnapp;
    private Button mJukseKnapp;
    private Button mNesteKnapp;
    private Button mRelativKnapp;
    private TextView mSporsmaalTeksten;

    private boolean mIsCheater;

    private static final String KEY_INDEX = "index";


    private SantUsant[] mSporsmaalTekstene = new SantUsant[]{
            new SantUsant(R.string.sporsmaal_iq,false),
            new SantUsant(R.string.sporsmaal_penger,false),
            new SantUsant(R.string.sporsmaal_vekt,false),
            new SantUsant(R.string.sporsmaal_tall,false),
            new SantUsant(R.string.sporsmaal_mer_tall,true),
            new SantUsant(R.string.sporsmaal_app,false),
            new SantUsant(R.string.sporsmaal_puling,true),
            new SantUsant(R.string.sporsmaal_dit_staat,false)
    };

    private int mIndeks;

    private void oppdaterSporsmaal(){
        int sporsmaal = mSporsmaalTekstene[mIndeks].getSporsmaal();
        mSporsmaalTeksten.setText(sporsmaal);
    }

    private void sjekkSvar(boolean brukerTrykketSant)
    {
        boolean svarSant = mSporsmaalTekstene[mIndeks].ismRiktigSvar();
        int beskjedResId=0;

        if (mIsCheater) {
            beskjedResId = R.string.juksepave;
        }
        else {
            if (brukerTrykketSant == svarSant) {
                beskjedResId = R.string.riktig_toast;
            } else {
                beskjedResId = R.string.feil_toast;
            }
        }

        Toast.makeText(this,beskjedResId,Toast.LENGTH_SHORT).show();
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN,true);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX,mIndeks);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null)
            mIndeks = savedInstanceState.getInt(KEY_INDEX,0);

        mSporsmaalTeksten = (TextView)findViewById(R.id.sporsmaal_tekst);

        mRelativKnapp = (Button)findViewById(R.id.relativ_knapp);
        mRelativKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RunaActivity.class);
                startActivity(i);
            }
        });

        mJukseKnapp = (Button)findViewById(R.id.jukse_knapp);
        mJukseKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean riktigSvar = mSporsmaalTekstene[mIndeks].ismRiktigSvar();
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, riktigSvar);
                startActivityForResult(i,0);
            }
        });

        mNesteKnapp = (Button)findViewById(R.id.neste_knapp);
        mNesteKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndeks = (mIndeks + 1) % mSporsmaalTekstene.length;
                oppdaterSporsmaal();
            }
        });

        mRettKnapp = (Button) findViewById(R.id.rett_knapp);
        mRettKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sjekkSvar(true);
                mIsCheater = false;
                oppdaterSporsmaal();
            }
        });

        mFeilKnapp = (Button) findViewById(R.id.feil_knapp);
        mFeilKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sjekkSvar(false);
                mIsCheater = false;
                oppdaterSporsmaal();
            }
        });
        oppdaterSporsmaal();
    }

    public SantUsant[] getSporsmaalTekstene()
    {
        return mSporsmaalTekstene;
    }

    public int getmIndeks()
    {
        return mIndeks;
    }
}
