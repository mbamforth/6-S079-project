package weird.gears;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

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
    private final JButton addRingGearButton;
    private boolean hasRing;
    
    // components for extra gears
    // components for third gear
    private JSlider radSlider2;
    private JLabel radSliderText2;
    private JSlider toothAngSlider2;
    private JLabel toothAngSliderText2;
    private JLabel toothAngSliderInfo2;
    private JSlider toothLenSlider2;
    private JLabel toothLenSliderText2;
    private JSlider xPosSlider2;
    private JLabel xPosSliderLabel2;
    private JSlider yPosSlider2;
    private JLabel yPosSliderLabel2;
    private JSlider rotationSlider2;
    private JLabel rotationSliderLabel2;
    
    // components for fourth gear
    private JSlider radSlider3;
    private JLabel radSliderText3;
    private JSlider toothAngSlider3;
    private JLabel toothAngSliderText3;
    private JLabel toothAngSliderInfo3;
    private JSlider toothLenSlider3;
    private JLabel toothLenSliderText3;
    private JSlider xPosSlider3;
    private JLabel xPosSliderLabel3;
    private JSlider yPosSlider3;
    private JLabel yPosSliderLabel3;
    private JSlider rotationSlider3;
    private JLabel rotationSliderLabel3;
    
    // components for fifth gear
    private JSlider radSlider4;
    private JLabel radSliderText4;
    private JSlider toothAngSlider4;
    private JLabel toothAngSliderText4;
    private JLabel toothAngSliderInfo4;
    private JSlider toothLenSlider4;
    private JLabel toothLenSliderText4;
    private JSlider xPosSlider4;
    private JLabel xPosSliderLabel4;
    private JSlider yPosSlider4;
    private JLabel yPosSliderLabel4;
    private JSlider rotationSlider4;
    private JLabel rotationSliderLabel4;
    
    // constants
    static final int ANG_MIN = 2;
    static final int ANG_MAX = 62;
    static final int ANG_SPACING = 5;
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
        model = new GearSet(gearSetType);
        hasRing = false;
        
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
                
                for (int j = 2; j < model.getNumGears(); j++) {
                    model.getNthGear(j).getTooth().setAng(len, wid, ang);
                    model.getNthGear(j).radiusScale(model.getNthGear(j).getRadius());
                }
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
                renderingPanel.setRotation(result, 1);
                renderingPanel.repaint();
            }
        });
        
        rotationSliderLabel = new JLabel("Slide to change the rotation of the gear");
        rotationSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // set everything to one color for UI reasons
        radSliderText.setForeground(Color.BLUE);
        toothAngSliderText.setForeground(Color.BLUE);
        toothAngSliderInfo.setForeground(Color.BLUE);
        toothLenSliderText.setForeground(Color.BLUE);
        xPosSliderLabel.setForeground(Color.BLUE);
        yPosSliderLabel.setForeground(Color.BLUE);
        rotationSliderLabel.setForeground(Color.BLUE);
        
        //////////////////////////////////////////////////////////////////////////////
        // end of basic components                                                  //
        //                                                                          //
        // beginning of multiple gear section                                       //
        //////////////////////////////////////////////////////////////////////////////
        
        
        addGearButton = new JButton("Click here to add another gear");
        addGearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addGearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model.getNumGears() > 4) {
                    // do nothing
                } else {
                    Gear g = new RoundGear(30, 30, 10, new Tooth(2.0, 2.5, 0.2));
                    model.addGear(g);
                    // add new sliders here
                    if (model.getNumGears() == 5) {
                        addGearButton.setText("You are at the maximum number of gears");
                        
                        radSlider4 = new JSlider(RAD_MIN, RAD_MAX, (int) model.getNthGear(4).getRadius());
                        radSlider4.setMajorTickSpacing(RAD_SPACING);
                        radSlider4.setPaintLabels(true);
                        radSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        radSlider4.setMaximumSize(new Dimension(370,30));
                        radSlider4.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) radSlider4.getValue();
                                model.getNthGear(4).radiusScale(val);
                                renderingPanel.repaint();
                            }
                        });
                        
                        radSliderText4 = new JLabel("Slide to change the radius");
                        radSliderText4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        int INITIAL_ANG = (int) (model.getNthGear(4).getTooth().getAng()*180.0/Math.PI);
                        toothAngSlider4 = new JSlider(ANG_MIN, ANG_MAX, INITIAL_ANG);
                        toothAngSlider4.setMajorTickSpacing(ANG_SPACING);
                        toothAngSlider4.setPaintLabels(true);
                        toothAngSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        toothAngSlider4.setMaximumSize(new Dimension(370,30));
                        toothAngSlider4.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double result = (double) toothAngSlider4.getValue();
                                double ang = result*Math.PI/180.0;
                                double len = model.getNthGear(4).getTooth().getLen();
                                double rad = model.getNthGear(4).getRadius();
                                double wid = ang*rad;
                                model.getNthGear(4).getTooth().setAng(len, wid, ang);
                                model.getNthGear(4).radiusScale(rad);
                                renderingPanel.repaint();
                            }
                        });
                        
                        toothAngSliderText4 = new JLabel("Slide to change the angle taken by each tooth (in degrees)");
                        toothAngSliderText4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        toothAngSliderInfo4 = new JLabel("The angle of a tooth is inversely proportional to the number of teeth");
                        toothAngSliderInfo4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        toothLenSlider4 = new JSlider(LEN_MIN, LEN_MAX, (int) model.getNthGear(4).getTooth().getLen());
                        toothLenSlider4.setMajorTickSpacing(LEN_SPACING);
                        toothLenSlider4.setPaintLabels(true);
                        toothLenSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        toothLenSlider4.setMaximumSize(new Dimension(370,30));
                        
                        toothLenSlider4.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) toothLenSlider4.getValue();
                                model.getNthGear(4).getTooth().setLen(val);
                                renderingPanel.repaint();
                            }
                        });
                        
                        toothLenSliderText4 = new JLabel("Slide to change the length of each tooth");
                        toothLenSliderText4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        xPosSlider4 = new JSlider(X_MIN, X_MAX, (int) model.getNthGear(4).getX());
                        xPosSlider4.setMajorTickSpacing(X_SPACING);
                        xPosSlider4.setPaintLabels(true);
                        xPosSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        xPosSlider4.setMaximumSize(new Dimension(370,30));
                        
                        xPosSlider4.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) xPosSlider4.getValue();
                                model.getNthGear(4).setOrigin(val, model.getNthGear(4).getY());
                                renderingPanel.repaint();
                            }
                        });
                        
                        xPosSliderLabel4 = new JLabel("Slide to change the X coordinate of the gear's center");
                        xPosSliderLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        // y slider
                        yPosSlider4 = new JSlider(Y_MIN, Y_MAX, (int) model.getNthGear(4).getY());
                        yPosSlider4.setMajorTickSpacing(Y_SPACING);
                        yPosSlider4.setPaintLabels(true);
                        yPosSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        yPosSlider4.setMaximumSize(new Dimension(370,30));
                        
                        yPosSlider4.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) yPosSlider4.getValue();
                                model.getNthGear(4).setOrigin(model.getNthGear(4).getX(), val);
                                renderingPanel.repaint();
                            }
                        });
                        
                        yPosSliderLabel4 = new JLabel("Slide to change the Y coordinate of the gear's center");
                        yPosSliderLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        
                        // rotation slider
                        rotationSlider4 = new JSlider(ROT_MIN, ROT_MAX, 0);
                        rotationSlider4.setMajorTickSpacing(ROT_SPACING);
                        rotationSlider4.setPaintLabels(true);
                        rotationSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        rotationSlider4.setMaximumSize(new Dimension(370,30));
                        
                        rotationSlider4.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double)rotationSlider4.getValue();
                                double result = val*Math.PI/180.0;
                                renderingPanel.setRotation(result, 4);
                                renderingPanel.repaint();
                            }
                        });
                        
                        rotationSliderLabel4 = new JLabel("Slide to change the rotation of the gear");
                        rotationSliderLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        
                        // colors
                        radSliderText4.setForeground(Color.ORANGE);
                        toothAngSliderText4.setForeground(Color.ORANGE);
                        toothAngSliderInfo4.setForeground(Color.ORANGE);
                        toothLenSliderText4.setForeground(Color.ORANGE);
                        xPosSliderLabel4.setForeground(Color.ORANGE);
                        yPosSliderLabel4.setForeground(Color.ORANGE);
                        rotationSliderLabel4.setForeground(Color.ORANGE);
                        
                        // add to panel
                        sliderPanel.add(radSlider4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(radSliderText4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(toothAngSlider4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(toothAngSliderText4);
                        sliderPanel.add(toothAngSliderInfo4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(toothLenSlider4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(toothLenSliderText4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(xPosSlider4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(xPosSliderLabel4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(yPosSlider4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(yPosSliderLabel4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(rotationSlider4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(rotationSliderLabel4);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.repaint();
                    } else if (model.getNumGears() == 4) {
                        radSlider3 = new JSlider(RAD_MIN, RAD_MAX, (int) model.getNthGear(3).getRadius());
                        radSlider3.setMajorTickSpacing(RAD_SPACING);
                        radSlider3.setPaintLabels(true);
                        radSlider3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        radSlider3.setMaximumSize(new Dimension(370,30));
                        radSlider3.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) radSlider3.getValue();
                                model.getNthGear(3).radiusScale(val);
                                renderingPanel.repaint();
                            }
                        });
                        
                        radSliderText3 = new JLabel("Slide to change the radius");
                        radSliderText3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        int INITIAL_ANG = (int) (model.getNthGear(3).getTooth().getAng()*180.0/Math.PI);
                        toothAngSlider3 = new JSlider(ANG_MIN, ANG_MAX, INITIAL_ANG);
                        toothAngSlider3.setMajorTickSpacing(ANG_SPACING);
                        toothAngSlider3.setPaintLabels(true);
                        toothAngSlider3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        toothAngSlider3.setMaximumSize(new Dimension(370,30));
                        toothAngSlider3.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double result = (double) toothAngSlider3.getValue();
                                double ang = result*Math.PI/180.0;
                                double len = model.getNthGear(3).getTooth().getLen();
                                double rad = model.getNthGear(3).getRadius();
                                double wid = ang*rad;
                                model.getNthGear(3).getTooth().setAng(len, wid, ang);
                                model.getNthGear(3).radiusScale(rad);
                                renderingPanel.repaint();
                            }
                        });
                        
                        toothAngSliderText3 = new JLabel("Slide to change the angle taken by each tooth (in degrees)");
                        toothAngSliderText3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        toothAngSliderInfo3 = new JLabel("The angle of a tooth is inversely proportional to the number of teeth");
                        toothAngSliderInfo3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        toothLenSlider3 = new JSlider(LEN_MIN, LEN_MAX, (int) model.getNthGear(3).getTooth().getLen());
                        toothLenSlider3.setMajorTickSpacing(LEN_SPACING);
                        toothLenSlider3.setPaintLabels(true);
                        toothLenSlider3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        toothLenSlider3.setMaximumSize(new Dimension(370,30));
                        
                        toothLenSlider3.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) toothLenSlider3.getValue();
                                model.getNthGear(3).getTooth().setLen(val);
                                renderingPanel.repaint();
                            }
                        });
                        
                        toothLenSliderText3 = new JLabel("Slide to change the length of each tooth");
                        toothLenSliderText3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        xPosSlider3 = new JSlider(X_MIN, X_MAX, (int) model.getNthGear(3).getX());
                        xPosSlider3.setMajorTickSpacing(X_SPACING);
                        xPosSlider3.setPaintLabels(true);
                        xPosSlider3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        xPosSlider3.setMaximumSize(new Dimension(370,30));
                        
                        xPosSlider3.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) xPosSlider3.getValue();
                                model.getNthGear(3).setOrigin(val, model.getNthGear(3).getY());
                                renderingPanel.repaint();
                            }
                        });
                        
                        xPosSliderLabel3 = new JLabel("Slide to change the X coordinate of the gear's center");
                        xPosSliderLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        // y slider
                        yPosSlider3 = new JSlider(Y_MIN, Y_MAX, (int) model.getNthGear(3).getY());
                        yPosSlider3.setMajorTickSpacing(Y_SPACING);
                        yPosSlider3.setPaintLabels(true);
                        yPosSlider3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        yPosSlider3.setMaximumSize(new Dimension(370,30));
                        
                        yPosSlider3.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) yPosSlider3.getValue();
                                model.getNthGear(3).setOrigin(model.getNthGear(3).getX(), val);
                                renderingPanel.repaint();
                            }
                        });
                        
                        yPosSliderLabel3 = new JLabel("Slide to change the Y coordinate of the gear's center");
                        yPosSliderLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        
                        // rotation slider
                        rotationSlider3 = new JSlider(ROT_MIN, ROT_MAX, 0);
                        rotationSlider3.setMajorTickSpacing(ROT_SPACING);
                        rotationSlider3.setPaintLabels(true);
                        rotationSlider3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        rotationSlider3.setMaximumSize(new Dimension(370,30));
                        
                        rotationSlider3.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double)rotationSlider3.getValue();
                                double result = val*Math.PI/180.0;
                                renderingPanel.setRotation(result, 3);
                                renderingPanel.repaint();
                            }
                        });
                        
                        rotationSliderLabel3 = new JLabel("Slide to change the rotation of the gear");
                        rotationSliderLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        
                        // colors
                        radSliderText3.setForeground(Color.GREEN);
                        toothAngSliderText3.setForeground(Color.GREEN);
                        toothAngSliderInfo3.setForeground(Color.GREEN);
                        toothLenSliderText3.setForeground(Color.GREEN);
                        xPosSliderLabel3.setForeground(Color.GREEN);
                        yPosSliderLabel3.setForeground(Color.GREEN);
                        rotationSliderLabel3.setForeground(Color.GREEN);
                        
                        // add to panel
                        sliderPanel.add(radSlider3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(radSliderText3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(toothAngSlider3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(toothAngSliderText3);
                        sliderPanel.add(toothAngSliderInfo3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(toothLenSlider3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(toothLenSliderText3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(xPosSlider3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(xPosSliderLabel3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(yPosSlider3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(yPosSliderLabel3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(rotationSlider3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(rotationSliderLabel3);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.repaint();
                        
                    } else if (model.getNumGears() == 3) {
                        radSlider2 = new JSlider(RAD_MIN, RAD_MAX, (int) model.getNthGear(2).getRadius());
                        radSlider2.setMajorTickSpacing(RAD_SPACING);
                        radSlider2.setPaintLabels(true);
                        radSlider2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        radSlider2.setMaximumSize(new Dimension(370,30));
                        radSlider2.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) radSlider2.getValue();
                                model.getNthGear(2).radiusScale(val);
                                renderingPanel.repaint();
                            }
                        });
                        
                        radSliderText2 = new JLabel("Slide to change the radius");
                        radSliderText2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        int INITIAL_ANG = (int) (model.getNthGear(2).getTooth().getAng()*180.0/Math.PI);
                        toothAngSlider2 = new JSlider(ANG_MIN, ANG_MAX, INITIAL_ANG);
                        toothAngSlider2.setMajorTickSpacing(ANG_SPACING);
                        toothAngSlider2.setPaintLabels(true);
                        toothAngSlider2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        toothAngSlider2.setMaximumSize(new Dimension(370,30));
                        toothAngSlider2.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double result = (double) toothAngSlider2.getValue();
                                double ang = result*Math.PI/180.0;
                                double len = model.getNthGear(2).getTooth().getLen();
                                double rad = model.getNthGear(2).getRadius();
                                double wid = ang*rad;
                                model.getNthGear(2).getTooth().setAng(len, wid, ang);
                                model.getNthGear(2).radiusScale(rad);
                                renderingPanel.repaint();
                            }
                        });
                        
                        toothAngSliderText2 = new JLabel("Slide to change the angle taken by each tooth (in degrees)");
                        toothAngSliderText2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        toothAngSliderInfo2 = new JLabel("The angle of a tooth is inversely proportional to the number of teeth");
                        toothAngSliderInfo2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        toothLenSlider2 = new JSlider(LEN_MIN, LEN_MAX, (int) model.getNthGear(2).getTooth().getLen());
                        toothLenSlider2.setMajorTickSpacing(LEN_SPACING);
                        toothLenSlider2.setPaintLabels(true);
                        toothLenSlider2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        toothLenSlider2.setMaximumSize(new Dimension(370,30));
                        
                        toothLenSlider2.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) toothLenSlider2.getValue();
                                model.getNthGear(2).getTooth().setLen(val);
                                renderingPanel.repaint();
                            }
                        });
                        
                        toothLenSliderText2 = new JLabel("Slide to change the length of each tooth");
                        toothLenSliderText2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        xPosSlider2 = new JSlider(X_MIN, X_MAX, (int) model.getNthGear(2).getX());
                        xPosSlider2.setMajorTickSpacing(X_SPACING);
                        xPosSlider2.setPaintLabels(true);
                        xPosSlider2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        xPosSlider2.setMaximumSize(new Dimension(370,30));
                        
                        xPosSlider2.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) xPosSlider2.getValue();
                                model.getNthGear(2).setOrigin(val, model.getNthGear(2).getY());
                                renderingPanel.repaint();
                            }
                        });
                        
                        xPosSliderLabel2 = new JLabel("Slide to change the X coordinate of the gear's center");
                        xPosSliderLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        // y slider
                        yPosSlider2 = new JSlider(Y_MIN, Y_MAX, (int) model.getNthGear(2).getY());
                        yPosSlider2.setMajorTickSpacing(Y_SPACING);
                        yPosSlider2.setPaintLabels(true);
                        yPosSlider2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        yPosSlider2.setMaximumSize(new Dimension(370,30));
                        
                        yPosSlider2.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double) yPosSlider2.getValue();
                                model.getNthGear(2).setOrigin(model.getNthGear(2).getX(), val);
                                renderingPanel.repaint();
                            }
                        });
                        
                        yPosSliderLabel2 = new JLabel("Slide to change the Y coordinate of the gear's center");
                        yPosSliderLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        
                        // rotation slider
                        rotationSlider2 = new JSlider(ROT_MIN, ROT_MAX, 0);
                        rotationSlider2.setMajorTickSpacing(ROT_SPACING);
                        rotationSlider2.setPaintLabels(true);
                        rotationSlider2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        rotationSlider2.setMaximumSize(new Dimension(370,30));
                        
                        rotationSlider2.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent event) {
                                double val = (double)rotationSlider2.getValue();
                                double result = val*Math.PI/180.0;
                                renderingPanel.setRotation(result, 2);
                                renderingPanel.repaint();
                            }
                        });
                        
                        rotationSliderLabel2 = new JLabel("Slide to change the rotation of the gear");
                        rotationSliderLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        
                        // colors
                        radSliderText2.setForeground(Color.RED);
                        toothAngSliderText2.setForeground(Color.RED);
                        toothAngSliderInfo2.setForeground(Color.RED);
                        toothLenSliderText2.setForeground(Color.RED);
                        xPosSliderLabel2.setForeground(Color.RED);
                        yPosSliderLabel2.setForeground(Color.RED);
                        rotationSliderLabel2.setForeground(Color.RED);
                        
                        // add to panel
                        sliderPanel.add(radSlider2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(radSliderText2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(toothAngSlider2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(toothAngSliderText2);
                        sliderPanel.add(toothAngSliderInfo2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(toothLenSlider2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(toothLenSliderText2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(xPosSlider2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(xPosSliderLabel2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(yPosSlider2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(yPosSliderLabel2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.add(rotationSlider2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        sliderPanel.add(rotationSliderLabel2);
                        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                        sliderPanel.repaint();
                    }
                    
                }
                renderingPanel.repaint();
            }
        });
        
        
        // just like addGearButton but for ring gears. there can only be 0 or 1
        addRingGearButton = new JButton("Click here to add an outer ring gear");
        addRingGearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRingGearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hasRing) {
                    // do nothing
                } else if (model.getNumGears() < 5) {
                    Gear g = new RingGear(50, 50, 30, 5, new Tooth(2.0, 2.5, 0.2));
                    model.addGear(g);
                    addRingGearButton.setText("You are at the maximum number of gears");
                    final int n = model.getNumGears() - 1;
                        
                    radSlider4 = new JSlider(RAD_MIN, RAD_MAX, (int) model.getNthGear(n).getRadius());
                    radSlider4.setMajorTickSpacing(RAD_SPACING);
                    radSlider4.setPaintLabels(true);
                    radSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                    radSlider4.setMaximumSize(new Dimension(370,30));
                    radSlider4.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent event) {
                            double val = (double) radSlider4.getValue();
                            model.getNthGear(n).radiusScale(val);
                            renderingPanel.repaint();
                        }
                    });
                    
                    radSliderText4 = new JLabel("Slide to change the radius");
                    radSliderText4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                    int INITIAL_ANG = (int) (model.getNthGear(n).getTooth().getAng()*180.0/Math.PI);
                    toothAngSlider4 = new JSlider(ANG_MIN, ANG_MAX, INITIAL_ANG);
                    toothAngSlider4.setMajorTickSpacing(ANG_SPACING);
                    toothAngSlider4.setPaintLabels(true);
                    toothAngSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                    toothAngSlider4.setMaximumSize(new Dimension(370,30));
                    toothAngSlider4.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent event) {
                            double result = (double) toothAngSlider4.getValue();
                            double ang = result*Math.PI/180.0;
                            double len = model.getNthGear(n).getTooth().getLen();
                            double rad = model.getNthGear(n).getRadius();
                            double wid = ang*rad;
                            model.getNthGear(n).getTooth().setAng(len, wid, ang);
                            model.getNthGear(n).radiusScale(rad);
                            renderingPanel.repaint();
                        }
                    });
                    
                    toothAngSliderText4 = new JLabel("Slide to change the angle taken by each tooth (in degrees)");
                    toothAngSliderText4.setAlignmentX(Component.CENTER_ALIGNMENT);
                    
                    toothAngSliderInfo4 = new JLabel("The angle of a tooth is inversely proportional to the number of teeth");
                    toothAngSliderInfo4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                    toothLenSlider4 = new JSlider(LEN_MIN, LEN_MAX, (int) model.getNthGear(n).getTooth().getLen());
                    toothLenSlider4.setMajorTickSpacing(LEN_SPACING);
                    toothLenSlider4.setPaintLabels(true);
                    toothLenSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                    toothLenSlider4.setMaximumSize(new Dimension(370,30));
                    
                    toothLenSlider4.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent event) {
                            double val = (double) toothLenSlider4.getValue();
                            model.getNthGear(n).getTooth().setLen(val);
                            renderingPanel.repaint();
                        }
                    });
                    
                    toothLenSliderText4 = new JLabel("Slide to change the length of each tooth");
                    toothLenSliderText4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                    xPosSlider4 = new JSlider(X_MIN, X_MAX, (int) model.getNthGear(n).getX());
                    xPosSlider4.setMajorTickSpacing(X_SPACING);
                    xPosSlider4.setPaintLabels(true);
                    xPosSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                    xPosSlider4.setMaximumSize(new Dimension(370,30));
                    
                    xPosSlider4.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent event) {
                            double val = (double) xPosSlider4.getValue();
                            model.getNthGear(n).setOrigin(val, model.getNthGear(n).getY());
                            renderingPanel.repaint();
                        }
                    });
                    
                    xPosSliderLabel4 = new JLabel("Slide to change the X coordinate of the gear's center");
                    xPosSliderLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                    // y slider
                    yPosSlider4 = new JSlider(Y_MIN, Y_MAX, (int) model.getNthGear(n).getY());
                    yPosSlider4.setMajorTickSpacing(Y_SPACING);
                    yPosSlider4.setPaintLabels(true);
                    yPosSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                    yPosSlider4.setMaximumSize(new Dimension(370,30));
                    
                    yPosSlider4.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent event) {
                            double val = (double) yPosSlider4.getValue();
                            model.getNthGear(n).setOrigin(model.getNthGear(n).getX(), val);
                            renderingPanel.repaint();
                        }
                    });
                    
                    yPosSliderLabel4 = new JLabel("Slide to change the Y coordinate of the gear's center");
                    yPosSliderLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        
                    // rotation slider
                    rotationSlider4 = new JSlider(ROT_MIN, ROT_MAX, 0);
                    rotationSlider4.setMajorTickSpacing(ROT_SPACING);
                    rotationSlider4.setPaintLabels(true);
                    rotationSlider4.setAlignmentX(Component.CENTER_ALIGNMENT);
                    rotationSlider4.setMaximumSize(new Dimension(370,30));
                    
                    rotationSlider4.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent event) {
                            double val = (double)rotationSlider4.getValue();
                            double result = val*Math.PI/180.0;
                            renderingPanel.setRotation(result, n);
                            renderingPanel.repaint();
                        }
                    });
                    
                    rotationSliderLabel4 = new JLabel("Slide to change the rotation of the gear");
                    rotationSliderLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                    ArrayList<Color> colors = new ArrayList<Color>(); 
                    colors.add(Color.RED);
                    colors.add(Color.GREEN);
                    colors.add(Color.ORANGE);

                    Color ringCol = colors.get(n - 2);
                    // colors
                    radSliderText4.setForeground(ringCol);
                    toothAngSliderText4.setForeground(ringCol);
                    toothAngSliderInfo4.setForeground(ringCol);
                    toothLenSliderText4.setForeground(ringCol);
                    xPosSliderLabel4.setForeground(ringCol);
                    yPosSliderLabel4.setForeground(ringCol);
                    rotationSliderLabel4.setForeground(ringCol);
                        
                    // add to panel
                    sliderPanel.add(radSlider4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                    sliderPanel.add(radSliderText4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                    sliderPanel.add(toothAngSlider4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                    sliderPanel.add(toothAngSliderText4);
                    sliderPanel.add(toothAngSliderInfo4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                    sliderPanel.add(toothLenSlider4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                    sliderPanel.add(toothLenSliderText4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                    sliderPanel.add(xPosSlider4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                    sliderPanel.add(xPosSliderLabel4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                    sliderPanel.add(yPosSlider4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                    sliderPanel.add(yPosSliderLabel4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                    sliderPanel.add(rotationSlider4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                    sliderPanel.add(rotationSliderLabel4);
                    sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                    sliderPanel.repaint();
                } 
                renderingPanel.repaint();
            }
        });
        
        
        
        //////////////////////////////////////////////////////////////////////////////
        // end of multiple gear section                                             //
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
        sliderPanel.setPreferredSize(new Dimension(400,2500));
        sliderPanel.setMaximumSize(new Dimension(400,2500));
        sliderPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.blue));
        scrollPane.setPreferredSize(new Dimension(450,2500));
        scrollPane.setMaximumSize(new Dimension(450,2500));
        
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
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        sliderPanel.add(addGearButton);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        sliderPanel.add(addRingGearButton);
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
    double[] currentRotation;
    
    public RenderPanel(GearSet mo) {
        model = mo;
        scale = 5.0;
        currentRotation = new double[5];
        currentRotation[0] = 0.0;
        currentRotation[1] = 0.0;
        currentRotation[2] = 0.0;
        currentRotation[3] = 0.0;
        currentRotation[4] = 0.0;
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
        paintGear(g2, points, rad, ang, numT, xOff, yOff, 0);
        
        
        // draws the unfixed gear
        g2.setColor(Color.BLUE);
        double[][] points2 = model.getUnfixedGear().getTooth().getToothSpline().getPoints();
        double xOff2 = model.getUnfixedGear().getX();
        double yOff2 = model.getUnfixedGear().getY();
        double rad2 = model.getUnfixedGear().getRadius();
        double ang2 = model.getUnfixedGear().getTooth().getAng();
        int numT2 = model.getUnfixedGear().getNumTeeth();
        paintGear(g2, points2, rad2, ang2, numT2, xOff2, yOff2, 1);
        
        
        // deals with more than two gears in a set
        if (model.getNumGears() > 2) {
            ArrayList<Color> colors = new ArrayList<Color>(); 
            colors.add(Color.RED);
            colors.add(Color.GREEN);
            colors.add(Color.ORANGE);
            for (int j = 2; j < model.getNumGears(); j++) {
                g2.setColor(colors.get(j-2));
                if (model.getNthGear(j) instanceof RoundGear) {
                    double[][] points3 = model.getNthGear(j).getTooth().getToothSpline().getPoints();
                    double xOff3 = model.getNthGear(j).getX();
                    double yOff3 = model.getNthGear(j).getY();
                    double rad3 = model.getNthGear(j).getRadius();
                    double ang3 = model.getNthGear(j).getTooth().getAng();
                    int numT3 = model.getNthGear(j).getNumTeeth();
                    paintGear(g2, points3, rad3, ang3, numT3, xOff3, yOff3, j);
                } else if (model.getNthGear(j) instanceof RingGear) {
                    double[][] points3 = model.getNthGear(j).getTooth().getToothSpline().getPoints();
                    double xOff3 = model.getNthGear(j).getX();
                    double yOff3 = model.getNthGear(j).getY();
                    double rad3 = model.getNthGear(j).getRadius();
                    double ang3 = model.getNthGear(j).getTooth().getAng();
                    int numT3 = model.getNthGear(j).getNumTeeth();
                    double thick = model.getNthGear(j).getThickness();
                    paintRingGear(g2, points3, rad3, ang3, numT3, thick, xOff3, yOff3, j);
                }
            }
        }
    }
    
    /** Paints a gear given: 
    *   The current Graphics2D object
    *   The points list of the gear's Tooth
    */
    public void paintGear(Graphics2D g2, double[][] points, double rad, double ang, int numT, double xOff, double yOff, int gear) {
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
        g2.rotate(currentRotation[gear]);
        
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
        g2.rotate(-1.0*currentRotation[gear]);
        
        g2.translate(-1.0*scale*xOff, -1.0*scale*yOff);
    }
    
    // paints a ring gear
    public void paintRingGear(Graphics2D g2, double[][] points, double rad, double ang, int numT, double thickness, double xOff, double yOff, int gear) {
        // creates the spline and its mirror
        CubicCurve2D.Double c = new CubicCurve2D.Double(
                scale*points[0][0], -1.0*scale*points[0][1] + scale*rad, 
                scale*points[1][0], -1.0*scale*points[1][1] + scale*rad, 
                scale*points[2][0], -1.0*scale*points[2][1] + scale*rad, 
                scale*points[3][0], -1.0*scale*points[3][1] + scale*rad);
        CubicCurve2D.Double cRef = new CubicCurve2D.Double(
                -1.0*scale*points[0][0], -1.0*scale*points[0][1] + scale*rad, 
                -1.0*scale*points[1][0], -1.0*scale*points[1][1] + scale*rad, 
                -1.0*scale*points[2][0], -1.0*scale*points[2][1] + scale*rad, 
                -1.0*scale*points[3][0], -1.0*scale*points[3][1] + scale*rad);
        g2.translate(scale*xOff, scale*yOff);
        g2.rotate(currentRotation[gear]);
        
        g2.draw(c);
        g2.draw(cRef);
        
        // creates a line with the length of the radius for debugging
        CubicCurve2D.Double line = new CubicCurve2D.Double(
                0, 0, 
                0, 10, 
                0, 20, 
                0, scale*rad);
        g2.draw(line);
        double outerRad = rad + thickness;
        Shape cir = new Ellipse2D.Double(-1.0*scale*outerRad, -1.0*scale*outerRad, 2.0*scale*outerRad, 2.0*scale*outerRad);
        g2.draw(cir);
        
        // rotates, drawing splines
        for (int i = 0; i < numT - 1; i++) {
            g2.rotate(ang);
            g2.draw(c);
            g2.draw(cRef);
        }
        
        // reset g2 to the original rotation and offset for future drawing
        g2.rotate(ang);
        g2.rotate(-1.0*currentRotation[gear]);
        
        g2.translate(-1.0*scale*xOff, -1.0*scale*yOff);
    }
    
    // sets any global rotations for the unfixed gear(s)
    public void setRotation(double rot, int gear) {
        currentRotation[gear] = rot;
    }
}