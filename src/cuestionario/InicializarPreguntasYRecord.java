package cuestionario;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InicializarPreguntasYRecord {

    public static void main(String[] args) {

        List<Pregunta> preguntas = new ArrayList<Pregunta>();
        Pregunta pregunta;
        String[] respuestas;

        respuestas = new String[]{"1492", "1520", "1898", "1710"};
        pregunta = new Pregunta("¿En qué año se descubrió América?", respuestas, 0);
        preguntas.add(pregunta);

        respuestas = new String[]{"Verdadero", "Falso"};
        pregunta = new Pregunta("El protagonista de Blade Runner es Harrison Ford", respuestas, 0);
        preguntas.add(pregunta);

        respuestas = new String[]{"Isabel y Carlos", "Juana y Fernando", "Isabel y Fernando"};
        pregunta = new Pregunta("Los Reyes Católicos se llamaban...", respuestas, 2);
        preguntas.add(pregunta);

        respuestas = new String[]{"Francisco López", "Francisco Ibáñez", "Francisco Gutiérrez"};
        pregunta = new Pregunta("El autor de Mortadelo y Filemón es...", respuestas, 1);
        preguntas.add(pregunta);

        respuestas = new String[]{"dBASE", "Clipper", "MySQL"};
        pregunta = new Pregunta("El primer sistema de gestión de base de datos para PC fue...", respuestas, 0);
        preguntas.add(pregunta);

        respuestas = new String[]{"7", "5", "9"};
        pregunta = new Pregunta("El número de musas es...", respuestas, 2);
        preguntas.add(pregunta);

        System.out.println("Creando fichero de preguntas... ");
        try ( FileOutputStream fos = new FileOutputStream("preguntas.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(preguntas);
            System.out.println("OK");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR, fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("ERROR de entrada-salida del fichero.");
        }

        System.out.println("Creando fichero de record... ");
        try ( FileOutputStream fos = new FileOutputStream("record.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(0);
            System.out.println("OK");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR, fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("ERROR de entrada-salida del fichero.");
        }

    }

}
