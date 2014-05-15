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
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class GUIWindow extends JPanel {
    // necessary to avoid an error
    private static final long serialVersionUID = 1L;
    
    private GearSet model;
    private final JScrollPane scrollPane;
    private final JLabel weirdGearsLabel;
    private final JTextArea weirdGearsInfo;
    private final JSlider radSlider;
    private final JLabel radSliderText;
    private final JSlider toothAngSlider;
    private final JLabel toothAngSliderText;
    private final JLabel toothAngSliderInfo;
    private final JSlider toothLenSlider;
    private final JLabel toothLenSliderText;
    //private final JLabel gearLabel;
    private final JPanel sliderPanel;
    private final JPanel gearPanel;
    private final RenderPanel renderingPanel;
    
    static final int ANG_MIN = 2;
    static final int ANG_MAX = 90;
    static final int ANG_SPACING = 8;
    static final int RAD_MIN = 1;
    static final int RAD_MAX = 50;
    static final int RAD_SPACING = 5;
    static final int LEN_MIN = 1;
    static final int LEN_MAX = 15;
    static final int LEN_SPACING = 1;

    public GUIWindow() {
        // sets up the model (0 is 2 circular uniform gears)
        model = new GearSet(1);
        
        // create components
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
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
        
        int INITIAL_ANG = (int) (model.getFixedGear().getTooth().getAng()*180.0/Math.PI);
        toothAngSlider = new JSlider(ANG_MIN, ANG_MAX, INITIAL_ANG);
        toothAngSlider.setMajorTickSpacing(ANG_SPACING);
        toothAngSlider.setPaintLabels(true);
        toothAngSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        toothAngSlider.setMaximumSize(new Dimension(370,30));
        
        toothAngSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                // print some things here to see if it is working
                double result = (double) radSlider.getValue();
                double val = result*Math.PI/180.0;
                model.getUnfixedGear().toothScale(val);
                renderingPanel.repaint();
            }
        });
        
        toothAngSliderText = new JLabel("Slide to change the angle taken by each tooth (in degrees)");
        toothAngSliderText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        toothAngSliderInfo = new JLabel("The angle of a tooth is inversely proportional to the number of teeth");
        toothAngSliderInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // make the tooth length slider
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

        // create the panels
        sliderPanel = new JPanel();
        gearPanel = new JPanel();
        renderingPanel = new RenderPanel(model);
        
        // lays out the main panel
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(950,400));
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.white));
        
        // set up rendering panel 
        // TODO: spline drawing goes here (?)
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
        sliderPanel.setPreferredSize(new Dimension(400,400));
        sliderPanel.setMaximumSize(new Dimension(400,800));
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
        
        // set up the root panel
        add(gearPanel);
        add(scrollPane);
        
        // TODO: add user input here, reset the sliders to the right values
        
    }
}

// the panel on which things are drawn
class RenderPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    GearSet model;
    double scale;
    
    public RenderPanel(GearSet mo) {
        model = mo;
        scale = 5.0;
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
        double xOff = 150.0;
        double yOff = 200.0;
        double rad = model.getFixedGear().getRadius();
        double ang = model.getFixedGear().getTooth().getAng();
        int numT = model.getFixedGear().getNumTeeth();
        paintGear(g2, points, rad, ang, numT, xOff, yOff);
        
        
        // draws the unfixed gear
        g2.setColor(Color.BLUE);
        double[][] points2 = model.getUnfixedGear().getTooth().getToothSpline().getPoints();
        double xOff2 = 300.0;
        double yOff2 = 200.0;
        double rad2 = model.getUnfixedGear().getRadius();
        double ang2 = model.getUnfixedGear().getTooth().getAng();
        int numT2 = model.getUnfixedGear().getNumTeeth();
        //System.out.println("tooth width: " + model.getUnfixedGear().getTooth().getWidth());
        //System.out.println("radius: " + rad2);
        //System.out.println("angle per tooth: " + ang2*180.0/Math.PI);
        //System.out.println("number of teeth: " + numT2);
        System.out.println(points == points2);
        paintGear(g2, points2, rad2, ang2, numT2, xOff2, yOff2);

    }
    
    /** Paints a gear given: 
    *   The current Graphics2D object
    *   The points list of the gear's Tooth
    */
    public void paintGear(Graphics2D g2, double[][] points, double rad, double ang, int numT, double xOff, double yOff) {
        
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
        g2.translate(xOff, yOff);
        g2.draw(c);
        g2.draw(cRef);
        
        CubicCurve2D.Double line = new CubicCurve2D.Double(
                0, 0, 
                0, 10, 
                0, 20, 
                0, scale*rad);
        g2.draw(line);
        for (int i = 0; i < numT - 1; i++) {
            g2.rotate(ang);
            g2.draw(c);
            g2.draw(cRef);
        }
        g2.rotate(ang);
        g2.translate(-xOff, -yOff);
    }
}