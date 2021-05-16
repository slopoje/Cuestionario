package cuestionario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PasarCuestionario {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int numeroPreguntasTotal = 0;
        int numeroPreguntasCuestionario = 0;
        int numeroPreguntaActual;
        int respuesta;
        int acertadas = 0, falladas = 0, puntuacion = 0, record = 0;
        boolean error;
        boolean errorFicheroPreguntas = true;
        boolean errorFicheroRecord = true;

        List<Pregunta> preguntas = null;

        try ( FileInputStream fis = new FileInputStream("preguntas.dat");  ObjectInputStream ois = new ObjectInputStream(fis)) {

            preguntas = (List<Pregunta>) ois.readObject();
            errorFicheroPreguntas = false;

        } catch (FileNotFoundException e) {
            System.out.println("ERROR, no encuentro el fichero de preguntas");
        } catch (IOException e) {
            System.out.println("ERROR, no puedo acceder al fichero de preguntas");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR, no encuentro la clase");
        }

        try ( FileInputStream fis = new FileInputStream("record.dat");  ObjectInputStream ois = new ObjectInputStream(fis)) {

            record = (int) ois.readObject();
            errorFicheroRecord = false;

        } catch (FileNotFoundException e) {
            System.out.println("ERROR, no encuentro el fichero de record");
        } catch (IOException e) {
            System.out.println("ERROR, no puedo acceder al fichero de record");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR, no encuentro la clase");
        }

        if (errorFicheroPreguntas || errorFicheroRecord) {
            System.out.println("No puedo pasarte el cuestionario, hay errores en los ficheros ¿Seguro que están creados?");
        } else {
            numeroPreguntasTotal = preguntas.size();

            System.out.println("¿Te atreves con un cuestionario de cultura general?");
            System.out.println("El record está ahora mismo en " + record + "% de aciertos.");

            do {
                error = true;
                try {
                    System.out.print("En mi lista tengo " + numeroPreguntasTotal + " preguntas ¿Cuantas quieres que te haga? ");
                    numeroPreguntasCuestionario = teclado.nextInt();
                    teclado.nextLine();
                    if (numeroPreguntasCuestionario < 0) {
                        System.out.println("¡No te puedo hacer un número negativo de preguntas!");
                    } else if (numeroPreguntasCuestionario > numeroPreguntasTotal) {
                        System.out.println("¡No te puedo hacer más preguntas de las que tengo en mi lista de preguntas!");
                    } else {
                        error = false;
                    }
                } catch (InputMismatchException e) {
                    teclado.nextLine();
                    System.out.println("¡Debes introducir un número entero!");
                }
            } while (error);

            System.out.print("Entendido, te haré " + numeroPreguntasCuestionario + " preguntas, pulsa [ENTER] para empezar...");
            teclado.nextLine();

            Random aleatorio = new Random();
            for (int i = 0; i < numeroPreguntasCuestionario; ++i) {
                System.out.println("");
                do {
                    numeroPreguntaActual = aleatorio.nextInt(numeroPreguntasTotal);
                } while (preguntas.get(numeroPreguntaActual).getHaSalidoYa());
                preguntas.get(numeroPreguntaActual).setHaSalidoYa(true);

                System.out.println(preguntas.get(numeroPreguntaActual).toString());
                System.out.print("Respuesta:");
                respuesta = teclado.nextInt() - 1;
                if (respuesta == preguntas.get(numeroPreguntaActual).getCorrecta()) {
                    acertadas++;
                    System.out.println("¡Acertaste!");
                    Sonido.exclamacion();
                } else {
                    falladas++;
                    System.out.println("Has fallado, la respuesta correcta era "
                            + preguntas.get(numeroPreguntaActual).getTextoRespuestaCorrecta());
                    Sonido.chord();
                }
            }
            System.out.println("");
            System.out.println("Preguntas acertadas:" + acertadas);
            System.out.println("Preguntas falladas:" + falladas);
            puntuacion = 100 * acertadas / numeroPreguntasCuestionario;
            System.out.println("Tu puntuacion es:" + puntuacion);

            if (record < puntuacion) {
                System.out.println("¡Nuevo record!");
                Sonido.tada();

                try ( FileOutputStream fos = new FileOutputStream("record.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(puntuacion);
                } catch (FileNotFoundException ex) {
                    System.out.println("ERROR, no encuentro el fichero de record");
                } catch (IOException ex) {
                    System.out.println("ERROR, no puedo acceder al fichero de record");
                }

            }
        }
    }
}
