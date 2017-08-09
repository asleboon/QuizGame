package com.awsmtexh.asle.quizgame;

public class SantUsant
{
    private int mSporsmaal;
    private boolean mRiktigSvar;

    public SantUsant(int sporsmaal, boolean riktigSvar)
    {
        mSporsmaal = sporsmaal;
        mRiktigSvar = riktigSvar;
    }



    public int getSporsmaal() {
        return mSporsmaal;
    }

    public void setSporsmaal(int Sporsmaal) {
        this.mSporsmaal = Sporsmaal;
    }

    public boolean ismRiktigSvar() {
        return mRiktigSvar;
    }

    public void setRiktigSvar(boolean RiktigSvar) {
        this.mRiktigSvar = RiktigSvar;
    }
}
