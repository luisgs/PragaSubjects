import java.util.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author busta
 */
public class pruebasmias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        ListaReuniones Reu = new ListaReuniones();
//        GregorianCalendar Fecha11 = new GregorianCalendar(1,1,2010,15,30);
//        GregorianCalendar Fecha12 = new GregorianCalendar(1,1,2010,16,30);
//        Reu.agregarReunion("aaa", 10, Fecha11, Fecha12);
//        System.out.println(Reu.Fechainicio("aaa").get(Calendar.HOUR_OF_DAY));
//        System.out.println(Reu.Fechafin("aaa").get(Calendar.HOUR_OF_DAY));
//
        Reservas Tar = new Reservas();
        String S = "pipa";
        int Cap = 5;
        int P = 12;
        boolean n;
        System.out.println("");
        GregorianCalendar Fecha11 = new GregorianCalendar(1,1,2010,15,30);
        GregorianCalendar Fecha12 = new GregorianCalendar(1,1,2010,16,30);
        GregorianCalendar Fecha21 = new GregorianCalendar(1,1,2010,17,30);
        GregorianCalendar Fecha22 = new GregorianCalendar(1,1,2010,18,30);
        GregorianCalendar Fecha31 = new GregorianCalendar(1,1,2010,16,00);
        GregorianCalendar Fecha32 = new GregorianCalendar(1,1,2010,17,00);
        System.out.println("1"+Tar.Agregar_Un_Cliente("aaa", "xxx", "Direcc1"));
        System.out.println("2"+Tar.Agregar_Un_Cliente("bbb", "yyy", "Direcc2"));
        System.out.println("3"+Tar.Agregar_Un_Cliente("ccc", "zzz", "Direcc3"));
        System.out.println("4"+Tar.Agregar_Sala_Reunion("Sala1", 15, 10));
        System.out.println("5"+Tar.Agregar_Sala_Reunion("Sala2", 20, 10));
        //System.out.println(Tar.Fechas_Solapadas(Fecha21, Fecha22, Fecha21, Fecha22));
        //Tar.Agregar_Una_Reunion("Reu1", 10, Fecha11, Fecha12, "aaa");
        n = Tar.Agregar_Una_Reunion("Reu1", 10, Fecha11, Fecha12, "aaa");
        System.out.println("6"+n);



        n = Tar.Agregar_Una_Reunion("Reu6", 10, Fecha21, Fecha22, "bbb");
        System.out.println("7"+n);
        n = Tar.Agregar_Una_Reunion("Reu3", 10, Fecha11, Fecha12, "ccc");
        System.out.println("8"+n);
        n = Tar.Agregar_Una_Reunion("Reu2", 10, Fecha21, Fecha22, "aaa");
        System.out.println("9"+n);
    }

}
