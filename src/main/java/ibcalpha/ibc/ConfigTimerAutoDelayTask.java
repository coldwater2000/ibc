package ibcalpha.ibc;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自动延时任务，向后延时固定时间段，保障系统不自动logout和重启
 */
public class ConfigTimerAutoDelayTask implements Runnable {
//    static GetConfigDialogTask _configDialogTask;
    final static Lock lock = new ReentrantLock();
    final static Condition gotConfigDialog = lock.newCondition();
    static JDialog configDialog;
    private int dealyMinute = 5;
    public ConfigTimerAutoDelayTask(int delayMinute){
        this.dealyMinute = dealyMinute;
    }

    @Override
    public void run() {
        try{
            Utils.logToConsole("Creating config dialog future");
            //点按钮
            final JFrame mainForm = MainWindowManager.mainWindowManager().getMainWindow();
            Utils.invokeMenuItem(mainForm, new String[] {"Configure", "Settings"});
            Utils.logToConsole("---Starting Lock");
            lock.lock();
            try{
                gotConfigDialog.await();
            }finally {
                lock.unlock();
            }
            Utils.logToConsole("---Unlock! ");
            SwingUtilities.invokeLater(()->{
                Utils.logToConsole("----Setting Lock and Exit Setting.");
                LocalDateTime now = LocalDateTime.now();
                //5分钟前
                LocalDateTime a_moment = now.plusMinutes(-this.dealyMinute);
                String time = a_moment.format(DateTimeFormatter.ofPattern("h:m"));

                try {
                    Utils.selectLockAndExitSettings(configDialog);
                } catch (IbcException e) {
                    e.printStackTrace();
                }
                JTextField timeField = SwingUtils.findTextField(configDialog,0);
                if (timeField != null) {
                    timeField.setText(time);
                    //AM/PM
                    String ampm = a_moment.format(DateTimeFormatter.ofPattern("a"));
                    if ("AM".equalsIgnoreCase(ampm)) {
                        JRadioButton amRadio = SwingUtils.findRadioButton(configDialog, "AM");
                        if (amRadio !=null ){
                            amRadio.setSelected(true);
                        }
                    }else {
                        JRadioButton pmRadio = SwingUtils.findRadioButton(configDialog, "PM");
                        if (pmRadio !=null ){
                            pmRadio.setSelected(true);
                        }
                    }
                    Utils.logToConsole("find Lock and Exit is now set to: " + time+" "+ampm);
                    SwingUtils.clickButton(configDialog, "OK");
                }


                configDialog = null;
            });


        } catch (InterruptedException e) {
            Utils.logException(e);
        }
    }

    public static void setConfigDialog(JDialog cDialog){
        Utils.logToConsole("get cDialog");
        configDialog = cDialog;
        lock.lock();
        try {
            gotConfigDialog.signal();
        }finally {
            lock.unlock();
        }
        Utils.logToConsole("---release Lock! ");
    }
}
