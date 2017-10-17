package ca.cgjennings.apps.librivox.tools;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import org.jaudiotagger.tag.id3.ID3v24Frames;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFrame;
import ca.cgjennings.apps.librivox.Checker;
import ca.cgjennings.apps.librivox.ImageUtils;
import ca.cgjennings.apps.librivox.metadata.MetadataView;
import ca.cgjennings.apps.librivox.LibriVoxAudioFile;
import ca.cgjennings.apps.librivox.metadata.LibriVoxFileNameMetadata;
import ca.cgjennings.apps.librivox.metadata.MP3FileMetadata;
import ca.cgjennings.apps.librivox.metadata.SafeMetadataView;
import ca.cgjennings.io.StreamCopier;
import ca.cgjennings.platform.PlatformSupport;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagFieldKey;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import static ca.cgjennings.apps.librivox.Checker.string;

/**
 * Dialog for editing ID3 tags.
 *
 * @author Christopher G. Jennings (cjennings@acm.org)
 * @since 0.92
 */
public class ID3Editor extends javax.swing.JDialog {
	private LibriVoxAudioFile exemplar;
	private LibriVoxAudioFile[] files;

	/** Creates new form ID3Editor */
	public ID3Editor( java.awt.Frame parent, LibriVoxAudioFile[] files, LibriVoxAudioFile exemplar ) {
		super( parent, true );
		this.exemplar = exemplar;
		this.files = files;
		initComponents();
		okBtn.setText( String.format( okBtn.getText(), files.length ) );
		getRootPane().setDefaultButton( okBtn );
		PlatformSupport.correctOKCancelOrder( okBtn, cancelBtn );

		initCheckboxes( files != null && files.length > 1 );

		if( !exemplar.isDone() ) {
			FileProcessor waitFP = new FileProcessor( parent );
			waitFP.addTask( new FileProcessor.Task( exemplar ) {
				@Override
				public void process() throws Throwable {}
			});
			waitFP.start();
			if( waitFP.isCancelled() ) throw new CancellationException();
		}

		fillIn( exemplar );
	}

	private void fillIn( LibriVoxAudioFile exemplar ) {
		MP3FileMetadata mp3 = exemplar.getMetadata();
		MetadataView v = new SafeMetadataView( mp3.getID3Metadata() );
		titleField.setText( v.getTitle().trim() );
		artistField.setText( v.getArtist().trim() );
		albumField.setText( v.getAlbum().trim() );
		genreField.setText( v.getGenre().trim() );
		commentField.setText( v.getComment().trim() );
		if( v.getTrack().length() == 0 ) {
			trackCheck.setSelected( false );
		}
		trackField.setText( v.getTrack() );
		yearField.setText( v.getYear() );

		artDrop.setImage( v.getImage() );

		select0( titleField, artistField, albumField, genreField, commentField );

		updateControls();
	}

	public void select0( JTextComponent... fields ) {
		for( int i=0; i<fields.length; ++i ) {
			fields[i].select(0,0);
		}
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        editPanel = new javax.swing.JPanel();
        javax.swing.JLabel chgLabel = new javax.swing.JLabel();
        titleCheck = new javax.swing.JCheckBox();
        titleLabel = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        artistCheck = new javax.swing.JCheckBox();
        artistLabel = new javax.swing.JLabel();
        artistField = new javax.swing.JTextField();
        albumCheck = new javax.swing.JCheckBox();
        albumLabel = new javax.swing.JLabel();
        albumField = new javax.swing.JTextField();
        javax.swing.JLabel chgLabel1 = new javax.swing.JLabel();
        trackCheck = new javax.swing.JCheckBox();
        trackLabel = new javax.swing.JLabel();
        yearCheck = new javax.swing.JCheckBox();
        yearLabel = new javax.swing.JLabel();
        genreCheck = new javax.swing.JCheckBox();
        genreLabel = new javax.swing.JLabel();
        genreField = new javax.swing.JTextField();
        artCheck = new javax.swing.JCheckBox();
        artLabel = new javax.swing.JLabel();
        commentCheck = new javax.swing.JCheckBox();
        commentLabel = new javax.swing.JLabel();
        javax.swing.JScrollPane commentScroll = new javax.swing.JScrollPane();
        commentField = new javax.swing.JTextArea();
        javax.swing.JLabel bracketLabel = new javax.swing.JLabel();
        autofillBtn = new javax.swing.JToggleButton();
        listPanel = new javax.swing.JPanel();
        checkAllBtn = new javax.swing.JButton();
        uncheckAll = new javax.swing.JButton();
        delArtPanel = new javax.swing.JPanel();
        artPanel = new javax.swing.JPanel();
        artDrop = new ca.cgjennings.ui.ImageDrop();
        javax.swing.JLabel dropLabel = new javax.swing.JLabel();
        deleteArtButton = new javax.swing.JButton();
        trackField = new javax.swing.JTextField();
        yearField = new javax.swing.JTextField();
        footerPanel = new javax.swing.JPanel();
        okCancelBtn = new javax.swing.JPanel();
        okBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        javax.swing.JLabel spacer = new javax.swing.JLabel();
        overlayPanel = new ca.cgjennings.ui.OverlayPanel();
        formatCombo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(string("id3-title")); // NOI18N

        editPanel.setLayout(new java.awt.GridBagLayout());

        chgLabel.setFont(chgLabel.getFont().deriveFont(chgLabel.getFont().getStyle() | java.awt.Font.BOLD, chgLabel.getFont().getSize()-1));
        chgLabel.setText(string("id3-change")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 2, 4);
        editPanel.add(chgLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 8);
        editPanel.add(titleCheck, gridBagConstraints);

        titleLabel.setDisplayedMnemonic('t');
        titleLabel.setLabelFor(titleField);
        titleLabel.setText(string("mv-title")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(titleLabel, gridBagConstraints);

        titleField.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(titleField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 8);
        editPanel.add(artistCheck, gridBagConstraints);

        artistLabel.setDisplayedMnemonic('a');
        artistLabel.setLabelFor(artistField);
        artistLabel.setText(string("mv-artist")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(artistLabel, gridBagConstraints);

        artistField.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(artistField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 8);
        editPanel.add(albumCheck, gridBagConstraints);

        albumLabel.setDisplayedMnemonic('b');
        albumLabel.setLabelFor(albumField);
        albumLabel.setText(string("mv-album")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(albumLabel, gridBagConstraints);

        albumField.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(albumField, gridBagConstraints);

        chgLabel1.setFont(chgLabel1.getFont().deriveFont(chgLabel1.getFont().getStyle() | java.awt.Font.BOLD, chgLabel1.getFont().getSize()-1));
        chgLabel1.setText(string("id3-change")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 2, 4);
        editPanel.add(chgLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 8);
        editPanel.add(trackCheck, gridBagConstraints);

        trackLabel.setDisplayedMnemonic('k');
        trackLabel.setLabelFor(trackField);
        trackLabel.setText(string("mv-track")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(trackLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 8);
        editPanel.add(yearCheck, gridBagConstraints);

        yearLabel.setDisplayedMnemonic('y');
        yearLabel.setText(string("mv-year")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(yearLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 8);
        editPanel.add(genreCheck, gridBagConstraints);

        genreLabel.setDisplayedMnemonic('g');
        genreLabel.setLabelFor(genreField);
        genreLabel.setText(string("mv-genre")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(genreLabel, gridBagConstraints);

        genreField.setColumns(16);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(genreField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 8);
        editPanel.add(artCheck, gridBagConstraints);

        artLabel.setDisplayedMnemonic('c');
        artLabel.setLabelFor(artDrop);
        artLabel.setText(string("id3-art")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        editPanel.add(artLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 8);
        editPanel.add(commentCheck, gridBagConstraints);

        commentLabel.setDisplayedMnemonic('m');
        commentLabel.setLabelFor(commentField);
        commentLabel.setText(string("mv-comment")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        editPanel.add(commentLabel, gridBagConstraints);

        commentField.setLineWrap(true);
        commentField.setRows(5);
        commentField.setWrapStyleWord(true);
        commentScroll.setViewportView(commentField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 36, 10, 2);
        editPanel.add(commentScroll, gridBagConstraints);

        bracketLabel.setText(" ");
        bracketLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, java.awt.Color.gray));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        editPanel.add(bracketLabel, gridBagConstraints);

        autofillBtn.setText(string("id3-autofill")); // NOI18N
        autofillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autofillBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 8, 8);
        editPanel.add(autofillBtn, gridBagConstraints);

        listPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.white), javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        listPanel.setLayout(new java.awt.GridLayout(2, 1, 0, 2));

        checkAllBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/checkall.png"))); // NOI18N
        checkAllBtn.setToolTipText(string("id3-checkall")); // NOI18N
        checkAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAllBtnActionPerformed(evt);
            }
        });
        listPanel.add(checkAllBtn);

        uncheckAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/uncheckall.png"))); // NOI18N
        uncheckAll.setToolTipText(string("id3-uncheckall")); // NOI18N
        uncheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAllBtnActionPerformed(evt);
            }
        });
        listPanel.add(uncheckAll);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 12, 8);
        editPanel.add(listPanel, gridBagConstraints);

        delArtPanel.setLayout(new java.awt.GridBagLayout());

        artPanel.setBackground(java.awt.Color.white);
        artPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray));
        artPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 10, 6);
        artPanel.add(artDrop, gridBagConstraints);

        dropLabel.setFont(dropLabel.getFont().deriveFont(dropLabel.getFont().getSize()-1f));
        dropLabel.setForeground(java.awt.Color.gray);
        dropLabel.setText(string("id3-drop-art")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 10, 6);
        artPanel.add(dropLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        delArtPanel.add(artPanel, gridBagConstraints);

        deleteArtButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete.png"))); // NOI18N
        deleteArtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteArtButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 6, 8);
        delArtPanel.add(deleteArtButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 36, 16, 8);
        editPanel.add(delArtPanel, gridBagConstraints);

        trackField.setColumns(4);
        trackField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(trackField, gridBagConstraints);

        yearField.setColumns(4);
        yearField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        editPanel.add(yearField, gridBagConstraints);

        getContentPane().add(editPanel, java.awt.BorderLayout.CENTER);

        footerPanel.setLayout(new java.awt.GridBagLayout());

        okCancelBtn.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        okBtn.setText(string("id3-ok")); // NOI18N
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });
        okCancelBtn.add(okBtn);

        cancelBtn.setText(string("cancel")); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        okCancelBtn.add(cancelBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 8);
        footerPanel.add(okCancelBtn, gridBagConstraints);

        spacer.setText("    ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        footerPanel.add(spacer, gridBagConstraints);

        ca.cgjennings.ui.ArcBorder arcBorder1 = new ca.cgjennings.ui.ArcBorder();
        arcBorder1.setArcSize(28);
        arcBorder1.setHardening(0.3F);
        arcBorder1.setThickness(2);
        overlayPanel.setBorder(arcBorder1);
        overlayPanel.setLayout(new java.awt.GridBagLayout());

        formatCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID3 v2.3", "ID3 v2.4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 16);
        overlayPanel.add(formatCombo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        footerPanel.add(overlayPanel, gridBagConstraints);

        getContentPane().add(footerPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void autofillBtnActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_autofillBtnActionPerformed
		JCheckBox[] boxes = new JCheckBox[]{ trackCheck, yearCheck, genreCheck };
		if( autofillBtn.isSelected() ) {
			// store whether check boxes are checked
			for( int i=0; i<boxes.length; ++i ) {
				safeChecked[i] = boxes[i].isSelected();
				boxes[i].setSelected( true );
			}
		} else {
			// restore saved check status
			for( int i=0; i<boxes.length; ++i ) {
				boxes[i].setSelected( safeChecked[i] );
			}
		}

		updateControls();

		if( autofillBtn.isSelected() ) {
			// store current values in case autofill disabled later
			safeTrack = trackField.getText();
			safeYear = yearField.getText();
			safeGenre = genreField.getText();

			// fill in autofill values
			trackField.setText( trackFromFile( exemplar, titleField.getText() ) );
			yearField.setText( currentYear );
			genreField.setText( "Speech" );
		} else {
			// restore previously saved values
			trackField.setText( safeTrack );
			yearField.setText( safeYear );
			genreField.setText( safeGenre );
		}
		select0( trackField, yearField, genreField );
	}//GEN-LAST:event_autofillBtnActionPerformed

	private void cancelBtnActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_cancelBtnActionPerformed
		dispose();
	}//GEN-LAST:event_cancelBtnActionPerformed

	private void okBtnActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_okBtnActionPerformed
		dispose();
		artDataCache = null;
		FileProcessor proc = new FileProcessor( (JFrame) getParent() );
		for( int i=0; i<files.length; ++i ) {
			proc.addTask( new FileProcessor.Task( files[i] ) {
				@Override
				public void process() throws Throwable {
					apply( getFile() );
					reanalyze();
				}
			});
		}
		proc.setTitle( getTitle() );
		proc.start();
	}//GEN-LAST:event_okBtnActionPerformed

	private void checkAllBtnActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_checkAllBtnActionPerformed
		checkAll( evt.getSource() == checkAllBtn );
	}//GEN-LAST:event_checkAllBtnActionPerformed

	private void deleteArtButtonActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_deleteArtButtonActionPerformed
		artDrop.setFile( null );
	}//GEN-LAST:event_deleteArtButtonActionPerformed



	private void checkAll( boolean check ) {
		int count = editPanel.getComponentCount();
		for( int i=0; i<count; ++i ) {
			Component comp = editPanel.getComponent( i );
			if( comp instanceof JCheckBox ) {
				((JCheckBox) comp).setSelected( check );
			}
		}
		updateControls();
	}

	/**
	 * Extract the track (section) number from a file if possible.
	 * @param f the file to find the track for
	 * @return the track number, or blank
	 */
	private String trackFromFile( LibriVoxAudioFile f, String trackTitle ) {
		if( trackTitle != null ) {
			Matcher m = Pattern.compile( "0*(\\d+)" ).matcher( trackTitle );
			if( m.find() ) {
				return m.group(1);
			}
		}

		LibriVoxFileNameMetadata fnmd = new LibriVoxFileNameMetadata( f.getFileName() );
		int ss = fnmd.getSection();
		return ss < 0 ? "" : String.valueOf( ss );
	}

	private final String currentYear = String.valueOf( Calendar.getInstance().get( Calendar.YEAR ) );

	private boolean[] safeChecked = new boolean[3];
	private String safeTrack;
	private String safeYear;
	private String safeGenre;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox albumCheck;
    private javax.swing.JTextField albumField;
    private javax.swing.JLabel albumLabel;
    private javax.swing.JCheckBox artCheck;
    private ca.cgjennings.ui.ImageDrop artDrop;
    private javax.swing.JLabel artLabel;
    private javax.swing.JPanel artPanel;
    private javax.swing.JCheckBox artistCheck;
    private javax.swing.JTextField artistField;
    private javax.swing.JLabel artistLabel;
    private javax.swing.JToggleButton autofillBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton checkAllBtn;
    private javax.swing.JCheckBox commentCheck;
    private javax.swing.JTextArea commentField;
    private javax.swing.JLabel commentLabel;
    private javax.swing.JPanel delArtPanel;
    private javax.swing.JButton deleteArtButton;
    private javax.swing.JPanel editPanel;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JComboBox formatCombo;
    private javax.swing.JCheckBox genreCheck;
    private javax.swing.JTextField genreField;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JPanel listPanel;
    private javax.swing.JButton okBtn;
    private javax.swing.JPanel okCancelBtn;
    private ca.cgjennings.ui.OverlayPanel overlayPanel;
    private javax.swing.JCheckBox titleCheck;
    private javax.swing.JTextField titleField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JCheckBox trackCheck;
    private javax.swing.JTextField trackField;
    private javax.swing.JLabel trackLabel;
    private javax.swing.JButton uncheckAll;
    private javax.swing.JCheckBox yearCheck;
    private javax.swing.JTextField yearField;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables

	private void initCheckboxes( boolean multiFile ) {
		ActionListener updateListener = new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				updateControls();
			}
		};

		int count = editPanel.getComponentCount();
		for( int i=0; i<count; ++i ) {
			Component c = editPanel.getComponent( i );
			if( c instanceof JCheckBox ) {
				((JCheckBox) c).setSelected( true );
				((JCheckBox) c).addActionListener( updateListener );
			}
		}

		if( multiFile ) {
			titleCheck.setSelected( false );
			trackCheck.setSelected( false );
			commentCheck.setSelected( false );
		}

		FocusListener selectOnFocus = new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				Object o = e.getSource();
				if( o != null && (o instanceof JTextComponent) ) {
					((JTextComponent) o).selectAll();
				}
			}
		};
		for( int i=0; i<editPanel.getComponentCount(); ++i ) {
			Component c = editPanel.getComponent( i );
			if( c instanceof JTextComponent ) {
				c.addFocusListener( selectOnFocus );
			}
		}

		titleField.getDocument().addDocumentListener( new DocumentListener() {
			@Override
			public void insertUpdate( DocumentEvent e ) {
				changedUpdate( e );
			}
			@Override
			public void removeUpdate( DocumentEvent e ) {
				changedUpdate( e );
			}
			@Override
			public void changedUpdate( DocumentEvent e ) {
				EventQueue.invokeLater( new Runnable() {
					@Override
					public void run() {
						if( autofillBtn.isSelected() ) {
							String track = trackField.getText();
							String newTrack = trackFromFile( exemplar, titleField.getText() );
							if( !track.equals( newTrack ) ) {
								trackField.setText( newTrack );
								trackField.select(0,0);
							}
						}
					}
				});
			}
		});

		updateControls();
	}

	private void updateControls() {
		// enable/disable based on "change" checks
		u( titleCheck, titleLabel, titleField );
		u( artistCheck, artistLabel, artistField );
		u( albumCheck, albumLabel, albumField );
		u( trackCheck, trackLabel, trackField );
		u( yearCheck, yearLabel, yearField );
		u( genreCheck, genreLabel, genreField );
		u( commentCheck, commentLabel, commentField );

		// enable/disable art
		boolean e = artCheck.isSelected();
		artLabel.setEnabled( e );
		artPanel.setEnabled( e );
		deleteArtButton.setEnabled( e );

		// you can uncheck an autofill field, but can't edit it
		if( autofillBtn.isSelected() ) {
			a( trackCheck, trackLabel, trackField );
			a( yearCheck, yearLabel, yearField );
			a( genreCheck, genreLabel, genreField );
		}

		// disable OK if nothing checked
		int boxes = editPanel.getComponentCount();
		int box=0;
		for( ; box < boxes; ++box ) {
			Component comp = editPanel.getComponent( box );
			if( comp instanceof JCheckBox && ((JCheckBox) comp).isSelected() ) {
				break;
			}
		}
		PlatformSupport.getOK( okBtn, cancelBtn ).setEnabled( box < boxes );
	}
	private static void u( JCheckBox check, JLabel label, JComponent field ) {
		boolean enable = check.isSelected();
		label.setEnabled( enable );
		field.setEnabled( enable );
	}
	private static void a( JCheckBox check, JLabel label, JComponent field ) {
//		check.setSelected( true );
//		check.setEnabled( false );
		label.setEnabled( false );
		field.setEnabled( false );
	}



	private void apply( LibriVoxAudioFile lvf ) throws IOException {
		try {
			MP3File mp3 = new MP3File( lvf.getLocalFile(), MP3File.LOAD_IDV1TAG|MP3File.LOAD_IDV2TAG );

			// create a tag with the initial fields from the existing tag
			// so we preserve any data we don't know how to handle
			ID3v24Tag tag = mp3.getID3v2TagAsv24();
			if( tag == null ) {
				// use ID3 v1 fields if available
				ID3v1Tag v1Tag = mp3.getID3v1Tag();
				if( v1Tag != null ) {
					tag = new ID3v24Tag( v1Tag );
				} else {
					tag = new ID3v24Tag();
				}
			}

			// copy fields we are supposed to change into the new tag

			// we need to start with an accurate track number so we
			// can handle the special title prefix
			if( trackCheck.isSelected() ) {
				String v;
				if( autofillBtn.isSelected() ) {
					v = trackFromFile( lvf, titleField.getText() );
				} else {
					v = trackField.getText();
				}
				tag.deleteTagField( TagFieldKey.TRACK );
				if( !e(v) ) {
					tag.setTrack( v );
				}
			}


			if( titleCheck.isSelected() ) {
				String v = titleField.getText();
				tag.deleteTagField( TagFieldKey.TITLE );
				if( !e(v) ) {
					tag.setTitle( v );
				}
			}
			if( artistCheck.isSelected() ) {
				String v = artistField.getText();
				tag.deleteTagField( TagFieldKey.ARTIST );
				if( !e(v) ) {
					tag.setArtist( v );
				}
			}
			if( albumCheck.isSelected() ) {
				tag.deleteTagField( TagFieldKey.ALBUM );
				String v = albumField.getText();
				if( !e(v) ) {
					tag.setAlbum( v );
				}
			}
			if( yearCheck.isSelected() ) {
				String v = yearField.getText();
				tag.deleteTagField( TagFieldKey.YEAR );
				if( !e(v) ) {
					tag.setYear( v );
				}
			}
			if( genreCheck.isSelected() ) {
				String v = genreField.getText();
				tag.deleteTagField( TagFieldKey.GENRE );
				if( !e(v) ) {
					tag.setGenre( v );
				}
			}
			if( commentCheck.isSelected() ) {
				String v = commentField.getText();
				tag.deleteTagField( TagFieldKey.COMMENT );
				if( !e(v) ) {
					tag.setComment( v );
				}
			}

			if( artCheck.isSelected() ) {
				// delete existing images
				tag.removeFrameOfType( ID3v24Frames.FRAME_ID_ATTACHED_PICTURE );
				tag.deleteTagField( TagFieldKey.COVER_ART );

				if( artDrop.getImage() != null ) {
					// insert current image as JPEG (de facto standard)
					if( artDataCache == null ) {
						prepareArtData();
					}
					tag.set( tag.createArtworkField( artDataCache, MIME_TYPE ) );
				}
			}

			// convert internal v2.4 to v2.3 if selected
			AbstractID3v2Tag v2Tag = tag;
			if( formatCombo.getSelectedIndex() == 0 ) {
				v2Tag = new ID3v23Tag( tag );
				// It seems that the automatic conversion of year tags v24 to v23
				// does not work sometimes, so we need to set it explicitly.
				if( yearCheck.isSelected() ) {
					v2Tag.setYear( tag.getFirstYear() );
				}
			}

			mp3.setID3v1Tag( (ID3v1Tag) null );
			mp3.setID3v2Tag( (AbstractID3v2Tag) v2Tag );
			mp3.commit();
		} catch( IOException e ) {
			Checker.getLogger().log( Level.SEVERE, null, e );
			throw e;
		} catch( Throwable e ) {
			Checker.getLogger().log( Level.SEVERE, null, e );
			throw new IOException( e );
		}
	}

	/**
	 * Returns <code>true</code> if a string is null or empty. This is used
	 * when creating a tag to decide whether to delete a field (empty string)
	 * or write a new value (non-empty string).
	 * @param val
	 * @return
	 */
	private boolean e( String val ) {
		return val == null || val.length() == 0;
	}

	private void prepareArtData() throws IOException {
		if( !artCheck.isSelected() ) {
			artDataCache = null;
			return;
		}
		BufferedImage bi = artDrop.getImage();
		File f = artDrop.getFile();

		// if it is already a jpeg and it is 300x300 or less,
		// then we will use the bytes from the file itself,
		// so we don't lose image quality on a re-encode
		//
		// otherwise, take the image data, resize if required,
		// and write as a JPEG to a byte array

		boolean reuseJPEG = false;
		if( f != null ) {
			String extCheck = f.getName().toLowerCase( Locale.CANADA );
			reuseJPEG = extCheck.endsWith( ".jpg" ) || extCheck.endsWith( ".jpeg");
		}

		if( reuseJPEG && bi.getWidth() < MAX_SIZE && bi.getHeight() < MAX_SIZE ) {
			ByteArrayOutputStream bout = new ByteArrayOutputStream( ((int)f.length()) + 1024 ); // add a bit in case the length is -1
			InputStream in = null;
			try {
				in = new FileInputStream( f );
				in = new BufferedInputStream( in, 64*1024 );
				StreamCopier.copyStream( in, bout );
				artDataCache = bout.toByteArray();
			} finally {
				if( in != null ) in.close();
			}
		} else {
			bi = ImageUtils.fitImage( bi, MAX_SIZE );
			ByteArrayOutputStream bout = new ByteArrayOutputStream( 96 * 1024 );
			ImageIO.write( bi, "jpg", bout );
			artDataCache = bout.toByteArray();
		}
	}

	private byte[] artDataCache;

	private static final String MIME_TYPE = "image/jpeg";
	private static final int MAX_SIZE = 300;
}
