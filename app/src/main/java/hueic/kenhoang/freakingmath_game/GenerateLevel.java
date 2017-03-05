package hueic.kenhoang.freakingmath_game;

import java.util.Random;

/**
 * Created by kenhoang on 05/03/2017.
 */

public class GenerateLevel {
    //score of each level
    public static final int EASY = 10;
    public static final int MEDIUM = 20;
    public static final int HARD = 150;

    public static LevelModel generateLevel (int count) {
        LevelModel level = new LevelModel();
        Random rad =  new Random();
        //get current difficult level;
        if (count <= EASY) {
            level.difficultLevel = 1;
        } else {
            if (count <= MEDIUM) {
                level.difficultLevel = 2;
            } else {
                if (count <= HARD) {
                    level.difficultLevel = 3;
                } else {
                    level.difficultLevel = 4;
                }
            }
        }
        // random operation
        level.operator = rad.nextInt(level.difficultLevel);
        // random operator

        level.x = rad.nextInt(level.arrMaxOperationValue[level.difficultLevel]) + 1;
        level.y = rad.nextInt(level.arrMaxOperationValue[level.difficultLevel]) + 1;

        //random result : correct or wrong

        level.correctWrong = rad.nextBoolean();

        // random result
        if (level.correctWrong == false) {
            switch (level.operator) {
                case LevelModel.ADD :
                    do {
                        level.result = rad.nextInt(level.arrMaxOperationValue[level.difficultLevel]);
                    }
                    while (level.result == (level.x + level.y));
                    break;
                case LevelModel.SUB :
                    do {
                        level.result = rad.nextInt(level.arrMaxOperationValue[level.difficultLevel]);
                    }
                    while (level.result == (level.x - level.y));
                    break;
                case LevelModel.MUL :
                    do {
                        level.result = rad.nextInt(level.arrMaxOperationValue[level.difficultLevel]);
                    }
                    while (level.result == (level.x * level.y));
                    break;
                case LevelModel.DIV :
                    do {
                        level.result = rad.nextInt(level.arrMaxOperationValue[level.difficultLevel]);
                    }
                    while (level.result == (level.x / level.y));
                    break;
                default:
                    break;
            }
        }
        if (level.correctWrong == true){
            switch (level.operator) {
                case LevelModel.ADD :
                    level.result = level.x + level.y;
                    break;
                case LevelModel.SUB :
                    level.result = level.x - level.y;
                    break;
                case LevelModel.MUL :
                    level.result = level.x * level.y;
                    break;
                case LevelModel.DIV :
                    level.result = level.x / level.y;
                    break;
                default:
                    break;
            }
        }
        // create string to display on screen
        // create string operators
        level.strOperator = String.valueOf(level.x)
                            + level.arrOperatorText[level.operator]
                            + String.valueOf(level.y);
        // create string result
        level.strResullt = LevelModel.EQU_TEXT + String.valueOf(level.result);
        //return levelmodel for next level

        return level;
    }
}
