package mohit.com.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;//0=o and 1=x
    boolean isGameActive=true;

    int[] gameState={2,2,2,2,2,2,2,2,2};
    int [][]winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view)
    {
        ImageView counter=(ImageView)view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && isGameActive) {
            counter.setTranslationY(-1000f);
            gameState[tappedCounter]=activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.oimage);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(1000);
            for(int []winningPosition:winningPositions)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                        && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                        && gameState[winningPosition[0]]!=2)
                {
                    String winner="O";
                    isGameActive=false;

                    if(gameState[winningPosition[0]]==0)
                    {
                        winner="X";
                    }
                    System.out.println(gameState[winningPosition[0]]);
                    TextView winnertextView=(TextView)findViewById(R.id.winnerMessage);
                    winnertextView.setText(winner+" has won");

                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameisOver=true;
                    for(int counterState:gameState)
                    {
                        if(counterState==2)
                        gameisOver=false;
                    }
                    if(gameisOver)
                    {
                        TextView winnertextView=(TextView)findViewById(R.id.winnerMessage);
                        winnertextView.setText("It's a draw");

                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playAgain(View view)
    {
        isGameActive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer=0;//0=o and 1=x
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        //int [][]winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{3,4,6}};
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);//0 to set it to an empty image
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
