package Recordtest;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class RecordTest {

    public static void main(){
        try{
            AudioFormat audioFormat=new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0f, 16, 2, 4, 44100, false);

            DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, audioFormat);

            if(!AudioSystem.isLineSupported(dataInfo)){
                System.out.println("Not Supported!...");
            }
            TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(dataInfo);
            targetLine.open();

            JOptionPane.showMessageDialog(null, "Click OK to start recording.");
            targetLine.start();

            Thread audioRecorderThread = new Thread(){
                @Override
                public void run() {
                    AudioInputStream recordingStream = new AudioInputStream(targetLine);
                    File outputFile = new File("record.wav");
                    try{
                        AudioSystem.write(recordingStream, AudioFileFormat.Type.WAVE, outputFile);

                    }catch (Exception e){
                        System.out.println(e);
                    }
                    System.out.println("Stopped Recording");
                }
            };
            audioRecorderThread.start();
            JOptionPane.showMessageDialog(null, "Clock ok to stop recording.");
            targetLine.stop();
            targetLine.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }

}
