package ca.uwo.csd.cs2212.team10;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import java.awt.BorderLayout; 

/**
 * This class implements the location set window
 * @author Conor
 */
public class MapLocationSetWindow extends JFrame {

    private JTextField locationName;
    private JLabel info;
    private JButton btnSearch;
    private JButton btnSave;
    private Map map;
    private Location location;  
    
    /**
     * Main constructor which passes in the current map object used by the main program
     * @param map Map object
     */
    public MapLocationSetWindow(Map map) {
        this.map = map;
        this.initUI();
    }
    
    /**
     * This method initialises the UI and calls other methods to set up the frame.
     */
    private void initUI () {
        this.setTitle("Set Current Location");
        this.setSize(315, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setBackground(new Color(40,40,40));

        this.setLayout(new BorderLayout());
        this.add(this.createForm(), BorderLayout.CENTER);
    }
    
    /**
     * This method provides the main frame
     * @return JPanel object
     */
    private JPanel createForm() {

        JPanel panel = new JPanel();
        panel.setBackground(new Color(40,40,40));
        JLabel lblName = new JLabel("Location:");
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

        locationName = new JTextField();
        locationName.setPreferredSize(new Dimension(75,25));

        btnSearch = new JButton("Search");
        btnSave  = new JButton("Save");

        info = new JLabel(); 

        //ButtonGroup buttons = new ButtonGroup();
        //buttons.add(this.btnSearch);

        btnSearch.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent event) {
                try {
                    location = map.getNewLocation(locationName.getText());
                    info.setIcon(new ImageIcon(location.getLocationImage()));
                } catch (Exception e) {
                    String msg = "An error occured. Please try again later.";
                    info.setText(msg);
                }
            }
        });
        btnSave.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent event) {
                try {
                    if(locationName.getText().equals("")) map.setCurrentLocation(map.getCurrentLocation());
                    else map.setCurrentLocation(location);
                    MapLocationSetWindow.super.setVisible(false);
                    MapLocationSetWindow.super.dispose();
                } catch (Exception e) {
                    String msg = "An error occured. Please try again later.";
                    info.setText(msg);
                    MapLocationSetWindow.super.setVisible(false);
                    MapLocationSetWindow.super.dispose(); 
                }
            }
        });

        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup( layout.createSequentialGroup()
                .addGroup( layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            )
                        .addGroup( layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(locationName)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSearch)
                                .addComponent(btnSave)
                                )
                            )
                        )
                    .addComponent(info)
                    )
                );
        layout.setVerticalGroup( layout.createSequentialGroup()
                .addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(locationName)
                    )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(btnSave)
                    )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(info)
                    //.addComponent(btnSave)
                    )
                );

        panel.setLayout(layout);

        return panel;
    }


}
