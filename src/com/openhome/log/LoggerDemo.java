package com.openhome.log;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerDemo {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(LoggerDemo.class.getName());
        //如果把level設定為warning的話，handler就只能處理warning層級以上的log
        logger.setLevel(Level.FINE);
        for(var handler: logger.getHandlers()) {
            System.out.println(handler.getLevel());
        }
        for(Handler handler : logger.getParent().getHandlers()) {
            handler.setLevel(Level.FINE);
        }
        logger.log(Level.WARNING, "WARNING");
        logger.log(Level.INFO, "INFO");
        logger.log(Level.CONFIG, "CONFIG");
        logger.log(Level.FINE, "FINE");
    }
}
