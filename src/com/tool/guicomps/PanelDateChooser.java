package com.tool.guicomps;

import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;



/**
 * Custom dialog box to enter dates. The <code>PanelDateChooser</code>
 * class presents a calendar and allows the user to visually select a
 * day, month and cmbYear so that it is impossible to enter an invalid
 * date.
 **/
public class PanelDateChooser extends JPanel implements ItemListener, MouseListener, FocusListener, KeyListener, ActionListener, Constants
{
    
     private DateFormat  dateFormat;
    
    /** Names of the months. */
    private static final String[] MONTHS = 
	new String[] {
	    "Ιανουάριος",
	    "Φεβρουάριος",
	    "Μάρτιος",
	    "Απρίλιος",
	    "Μάϊος",
	    "Ιούνιος",
	    "Ιούλιος",
	    "Αύγουστος",
	    "Σεπτέμβριος",
	    "Οκτώβριος",
	    "Νοέμβριος",
	    "Δεκέμβριος"
	};

    /** Names of the days of the week. */
    private static final String[] DAYS =
	new String[] {
	    "Κυρ",
	    "Δευ",
	    "Τρι",
	    "Τετ",
	    "Πέμ",
	    "Παρ",
	    "Σαβ"
	};

    /** Text color of the days of the weeks, used as column headers in
        the calendar. */
    private static final Color WEEK_DAYS_FOREGROUND = Color.black;

    /** Text color of the days' numbers in the calendar. */
    private static final Color DAYS_FOREGROUND = Color.blue;

    /** Background color of the selected day in the calendar. */
    private static final Color SELECTED_DAY_FOREGROUND = Color.white;

    /** Text color of the selected day in the calendar. */
    private static final Color SELECTED_DAY_BACKGROUND = Color.blue;

    /** Empty border, used when the calendar does not have the focus. */
    private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(1,1,1,1);

    /** Border used to highlight the selected day when the calendar
        has the focus. */
    private static final Border FOCUSED_BORDER = BorderFactory.createLineBorder(Color.yellow,1);



    /** Auxiliary variable to compute dates. */
    private GregorianCalendar calendar;

    /** Calendar, as a matrix of labels. The first row represents the
        first week of the month, the second row, the second week, and
        so on. Each column represents a day of the week, the first is
        Sunday, and the last is Saturday. The label's text is the
        number of the corresponding day. */
    private JLabel[][] days;

    /** Day selection control. It is just a panel that can receive the
        focus. The actual user interaction is driven by the
        <code>PanelDateChooser</code> class. */
    private FocusablePanel daysGrid;

    /** Month selection control. */
    private JComboBox month;

    /** Year selection control. */
    private JComboBox cmbYear;

    /** "Ok" button. */
//    private JButton ok;

    /** "Cancel" button. */
//    private JButton cancel;

    /** Day of the week (0=Sunday) corresponding to the first day of
        the selected month. Used to calculate the position, in the
        calendar ({@link #days}), corresponding to a given day. */
    private int offset;

    /** Last day of the selected month. */
    private int lastDay;

    /** Selected day. */
    private JLabel day;

    /** <code>true</code> if the "Ok" button was clicked to close the
        dialog box, <code>false</code> otherwise. */
    private boolean okClicked;



    /**
     * Custom panel that can receive the focus. Used to implement the
     * calendar control.
     **/
    private static class FocusablePanel extends JxPanel
    {
	/**
	 * Constructs a new <code>FocusablePanel</code> with the given
	 * layout manager.
	 *
	 * @param layout layout manager
	 **/
	public FocusablePanel( LayoutManager layout ) {
	    super( layout );
	}


	/**
	 * Always returns <code>true</code>, since
	 * <code>FocusablePanel</code> can receive the focus.
	 *
	 * @return <code>true</code>
	 **/
	public boolean isFocusable() {
	    return true;
	}
    }



    /**
     * Initializes this <code>PanelDateChooser</code> object. Creates the
     * controls, registers listeners and initializes the dialog box.
     **/
    private void construct()
    {
	calendar = new GregorianCalendar();

	month = new JComboBox(MONTHS);
	month.addItemListener( this );

	cmbYear = new JComboBox();
	for ( int i=FIRST_YEAR; i<=LAST_YEAR; i++ )
	    cmbYear.addItem( Integer.toString(i) );
	cmbYear.addItemListener( this );

	days = new JLabel[7][7];
	for ( int i=0; i<7; i++ ) {
	    days[0][i] = new JLabel(DAYS[i],JLabel.RIGHT);
	    days[0][i].setForeground( WEEK_DAYS_FOREGROUND );
	}
	for ( int i=1; i<7; i++ )
	    for ( int j=0; j<7; j++ )
		{
		    days[i][j] = new JLabel(" ",JLabel.RIGHT);
		    days[i][j].setForeground( DAYS_FOREGROUND );
		    days[i][j].setBackground( SELECTED_DAY_BACKGROUND );
		    days[i][j].setBorder( EMPTY_BORDER );
		    days[i][j].addMouseListener( this );
		}

//	ok = new JButton("οκ");
//	ok.addActionListener( this );
//	cancel = new JButton("άκυρο");
//	cancel.addActionListener( this );

	JPanel monthYear = new JPanel();
	monthYear.add( month );
	monthYear.add( cmbYear );

	daysGrid = new FocusablePanel(new GridLayout(7,7,5,0));
	daysGrid.addFocusListener( this );
	daysGrid.addKeyListener( this );
	for ( int i=0; i<7; i++ )
	    for ( int j=0; j<7; j++ )
		daysGrid.add( days[i][j] );
	daysGrid.setBackground( Color.white );
	//daysGrid.setBorder( BorderFactory.createLoweredBevelBorder() );
	JPanel daysPanel = new JPanel();
	daysPanel.add( daysGrid );

//	JPanel buttons = new JPanel();
//	buttons.add( ok );
//	buttons.add( cancel );

	//Container dialog = getContentPane();
	this.setLayout(new BorderLayout());
	this.add(  monthYear, BorderLayout.PAGE_START );
	this.add( daysPanel, BorderLayout.CENTER );
//	this.add( buttons,  BorderLayout.PAGE_END );

	//pack();
	//setResizable( false );
    }



    /**
     * Gets the selected day, as an <code>int</code>. Parses the text
     * of the selected label in the calendar to get the day.
     *
     * @return the selected day or -1 if there is no day selected
     **/
    private int getSelectedDay()
    {
	if ( day == null )
	    return -1 ;
	try {
	    return Integer.parseInt(day.getText());
	} catch ( NumberFormatException e ) {
	}
	return -1;
    }



    /**
     * Sets the selected day. The day is specified as the label
     * control, in the calendar, corresponding to the day to select.
     *
     * @param newDay day to select
     **/
    private void setSelected( JLabel newDay )
    {
    //System.out.println("PanelDateChooser.setSelected");
	if ( day != null )
	{
	    day.setForeground( DAYS_FOREGROUND );
	    day.setOpaque( false );
	    day.setBorder( EMPTY_BORDER );
	}
	day = newDay;
	day.setForeground( SELECTED_DAY_FOREGROUND );
	day.setOpaque( true );
	if ( daysGrid.hasFocus() )
	    day.setBorder( FOCUSED_BORDER );
    }



    /**
     * Sets the selected day. The day is specified as the number of
     * the day, in the month, to selected. The function compute the
     * corresponding control to select.
     *
     * @param newDay day to select
     **/
    private void setSelected( int newDay )
    {
	//System.out.println("PanelDateChooser.setSelected '"+newDay+"'");
	setSelected( days[(newDay+offset-1)/7+1][(newDay+offset-1)%7] );
    }



    /**
     * Updates the calendar. This function updates the calendar panel
     * to reflect the month and cmbYear selected. It keeps the same day
     * of the month that was selected, except if it is beyond the last
     * day of the month. In this case, the last day of the month is
     * selected.
     **/
    private void update()
    {
    //System.out.println("PanelDateChooser.update");
	int iday = getSelectedDay();
	for ( int i=0; i<7; i++ ) {
	    days[1][i].setText( " " );
	    days[5][i].setText( " " );
	    days[6][i].setText( " " );
	}
	calendar.set( Calendar.DATE, 1 );
	calendar.set( Calendar.MONTH, month.getSelectedIndex()+Calendar.JANUARY );
	calendar.set( Calendar.YEAR, cmbYear.getSelectedIndex()+FIRST_YEAR );

	offset = calendar.get(Calendar.DAY_OF_WEEK)-Calendar.SUNDAY;
	lastDay = calendar.getActualMaximum(Calendar.DATE);
	for ( int i=0; i<lastDay; i++ )
	    days[(i+offset)/7+1][(i+offset)%7].setText( String.valueOf(i+1) );
	if ( iday != -1 ) {
	    if ( iday > lastDay )
		iday = lastDay;
	    setSelected( iday );
	}
    }



    /**
     * Called when the "Ok" button is pressed. Just sets a flag and
     * hides the dialog box.
     **/
    public void actionPerformed( ActionEvent e )
    {
//	if ( e.getSource() == ok )
//	    okClicked = true;
//	setVisible(false);
    }

    /**
     * Called when the calendar gains the focus. Just re-sets the
     * selected day so that it is redrawn with the border that
     * indicate focus.
     **/
    public void focusGained( FocusEvent e ) {
	setSelected( day );
    }

    /**
     * Called when the calendar loses the focus. Just re-sets the
     * selected day so that it is redrawn without the border that
     * indicate focus.
     **/
    public void focusLost( FocusEvent e ) {
	setSelected( day );
    }

    /**
     * Called when a new month or cmbYear is selected. Updates the calendar
     * to reflect the selection.
     **/
    public void itemStateChanged( ItemEvent e ) {
	update();
    }

    /**
     * Called when a key is pressed and the calendar has the
     * focus. Handles the arrow keys so that the user can select a day
     * using the keyboard.
     **/
    public void keyPressed( KeyEvent e ) {
	int iday = getSelectedDay();
	switch ( e.getKeyCode() ) {
	case KeyEvent.VK_LEFT:
	    if ( iday > 1 )
		setSelected( iday-1 );
	    break;
	case KeyEvent.VK_RIGHT:
	    if ( iday < lastDay )
		setSelected( iday+1 );
	    break;
	case KeyEvent.VK_UP:
	    if ( iday > 7 )
		setSelected( iday-7 );
	    break;
	case KeyEvent.VK_DOWN:
	    if ( iday <= lastDay-7 )
		setSelected( iday+7 );
	    break;
	}
    }

    /**
     * Called when the mouse is clicked on a day in the
     * calendar. Selects the clicked day.
     **/
    public void mouseClicked( MouseEvent e )
    {
    //System.out.println("PanelDateChooser.mouseClicked");
	JLabel day = (JLabel)e.getSource();
	if ( !day.getText().equals(" ") )
	    setSelected( day );
	daysGrid.requestFocus();
    }

    public void keyReleased( KeyEvent e ) {}
    public void keyTyped( KeyEvent e ) {}
    public void mouseEntered( MouseEvent e ) {}
    public void mouseExited( MouseEvent e) {}
    public void mousePressed( MouseEvent e ) {}
    public void mouseReleased( MouseEvent e) {}



    /**
     * Constructs a new <code>PanelDateChooser</code> with the given title.
     *
     * @param owner owner dialog
     *
     * @param title dialog title
     **/
    public PanelDateChooser()//Dialog owner, String title )
    {
	//super( owner, title, true );
	construct();
    }



    /**
     * Constructs a new <code>PanelDateChooser</code>.
     *
     * @param owner owner dialog
     **/
    public PanelDateChooser( JDialog owner )
    {
	//super( owner, true );
	construct();
    }



    /**
     * Constructs a new <code>PanelDateChooser</code> with the given title.
     *
     * @param owner owner frame
     *
     * @param title dialog title
     **/
    public PanelDateChooser( JFrame owner, String title )
    {
	//super( owner, title, true );
	construct();
    }



    /**
     * Constructs a new <code>PanelDateChooser</code>.
     *
     * @param owner owner frame
     **/
    public PanelDateChooser( JFrame owner )
    {
	//super( owner, true );
	construct();
    }

    public void setYearEnforce( String yearEnforce )
    {
	//super( owner, true );
	
	   if(yearEnforce!= null && !yearEnforce.equalsIgnoreCase(""))
	   {
	   	//System.out.println("PanelDateChooser.setYearEnforce yearEnforce "+yearEnforce);
	    cmbYear.setSelectedItem(yearEnforce);
	    //cmbYear.setEnabled(false);
	   }
    }

    /**
     * Selects a date. Displays the dialog box, with a given date as
     * the selected date, and allows the user select a new date.
     *
     * @param date initial date
     *
     * @return the new date selected or <code>null</code> if the user
     * press "Cancel" or closes the dialog box
     **/
    public Date select( Date date )
    {
    //System.out.println("PanelDateChooser.select");
	calendar.setTime( date );
	int _day = calendar.get(Calendar.DATE);
	int _month = calendar.get(Calendar.MONTH);
	int _cmbYear = calendar.get(Calendar.YEAR);

	cmbYear.setSelectedIndex( _cmbYear-FIRST_YEAR );
	month.setSelectedIndex( _month-Calendar.JANUARY );
	setSelected( _day );
	okClicked = false;
	setVisible(true);
	if ( !okClicked )
	    return null;
	calendar.set( Calendar.DATE, getSelectedDay() );
	calendar.set( Calendar.MONTH, month.getSelectedIndex()+Calendar.JANUARY );
	calendar.set( Calendar.YEAR, cmbYear.getSelectedIndex()+FIRST_YEAR );
	return calendar.getTime();
    }
    
    // gets date as string and returns date as a string
    public void select(String date, DateFormat  df)
    {
    	dateFormat = df;
    	//DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date dt = new Date();
    	try
    	{
    		//System.out.println("PanelDateChooser.select date:"+date);
    		
    	  if( date == null || date.toString().equalsIgnoreCase("") )
    	  {
    	  	     		//System.out.println("PanelDateChooser.select date:"+date);
    	  }
    	  else
    	  {
             dt = dateFormat.parse(date);// parse date fromstring to Date
          }
    	  
    	}
    	catch(ParseException pe)
    	{
    		System.out.println("PanelDateChooser.select "+pe+" date:"+date);
    	}
	    
	    calendar.setTime( dt );
	    int _day = calendar.get(Calendar.DATE);
	    int _month = calendar.get(Calendar.MONTH);
	    int _cmbYear = calendar.get(Calendar.YEAR);    	
	    cmbYear.setSelectedIndex( _cmbYear-FIRST_YEAR );
	    month.setSelectedIndex( _month-Calendar.JANUARY );
	    setSelected( _day );
	    //okClicked = false;
	    
	     //System.out.println("PanelDateChooser.select "+getSelectedDay()+ "--");
	    
	    //show();
	   // if ( !okClicked )
	   //     return null;
	   // calendar.set( Calendar.DATE, getSelectedDay() );
	   // calendar.set( Calendar.MONTH, month.getSelectedIndex()+Calendar.JANUARY );
	   // calendar.set( Calendar.YEAR, cmbYear.getSelectedIndex()+FIRST_YEAR );
	    
	   // System.out.println("PanelDateChooser.select "+getSelectedDay()+ "--" +dateFormat.format(calendar.getTime()));
	    
	   // return dateFormat.format(calendar.getTime());    	
    }

   public String getDate()
   {
   	  
  	  calendar.set( Calendar.DATE, getSelectedDay() );
	  calendar.set( Calendar.MONTH, month.getSelectedIndex()+Calendar.JANUARY );
	  calendar.set( Calendar.YEAR, cmbYear.getSelectedIndex()+FIRST_YEAR );
	  
	  
   	 // System.out.println("PanelDateChooser.getDate "+dateFormat.format(calendar.getTime()));
   	  return dateFormat.format(calendar.getTime());
   }

    /**
     * Selects new date. Just calls {@link #select(Date)} with the
     * system date as the parameter.
     *
     * @return the same as the function {@link #select(Date)}
     **/
    public Date select()
    {
	return select(new Date());
    }
}

