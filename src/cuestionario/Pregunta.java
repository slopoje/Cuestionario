package cuestionario;

import java.io.Serializable;

public class Pregunta implements Serializable {

    private String enunciado;
    private String[] respuestas;
    private int correcta;
    private boolean haSalidoYa;
    private static final long serialVersionUID = 1L;

    public Pregunta(String enunciado, String[] respuestas, int correcta) {
        this.enunciado = enunciado;
        this.respuestas = respuestas;
        this.correcta = correcta;
        this.haSalidoYa = false;
    }

    public boolean getHaSalidoYa() {
        return this.haSalidoYa;
    }

    public int getCorrecta() {
        return this.correcta;
    }
    
    public String getTextoRespuestaCorrecta(){
        return this.respuestas[this.correcta];
    }

    public void setHaSalidoYa(boolean haSalidoYa) {
        this.haSalidoYa = haSalidoYa;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();

        texto.append("PREGUNTA: " + this.enunciado + "\n");

        for (int i = 0; i < this.respuestas.length; i++) {
            texto.append("  ").append(i + 1).append(") ");
            texto.append(this.respuestas[i]);
            texto.append("\n");
        }

        return texto.toString();
    }
}
