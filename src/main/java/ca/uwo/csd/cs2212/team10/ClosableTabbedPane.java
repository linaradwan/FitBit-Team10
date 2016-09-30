package ca.uwo.csd.cs2212.team10;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTabbedPane;

/**
 * This class implements the closable main tabs on the main window. 
 * @author Team10 
 */ 
public class ClosableTabbedPane extends JTabbedPane
{
  private static final long serialVersionUID = 1L;
  private TabCloseUI closeUI = new TabCloseUI(this);
    
  /**
   * This method draws the tabs
   */
  public void paint(Graphics g) {
    super.paint(g);
    this.closeUI.paint(g);
  }

  /**
   * This method adds a tab to the tabbed pane
   * @param title String that contains the title
   * @param component Component object
   */
  public void addTab(String title, Component component) {
    super.addTab(title + "  ", component);
  }
    
  /**
   * This method returns the title of the tab at a given index
   * @param index integer value of the index 
   * @return String containing the tab title
   */
  public String getTabTitleAt(int index)
  {
    return super.getTitleAt(index).trim();
  }
    
  /**
   * This method returns true or false if the tab at the given index is about to close
   * @param tabIndex integer value of the index
   * @return Boolean true or false
   */
  public boolean tabAboutToClose(int tabIndex)
  {
    return true;
  }
    
  /**
   * This class implements the actions of the thab
   */
  private class TabCloseUI
    implements MouseListener, MouseMotionListener
  {
    private ClosableTabbedPane tabbedPane;
    private int closeX = 0; private int closeY = 0; private int meX = 0; private int meY = 0;
    private int selectedTab;
    private final int width = 8; private final int height = 8;
    private Rectangle rectangle = new Rectangle(0, 0, 8, 8);
    
    /**
     * Private constructor
     */
    private TabCloseUI() {
    }

    /**
     * Main public constructor
     * @param pane ClosedTabbedPane object which is implemented by the class above
     */
    public TabCloseUI(ClosableTabbedPane pane) {
      this.tabbedPane = pane;
      this.tabbedPane.addMouseMotionListener(this);
      this.tabbedPane.addMouseListener(this);
    }

    /**
     * Empty method for mouse entered event
     * @param me MouseEvent
     */
    public void mouseEntered(MouseEvent me) {
    }
    /**
     * Empty method for mouse exited event
     * @param me MouseEvent
     */
    public void mouseExited(MouseEvent me) {
    }
    /**
     * Empty method for mouse pressed event
     * @param me MouseEvent
     */
    public void mousePressed(MouseEvent me) {
    }
    /**
     * Empty method for mouse clicked event
     * @param me MouseEvent
     */
    public void mouseClicked(MouseEvent me) {
    }

    /**
     * Empty method for mouse dragged event
     * @param me MouseEvent
     */
    public void mouseDragged(MouseEvent me) {  }

   /**
    * This method implements the actions of releasing a mouse press - the tab is removed
    * @param me MouseEvent
    */ 
    public void mouseReleased(MouseEvent me) { if (closeUnderMouse(me.getX(), me.getY())) {
        boolean isToCloseTab = ClosableTabbedPane.this.tabAboutToClose(this.selectedTab);
        if ((isToCloseTab) && (this.selectedTab > -1)) {
          this.tabbedPane.removeTabAt(this.selectedTab);
        }
        this.selectedTab = this.tabbedPane.getSelectedIndex();
      } }
    
    /**
     * This method implements the actions of a mouse moved event
     * @param me MouseEvent
     */
    public void mouseMoved(MouseEvent me)
    {
      this.meX = me.getX();
      this.meY = me.getY();
      if (mouseOverTab(this.meX, this.meY)) {
        controlCursor();
        this.tabbedPane.repaint();
      }
    }
    
    /**
     * This method creates a "Close" tool tip message
     */
    private void controlCursor() {
      if (this.tabbedPane.getTabCount() > 0)
        if (closeUnderMouse(this.meX, this.meY)) {
          this.tabbedPane.setCursor(new Cursor(12));
          if (this.selectedTab > -1)
            this.tabbedPane.setToolTipTextAt(this.selectedTab, "Close " + this.tabbedPane.getTitleAt(this.selectedTab));
        }
        else {
          this.tabbedPane.setCursor(new Cursor(0));
          if (this.selectedTab > -1)
            this.tabbedPane.setToolTipTextAt(this.selectedTab, "");
        }
    }

    /**
     * This method returns true or false if the cursor is above the close button
     * @param x integer horizontal position
     * @param y integer vertical position
     * @return Boolean value true or false
     */
    private boolean closeUnderMouse(int x, int y) {
      this.rectangle.x = this.closeX;
      this.rectangle.y = this.closeY;
      return this.rectangle.contains(x, y);
    }
    
    /**
     * This method draws the X button onto the tab
     * @param g Graphics object
     */
    public void paint(Graphics g)
    {
      int tabCount = this.tabbedPane.getTabCount();
      for (int j = 0; j < tabCount; j++)
        if (this.tabbedPane.getComponent(j).isShowing()) {
          int x = this.tabbedPane.getBoundsAt(j).x + this.tabbedPane.getBoundsAt(j).width - 8 - 5;
          int y = this.tabbedPane.getBoundsAt(j).y + 5;
          drawClose(g, x, y);
          break;
        }
      if (mouseOverTab(this.meX, this.meY))
        drawClose(g, this.closeX, this.closeY);
    }
    
    /**
     * This method draws a red x button if the mouse cursor is above it
     * @param g Graphics object
     * @param x integer horizontal position
     * @param y integer vertical position
     */
    private void drawClose(Graphics g, int x, int y)
    {
      if ((this.tabbedPane != null) && (this.tabbedPane.getTabCount() > 0)) {
        Graphics2D g2 = (Graphics2D)g;
        drawColored(g2, isUnderMouse(x, y) ? Color.RED : Color.WHITE, x, y);
      }
    }
    
    /**
     * This method draws a black line around the x button
     * @param g2 Graphics2D object
     * @param color Color object
     * @param x integer horizontal position
     * @param y integer vertical position
     */
    private void drawColored(Graphics2D g2, Color color, int x, int y) {
      g2.setStroke(new BasicStroke(5.0F, 1, 1));
      g2.setColor(Color.BLACK);
      g2.drawLine(x, y, x + 8, y + 8);
      g2.drawLine(x + 8, y, x, y + 8);
      g2.setColor(color);
      g2.setStroke(new BasicStroke(3.0F, 1, 1));
      g2.drawLine(x, y, x + 8, y + 8);
      g2.drawLine(x + 8, y, x, y + 8);
    }
    
    /**
     * Method returns true or false if button is under the mouse 
     * @param x integer horiztonal position
     * @param y integer vertical position
     * @return Boolean true or false
     */
    private boolean isUnderMouse(int x, int y)
    {
      if ((Math.abs(x - this.meX) < 8) && (Math.abs(y - this.meY) < 8))
        return true;
      return false;
    }
    
    /**
     * Method returns true or false if the mouse is over the tab
     * @param x integer horiztonal position
     * @param y integer vertical position
     * @return Boolean true or false
     */
    private boolean mouseOverTab(int x, int y) {
      int tabCount = this.tabbedPane.getTabCount();
      for (int j = 0; j < tabCount; j++)
        if (this.tabbedPane.getBoundsAt(j).contains(this.meX, this.meY)) {
          this.selectedTab = j;
          this.closeX = (this.tabbedPane.getBoundsAt(j).x + this.tabbedPane.getBoundsAt(j).width - 8 - 5);
          this.closeY = (this.tabbedPane.getBoundsAt(j).y + 5);
          return true;
        }
      return false;
    }
  }
}
