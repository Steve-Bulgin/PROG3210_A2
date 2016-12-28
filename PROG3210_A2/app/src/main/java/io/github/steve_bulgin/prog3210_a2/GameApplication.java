package io.github.steve_bulgin.prog3210_a2;

import android.app.Application;
import android.widget.Toast;

public class GameApplication extends Application{

    //Global Variables

    private String full_name_one;
    private String f_name_one;
    private String l_name_one;
    private String full_name_two;
    private String f_name_two;
    private String l_name_two;

    //Getter Setters
    //P1
    //FullName
    public String getFull_name_one(){
        return this.full_name_one;
    }

    public void setFull_name_one(String full_name_one){
        this.full_name_one=full_name_one;
    }

    //First name 1

    public String getF_name_one() { return this.f_name_one; }

    public void setF_name_one(String f_name_one) { this.f_name_one=f_name_one; }

    //Lastname 1
    public String getL_name_one(){
        return this.l_name_one;
    }

    public void setL_name_one(String l_name_one){
        this.l_name_one=l_name_one;
    }

    //P2
    //Fullname
    public String getFull_name_two(){
        return this.full_name_two;
    }

    public void setFull_name_two(String full_name_two){
        this.full_name_two=full_name_two;
    }

    //First 2

    public String getF_name_two() { return this.f_name_two; }

    public void setF_name_two(String f_name_two) { this.f_name_two=f_name_two; }

    //Lastname 2
    public String getL_name_two(){
        return this.l_name_two;
    }

    public void setL_name_two(String l_name_two){
        this.l_name_two=l_name_two;
    }

    public void onCreate(){
        super.onCreate();

    }


}
