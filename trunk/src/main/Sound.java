package main;


import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

class Song 
{

    double length = 0;
    private AudioClip ac;

    public Song(String url) throws MalformedURLException, UnsupportedAudioFileException, IOException, Exception {
        File file = new File(url);
        ac = Applet.newAudioClip(file.toURL());        
        length = getLength(file.toURL());
    }
    
    public void play()
    {
       ac.play();
    }

    public static double getLength(URL path) throws Exception
    {
        AudioInputStream stream;
        stream = AudioSystem.getAudioInputStream(path);
        AudioFormat format = stream.getFormat();
        if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
            format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2, format.getFrameRate(), true); // big endian
            stream = AudioSystem.getAudioInputStream(format, stream);
        }
        DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(),
                ((int) stream.getFrameLength() * format.getFrameSize()));
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.close();
        return clip.getBufferSize()
                / (clip.getFormat().getFrameSize() * clip.getFormat().getFrameRate());
    }
}

class TrackList 
{
    
    static private int current_time, index, n = 2;  
    
    static private Song current;
    static String [] filenames = {"resources/track1.wav", "resources/track2.wav"};
    static private Song[] tracks = createList();
    
    static public Song[] createList()
    {
        Song [] mas = null;
        try
        {
            mas = new Song[n];
            for(int i = 0; i < n; i++)
            {
                mas[i] = new Song(filenames[i]);
            }
            index = 0;
            current_time = (int)(System.nanoTime() / 1000000000);
            current = mas[index];
            current.play();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return mas;
    }
    
    static public void update()
    {
        int time = (int)(System.nanoTime() / 1000000000);
        if(time - current_time >= current.length)
        {
            index = (index + 1)%n;
            current = tracks[index];
            current.play();
            current_time = time;
        }
    }
}
