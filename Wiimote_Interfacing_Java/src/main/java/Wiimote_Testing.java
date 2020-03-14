import org.wiigee.control.WiimoteWiigee;
import org.wiigee.device.Device;
import org.wiigee.device.Wiimote;
import org.wiigee.event.AccelerationEvent;
import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;

public class Wiimote_Testing implements GestureListener
{
    public static void main( String[] args )
    {
        WiimoteWiigee wiigee = new WiimoteWiigee();
        wiigee.setTrainButton(Wiimote.BUTTON_A);
        wiigee.setCloseGestureButton(Wiimote.BUTTON_HOME);
        wiigee.setRecognitionButton(Wiimote.BUTTON_B);
    }

    public void gestureReceived( GestureEvent e )
    {
        AccelerationEvent a = new AccelerationEvent(new Device(true), 1, 1, 1, 1 );
    }
}
