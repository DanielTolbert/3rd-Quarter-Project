package Wii;

import FRCCodexClasses.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Optional;

import static Wii.CSVLogger.kCSVLoggerQueue;

public class CSVWriter {

    public static final String USB_DIR = "/u";
    public static final String USER_DIR = System.getProperty("user.home");
    private static final String LOG_PATH_FORMAT = "/logs/%s/%s.csv";
    private Optional<BufferedWriter> bw;

    private static int mLogFailures;

    private File file;
    private Codex<?> mCodex;

    public CSVWriter(Codex<?> pCodex) {

        mCodex = pCodex;

        mLogFailures = 0;
        file = file();
        if ( file != null ) {
            handleCreation( file );
        }

        bw = Optional.empty();
        try  {
            if ( file != null ) {
                bw = Optional.of( new BufferedWriter( new FileWriter( file ) ) );
            }
        } catch ( Exception e ) {}

    }

    public void handleCreation(File pFile) {
        //Makes every folder before the file if the CSV's parent folder doesn't exist
        if(Files.notExists(pFile.toPath())) {
            System.out.println( pFile.getAbsoluteFile().getParentFile().mkdirs() );
        }

        //Creates the .CSV if it doesn't exist
        if(!pFile.exists()) {
            try {
                pFile.createNewFile();
            } catch (IOException e) {}
        }
    }

    public void log( String s ) {
        try {
            if ( bw.isPresent() ) {
                bw.get().append(s);
                bw.get().newLine();
            }
            else {
                if ( mLogFailures < 16 ) {
                    System.out.println("Failure with logging codex: " + mCodex.meta().getEnum().getSimpleName() );
                    System.out.println( "Could not find Path:  (Path to USB)  on roborio! Try plugging in the USB." );
                    mLogFailures++;
                }
                else if ( mLogFailures == 16 ) {
                    System.out.println("---------------------CSV LOGGING DISABLED----------------------");
//                    Robot.mCSVLogger.closeWriters();
                    mLogFailures++;
                }
            }
        } catch (IOException pE) {}
    }

    public void close() {
        if ( bw.isPresent() ) {
            try {
                bw.get().flush();
                bw.get().close();
            }
            catch ( Exception e ) {}

        }
    }

    public void writeHeader() {
        kCSVLoggerQueue.add( new Log( mCodex.getCSVHeader(), mCodex.meta().gid() ) );
    }

    public CodexMetadata<?> getMetaDataOfAssociatedCodex() {
        return mCodex.meta();
    }

    public File file() {

        // Don't default to home dir to avoid filling up memory
//        String dir = "";
//        if(Files.notExists(new File(USB_DIR).toPath())) {
//            dir = USER_DIR;
//        } else {
//            dir = USB_DIR;
//        }

        //THIS CODE IS USED FOR A MULTI-PARTITION DRIVE AND FINDING IT'S LOCATION IN THE ROBORIO FILE DIRECTORIES
        //It is also needed for re-discovering the usb when the roborio resets
//        String dir = "";
//        char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//        for ( char c : letters ) {
//            if (Files.exists((new File(String.format("/%c/logs/here.txt", c )).toPath()))) {
//                dir = "/" + c;
//                break;
//            }
//        }


        String dir = USER_DIR;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();

        File file = null;
        file = new File(String.format(dir + LOG_PATH_FORMAT,
                dtf.format( now ),
                mCodex.meta().getEnum().getSimpleName()
        ));
        System.out.println("Creating log file at " + file.toPath());

        return file;
    }
}