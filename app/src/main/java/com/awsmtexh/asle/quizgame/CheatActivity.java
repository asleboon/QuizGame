package com.awsmtexh.asle.quizgame;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private Button mVisKanpp;
    private TextView mSvarTekst;
    private boolean mRiktigSvar;
    public static final String EXTRA_ANSWER_IS_TRUE = "com.awsmtexh.asle.quizgame.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.awsmtexh.asle.quizgame.answer_is_shown";

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK,data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        setAnswerShownResult(false);
        mRiktigSvar = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,true);

        mVisKanpp = (Button)findViewById(R.id.vis_knapp);
        mVisKanpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSvarTekst = (TextView) findViewById(R.id.jukse_svar);
                if (mRiktigSvar)
                    mSvarTekst.setText(R.string.jukse_svar_riktig);
                else
                    mSvarTekst.setText(R.string.jukse_svar_feil);
                setAnswerShownResult(true);
                }
            });
        }
    }
