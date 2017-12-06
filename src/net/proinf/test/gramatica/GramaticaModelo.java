/*
 */
package net.proinf.test.gramatica;


import java.util.Observable;
import net.proinf.gramatica.*;

/**
 * Modelo para probar las clases del paquete "gramatica"
 * en un interfaz de usuario
 */
public class GramaticaModelo extends Observable  {
    
    private Palabra palabra;
    private Palabras palabras;
    private int numeroElementos = 0;
    private Palabra concepto;
    
    public GramaticaModelo() {
        palabra = new Palabra();
        concepto = new Palabra();        
    }    

    public Palabra getPalabra () {
        return palabra;
    }
    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;        
        setChanged();
        notifyObservers();
    }
       
    public Palabras getPalabras() {
        return palabras;
    }
    public void setPalabras(Palabras palabras) {
        this.palabras = palabras;
        setChanged();
        notifyObservers();
    }
     
    public int getNumeroElementos() {
        return numeroElementos;
    }
    public void setNumeroElementos(int numeroElementos) {
        this.numeroElementos = numeroElementos;
        setChanged();
        notifyObservers();
    }
    
    public Palabra getConcepto() {
        return concepto;       
    }
    public void setConcepto (Palabra concepto) {
        if (concepto.es("")) concepto = new Palabra("palabra");
        this.concepto = concepto;
        setChanged();
        notifyObservers();
    }
  
}
