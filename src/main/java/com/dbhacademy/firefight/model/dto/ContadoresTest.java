package com.dbhacademy.firefight.model.dto;

public class ContadoresTest {

    private int current;
    private int aciertos;
    private int errores;
    private int numPreguntasPorTemas;
    private int numPreguntasTotal;

    public ContadoresTest() {
        this.aciertos = 0;
        this.errores = 0;
        this.current = 0;
        this.numPreguntasPorTemas = 0;
        this.numPreguntasTotal = 0;
    }

    /**
     * Constructor de copia
     * @param contadoresTest
     */
    public ContadoresTest(ContadoresTest contadoresTest) {
        this.aciertos = contadoresTest.getAciertos();
        this.errores = contadoresTest.getErrores();
        this.current = contadoresTest.getCurrent();
        this.numPreguntasPorTemas = contadoresTest.getNumPreguntasPorTemas();
        this.numPreguntasTotal = contadoresTest.getNumPreguntasTotal();
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getErrores() {
        return errores;
    }

    public void setErrores(int errores) {
        this.errores = errores;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getNumPreguntasPorTemas() {
        return numPreguntasPorTemas;
    }

    public void setNumPreguntasPorTemas(int numPreguntasPorTemas) {
        this.numPreguntasPorTemas = numPreguntasPorTemas;
    }

    public int getNumPreguntasTotal() {
        return numPreguntasTotal;
    }

    public void setNumPreguntasTotal(int numPreguntasTotal) {
        this.numPreguntasTotal = numPreguntasTotal;
    }

    public void incrementaCurrent(int cantidad){
        this.setCurrent(this.getCurrent()+cantidad);
    }

    @Override
    public String toString() {
        return "ContadoresTest{" +
                "current=" + current +
                ", aciertos=" + aciertos +
                ", errores=" + errores +
                ", numPreguntasPorTemas=" + numPreguntasPorTemas +
                ", numPreguntasTotal=" + numPreguntasTotal +
                '}';
    }
}
