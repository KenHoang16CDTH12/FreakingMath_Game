package hueic.kenhoang.freakingmath_game;

/**
 * Created by kenhoang on 05/03/2017.
 */

public class LevelModel {
    public int difficultLevel = 1;
    //operators

    public static final int ADD = 0;
    public static final int SUB = 1;
    public static final int MUL = 2;
    public static final int DIV = 3;

    //operators text

    public static final String ADD_TEXT = "+";
    public static final String SUB_TEXT = "-";
    public static final String MUL_TEXT = "x";
    public static final String DIV_TEXT = ":";
    public static final String EQU_TEXT = "=";
    public static final String [] arrOperatorText = {ADD_TEXT,
                                                    SUB_TEXT,
                                                    MUL_TEXT,
                                                    DIV_TEXT};

    //components of formular

    public int x;                   //Variable x
    public int y;                   //Variable y
    public int result;              //Result
    public int operator;            //Opearator
    public boolean correctWrong;    //Correct Wrong
    public String strOperator = "";      //Show Operator
    public String strResullt = "";       //Show Result

    // max value of operator depend on level : easy, medium, hard
    public static final int MAX_OPERATOR_LEVEL_EASY = 5;
    public static final int MAX_OPERATOR_LEVEL_MEDIUM = 10;
    public static final int MAX_OPERATOR_LEVEL_HARD = 50;

    public static final int [] arrMaxOperationValue = {MAX_OPERATOR_LEVEL_EASY,
                                                    MAX_OPERATOR_LEVEL_MEDIUM,
                                                    MAX_OPERATOR_LEVEL_HARD};
}
