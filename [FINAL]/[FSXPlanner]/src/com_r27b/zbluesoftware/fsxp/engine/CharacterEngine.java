// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CharacterEngine.java

package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class CharacterEngine
{

    private CharacterEngine()
    {
    }

    public static CharacterEngine getInstance()
    {
        if(engine == null)
            engine = new CharacterEngine();
        return engine;
    }

    public void drawCharacter(char character, GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        switch(character)
        {
        case 115: // 's'
            draws(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 116: // 't'
            drawt(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 111: // 'o'
            drawo(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 108: // 'l'
            drawl(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 48: // '0'
            draw0(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 49: // '1'
            draw1(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 50: // '2'
            draw2(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 51: // '3'
            draw3(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 52: // '4'
            draw4(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 53: // '5'
            draw5(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 54: // '6'
            draw6(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 55: // '7'
            draw7(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 56: // '8'
            draw8(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 57: // '9'
            draw9(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 76: // 'L'
            drawL(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 82: // 'R'
            drawR(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;

        case 67: // 'C'
            drawC(generalPath, rotatePoint, basePoint, heading, scale, secondary);
            break;
        }
    }

    private void draws(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(15F * scale)), (float)(basePoint.getY() - (double)(24F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(24F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(12F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(15F * scale)), (float)(basePoint.getY() - (double)(12F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(15F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void drawt(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(24F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(15F * scale)), (float)(basePoint.getY() - (double)(24F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(7.5F * scale)), (float)(basePoint.getY() - (double)(24F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(7.5F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void drawo(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(24F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(15F * scale)), (float)(basePoint.getY() - (double)(24F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(15F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void drawl(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(24F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(15F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw0(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw1(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(9F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(12F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(12F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw2(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(50F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(47.5F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(10F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw3(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(50F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(13F * scale)), (float)(basePoint.getY() - (double)(37.5F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(25F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw4(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(10F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(15F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(15F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(13F * scale)), (float)(basePoint.getY() - (double)(30F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(13F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw5(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(40F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(40F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw6(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(16F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(47F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(25F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(25F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw7(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw8(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(40F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(5F * scale)), (float)(basePoint.getY() - (double)(35F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(30F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(30F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(15F * scale)), (float)(basePoint.getY() - (double)(35F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(40F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(5F * scale)), (float)(basePoint.getY() - (double)(35F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(15F * scale)), (float)(basePoint.getY() - (double)(35F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void draw9(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(4F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(13F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(35F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(35F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void drawL(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void drawR(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(30F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(30F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(10F * scale)), (float)(basePoint.getY() - (double)(30F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private void drawC(GeneralPath generalPath, java.awt.geom.Point2D.Float rotatePoint, java.awt.geom.Point2D.Float basePoint, float heading, float scale, boolean secondary)
    {
        java.awt.geom.Point2D.Float rotatedBasePoint = basePoint;
        if(secondary)
            rotatedBasePoint = Utilities.rotatePoint(rotatePoint, basePoint, heading);
        java.awt.geom.Point2D.Float characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(50F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.moveTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)(basePoint.getY() - (double)(60F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)basePoint.getX(), (float)basePoint.getY()), heading);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)basePoint.getY()), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
        characterPoint = Utilities.rotatePoint(rotatePoint, new java.awt.geom.Point2D.Float((float)(basePoint.getX() + (double)(20F * scale)), (float)(basePoint.getY() - (double)(10F * scale))), heading);
        if(secondary)
            characterPoint = Utilities.rotatePoint(rotatedBasePoint, characterPoint, 180F);
        generalPath.lineTo(characterPoint.x, characterPoint.y);
    }

    private static CharacterEngine engine = null;

}
