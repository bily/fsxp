// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GateNameItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class GateNameItem extends Item
{

    public GateNameItem()
    {
        this("gateName");
    }

    public GateNameItem(String name)
    {
        this.name = name;
        dataType = "Word";
        offset = 0;
        length = 2;
        hexData = null;
        decodedData = null;
    }

    public void setDecodedData()
    {
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = tempHex.length() - 1; i >= 0; i -= 2)
            buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

        int tempData = Long.valueOf(buffer.toString(), 16).intValue();
        if(tempData >= 0 && tempData <= 37)
            decodedData = names[tempData];
    }

    public static String names[];

    static 
    {
        names = new String[38];
        names[0] = "NONE";
        names[1] = "PARKING";
        names[2] = "N_PARKING";
        names[3] = "NE_PARKING";
        names[4] = "E_PARKING";
        names[5] = "SE_PARKING";
        names[6] = "S_PARKING";
        names[7] = "SW_PARKING";
        names[8] = "W_PARKING";
        names[9] = "NW_PARKING";
        names[10] = "GATE";
        names[11] = "DOCK";
        names[12] = "GATE_A";
        names[13] = "GATE_B";
        names[14] = "GATE_C";
        names[15] = "GATE_D";
        names[16] = "GATE_E";
        names[17] = "GATE_F";
        names[18] = "GATE_G";
        names[19] = "GATE_H";
        names[20] = "GATE_I";
        names[21] = "GATE_J";
        names[22] = "GATE_K";
        names[23] = "GATE_L";
        names[24] = "GATE_M";
        names[25] = "GATE_N";
        names[26] = "GATE_O";
        names[27] = "GATE_P";
        names[28] = "GATE_Q";
        names[29] = "GATE_R";
        names[30] = "GATE_S";
        names[31] = "GATE_T";
        names[32] = "GATE_U";
        names[33] = "GATE_V";
        names[34] = "GATE_W";
        names[35] = "GATE_X";
        names[36] = "GATE_Y";
        names[37] = "GATE_Z";
    }
}
