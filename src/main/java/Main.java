import com.raylib.Jaylib;
import com.raylib.Raylib;

import static com.raylib.Jaylib.*;

public class Main {

    //init variáveis
    private static final int playY = 500;
    private static final int playW = 100;
    private static final int playH = 50;


    static GameScreen currntScreen;

    public static Raylib.Color color = WHITE;

    public static Texture title;
    public static Texture playBtnOFF;
    public static Texture playBtnON;




    public static void main(String[] args)
    {
        //initialize objects
        InitWindow(500, 800, "Demo");
        SetTargetFPS(60);
        currntScreen = GameScreen.TUTORIAL;

        //images
        title = LoadTexture("resources/Title_Stuff.png");
        playBtnOFF = LoadTexture("resources/Play_btn_off.png");
        playBtnON = LoadTexture("resources/Play_btn_on.png");

        int timer = 0;

        //gameloop ou render method
        while(!WindowShouldClose())
        {

            //o update
            switch (currntScreen)
            {
                case TITLE ->
                {
                    int playX = GetScreenWidth()/2 - playW/2;
                    if (GetMouseX() < playX + playW && GetMouseX() > playX && GetScreenHeight() - GetMouseY() < playY + playH && GetScreenHeight() - GetMouseY() < playY)
                    {
                        DrawTexture(playBtnON, playX, playY, WHITE);
                        if (IsMouseButtonPressed(0))
                        {
                            currntScreen = GameScreen.TUTORIAL;
                        }
                    } else
                    {
                        DrawTexture(playBtnOFF, playX, playY, WHITE);
                    }
                }
                case TUTORIAL ->
                {
                    timer ++;
                    if (timer >= 900){currntScreen = GameScreen.PHASE1;}
                }
            }


            //basicamente um redner
            BeginDrawing();

            switch (currntScreen)
            {
                case TITLE -> {
                    ClearBackground(RAYWHITE);

                    DrawTexture(title, 0, 0, WHITE);

                    updateTITLE();
                }
                case TUTORIAL -> {
                    ClearBackground(RAYWHITE);
                    DrawText(TextFormat("Hi! = D \n\n Since I've discovered the Jam late in the game\n (pun intended) I only had time to do this little\n thing!\n\n This first four levels will be an " +
                            "intro to the\n game! Hope you enjoy it <3"), 10, 10, 20, BLACK);
                    DrawText("" + timer, 450, 650, 20, RED);
                }
                case PHASE1 -> {
                    ClearBackground(RAYWHITE);
                }
            }


            EndDrawing();
        }

        CloseWindow();
    }

    public static void updateTITLE()
    {

        //Identificar área horizontal do botão de play;
        int playX = GetScreenWidth()/2 - playW/2;
        if (GetMouseX() < playX + playW && GetMouseX() > playX && GetScreenHeight() - GetMouseY() < playY + playH && GetScreenHeight() - GetMouseY() < playY)
        {
            DrawTexture(playBtnON, playX, playY, WHITE);
            if (IsMouseButtonPressed(0))
            {
                currntScreen = GameScreen.TUTORIAL;
            }
        } else
        {
            DrawTexture(playBtnOFF, playX, playY, WHITE);
        }


    }

}