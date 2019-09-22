package com.example.lionandtiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE,TWO,NO
    }

    Player currentPlayer = Player.ONE;

    Player [] playerChoices = new Player[9];

    int [][] winerRowsColumns= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    private boolean gameOver = false;
    private Button restartGame;
    private GridLayout gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerChoices[0] = Player.NO;
        playerChoices[1] = Player.NO;
        playerChoices[2] = Player.NO;
        playerChoices[3] = Player.NO;
        playerChoices[4] = Player.NO;
        playerChoices[5] = Player.NO;
        playerChoices[6] = Player.NO;
        playerChoices[7] = Player.NO;
        playerChoices[8] = Player.NO;


        gridView = findViewById(R.id.gridLayout);

        restartGame = findViewById(R.id.btnReset);
        restartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reStartTheGame();
            }
        });

    }
    public void imageViewIsTapped(View imageView){
        Log.i("MY TAG", "TApped image view");
        ImageView tappedImageView = (ImageView) imageView;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tiTag] == Player.NO && gameOver == false) {
            tappedImageView.setTranslationX(-2000f);


            playerChoices[tiTag] = currentPlayer;
            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }

            tappedImageView.animate().alpha(1f).translationXBy(2000f).rotation(3600).setDuration(1000);
            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_LONG).show();

            String winnerOfGame = "";

            for (int[] winnerColumns : winerRowsColumns) {

                if (playerChoices[winnerColumns[0]] ==
                        playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]] ==
                        playerChoices[winnerColumns[2]]
                        && playerChoices[winnerColumns[0]] != Player.NO) {

                    restartGame.setVisibility(View.VISIBLE);
                    gameOver = true;


                    if (currentPlayer == Player.ONE) {

                        winnerOfGame = "Player Two ";

                    } else if (currentPlayer == Player.TWO) {

                        winnerOfGame = "Player One ";

                    } else {

                        winnerOfGame = " Draw ";
                    }

                    Toast.makeText(this, winnerOfGame + "is The Winner",
                            Toast.LENGTH_LONG).show();


                }
            }
        }
    }

    private void reStartTheGame(){

        for (int index = 0; index <gridView.getChildCount(); index++){

            ImageView imageView = (ImageView) gridView.getChildAt(index);
            imageView.setImageDrawable(null);

        }

        currentPlayer = Player.ONE;
        playerChoices[0] = Player.NO;
        playerChoices[1] = Player.NO;
        playerChoices[2] = Player.NO;
        playerChoices[3] = Player.NO;
        playerChoices[4] = Player.NO;
        playerChoices[5] = Player.NO;
        playerChoices[6] = Player.NO;
        playerChoices[7] = Player.NO;
        playerChoices[8] = Player.NO;

        gameOver = false;
        restartGame.setVisibility(View.INVISIBLE);

    }
}
