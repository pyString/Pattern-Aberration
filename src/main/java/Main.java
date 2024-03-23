import com.raylib.Jaylib;
import com.raylib.Raylib;

import static com.raylib.Jaylib.*;

public class Main {

    //init variáveis
    private static final int playY = 500;
    private static final int playW = 100;
    private static final int playH = 50;
    private static float xPos = 0.0f;
    private static float yPos = 0.0f;


    static GameScreen currntScreen;

    public static Raylib.Color color = BLACK;

    public static Texture title;
    public static Texture playBtnOFF;
    public static Texture playBtnON;
    public static Texture shitPuzzle1;
    public static Raylib.Vector2 mouse;

    public static Raylib.Vector2 rect;

    public static Jaylib.Rectangle playBtnArea;
    public static Jaylib.Rectangle mousePoint;

    public static Jaylib.Rectangle aberration1;
    public static Jaylib.Rectangle aberration2;



    public static void main(String[] args)
    {
        //initialize objects
        InitWindow(500, 800, "Demo");
        SetTargetFPS(60);
        currntScreen = GameScreen.PHASE1;

        //images
        title = LoadTexture("resources/Title_Stuff.png");
        playBtnOFF = LoadTexture("resources/Play_btn_off.png");
        playBtnON = LoadTexture("resources/Play_btn_on.png");

        mouse = new Raylib.Vector2();
        rect = new Jaylib.Vector2(0.0f, 0.0f);

        int timer = 0;
        playBtnArea = new Jaylib.Rectangle(GetScreenWidth()/2.0f - playW/2.0f, GetScreenHeight()/2.0f - playH/2.0f, (float) playW, GetScreenHeight());
        mousePoint = new Jaylib.Rectangle(GetMouseX(), GetMouseY(), 2.0f, 2.0f);
        aberration1 = new Jaylib.Rectangle(50.0f, 650.0f, 100.0f, 100.0f);
        aberration2 = new Jaylib.Rectangle(GetMousePosition().x(), GetMousePosition().y(), 100.0f, 100.0f);

        //gameloop ou render method
        while(!WindowShouldClose())
        {
            rect = GetMousePosition();

            //o update
            switch (currntScreen)
            {
                case TITLE ->
                {
                    /*System.out.print("x: " + GetMouseX() + "\n");
                    System.out.print("y: " + GetMouseY() + "\n");*/
                    int playX = GetScreenWidth()/2 - playW/2;
                    if (CheckCollisionPointRec(rect, playBtnArea))
                    {
                        System.out.print("RETANGULO\n");
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
                    if (timer >= 600){currntScreen = GameScreen.PHASE1; timer = 0;}
                }
                case PHASE1 ->
                {
                    if (CheckCollisionPointRec(rect, aberration1))
                    {
                        System.out.print("TÀ NO RETANGULO POHA\n");
                        color = YELLOW;
                        if (IsMouseButtonPressed(0))
                        {
                            currntScreen = GameScreen.PHASE2;
                        }
                    } else
                    {
                        color = BLACK;
                    }

                }

            }


            //basicamente um redner
            BeginDrawing();

            switch (currntScreen)
            {
                case TITLE -> {
                    ClearBackground(RAYWHITE);

                    DrawTexture(title, 0, 0, WHITE);

                    //updateTITLE();
                }
                case TUTORIAL -> {
                    ClearBackground(RAYWHITE);
                    DrawText(TextFormat("""
                            Hi! = D\s

                             Since I've discovered the Jam late in the game
                             (pun intended) I only had time to do this little
                             thing!

                             This first four levels will be an intro to the
                             game! Hope you enjoy it <3"""), 10, 10, 20, BLACK);
                }
                case PHASE1 -> {
                    ClearBackground(RAYWHITE);

                    DrawText("Here you have a pattern. In this case is a ", 10, 10, 10, BLACK);
                    DrawRectangle(50, 150, 100, 100, BLACK);//1
                    DrawRectangle(250,150, 100, 100, BLACK);//2
                    DrawRectangle(150,250, 100, 100, BLACK);//3
                    DrawRectangle(350,250, 100, 100, BLACK);//4
                    DrawRectangle(50, 350, 100, 100, BLACK);//5
                    DrawRectangle(250,350, 100, 100, BLACK);//6
                    DrawRectangle(150, 450,100, 100, BLACK);//7
                    DrawRectangle(350,450, 100, 100, BLACK);//8
                    DrawRectangle(50, 550, 100, 100, BLACK);//9
                    DrawRectangle(250,550, 100, 100, BLACK);//10
                    DrawRectangle(150,650, 100, 100, BLACK);//12
                    DrawRectangle(350,650, 100, 100, BLACK);//13
                    DrawRectangleRec(aberration1, color);
                }
                case PHASE2 ->
                {
                    ClearBackground(RAYWHITE);

                    DrawText("SHITFUCK DUNE FUCK", 10, 10, 10, BLACK);
                }
            }


            EndDrawing();
        }

        CloseWindow();
    }

}