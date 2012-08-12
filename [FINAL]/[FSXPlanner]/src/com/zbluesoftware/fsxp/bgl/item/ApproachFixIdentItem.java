// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachFixIdentItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ApproachFixIdentItem extends Item
{

    public ApproachFixIdentItem()
    {
        this("fixIdent", true, 0, 8);
    }

    public ApproachFixIdentItem(String name, boolean shifted, int startBit, int endBit)
    {
        this.name = name;
        this.shifted = shifted;
        this.startBit = startBit;
        this.endBit = endBit;
        dataType = "DWord";
        offset = 0;
        length = 4;
        hexData = null;
        decodedData = null;
    }

    public void setDecodedData()
    {
        char chArray[] = new char[endBit - startBit];
        int num = 0;
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = tempHex.length() - 1; i >= 0; i -= 2)
            buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

        long tempData = Long.parseLong(buffer.toString(), 16);
        StringBuffer binary = new StringBuffer(Long.toBinaryString(tempData));
        for(int i = binary.length(); i < 32; i++)
            binary.insert(0, "0");

        tempData = Long.valueOf(binary.substring(startBit, endBit), 2).intValue();
        if(tempData == 0L)
        {
            decodedData = "";
            return;
        }
        if(shifted)
            tempData >>= 5;
        for(int i = 0; i < endBit - startBit; i++)
        {
            num = (int)(tempData % 38L);
            chArray[i] = letters[num];
            tempData /= 38L;
        }

        buffer = new StringBuffer(new String(chArray));
        decodedData = buffer.reverse().toString().trim();
    }

    private boolean shifted;
    private int startBit;
    private int endBit;
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
