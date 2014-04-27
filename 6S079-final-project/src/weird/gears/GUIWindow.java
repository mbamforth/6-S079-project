package weird.gears;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class GUIWindow extends JPanel {
    // necessary to avoid an error
    private static final long serialVersionUID = 1L;
    
    private GearSet model;
    private final JLabel weirdGearsLabel;
    private final JTextArea weirdGearsInfo;
    private final JSlider radSlider;
    private final JLabel radSliderText;
    private final JSlider toothAngSlider;
    private final JLabel toothAngSliderText;
    private final JLabel toothAngSliderInfo;
    //private final JLabel gearLabel;
    private final JPanel sliderPanel;
    private final JPanel gearPanel;
    private final RenderPanel renderingPanel;
    
    static final int ANG_MIN = 2;
    static final int ANG_MAX = 90;
    static final int ANG_SPACING = 8;
    static int INITIAL_ANG = 30;
    static final int RAD_MIN = 0;
    static final int RAD_MAX = 100;
    static final int RAD_SPACING = 10;
    static final int INITIAL_RAD = 10;

    public GUIWindow() {
        // sets up the model (0 is 2 circular uniform gears)
        model = new GearSet(1);
        
        // create components
        weirdGearsLabel = new JLabel("Welcome to Weird Gears!");
        weirdGearsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        weirdGearsInfo = new JTextArea("Weird Gears is a program which will simulate sets of gears based "
                + "on parameters passed by you, the user. Feel free to adjust the displayed gears with the sliders below.");
        weirdGearsInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        weirdGearsInfo.setEditable(false);
        weirdGearsInfo.setCursor(null);
        weirdGearsInfo.setOpaque(false);
        weirdGearsInfo.setFocusable(false);
        weirdGearsInfo.setWrapStyleWord(true);
        weirdGearsInfo.setLineWrap(true);
        weirdGearsInfo.setMaximumSize(new Dimension(370,80));
        
        radSlider = new JSlider(RAD_MIN, RAD_MAX, INITIAL_RAD);
        radSlider.setMajorTickSpacing(RAD_SPACING);
        radSlider.setPaintLabels(true);
        radSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        radSlider.setMaximumSize(new Dimension(370,30));
        
        radSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                double val = (double) radSlider.getValue();
                model.getUnfixedGear().radiusScale(val);
                // repaint here?
            }
        });
        
        
        radSliderText = new JLabel("Slide to change the radius");
        radSliderText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        INITIAL_ANG = (int) (model.getFixedGear().getTooth().getAng()*180.0/Math.PI);
        toothAngSlider = new JSlider(ANG_MIN, ANG_MAX, INITIAL_ANG);
        toothAngSlider.setMajorTickSpacing(ANG_SPACING);
        toothAngSlider.setPaintLabels(true);
        toothAngSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        toothAngSlider.setMaximumSize(new Dimension(370,30));
        
        toothAngSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                // print some things here to see if it is working
                double val = (double) radSlider.getValue()*2*Math.PI/360;
                model.getUnfixedGear().toothScale(val);
            }
        });
        
        
        toothAngSliderText = new JLabel("Slide to change the angle taken by each tooth (in degrees)");
        toothAngSliderText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        toothAngSliderInfo = new JLabel("The angle of a tooth is inversely proportional to the number of teeth");
        toothAngSliderInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        sliderPanel = new JPanel();
        gearPanel = new JPanel();
        renderingPanel = new RenderPanel(model);
        
        // lays out the main panel
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(900,400));
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.white));
        
        // set up rendering panel 
        // TODO: spline drawing goes here (?)
        renderingPanel.setPreferredSize(new Dimension(500,400));
        renderingPanel.setOpaque(true);
        renderingPanel.setBackground(Color.white);
        
        // set up the gear panel
        gearPanel.setLayout(new BoxLayout(gearPanel, BoxLayout.PAGE_AXIS));
        gearPanel.setPreferredSize(new Dimension(500,400));
        gearPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

        //gearPanel.add(gearLabel);
        gearPanel.add(renderingPanel);

        // set up the slider panel
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS));
        sliderPanel.setPreferredSize(new Dimension(400,400));
        sliderPanel.setMaximumSize(new Dimension(400,400));
        sliderPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.blue));
        
        // add things to the slider panel
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sliderPanel.add(weirdGearsLabel);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        sliderPanel.add(weirdGearsInfo);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sliderPanel.add(radSlider);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        sliderPanel.add(radSliderText);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sliderPanel.add(toothAngSlider);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        sliderPanel.add(toothAngSliderText);
        sliderPanel.add(toothAngSliderInfo);
        sliderPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        // set up the root panel
        add(gearPanel);
        add(sliderPanel);
        
        // TODO: add user input here, reset the sliders to the right values, paint the model
        
    }
}

// the panel on which things are drawn
class RenderPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    GearSet model;
    
    public RenderPanel(GearSet mo) {
        model = mo;
    }
    
    // helps draw the splines
    @Override
    public void paintComponent (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.WHITE);
        g2.setColor(Color.BLACK);
        
        
        // draws the fixed gear
        double[][] points = model.getFixedGear().getTooth().getToothSpline().getPoints();
        double scale = 8.0;
        double xOff = 155.0;
        double yOff = 77.0;
        CubicCurve2D.Double c = new CubicCurve2D.Double(scale*points[0][0] + xOff, scale*points[0][1] + yOff, 
                scale*points[1][0] + xOff, scale*points[1][1] + yOff, 
                scale*points[2][0] + xOff, scale*points[2][1] + yOff, 
                scale*points[3][0] + xOff, scale*points[3][1] + yOff);
        CubicCurve2D.Double cRef = new CubicCurve2D.Double(-1.0*scale*points[0][0] + xOff, scale*points[0][1] + yOff, 
                -1.0*scale*points[1][0] + xOff, scale*points[1][1] + yOff, 
                -1.0*scale*points[2][0] + xOff, scale*points[2][1] + yOff, 
                -1.0*scale*points[3][0] + xOff, scale*points[3][1] + yOff);
        g2.draw(c);
        g2.draw(cRef);
        for (int i = 0; i < model.getFixedGear().getNumTeeth() - 1; i++) {
            g2.rotate(model.getFixedGear().getTooth().getAng(), 155.0, 127.0);
            g2.draw(c);
            g2.draw(cRef);
        }
        
        // draws the unfixed gear
        g2.setColor(Color.BLUE);
        double[][] points2 = model.getUnfixedGear().getTooth().getToothSpline().getPoints();
        double xOff2 = 237.0;
        double yOff2 = 97.0;
        CubicCurve2D.Double c2 = new CubicCurve2D.Double(scale*points2[0][0] + xOff2, scale*points2[0][1] + yOff2, 
                scale*points2[1][0] + xOff2, scale*points2[1][1] + yOff2, 
                scale*points2[2][0] + xOff2, scale*points2[2][1] + yOff2, 
                scale*points2[3][0] + xOff2, scale*points2[3][1] + yOff2);
        CubicCurve2D.Double cRef2 = new CubicCurve2D.Double(-1.0*scale*points2[0][0] + xOff2, scale*points2[0][1] + yOff2, 
                -1.0*scale*points2[1][0] + xOff2, scale*points2[1][1] + yOff2, 
                -1.0*scale*points2[2][0] + xOff2, scale*points2[2][1] + yOff2, 
                -1.0*scale*points2[3][0] + xOff2, scale*points2[3][1] + yOff2);
        g2.draw(c2);
        g2.draw(cRef2);
        for (int j = 0; j < model.getUnfixedGear().getNumTeeth() - 1; j++) {
            g2.rotate(model.getUnfixedGear().getTooth().getAng(), 237.0, 139.0);
            g2.draw(c2);
            g2.draw(cRef2);
        }
        
    }
}