package graphing;

import java.util.Timer;

public class DataCollector {

    private static boolean firstTime = true;
    private static long mStartTime;

    public static void startTimer() {
        if (firstTime) {
            mStartTime = System.currentTimeMillis();
            firstTime = false;
        }
    }

    public static int getRunTimeSeconds() {
        return (int) ( ( Math.abs(mStartTime - System.currentTimeMillis() )) / 1000 );
    }

    public static void collectData() {

    }

    public static void main ( String[] args ) {
        Graph.main(args);
    }
}
