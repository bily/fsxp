// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ZRibbon.java

package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.FSXPRibbonUI;
import com.zbluesoftware.fsxp.ribbon.ui.ZRibbonUI;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon:
//            ZToggleButton, ZRibbonTask, ZRibbonBand, ZPopupGallery

public class ZRibbon extends JComponent
{

    public ZRibbon()
    {
        taskAL = new ArrayList();
        regularComponentAL = new ArrayList();
        taskToggleAL = new ArrayList();
        bandAL = new ArrayList();
        currentlySelectedTask = null;
        taskBG = new ButtonGroup();
        alignment = 2;
        taskHM = new HashMap();
        updateUI();
    }

    public Component add(Component comp, int index)
    {
        throw new UnsupportedOperationException();
    }

    public void add(Component comp, Object constraints, int index)
    {
        throw new UnsupportedOperationException();
    }

    public void add(Component comp, Object constraints)
    {
        throw new UnsupportedOperationException();
    }

    public Component add(Component comp)
    {
        regularComponentAL.add(comp);
        return super.add(comp);
    }

    private Component addInternal(Component comp)
    {
        return super.add(comp);
    }

    public void remove(Component comp)
    {
        regularComponentAL.remove(comp);
        super.remove(comp);
    }

    public JToggleButton addTask(String name, final ZRibbonTask task)
    {
        taskAL.add(task);
        ZToggleButton taskToggleButton = new ZToggleButton(name);
        taskBG.add(taskToggleButton);
        taskToggleAL.add(taskToggleButton);
        addInternal(taskToggleButton);
        taskHM.put(task, taskToggleButton);
        if(taskAL.size() == 1)
        {
            setSelectedTask(task);
            taskToggleButton.setSelected(true);
        }
        taskToggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                ZPopupGallery.hidePopups(null);
				SwingUtilities.invokeLater(new Runnable() { 
                    public void run()
                    {
                        setSelectedTask(task);
                    }
				});
            }
        });
        for(int i = 0; i < task.getBandCount(); i++)
        {
            ZRibbonBand panel = task.getBand(i);
            addInternal(panel);
            if(taskAL.size() > 1)
                panel.setVisible(false);
        }
        return taskToggleButton;
    }

    public void removeTask(ZRibbonTask task)
    {
        int index = taskAL.indexOf(task);
        removeTask(index);
    }

    public void removeTask(int index)
    {
        if(index >= 0)
        {
            ZRibbonTask toRemove = (ZRibbonTask)taskAL.get(index);
            taskAL.remove(toRemove);
            if(currentlySelectedTask == toRemove)
            {
                if(--index < 0)
                    index++;
                if(index < taskAL.size())
                    setSelectedTask((ZRibbonTask)taskAL.get(index));
            }
            JToggleButton taskToggleButton = (JToggleButton)taskToggleAL.get(index);
            taskToggleAL.remove(taskToggleButton);
            taskToggleAL.remove(taskToggleButton);
            taskBG.remove(taskToggleButton);
            remove(taskToggleButton);
        }
    }

    public int getTaskCount()
    {
        return taskAL.size();
    }

    public ZRibbonTask getTask(int index)
    {
        return (ZRibbonTask)taskAL.get(index);
    }

    public void setSelectedTask(ZRibbonTask task)
    {
        ZRibbonBand panel;
        for(Iterator i$ = bandAL.iterator(); i$.hasNext(); panel.setVisible(false))
            panel = (ZRibbonBand)i$.next();

        bandAL.clear();
        for(int i = 0; i < task.getBandCount(); i++)
        {
            panel = task.getBand(i);
            panel.setVisible(true);
            bandAL.add(panel);
        }

        currentlySelectedTask = task;
        ((JToggleButton)taskHM.get(task)).setSelected(true);
        revalidate();
        repaint();
    }

    public void setSelectedTask(int taskIndex)
    {
        int taskCount = getTaskCount();
        if(taskIndex < 0 || taskIndex >= taskCount)
        {
            return;
        } else
        {
            setSelectedTask((ZRibbonTask)taskAL.get(taskIndex));
            return;
        }
    }

    public int getSelectedTaskIndex()
    {
        return taskAL.indexOf(currentlySelectedTask);
    }

    public ZRibbonTask getSelectedTask()
    {
        return currentlySelectedTask;
    }

    public void setUI(ZRibbonUI ui)
    {
        super.setUI(ui);
    }

    public void updateUI()
    {
        if(UIManager.get(getUIClassID()) != null)
            setUI((ZRibbonUI)UIManager.getUI(this));
        else
            setUI(new FSXPRibbonUI());
    }

    public ZRibbonUI getUI()
    {
        return (ZRibbonUI)ui;
    }

    public String getUIClassID()
    {
        return "ZRibbonUI";
    }

    public ArrayList getRegularComponentAL()
    {
        return regularComponentAL;
    }

    public ArrayList getBandAL()
    {
        return bandAL;
    }

    public ArrayList getTaskToggleAL()
    {
        return taskToggleAL;
    }

    public int getAlignment()
    {
        return alignment;
    }

    public void setAlignment(int alignment)
    {
        this.alignment = alignment;
        doLayout();
        repaint();
    }

    private ArrayList taskAL;
    private ArrayList regularComponentAL;
    private ArrayList taskToggleAL;
    private ArrayList bandAL;
    private ZRibbonTask currentlySelectedTask;
    private HashMap taskHM;
    private ButtonGroup taskBG;
    private int alignment;
    private static final String uiClassID = "ZRibbonUI";
}