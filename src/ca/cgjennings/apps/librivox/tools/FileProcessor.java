package ca.cgjennings.apps.librivox.tools;

import javax.swing.JFrame;
import ca.cgjennings.apps.librivox.Checker;
import java.util.LinkedList;
import ca.cgjennings.apps.librivox.LibriVoxAudioFile;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.logging.Level;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import static ca.cgjennings.apps.librivox.Checker.string;

/**
 * A file processor performs a task on a series of audio files, and presents a
 * dialog showing the progress as it goes. Any errors that occur during
 * processing are collected and displayed separately after completion.
 *
 * @author Christopher G. Jennings https://cgjennings.ca/contact/
 * @since 0.92
 */
public class FileProcessor extends javax.swing.JDialog {

    private LinkedList<Task> tasks = new LinkedList<Task>();

    /**
     * Creates new form FileProcessor
     */
    public FileProcessor(java.awt.Frame parent) {
        super(parent, true);
        setUndecorated(true);
        initComponents();
        getRootPane().setDefaultButton(cancelBtn);
        ((JComponent) getContentPane()).setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        setLocationRelativeTo(parent);
    }

    public void setCancelEnabled(boolean enable) {
        if (isVisible()) {
            throw new IllegalStateException("call before starting");
        }
        cancelBtn.setVisible(enable);
        pack();
    }

    public boolean isCancelled() {
        return !cancelBtn.isEnabled();
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

        heading = new javax.swing.JLabel();
        bar = new javax.swing.JProgressBar();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        heading.setFont(heading.getFont().deriveFont(heading.getFont().getStyle() | java.awt.Font.BOLD, heading.getFont().getSize()+1));
        heading.setText(string("fp-heading")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 0, 8);
        getContentPane().add(heading, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(16, 16, 24, 16);
        getContentPane().add(bar, gridBagConstraints);

        cancelBtn.setText(string("cancel")); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 8, 8);
        getContentPane().add(cancelBtn, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void cancelBtnActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_cancelBtnActionPerformed
            cancelBtn.setEnabled(false);
            heading.setText(string("fp-cancelling"));
            if (worker != null) {
                worker.interrupt();
            }
	}//GEN-LAST:event_cancelBtnActionPerformed

    private void setHeading(final String text) {
        if (EventQueue.isDispatchThread()) {
            heading.setText(text);
        } else {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    heading.setText(text);
                }
            });
        }
    }

    private void indeterminate(final boolean indet) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                bar.setIndeterminate(indet);
            }
        });
    }

    private void value(final int value) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                bar.setValue(value);
            }
        });
    }

    public void addTask(Task t) {
        if (worker != null) {
            throw new IllegalStateException("already started");
        }
        if (t == null) {
            throw new NullPointerException("task");
        }
        tasks.add(t);
    }

    public void start() {
        bar.setMinimum(0);
        bar.setMaximum(tasks.size());
        worker = new Thread() {
            @Override
            public void run() {
                int n = 0;
                while (!tasks.isEmpty()) {
                    Task t = tasks.poll();
                    LibriVoxAudioFile f = t.getFile();

                    // wait for file to complete so we can modify it
                    if (!f.isDone()) {
                        setHeading(string("fp-waiting"));
                        indeterminate(true);

                        while (!f.isDone()) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                cancelFinish();
                                return; // cancelled
                            }
                        }

                        indeterminate(false);
                        setHeading(string("fp-heading"));
                    }

                    // still here? file is done, try doing the task
                    try {
                        t.process();
                    } catch (Throwable ex) {
                        Checker.getLogger().log(Level.SEVERE, null, ex);
                        if (b.length() > 0) {
                            b.append("\n\n");
                        }
                        b.append(f.getFileName()).append('\n');
                        String msg = ex.getMessage();
                        if (msg == null) {
                            msg = ex.toString();
                        }
                        b.append(msg);
                    }
                    if (Thread.interrupted()) {
                        cancelFinish();
                        return;
                    }
                    value(++n);
                }

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
            }
        };
        worker.start();
        setVisible(true);
    }

    private StringBuilder b = new StringBuilder();

    /**
     * Called when a cancel is detected to post this.dispose() to the EDT
     */
    private void cancelFinish() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                dispose();
            }
        });
    }

    private void finish() {
        dispose();
        if (b.length() > 0) {
            ProcessingErrorDialog d = new ProcessingErrorDialog((JFrame) getParent(), b.toString());
            d.setTitle(getTitle());
            d.setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bar;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel heading;
    // End of variables declaration//GEN-END:variables

    public static abstract class Task {

        private LibriVoxAudioFile f;

        public Task(LibriVoxAudioFile f) {
            this.f = f;
        }

        public LibriVoxAudioFile getFile() {
            return f;
        }

        /**
         * This can be called after you complete a task if the file should be
         * re-checked as a result of the processing.
         */
        public final void reanalyze() {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    getFile().reanalyze();
                }
            });
        }

        public abstract void process() throws Throwable;
    }

    private Thread worker;
}
