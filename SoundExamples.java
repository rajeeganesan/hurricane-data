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


class SoundExamples{
  public static void main(String[] args){
    int[] example1 = {3,4,5,3,2,4,5,6,4,4,3,2};
    int[] example2 = {3,5,4,2,4,3,5,6,7,3,2,1,1,4,2};
    int[] mixExample1 = {2,2,2};
    Sound mixSound = new Sound(mixExample1);
    Sound newSound = new Sound(example1);
    Sound newSound2 = new Sound(example2);


    // getnumSamples

    int numSamples1 = newSound.getNumSamples();
    int numSamples1Expected = 12;
    System.out.println("numSamples1:  " + numSamples1);
    System.out.println("numSamples1 expect: " + numSamples1Expected);

    int numSamples2 = newSound2.getNumSamples();
    int numSamples2Expected = 15;
    System.out.println("numSamples2:   " + numSamples2);
    System.out.println("numSamples2 expect:  " + numSamples2Expected);

    // getSample

    int getSample1 = newSound.getSample(3);
    int getSample1Expected = 3;
    System.out.println("getSample1:  " + getSample1);
    System.out.println("getSample1 expect: " + getSample1Expected);

    int getSample2 = newSound2.getSample(3);
    int getSample2Expected = 2;
    System.out.println("getSample2:  " + getSample2);
    System.out.println("getSample2 expect:  " + getSample2Expected);

    // getSamples

    int[] getSamples1 = newSound.getSamples();
    int[] getSamples1Expected = {3,4,5,3,2,4,5,6,4,4,3,2};
    System.out.println("getSamples1:   " + Arrays.toString(getSamples1));
    System.out.println("getSamples1 expect:   " + Arrays.toString(getSamples1Expected));

    int[] getSamples2 = newSound2.getSamples();
    int[] getSamples2Expected = {3,5,4,2,4,3,5,6,7,3,2,1,1,4,2};
    System.out.println("getSamples2:   " + Arrays.toString(getSamples2));
    System.out.println("getSamples2 expect:   " + Arrays.toString(getSamples2Expected));

    // toString (FIX ME)

    String toString1 = newSound.toString();
    String toString1Expected = "Sound[samples=[12]@...]";
    System.out.println("toString1:   " + toString1);
    System.out.println("toString1 expect" + toString1Expected);

    String toString2 = newSound2.toString();
    String toString2Expected = "Sound[samples=[15]@...]";
    System.out.println("toString2:   " + toString2);
    System.out.println("toString2 expect" + toString2Expected);

    // crop

    Sound crop1 = Sound.crop(newSound,3,6);
    int[] crop1SamplesExpect = {4,5,3};
    System.out.println("crop1:  " + crop1.toString());
    System.out.println("crop1 expect:   " + "Sound[samples=[3]@...");
    System.out.println("crop1 samples:  " + Arrays.toString(crop1.getSamples()));
    System.out.println("crop1 samples expect:  " + Arrays.toString(crop1SamplesExpect));

    Sound crop2 = Sound.crop(newSound2,3,6);
    int[] crop2SamplesExpect = {5,4,2};
    System.out.println("crop2:  " + crop2.toString());
    System.out.println("crop2 expect:   " + "Sound[samples=[3]@..]");
    System.out.println("crop2 samples:  " + Arrays.toString(crop2.getSamples()));
    System.out.println("crop2 samples expect:  " + Arrays.toString(crop2SamplesExpect));

   // mix

    Sound mix1 = Sound.mix(newSound, mixSound, 3);
    int[] mix1SamplesExpect = {3,4,5,5,4,6,5,6,4,4,3,2};
    System.out.println("mix1:  " + mix1.toString());
    System.out.println("mix1 expect:  " + "Sound[samples=[3]@...]");
    System.out.println("mix1 samples:  " + Arrays.toString(mix1.getSamples()));
    System.out.println("mix1 samples expect  " + Arrays.toString(mix1SamplesExpect));

    Sound mix2 = Sound.mix(newSound2, mixSound, 7);
    int[] mix2SamplesExpect = {3,5,4,2,4,3,5,8,9,5,2,1,1,4,2};
    System.out.println("mix2:  " + mix2.toString());
    System.out.println("mix2 expect:  " + "Sound[samples=[3]@..]");
    System.out.println("mix2 samples:  " + Arrays.toString(mix2.getSamples()));
    System.out.println("mix2 samples expect  " + Arrays.toString(mix2SamplesExpect));

    // silence

    Sound silence1 = Sound.silence(3);
    int[] silenceSamplesExpect = {0,0,0};
    System.out.println("silence1:  " + silence1.toString());
    System.out.println("silence1 expect:  " + "Sound[samples=[3]@...]");
    System.out.println("silence1 samples:   " + Arrays.toString(silence1.getSamples()));
    System.out.println("silence1 samples expect:  " + Arrays.toString(silenceSamplesExpect));

    Sound silence2 = Sound.silence(5);
    int[] silenceSamples2Expect = {0,0,0,0,0};
    System.out.println("silence2:  " + silence2.toString());
    System.out.println("silence2 expect:  " + "Sound[samples=[5]@...]");
    System.out.println("silence2 samples:   " + Arrays.toString(silence2.getSamples()));
    System.out.println("silence2 samples expect:  " + Arrays.toString(silenceSamples2Expect));

    // scaleVolume

    Sound scaleVolume1 = Sound.scaleVolume(newSound, 2.0);
    int[] scaleVolume1SamplesExpect = {6,8,10,6,4,8,10,12,8,8,6,4};
    System.out.println("scaleVolume1:  " + scaleVolume1.toString());
    System.out.println("scaleVolume1 expect:  " + "Sound[samples=[12]@...]");
    System.out.println("scaleVolume1 samples:  " + Arrays.toString(scaleVolume1.getSamples()));
    System.out.println("scaleVolume1 samples expect: " + Arrays.toString(scaleVolume1SamplesExpect));

    Sound scaleVolume2 = Sound.scaleVolume(newSound2, 0.7);
    int[] scaleVolume2SamplesExpect = {2,3,2,1,2,2,3,4,4,2,1,0,0,2,1};
    System.out.println("scaleVolume2:  " + scaleVolume2.toString());
    System.out.println("scaleVolume2 expect:  " + "Sound[samples=[12]@...]");
    System.out.println("scaleVolume2 samples:  " + Arrays.toString(scaleVolume2.getSamples()));
    System.out.println("scaleVolume2 samples expect: " + Arrays.toString(scaleVolume2SamplesExpect));
  }
}
