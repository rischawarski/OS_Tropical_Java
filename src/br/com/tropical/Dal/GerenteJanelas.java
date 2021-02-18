package br.com.tropical.Dal;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author RSC Sistemas (gerencia janelas em java swing)
 */
public class GerenteJanelas {

    private static JDesktopPane jDesktopPane;

    public GerenteJanelas(JDesktopPane jDesktopPane) {
        GerenteJanelas.jDesktopPane = jDesktopPane;

    }

    public void abrirJanela(JInternalFrame jInternalFrame) {
        if (jInternalFrame.isVisible()) {
            jInternalFrame.toFront();
            jInternalFrame.requestFocus();
        } else {
            jDesktopPane.add(jInternalFrame);
            jInternalFrame.setVisible(true);
        }
    }

}
