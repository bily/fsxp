// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProgressDialog.java

package flightsim.shiptraffic.btcgui;

import flightsim.shiptraffic.ProgressAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class ProgressDialog extends JDialog
    implements ProgressAdapter, PropertyChangeListener
{

    public ProgressDialog(JFrame parent, String title, String message)
    {
        super(parent, false);
        closed = false;
        value = 0;
        currentMax = 100;
        setTitle(title);
        setSize(300, 130);
        setDefaultCloseOperation(0);
        setLocationRelativeTo(parent);
        JPanel jpb = new JPanel();
        jpb.setLayout(new BoxLayout(jpb, 3));
        label = new JLabel(message == null ? "" : message, 2);
        jpb.add(label);
        pb = new JProgressBar(0, 100);
        jpb.add(pb);
        pane = new JOptionPane(jpb, 1, -1, null, new Object[] {
            "Cancel"
        });
        pane.addPropertyChangeListener(this);
        setContentPane(pane);
    }

    public void close()
    {
        setVisible(false);
        closed = true;
    }

    public void increment()
    {
        setValue(value + 1);
    }

    public void setSection(String note, int nops)
    {
        label.setText(note);
        currentMax = nops;
        setValue(0);
    }

    public void setValue(int nv)
    {
        if(closed)
            return;
        if(!isVisible())
            setVisible(true);
        value = nv;
        int diff = (int)(((double)value / (double)currentMax) * 100D);
        pb.setValue(diff);
    }

    public boolean isClosed()
    {
        return closed;
    }

    public void propertyChange(PropertyChangeEvent evt)
    {
        String prop = evt.getPropertyName();
        if(isVisible() && evt.getSource() == pane && ("value".equals(prop) || "inputValue".equals(prop)))
        {
            Object value = pane.getValue();
            if(value == JOptionPane.UNINITIALIZED_VALUE)
                return;
            pane.setValue(JOptionPane.UNINITIALIZED_VALUE);
            setVisible(false);
            close();
        }
    }

    private JProgressBar pb;
    private JOptionPane pane;
    private JLabel label;
    private boolean closed;
    private int value;
    private int currentMax;
}
