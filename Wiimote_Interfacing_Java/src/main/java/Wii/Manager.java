package Wii;

import FRCCodexClasses.Codex;
import Wii.Data;

public class Manager {

    public static final Data DATA = new Data();
    public static final Wiimote_Comms wiimote_Comms = new Wiimote_Comms();
    public static final CSVLogger csvLogger = new CSVLogger( true );

    static {
        for ( Codex c : DATA.allCodexes ) {
            c.meta().setGlobalId( c.hashCode() );
        }
        csvLogger.start();
        csvLogger.logFromCodexToCSVHeader();
    }

    public static void main( String[] args ) {
        for (Codex c : Data.allCodexes ) {
            csvLogger.kCSVLoggerQueue.add( new Log( c.toCSV(), c.meta().gid() ) );
            c.reset();
        }
    }

}
