package Wrap;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.temporal.ChronoUnit;

import javax.swing.*;

import static Wrap.Constants.*;

public class App implements ActionListener {
	
	// The frame and panel of the program
	private JFrame frame;
	private JPanel panel;
	
	//The beginning day/month/year/hour/minute/second
	private JTextField bYear;
	private JTextField bMonth;
	private JTextField bDay;
	private JTextField bHour;
	private JTextField bMin;
	private JTextField bSec;
	private JTextField[] bDate = {bYear, bMonth, bDay, bHour, bMin, bSec};
	
	//The end day/month/year/hour/minute/second
	private JTextField eYear;
	private JTextField eMonth;
	private JTextField eDay;
	private JTextField eHour;
	private JTextField eMin;
	private JTextField eSec;
	private JTextField[] eDate = {eYear, eMonth, eDay, eHour, eMin, eSec};
	
	//The "Current Time" button and the "Calculate!" button.
	private JButton bNow;
	private JButton eNow;
	private JButton calculate;
	
	// Start and End date
	private LocalDateTime start;
	private LocalDateTime end;
	
	// Check boxes for your specific wants
	private JCheckBox cYear = new JCheckBox("Years");
	private JCheckBox cMonth = new JCheckBox("Months");
	private JCheckBox cDay = new JCheckBox("Days");
	private JCheckBox cHour = new JCheckBox("Hours");
	private JCheckBox cMin = new JCheckBox("Minutes");;
	private JCheckBox cSec = new JCheckBox("Seconds");
	private JCheckBox[] cDate = {cYear, cMonth, cDay, cHour, cMin, cSec};
	
	//The Result of the Calculation
	private JLabel result;
	private long rYear;
	private long rMonth;
	private long rDay;
	private long rHour;
	private long rMin;
	private long rSec;
	private long[] rDate = {rYear, rMonth, rDay, rHour, rMin, rSec};
	
	public App() {
		displaySoup();
		checkBoxSoup();
		for (JTextField i: bDate){
			i.setText("0");
		}
		for (JTextField i: eDate){
			i.setText("0");
		}
	}
	
	/*
	 * Gets all the Check boxes to work
	 */
	public void checkBoxSoup(){
		cDate[0].setSelected(true);
		cDate[0].setName("cYear");
		cDate[0].addActionListener(this);
		
		cDate[1].setSelected(true);
		cDate[1].setName("cMonth");
		cDate[1].addActionListener(this);
		
		cDate[2].setSelected(true);
		cDate[2].setName("cDay");
		cDate[2].addActionListener(this);
		
		cDate[3].setSelected(true);
		cDate[3].setName("cHour");
		cDate[3].addActionListener(this);
		
		cDate[4].setSelected(true);
		cDate[4].setName("cMin");
		cDate[4].addActionListener(this);
		
		cDate[5].setSelected(true);
		cDate[5].setName("cSec");
		cDate[5].addActionListener(this);
	}
	
	/*
	 * Mixes all the display elements together in a well organized format
	 */
	public JFrame displaySoup(){
		frame = new JFrame();
		frame.setContentPane(view());
		frame.setTitle("Time Calculator");
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setVisible(true);
		frame.pack();
		// Exits on close
		frame.setDefaultCloseOperation(3);
		return frame;
	}
	
	/*
	 * Packs the JPanel together with all it's components
	 */
	public JPanel view(){
		// The main pannel and it's layout
		panel = new JPanel();
		BoxLayout box = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(box);
		
		JLabel explain = new JLabel("Insert the start and end dates here");
		JLabel explain2 = new JLabel("The fields to enter are Day, Month, Year, Hour, Minute, Secound."); 
		panel.add(explain);
		panel.add(explain2);
		
		// The adding of the date boxes and the "Current Time" Buttons.
		panel.add(bDate());
		panel.add(eDate());
		
		//Adds the check boxes for the days
		panel.add(timeDivide());
		
		// Adds a label to show calculate button and the resulting text
		calculate = new JButton("Calculate!");
		calculate.addActionListener(this);
		calculate.setName("calculate");
		panel.add(calculate);
		panel.add(resulter());
		
		// Wrap it all together and spit it back out
		panel.setVisible(true);
		return panel;
	}
	
	/*
	 * The Beginning date input. 
	 */
	public JPanel bDate(){
		JPanel date = new JPanel();
		date.add(new JLabel("Enter the Start Date:"));
		
		/* 
		 * Adds the text fields of Day, Month, and Year divided by a slash
		 * The slashes and colons are commented out because there is a bug 
		 * I don't want to bother trying to fix quite yet.
		 */
		bDate[2] = new JTextField(2);
		date.add(bDate[2]);
		bDate[1] = new JTextField(2);
		date.add(bDate[1]);
		bDate[0] = new JTextField(3);
		date.add(bDate[0]);
		
		// A break inbetween the two
		date.add(BREAK);
		
		// Adds the text fields of Hour, Second, and Minute divided by a colon
		bDate[3] = new JTextField(2);
		date.add(bDate[3]);
		bDate[4] = new JTextField(2);
		date.add(bDate[4]);
		bDate[5] = new JTextField(2);
		date.add(bDate[5]);
		
		// Adds the Current Time Button
		bNow = new JButton("Current Time");
		bNow.addActionListener(this);
		bNow.setName("bNow");
		date.add(bNow);
		
		return date;
	}
	
	/*
	 * The End Date input
	 */
	public JPanel eDate(){
		JPanel date = new JPanel();
		date.add(new JLabel("Enter the End Date:"));
		
		/* 
		 * Adds the text fields of Day, Month, and Year divided by a slash
		 * Again, the slashes and colons are commented out because there is 
		 * a bug I don't want to bother trying to fix quite yet.
		 */
		eDate[2] = new JTextField(2);
		date.add(eDate[2]);
		eDate[1] = new JTextField(2);
		date.add(eDate[1]);
		eDate[0] = new JTextField(3);
		date.add(eDate[0]);
		
		// A break inbetween the two
		date.add(BREAK);
		
		// Adds the text fields of Hour, Second, and Minute divided by a colon
		eDate[3] = new JTextField(2);
		date.add(eDate[3]);
		eDate[4] = new JTextField(2);
		date.add(eDate[4]);
		eDate[5] = new JTextField(2);
		date.add(eDate[5]);
		
		// Adds the Current Time Button
		eNow = new JButton("Current Time");
		eNow.addActionListener(this);
		eNow.setName("eNow");
		date.add(eNow);
		
		return date;
	}
	
	//adds Checkmarks to the display
	public JPanel timeDivide(){
		JPanel check = new JPanel();
		check.add(cDate[0]);
		check.add(cDate[1]);
		check.add(cDate[2]);
		check.add(cDate[3]);
		check.add(cDate[4]);
		check.add(cDate[5]);
		return check;
	}
	
	// Mashes together the results
	public JPanel resulter(){
		JPanel tag = new JPanel();
		JLabel resultLabel = new JLabel("Resulting Time:  ");
		result = new JLabel("");
		tag.add(resultLabel);
		tag.add(result);
		return tag;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = ((Component) e.getSource()).getName();
		String text = "";
		// Sets the First set of boxes to the current date
		if (name.equals("bNow")){
			bDate[2].setText("" + LocalDateTime.now().getDayOfMonth());
			bDate[1].setText("" + LocalDateTime.now().getMonthValue());
			bDate[0].setText("" + LocalDateTime.now().getYear());
			bDate[3].setText("" + LocalDateTime.now().getHour());
			bDate[4].setText("" + LocalDateTime.now().getMinute());
			bDate[5].setText("" + LocalDateTime.now().getSecond());
		}
		// Sets the Second set of boxes to the current date.
		else if (name.equals("eNow")){
			eDate[2].setText("" + LocalDateTime.now().getDayOfMonth());
			eDate[1].setText("" + LocalDateTime.now().getMonthValue());
			eDate[0].setText("" + LocalDateTime.now().getYear());
			eDate[3].setText("" + LocalDateTime.now().getHour());
			eDate[4].setText("" + LocalDateTime.now().getMinute());
			eDate[5].setText("" + LocalDateTime.now().getSecond());
		}
		try{
			// Start Date
			start = LocalDateTime.of(Integer.parseInt(bDate[0].getText()),
					Integer.parseInt(bDate[1].getText()), Integer.parseInt(bDate[2].getText()), 
					Integer.parseInt(bDate[3].getText()), Integer.parseInt(bDate[4].getText()), 
					Integer.parseInt(bDate[5].getText()));
			// End Date
			end = LocalDateTime.of(Integer.parseInt(eDate[0].getText()),
					Integer.parseInt(eDate[1].getText()), Integer.parseInt(eDate[2].getText()), 
					Integer.parseInt(eDate[3].getText()), 
					Integer.parseInt(eDate[4].getText()), 
					Integer.parseInt(eDate[5].getText()));
			
			rDate[0] = start.until(end, ChronoUnit.YEARS);
			rDate[1] = start.until(end, ChronoUnit.MONTHS);
			rDate[2] = start.until(end, ChronoUnit.DAYS);
			rDate[3] = start.until(end, ChronoUnit.HOURS);
			rDate[4] = start.until(end, ChronoUnit.MINUTES);
			rDate[5] = start.until(end, ChronoUnit.SECONDS);
		}
		catch (Exception r){
		}
		
		/* Test
		for (long i: rDate){
			System.out.println(i);
		}
		System.out.println();
		//jacobs thoughts
		
		if (cDate[0].isSelected()){
			int time_year=31536000;
		//	int years = total/time_year;
			total = total%time_year;
		}
		if (cDate[1].isSelected()){
		int time_month = 2678400;
		// int month = total / time_month;
		total = total% time_month;
		}
		*/
		
		//end jacobs thoughts
		
		/*
		 * Use a different math if it isn't "checked"
		 * 
		 * use Gragorian chalendar object.
		 */
		
		// Checks which boxes are marked
		if (cDate[0].isSelected()){
			text += rDate[0] + (rDate[0] == 1? " Year  ": " Years  ");
		}
		if (cDate[1].isSelected()){
			text += Math.abs((rDate[1] % 12)) + ((rDate[1] % 12) == 1? " Month  ": " Months  ");
		}
		if (cDate[2].isSelected()){
			text += Math.abs((rDate[2] % 30)) + ((rDate[2] % 30) == 1? " Day ":" Days  ");
		}
		if (cDate[3].isSelected()){
			text += Math.abs((rDate[3] % 24)) + ((rDate[3] % 24) == 1? " Hour  ":" Hours  ");
		}
		if (cDate[4].isSelected()){
			text += Math.abs((rDate[4] % 60)) + ((rDate[4] % 60) == 1? " Minute  ": " Mintues  ");
		}
		if (cDate[5].isSelected()){
			text += Math.abs((rDate[5] % 60)) + ((rDate[5] % 60) == 1? " Second":" Seconds");
		}
		 // Calculates the time between the two boxes.
		if (name.equals("calculate")){
			try{
				// What this whole thing spits out
				result.setText(text);
				text = "";
			}
			// A catch in case an invalid input it entered
			catch (Exception r){
				result.setText("Not a vaild input.");
			}
		}
	}
	
}
