package cuestionario;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * Clase para reproducir los sonidos del sistema Windows 10.
 *
 * @author IES Aguadulce - FP Informática - Módulo Programación.
 */
public class Sonido {

    /**
     * Constante que guarda el directorio con los ficheros de sonido.
     */
    private static final String PATH_SONIDOS = "C:\\Windows\\Media\\";

    /**
     * Reproduce el fichero "chord.wav"
     */
    public static void chord() {
        Sonido.play(Sonido.PATH_SONIDOS + "chord.wav");
    }

    /**
     * Reproduce el fichero "tada.wav"
     */
    public static void tada() {
        Sonido.play(Sonido.PATH_SONIDOS + "tada.wav");
    }

    /**
     * Reproduce el fichero "Windows Exclamation.wav"
     */
    public static void exclamacion() {
        Sonido.play(Sonido.PATH_SONIDOS + "Windows Exclamation.wav");
    }

    /**
     * Reproduce el fichero "Windows Hardware Remove.wav"
     */
    public static void error() {
        Sonido.play(Sonido.PATH_SONIDOS + "Windows Hardware Remove.wav");
    }

    /**
     * Reproduce el sonido del fichero que se pasa como parámetro.
     *
     * @param pathToFicheroSonido Ruta completa al fichero de sonido.
     */
    private static void play(String pathToFicheroSonido) {

        try {
            File ficheroSonido = new File(pathToFicheroSonido);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(ficheroSonido);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
            Thread.sleep(700);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException | InterruptedException e) {
            //No se puede reproducir el sonido
        }
    }
}
