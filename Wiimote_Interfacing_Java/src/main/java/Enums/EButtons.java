package Enums;

import device.Wiimote;

public enum EButtons {

    //Not Implemeted
    A( Wiimote.BUTTON_A ),
    B( Wiimote.BUTTON_B ),
    DOWN( Wiimote.BUTTON_DOWN ),
    UP( Wiimote.BUTTON_UP ),
    LEFT( Wiimote.BUTTON_LEFT ),
    RIGHT( Wiimote.BUTTON_RIGHT ),
    HOME( Wiimote.BUTTON_HOME ),
    MINUS( Wiimote.BUTTON_MINUS ),
    PLUS( Wiimote.BUTTON_PLUS ),
    ONE( Wiimote.BUTTON_1 ),
    TWO( Wiimote.BUTTON_2 ),
    POWER( Wiimote.BUTTON_A );

    int num;

    EButtons( int num ) {
        this.num = num;
    }

    public static EButtons button( int pressed ) {
        for ( EButtons b : values() ) {
            if ( b.num == pressed ) {
                return b;
            }
        }
        return null;
    }
}