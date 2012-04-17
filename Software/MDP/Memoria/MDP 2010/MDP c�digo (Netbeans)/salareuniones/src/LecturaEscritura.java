import java.io.*;
//import java.lang.*;
import java.util.*;
import java.text.*;





public class LecturaEscritura{
    //Atributos

    private FileReader fe;//Flujo de entrada
    private BufferedReader bf;    
    int nbytes; //Numero de Bytes leidos en el fichero    
    Reservas operaciones = new Reservas();
    //Escritura W = new Escritura();
    //Reuniones reuperiodo;
    ReunionesDia reudia;
    SalasDisp sdisp;
    SalasMes smes;
    Factura fact;
    int abierto = 0;
    private FileWriter fs;
    private BufferedWriter bw;
    private FileOutputStream fd;
    private DataOutputStream fichero;
   //String cadena = new String();

 /*=========================================================================
 =================================LECTURA===================================
=============================================================================*/

    public void Lectura_Fichero(String nombre,String salida) {
     try
     {
     //Abrimos el fichero y creamos el buffer de lectura.
     this.fe = new FileReader(nombre);
     this.bf = new BufferedReader(fe);

     Escritura_Fichero(salida);


     while (bf != null ) {
         //System.out.println("Llamamos a Leer_Linea"+bf);
             Leer_Linea();
         }
     try
            {
               fe.close();
               fd.close();
            }
            catch(IOException e)
            {
               //System.out.println("Error: al cerrar los ficheros");
            }
     //Lectura del Fichero
    }
    catch(FileNotFoundException ex)
     //Capturamos las excepciones, en este caso el fallo de lectura
    {
        System.out.println("El fichero " + nombre + " no existe");
    }
     

    }


    ////////////////////////////////////////////////////////////////////////
    /////////////////Formatos de Ordenes////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    private boolean formato_ns (String ns) {
        int tam = ns.length();
        char comillai, comillaf;
        comillai = ns.charAt(0);
        comillaf = ns.charAt(tam -1);

//Nombre sala con 1 caracter minimo y 10 maximo sin contar comillas
        if ((tam <= 12) && (tam >= 3)) {
            if ((comillai == '"') && (comillaf == '"')) {
                return true;
            }
            else
                return false;
        }
        else {
            return false;
        }

    }

   private boolean formato_ni (String ni) {
        int tam = ni.length();
        //System.out.println("EL tamaño del dni es " +tam);
        int dni;
        String  dni2;
        char letra;
        boolean resultado  = true;
        if (tam == 9) {
          dni2 = ni.substring(0, 8);
          letra = ni.charAt(8);
            //System.out.println("El dni es " + dni2);
                if (!Character.isLetter(letra)) {
                resultado = false;
                //System.out.println("La letra nif no es una letra");
                resultado = false;
                }
                else {

                     try {
                            dni = Integer.parseInt(dni2);
                     }
                     catch (NumberFormatException e) {
                            //System.out.println("EL formato del dni es incorrecto");
                            resultado =  false;
                     }
                 }
        }
        else {
            resultado = false;
        }
        return resultado;
    }

    private boolean formato_nc (String nc) {
        int tam = nc.length();
        char comillai, comillaf;
        comillai = nc.charAt(0);
        comillaf = nc.charAt(tam -1);

//Nombre del cliente  con 1 caracter minimo y 15 maximo sin contar comillas
        if ((tam <= 17) && (tam >= 3)) {
            if ((comillai == '"') && (comillaf == '"')) {
                return true;
            }
            else
                return false;
        }
        else {
            return false;
        }

    }

    private boolean formato_df (String nc) {
        int tam = nc.length();
        char comillai, comillaf;
        comillai = nc.charAt(0);
        comillaf = nc.charAt(tam -1);

//Nombre de la reuniom  con 1 caracter minimo y 50 maximo sin contar comillas
        if ((tam <= 52) && (tam >= 3)) {
            if ((comillai == '"') && (comillaf == '"')) {
                return true;
            }
            else
                return false;
        }
        else {
            return false;
        }

    }
    private boolean formato_nr (String nr) {
        int tam = nr.length();
        char comillai, comillaf;
        comillai = nr.charAt(0);
        comillaf = nr.charAt(tam -1);

//Nombre de la reunion  con 1 caracter minimo y 15 maximo sin contar comillas
        if ((tam <= 27) && (tam >= 3)) {
            if ((comillai == '"') && (comillaf == '"')) {
                return true;
            }
            else
                return false;
        }
        else {
            return false;
        }

    }

    private int dia_mes (int mes, int año) {
        int dias = -1;
       // System.out.println("El mes es " +mes);
        switch (mes){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                dias = 31;
                break;
            case 4: case 6: case 9: case 11:
                dias = 30;
                break;
                case 2:
                    //System.out.println("¿Bisiesto?");
                 //¿El año es bisiesto? Un año es bisiesto si es divisible por 4, excepto el último
                 // de cada siglo (aquel divisible por 100), salvo que este último sea divisible por 400.
                    if ((año % 4 == 0) && (año % 100 != 0) || (año % 400 == 0)){
                        dias = 29;
                        break;
                    }
                    else {
                        dias = 28;
                        break;
                    }
            default:
                //System.out.println("El mes introducido no es valido");
                break;

        }
        return dias;
    }

//private boolean formato_fecha_string(String fecha) {
//
//}

private boolean formato_fecha (int dia, int mes, int año) {
    if ((dia >= 1) && (dia <= dia_mes(mes,año))){
    return true;
    }
    else {
        return false;
    }

}



private boolean formato_hora (int hora, int min) {
    if ((hora < 9) || (hora > 18)) {
        return false;
    }
    else {
          if ((min < 0) || (min > 59)) {
              return false;
          }
          else {
               return true;
           }
        }
    }


// Formato de fechas "dd-mm-yyyy hh:mm"
private double resta_fechas (String fi, String ff ) {
    DateFormat dateformat = DateFormat.getDateInstance();
    java.util.GregorianCalendar FechaI = new java.util.GregorianCalendar();
    java.util.GregorianCalendar FechaF= new java.util.GregorianCalendar();
    //SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    //Date fecha = null;
    //Nota: Los meses en un Calendar son tratados de 0 a 11!
//    System.out.println("año :"+fi.substring(6,10));
//    System.out.println("dia :"+fi.substring(0,2));
//    System.out.println("mes :"+fi.substring(3,5));
//    System.out.println("hora :"+fi.substring(11,13));
//    System.out.println("minuto :"+fi.substring(14,16));
//
//    System.out.println("año :"+ff.substring(6,10));
//    System.out.println("dia :"+ff.substring(0,2));
//    System.out.println("mes :"+ff.substring(3,5));
//    System.out.println("hora :"+ff.substring(11,13));
//    System.out.println("minuto :"+ff.substring(14,16));


    FechaI.set(Integer.parseInt(fi.substring(6, 10)),Integer.parseInt(fi.substring(3, 5))-1,Integer.parseInt(fi.substring(0, 2)),Integer.parseInt(fi.substring(11,13)) , Integer.parseInt(fi.substring(14, 16)));
    FechaF.set(Integer.parseInt(ff.substring(6, 10)),Integer.parseInt(ff.substring(3, 5))-1,Integer.parseInt(ff.substring(0, 2)),Integer.parseInt(ff.substring(11,13)) , Integer.parseInt(ff.substring(14, 16)));
//     System.out.println("Date format " + dateformat.format(FechaI.getTime().getTime()) + "\n");
 //    System.out.println("Date format " + dateformat.format(FechaF.getTime().getTime()) + "\n");

      long diferencia = FechaF.getTime().getTime()-FechaI.getTime().getTime();
      double minutos = diferencia / (1000 * 60);
        long horas = (long) (minutos / 60);
        long minuto = (long) (minutos%60);
        long segundos = (long) diferencia % 1000;
        long dias = horas/24;
    //dateformat.format(FechaI.getTime());
    //dateformat.format(FechaF.getTime());
    //dateformat.format(FechaI.getTimeInMillis());
    //dateformat.format(FechaF.getTimeInMillis());


    //long diferencia = FechaF.getTime().getTime()-FechaI.getTime().getTime();
    //long diferencia = FechaF.getTimeInMillis() - FechaI.getTimeInMillis();
//    System.out.println("FechaF - Fecha I  :"+ diferencia);
    //double minutos = diferencia / (1000 * 60);

//   System.out.println("la diferencia de minutos es " + minutos );
//    System.out.println("la diferencia de horas es " + horas);
//    System.out.println("la diferencia de dias  es " + dias);
    return minutos;

}

//private boolean comprobarsubstring (String str, int inicio, int fin ) {
//    try {
//
//    } catch (Exception e) {
//    }
//}
//
////////////////////////////////////////////////////////////////////////
    /////////////////ESCRIBIR RESPUESTAS//////////////////////////////////
    ///////////////////////////////////////////////////////////////////////

public void AbrirFichero (String Nombre) {
        String cadena = new String();
    try {
//       System.out.println("leido LecturaEScritura");
            cadena = bf.readLine();
        }
        catch (IOException e) {
 //           System.out.println("Error: al leer linea en el fichero");
        }
        if (cadena != null) {
    Escritura_Fichero(Nombre);
}
}

    ///////////////////////////////////////////////////////////////
    /////////////////////LEER LINEA///////////////////////////////
    //////////////////////////////////////////////////////////////

    public void Leer_Linea () {
        String cadena = new String();
        StringTokenizer subcadena;
        String elemento = null;
        String messsss = "";
        int as = 0,cs = 0,ps = 0;
        String as2 = new String();//Leemos el numero de asistentes como String//
        String cs2 = new String();//Leemos la capacidad como String//
        String ps2 = new String();//Leemos el precio como String//
        int dia,dia2,mes,mes2,año,año2,hora,minuto;
        String df,nc,ni,nr,ns = new String();
        String stringfi,stringff,stringhi,stringhf,stringfihi,stringffhf = new String();
        GregorianCalendar ff = new GregorianCalendar();  //Fecha fin
        GregorianCalendar fi = new GregorianCalendar();  //Fecha inicio
        //GregorianCalendar hf = new GregorianCalendar();  //Hora  fin
        //GregorianCalendar hi = new GregorianCalendar();  //Hora inicio
        GregorianCalendar fm = new GregorianCalendar();  //fecha de mostrar datos
        GregorianCalendar me = new GregorianCalendar();  //Fecha con mes año
        //GregorianCalendar horai = new GregorianCalendar();  //Lo que me devuelve la funcion horaInicio       
        String fm2,me2 = new String();//Leemos la fecha como un string//
        char  barradiames,barramesaño,dospuntos;

//        System.out.println("entramos en leer linea");

        try
        {
            cadena = bf.readLine();
        }
        catch (IOException e)
        {
//           System.out.println("Error: al leer linea en el fichero");
            bf = null;
        }

 //       System.out.println("Hemos leido la linea");

        
        if (cadena==null)
        {
//            System.out.println("-----------------------FINDEFICHERO--------------");
            bf = null;
            
        }
        else
        {

            
//            System.out.println("Fichero leido y no nulo");//Quitar
            subcadena = new StringTokenizer(cadena);
            if (subcadena.hasMoreElements()) {
                elemento = subcadena.nextToken();
     //================AGREGAR SALA DE REUNIONES================================================//
                if (elemento.equals("ASR")) {
//                    System.out.println("EL primer parametro(ASR) coincide");//Quitar
                    if (!subcadena.hasMoreTokens()){
                        //fichero.writeBytes("ERROR: El mandato EPR necesita dos parámetros: np y nc"+ '\n');
//                          System.out.println("Faltan argumentos de entrada");//Quitar
                          Error_Parametros(10);                          
                        //Faltarian argumentos
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(1);
//                        return atrib;
                    }
                    else {
 //                       System.out.println("Leo el segundo argumento");//Quitar
                        //Leo el nombre de Sala y compruebo el formato//
                        ns = subcadena.nextToken();
                        while(!formato_ns(ns)){ //Solo si el formato es incorrecto
                            if (subcadena.hasMoreTokens()) {
                                ns = ns + " " + subcadena.nextToken();
                            }
                            else {
 //                               System.out.println("2 argumento incorrecto(NS)");//Quitar
                                Error_Parametros(11);
                            }
                        }
                        if (!subcadena.hasMoreTokens()){
 //                       System.out.println("Faltan argumentos de entrada");//Quitar
                        Error_Parametros(10);

                        }
                        else {

 //                       System.out.println("3 parametro");//Quitar
                        //Leo la capacidad de la Sala pero tendremos q transformalo a int
                        cs2 = subcadena.nextToken();
                        if (cs2.length() == 3) {
                            try {
                                cs = Integer.parseInt(cs2);
                            } catch ( NumberFormatException e) {
  //                              System.out.println("3 parametro no es un entero(cs)");//Quitar

                                  Error_Parametros(21);
                                  ////////return null;
                                //Error: el numero no es un entero
                                //atrib = new Atributos();
                                //atrib.setnum_error(3);
                                //return atrib;
                            }
                        }
                        else {
 //                           System.out.println("3 parametro es un entero pero no de 3 digitos(cs)");//Quitar
                            Error_Parametros(21);
                            ////////return null;
                        //El numero de digitos es mayor o menos al requerido
//                            atrib = new Atributos();
//                            atrib.setnum_error(4);
//                                return atrib;
                        }
                        if (!subcadena.hasMoreTokens()){
//                        System.out.println("Faltan argumentos de entrada");//Quitar
                        //Faltarian argumentos
                        //atrib = new Atributos();
                        //atrib.settarea(elemento);
                        //atrib.setnum_error(1);
                        //return atrib;
                        Error_Parametros(10);
                        ////////return null;
                    }
                        else {


 //                       System.out.println("Leo el precio");//Quitar
                        //Leo el precio por sala
                        ps2 = subcadena.nextToken();
                        if (ps2.length() == 2) {
                            try {
                                ps = Integer.parseInt(ps2);
                            } catch ( NumberFormatException e) {
   //                             System.out.println("4 parametro no es un entero(ps)");//Quitar
                                Error_Parametros(31);
                                ////////return null;
                                //Error: el numero no es un entero
                                //atrib = new Atributos();
                                //atrib.setnum_error(5);
                                //return atrib;
                            }
                            if (subcadena.hasMoreTokens()){
 //                       System.out.println("Sobran argumentos de entrada");//Quitar
                         Error_Parametros(10);
                        //Faltarian argumentos
                        //atrib = new Atributos();
                        //atrib.settarea(elemento);
                        //atrib.setnum_error(55);
                        //return atrib;
                         ////////return null;
                    }
                      else {
                                operaciones.Agregar_Sala_Reunion(ns, cs, ps);
                                      Asr_Correcto(ns);
//                                if (operaciones.Agregar_Sala_Reunion(ns, cs, ps)) {
//                                    Asr_Correcto(ns);
//                                    System.out.println("La sala se ha  creado");
//                                }
//                                else {
//                                    System.out.println("ERROR: La sala no ha podido ser creada");
//                                    Asr_Incorrecto(ns);
//                                }

//                             DESCOMENTADO//
      //              System.out.println("------FIN-------");
//                    atrib = new Atributos();
//                    atrib.settarea(elemento);
//                    atrib.setns(ns);
//                    atrib.setcs(cs);
//                    atrib.setps(ps);
//                    atrib.setnum_error(0);
//                    return atrib;
                      }
                        }
                        else {
//                            System.out.println("4 parametro es un entero pero no de 3 digitos");//Quitar
                            Error_Parametros(31);
                            //////return null;
                        //El numero de digitos es mayor o menos al requerido
//                            atrib = new Atributos();
//                                atrib.settarea(elemento);
//                                atrib.setnum_error(6);
//                                return atrib;
                        }
                        }
                        }
                    }
                }

                //================ELIMINAR SALA DE REUNIONES================================================//
                else if (elemento.equals("ESR")) {
//                    System.out.println("EL primer parametro(ESR) coincide");//Quitar
                    if (!subcadena.hasMoreTokens()){
 //                       System.out.println("Faltan argumentos de entrada");//Quitar
                        Error_Parametros(20);
                            //////return null;
                        //Faltarian argumentos
                        //atrib = new Atributos();
                        //atrib.settarea(elemento);
                        //atrib.setnum_error(1);
                        //return atrib;
                    }
                    else {
                        ns = subcadena.nextToken();
//                        System.out.println("Leemos el nombre de la sala");//Quitar
                        while(!formato_ns(ns)){ //Solo si el formato es incorrecto
                            if (subcadena.hasMoreTokens()) { //El nombre de la sala puede tener espacios en blanco
                                ns = ns + " " + subcadena.nextToken();
                            }
                            else {
  //                              System.out.println("2 argumento incorrecto");//Quitar
                                Error_Parametros(11);
                                //////return null;
                              //el argumento pasado es incorrecto;
//                                atrib = new Atributos();
//                                atrib.setnum_error(2);
//                                return atrib;
                            }
                        }
                    }
                           if (subcadena.hasMoreTokens()){
 //                       System.out.println("Sobran argumentos de entrada");//Quitar
                        Error_Parametros(20);
                        //////return null;
                        //Faltarian argumentos
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(55);
//                        return atrib;
                    }
                      else {
 //                   System.out.println("------FIN-------");
                        if (operaciones.Eliminar_Sala_Reunion(ns)){
//                                    System.out.println("ESR_CORRECTO");
                                    Esr_Correcto(ns);
                                }
                                else {
 //                                   System.out.println("ERROR: La sala no ha podido ser eliminada");
                                    Esr_Incorrecto(ns);
                                }

//                    atrib = new Atributos();
//                    atrib.setns(ns);
//                    atrib.setnum_error(2);
//                          return atrib;
                      }


                }
                //================AGREGAR CLIENTE ================================================//
                else if (elemento.equals("ACL")){
 //                    System.out.println("EL primer parametro(ACL) coincide");//Quitar
                    if (!subcadena.hasMoreTokens()){
 //                       System.out.println("Faltan argumentos de entrada");//Quitar
                        //Faltarian argumentos
                        Error_Parametros(30);
                        //////return null;
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(1);
//                        return atrib;
                    }
                    else {
//                         System.out.println("Leo el segundo argumento(Nif)");//Quitar
                         ni = subcadena.nextToken();
                         if (!formato_ni(ni)) {
                             Error_Parametros(41);
                            //////return null;
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setnum_error(7);
//                            return atrib;

                         }
                         else {
                             if (!subcadena.hasMoreTokens()){
 //                              System.out.println("Faltan argumentos de entrada");//Quitar
                                //Faltarian argumentos
                                Error_Parametros(30);
                                //////return null;
//                                atrib = new Atributos();
//                                atrib.settarea(elemento);
//                                atrib.setnum_error(1);
//                                return atrib;
                             }
                             else {
                                 nc = subcadena.nextToken();
                                 while(!formato_nc(nc)) {
                                     if (subcadena.hasMoreTokens()){
                                         nc = nc +" "+subcadena.nextToken();
                                     }
                                     else{
//                                         System.out.println("3 argumento incorrecto");//Quitar
                                         //el argumento pasado es incorrecto;
                                         Error_Parametros(51);
                                         //////return null;
//                                         atrib = new Atributos();
//                                         atrib.setnum_error(8);
//                                         return atrib;
                                     }
                                 }
                              if (subcadena.hasMoreElements()) {
                                  df = subcadena.nextToken();
                                 while(!formato_df(df)) {
                                     if (subcadena.hasMoreTokens()){
                                         df = df +" "+subcadena.nextToken();
                                     }
                                     else{
                                         //System.out.println("3 argumento incorrecto(df)");//Quitar
                                         Error_Parametros(61);
                                        //////return null;
                                         //el argumento pasado es incorrecto;
//                                         atrib = new Atributos();
//                                         atrib.setnum_error(9);
//                                         return atrib;
                                     }
                                 }
            if (subcadena.hasMoreTokens()){
                        //System.out.println("Sobran argumentos de entrada");//Quitar
                        //Faltarian argumentos
                        Error_Parametros(30);
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(55);
//                        return atrib;
                    }
                      else {
                    //System.out.println("------FIN-------");
                    if (operaciones.Agregar_Un_Cliente(ni, nc, df) ) {
                                    Acl_Correcto(ni);
                                }
                                else {
                                    //System.out.println("ERROR: No se ha podido agregar el cliente");
                                    Acl_Incorrecto(ni);
                                }
//                     atrib = new Atributos();
//                     atrib.setni(ni);
//                     atrib.setnc(nc);
//                     atrib.setdf(df);
//                     atrib.setnum_error(0);
//                     return atrib;
                      }
                              }
                              else {
                                     //System.out.println("Faltan argumentos de entrada");//Quitar
                                    //Faltarian argumentos
                                     Error_Parametros(30);
                                     //////return null;
//                                    atrib = new Atributos();
//                                    atrib.settarea(elemento);
//                                    atrib.setnum_error(1);
//                                    return atrib;
                              }
                         }
                    }
                }                        



            }
                //================ELIMINAR CLIENTES================================================//
                else if (elemento.equals("ECL")) {
                    //System.out.println("EL primer parametro(ECL) coincide");//Quitar
                    if (!subcadena.hasMoreTokens()){
                        //System.out.println("Faltan argumentos de entrada");//Quitar
                        Error_Parametros(40);
                        //////return null;
                        //Faltarian argumentos
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(1);
//                        return atrib;
                    }
                    else {
                        ni = subcadena.nextToken();
                        //System.out.println("Leemos el nif del cliente");//Quitar
                        if (!formato_ni(ni)){ //Solo si el formato es incorrecto
                            //System.out.println("El nif no tiene el formato indicado");
                            Error_Parametros(41);
                            //////return null;
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setnum_error(7);
//                        return atrib;
                            }
                        else {
                               if (subcadena.hasMoreTokens()){
                        //System.out.println("Sobran argumentos de entrada");//Quitar
                        //Faltarian argumentos
                        Error_Parametros(40);
                        //////return null;
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(55);
//                        return atrib;
                    }
                      else {
                            //System.out.println("------FIN-------");
                            if (operaciones.Eliminar_Un_Cliente(ni)) {
                                ECL_Correcto(ni);
                            }
                            else {
                                Ecl_Incorrecto(ni);
                            }
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setni(ni);
//                            atrib.setnum_error(0);
//                        return atrib;
                      }

                            }
                        }
                    }
                //================AGREGAR REUNION ================================================//
                else if (elemento.equals("ARE"))
                    {
                    nr = "";
                    ni = "";
                    //System.out.println("El primer parametro(ARE) coincide");//Quitar
                    if (!subcadena.hasMoreTokens()){
                        //System.out.println("Faltan argumentos de entrada");//Quitar
                        Error_Parametros(50);

                    }
                    else
                    {
                        //System.out.println("Leo el nombre de la reunion");
                        nr = subcadena.nextToken();
                        while(!formato_nr(nr))
                        {
                            //System.out.println("Formato nr ha dado correcto");
                            if (subcadena.hasMoreTokens())
                            {
                                nr = nr +" "+subcadena.nextToken();
                            }
                            else
                            {
                                //System.out.println("2 argumento incorrecto(nr)");//Quitar
                                                                                 //el argumento pasado es incorrecto;
                                Error_Parametros(71);
                            }
                        }
                        if (!subcadena.hasMoreTokens())
                        {
                        //System.out.println("Faltan argumentos de entrada(as,...)");//Quitar

                        Error_Parametros(50);
                        }
                        else
                        {
                            //System.out.println("Leo los asistentes de la reunion");//Quitar
                        //Leo los asistentes de la reunion.
                            as2 = subcadena.nextToken();
                            //System.out.println("los asistentes de la reunion son " +as2 + " y tamaño  " + as2.length() );//Quitar
                            if (as2.length() == 3)
                            {
                                try
                                {
                                as = Integer.parseInt(as2);
                                }
                                catch ( NumberFormatException e)
                                {
                                    //System.out.println("3 parametro no es un entero(as)");//Quitar
                                    //Error: el numero no es un entero
                                    Error_Parametros(81);
                                }
                            }
                            else
                            {
                                //System.out.println("3 parametro es un entero de mas de 3 digitos o de menos de 3 digitos ");//Quitar
                                Error_Parametros(81);                               
                            }


                            if (!subcadena.hasMoreTokens())
                            {
                                Error_Parametros(50);                                
                            }
                            else
                            {
                                //System.out.println("Leo la fecha inicial de la reunion");
                                //Leo la fechai en string pero tengo que comprobar que el string sea de 12
                                stringfi = subcadena.nextToken();

                                if (stringfi.length() != 10)
                                {
                                    //System.out.println("La fechai no tiene el formato indicado(longitud = 10)");
                                    Error_Parametros(91);
                                }
                                else
                                {
                                    barradiames = stringfi.charAt(2);
                                    barramesaño = stringfi.charAt(5);
                                    if ((barradiames != '-') || (barramesaño != '-'))
                                    {
                                        //System.out.println("La fechai no se ajusta el formato(-)");
                                        Error_Parametros(91);
                                    }
                                    else
                                    {
                                        try
                                        {
                                            dia = Integer.parseInt(stringfi.substring(0,2));
                                            mes = (Integer.parseInt(stringfi.substring(3,5)));
                                            ////MODIFICADO MES
                                            año = Integer.parseInt(stringfi.substring(6,10));
//MOdificado//
                                              fi.set(año, mes-1, dia);
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            //System.out.println("No se puede parsear el numero");
                                            //System.out.println("La fechai no se ajusta el formato(-)");
                                             Error_Parametros(91);
                                        }

                                        if (!formato_fecha((Integer.parseInt(stringfi.substring(0,2))),
                                        (Integer.parseInt(stringfi.substring(3,5))), //El mes sin menos 1 xq mi funcion va del 0 al 12;
                                        Integer.parseInt(stringfi.substring(6,10))))
                                        {
                                        //System.out.println("La fechai no se ajusta el formato");

                                        Error_Parametros(91);

                                        }
                                    }
                                       //fi.set(año,mes -1 ,dia, hora, minuto);
                                    if (!subcadena.hasMoreTokens())
                                    {
                                        Error_Parametros(50);                                       
                                          //Faltarian argumentos
                                    }
                                    else
                                    {
                                        //System.out.println("Leo la hora inicial de la reunion");
                                    //Leo la horai en string
                                        stringhi = subcadena.nextToken();
                                        if (stringhi.length() == 5)
                                        {
                                            dospuntos = stringhi.charAt(2);
                                            if (dospuntos != ':') {
                                            //System.out.println("La horai no se ajusta el formato(:)");

                                            Error_Parametros(101);
                                            }
                                            else
                                            {
                                                try
                                                {
                                                    hora= Integer.parseInt(stringhi.substring(0,2));
                                                    minuto = (Integer.parseInt(stringhi.substring(3,5)));
                                                    fi.set(Calendar.HOUR_OF_DAY, hora);
                                                    fi.set(Calendar.MINUTE, minuto);
                                                    if (!formato_hora(hora, minuto))
                                                    {
                                                        //System.out.println("La horai no se ajusta a las horas de actividad  ");

                                                        Error_Parametros(111);
                                                    }
                                                }
                                                catch (NumberFormatException e)
                                                {
                                                    //System.out.println("No se puede parsear el numero(la hora)");
                                                    Error_Parametros(91);
                                                }
                                        
                                            }

                                        }
                                        else
                                        {
                                            //System.out.println("La horai no tiene el formato indicado(longitud = 5)");
                                            Error_Parametros(101);
                                        }
                                //Fecha de inicio en modo Calendar dddd/mm/yyyy hh:mm//

                                //System.out.println(año + "-"+mes+ "-" + dia + " "+  minuto + ":" +  hora );
                                //fi.set(año,mes  ,dia, hora, minuto);
                                        //System.out.println("Leo la fecha inicial total de la reunion en modo Calendar es " + fi);
                                        //System.out.println("Leo la fecha inicial total de la reunion en modo Calendar es " + fi.get(Calendar.YEAR)+ "-" + fi.get(Calendar.MONTH) + "-"+ fi.get(Calendar.DATE) + " " + fi.get(Calendar.HOUR) + ":" + fi.get(Calendar.MINUTE));

                                //Leo fecha fin y despues hora fin//
                                        if (!subcadena.hasMoreTokens())
                                        {
                                      //Faltarian argumentos
                                        Error_Parametros(50);
                                        }
                                        else
                                        {
                                //Leo la fechai en string pero tengo que comprobar que el string sea de 12
                                            stringff = subcadena.nextToken();

                                            if (stringff.length() != 10)
                                            {
                                                //System.out.println("La fechaf no tiene el formato indicado(longitud = 10)");
                                                Error_Parametros(121);
                             //////return null;
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setnum_error(10);
//                            return atrib;
                                        }
                                        else {
                                            barradiames = stringff.charAt(2);
                                            barramesaño = stringff.charAt(5);
                                            if ((barradiames != '-') || (barramesaño != '-')) {
                                             //System.out.println("La fechaf no se ajusta el formato(-)");
                                             Error_Parametros(121);
                                            //////return null;
        //                                     atrib = new Atributos();
        //                                     atrib.settarea(elemento);
        //                                     atrib.setnum_error(10);
        //                                      return atrib;
                                            }
                                            else
                                            {
                                                try
                                                {
                                                    dia = Integer.parseInt(stringff.substring(0,2));
                                                    mes = (Integer.parseInt(stringff.substring(3,5)));
                                                    //Modificado
                                                    año = Integer.parseInt(stringff.substring(6,10));
                                                    ff.set(año, mes-1, dia);
                                                    //System.out.println(""+ff.get(Calendar.DATE));

                                                }
                                                catch (NumberFormatException e)
                                                {
                                                    //System.out.println("No se puede parsear el numero");
                                                    Error_Parametros(121);
                                                    //////return null;
        //                                            atrib = new Atributos();
        //                                            atrib.settarea(elemento);
        //                                            atrib.setnum_error(10);
        //                                            return atrib;
                                                }
                                                if (!formato_fecha((Integer.parseInt(stringff.substring(0,2))),
                                                (Integer.parseInt(stringff.substring(3,5))), // mes de formato_fecha del 1-12;
                                                Integer.parseInt(stringff.substring(6,10))))
                                                {
                                                //System.out.println("La fechaf no se ajusta el formato");
                                                Error_Parametros(121);
                                                //////return null;
        //                                        atrib = new Atributos();
        //                                        atrib.settarea(elemento);
        //                                        atrib.setnum_error(10);
        //                                        return atrib;
                                                }

                                            }
                                       //ff.set(año,mes -1 ,dia, hora, minuto);
                                            if (!subcadena.hasMoreTokens())
                                            {
                                          //Faltarian argumentos
                                                Error_Parametros(50);
                                           //////return null;
//                                    atrib = new Atributos();
//                                    atrib.settarea(elemento);
//                                    atrib.setnum_error(1);
//                                    return atrib;
                                            }
                                            else
                                            {
                                    //Leo la horaf en string
                                                stringhf = subcadena.nextToken();
                                                if (stringhf.length() == 5)
                                                {
                                                    dospuntos = stringhf.charAt(2);

                                                    if (dospuntos != ':')
                                                    {
                                                        //System.out.println("La horaf no se ajusta el formato(:)");
                                                        Error_Parametros(131);
                                                        //////return null;
                //                                        atrib = new Atributos();
                //                                        atrib.settarea(elemento);
                //                                        atrib.setnum_error(10);
                //                                        return atrib;
                                                    }
                                                    else
                                                    {
                                                        try
                                                        {
                                                             hora= Integer.parseInt(stringhf.substring(0,2));
                                                             minuto = (Integer.parseInt(stringhf.substring(3,5)));
                                                            if (!formato_hora(hora, minuto))
                                                            {
                                                                //System.out.println("La horaf no se ajusta a las horas de actividad  ");
                                                                Error_Parametros(141);
                                                            }
                                                            else
                                                            {
                                                                ff.set(Calendar.HOUR_OF_DAY, hora);
                                                                ff.set(Calendar.MINUTE, minuto);
                                                            }

                                                        }
                                                        catch (NumberFormatException e)
                                                        {
                                                            //System.out.println("No se puede parsear el numero(la horaf)");
                                                            //System.out.println("La horaf no se ajusta el formato(:)");
                                                            Error_Parametros(131);
                                                        }

                                                    }

                                                }
                                
                                                else
                                                { //La longitud es de menor o mayor que 10
                                                    //System.out.println("La horaf no tiene el formato indicado(longitud = 5)");
                                                    Error_Parametros(141);
                                                }
                                                    //Fecha de inicio en modo Calendar dddd/mm/yyyy hh:mm//
                                                    //ff.set(año,mes -1 ,dia, hora, minuto);
                                                    stringfihi = stringfi.concat(" ".concat(stringhi));
                                                    stringffhf = stringff.concat(" ".concat(stringhf));
                                                    //System.out.println("Fecha y hora inicial " + stringfihi);
                                                    //System.out.println("Fecha y hora final   " + stringffhf);



                                                    if (resta_fechas(stringfihi,stringffhf) < 0)
                                                    {
                                                        //System.out.println("La fecha de inicio es superior a la inicial");
                                                        Error_Parametros(151);
                                                        //////return null;
                    //                                    atrib = new Atributos();
                    //                                    atrib.settarea(elemento);
                    //                                    atrib.setnum_error(15);
                    //                                    return atrib;
                                                    }
                                                }
                                            }

                                //Leo el nif
                                            if (!subcadena.hasMoreTokens())
                                            {
                                                //System.out.println("Faltan argumentos de entrada");//Quitar
                                                Error_Parametros(50);
                                                //////return null;
                                                //Faltarian argumentos
                        //                        atrib = new Atributos();
                        //                        atrib.settarea(elemento);
                        //                        atrib.setnum_error(1);
                        //                        return atrib;
                                            }
                                            else
                                            {
                                                 //System.out.println("Leo el octavo argumento(Nif)");//Quitar
                                                 ni = subcadena.nextToken();
                                                 if (!formato_ni(ni))
                                                 {
                                                     Error_Parametros(41);
                                                     //////return null;
                        //                            atrib = new Atributos();
                        //                            atrib.settarea(elemento);
                        //                            atrib.setnum_error(7);
                        //                            return atrib;
                                                 }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (subcadena.hasMoreTokens())
                    {
                        //System.out.println("Sobran argumentos de entrada");//Quitar
                        Error_Parametros(50);
                        //////return null;
                        //Faltarian argumentos
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(55);
//                        return atrib;
                    }
                    else
                    {
                        //System.out.println("------FIN-------");
                        if (operaciones.Agregar_Una_Reunion(nr,as,fi,ff,ni)) {                            
                                    //System.out.println("Parametros= "+nr+" "+as+" "+fi.get(Calendar.DATE)+"-"+fi.get(Calendar.MONTH)+"-"+fi.get(Calendar.YEAR)+" " +
                                    //ff.get(Calendar.DATE)+"-"+ff.get(Calendar.MONTH)+"-"+ff.get(Calendar.YEAR)+" "+ni);
                             ns = operaciones.Dar_Nom_Sala_Reu(nr);
                                    Are_Correcto(nr, ni, ns);
                                }
                                else {
                            //System.out.println("Parametros Error = "+nr+" "+as+" "+fi.get(Calendar.DATE)+"-"+fi.get(Calendar.MONTH)+"-"+fi.get(Calendar.YEAR)+" "+
                                    //ff.get(Calendar.DATE)+"-"+ff.get(Calendar.MONTH)+"-"+ff.get(Calendar.YEAR)+" "+ni);
                                    //System.out.println("ERROR: No se ha podido agregar reunion");
                                    Are_Incorrecto(nr, ni, ns);
                                }
//                    atrib = new Atributos();
//                    atrib.setnr(nr);
//                    atrib.setas(as);
//                    atrib.setfi(fi); //La hora tb esta incluida en la fecha
//                    atrib.setfi(ff);// Idem que la anterior;
//                    atrib.setni(ni);
//                    atrib.setnum_error(0);
//                          return atrib;
                      }

                }
                ///------------MOSTRAR REUNIONES DE UN DIA--------------------------------///
                else if (elemento.equals("MRD")) {
                    //System.out.println("EL primer parametro(MOS) coincide");//Quitar
                     //System.out.println("-----------------------------------------------------");
                     //System.out.println("----------------MOSTRAR REUNIONES DIA-----------------------");
                     //System.out.println("-----------------------------------------------------");
                    //System.out.println("El primer parametro(MRD) coincide");//Quitar
                    if (!subcadena.hasMoreTokens()){
                        //System.out.println("Faltan argumentos de entrada");//Quitar
                        //Faltarian argumentos
                          Error_Parametros(60);
                          //////return null;
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(1);
//                        return atrib;
                    }
                    else {
                         //System.out.println("Leo el segundo argumento(fm)");//Quitar
                         fm2 = subcadena.nextToken();
                         if (fm2.length() != 10){
                             //System.out.println("La fecha fm no tiene el formato indicado(longitud = 10)");
                             Error_Parametros(161);



                             //////return null;
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setnum_error(10);
//                            return atrib;
                                }
                                else {
                                    barradiames = fm2.charAt(2);
                                    barramesaño = fm2.charAt(5);
                                    if ((barradiames != '-') || (barramesaño != '-')) {
                                     //System.out.println("La fecha fm no se ajusta el formato(-)");
                                     Error_Parametros(161);
                                     //////return null;
//                                     atrib = new Atributos();
//                                     atrib.settarea(elemento);
//                                     atrib.setnum_error(10);
//                                      return atrib;
                                    }
                                    else {
                                        try {
                                            dia = Integer.parseInt(fm2.substring(0,2));
                                            mes = (Integer.parseInt(fm2.substring(3,5)));
                                            año = Integer.parseInt(fm2.substring(6,10));
                                            fm.set(año,mes -1,dia);
                                            //System.out.println("El mes fm en Modo Gregorian Calendar es  " + fm.get(Calendar.MONTH));
                                            //System.out.println("La Fecha en fm en Modo Normal es  " + dia +"-"+ mes + "-" + año );
                                        }
                                        catch (NumberFormatException e) {
                                            //System.out.println("No se puede parsear el numero");
                                            Error_Parametros(161);
                                            //////return null;
//                                            atrib = new Atributos();
//                                            atrib.settarea(elemento);
//                                            atrib.setnum_error(10);
//                                            return atrib;
                                        }
                                        if (!formato_fecha((Integer.parseInt(fm2.substring(0,2))),
                                        (Integer.parseInt(fm2.substring(3,5))), //El mes sin menos 1 xq mi funcion va del 0 al 12;
                                        Integer.parseInt(fm2.substring(6,10)))) {
                                        //System.out.println("La fechafm no se ajusta el formato");
                                        Error_Parametros(161);
                                        //////return null;
//                                        atrib = new Atributos();
//                                        atrib.settarea(elemento);
//                                        atrib.setnum_error(10);
//                                        return atrib;

                                        }
                                    }


                                }
                            if (subcadena.hasMoreTokens()){
                        //System.out.println("Sobran argumentos de entrada");//Quitar
                        //Faltarian argumentos
                        Error_Parametros(60);
                          //////return null;
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(55);
//                        return atrib;
                    }
                      else {
                             try {
                             //MODIFICADO
                            dia = Integer.parseInt(fm2.substring(0,2));
                            mes = (Integer.parseInt(fm2.substring(3,5)));
                            año = Integer.parseInt(fm2.substring(6,10))  ;

                            fm.set(año ,mes -1 ,dia);
                            //System.out.println("La Fecha2 en fm en Modo Normal es  " + dia +"-"+ mes + "-" + año );
                             }
                             catch (NumberFormatException e) {
                                            //System.out.println("No se puede parsear el numero");
                                            Error_Parametros(161);
                                 
                             }

                    //System.out.println("------FIN-------------------------------------------------------");
                    //System.out.println("El mes fm en Modo Gregorian Calendar es  " + fm.get(Calendar.MONTH));
                     reudia = operaciones.Mostrar_Reuniones_Dia(fm);
                                //System.out.println("La llamada a Mostrar_Reuniones lo hacemos con fecha en modo Gregorian Calendar " +fm.get(Calendar.DATE)+"-"                                                    +fm.get(Calendar.MONTH)+ "-"+fm.get(Calendar.YEAR));
                                //System.out.println(" Fecha antesssssssssssssssssss " + fm.get(Calendar.MONTH));

                     
                    //fichero.writeBytes("REUNIONES DIA\n");
                         try{
                            fichero.writeBytes("REUNIONES DIA\n");


                            int DiaReunion = fm.get(Calendar.DATE);
                            //System.out.println("El dia que cogemos es " +  fm.get(Calendar.DATE) );
                            int MesReunion = fm.get(Calendar.MONTH) +1 ;
                            //System.out.println("El mes que cogemos es " + MesReunion);
                            String SDiaReunion = Integer.toString(DiaReunion);
                            String SMesReunion = Integer.toString(MesReunion);
                            if (SDiaReunion.length() == 1) {
                                SDiaReunion = "0"+ SDiaReunion;
                            }
                            if (SMesReunion.length() == 1) {
                                SMesReunion = "0"+ SMesReunion;
                            }
                            fichero.writeBytes("Dia: " +SDiaReunion+ "-" + SMesReunion + "-"+ fm.get(Calendar.YEAR) +"\n");
                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en reunion");
                            }
                                //System.out.println("La longitud de reudia es de " +reudia.Dar_Long());
                         for (int i=0; i<reudia.Dar_Long();i++) {
                         try{                                                       
                             fichero.writeBytes(reudia.Dar_NomReunion(i));
                             fichero.writeBytes(" ");
                             fichero.writeBytes(reudia.Dar_NomCliente(i));
                             fichero.writeBytes(" ");
                             fichero.writeBytes(reudia.Dar_NomSala(i));
                             fichero.writeBytes(" ");
                             int horaInicial = reudia.Dar_HoraInicio(i).get(Calendar.HOUR_OF_DAY);
                             int minutoInicial = reudia.Dar_HoraInicio(i).get(Calendar.MINUTE);
                             int HoraFinal = reudia.Dar_HoraFin(i).get(Calendar.HOUR_OF_DAY);
                             int minutoFinal = reudia.Dar_HoraFin(i).get(Calendar.MINUTE);
                             String horaiString = Integer.toString(horaInicial);
                             String minutoiString = Integer.toString(minutoInicial);
                             String horafString = Integer.toString(HoraFinal);
                             String minutofString = Integer.toString(minutoFinal);
                             if (horaiString.length() == 1 ) {
                                 horaiString = "0"+ horaiString;
                             }
                             if (horafString.length() == 1 ) {
                                 horafString = "0"+ horafString;
                             }
                             if (minutoiString.length() == 1 ) {
                                 minutoiString = "0"+ minutoiString;
                             }
                             if (minutofString.length() == 1 ) {
                                 minutofString = "0"+ minutofString;
                             }
                             fichero.writeBytes(horaiString);                             
                             fichero.writeBytes(":");
                             fichero.writeBytes(minutoiString);
                             fichero.writeBytes(" ");                                                          
                             fichero.writeBytes(horafString);
                             fichero.writeBytes(":");                                                          
                             fichero.writeBytes(minutofString);
                             //System.out.println( "LA hora de inicio es " +reudia.Dar_HoraInicio(i).get(Calendar.HOUR_OF_DAY));

                             //fichero.writeLong(reudia.Dar_HoraInicio(i).get(Calendar.HOUR_OF_DAY));
                             //fichero.writeUTF(reudia.Dar_HoraInicio(i).get(Calendar.HOUR_OF_DAY));

                             //fichero.writeBytes(reudia.Dar_HoraInicio(i).get(Calendar.HOUR_OF_DAY));
                             //fichero.writeBytes(toString(reudia.Dar_HoraInicio(i).get(Calendar.HOUR_OF_DAY)))                                     ;
                             fichero.writeBytes("  \n");

                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en escribir reuniones");
                            }
                         }
                     try{
                            fichero.writeBytes("FIN REUNIONES DIA\n");
                                                       
                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en escribir reuniones");
                            }

                                }
//                    atrib = new Atributos();
//                    atrib.setfm(fm);
//                    atrib.setnum_error(0);
//                          return atrib;
                      }
                    }
                
                ///------------MOSTRAR OCUPACION DE SALA--------------------------------///
                 else if (elemento.equals("MOS")) {
                     //System.out.println("EL primer parametro(MOS) coincide");//Quitar
                     //System.out.println("-----------------------------------------------------");
                     //System.out.println("----------------OCUPACION SALA-----------------------");
                     //System.out.println("-----------------------------------------------------");
                    if (!subcadena.hasMoreTokens()){
                        //System.out.println("Faltan argumentos de entrada");//Quitar
                        Error_Parametros(70);
                        //////return null;
                        //Faltarian argumentos

//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(1);
//                        return atrib;
                    }
                    else {
                        //System.out.println("Leo el segundo argumento(ns)");//Quitar
                        //Leo el nombre de la sala
                        ns = subcadena.nextToken();
                        while(!formato_ns(ns)){ //Solo si el formato es incorrecto
                            if (subcadena.hasMoreTokens()) {
                                ns = ns + " " + subcadena.nextToken();
                            }
                            else {
                                //System.out.println("2 argumento incorrecto(NS)");//Quitar
                              //el argumento pasado es incorrecto;
                                Error_Parametros(11);
                                //////return null;
//                                atrib = new Atributos();
//                                atrib.setnum_error(2);
//                                return atrib;
                            }
                        }
                        if (!subcadena.hasMoreTokens()){
                        //System.out.println("Faltan argumentos de entrada");//Quitar
                        Error_Parametros(70);
                        //////return null;
                        //Faltarian argumentos
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(1);
//                        return atrib;
                        }
                        else {

                        //System.out.println("3 parametro(me)");//Quitar
                        //Leo el mes año
                        me2 = subcadena.nextToken();
                        messsss = me2;
                        if (me2.length() != 7){
                             //System.out.println("La fecha mes año no tiene el formato indicado(longitud = 7)");
                             Error_Parametros(171);
                              //////return null;
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setnum_error(10);
//                            return atrib;
                                }
                                else {
                                    barramesaño = me2.charAt(2);
                                    if (barramesaño != '-') {
                                     //System.out.println("La fecha mes año no se ajusta el formato(-)");
                                     Error_Parametros(171);
                                     //////return null;
//                                     atrib = new Atributos();
//                                     atrib.settarea(elemento);
//                                     atrib.setnum_error(10);
//                                      return atrib;
                                    }
                                    else {
                                        try {

                                            mes = (Integer.parseInt(me2.substring(0,2))-1);
                                            año = Integer.parseInt(me2.substring(3,7));
                                            //me.set(mes ,Calendar.MONTH);
                                            //me.set(año,Calendar.YEAR);
                                            me = new GregorianCalendar(año,mes,1,0,0);

                                           if ((mes > 11) || (mes < 0)) {
                                            //System.out.println("El mes no es valido");
                                            Error_Parametros(171);
                                            //////return null;
//                                            atrib = new Atributos();
//                                            atrib.settarea(elemento);
//                                            atrib.setnum_error(10);
//                                            return atrib;
                                        }

                                        }
                                        catch (NumberFormatException e) {
                                            //System.out.println("No se puede parsear el numero");
                                            Error_Parametros(171);
                                            //////return null;
//                                            atrib = new Atributos();
//                                            atrib.settarea(elemento);
//                                            atrib.setnum_error(10);
//                                            return atrib;
                                        }

                                    }
                                }
                        }
                    }
                        if (subcadena.hasMoreTokens()){
                        //System.out.println("Sobran argumentos de entrada");//Quitar
                        //Faltarian argumentos
                        Error_Parametros(70);
                        //////return null;
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(55);
//                        return atrib;
                    }
                      else {

                         smes = new SalasMes ("",0);                            
                         smes = operaciones.Mostrar_Ocupacion_Sala_Mes(ns, me);
                         //System.out.println("mes es mes es mes es mes es "+me2);
                         //System.out.println("------FIN-------\n");
                        try{
                            
                            
                             //int capacidadd = smes.Dar_Capacidad();
                            //System.out.println("La capacidad es de " + capacidaddd +"\n");
                            //String scapacidad = Integer.toString(capacidaddd);

                            
                            int capacidad = smes.Dar_Capacidad();
                            String scapacidad = Integer.toString(capacidad);
                            if (scapacidad.length() == 1) {
                                scapacidad = "00" + scapacidad;
                            }
                            else if (scapacidad.length() == 2) {
                                scapacidad = "0" + scapacidad;
                            }
                            fichero.writeBytes("OCUPACION SALA\n");
                            fichero.writeBytes("Sala: ");
                            fichero.writeBytes(smes.Dar_NomSala() + " " );
                            fichero.writeBytes(scapacidad + "\n");
                            fichero.writeBytes("Mes: " +me2+"\n");
                            //////Cuidado/////
                            //fichero.writeBytes("Mes: " +smes.Dar_fecha(Calendar.MONTH)+ "-"+smes.Dar_fecha(Calendar.YEAR));
                            }
                        catch (IOException e)
                            {
                         //System.out.println("Error: Excepcion en sala");
                            }
                         //System.out.println("la longitud de las salas es = " +smes.Dar_Long_Salas());
                         for (int i = 0; i< smes.Dar_Long_Salas(); i++){
                         try{
                         int diamos = smes.Dar_fecha(i).get(Calendar.DATE);
                         String sdiamos = Integer.toString(diamos);
                         if (sdiamos.length() == 1) {
                             sdiamos = "0" + sdiamos;
                         }
                         fichero.writeBytes("Dia " +sdiamos +  "\n");
                         fichero.writeBytes(smes.Dar_NomReunion(i)+" ");
                         fichero.writeBytes(smes.Dar_NomCliente(i)+ " "+ "\n" + "FIN DIA\n");
                         //fichero.writeBytes(smes.);
                         //fichero.writeBytes(reudia.Dar_NomReunion(i)+ " ");
                         //fichero.writeBytes(reudia.Dar_NomCliente(i)
                         //+ "\n" + "FIN DIA\n");
                         
                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en escribir reuniones");
                            }
                 }// LLAve FOR
                     try{
                            fichero.writeBytes("FIN REUNIONES DIA\n");

                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en escribir reuniones");
                            }                    
                      }
                 }
                ///------------MOSTRAR DISPONIBILIDAD DE SALA--------------------------------///
                 else if (elemento.equals("MDS")) {
                     //System.out.println("-----------MOSTRAR DISPONIBILIDAD DE SALAS-----------------------");
                     //System.out.println("EL primer parametro(MDS) coincide");//Quitar
                    if (!subcadena.hasMoreTokens()){
                        //System.out.println("Faltan argumentos de entrada");//Quitar
                        //Faltarian argumentos
                        Error_Parametros(80);
                        //////return null;
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(1);
//                        return atrib;
                    }
                    else {
                         //System.out.println("Leo la fecha inicial para  la disponibilidad de salas");
                                //Leo la fechai en string pero tengo que comprobar que el string sea de 12
                                stringfi = subcadena.nextToken();

                                if (stringfi.length() != 10){
                             //System.out.println("La fechai no tiene el formato indicado(longitud = 10) MdS");
                             Error_Parametros(91);
                             //////return null;
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setnum_error(10);
//                            return atrib;
                                }
                                else {
                                    barradiames = stringfi.charAt(2);
                                    barramesaño = stringfi.charAt(5);
                                    if ((barradiames != '-') || (barramesaño != '-')) {
                                     //System.out.println("La fechai no se ajusta el formato(-)");
                                     Error_Parametros(91);
                                     //////return null;
//                                     atrib = new Atributos();
//                                     atrib.settarea(elemento);
//                                     atrib.setnum_error(10);
//                                      return atrib;
                                    }
                                    else {
                                        try {
                                            dia = Integer.parseInt(stringfi.substring(0,2));
                                            mes = (Integer.parseInt(stringfi.substring(3,5))-1);
                                            año = Integer.parseInt(stringfi.substring(6,10));
                                            fi.set(año, mes, dia);
                                        }
                                        catch (NumberFormatException e) {
                                            //System.out.println("No se puede parsear el numero");
                                            Error_Parametros(91);
                                            //////return null;
//                                            atrib = new Atributos();
//                                            atrib.settarea(elemento);
//                                            atrib.setnum_error(10);
//                                            return atrib;
                                        }
                                        if (!formato_fecha((Integer.parseInt(stringfi.substring(0,2))),
                                        (Integer.parseInt(stringfi.substring(3,5))), //El mes sin menos 1 xq mi funcion va del 0 al 12;
                                        Integer.parseInt(stringfi.substring(6,10)))) {
                                        //System.out.println("La fechai no se ajusta el formato");
                                        Error_Parametros(91);
                                        //////return null;
//                                        atrib = new Atributos();
//                                        atrib.settarea(elemento);
//                                        atrib.setnum_error(10);
//                                        return atrib;
                                }

                                    }
                                    if (!subcadena.hasMoreTokens()) {
                                          //Faltarian argumentos
                                        Error_Parametros(80);
                                        //////return null;
//                                    atrib = new Atributos();
//                                    atrib.settarea(elemento);
//                                    atrib.setnum_error(1);
//                                    return atrib;
                                        }
                                    else{
                                        //Leo la fechaf en string pero tengo que comprobar que el string sea de 12
                                stringff = subcadena.nextToken();

                                if (stringff.length() != 10){
                             //System.out.println("La fechaf no tiene el formato indicado(longitud = 10)");
                             Error_Parametros(121);
                             //////return null;
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setnum_error(10);
//                            return atrib;
                                }
                                else {
                                    barradiames = stringff.charAt(2);
                                    barramesaño = stringff.charAt(5);
                                    if ((barradiames != '-') || (barramesaño != '-')) {
                                     //System.out.println("La fechaf no se ajusta el formato(-)");
                                     Error_Parametros(121);
                                     //////return null;
//                                     atrib = new Atributos();
//                                     atrib.settarea(elemento);
//                                     atrib.setnum_error(10);
//                                      return atrib;
                                    }
                                    else {
                                        try {
                                            dia2 = Integer.parseInt(stringff.substring(0,2));
                                            mes2 = (Integer.parseInt(stringff.substring(3,5))-1);
                                            año2 = Integer.parseInt(stringff.substring(6,10));
                                            ff.set(año2, mes2, dia2);
                                        }
                                        catch (NumberFormatException e) {
                                            //System.out.println("No se puede parsear el numero");
                                            Error_Parametros(121);
                                            //////return null;
//                                            atrib = new Atributos();
//                                            atrib.settarea(elemento);
//                                            atrib.setnum_error(10);
//                                            return atrib;
                                        }
                                        if (!formato_fecha((Integer.parseInt(stringff.substring(0,2))),
                                        (Integer.parseInt(stringff.substring(3,5))), // mes de formato_fecha del 1-12;
                                        Integer.parseInt(stringff.substring(6,10)))) {
                                        //System.out.println("La fechaf no se ajusta el formato");
                                        Error_Parametros(121);
                                        //////return null;
//                                        atrib = new Atributos();
//                                        atrib.settarea(elemento);
//                                        atrib.setnum_error(10);
//                                        return atrib;
                                        }
                                        else {
                                            //MODIFICADO
                                          //fi.set(año, mes, dia);
                                          //ff.set(año2, mes2, dia2);
                                          if (fi.before(ff)) {
                                              //System.out.println("Fecha de inicio inferior o igual a la fecha final");
                                          }
                                          else {
                                              //System.out.println("ERROR : La fecha de inicio superior a la fecha final");
                                              Error_Parametros(151);

                                          }
                                        }
                                    }
                                }
                                    }
                                }
                    }
                        if (subcadena.hasMoreTokens()){
                        //System.out.println("Sobran argumentos de entrada");//Quitar
                        //Faltarian argumentos
                         Error_Parametros(80);
                         //////return null;
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(55);
//                        return atrib;
                    }
                      else {
                    //System.out.println("------FIN-------");
                    //reuperiodo = operaciones.Mostrar_Disponibilidad_Salas_Tiempo(fi, ff);
                    //System.out.println("EL MES INICIAL QUE INTRODUZCO A OPERACIONES ES: MESi " + fi.get(Calendar.MONTH) +
                                                                                         //  " MESf "+ ff.get(Calendar.MONTH));
                    sdisp = operaciones.Mostrar_Disponibilidad_Salas_Tiempo(fi, ff);
try{
                            int DiaReunioni = fi.get(Calendar.DATE);
                            int DiaReunionf = ff.get(Calendar.DATE);
                            //System.out.println("El dia inicial que cogemos es " +  DiaReunioni );
                            //System.out.println("El dia final que cogemos es " +  DiaReunionf );
                            int MesReunioni = fi.get(Calendar.MONTH) + 1 ;
                            int MesReunionf = ff.get(Calendar.MONTH) + 1;
                            //System.out.println("El mes inicial que cogemos es " + MesReunioni);
                            //System.out.println("El mes final que cogemos es " + MesReunionf);
                            String SDiaReunioni = Integer.toString(DiaReunioni);
                            String SDiaReunionf = Integer.toString(DiaReunionf);
                            String SMesReunioni = Integer.toString(MesReunioni);
                            String SMesReunionf = Integer.toString(MesReunionf);
                            if (SDiaReunioni.length() == 1) {
                                SDiaReunioni = "0"+ SDiaReunioni;
                            }
                            if (SDiaReunionf.length() == 1) {
                                SDiaReunionf = "0"+ SDiaReunionf;
                            }
                            if (SMesReunioni.length() == 1) {
                                SMesReunioni = "0"+ SMesReunioni;
                            }
                            if (SMesReunionf.length() == 1) {
                                SMesReunionf = "0"+ SMesReunionf;
                            }


                            //int capacidaddd = smes.Dar_Capacidad();
                            //System.out.println("La capacidad es de " + capacidaddd +"\n");
                            //String scapacidad = Integer.toString(capacidaddd);


                            
                            fichero.writeBytes("DISPONIBILIDAD SALAS\n");
                            fichero.writeBytes("Fechas: desde " + SDiaReunioni + "-" + SMesReunioni
                                               + "-" +fi.get(Calendar.YEAR) + " hasta "  + SDiaReunionf + "-"
                                               + SMesReunionf + "-" +ff.get(Calendar.YEAR) + "\n");
                            }
                        catch (IOException e)
                            {
                         //System.out.println("Error: Excepcion en disponibilidad sala");
                            }
                         for (int i = 0; i< sdisp.Long_Salas(); i++){
                         try{
                         int cap = sdisp.Dar_Capacidad(i);
                         int precio = sdisp.Dar_Precio(i);
                         String scap = Integer.toString(cap);
                         String sprecio = Integer.toString(precio);
                                if (sprecio.length() == 1)
                                {
                                sprecio = "0"+ sprecio;
                                }
                                if (scap.length() == 1)
                                {
                                    scap = "00"+ sprecio;
                                }
                                else if  (scap.length() == 2)
                                {
                                          scap = "0"+ sprecio;
                                }
                         fichero.writeBytes(sdisp.Dar_Sala(i) + " " );
                         fichero.writeBytes(scap + " ");
                         fichero.writeBytes(sprecio + "\n");
                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en disponibilidad de salas");
                            }
                 }// LLAve FOR
                          try{
                            fichero.writeBytes("FIN DISPONIBILIDAD SALAS\n");
                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en escribir reuniones");
                            }

                      }
                 }
                //================CALCULAR FACTURA CLIENTE================================================//
                else if (elemento.equals("CFC")) {
                     //System.out.println("EL primer parametro(CFC) coincide");//Quitar
                 if (!subcadena.hasMoreTokens()){
                        //System.out.println("Faltan argumentos de entrada");//Quitar
                        //Faltarian argumentos
                        Error_Parametros(90);
                             //////return null;
//                        atrib = new Atributos();
//                        atrib.settarea(elemento);
//                        atrib.setnum_error(1);
//                        return atrib;
                    }
                    else {
                         //System.out.println("Leo el segundo argumento(Nif)");//Quitar
                         ni = subcadena.nextToken();
                         if (!formato_ni(ni)) {
                             Error_Parametros(41);
                             //////return null;
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setnum_error(7);
//                            return atrib;

                         }
                         else {
                             if (!subcadena.hasMoreTokens()){
                               //System.out.println("Faltan argumentos de entrada");//Quitar
                                //Faltarian argumentos
                                 Error_Parametros(90);
                                 //////return null;
//                                atrib = new Atributos();
//                                atrib.settarea(elemento);
//                                atrib.setnum_error(1);
//                                return atrib;

                             }
                             else {
                                 //System.out.println("3 parametro(me)");//Quitar
                        //Leo el mes año
                        me2 = subcadena.nextToken();
                        if (me2.length() != 7){
                             //System.out.println("La fecha mes año no tiene el formato indicado(longitud = 7)");
                             Error_Parametros(171);
                             //////return null;
//                            atrib = new Atributos();
//                            atrib.settarea(elemento);
//                            atrib.setnum_error(10);
//                            return atrib;
                                }
                                else {
                                    barramesaño = me2.charAt(2);
                                    if (barramesaño != '-') {
                                     //System.out.println("La fecha mes año no se ajusta el formato(-)");
                                     Error_Parametros(171);
                                     //////return null;
//                                     atrib = new Atributos();
//                                     atrib.settarea(elemento);
//                                     atrib.setnum_error(10);
//                                      return atrib;
                                    }
                                    else {
                                        try {

                                            mes = (Integer.parseInt(me2.substring(0,2))-1);
                                            año = Integer.parseInt(me2.substring(3,7));

                                            me = new GregorianCalendar(año,mes,1,0,0);

                                            if ((mes > 11) || (mes < 0)) {
                                            //System.out.println("El mes no es valido");
                                            Error_Parametros(171);
                                            //////return null;
//                                            atrib = new Atributos();
//                                            atrib.settarea(elemento);
//                                            atrib.setnum_error(10);
//                                            return atrib;
                                        }
                                        }
                                        catch (NumberFormatException e) {
                                            //System.out.println("No se puede parsear el numero");
                                            Error_Parametros(171);
                                            //////return null;
//                                            atrib = new Atributos();
//                                            atrib.settarea(elemento);
//                                            atrib.setnum_error(10);
//                                            return atrib;
                                        }
                                        
                                    }
                                }
                           if (subcadena.hasMoreTokens()){
                        //System.out.println("Sobran argumentos de entrada");//Quitar
                        //Faltarian argumentos

                    }
                      else {
                    //System.out.println("------FIN-------");
                    fact = operaciones.Factura_Cliente(ni, me);
                    try{
                            fichero.writeBytes("FACTURA CLIENTE\n");
                            fichero.writeBytes("Cliente: " + fact.Dar_Cliente() + " " +
                            fact.Dar_Nif() + " " +fact.Dar_Direccion() +"\n");
                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en reunion");
                            }

                   for (int i=0; i<fact.Dar_Long();i++) {
                    try{
                            int Hora = fact.Dar_Horas(i);
                            String shora = Integer.toString(Hora);
                            int Coste = fact.Dar_Coste(i);
                            String scoste = Integer.toString(Coste);
                            int DiaReunion = fact.Dar_FechaInicio(i).get(Calendar.DATE);
                            //System.out.println("El dia que cogemos es " +  DiaReunion );
                            int MesReunion = fact.Dar_FechaInicio(i).get(Calendar.MONTH);
                            MesReunion = MesReunion + 1; //Leemos y para imprimir incrementamos en uno xq
                                                         //en Calendar va de 0-11;
                            //System.out.println("El mes que cogemos es " + MesReunion);
                            String SDiaReunion = Integer.toString(DiaReunion);
                            String SMesReunion = Integer.toString(MesReunion);
                            if (SDiaReunion.length() == 1) {
                                SDiaReunion = "0"+ SDiaReunion;
                            }
                            if (SMesReunion.length() == 1) {
                                SMesReunion = "0"+ SMesReunion;
                            }
                            if (scoste.length() == 1) {
                                scoste = "0000"+ scoste;
                            }
                            else if (scoste.length() == 2) {
                                scoste = "000"+ scoste;
                            }
                            else if (scoste.length() == 3) {
                                scoste = "00"+ scoste;
                            }
                            else if (scoste.length() == 4) {
                                scoste = "0"+ scoste;
                            }
                            if (shora.length() == 1) {
                                shora = "00"+ shora;
                            }
                            else if (shora.length() == 2) {
                                shora = "0"+ shora;
                            }
                         fichero.writeBytes(fact.Dar_NomReunion(i)+ " ");
                         fichero.writeBytes(fact.Dar_NomSala(i)+ " ");
                         fichero.writeBytes(SDiaReunion + "-" + SMesReunion + "-"
                                            + fact.Dar_FechaInicio(i).get(Calendar.YEAR)+ " " );
                         fichero.writeBytes(shora + " ");
                         fichero.writeBytes(scoste+ "\n");
                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en escribir reuniones");
                            }
                   }
                    try{
                            //int Total = fact.Dar_Total();
                            fichero.writeBytes("Total: " + fact.Dar_Total()+"\n");
                            fichero.writeBytes("FIN FACTURA CLIENTE\n");
                            }
                             catch (IOException e)
                            {
                          //System.out.println("Error: Excepcion en reunion");
                            }

                      }



                        }
                             }
                        }


                    }


                else {
//                    atrib = new Atributos();
//                    atrib.settarea("NOEXISTE");
//                    atrib.setnum_error(999);
                    //System.out.println("El primer argumento no corresponde a ninguna orden");
                    Error_Parametros(100);
                    //////return null;
//                    return atrib;
                }


            }
        }
        //////return null;
    }
/*=========================================================================
 =================================ESCRITURA==================================
=========================================================================*/
    public void Error_Parametros (int error) {

        try {
         if (error == 10) {
             //System.out.println("Entro error 10");
        fichero.writeBytes("ERROR: El mandato ASR consta de  3 parametros: ns,cs,ps\n");
        }
         else if (error == 20) {
             //System.out.println("Entro error 20");
        fichero.writeBytes("ERROR: El mandato ESR consta de 1 parametro: ns\n");
        }
         else if (error == 30) {
             //System.out.println("Entro error 30");
        fichero.writeBytes("ERROR: El mandato ACL consta de 3 parametros: ni,nc,df\n");
        }
         else if (error == 40) {
             //System.out.println("Entro error 40");
        fichero.writeBytes("ERROR: El mandato ECL consta de 1 parametros: ni\n");
        }
         else if (error == 50) {
             //System.out.println("Entro error 50");
        fichero.writeBytes("ERROR: El mandato ARE consta de  7 parametros: nr,as,fi,hi,ff,hf,ni\n");
        }
         else if (error == 60) {
             //System.out.println("Entro error 60");
        fichero.writeBytes("ERROR: El mandato MRD consta de 1 parametros: fm\n");
        }
         else if (error == 70) {
             //System.out.println("Entro error 70");
        fichero.writeBytes("ERROR: El mandato MOS consta de 2 parametros: ns,me\n");
        }
         else if (error == 80) {
             //System.out.println("Entro error 80");
        fichero.writeBytes("ERROR: El mandato MDS consta de 2 parametros: fi,ff\n");
        }
         else if (error == 90) {
             //System.out.println("Entro error 90");
        fichero.writeBytes("ERROR: El mandato CFC consta 2 parametros: ni,me\n");
        }
else if (error == 100) {
             //System.out.println("Entro error 100");
        fichero.writeBytes("ERROR: El mandato introducido no corresponde a ningun mandato\n");
        }

         else if (error == 11){
        //System.out.println("Entro error 11");
        fichero.writeBytes("ERROR: El formato de ns es incorrecto\n");
         }
         else if (error == 21){
        //System.out.println("Entro error 21");
        fichero.writeBytes("ERROR: El formato de cs es incorrecto\n");
         }
         else if (error == 31){
        //System.out.println("Entro error 31");
        fichero.writeBytes("ERROR: El formato de ps es incorrecto\n");
         }
        else if (error == 41){
        //System.out.println("Entro error 41");
        fichero.writeBytes("ERROR: El formato de ni es incorrecto\n");
        }
        else if (error == 51){
        //System.out.println("Entro error 51");
        fichero.writeBytes("ERROR: El formato de nc es incorrecto\n");
        }
        else if (error == 61){
        //System.out.println("Entro error 61");
        fichero.writeBytes("ERROR: El formato de df es incorrecto\n");
        }
         else if (error == 71){
        //System.out.println("Entro error 71");
        fichero.writeBytes("ERROR: El formato de nr es incorrecto\n");
        }
       else if (error == 81){
        //System.out.println("Entro error 81");
        fichero.writeBytes("ERROR: El formato de as es incorrecto\n");
        }
       else if (error == 91){
        //System.out.println("Entro error 91");
        fichero.writeBytes("ERROR: El formato de fi es incorrecto\n");
        }
        else if (error == 101){
        //System.out.println("Entro error 101");
        fichero.writeBytes("ERROR: El formato de hi es incorrecto\n");
        }
         else if (error == 111){
        //System.out.println("Entro error 111");
        fichero.writeBytes("ERROR: La hora inicial se sale de rango \n");
        }
        else if (error == 121){
        //System.out.println("Entro error 121");
        fichero.writeBytes("ERROR: El formato de ff es incorrecto\n");
        }
       else if (error == 131){
        //System.out.println("Entro error 131");
        fichero.writeBytes("ERROR: El formato de hf es incorrecto\n");
        }
        else if (error == 141){
        //System.out.println("Entro error 141");
        fichero.writeBytes("ERROR: La hora final se sale de rango \n");
        }
         else if (error == 151){
        //System.out.println("Entro error 151");
        fichero.writeBytes("ERROR: La fecha inicial es posterior a la fecha final\n");
        }
         else if (error == 161){
        //System.out.println("Entro error 161");
        fichero.writeBytes("ERROR: El formato de fm es incorrecto\n");
        }
          else if (error == 171){
        //System.out.println("Entro error 171");
        fichero.writeBytes("ERROR: El formato de me es incorrecto\n");
        }


         else {
             //System.out.println("No error");
         }

        } catch (Exception e) {
            //System.out.println("Excepcion en error\n");
        }
    }


    public void Escritura_Fichero(String nombre) {
    try
    {
      fd = new  FileOutputStream (nombre);
      fichero = new DataOutputStream (fd);
     this.fs = new FileWriter(nombre);
     this.bw = new BufferedWriter(fs);
    }
    catch

(Exception e)
    {
        //System.out.println("Error:el fichero "+ nombre + " no ha podido ser creado ");

    }

    }
    public void Asr_Correcto (String ns) {
        try{
            fichero.writeBytes("Creada sala: " + ns +"\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en la creacion de sala");
         }
    }
    public void Asr_Incorrecto (String ns) {
        try{
            fichero.writeBytes("ERROR: La sala " + ns +" no ha podido ser creada\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en la creacion de sala");
         }
    }
     public void Esr_Correcto (String ns) {
         try{
            fichero.writeBytes("Eliminada sala: " + ns+"\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en la eliminacion sala");
         }
     }
     public void Esr_Incorrecto (String ns) {
        try{
            fichero.writeBytes("ERROR: La sala " + ns +" no ha podido ser eliminada\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en la creacion de sala");
                  }
     }

     public void Acl_Correcto (String ni) {
         try{
            fichero.writeBytes("Agregado Cliente: " + ni+"\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en la agregacion cliente");
         }
     }
     public void Acl_Incorrecto (String ni) {
        try{
            fichero.writeBytes("ERROR: El cliente " + ni +" no ha podido ser agregado\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en agregar cliente");
                  }
     }

     public void ECL_Correcto (String ni) {
         try{
            fichero.writeBytes("Eliminado cliente: " + ni+"\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en la eliminacion cliente");
         }
     }

     public void Ecl_Incorrecto (String ni) {
        try{
            fichero.writeBytes("ERROR: El cliente " + ni +" no ha podido ser eliminado\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en la eliminacion cliente");
                  }
     }

     public void Are_Correcto (String nr, String ni, String ns ) {
         try{
            //System.out.println("El nombre de la sala en agregar reunion es ---------> " + ns);
            fichero.writeBytes("Agregada reunion: " +nr+ " " +ni+ " ");
            fichero.writeBytes( ns+"\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en agregar reunion");
         }
     }
public void Are_Incorrecto (String nr, String ni, String ns) {
        try{
            fichero.writeBytes("ERROR: La reunion" + nr +" no ha podido ser agregada por el cliente "+ni+   "\n");
            }
         catch (IOException e)
         {
             //System.out.println("Error: Excepcion en la eliminacion cliente");
                  }
     }


/*=========================================================================
 =================================PRUEBAS==================================
=========================================================================*/



     public static void main(String[] args) {
        LecturaEscritura prueba = new LecturaEscritura();




        //boolean resultado ;
        //int dias;
        //----------------Prueba Fechas----------------------//
        //String fi = "25-04-2010 10:00";
        //String ff = "01-05-2010 11:00";
        //prueba.resta_fechas(fi, ff);

        //---------------------------------------------------//
         //n =  prueba.Lectura_Fichero("C:\Java\Entrada_Salida\src\texto.txt");
         //prueba.Lectura_Fichero("entrttadafi1.txt","salidafi1.txt");
         prueba.Lectura_Fichero(args[0],args[1]);
         
         //while (prueba.bf != null) {
           //  prueba.Leer_Linea();
         //}
         //prueba.AbrirFichero("Salida.doc");
         //System.out.println("El fichero existe");
         //W.Escritura_Fichero("Salida.txt");
//         prueba.Leer_Linea();
//         System.out.println("ya");
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
//         prueba.Leer_Linea();
         //dias = prueba.dia_mes(12, 2008);
         //System.out.println("el numero de dias de la fecha introducida es = " + dias);
         //resultado = prueba.formato_fecha(31, 11, 1986);
         //System.out.println("EL formato de la fecha es " + resultado);
        // resultado = prueba.formato_hora(8, 59);
         //System.out.println("EL formato de la hora es " + resultado);
//         resultado = prueba.formato_ni("470304942");
//         System.out.println("EL formato del dni es " + resultado);
//
//         if (!resultado) {
//             System.out.println("El dni es incorrecto");
//         }
//         else {
//             System.out.println("El dni es correcto");
//         }



    }
}

