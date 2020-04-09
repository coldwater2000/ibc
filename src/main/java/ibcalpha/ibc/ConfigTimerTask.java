package ibcalpha.ibc;

import ibcalpha.ibc.ConfigurationAction;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfigTimerTask implements ConfigurationAction {
    JDialog configDialog;

    int delayMin;
    @Override
    public void initialise(JDialog configDialog) {
        this.configDialog = configDialog;
    }

    public ConfigTimerTask(int delayMinute){
        this.delayMin = delayMinute;
    }
    @Override
    public void run() {
        try{
            Utils.logToConsole("Setting Lock and Exit Setting.");
            LocalDateTime now = LocalDateTime.now();
            //5分钟前
            LocalDateTime a_moment = now.plusMinutes(-delayMin);
            String time = a_moment.format(DateTimeFormatter.ofPattern("h:m"));
            Utils.selectLockAndExitSettings(configDialog);
            JTextField timeField = SwingUtils.findTextField(configDialog,0);
            if (timeField == null) {
                // NB: we don't throw here because older TWS versions did not have this setting
                Utils.logError("could not find Lock and Exit ");
                return;
            }else {
                timeField.setText(time);
                Utils.logToConsole("find Lock and Exit is now set to: " + time);
            }

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
        } catch (IbcException e) {
            Utils.logException(e);
        }
    }
}
