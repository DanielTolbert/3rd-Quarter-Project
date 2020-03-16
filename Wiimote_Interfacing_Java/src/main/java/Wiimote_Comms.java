import Enums.EButtons;
import Enums.EGyro;
import Enums.EInfared;
import control.*;
import device.*;
import event.*;
import logic.*;


import javax.bluetooth.*;


public class Wiimote_Comms
{

    private static Wiimote wiigee;

    public Wiimote_Comms( DeviceDiscovery wiimoteDeviceDiscovery ) {

        try {
            System.setProperty("bluecove.jsr82.psm_minimum_off", "true");
            wiigee = wiimoteDeviceDiscovery.getDiscoveredWiimotes()[0];
            wiigee.setTrainButton(Wiimote.BUTTON_A);
            wiigee.setCloseGestureButton(Wiimote.BUTTON_HOME);
            wiigee.setRecognitionButton(Wiimote.BUTTON_B);
//            wiigee.setWiiMotionPlusEnabled(true);
//            wiigee.setAccelerationEnabled( true );
//            wiigee.setInfraredCameraEnabled( true );
            wiigee.enableAccelerationSensors();
            wiigee.enableInfraredCamera();
            wiigee.setLED( 1 );
            wiigee.addWiimoteListener(new WiimoteListener() {
                @Override
                public void accelerationReceived(WiimoteAccelerationEvent wiimoteAccelerationEvent) { }

                @Override
                public void buttonPressReceived(WiimoteButtonPressedEvent wiimoteButtonPressedEvent) {
                    Manager.data.buttons1.set( EButtons.button( wiimoteButtonPressedEvent.getButton() ), true );
                }

                @Override
                public void buttonReleaseReceived(WiimoteButtonReleasedEvent wiimoteButtonReleasedEvent) {}

                @Override
                public void motionStartReceived(WiimoteMotionStartEvent wiimoteMotionStartEvent) {}

                @Override
                public void motionStopReceived(WiimoteMotionStopEvent wiimoteMotionStopEvent) {}

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
                    Manager.data.infared1.set(EInfared.VALID, infraredEvent.isValid(0));
                }
            });
//            wiigee.addInfraredListener(new InfraredListener() {
//                @Override
//                public void infraredReceived(InfraredEvent infraredEvent) {
//
//                    Manager.data.infared1.set(EInfared.X_LOC_POINT_ONE, infraredEvent.getCoordinates()[0][0]);
//                    Manager.data.infared1.set(EInfared.Y_LOC_POINT_ONE, infraredEvent.getCoordinates()[0][1]);
//                    Manager.data.infared1.set(EInfared.X_LOC_POINT_TWO, infraredEvent.getCoordinates()[1][0]);
//                    Manager.data.infared1.set(EInfared.Y_LOC_POINT_TWO, infraredEvent.getCoordinates()[1][1]);
//                    int midX = (infraredEvent.getCoordinates()[0][0] + infraredEvent.getCoordinates()[1][0]) >> 1;
//                    int midY = (infraredEvent.getCoordinates()[1][0] + infraredEvent.getCoordinates()[1][1]) >> 1;
//                    Manager.data.infared1.set(EInfared.X_LOC_MID, midX);
//                    Manager.data.infared1.set(EInfared.Y_LOC_MID, midY);
//                    Manager.data.infared1.set(EInfared.SIZE, infraredEvent.getSize()[0]);
//                    Manager.data.infared1.set(EInfared.VALID, infraredEvent.getValids()[0]);
//                }
//            });
//            wiigee.addRotationListener(new RotationListener() {
//                @Override
//                public void rotationSpeedReceived(RotationSpeedEvent rotationSpeedEvent) {
//                    Manager.data.gyro1.set( EGyro.PHI, rotationSpeedEvent.getPhi() );
//                    Manager.data.gyro1.set( EGyro.PSI, rotationSpeedEvent.getPsi() );
//                    Manager.data.gyro1.set( EGyro.THETA, rotationSpeedEvent.getTheta() );
//                }
//
//                @Override
//                public void rotationReceived(RotationEvent rotationEvent) {
//                    Manager.data.gyro1.set( EGyro.YAW, rotationEvent.getYaw() );
//                    Manager.data.gyro1.set( EGyro.PITCH, rotationEvent.getPitch() );
//                    Manager.data.gyro1.set( EGyro.ROLL, rotationEvent.getRoll() );
//                }
//            });
//            wiigee.addButtonListener(new ButtonListener() {
//                @Override
//                public void buttonPressReceived(ButtonPressedEvent buttonPressedEvent) {
//                    Manager.data.buttons1.set( EButtons.button( buttonPressedEvent.getButton() ), true );
//                }
//
//                @Override
//                public void buttonReleaseReceived(ButtonReleasedEvent buttonReleasedEvent) {}
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

