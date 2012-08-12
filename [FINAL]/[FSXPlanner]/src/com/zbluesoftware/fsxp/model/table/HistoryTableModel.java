package com.zbluesoftware.fsxp.model.table;

import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.HistoryModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

public class HistoryTableModel extends AbstractTableModel {

    public HistoryTableModel()
    {
        gelFormat.setMaximumFractionDigits( 2 );
    }

    private ArrayList data = new ArrayList();
    private String[] columnNames = { "Object", "Changed Property", "Before Change", "After Change", "Current Value" };
    private NumberFormat gelFormat = NumberFormat.getInstance();

    public int getRowCount()
    {
        return data.size();
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public Object getValueAt(int row, int column)
    {
        if( row < 0 || row >= data.size() )
            return "";
        else
        {
            HashMap hashMap = (HashMap) data.get( row );

            switch( column )
            {
                case 0:
                    return hashMap.get( "Object" );
                case 1:
                    return (String) hashMap.get( "Property" );
                case 2:
                    return hashMap.get( "Before" );
                case 3:
                    return hashMap.get( "After" );
                case 4:
                    return hashMap.get( "Current" );
                default:
                    return "";
            }
        }
    }

    public ArrayList getData()
    {
        return data;
    }

    public void setModelData(HistoryModel historyModel)
    {
        Object hashMap;

        data.clear();
        hashMap = new HashMap();
        ((HashMap) hashMap).put( "Property", historyModel.getPropertyDesc() );
        ((HashMap) hashMap).put( "Before", historyModel.getOldValue() );
        ((HashMap) hashMap).put( "After", historyModel.getNewValue() );
        ((HashMap) hashMap).put( "Object", historyModel.getBaseModel().getModelName() );
        ((HashMap) hashMap).put( "Current", "" );
        if( historyModel.getGetMethodName() != null )
        {
            BaseModel baseModel = historyModel.getBaseModel();

            try
            {
                Object method = baseModel.getClass().getMethod( historyModel.getGetMethodName() );
                Object currentValue = ((java.lang.reflect.Method) method).invoke( baseModel );

                ((HashMap) hashMap).put( "Current", currentValue.toString() );
            }
            catch( NoSuchMethodException nosuchmethodexception1 )
            {
                LogEngine.getInstance().log( nosuchmethodexception1 );
            }
            catch( IllegalAccessException illegalaccessexception1 )
            {
                LogEngine.getInstance().log( illegalaccessexception1 );
            }
            catch( java.lang.reflect.InvocationTargetException invocationtargetexception1 )
            {
                LogEngine.getInstance().log( (Throwable) invocationtargetexception1 );
            }
        }
        data.add( hashMap );
        fireTableDataChanged();
    }
}