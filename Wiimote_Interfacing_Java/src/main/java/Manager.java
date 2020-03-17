import FRCCodexClasses.Codex;
import com.intel.bluetooth.BlueCoveConfigProperties;

public class Manager {

    public static final Data data = new Data();
    public static final Wiimote_Comms wiimote_comms = new Wiimote_Comms();

    public static void main( String[] args ) {
        for (Codex c : Data.allCodexes ) {
            if ( c.hasChanged() ) {
                System.out.println( "Codex: " + c.meta().getEnum().getSimpleName() + " has changed!!!" );
            }
            c.reset();
        }
    }

}
