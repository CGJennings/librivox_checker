package ca.cgjennings.apps.librivox;

import static ca.cgjennings.apps.librivox.Checker.string;
import ca.cgjennings.ui.LinearHistory;
import ca.cgjennings.util.Settings;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * Dialog window that displays built-in help files and opens external links in
 * the system browser.
 *
 * @author Christopher G. Jennings https://cgjennings.ca/contact/
 */
public class HelpViewer extends javax.swing.JDialog {

    private HelpViewer(java.awt.Frame parent) {
        super(parent, false);
        initComponents();
        customizeStyleSheet();

        getRootPane().setDefaultButton(closeBtn);
        ((HTMLDocument) view.getDocument()).setAsynchronousLoadPriority(-1);
        setLocationRelativeTo(parent);
    }

    /**
     * Customize the stylesheet for help files compared to reports.
     */
    private void customizeStyleSheet() {
        final HTMLEditorKit kit = (HTMLEditorKit) view.getEditorKit();
        final StyleSheet styles = kit.getStyleSheet();
        styles.addRule("body { margin: 18px; }");
    }

    /**
     * Creates a help viewer that is configured to behave as an About dialog.
     *
     * @param parent
     */
    static void showAboutViewer(java.awt.Frame parent) {
        HelpViewer v = new HelpViewer(parent);
        try {
            v.view.setPage(Settings.findResourceForLocale(v.getClass(), Checker.getPreferredLocale(), "about.html"));
        } catch (IOException e) {
            Checker.getLogger().log(Level.SEVERE, "missing help file", e);
        }
        try {
            BufferedImage bi = ImageIO.read(v.getClass().getResource("/resources/about.png"));
            if (bi != null) {
                v.aboutIcon.setIcon(new ImageIcon(bi));
                v.aboutIcon.setVisible(true);
                v.setLocationRelativeTo(parent);
            }
        } catch (Exception e) {
            Checker.getLogger().log(Level.WARNING, "failed to load About icon", e);
        }
        v.navPanel.setVisible(false);
        v.setTitle(string("about").replace("&", ""));
        v.setModal(true);
        v.setVisible(true);
    }

    /**
     * Shows a help page. The URL should be the URL of one of the help pages in
     * the resources.help package.
     *
     * @param parent the application window
     * @param content the URL of the page to display
     */
    public static void showHelpPage(Checker parent, URL content) {
        if (shared == null) {
            shared = new HelpViewer(parent);
            Checker.popToEdge(shared, 0);
        }
        shared.setPage(content);
        if (!shared.isVisible()) {
            shared.setVisible(true);
        }
    }
    private static HelpViewer shared;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JScrollPane viewScroll = new javax.swing.JScrollPane();
        view = new javax.swing.JEditorPane();
        closeBtn = new javax.swing.JButton();
        aboutIcon = new javax.swing.JLabel();
        aboutIcon.setVisible( false );
        externalURLLabel = new javax.swing.JLabel();
        navPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        fwdBtn = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        contentsBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(string("help-title")); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        viewScroll.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, java.awt.Color.gray));

        view.setEditable(false);
        view.setBorder(null);
        view.setContentType("text/html"); // NOI18N
        view.addHyperlinkListener( new HyperlinkListener() {
            public void hyperlinkUpdate( HyperlinkEvent e ) {
                if( e.getEventType() == HyperlinkEvent.EventType.ACTIVATED ) {
                    URL url = e.getURL();
                    if( url != null ) {
                        String protocol = url.getProtocol();
                        if( protocol.equalsIgnoreCase( "jar" ) || protocol.equalsIgnoreCase( "file" ) ) {
                            setPage( url );
                        } else {
                            try {
                                Desktop.getDesktop().browse( url.toURI() );
                            } catch( Throwable t ) {
                                ca.cgjennings.apps.librivox.Checker.getLogger().warning( t.toString() );
                            }
                        }
                    }
                } else if( e.getEventType() == HyperlinkEvent.EventType.ENTERED ) {
                    URL url = e.getURL();
                    if( url != null ) {
                        String protocol = url.getProtocol();
                        if( protocol.equalsIgnoreCase( "jar" ) || protocol.equalsIgnoreCase( "file" ) ) {
                            setURLLabel( null );
                        } else {
                            setURLLabel( url.toString() );
                        }
                    }
                } else if( e.getEventType() == HyperlinkEvent.EventType.EXITED ) {
                    setURLLabel( null );
                }
            }
        });
        viewScroll.setViewportView(view);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(viewScroll, gridBagConstraints);

        closeBtn.setText(string("close")); // NOI18N
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 8, 8);
        getContentPane().add(closeBtn, gridBagConstraints);

        aboutIcon.setBackground(java.awt.Color.white);
        aboutIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aboutIcon.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(aboutIcon, gridBagConstraints);

        externalURLLabel.setFont(externalURLLabel.getFont().deriveFont(externalURLLabel.getFont().getSize()-1f));
        externalURLLabel.setForeground(java.awt.Color.gray);
        externalURLLabel.setText("    ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 8, 4, 8);
        getContentPane().add(externalURLLabel, gridBagConstraints);

        navPanel.setLayout(new java.awt.GridBagLayout());

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/back.png"))); // NOI18N
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        navPanel.add(backBtn, gridBagConstraints);

        fwdBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/forward.png"))); // NOI18N
        fwdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fwdBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        navPanel.add(fwdBtn, gridBagConstraints);

        titleLabel.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        navPanel.add(titleLabel, gridBagConstraints);

        contentsBtn.setFont(contentsBtn.getFont().deriveFont(contentsBtn.getFont().getSize()-1f));
        contentsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/contents.png"))); // NOI18N
        contentsBtn.setText(string("help-contents")); // NOI18N
        contentsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentsBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        navPanel.add(contentsBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 3, 8);
        getContentPane().add(navPanel, gridBagConstraints);

        setBounds(0, 0, 419, 453);
    }// </editor-fold>//GEN-END:initComponents

private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
    dispose();
}//GEN-LAST:event_closeBtnActionPerformed

	private void contentsBtnActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_contentsBtnActionPerformed
            setPage(Settings.findResourceForLocale(getClass(), Checker.getPreferredLocale(), "/resources/help/index.html"));
	}//GEN-LAST:event_contentsBtnActionPerformed

	private void backBtnActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_backBtnActionPerformed
            history.back();
	}//GEN-LAST:event_backBtnActionPerformed

	private void fwdBtnActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_fwdBtnActionPerformed
            history.forward();
	}//GEN-LAST:event_fwdBtnActionPerformed

    private void setURLLabel(String label) {
        if (label == null) {
            label = " ";
        }
        externalURLLabel.setText(label);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutIcon;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton contentsBtn;
    private javax.swing.JLabel externalURLLabel;
    private javax.swing.JButton fwdBtn;
    private javax.swing.JPanel navPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JEditorPane view;
    // End of variables declaration//GEN-END:variables

    // maximum amount of help file to read in when looking for <title> tag, in bytes
    private static final int TITLE_HEADER_SCAN_SIZE = 4096;

    private LinearHistory<URL> history = new LinearHistory<URL>() {
        @Override
        public void display(URL position) {
            try {
                try {
                    // try to grab the page title
                    InputStream in = null;
                    try {
                        in = position.openStream();
                        BufferedReader r = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")), TITLE_HEADER_SCAN_SIZE);
                        char[] buff = new char[TITLE_HEADER_SCAN_SIZE];
                        int charsRead = r.read(buff);
                        String header = new String(buff, 0, charsRead);
                        Matcher m = Pattern.compile("<title>(.*)</title>", Pattern.CASE_INSENSITIVE).matcher(header);
                        if (m.find()) {
                            setTitle(m.group(1));
                        }
                    } finally {
                        if (in != null) {
                            in.close();
                        }
                    }

                    // load the page into the browser
                    view.setPage(position);
                } catch (FileNotFoundException f) {
                    view.setPage(Settings.findResourceForLocale(getClass(), Checker.getPreferredLocale(), "/resources/help/nyi.html"));
                }
            } catch (IOException ex) {
                Checker.getLogger().log(Level.SEVERE, null, ex);
                UIManager.getLookAndFeel().provideErrorFeedback(view);
            }
            updateHistoryButtons();
        }

        @Override
        public void go(URL position) {
            super.go(position);
        }
    };

    private void setPage(URL location) {
        history.go(location);
    }

    private void updateHistoryButtons() {
        backBtn.setEnabled(history.canGoBack());
        fwdBtn.setEnabled(history.canGoForward());
    }
}
