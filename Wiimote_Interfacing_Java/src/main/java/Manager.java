import FRCCodexClasses.Codex;
import control.DeviceDiscovery;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;

public class Manager {

    public static final Data data = new Data();
//    public static final Wiimote_Comms wiimote_comms = new Wiimote_Comms( );

    public static void main( String[] args ) {

        System.setProperty("bluecove.stack", "widcomm");

        Object lock = new Object();
        DeviceDiscovery wiimoteDeviceDiscovery = new DeviceDiscovery( );

        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = localDevice.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, wiimoteDeviceDiscovery);
            while ( wiimoteDeviceDiscovery.isInquirying() ) { }
            System.out.println("Device Inquiry Completed. ");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Wiimote_Comms wiimote_comms = new Wiimote_Comms( wiimoteDeviceDiscovery );

        for (Codex c : Data.allCodexes ) {
            if ( c.hasChanged() ) {
                System.out.println( "Codex: " + c.meta().getEnum().getSimpleName() + " has changed!!!" );
            }
            c.reset();
        }
    }

}
