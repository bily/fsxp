
package com.zbluesoftware.fsxp.ui;
import com.zbluesoftware.fsxp.menu.HistoryPopupMenu;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class HistoryItem extends JPanel
  implements MouseListener
{
  private Vector listeners;
  private JLabel changeDataLabel;
  private JLabel objectDataLabel;
  private JLabel airportDataLabel;
  private JLabel timeDataLabel;
  private JLabel changeLabel;
  private JLabel objectLabel;
  private JLabel airportLabel;
  private JLabel timeLabel;
  private boolean colorChange;
  public static String BLANK_ITEM = "----";
  public static Color HIGHLIGHT_COLOR = new Color(255, 255, 204);
  public HistoryItem()
  {
    this.listeners = new Vector();
    this.colorChange = true;
    setLayout(new GridBagLayout());
    setBackground(Color.lightGray);
    this.changeLabel = new JLabel("Change:");
    this.changeLabel.setFont(Utilities.LABEL_FONT);
    this.changeLabel.setForeground(Color.black);
    this.changeLabel.setToolTipText("A description of the change");
    this.changeLabel.setEnabled(false);
    this.changeLabel.addMouseListener(this);
    this.changeDataLabel = new JLabel(BLANK_ITEM);
    this.changeDataLabel.setFont(Utilities.LABEL_FONT);
    this.changeDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
    this.changeDataLabel.setEnabled(false);
    this.changeDataLabel.addMouseListener(this);
    this.objectLabel = new JLabel("Object:");
    this.objectLabel.setFont(Utilities.LABEL_FONT);
    this.objectLabel.setForeground(Color.black);
    this.objectLabel.setToolTipText("The object the change applies to");
    this.objectLabel.setEnabled(false);
    this.objectLabel.addMouseListener(this);
    this.objectDataLabel = new JLabel(BLANK_ITEM);
    this.objectDataLabel.setFont(Utilities.LABEL_FONT);
    this.objectDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
    this.objectDataLabel.setEnabled(false);
    this.objectDataLabel.addMouseListener(this);
    this.airportLabel = new JLabel("Airport:");
    this.airportLabel.setFont(Utilities.LABEL_FONT);
    this.airportLabel.setForeground(Color.black);
    this.airportLabel.setToolTipText("The airport the change applies to");
    this.airportLabel.setEnabled(false);
    this.airportLabel.addMouseListener(this);
    this.airportDataLabel = new JLabel(BLANK_ITEM);
    this.airportDataLabel.setFont(Utilities.LABEL_FONT);
    this.airportDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
    this.airportDataLabel.setEnabled(false);
    this.airportDataLabel.addMouseListener(this);
    this.timeLabel = new JLabel("Time:");
    this.timeLabel.setFont(Utilities.LABEL_FONT);
    this.timeLabel.setForeground(Color.black);
    this.timeLabel.setToolTipText("The time the change occured");
    this.timeLabel.setEnabled(false);
    this.timeLabel.addMouseListener(this);
    this.timeDataLabel = new JLabel(BLANK_ITEM);
    this.timeDataLabel.setFont(Utilities.LABEL_FONT);
    this.timeDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
    this.timeDataLabel.setEnabled(false);
    this.timeDataLabel.addMouseListener(this);
    Utilities.addComponent(this, this.changeLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0D, 0D);
    Utilities.addComponent(this, this.changeDataLabel, 1, 0, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0D);
    Utilities.addComponent(this, this.objectLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0D, 0D);
    Utilities.addComponent(this, this.objectDataLabel, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0D);
    Utilities.addComponent(this, this.airportLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0D, 0D);
    Utilities.addComponent(this, this.airportDataLabel, 1, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0D);
    Utilities.addComponent(this, this.timeLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0D, 0D);
    Utilities.addComponent(this, this.timeDataLabel, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0D);
    addMouseListener(this);
  }
  public void setChangeData(String text)
  {
    this.changeDataLabel.setText(text);
  }
  public void setObjectData(String text)
  {
    this.objectDataLabel.setText(text);
  }
  public void setAirportData(String text)
  {
    this.airportDataLabel.setText(text);
  }
  public void setTimeData(String text)
  {
    this.timeDataLabel.setText(text);
  }
  public void setLabelsEnabled(boolean state)
  {
    this.changeDataLabel.setEnabled(state);
    this.objectDataLabel.setEnabled(state);
    this.airportDataLabel.setEnabled(state);
    this.timeDataLabel.setEnabled(state);
    this.changeLabel.setEnabled(state);
    this.objectLabel.setEnabled(state);
    this.airportLabel.setEnabled(state);
    this.timeLabel.setEnabled(state);
    if (!(state))
      setBackground(Color.lightGray);
  }
  private void displayPopup(Component component, Point point)
  {
    if (this.changeLabel.isEnabled())
    {
      this.colorChange = false;
      firePropertyChange("setTrigger", "", "");
      HistoryPopupMenu.getInstance().show(component, point.x, point.y);
    }
  }
  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    if (!(this.listeners.contains(listener)))
      this.listeners.add(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    this.listeners.remove(listener);
  }
  protected void firePropertyChange(String propertyName, Object oldValue, Object newValue)
  {
    Vector list;
    int i;
    super.firePropertyChange(propertyName, oldValue, newValue);
    synchronized (this)
    {
      if (this.listeners == null)
      {
        return;
      }
      list = (Vector)this.listeners.clone();
    }
    if (list.size() == 0)
      return;
    PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
    i = list.size() - 1;
    while (true)
    {
      if (i >= 0)
        return;
      ((PropertyChangeListener)list.elementAt(i)).propertyChange(event);
      --i;
    }
  }
  public void mouseClicked(MouseEvent event)
  {
    if (event.getClickCount() == 2)
      firePropertyChange("displayDetails", "", "");
  }
  public void mousePressed(MouseEvent event)
  {
    if (event.isPopupTrigger())
      displayPopup((Component)event.getSource(), event.getPoint());
  }
  public void mouseReleased(MouseEvent event)
  {
    if (event.isPopupTrigger())
      displayPopup((Component)event.getSource(), event.getPoint());
  }
  public void mouseEntered(MouseEvent event)
  {
    if ((this.colorChange) && (this.changeLabel.isEnabled()))
      setBackground(HIGHLIGHT_COLOR);
  }
  public void mouseExited(MouseEvent event)
  {
    if ((this.colorChange) && (this.changeLabel.isEnabled()))
      setBackground(Color.lightGray);
    else if (this.changeLabel.isEnabled())
      this.colorChange = true;
  }
}