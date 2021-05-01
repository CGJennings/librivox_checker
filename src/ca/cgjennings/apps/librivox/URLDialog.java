package ca.cgjennings.apps.librivox;

import static ca.cgjennings.apps.librivox.Checker.string;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.UIManager;

/**
 * Dialog for entering URLs to add to the file table.
 *
 * @author Christopher G. Jennings (cjennings@acm.org)
 */
public class URLDialog extends javax.swing.JDialog {

    /**
     * Creates new form URLDialog
     */
    public URLDialog(Checker parent) {
        super(parent, false);
        initComponents();
        getRootPane().setDefaultButton(addButton);
        setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel instructions = new javax.swing.JLabel();
        urlField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(string("add-url-title")); // NOI18N
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        instructions.setText(string("add-url-instr")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(instructions, gridBagConstraints);

        urlField.setColumns(40);
        urlField.setText("http://");
        urlField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlFieldActionPerformed(evt);
            }
        });
        urlField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                urlFieldKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 8, 8);
        getContentPane().add(urlField, gridBagConstraints);

        addButton.setText(string("add-url-ok")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 32, 8, 8);
        getContentPane().add(addButton, gridBagConstraints);

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getSize()-1f));
        jLabel2.setText(string("add-url-note")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void urlFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlFieldActionPerformed
    try {
        URL url = new URL(urlField.getText());
        ((Checker) getParent()).checkURL(url);
    } catch (MalformedURLException e) {
        UIManager.getLookAndFeel().provideErrorFeedback(urlField);
        urlField.requestFocusInWindow();
    }
}//GEN-LAST:event_urlFieldActionPerformed

	private void formFocusGained( java.awt.event.FocusEvent evt ) {//GEN-FIRST:event_formFocusGained
            urlField.requestFocusInWindow();
	}//GEN-LAST:event_formFocusGained

	private void urlFieldKeyPressed( java.awt.event.KeyEvent evt ) {//GEN-FIRST:event_urlFieldKeyPressed
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                evt.consume();
                dispose();
            }
	}//GEN-LAST:event_urlFieldKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField urlField;
    // End of variables declaration//GEN-END:variables
}
