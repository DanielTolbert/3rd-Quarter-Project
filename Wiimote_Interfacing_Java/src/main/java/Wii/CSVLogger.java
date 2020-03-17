package Wii;

import FRCCodexClasses.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CSVLogger {
    public static LinkedBlockingDeque<Log> kCSVLoggerQueue = new LinkedBlockingDeque<>();
    private List<CSVWriter> mCSVWriters;
    private static final ScheduledExecutorService mExService =
            Executors.newSingleThreadScheduledExecutor((run)->new Thread(run, "My timer thread"));
    private ScheduledFuture<?> scheduledFuture;
    private boolean mIsAcceptingToQueue;

    public CSVLogger( boolean pIsLogging ) {
        if ( pIsLogging ) {
            mCSVWriters = new ArrayList<>();
            for (Codex c : Manager.DATA.allCodexes) {
                mCSVWriters.add(new CSVWriter(c));
            }
            mIsAcceptingToQueue = false;
            logFromCodexToCSVHeader();
            scheduledFuture = mExService.scheduleAtFixedRate(this::run, 1, 1, TimeUnit.SECONDS);
        }
        else {
            mIsAcceptingToQueue = false;
        }
    }

    private void run() {
        if ( !kCSVLoggerQueue.isEmpty() ) {
            try {
                ArrayList<Log> kTempCSVLogs = new ArrayList<>();
                kCSVLoggerQueue.drainTo(kTempCSVLogs);
                //mLogger.error( "Drained queue got: " + kTempCSVLogs.size() );

                for ( Log log : kTempCSVLogs ) {
                    //TODO - fix the excessive exceptions
                    logFromCodexToCSVLog( log );
                }
            } catch (Exception e) {}
        }
    }

    public void logFromCodexToCSVHeader() {
        mCSVWriters.forEach(c -> c.writeHeader());
    }

    public void logFromCodexToCSVLog( Log pLog ) {
        for ( CSVWriter c : mCSVWriters ) {
            if ( c.getMetaDataOfAssociatedCodex().gid() == pLog.getmGlobalId() ) {
                c.log( pLog.getmLogData() );
                break;
            }
        }
    }

    /**
     * Opens the queue
     */
    public void start() {
        mIsAcceptingToQueue = true;
    }

    /**
     * Closes the queue
     */
    public void stop() {
        mIsAcceptingToQueue = false;
    }

    public void addToQueue( Log pLog ) {
        if ( mIsAcceptingToQueue ) {
            kCSVLoggerQueue.add( pLog );
        }
    }

    public void closeWriters() {
        for ( CSVWriter cw : mCSVWriters ) {
            cw.close();
        }
    }

}