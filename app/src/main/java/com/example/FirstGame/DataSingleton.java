package com.example.FirstGame;

public class DataSingleton {
    private static DataSingleton instance;
    private String takim;
    private int skor1;
    private int skor2;

    private DataSingleton(){

    }

    public static synchronized DataSingleton getInstance(){
        if(instance == null){
            instance = new DataSingleton();
        }
        return instance;
    }

    public static void setInstance(DataSingleton instance) {
        DataSingleton.instance = instance;
    }

    public String getTakim() {
        return takim;
    }

    public void setTakim(String takim) {
        this.takim = takim;
    }

    public int getSkor1() {
        return skor1;
    }

    public void setSkor1(int skor) {
        this.skor1 = skor;
    }

    public int getSkor2() {
        return skor2;
    }

    public void setSkor2(int skor2) {
        this.skor2 = skor2;
    }
}
