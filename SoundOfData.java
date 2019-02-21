import java.awt.Color;
import java.time.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import sound.*;
import image.*;
import java.util.Arrays;

class SoundOfData {

// soundForRow method signature & body

  public static Sound soundForRow(CSV csv, int rowIndex, Sound clip){
    int amplitude = Integer.parseInt(csv.getData(rowIndex,4));
    if (amplitude > 50) {
      return Sound.scaleVolume(clip,20.0);
    }
    else
        return Sound.scaleVolume(clip,5.5);
  }

// dataToSound method signature & body

  public static Sound dataToSound(CSV csv, Sound clip){
  int originalSize = clip.getNumSamples();
  int csvTotalRows = csv.getNumRows();
  int csvSize = csvTotalRows * originalSize;
  int[] newArray = new int[csvSize];
  Sound newSound = new Sound(newArray);
  for (int i = 1; i < csvTotalRows; i +=1){
    newSound = Sound.mix(newSound, soundForRow(csv, i, clip), originalSize * (i-1));
    }
    return newSound;
    }

// examples

public static void main(String[] args){

// variable declarations
  int[] exampleClip = {100,125,102,100,102};
  Sound newSound = new Sound(exampleClip);
  String myNoiseString = "Date,Time,Lat,Lon,Wind,Pressure\nAug 1,11:00 GMT,13.1,-55.1,35,1008\nAug 1,12:00 GMT,13.1,-55.1,35,1007\nAug 1,20:00 GMT,13.0,-55.8,40,1004\nAug 1,01:00 GMT,12.9,-56.5,40,1005\nAug 1,02:00 GMT,13.0,-57.4,40,1005";
  String myNoiseString2 = "Date,Time,Lat,Lon,Wind,Pressure\nAug 1,11:00 GMT,13.1,-55.1,35,1008\nAug 1,12:00 GMT,13.1,-55.1,35,1007\nAug 1,20:00 GMT,13.0,-55.8,40,1004";
  String franklinNoiseString = Media.readFile("data/franklin.csv");
  String mariaNoiseString = Media.readFile("data/maria.csv");
  CSV franklinNoise = new CSV(franklinNoiseString);
  CSV myNoise2 = new CSV(myNoiseString2);
  CSV mariaNoise = new CSV(mariaNoiseString);
  CSV myNoise = new CSV(myNoiseString);
  int[] windSound = Media.readSound("sounds/wind.wav");
  Sound windNoise = new Sound(windSound);
  Sound croppedWindNoise = Sound.crop(windNoise, 0,100000);

// soundforRow small examples

  Sound soundForRow1 = soundForRow(myNoise, 2, newSound);
  int[] exampleSound1 = {550,687,550,649,561};
  System.out.println("soundForRow1:  " + soundForRow1.toString());
  System.out.println("soundForRow1 expect:   " + "Sound[samples=[5]@...");
  System.out.println("soundForRow1 samples:  " + Arrays.toString(soundForRow1.getSamples()));
  System.out.println("soundForRow1 samples expect:  " + Arrays.toString(exampleSound1));


  Sound soundForRow2 = soundForRow(myNoise2, 2, newSound);
  int[] exampleSound2 = {550,687,550,649,561};
  System.out.println("soundForRow2:  " + soundForRow2.toString());
  System.out.println("soundForRow2 expect:   " + "Sound[samples=[5]@...");
  System.out.println("soundForRow2 samples:  " + Arrays.toString(soundForRow2.getSamples()));
  System.out.println("soundForRow2 samples expect:  " + Arrays.toString(exampleSound2));

// soundForRow large examples

 Sound soundForRow3 = soundForRow(mariaNoise, 4, croppedWindNoise);
  int[] exampleSound3 = {550,687,550,649,561};
  System.out.println("soundForRow3:  " + soundForRow3.toString());
  System.out.println("soundForRow3 expect:   " + "Sound[samples=[5]@...");
  System.out.println("soundForRow3 samples:  " + Arrays.toString(soundForRow3.getSamples()));
  System.out.println("soundForRow3 samples expect:  " + Arrays.toString(exampleSound3));
Media.explore(soundForRow3.getSamples());

  Sound soundForRow4 = soundForRow(franklinNoise, 4, croppedWindNoise);
  int[] exampleSound4 = {550,687,550,649,561};
  System.out.println("soundForRow4:  " + soundForRow4.toString());
  System.out.println("soundForRow4 expect:   " + "Sound[samples=[5]@...");
  System.out.println("soundForRow4 samples:  " + Arrays.toString(soundForRow4.getSamples()));
  System.out.println("soundForRow4 samples expect:  " + Arrays.toString(exampleSound4));
  Media.explore(soundForRow4.getSamples());

// dataToSound small examples

Sound dataToSound1 = dataToSound(myNoise, newSound);
  int[] dataSound1 = {550,687,561,550,561};
  System.out.println("dataToSound1:  " + dataToSound1.toString());
  System.out.println("dataToSound1 expect:   " + "Sound[samples=[25]@...");
  System.out.println("dataToSound1 samples:  " + Arrays.toString(dataToSound1.getSamples()));


  Sound dataToSound2 = dataToSound(myNoise2, newSound);
  int[] dataSound2 = {550,687,56,550,561};
  System.out.println("dataToSound2:  " + dataToSound2.toString());
  System.out.println("dataToSound2 expect:   " + "Sound[samples=[15]@...");
  System.out.println("dataToSound2 samples:  " + Arrays.toString(dataToSound2.getSamples()));



// dataToSound large examples

  Sound dataToSound3 = dataToSound(mariaNoise, croppedWindNoise);
  int[] dataSound3 = {2000,2500,2040,2000,2040};
  System.out.println("dataToSound3:  " + dataToSound3.toString());
  System.out.println("dataToSound3 expect:   " + "Sound[samples=[600]@...");
  System.out.println("dataToSound3 samples:  " + Arrays.toString(dataToSound3.getSamples()));
  Media.explore(dataToSound3.getSamples());



Sound dataToSound4 = dataToSound(franklinNoise, croppedWindNoise);
  int[] dataSound4 = {550,687,561,550,561};
  System.out.println("dataToSound4:  " + dataToSound4.toString());
  System.out.println("dataToSound4 expect:   " + "Sound[samples=[160]@...");
  System.out.println("dataToSound4 samples:  " + Arrays.toString(dataToSound4.getSamples()));
  Media.explore(dataToSound4.getSamples());



CSV jose = new CSV(Media.readFile("data/jose.csv"));
  Sound windClip = Sound.crop(dataToSound3,30,50);
  Sound mySound = dataToSound(jose, windClip);
  Media.saveSound(mySound.getSamples(), "sounds/hurricane.wav");


  }

  }
