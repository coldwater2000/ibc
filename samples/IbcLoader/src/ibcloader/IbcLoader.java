// This file is part of IBC.
// Copyright (C) 2004 Steven M. Kearns (skearns23@yahoo.com )
// Copyright (C) 2004 - 2018 Richard L King (rlking@aultan.com)
// For conditions of distribution and use, see copyright notice in COPYING.txt

// IBC is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// IBC is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with IBC.  If not, see <http://www.gnu.org/licenses/>.

package ibcloader;

import ibcalpha.ibc.*;

import java.util.concurrent.*;

public class IbcLoader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            setupEnvironment(true);
            ibcalpha.ibc.IbcTws.load();
//            new ConfigurationTask(new ConfigureDownloadOrderApiTask(false)).executeAsync();
//            ConfigurationTask timeTask = new ConfigurationTask(new ConfigTimerTask());
//            timeTask.executeAsync();
            //每一分钟修正重启时间，当前时间向后挪5分钟
//            new ScheduledThreadPoolExecutor(1).scheduleWithFixedDelay(()->{
////                ConfigDialogManager.setDefault();
////                ConfigDialogManager.configDialogManager().setSplashScreenClosed();
////                ConfigDialogManager.configDialogManager().clearConfigDialog();
//                new ConfigTimerAutoDelayTask(5).run();
//            },30, 20,TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void setupEnvironment(final boolean isGateway) throws Exception {
        Settings.initialise(new MySettings());
        LoginManager.initialise(new MyLoginManager());
        MainWindowManager.initialise(new MyMainWindowManager(isGateway));
        TradingModeManager.initialise(new MyTradingModeManager());
    }

}
