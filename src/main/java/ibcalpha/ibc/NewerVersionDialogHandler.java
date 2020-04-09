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
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

class NewerVersionDialogHandler implements WindowHandler {
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
        if (SwingUtils.clickButton(window, "OK")) {
        } else if (SwingUtils.clickButton(window, "No")) { // ie no we don't want the opportunity to upgrade now - Linux version only
        } else {
            Utils.logError("could not dismiss Newer Version because we could not find one of the controls.");
        }
    }

    public boolean recogniseWindow(Window window) {
        if (!(window instanceof JDialog)) return false;

        if (SwingUtils.findLabel(window, "Newer Version") != null) return true;
        JOptionPane op = SwingUtils.findOptionPane(window);
        return (op != null && op.getMessage() != null && op.getMessage().toString().contains("Newer Version"));
    }

}

