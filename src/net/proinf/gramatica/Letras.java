package net.proinf.gramatica;

import java.util.Iterator;
import java.util.Observable;

/**
 * Un conjunto de letras forma una sílaba o una palabra
 *  
 * <p>Licencia: <a href="http://creativecommons.org/licenses/GPL/2.0/deed.es">Este software está sujeto a la CC-GNU GPL</a></p>
 * @author Francisco Cascales <fco@proinf.net>
 * @version 0.05, 24-dic-2007 - Inicio del proyecto
 * @version 0.07,  2-ene-2008 - Deriva de Observable para poder detectar los posibles cambios
 */
public abstract class Letras<T extends Letras> extends Observable implements Iterable<Letra> {
    protected StringBuffer bafer = null;    

    //////////////////////////////////////////////////
    // Iterador
    
    public Iterator<Letra> iterator() { return new LetrasIterator(); }
    private class LetrasIterator implements Iterator<Letra> {
        int posicion = 0;
        public boolean hasNext() { return posicion < bafer.length(); }
        public Letra next() { return letra(posicion++); }
        public void remove() { bafer.deleteCharAt(posicion--); }
    }   
    
    //////////////////////////////////////////////////
    // Métodos auxiliares
    
    private static String limpiar(String letras) {
        return letras.trim()/*.toLowerCase()*/;
    }       
    protected void requiereActualizacion(Object objeto) {
        setChanged();
        notifyObservers(objeto);
    }
        
    //////////////////////////////////////////////////
    // Constructor
    
    public Letras() {
        this("");
    }
    public Letras(String letras) {   
        bafer = new StringBuffer(limpiar(letras));     
    }
     
    //////////////////////////////////////////////////
    // Interfaz escritura
    
    public T cambiarPor (String letras) {        
        bafer = new StringBuffer(limpiar(letras));
        requiereActualizacion(this);
        return (T)this;
    }
    public T agregar (String sufijo) {
        bafer.append(sufijo);
        requiereActualizacion(this);
        return (T)this;
    }
    public T agregar (Letra letra) {
        bafer.append(letra.getChar());
        requiereActualizacion(this);
        return (T)this;
    }
    public T quitarUltimaLetra() {
        bafer.deleteCharAt(bafer.length()-1);
        requiereActualizacion(this);
        return (T)this;
    }         
    
    //////////////////////////////////////////////////
    // Interfaz lectura
        
    public Letra letra(int posicion) {
        return new Letra(this, posicion);
    }
    public Letra primeraLetra()   { return letra(0); }
    public Letra segundaLetra()   { return letra(1); }
    public Letra penultimaLetra() { return letra(bafer.length() - 2); }
    public Letra ultimaLetra()    { return letra(bafer.length() - 1);  }

    public Letra ultimaVocal() {
        for (int posicion=bafer.length()-1; posicion>=0; --posicion) {
            Letra letra = letra(posicion);
            if (letra.esVocal()) return letra;                
        }
        return null;
    }
    public boolean estaVacia() {
        return bafer.length() == 0;
    }
    @Override public String toString() {
        return bafer.toString();
    }
    public int numeroLetras() {
        return bafer.length();
    }

    public int numeroVocales () {            
        int cuentaVocales = 0;    
        for (Letra letra: this) if (letra.esVocal()) ++cuentaVocales;           
        return cuentaVocales;            
    }
    public boolean todoVocales() {
        for (Letra letra: this) if (letra.esConsonante()) return false;
        return true;
    }
    public boolean todoConsonantes() {
        for (Letra letra: this) if (letra.esVocal()) return false;
        return true;
    }
    public boolean hayVocales() {
        return numeroVocales() > 0;
    }    
    public boolean tieneAcento() {
        for (Letra letra: this) if (letra.estaAcentuada()) return true;
        return false;
    }
    public boolean acabaEn(String sufijo) {
        return bafer.toString().endsWith(sufijo);
    }
    public boolean acabaEn(String... sufijos) {
        String letras = bafer.toString();
        for (String sufijo: sufijos) if (letras.endsWith(sufijo)) return true;
        return false;
    }
    public boolean empiezaPor (String prefijo) {
        return bafer.toString().startsWith(prefijo);
    }
    public boolean empiezaPor (String... prefijos) {
        String letras = bafer.toString();
        for (String prefijo: prefijos) if (letras.startsWith(prefijo)) return true;
        return false;
    }
    public boolean es (String letras) { return bafer.toString().equals(letras); }
    public boolean es (T letras) { return bafer.toString().equals(letras.bafer.toString()); }
    public boolean es (String... lista) {
        for (String elemento: lista) if (elemento.equals(bafer.toString())) return true;            
        return false;
    }   

       
}
