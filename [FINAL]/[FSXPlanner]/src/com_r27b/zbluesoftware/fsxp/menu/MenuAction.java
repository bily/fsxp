// 
// SourceAgain (TM) Professional v1.10k (C) 2003 Ahpah Software Inc
// 

package com.zbluesoftware.fsxp.menu;

import com.zbluesoftware.fsxp.event.MenuActionEvent;
import com.zbluesoftware.fsxp.event.MenuActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public class MenuAction extends AbstractAction {

    public MenuAction(String text, Icon icon)
    {
        super( text, icon );
    }

    private Vector listeners = new Vector();

    public synchronized void addMenuListener(MenuActionListener listener)
    {
        if( !listeners.contains( listener ) )
            listeners.addElement( listener );
    }

    public synchronized void removeMenuListener(MenuActionListener listener)
    {
        listeners.removeElement( listener );
    }

    public void actionPerformed(ActionEvent event)
    {
        Object menuActionEvent = this;
        Vector list = null;

        synchronized( menuActionEvent )
        {
            list = (Vector) listeners.clone();
        }
        if( list.size() != 0 )
        {
            int i = 0;

            menuActionEvent = new MenuActionEvent( this, getValue( "ActionCommandKey" ) );
            for( i = list.size() - 1; i >= 0; --i )
                ((MenuActionListener) list.elementAt( i )).menuItemSelected( (MenuActionEvent) menuActionEvent );
        }
    }
}