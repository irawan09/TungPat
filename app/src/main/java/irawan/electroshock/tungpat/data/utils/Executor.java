package irawan.electroshock.tungpat.data.utils;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {

    public static void IOThread(Runnable r){
        ExecutorService IOExecutors = Executors.newSingleThreadExecutor();
        IOExecutors.execute(r);
        IOExecutors.shutdown();
        Log.i("Executors Status", "All execution is done? "+IOExecutors.isShutdown());
    }
}
