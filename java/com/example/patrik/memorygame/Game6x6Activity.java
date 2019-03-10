package com.example.patrik.memorygame;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;

public class Game6x6Activity extends AppCompatActivity implements View.OnClickListener{


    private int numberOfElements;
    private MemoryButton [] buttons;
    private int[] buttonGraphicLocations;
    private int [] buttonGraphics;

    private MemoryButton selectButton1;
    private MemoryButton selectButton2;

    private boolean isBusy = false;


    // tar in Gridlayout specen från classen MemoryButton och ger den till den bestämda
    // gridlayouten som skall användas grid_layout_4x4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6x6);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid_layout_6x6);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfElements = numColumns * numRows;

        buttons = new MemoryButton[numberOfElements];

        buttonGraphics = new int [numberOfElements / 2];
// för in alla bilder i en array från mappen drawable
        buttonGraphics[0] = R.drawable.ic_image101;
        buttonGraphics[1] = R.drawable.ic_image102;
        buttonGraphics[2] = R.drawable.ic_image103;
        buttonGraphics[3] = R.drawable.ic_image104;
        buttonGraphics[4] = R.drawable.ic_image105;
        buttonGraphics[5] = R.drawable.ic_image106;
        buttonGraphics[6] = R.drawable.ic_image107;
        buttonGraphics[7] = R.drawable.ic_image108;
        buttonGraphics[8] = R.drawable.ic_image109;
        buttonGraphics[9] = R.drawable.ic_image110;
        buttonGraphics[10] = R.drawable.ic_image111;
        buttonGraphics[11] = R.drawable.ic_image112;

        buttonGraphicLocations = new int[numberOfElements];

        shuffleButtonGraphics();


        for (int r =0; r < numRows; r++)
        {
            for (int c =0; c < numColumns; c++)
            {
                MemoryButton tempButtom = new MemoryButton(this,r,c,buttonGraphics[buttonGraphicLocations[r * numColumns + c]]);
                tempButtom.setId(View.generateViewId());
                tempButtom.setOnClickListener(this);
                buttons[r*numColumns +c] = tempButtom;
                gridLayout.addView(tempButtom);
            }

        }




    }
    //slumpar utt bildena så att de inte hamnar på samma plats varje gång.
    private void shuffleButtonGraphics() {

        Random rand = new Random();

        for (int i = 0; i < numberOfElements; i++)
        {
            buttonGraphicLocations[i] = i % (numberOfElements / 2);
        }

        for (int i = 0; i < numberOfElements; i++)
        {
            int temp = buttonGraphicLocations[i];
            int swapIndex = rand.nextInt(24);
            buttonGraphicLocations[i] = buttonGraphicLocations[swapIndex];
            buttonGraphicLocations[swapIndex] = temp;
        }
    }
    // felhantering för att ta hand om fel som uppstår när appen körs.
    @Override
    public void onClick(View view) {


        if ( isBusy)
            return;

        MemoryButton button = (MemoryButton) view;

        if ( button.isMatched)
            return;

        if (selectButton1 == null)
        {
            selectButton1 =button;
            selectButton1.flip();
            return;
        }

        if (selectButton1.getId() == button.getId())
        {
            return;
        }
        if (selectButton1.getFrontDrawableId()== button.getFrontDrawableId())
        {
// om man hittar en matchning blir båda buttons true
            button.flip();
            button.setMatched(true);
            selectButton1.setMatched(true);

            selectButton1.setEnabled(false);
            button.setEnabled(false);

            selectButton1 = null;
            return;
        }
// annars vänds dom tillbaka med en fördröjning på 1 sekund.
        else
        {
            selectButton2 = button;
            selectButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectButton2.flip();
                    selectButton1.flip();
                    selectButton1 = null;
                    selectButton2 = null;
                    isBusy = false;
                }
            },1000);
        }
    }
}
