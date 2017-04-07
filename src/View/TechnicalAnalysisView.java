package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 * @author Gurpreet
 */

// http://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html
public class TechnicalAnalysisView extends JFrame {

    private static final long serialVersionUID = 494000442742952620L;

   private Dimension getScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
   private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
    // Set up the menus
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuExit;

    private JPanel stockPanel;

    private JPanel stockOptionsPanel;
    private JComboBox<String> stocklist;
    private JLabel from;
    //    private JTextField fromField;
    private JSpinner fromDate;
    private JLabel to;
    //    private JTextField toField;
    private JSpinner toDate;
    private JButton updateBtn;
    private JButton addToWishBtn;

    private JPanel wChartPanel;
    private ChartPanel chartPanel;

    private JPanel analysisPanel;
    private JLabel analysisLabel;
    private JLabel resultLabel;
    private JCheckBox shortChBox;
    private JCheckBox midCBhox;
    private JCheckBox longChBox;

    private JPanel watchListPanel;
    private JLabel watchListPlaceholder;
    private JPanel tempJPanel;
    
    private JPanel watchlistOptionPanel;
    private JButton delStock;

    //Todo Cite the icons.
    //License: Linkware (Backlink to http://www.visualpharm.com required)
    //Commercial usage: Allowed (Backlink to http://www.visualpharm.com required)

    private ImageIcon upImage;
    private String upImagePath = "resources/Stock-Index-Up-icon_16.png";
    private ImageIcon downImage;
    private String downImagePath = "resources/Stock-Index-Down-icon_16.png";
//    private LinkedList<JPanel> watchListEntities;

    private JPanel controlsPanel;
    private JLabel messageLabel;
    private JButton closeBtn;


    private JButton nextBtn;
    private JButton BackBtn;
    private JButton addStockBtn;

    public TechnicalAnalysisView(String[] stockList) {
        initComponents(stockList);
    }

    private void initComponents(String[] listString) {

        // Declare Views

        // Set up the menus
        menuBar = new JMenuBar();

        //Create the menubar
        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        menuExit = new JMenuItem("Exit");
        menuFile.add(menuExit);
        this.setJMenuBar(menuBar);
        // build the menu

        // Stock Panel
        stockPanel = new JPanel();
        stockPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        // Stock Panel : Stock, date and update botton.
        stockOptionsPanel = new JPanel();
        stockOptionsPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        stocklist = new JComboBox<String>(listString);
        from = new JLabel("From:");

        Calendar calendar = new GregorianCalendar();
        Date toStockDate = calendar.getTime();

        calendar.add(Calendar.YEAR, -1);
        Date fromStockDate = calendar.getTime();

        //Todo change to dates http://docs.oracle.com/javase/tutorial/uiswing/components/spinner.html
        //Todo: change earliestDate to first date of stock history
        calendar.add(Calendar.YEAR, -10);
        Date earliestStockDate = calendar.getTime();


        SpinnerDateModel toSpinnerDateModel = new SpinnerDateModel(
                toStockDate, //current
                earliestStockDate, //first
                toStockDate, //last
                Calendar.MONTH);

        SpinnerDateModel fromSpinnerDateModel = new SpinnerDateModel(
                fromStockDate,
                earliestStockDate,
                toStockDate,
                Calendar.MONTH);


        fromDate = new JSpinner();
        fromDate.setModel(fromSpinnerDateModel);

        to = new JLabel("To:");
        toDate = new JSpinner();
        toDate.setModel(toSpinnerDateModel);

        updateBtn = new JButton();
        updateBtn.setText("Update");
        updateBtn.setName("Update");
        
        addToWishBtn = new JButton("Add to Wishlist");
        addToWishBtn.setName("Add to Wishlist");

        // Stock Panel : Stock chart.
        wChartPanel = new JPanel();
        wChartPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        wChartPanel.setName("wChartPanel");
        wChartPanel.validate();
        wChartPanel.setSize(getScreenSize);;

        // Stock Panel : Analysis panel, Recommendation label, strategy checkboxes.
        analysisPanel = new JPanel();
        analysisPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        resultLabel = new JLabel("Results will be sown here");
        resultLabel.setName("AnalsysResult");

        analysisLabel = new JLabel("Analisis Lable placeholder");
        analysisLabel.setText("Analysis");
        analysisLabel.setName("analysisLabel");


        shortChBox = new JCheckBox("Short-term");
        shortChBox.setName("shortChBox");

        midCBhox = new JCheckBox("Medium-term");
        midCBhox.setName("midCBhox");

        longChBox = new JCheckBox("Long-term");
        longChBox.setName("longChBox");

        // Watch list Panel.
        watchListPanel = new JPanel();
        watchListPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        watchListPlaceholder = new JLabel("Watch List ");

        // Control Panel.
        controlsPanel = new JPanel();
        controlsPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        messageLabel = new JLabel();

        closeBtn = new JButton();
        closeBtn.setText("Close");
        closeBtn.setName("Back");
        closeBtn.addActionListener(e -> System.exit(0));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //other
        nextBtn = new JButton();
        nextBtn.setText("Next");

        addStockBtn = new JButton();
        addStockBtn.setText("Add Stock");

        BackBtn = new JButton();
        BackBtn.setText("Back");
        
        watchlistOptionPanel = new JPanel();
        watchlistOptionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        delStock = new JButton("Remove selected");

//Build view https://examples.javacodegeeks.com/desktop-java/swing/java-swing-layout-example/


        //Setting Panels

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        //https://docs.oracle.com/javase/7/docs/api/java/awt/GridBagConstraints.html
        gbc.fill = GridBagConstraints.BOTH;
        this.getContentPane().setLayout(layout);

        stockOptionsPanel.add(stocklist);
        stockOptionsPanel.add(from);
        stockOptionsPanel.add(fromDate);
        stockOptionsPanel.add(to);
        stockOptionsPanel.add(toDate);
        stockOptionsPanel.add(updateBtn);
        stockOptionsPanel.add(addToWishBtn);
        stockOptionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        this.add(stockOptionsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(wChartPanel, gbc);


        watchListPanel.add(watchListPlaceholder);
        loadArrowsImages();
        watchListPanel.setLayout(new BoxLayout(watchListPanel, BoxLayout.Y_AXIS));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 7;
        gbc.weightx = 0.3;
        gbc.weighty = 0.0;
        this.add(watchListPanel, gbc);

        analysisPanel.add(analysisLabel);
        analysisPanel.add(shortChBox);
        analysisPanel.add(midCBhox);
        analysisPanel.add(longChBox);
        analysisPanel.add(messageLabel);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        this.add(analysisPanel, gbc);
        
        watchlistOptionPanel.add(delStock);
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.3;
        gbc.weighty = 0.0;
        this.add(watchlistOptionPanel, gbc);
        

//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //Listeners
    public void addUdateBtnListner(ActionListener actionListener) {
    	updateBtn.addActionListener(actionListener);
    }
    public void addToWishBtnListener(ActionListener actionListener){
    	addToWishBtn.addActionListener(actionListener);
    }

    public void addShortCheckboxListner(ActionListener actionListener) {
        shortChBox.addActionListener(actionListener);
    }
    public void addMidCheckboxListner(ActionListener actionListener) {
        midCBhox.addActionListener(actionListener);
    }
    public void addLongCheckboxListner(ActionListener actionListener) {
        longChBox.addActionListener(actionListener);
    }

    public void addMenuExitListner(ActionListener actionListener) {
        menuExit.addActionListener(actionListener);
    }
    
    public void delStockBtnListner(ActionListener actionListener) {
       delStock.addActionListener(actionListener);
    }

    //Setters and Getters
    public void resetChkboxes(){
    	shortChBox.setSelected(false);
    	midCBhox.setSelected(false);
        longChBox.setSelected(false);
    }
    
    public void setLabelText(String text) {
        messageLabel.setText(text);
    }

    public Date[] getDates() {
        return new Date[]{(Date) fromDate.getValue(), (Date) toDate.getValue()};
    }

    public String getStockSelected() {
        return (String) stocklist.getSelectedItem();
    }
    
    public String[] getSelectedCheckboxes(){
    	ArrayList<String> remstocks= new ArrayList<String>();
    	for(JCheckBox c: checkBoxes){
    		if(c.isSelected()){
    			remstocks.add(c.getText());
    		}
    	}
    	
    	return remstocks.toArray(new String[remstocks.size()]);
    }

    private void loadArrowsImages() {
        upImage = new ImageIcon(upImagePath);
        downImage = new ImageIcon(downImagePath);
    }
    

    public void setChart(JFreeChart chart) {
    	chartPanel = new ChartPanel(chart);
        chartPanel.setSize(getScreenSize);
        chartPanel.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent e) {
        	//chartPanel.updateUI();
        	}
		});
        
        if(wChartPanel.getComponentCount() != 0){
    		wChartPanel.removeAll();
    	}
        
        chartPanel.updateUI();
        wChartPanel.add(chartPanel);
        
        
        wChartPanel.revalidate();
        wChartPanel.repaint();
        wChartPanel.updateUI();
    }
    
    public void setWatchlist(String[][] watchListEntities){
    	if(watchListPanel.getComponentCount()!=0){
    		watchListPanel.removeAll();
    		this.checkBoxes.clear();
    	}
    	for (String[] str : watchListEntities) {
            tempJPanel = new JPanel();
            tempJPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            if (str[1].equals("buy")) {
                tempJPanel.add(new JLabel(upImage));
                //System.out.println("add buy signal");
            } else if(str[1].equals("sell")){
                tempJPanel.add(new JLabel(downImage));
            }
            else{
            	tempJPanel.add(new JLabel("     "));
            }
            JCheckBox cb = new JCheckBox(str[0]);
            cb.setHorizontalTextPosition(SwingConstants.LEFT);
            tempJPanel.add(cb);
            this.checkBoxes.add(cb);
            watchListPanel.add(tempJPanel);
        }
    	watchListPanel.revalidate();
    	watchListPanel.repaint();
    	watchListPanel.updateUI();
    }
    
    public static void main(String[] arg){
    	String[] stockList = {"Google","Apple","Intel"};
    	String[][] watchlist = {{"Google","buy"},{"Apple","sell"},{"Intel","buy"}};
    	TechnicalAnalysisView tv = new TechnicalAnalysisView(stockList);
    	tv.setWatchlist(watchlist);
    	tv.setVisible(true);
    	ActionListener delStockBtnListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] boxes = tv.getSelectedCheckboxes();
				for(String s:boxes){
					System.out.println(s);
				}
			}
		};
		tv.delStockBtnListner(delStockBtnListener);
    }
}
