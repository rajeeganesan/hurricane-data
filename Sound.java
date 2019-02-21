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

class Sound {
  //fields
  private final int[] samples;

  // constructor method
   Sound(int[] samples){
    this.samples = samples;
}

  // instance methods

  // getNumSamples
  public int getNumSamples(){
    return this.samples.length;
  }

// getSample
  public int getSample(int index){
    return this.samples[index];
  }

  // getSamples
  public int[] getSamples(){
    return this.samples;
  }

 // toString
  public String toString(){
    int len = this.samples.length;
    String samplesRef = this.samples.toString();
    String address = samplesRef.substring(samplesRef.indexOf("@"));
    return "Sound[samples=[" + len + "]" + address + "]";
  }

  // static methods

  // crop
  public static Sound crop(Sound original, int start, int end){
    int size = (end - start);
    int[] newArray = new int[size];

    for(int i = 0; i < size; i+=1){
      newArray[i] = original.getSample(start + i);
    }
    Sound newSound = new Sound(newArray);
    return newSound;
  }

  // mix
  public static Sound mix(Sound background, Sound foreground, int index){
    int[] backgroundArray = background.getSamples();
    int[] foregroundArray = foreground.getSamples();
    int size = background.getNumSamples();
    int foregroundSize = foreground.getNumSamples();
    int[] mixedArray = new int[size];

    for(int i = 0; i < size; i +=1){
      if (i < index || i >= (index + foregroundSize)) {
      mixedArray[i] = backgroundArray[i];
    }
     else {
       mixedArray[i] = backgroundArray[i] + foregroundArray[i-index];
    }
  }
    Sound mixedSound = new Sound(mixedArray);
    return mixedSound;

  }
  // silence
  public static Sound silence(int numSamples){
    int size = numSamples;
    int[] newArray = new int[size];

    for(int i = 0; i < size; i +=1){
      newArray[i] = 0;
    }
    Sound newSound = new Sound(newArray);
    return newSound;
  }

  // scaleVolume
  public static Sound scaleVolume(Sound original, double ratio){
    Sound copy = new Sound(original.getSamples());
    int[] samples = copy.getSamples();
    int size = copy.getNumSamples();
    int[] newArray = new int[size];
    for(int i = 0; i < size; i +=1){
      newArray[i] = (int)(samples[i]*ratio);
    }
    Sound newSound = new Sound(newArray);
    return newSound;
  }
}
