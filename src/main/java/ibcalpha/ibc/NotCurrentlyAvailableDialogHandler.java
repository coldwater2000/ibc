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

package ibcalpha.ibc;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;

class NotCurrentlyAvailableDialogHandler implements WindowHandler {
    public boolean filterEvent(Window window, int eventId) {
        switch (eventId) {
            case WindowEvent.WINDOW_OPENED:
            case WindowEvent.WINDOW_ACTIVATED:
                return true;
            default:
                return false;
        }
    }

    public void handleWindow(Window window, int eventID) {
        if (! SwingUtils.clickButton(window, "OK")) {
            Utils.logError("The system is not currently available.");
            return;
        }

        if (LoginManager.loginManager().getLoginFrame() != null) {
            JButton button2 =
                    SwingUtils.findButton(LoginManager.loginManager().getLoginFrame(), "Login");
            button2.requestFocus();
            KeyEvent ke =
                     new KeyEvent(button2, KeyEvent.KEY_PRESSED,
                                  System.currentTimeMillis(),
                                  KeyEvent.ALT_DOWN_MASK,
                                  KeyEvent.VK_F4,
                                  KeyEvent.CHAR_UNDEFINED);
            button2.dispatchEvent(ke);
        }
    }

    public boolean recogniseWindow(Window window) {
        if (! (window instanceof JDialog)) return false;

        return (SwingUtils.titleContains(window, "Login") &&
                SwingUtils.findLabel(window, "not currently available") != null);
    }
}

