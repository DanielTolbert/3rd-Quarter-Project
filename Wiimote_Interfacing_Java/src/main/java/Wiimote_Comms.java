import Enums.EButtons;
import Enums.EGyro;
import Enums.EInfared;
import com.intel.bluetooth.BlueCoveConfigProperties;
import org.wiigee.control.WiimoteDeviceDiscovery;
import org.wiigee.device.Wiimote;
import org.wiigee.event.*;
import org.wiigee.util.Log;

import javax.bluetooth.*;
import java.io.IOException;
import java.util.Vector;

public class Wiimote_Comms
{

    private static Wiimote wiigee;

    public Wiimote_Comms() {

        Object lock = new Object();
        WiimoteDeviceDiscovery wiimoteDeviceDiscovery = new WiimoteDeviceDiscovery( lock );

        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = localDevice.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, wiimoteDeviceDiscovery);

            try {
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Device Inquiry Completed. ");

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
            wiigee = wiimoteDeviceDiscovery.getDiscoveredWiimotes().firstElement();
            wiigee.setTrainButton(Wiimote.BUTTON_A);
            wiigee.setCloseGestureButton(Wiimote.BUTTON_HOME);
            wiigee.setRecognitionButton(Wiimote.BUTTON_B);
            wiigee.setWiiMotionPlusEnabled(true);
            wiigee.setAccelerationEnabled( true );
            wiigee.setInfraredCameraEnabled( true );
            wiigee.setLED( 1 );
            wiigee.addInfraredListener(new InfraredListener() {
                @Override
                public void infraredReceived(InfraredEvent infraredEvent) {

                    Manager.data.infared1.set(EInfared.X_LOC_POINT_ONE, infraredEvent.getCoordinates()[0][0]);
                    Manager.data.infared1.set(EInfared.Y_LOC_POINT_ONE, infraredEvent.getCoordinates()[0][1]);
                    Manager.data.infared1.set(EInfared.X_LOC_POINT_TWO, infraredEvent.getCoordinates()[1][0]);
                    Manager.data.infared1.set(EInfared.Y_LOC_POINT_TWO, infraredEvent.getCoordinates()[1][1]);
                    int midX = (infraredEvent.getCoordinates()[0][0] + infraredEvent.getCoordinates()[1][0]) >> 1;
                    int midY = (infraredEvent.getCoordinates()[1][0] + infraredEvent.getCoordinates()[1][1]) >> 1;
                    Manager.data.infared1.set(EInfared.X_LOC_MID, midX);
                    Manager.data.infared1.set(EInfared.Y_LOC_MID, midY);
                    Manager.data.infared1.set(EInfared.SIZE, infraredEvent.getSize()[0]);
                    Manager.data.infared1.set(EInfared.VALID, infraredEvent.getValids()[0]);
                }
            });
            wiigee.addRotationListener(new RotationListener() {
                @Override
                public void rotationSpeedReceived(RotationSpeedEvent rotationSpeedEvent) {
                    Manager.data.gyro1.set( EGyro.PHI, rotationSpeedEvent.getPhi() );
                    Manager.data.gyro1.set( EGyro.PSI, rotationSpeedEvent.getPsi() );
                    Manager.data.gyro1.set( EGyro.THETA, rotationSpeedEvent.getTheta() );
                }

                @Override
                public void rotationReceived(RotationEvent rotationEvent) {
                    Manager.data.gyro1.set( EGyro.YAW, rotationEvent.getYaw() );
                    Manager.data.gyro1.set( EGyro.PITCH, rotationEvent.getPitch() );
                    Manager.data.gyro1.set( EGyro.ROLL, rotationEvent.getRoll() );
                }
            });
            wiigee.addButtonListener(new ButtonListener() {
                @Override
                public void buttonPressReceived(ButtonPressedEvent buttonPressedEvent) {
                    Manager.data.buttons1.set( EButtons.button( buttonPressedEvent.getButton() ), true );
                }

                @Override
                public void buttonReleaseReceived(ButtonReleasedEvent buttonReleasedEvent) {}
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

