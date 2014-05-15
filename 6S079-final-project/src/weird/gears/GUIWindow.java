package weird.gears;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.geom.CubicCurve2D;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIWindow extends JPanel {
    // necessary to avoid an error
    private static final long serialVersionUID = 1L;
    
    private GearSet model;
    private int gearSetType;
    private final JScrollPane scrollPane;
    private final JButton userInputButton;
    private final JLabel weirdGearsLabel;
    private final JTextArea weirdGearsInfo;
    private final JSlider radSlider;
    private final JLabel radSliderText;
    private final JSlider toothAngSlider;
    private final JLabel toothAngSliderText;
    private final JLabel toothAngSliderInfo;
    private final JSlider toothLenSlider;
    private final JLabel toothLenSliderText;
    private final JSlider xPosSlider;
    private final JLabel xPosSliderLabel;
    private final JSlider yPosSlider;
    private final JLabel yPosSliderLabel;
    private final JSlider rotationSlider;
    private final JLabel rotationSliderLabel;
    //private final JLabel gearLabel;
    private final JPanel sliderPanel;
    private final JPanel gearPanel;
    private final RenderPanel renderingPanel;
    private final JTextField userInputRad;
    private final JTextField userInputAng;
    private final JTextField userInputLen;
    private final JTextField userInputX;
    private final JTextField userInputY;
    private final JLabel userInputRadLabel;
    private final JLabel userInputAngLabel;
    private final JLabel userInputLenLabel;
    private final JLabel userInputXLabel;
    private final JLabel userInputYLabel;
    private final JPanel userInput;
    private final JButton fitToGear;
    private final JButton addGearButton;
    
    static final int ANG_MIN = 2;
    static final int ANG_MAX = 90;
    static final int ANG_SPACING = 8;
    static final int RAD_MIN = 1;
    static final int RAD_MAX = 50;
    static final int RAD_SPACING = 5;
    static final int LEN_MIN = 1;
    static final int LEN_MAX = 15;
    static final int LEN_SPACING = 1;
    static final int X_MIN = 0;
    static final int X_MAX = 200;
    static final int X_SPACING = 20;
    static final int Y_MIN = 0;
    static final int Y_MAX = 200;
    static final int Y_SPACING = 20;
    static final int ROT_MIN = 0;
    static final int ROT_MAX = 360;
    static final int ROT_SPACING = 30;

    public GUIWindow() {
        // sets up the model
        gearSetType = 1;
        model = new GearSet(1);
        
        // create the components
        
        // create scroll pane
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // create user input section
        userInput = new JPanel();
        userInput.setLayout(new FlowLayout());
        userInput.setPreferredSize(new Dimension(400,40));
        userInput.setMaximumSize(new Dimension(400,40));
        // things that go in the user input section
        userInputRadLabel = new JLabel("Radius:");
        userInputRad = new JTextField("10.0");
        userInputAngLabel = new JLabel("Angle:");
        userInputAng = new JTextField("30.0");
        userInputLenLabel = new JLabel("Tooth Length:");
        userInputLen = new JTextField("3.0");
        userInputXLabel = new JLabel("X:");
        userInputX = new JTextField("30.0");
        userInputYLabel = new JLabel("Y:");
        userInputY = new JTextField("40.0");
        // adding things to user input section
        userInput.add(userInputRadLabel);
        userInput.add(userInputRad);
        userInput.add(userInputAngLabel);
        userInput.add(userInputAng);
        userInput.add(userInputLenLabel);
        userInput.add(userInputLen);
        userInput.add(userInputXLabel);
        userInput.add(userInputX);
        userInput.add(userInputYLabel);
        userInput.add(userInputY);
        userInput.setVisible(false);
        
        // user input button
        userInputButton = new JButton("Click here to enter parameters for the fixed gear");
        userInputButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set dialogue visible
                if(userInput.isVisible()) {
                    userInput.setVisible(false);
                    double rad = Double.parseDouble(userInputRad.getText());
                    double ang = Double.parseDouble(userInputAng.getText());
                    ang = ang*Math.PI/180.0;
                    double len = Double.parseDouble(userInputLen.getText());
                    double x = Double.parseDouble(userInputX.getText());
                    double y = Double.parseDouble(userInputY.getText());
                    model.getFixedGear().setOrigin(x, y);
                    double wid = rad*ang;
                    model.getFixedGear().getTooth().setAng(len, wid, ang);
                    model.getFixedGear().radiusScale(rad);
                    renderingPanel.repaint();
                    userInputButton.setText("Click here to enter parameters for the fixed gear");
                } else {
                    userInput.setVisible(true);
                    userInputButton.setText("Click here again to confirm parameters");
                }
            }
        });
        
        // explanation of program in right panel
        weirdGearsInfo = new JTextArea("Weird Gears is a program which will simulate sets of gears based "
                + "on parameters passed by you, the user. "
                + "The black gear is fixed by the parameters you gave. Press the button below"
                + " to change the fixed gear's parameters."
                + "\n\nFeel free to adjust the colorful gears with the corresponding sliders below. "
                + "Alternatively, press the 'Fit to Fixed Gear' button to fit the colorful gears to "
                + "the fixed gear.");
        weirdGearsInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        weirdGearsInfo.setEditable(false);
        weirdGearsInfo.setCursor(null);
        weirdGearsInfo.setOpaque(false);
        weirdGearsInfo.setFocusable(false);
        weirdGearsInfo.setWrapStyleWord(true);
        weirdGearsInfo.setLineWrap(true);
        weirdGearsInfo.setMaximumSize(new Dimension(370,130));
        
        weirdGearsLabel = new JLabel("Welcome to Weird Gears!");
        weirdGearsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        // fit unfixed gears to fixed gear button
        fitToGear = new JButton("Click here to fit the unfixed gear(s) to the fixed gear");
        fitToGear.setAlignmentX(Component.CENTER_ALIGNMENT);
        fitToGear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //fit the colored gear to the black gear
                double len = model.getFixedGear().getTooth().getLen();
                double ang = model.getFixedGear().getTooth().getAng();
                double wid = ang*model.getFixedGear().getRadius();
                model.getUnfixedGear().getTooth().setAng(len, wid, ang);
                model.getUnfixedGear().radiusScale(model.getUnfixedGear().getRadius());
                renderingPanel.repaint();
            }
        });
        
        // radius slider
        radSlider = new JSlider(RAD_MIN, RAD_MAX, (int) model.getUnfixedGear().getRadius());
        radSlider.setMajorTickSpacing(RAD_SPACING);
        radSlider.setPaintLabels(true);
        radSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        radSlider.setMaximumSize(new Dimension(370,30));
        
        radSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                double val = (double) radSlider.getValue();
                model.getUnfixedGear().radiusScale(val);
                renderingPanel.repaint();
            }
        });
        
        radSliderText = new JLabel("Slide to change the radius");
        radSliderText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // tooth angle slider
        int INITIAL_ANG = (int) (model.getFixedGear().getTooth().getAng()*180.0/Math.PI);
        toothAngSlider = new JSlider(ANG_MIN, ANG_MAX, INITIAL_ANG);
        toothAngSlider.setMajorTickSpacing(ANG_SPACING);
        toothAngSlider.setPaintLabels(true);
        toothAngSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        toothAngSlider.setMaximumSize(new Dimension(370,30));
        
        toothAngSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                double result = (double) toothAngSlider.getValue();
                double ang = result*Math.PI/180.0;
                double len = model.getUnfixedGear().getTooth().getLen();
                double rad = model.getUnfixedGear().getRadius();
                double wid = ang*rad;
                model.getUnfixedGear().getTooth().setAng(len, wid, ang);
                model.getUnfixedGear().radiusScale(rad);
                renderingPanel.repaint();
            }
        });
        
        toothAngSliderText = new JLabel("Slide to change the angle taken by each tooth (in degrees)");
        toothAngSliderText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        toothAngSliderInfo = new JLabel("The angle of a tooth is inversely proportional to the number of teeth");
        toothAngSliderInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        // tooth length slider
        toothLenSlider = new JSlider(LEN_MIN, LEN_MAX, (int) model.getUnfixedGear().getTooth().getLen());
        toothLenSlider.setMajorTickSpacing(LEN_SPACING);
        toothLenSlider.setPaintLabels(true);
        toothLenSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        toothLenSlider.setMaximumSize(new Dimension(370,30));
        
        toothLenSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                double val = (double) toothLenSlider.getValue();
                model.getUnfixedGear().getTooth().setLen(val);
                renderingPanel.repaint();
            }
        });
        
        toothLenSliderText = new JLabel("Slide to change the length of each tooth");
        toothLenSliderText.setAlignmentX(Component.CENTER_ALIGNMENT);

        // x slider
        xPosSlider = new JSlider(X_MIN, X_MAX, (int) model.getUnfixedGear().getX());
        xPosSlider.setMajorTickSpacing(X_SPACING);
        xPosSlider.setPaintLabels(true);
        xPosSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        xPosSlider.setMaximumSize(new Dimension(370,30));
        
        xPosSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                double val = (double) xPosSlider.getValue();
                model.getUnfixedGear().setOrigin(val, model.getUnfixedGear().getY());
                renderingPanel.repaint();
            }
        });
        
        xPosSliderLabel = new JLabel("Slide to change the X coordinate of the gear's center");
        xPosSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // y slider
        yPosSlider = new JSlider(Y_MIN, Y_MAX, (int) model.getUnfixedGear().getY());
        yPosSlider.setMajorTickSpacing(Y_SPACING);
        yPosSlider.setPaintLabels(true);
        yPosSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        yPosSlider.setMaximumSize(new Dimension(370,30));
        
        yPosSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                double val = (double) yPosSlider.getValue();
                model.getUnfixedGear().setOrigin(model.getUnfixedGear().getX(), val);
                renderingPanel.repaint();
            }
        });
        
        yPosSliderLabel = new JLabel("Slide to change the Y coordinate of the gear's center");
        yPosSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // rotation slider
        rotationSlider = new JSlider(ROT_MIN, ROT_MAX, 0);
        rotationSlider.setMajorTickSpacing(ROT_SPACING);
        rotationSlider.setPaintLabels(true);
        rotationSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        rotationSlider.setMaximumSize(new Dimension(370,30));
        
        rotationSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                double val = (double)rotationSlider.getValue();
                double result = val*Math.PI/180.0;
                renderingPanel.setRotation(result);
                renderingPanel.repaint();
            }
        });
        
        rotationSliderLabel = new JLabel("Slide to change the rotation of the gear");
        rotationSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        addGearButton = new JButton("Click here to add another gear");
        addGearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addGearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // add another gear
            }
        });
        
        //////////////////////////////////////////////////////////////////////////////
        // end of basic components                                                  //
        //                                                                          //
        // beginning of panels                                                      //
        //////////////////////////////////////////////////////////////////////////////
        
        
        
        // create the panels
        sliderPanel = new JPanel();
        gearPanel = new JPanel();
        renderingPanel = new RenderPanel(model);
        
        // lays out the main panel
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(1250,600));
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.white));
        
        // set up rendering panel 
        renderingPanel.setPreferredSize(new Dimension(500,400));
        renderingPanel.setOpaque(true);
        renderingPanel.setBackground(Color.white);
        //renderingPanel.setLayout(new GridLayout(200, 200, 1, 1));
        
        // set up the gear panel
        gearPanel.setLayout(new BoxLayout(gearPanel, BoxLayout.PAGE_AXIS));
        gearPanel.setPreferredSize(new Dimension(500,400));
        gearPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

        //gearPanel.add(gearLabel);
        gearPanel.add(renderingPanel);

        // set up the slider panel with scroller
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS));
        sliderPanel.setPreferredSize(new Dimension(400,1000));
        sliderPanel.setMaximumSize(new Dimension(400,2000));
        sliderPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.blue));
        scrollPane.setPreferredSize(new Dimension(450,400));
        scrollPane.setMaximumSize(new Dimension(450,800));
        
        // add things to the slider panel
        scrollPane.setViewportView(sliderPanel);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sliderPanel.add(weirdGearsLabel);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        sliderPanel.add(weirdGearsInfo);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sliderPanel.add(userInputButton);
        sliderPanel.add(userInput);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        sliderPanel.add(fitToGear);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sliderPanel.add(radSlider);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        sliderPanel.add(radSliderText);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sliderPanel.add(toothAngSlider);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        sliderPanel.add(toothAngSliderText);
        sliderPanel.add(toothAngSliderInfo);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sliderPanel.add(toothLenSlider);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        sliderPanel.add(toothLenSliderText);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sliderPanel.add(xPosSlider);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        sliderPanel.add(xPosSliderLabel);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sliderPanel.add(yPosSlider);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        sliderPanel.add(yPosSliderLabel);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sliderPanel.add(rotationSlider);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        sliderPanel.add(rotationSliderLabel);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // set up the root panel
        add(gearPanel);
        add(scrollPane);        
    }
}

// the panel on which things are drawn
class RenderPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    GearSet model;
    double scale;
    double currentRotation;
    
    public RenderPanel(GearSet mo) {
        model = mo;
        scale = 5.0;
        currentRotation = 0.0;
    }
    
    // helps draw the splines
    @Override
    public void paintComponent (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        g2.setBackground(Color.WHITE);
        g2.setColor(Color.BLACK);
        
        
        // draws the fixed gear
        double[][] points = model.getFixedGear().getTooth().getToothSpline().getPoints();
        double xOff = model.getFixedGear().getX();
        double yOff = model.getFixedGear().getY();
        double rad = model.getFixedGear().getRadius();
        double ang = model.getFixedGear().getTooth().getAng();
        int numT = model.getFixedGear().getNumTeeth();
        paintGear(g2, points, rad, ang, numT, xOff, yOff, false);
        
        
        // draws the unfixed gear
        g2.setColor(Color.BLUE);
        double[][] points2 = model.getUnfixedGear().getTooth().getToothSpline().getPoints();
        double xOff2 = model.getUnfixedGear().getX();
        double yOff2 = model.getUnfixedGear().getY();
        double rad2 = model.getUnfixedGear().getRadius();
        double ang2 = model.getUnfixedGear().getTooth().getAng();
        int numT2 = model.getUnfixedGear().getNumTeeth();
        //System.out.println("tooth width: " + model.getUnfixedGear().getTooth().getWidth());
        //System.out.println("radius: " + rad2);
        //System.out.println("angle per tooth: " + ang2*180.0/Math.PI);
        //System.out.println("number of teeth: " + numT2);
        paintGear(g2, points2, rad2, ang2, numT2, xOff2, yOff2, true);
    }
    
    /** Paints a gear given: 
    *   The current Graphics2D object
    *   The points list of the gear's Tooth
    */
    public void paintGear(Graphics2D g2, double[][] points, double rad, double ang, int numT, double xOff, double yOff, boolean unfixed) {
        // creates the spline and its mirror
        CubicCurve2D.Double c = new CubicCurve2D.Double(
                scale*points[0][0], scale*points[0][1] + scale*rad, 
                scale*points[1][0], scale*points[1][1] + scale*rad, 
                scale*points[2][0], scale*points[2][1] + scale*rad, 
                scale*points[3][0], scale*points[3][1] + scale*rad);
        CubicCurve2D.Double cRef = new CubicCurve2D.Double(
                -1.0*scale*points[0][0], scale*points[0][1] + scale*rad, 
                -1.0*scale*points[1][0], scale*points[1][1] + scale*rad, 
                -1.0*scale*points[2][0], scale*points[2][1] + scale*rad, 
                -1.0*scale*points[3][0], scale*points[3][1] + scale*rad);
        g2.translate(scale*xOff, scale*yOff);
        if (unfixed) {
            g2.rotate(currentRotation);
        }
        g2.draw(c);
        g2.draw(cRef);
        
        // creates a line with the length of the radius for debugging
        CubicCurve2D.Double line = new CubicCurve2D.Double(
                0, 0, 
                0, 10, 
                0, 20, 
                0, scale*rad);
        g2.draw(line);
        
        // rotates, drawing splines
        for (int i = 0; i < numT - 1; i++) {
            g2.rotate(ang);
            g2.draw(c);
            g2.draw(cRef);
        }
        
        // reset g2 to the original rotation and offset for future drawing
        g2.rotate(ang);
        if (unfixed) {
            g2.rotate(-1.0*currentRotation);
        }
        g2.translate(-1.0*scale*xOff, -1.0*scale*yOff);
    }
    
    // sets any global rotations for the unfixed gear(s)
    public void setRotation(double rot) {
        currentRotation = rot;
    }
}