package com.example.patrik.memorygame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.patrik.memorygame.R;


@SuppressLint("AppCompatCustomView")
public class MemoryButton extends Button {

    protected int row;
    protected int column;
    protected int frontDrawableId;

        protected boolean isFlipped = false;
        protected boolean isMatched = false;

        protected Drawable front;
    protected Drawable back;


    @SuppressLint("RestrictedApi")
    public MemoryButton(Context context, int r, int c, int frontImageDrawableId){

        super(context);
        row = r;
        column = c;
        frontDrawableId = frontImageDrawableId;
// Deklarerar vad som är främre bilden och vad som är back
        front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableId);
        back = AppCompatDrawableManager.get().getDrawable(context, R.drawable.imagedisney);

        setBackground(back);
        // Bestämmer layout parametrarna
        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));
        // Bestämmer storleken på grid viewns knappar
       tempParams.width = (int)getResources().getDisplayMetrics().density*125;
        tempParams.height = (int)getResources().getDisplayMetrics().density*120;
        setLayoutParams(tempParams);
    }

    public boolean isMatched() {

        return isMatched;
    }
// kollar om värdena matchar med varandra för att bedömma om dom har samma id
    public void setMatched(boolean matched) {
        isMatched = matched;
    }


    public int getFrontDrawableId() {
        return frontDrawableId;
    }

    public void flip(){

        if (isMatched)
            return;
// om fliped tryckt på vissas bilden som döljer sig bakom
        if (isFlipped){
            setBackground(back);
            isFlipped = false;
        }
        else {
            setBackground(front);
            isFlipped = true;
        }
    }
}
