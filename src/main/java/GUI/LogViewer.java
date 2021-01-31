package GUI;

import Model.Log;

import java.util.Optional;

public class LogViewer extends Thread{

    private Log log;

    public LogViewer(Log log) {
        this.log = log;
    }

    private LogViewer() {
    }


    @Override
    public void run() {
        super.run();
        Optional<String> message = this.log.loadLog();
        System.out.println(message.isPresent()?message.get():"No hay log");
    }
}
