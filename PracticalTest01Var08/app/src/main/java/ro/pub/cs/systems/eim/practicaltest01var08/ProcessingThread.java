package ro.pub.cs.systems.eim.practicaltest01var08;

/**
 * Created by root on 01.04.2016.
 */
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private String text;

    public ProcessingThread(Context context, String text) {
        this.context = context;
        this.text = text;

    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private String[] actionTypes = {
            "com.example.root.practic.actionType1",
            "com.example.root.practic.actionType2",
            "com.example.root.practic.actionType3"
    };

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(actionTypes[random.nextInt(actionTypes.length)]);
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + text);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(50000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}