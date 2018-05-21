package edu.fandm.aboak.android.millportapp;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

public class Place
{
    private double[] cords = new double[2];
    private int locImage;
    private String[] info = new String[3];

    public Place()
    {
        this.cords[0] = 40.0461;
        this.cords[1] = -76.3199;
        this.locImage = R.drawable.blue_line;
        this.info[0] = "Blue Line";
        this.info[1] = cords[0] + ", " + cords[1];
        this.info[2] = "The best place to eat on campus!";
    }

    public Place(double[] cords, String[] info, int image)
    {
        this.cords = cords;
        this.info = info;
        this.locImage = image;
    }

    public double[] getCords()
    {
        return(cords);
    }

    public void setCords(double[] newCords)
    {
        cords[0] = newCords[0];
        cords[1] = newCords[1];
    }

    public int getLocImage()
    {
        return(locImage);
    }

    public void setLocImage(int newImage)
    {
        locImage = newImage;
    }

    public String[] getInfo()
    {
        return(info);
    }

    public void setInfo(String[] newInfo)
    {
        info[0] = newInfo[0];
        info[1] = newInfo[1];
        info[2] = newInfo[2];
    }
}
