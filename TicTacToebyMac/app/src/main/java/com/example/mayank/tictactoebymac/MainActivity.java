package com.example.mayank.tictactoebymac;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // X's turn = 0 .....O's turn = 1...
    int turn = 0; //Setting X's turn first

    int[] gameState = {2,2,2,2,2,2,2,2,2}; // Initial state to something other than 0-cross and/or 1-circle

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}}; // The only combinations to win

    boolean gameActive = true;

    int winner = 2;
    
    public void dropIn(View view) {     // dropIn method : when user clicks any position in the board

        if(gameActive) {

            ImageView imageView = (ImageView) view;


            if (turn == 0) {   // X's turn

                if (gameState[Integer.parseInt(imageView.getTag().toString())] == 2) {

                    imageView.setTranslationY(-1000f);
                    imageView.setImageResource(R.drawable.cross);
                    imageView.animate().translationYBy(1000f).rotation(360f).setDuration(500);
                    gameState[Integer.parseInt(imageView.getTag().toString())] = 0;

                    turn = 1;

                } else {
                    Toast.makeText(MainActivity.this, "Sorry! This place is already full.", Toast.LENGTH_LONG).show();
                }


            } else {        // O's turn

                if (gameState[Integer.parseInt(imageView.getTag().toString())] == 2) {

                    imageView.setTranslationY(-1000f);
                    imageView.setImageResource(R.drawable.circle);
                    imageView.animate().translationYBy(1000f).rotation(360f).setDuration(500);
                    gameState[Integer.parseInt(imageView.getTag().toString())] = 1;

                    turn = 0;

                } else {
                    Toast.makeText(MainActivity.this, "Sorry! This place is already full.", Toast.LENGTH_LONG).show();
                }

            }

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    winner = gameState[winningPosition[0]];
                }

            }

            int counter = 9;
            if (winner == 2) {
                for (int eachposition : gameState) {
                    if (eachposition == 2) {         //This place is not filled up yet
                        counter--;
                    }
                }
            }
            if (winner == 0 || winner == 1 || counter == 9) {
                LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                playAgainLayout.setVisibility(view.VISIBLE);

                gameActive = false;     // User should not be able to play game anymore

                TextView textViewMessage = (TextView) findViewById(R.id.textViewMessage);

                if (winner == 0) {
                    textViewMessage.setText("X has won");
                } else if (winner == 1) {
                    textViewMessage.setText("O has won");
                } else {
                    textViewMessage.setText("Game Draw");
                }

            }
        }

    }

    public void playAgainButton (View view) {

        // X's turn = 0 .....O's turn = 1...
        turn = 0; //Setting X's turn first

        gameActive = true;

        winner = 2;

        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(view.INVISIBLE);

        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        // Setting all image resources to 0 as an initial value

        ImageView imageView = (ImageView) findViewById(R.id.imageView0);
        imageView.setImageResource(0);

        imageView = (ImageView) findViewById(R.id.imageView1);
        imageView.setImageResource(0);

        imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setImageResource(0);

        imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setImageResource(0);

        imageView = (ImageView) findViewById(R.id.imageView4);
        imageView.setImageResource(0);

        imageView = (ImageView) findViewById(R.id.imageView5);
        imageView.setImageResource(0);

        imageView = (ImageView) findViewById(R.id.imageView6);
        imageView.setImageResource(0);

        imageView = (ImageView) findViewById(R.id.imageView7);
        imageView.setImageResource(0);

        imageView = (ImageView) findViewById(R.id.imageView8);
        imageView.setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
