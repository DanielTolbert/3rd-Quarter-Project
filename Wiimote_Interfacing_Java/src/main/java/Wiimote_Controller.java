import Enums.EGyro;
import Enums.EInfared;
import org.wiigee.device.Wiimote;
import org.wiigee.event.*;

import javax.bluetooth.*;


public class Wiimote_Controller
{

    private static Wiimote wiigee;

    public Wiimote_Controller() {

        Data data = new Data();
        Object lock = new Object();
        MyDiscoveryListener discoveryListener = new MyDiscoveryListener(lock);

        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = localDevice.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, discoveryListener);

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
            wiigee = new Wiimote(discoveryListener.getAddress(), false, true);
            wiigee.setTrainButton(Wiimote.BUTTON_A);
            wiigee.setCloseGestureButton(Wiimote.BUTTON_HOME);
            wiigee.setRecognitionButton(Wiimote.BUTTON_B);
            wiigee.setWiiMotionPlusEnabled(true);
            wiigee.addInfraredListener(new InfraredListener() {
                @Override
                public void infraredReceived(InfraredEvent infraredEvent) {
                    data.infared.set(EInfared.X_LOC, infraredEvent.getCoordinates()[0][0]);
                    data.infared.set(EInfared.Y_LOC, infraredEvent.getCoordinates()[0][1]);
                    data.infared.set(EInfared.SIZE, infraredEvent.getSize()[0]);
                    data.infared.set(EInfared.VALID, infraredEvent.getValids()[0]);
                }
            });
            wiigee.addRotationListener(new RotationListener() {
                @Override
                public void rotationSpeedReceived(RotationSpeedEvent rotationSpeedEvent) {
                    data.gyro.set( EGyro.PHI, rotationSpeedEvent.getPhi() );
                    data.gyro.set( EGyro.PSI, rotationSpeedEvent.getPsi() );
                    data.gyro.set( EGyro.THETA, rotationSpeedEvent.getTheta() );
                }

                @Override
                public void rotationReceived(RotationEvent rotationEvent) {
                    data.gyro.set( EGyro.YAW, rotationEvent.getYaw() );
                    data.gyro.set( EGyro.PITCH, rotationEvent.getPitch() );
                    data.gyro.set( EGyro.ROLL, rotationEvent.getRoll() );
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class MyDiscoveryListener implements DiscoveryListener {

        private Object lock;
        private String address;

        public MyDiscoveryListener( Object lock ) {
            super();
            this.lock = lock;
        }

        @Override
        public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {
            address = btDevice.getBluetoothAddress();

            System.out.println("device found: " + address);
        }

        @Override
        public void servicesDiscovered(int i, ServiceRecord[] serviceRecords) {

        }

        @Override
        public void inquiryCompleted(int arg0) {
            synchronized(lock){
                lock.notify();
            }
        }

        public String getAddress() {
            return address;
        }

        @Override
        public void serviceSearchCompleted(int i, int i1) {

        }

    }
}
