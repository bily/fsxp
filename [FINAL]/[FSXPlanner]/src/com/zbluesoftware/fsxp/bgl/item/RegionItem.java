// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegionItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class RegionItem extends Item
{

    public RegionItem()
    {
        this("region", true);
    }

    public RegionItem(String name, boolean shifted)
    {
        this.name = name;
        this.shifted = shifted;
        dataType = "2Word";
        offset = 0;
        length = 2;
        hexData = null;
        decodedData = null;
    }

    public void setDecodedData()
    {
        char chArray[] = new char[3];
        int num = 0;
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = tempHex.length() - 1; i >= 0; i -= 2)
            buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

        int tempData = Long.valueOf(buffer.toString(), 16).intValue();
        if(tempData == 0)
        {
            decodedData = "";
            return;
        }
        if(shifted)
            tempData >>= 5;
        for(int i = 0; i < 3; i++)
        {
            num = tempData % 38;
            chArray[i] = letters[num];
            tempData /= 38;
        }

        buffer = new StringBuffer(new String(chArray));
        decodedData = buffer.reverse().toString().trim();
    }

    private boolean shifted;
    public static char letters[];

    static 
    {
        letters = new char[38];
        letters[0] = ' ';
        letters[1] = ' ';
        letters[2] = '0';
        letters[3] = '1';
        letters[4] = '2';
        letters[5] = '3';
        letters[6] = '4';
        letters[7] = '5';
        letters[8] = '6';
        letters[9] = '7';
        letters[10] = '8';
        letters[11] = '9';
        letters[12] = 'A';
        letters[13] = 'B';
        letters[14] = 'C';
        letters[15] = 'D';
        letters[16] = 'E';
        letters[17] = 'F';
        letters[18] = 'G';
        letters[19] = 'H';
        letters[20] = 'I';
        letters[21] = 'J';
        letters[22] = 'K';
        letters[23] = 'L';
        letters[24] = 'M';
        letters[25] = 'N';
        letters[26] = 'O';
        letters[27] = 'P';
        letters[28] = 'Q';
        letters[29] = 'R';
        letters[30] = 'S';
        letters[31] = 'T';
        letters[32] = 'U';
        letters[33] = 'V';
        letters[34] = 'W';
        letters[35] = 'X';
        letters[36] = 'Y';
        letters[37] = 'Z';
    }
}
