package net.proinf.test.gramatica;

import net.proinf.gramatica.*;

public class GramaticaTest
{
    public static void main (String[] args) {
        
        //print (""+new Palabra("base"));
        //new Silabas(new Palabra("ciudad"));
        //print(""+new Palabra("águila").generoAntepuesto());
        
        if (args.length == 0)
            print("Indica los tests a pasar: [P]lural, [S]ílabas, [G]énero, [U]nión");
        else for (int i=0; i<args.length; ++i) {    
            switch (args[i].toUpperCase().charAt(0)) {
                case 'P': test_plural(); break;
                case 'S': test_silabas(); break;
                case 'G': test_genero(); break;
                case 'U': test_union(); break;
                default: print("Parámetro '" + args[i] + "' incorrecto.");
            }
        }
    }

    /////////////////////////////////////////////
    // TESTS
    
    public final static String[] palabrasBellas = {
        "esternocleidomastoideo","electroencefalografista","guineoecuatoriano",
        "azahar","libélula","albahaca","susurro","mandarina","lapislázuli","cigüeña",
        "gaznápiro","zangolotino","zaguán","damajuana","botarate","cachivache",
        "alcancía","cáspita","caramba",
    };

    public final static String[] palabrasEnSingular = {"tótem","leal","verdad",
        "manta","café","camión","lápiz","veloz","tabú","baladí","sol","a","yo",
        "sí","casa","fe","plató","canon","examen",
        "mes","dios","autobús","paréntesis","dosis","espécimen","fax",
        "iceberg","clímax","raíz",
    };
    public final static String[] palabrasEnSingular_referencias = {"tótemes","leales","verdades",
        "mantas","cafés","camiones","lápices","veloces","tabúes","baladíes","soles","aes","yoes",
        "síes","casas","fes","platós","cánones","exámenes",
        "meses","dioses","autobuses","paréntesis","dosis","especímenes","faxes",
        "icebergs","clímax","raíces",
    };
    
    public final static String[] palabrasSilabas = { 
        "pelapatatas","mano","pena","seca","salón","gimnasio","acróstico",
        "calcificación","atraen","playa","padres","premiación","transgresión",
        "institución","constitución","entrega","espronceda","transgredir",
        "instrucción","llevar","carretera","perro","chicharrón","adherir","que",
        "hembra","querida","alquimista","estupenda","teléfono","koala","poeta",
        "cecilia","suelo","cuidado","caigo","aura","peine","europa","cuarto",
        "diez","puente","canción","suntuoso","ciudad","maría","raíz",
        "acentúa","semiautomático","ampliéis","contagiáis","paella","geometría",
        "oír","caída","construir","construidos","instruir","instruida",
        "búho","prohíbe","pastel","lápiz","camión","café","devuélvemelo",
        "préstamelo","examen","miau","agua","hada","águila"        
    };
    public final static String[] palabrasSilabas_referencias = {
        "pe-la-pa-ta-tas","ma-no","pe-na","se-ca","sa-lón","gim-na-sio","a-crós-ti-co",
        "cal-ci-fi-ca-ción","a-tra-en","pla-ya","pa-dres","pre-mia-ción","trans-gre-sión",
        "ins-ti-tu-ción","cons-ti-tu-ción","en-tre-ga","es-pron-ce-da","trans-gre-dir",
        "ins-truc-ción","lle-var","ca-rre-te-ra","pe-rro","chi-cha-rrón","ad-he-rir","que",
        "hem-bra","que-ri-da","al-qui-mis-ta","es-tu-pen-da","te-lé-fo-no","ko-a-la","po-e-ta",
        "ce-ci-lia","sue-lo","cui-da-do","cai-go","au-ra","pei-ne","eu-ro-pa","cuar-to",
        "diez","puen-te","can-ción","sun-tuo-so","ciu-dad","ma-rí-a","ra-íz",
        "a-cen-tú-a","se-miau-to-má-ti-co","am-pliéis","con-ta-giáis","pa-e-lla","ge-o-me-trí-a",
        "o-ír","ca-í-da","cons-tru-ir","cons-tru-i-dos","ins-tru-ir","ins-tru-i-da",
        "bú-ho","pro-hí-be","pas-tel","lá-piz","ca-mión","ca-fé","de-vuél-ve-me-lo",
        "prés-ta-me-lo","e-xa-men","miau","a-gua","ha-da","á-gui-la"};

     public final static String[] palabrasFemeninas = {
        "abadesa","actriz","apotema","base","cafeína","casa","certidumbre",
        "dinamo","emperatriz","estupidez","flor",
        "heroína","legumbre","madre","mamá","mano","miel","muerte",
        "nación","perdiz","pesadez","poetisa","salud","seo","tesis","verdad"
     };
     public final static String[] palabrasMasculinas = {
         "abad","actor","alud","apotegma","árbol","café","camión","cinturón",
         "día","estigma","fantasma","frontis","idioma","inodoro","koala",
         "magma","mapa","olor","pedigrí","pelo","pez","poeta",
         "sofá","virus","volante"
         
     };
  
    /////////////////////////////////////////////
    // GRAMATICA

    public static void test_plural () {
        print("TEST plural");
        for (int i=0; i<palabrasEnSingular.length; ++i) { 
            String str = palabrasEnSingular[i];
            Palabra palabra = new Palabra(str);                    
            test(str,palabra.enPlural().toString(),palabrasEnSingular_referencias[i]);
        }    
    }
    public static void test_silabas () {
        print("TEST sílabas");
        for (int i=0; i<palabrasSilabas.length; ++i) {        
            Palabra palabra = new Palabra(palabrasSilabas[i]);
            Silabas silabas = new Silabas(palabra);
            String acento = silabas.acentuacion().toString();

            test(palabrasSilabas[i],silabas.toString(),palabrasSilabas_referencias[i],acento);
        }    
    }
    public static void test_genero() {
        print("TEST género");
        for (String palabra: palabrasFemeninas) {
            test(palabra,Gramatica.genero(palabra),"femenino");
        }
        for (String palabra: palabrasMasculinas) {
            test(palabra,Gramatica.genero(palabra),"masculino");
        }
    }
    
    public static void test_union() {
        print("TEST unión");        
        print(new Palabras ("categoría_producto").unirPluralesCapitalizando());
        print(new Palabras ("CategoríaProducto").unirPluralesGuionando());
        print(new Palabras ("categoría producto").unir(", ", null));
    }
    
    /////////////////////////////////////////////
    // FUNCIONES AUXILIARES

    private static void print(String mensaje) {
      System.out.println(mensaje);
    }
    private static void test(String cifra, String prueba, String referencia) {
      test(cifra,prueba,referencia,"");
    }
    private static void test(String cifra, String prueba, String referencia, String etc) {
      print( (prueba.equals(referencia)?"correcto":"ERROR") + ": " + cifra + " = " + prueba + " " + etc);
    }
    
    /////////////////////////////////////////////
    // INTERFAZ
    
    public static String palabras() {        
        /*return new Palabras(
          new Palabras(palabrasEnSingular).toString(),
          new Palabras(palabrasSilabas).toString(),
          new Palabras(palabrasFemeninas).toString(),
          new Palabras(palabrasMasculinas).toString()          
        ).toString();*/
        
        StringBuffer bafer = new StringBuffer();
        for (String palabra: palabrasBellas) bafer.append(palabra + " ");
        for (String palabra: palabrasEnSingular) bafer.append(palabra + " ");
        for (String palabra: palabrasSilabas) bafer.append(palabra + " ");
        for (String palabra: palabrasFemeninas) bafer.append(palabra + " ");
        for (String palabra: palabrasMasculinas) bafer.append(palabra + " ");        
        return bafer.toString();
    }

}

