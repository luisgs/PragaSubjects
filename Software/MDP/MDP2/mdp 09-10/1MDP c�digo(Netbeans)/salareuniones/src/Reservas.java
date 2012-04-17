import java.util.*;
/*
 * author Javier Bustamante
*/
public class Reservas {
//private String Nombre_Sala,Nif,Nombre_Cliente,Direccion_Facturacion, Nombre_Reunion,fi,ff,hf,hi;
//private int Capacidad_Sala,Precio_Sala,Asistentes_Reunion;
//private List
GregorianCalendar Fecha_R = (GregorianCalendar) Calendar.getInstance();
//Instanciamos las Listas.
private Lista_Salas LSalas = new Lista_Salas();
private Lista_Clientes LClientes = new Lista_Clientes();
private ListaReuniones LReuniones = new ListaReuniones();

protected class Reserva
{
    String Nombre_Sala;
    String Nombre_Reunion;
    String Nif;

    public void Reserva (String sala, String Reunion, String Numnif)
    {
        Nombre_Sala = sala;
        Nombre_Reunion = Reunion;
        Nif = Numnif;
    }
}

private ArrayList<Reserva> Lista_Reservas = new ArrayList<Reserva>();

public String Dar_Nom_Sala_Reu (String R)
{
    for (int i = 0; i<Lista_Reservas.size(); i++)
    {
        if (R.compareTo(Lista_Reservas.get(i).Nombre_Reunion) == 0)
        {
            return Lista_Reservas.get(i).Nombre_Sala;
        }
    }
    return null;
}

public boolean Agregar_Sala_Reunion(String Nombre, int Capacidad, int Precio)
{
    if (LSalas.Agregar_Sala(Nombre, Capacidad, Precio))
    {
        return true;
    }
    else
    {
        return false;
    }
}


public boolean Eliminar_Sala_Reunion(String Nombre)
{
    for (int i = 0; i< Lista_Reservas.size();i++)
    {
        Reserva Reserv = Lista_Reservas.get(i);
        if (Nombre.compareTo(Reserv.Nombre_Sala) == 0)
        {
            return false;
        }
    }

    if (LSalas.Eliminar_Sala(Nombre))
    {
        return true;
    }
    else
    {
        return false;
    }
}

public boolean Agregar_Un_Cliente (String Nif, String Nombre, String Direccion)
{
    if (LClientes.Agregar_Clientes(Nif, Nombre, Direccion))
    {
        return true;
    }
    else
    {
        return false;
    }
}

public boolean Eliminar_Un_Cliente (String Nif)
{
    boolean res;
    //System.out.println(Nif);
    if (LClientes.Eliminar_Cliente(Nif))//Si el cliente existe se eliminan 
                                        //todas sus reuniones y la reserva 
                                        //de las salas asociadas
    {
        //System.out.println("El cliente se ha eliminado y miramos si hay reservas");
        for (int i=0; i<Lista_Reservas.size();i++)
        {
            Reserva Reserv = Lista_Reservas.get(i);
        //    System.out.println(Nif+"<->"+Reserv.Nombre_Reunion);
            if (Nif.compareTo(Reserv.Nif) == 0)
            {
                //System.out.println("-------ELIMINO");
                //Elimino Reserva del cliente y de la Lista_Reservas
                LReuniones.Eliminar_Reunion(Reserv.Nombre_Reunion);
                Lista_Reservas.remove(i);
                i--;//Al eliminar una entrada el indice i apuntará a la siguiente entrada y
                    //cuando incrementemos en el for, nos habremos saltado la nueva entrada i
                    //que antes de eliminar era i+1. Por lo tanto ponemos el puntero i una posición por detras.
            }
        }
        return true;
    }
    else//Si el cliente no existe
    {
        return false;
    }
}


private boolean Fechas_Solapadas (GregorianCalendar ini_dato,GregorianCalendar fin_dato,
                                     GregorianCalendar ini_lista,GregorianCalendar fin_lista)
{ 
    
if ((ini_lista.after(fin_dato)) || (ini_dato.after(fin_lista)))//3>2 or 1>4
    return false;
return true;
}

/*
//Agrega a Lista_Reservas los elementos ordenados de menor a mayor fecha de inicio
private boolean Agregar_Lista_Ordenada (String sala, String reunion, String Nif)
{
    GregorianCalendar fech = LReuniones.Fechainicio(reunion);
    int n=1;
    boolean fecha_insertada = false;
    Tipo_Reservas Reserv = new Tipo_Reservas();
    Reserv.Reserva(sala, reunion, Nif);

    if (Lista_Reservas.isEmpty())
    {
        Lista_Reservas.add(Reserv);
        fecha_insertada = true;
    }
    else
    {
        while (fecha_insertada == false)
        {
            GregorianCalendar fechlist = LReuniones.Fechainicio(Lista_Reservas.get(n).Nombre_Reunion);
            if (fech.after(fechlist) && 
                LReuniones.Fechainicio(Lista_Reservas.get(n).Nombre_Reunion) != LReuniones.Fechainicio(reunion))
            {
                Lista_Reservas.add(Reserv);
                fecha_insertada = true;
            }
            else if (fech.before(fechlist))
            {
                Lista_Reservas.add(n, Reserv);
                fecha_insertada = true;
            }
            else
            {
                fecha_insertada =false;
            }
            n=n+1;
        }
    }
    return true;
}*/

//Agrega a Lista_Reservas los elementos ordenados de menor a mayor reunion y menor a mayor cliente
private boolean Agregar_Lista_Ordenada (String sala, String reunion, String Nif)
{
    //GregorianCalendar fech = LReuniones.Fechainicio(reunion);
    int n=0;
    boolean fecha_insertada = false;
    Reserva Reserv = new Reserva();
    Reserv.Reserva(sala, reunion, Nif);

    //System.out.println("agreAR lista ordenada");

    if (Lista_Reservas.isEmpty())
    {
//        System.out.println("Lista vacia");
        Lista_Reservas.add(Reserv);
        fecha_insertada = true;
    }
    else
    {
//        System.out.println("Lista ordenDA no vacia");
        while (fecha_insertada == false)
        {
//            System.out.println("entramos en bucle");
//            System.out.println(Nif);
//            System.out.println(LClientes.Nombre(Lista_Reservas.get(n).Nif));
//            System.out.println(LClientes.Nombre(Nif));
//            System.out.println(LClientes.Nombre(Lista_Reservas.get(n).Nif).compareTo(LClientes.Nombre(Nif)));

            if ((Lista_Reservas.get(n).Nombre_Reunion).compareTo(reunion) < 0 &&
                    (n == Lista_Reservas.size()-1))
            {
//                System.out.println("insertamos en ultimo lugar de la lista");
                Lista_Reservas.add(Reserv);
                fecha_insertada = true;
            }
            else if (((Lista_Reservas.get(n).Nombre_Reunion).compareTo(reunion) >= 0))
            {
//                System.out.println("estamos en n");
                Lista_Reservas.add(n,Reserv);
                fecha_insertada = true;
            }
            else
            {
//                System.out.println("siguo");
                fecha_insertada = false;
//                System.out.println("Debemos insertar");
//                if ((LClientes.Nombre(Lista_Reservas.get(n).Nif)).compareTo(LClientes.Nombre(Nif)) < 0)
//                {
//                    fecha_insertada = false;
//                }
//                else if ((LClientes.Nombre(Lista_Reservas.get(n).Nif)).compareTo(LClientes.Nombre(Nif)) > 0)
//                {
//                    Lista_Reservas.add(n,Reserv);
//                    fecha_insertada = true;
//                }
            }
            n=n+1;
        }
    }

//    System.out.println("");
//    for (int i=0; i < Lista_Reservas.size();i++)
//    {
//        System.out.println(Lista_Reservas.get(i).Nif);
//        System.out.println(Lista_Reservas.get(i).Nombre_Reunion);
//        System.out.println("");
//    }
    return true;
}

public boolean Agregar_Una_Reunion(String Nombre, int Asistentes, GregorianCalendar Fecha_I,
                                    GregorianCalendar Fecha_F, String Nif)
{
    //System.out.println("Llego");
    boolean agregar = true;
    int n;
    boolean sala_correcta = false;
    String sala_way = "";
    GregorianCalendar Fecha_I_Lista;
    GregorianCalendar Fecha_F_Lista;
    int nf = 0;

    if (LClientes.Comparar(Nif))
    {

        while (sala_correcta == false)
        {
            sala_way = LSalas.Sala_Optima(nf, Asistentes);
    //        System.out.println(sala_way);
            sala_correcta = true;
            if (sala_way==null)
            {
               sala_correcta = false;
               break;
            }
            //Con cada entrada de Lista_Reservas
            n = 0;
            while (n < Lista_Reservas.size())
            {
    //                System.out.println(n);
                //--Si sala no coincide agregar = true
                    if ((sala_way.compareTo(Lista_Reservas.get(n).Nombre_Sala)) != 0)
                    {
    //                  System.out.println("Los nombre de sala no coinciden");
                        agregar = true;
                        sala_correcta = true;
                    }
                //--Si la sala coincide comprobamos las fechas de cada reunión cuya sala coincida

                else
                    {
                //----Si no estan solapadas -> agregar=true y salimos del bucle
    //                    System.out.println("Los nombre de sala coinciden");
    //                    System.out.println("CREAMod calendarsxa comparar");
                        Fecha_I_Lista = LReuniones.Fechainicio(Lista_Reservas.get(n).Nombre_Reunion);
    //                    System.out.println("hora de ininio----"+Fecha_I_Lista.get(Calendar.HOUR_OF_DAY));
                        Fecha_F_Lista = LReuniones.Fechafin(Lista_Reservas.get(n).Nombre_Reunion);
    //                    System.out.println("hora de fin-------"+Fecha_F_Lista.get(Calendar.HOUR_OF_DAY));
                        //System.out.println("terminamos de crear fechas");
                        //System.out.println("compramos fechas");
                        if (Fechas_Solapadas(Fecha_I,Fecha_F,Fecha_I_Lista,Fecha_F_Lista)==false)
                        {
    //                        System.out.println("las fechas no se solapan");
                            agregar = true;
                            sala_correcta = true;
                        }
                //----Si Fechas solapadas -> agregar=false
                        else
                        {
    //                        System.out.println("las fechas se solapan");
                            agregar = false;
                            sala_correcta = false;
                            break;
                        }
                    }
                    n=n+1;
                }

            nf++;
    //        System.out.println("sumamos num errores para sala optima ----"+nf);
            }


    //    System.out.println("intentamos agregar -----"+ Nombre);
        if (sala_correcta == true && agregar == true &&
            LReuniones.agregarReunion(Nombre,Asistentes,Fecha_I,Fecha_F) == true)//Si agregar=true agregamos
        {
            Agregar_Lista_Ordenada (sala_way, Nombre, Nif);
            return true;
        }
        else//Si agregar=false no podemos agregar
    //        System.out.println("nos vamos por else");
            return false;
    }
    else
    {
        return false;
    }
}

public ReunionesDia Mostrar_Reuniones_Dia (GregorianCalendar fecha)
{
    //Hay que tener en cuenta que la fecha tiene 0 en la hora y el minuto
//    System.out.println(Lista_Reservas.size());
    ReunionesDia ReuDias = new ReunionesDia();
    GregorianCalendar HoraI = new GregorianCalendar();
    GregorianCalendar HoraF = new GregorianCalendar();
    int n = 0;
    Calendar FechaICompar = new GregorianCalendar(fecha.get(Calendar.YEAR),
            fecha.get(Calendar.MONTH),fecha.get(Calendar.DATE),9,0);
    Calendar FechaFCompar = new GregorianCalendar(fecha.get(Calendar.YEAR),
            fecha.get(Calendar.MONTH),fecha.get(Calendar.DATE),19,0);
    //Mientras no alcancemos el final de lista reservas
    while (n < Lista_Reservas.size())
    {
        //Si la fechaICompar es mayor que FechaFinal o fechaFcompar en menor que FechaInicial
        //no añadimos, en otro caso añadimos xq Lista_Reservas está ordenada
        if (FechaICompar.after(LReuniones.Fechafin(Lista_Reservas.get(n).Nombre_Reunion)) ||
            FechaFCompar.before(LReuniones.Fechainicio(Lista_Reservas.get(n).Nombre_Reunion)))
        {
            n= n + 1;
        }
        else
        {
            if (fecha.get(Calendar.DATE) != LReuniones.Fechainicio(Lista_Reservas.get(n).Nombre_Reunion).get(Calendar.DATE))
            {
                HoraI = new GregorianCalendar(fecha.get(Calendar.YEAR),
                        fecha.get(Calendar.MONTH),fecha.get(Calendar.DATE),9,0);
            }
            else
            {
                HoraI = LReuniones.Fechainicio(Lista_Reservas.get(n).Nombre_Reunion);
            }

            if (fecha.get(Calendar.DATE) != LReuniones.Fechafin(Lista_Reservas.get(n).Nombre_Reunion).get(Calendar.DATE))
            {
                HoraF = new GregorianCalendar(fecha.get(Calendar.YEAR),
                        fecha.get(Calendar.MONTH),fecha.get(Calendar.DATE),19,0);
            }
            else
            {
                HoraF = LReuniones.Fechafin(Lista_Reservas.get(n).Nombre_Reunion);
            }
//            System.out.println("agrega reunion"+ Lista_Reservas.get(n).Nombre_Reunion);
            ReuDias.AñadirReunionDia (Lista_Reservas.get(n).Nombre_Reunion,
                    LClientes.Nombre(Lista_Reservas.get(n).Nif),
                    Lista_Reservas.get(n).Nombre_Sala,
                    HoraI, HoraF);
            n = n + 1;
        }
    }
    return ReuDias;
}

public SalasMes Mostrar_Ocupacion_Sala_Mes (String Sala, GregorianCalendar Mes)
{
//    System.out.println("entramos ocup salas mes");
    SalasMes Salmes = new SalasMes(Sala, LSalas.Capacidad(Sala));
    
    for (int n=1;n<=31;n++)
    {
//        System.out.println("entramos en el for");
        //Cliente, Reunion, Fecha
        GregorianCalendar fecha = new GregorianCalendar(Mes.get(Calendar.YEAR),
                Mes.get(Calendar.MONTH), n);
        Calendar FechaICompar = new GregorianCalendar(fecha.get(Calendar.YEAR),
                fecha.get(Calendar.MONTH),fecha.get(Calendar.DATE),9,0);
        Calendar FechaFCompar = new GregorianCalendar(fecha.get(Calendar.YEAR),
                fecha.get(Calendar.MONTH),fecha.get(Calendar.DATE),19,0);
//        System.out.println("Dia---------"+n);
        int m = 0;
        while (m < Lista_Reservas.size())
        {
            //Si la fechaICompar es mayor que FechaFinal o fechaFcompar en menor que FechaInicial
            //no añadimos xq la reunion , en otro caso añadimos xq Lista_Reservas está ordenada
            if (FechaICompar.after(LReuniones.Fechafin(Lista_Reservas.get(m).Nombre_Reunion)) ||
                FechaFCompar.before(LReuniones.Fechainicio(Lista_Reservas.get(m).Nombre_Reunion))
                || Sala.compareTo(Lista_Reservas.get(m).Nombre_Sala)!=0)
            {
//                System.out.println("no añadimos la sala");
                m = m + 1;
            }
            else
            {
//                System.out.println("añadimos sala de dia");
                Salmes.AnadirSalaMes(LClientes.Nombre(Lista_Reservas.get(m).Nif), Lista_Reservas.get(m).Nombre_Reunion, fecha);
                m = m + 1;
            }
        }
    }
    return Salmes;
}

public SalasDisp Mostrar_Disponibilidad_Salas_Tiempo (GregorianCalendar FechaI,
                                                      GregorianCalendar FechaF)
{
//    System.out.println("entramos en disponibilidad");
    SalasDisp SDisp = new SalasDisp();

    int n = 0;
    String Sala_Actual;
    String Sala_Anterior = "";//Ponemos "   " para diferenciar el primer caso
                               //y que no sea ni = al primer nombre de sala ni a
                               //un string vacio.
    while (Sala_Anterior != null)//Mientas no alcSala_Actualancemos el limite de capacidad
    {
//        System.out.println("entramos en while");
        Sala_Actual = LSalas.Sala_Optima(n, 1);
//        System.out.println("Sala----------->"+Sala_Actual+"---n = "+n);
        if (Sala_Actual == null)
        {
            Sala_Anterior = Sala_Actual;//para salir del bucle
        }
        else if ((Sala_Anterior.equals(Sala_Actual)))
        {
            Sala_Anterior = Sala_Actual;
            n = n + 1;
        }
        else
        {
            //System.out.println(Sala_Actual);
            //System.out.println("salas distintas");
            int m = 0;
            if(Lista_Reservas.size()==0)
            {
                SDisp.AñadirSalaDispOrdenado(Sala_Actual, LSalas.Capacidad(Sala_Actual),
                                                         LSalas.PrecioHora(Sala_Actual));
            }
            else
            {
                while (m < Lista_Reservas.size())
                {
    //                System.out.println("entramos en while 2");
                    GregorianCalendar FechaILista = LReuniones.Fechainicio(Lista_Reservas.get(m).Nombre_Reunion);
                    GregorianCalendar FechaFLista = LReuniones.Fechafin(Lista_Reservas.get(m).Nombre_Reunion);
                    if (Sala_Actual.equals(Lista_Reservas.get(m).Nombre_Sala))
                    {
    //                    System.out.println("La sala coincide");
                        if (Fechas_Solapadas(FechaI,FechaF,FechaILista,FechaFLista))//Si Fechas solapadas
                                                                       //Salimos del bucle para coger la siguiente sala
                        {
    //                        System.out.println("fechas solapadas");
                            Sala_Anterior = Sala_Actual;
                            //n = n + 1;
                            break;
                        }
                        else if (m == Lista_Reservas.size()-1 &&
                                (!Fechas_Solapadas(FechaI,FechaF,FechaILista,FechaFLista)))
                        {
    //                        System.out.println("añadimos una sala-------"+Sala_Actual);
                            SDisp.AñadirSalaDispOrdenado(Sala_Actual, LSalas.Capacidad(Sala_Actual),
                                                         LSalas.PrecioHora(Sala_Actual));
                            Sala_Anterior = Sala_Actual;
                            m = m + 1;
                        }
                        else
                        {
    //                        System.out.println("seguimos");
                            m = m + 1;
                        }
                        //System.out.println("sala anterior"+Sala_Anterior);
                    }
                    else if(m == Lista_Reservas.size()-1 &&
                            !(Sala_Actual.equals(Lista_Reservas.get(m).Nombre_Sala)))
                    {
    //                    System.out.println("añadimos una sala-------"+Sala_Actual);
                        SDisp.AñadirSalaDispOrdenado(Sala_Actual, LSalas.Capacidad(Sala_Actual),
                                                         LSalas.PrecioHora(Sala_Actual));
                        Sala_Anterior = Sala_Actual;
                        m = m + 1;
                    }
                    else
                    {
    //                    System.out.println("la sala no coincide");
                        m++;
                    }
                }
            }
            n++;
        }
    }
    return SDisp;
}

//public int Calcula_Horas_Solo_En_El_Mes (GregorianCalendar FechaI, GregorianCalendar FechaF, GregorianCalendar Mes)
//{
//    int Horas = 0;
//    GregorianCalendar HIC = new GregorianCalendar(Mes.get(Calendar.YEAR), Mes.get(Calendar.MONTH),1,9,0);
//    GregorianCalendar HFC = new GregorianCalendar(Mes.get(Calendar.YEAR), Mes.get(Calendar.MONTH),
//            Mes.getActualMaximum(Calendar.DATE),19,0);
//    int DiaMax = Mes.getActualMaximum(Calendar.DATE);
//
//    if (FechaI.before(HIC) && ((FechaF.before(HFC) && FechaF.after(HIC)) ||
//            FechaF.equals(HFC)))//Empieza antes del mes y termina en él
//    {
//        if (FechaF.get(Calendar.MINUTE) != 0)
//        {
//            Horas = 1;
//        }
//        Horas = Horas + FechaF.get(Calendar.HOUR_OF_DAY)-9;
//        Horas = Horas + (10 * (FechaF.get(Calendar.DATE)-1));
//    }
//    else if ((FechaI.after(HIC) && FechaI.before(HFC) || FechaI.equals(HIC)) &&
//            FechaF.after(HFC))//Empieza en el mes y termina despues del mismo
//    {
//        Horas = Horas + (19 - FechaI.get(Calendar.HOUR_OF_DAY));
//        Horas = Horas + ((DiaMax - FechaI.get(Calendar.DATE))*10);
//    }
//    else if (FechaI.before(HIC) && FechaF.after(HFC))//Empieza antes del mes y termina despues del mismo
//    {
//        Horas = Horas + (DiaMax * 10);
//    }
//    else if ((FechaI.after(HIC) || FechaI.equals(HIC)) &&
//            (FechaF.before(HFC) || FechaF.equals(HFC)))//Empieza y termina en el mes
//    {
//        if (FechaF.get(Calendar.MINUTE) != 0)
//        {
//            Horas = 1;
//        }
//        Horas = Horas + (19 - FechaI.get(Calendar.HOUR_OF_DAY)) + (FechaF.get(Calendar.HOUR_OF_DAY) - 9);
//        Horas = Horas + (10 * ((FechaF.get(Calendar.DATE))-(FechaI.get(Calendar.DATE))));
//    }
//    else //Empieza y termina antes o despues del mes
//    {
//        Horas = 0;
//    }
//    return Horas;
//}


private int Calcula_Horas (GregorianCalendar FechaI, GregorianCalendar FechaF, GregorianCalendar Mes)
{
    int horas = 0;
    GregorianCalendar FI = new GregorianCalendar(FechaI.get(Calendar.YEAR), FechaI.get(Calendar.MONTH), FechaI.get(Calendar.DATE));
    //GregorianCalendar FI = FechaI;
    //FI.set(Calendar.HOUR_OF_DAY, 0);
    //FI.set(Calendar.MINUTE, 0);
    GregorianCalendar FF = new GregorianCalendar(FechaF.get(Calendar.YEAR), FechaF.get(Calendar.MONTH), FechaF.get(Calendar.DATE));
    //GregorianCalendar FF = FechaF;
    //FF.set(Calendar.HOUR_OF_DAY, 0);
    //FF.set(Calendar.MINUTE, 0);
    GregorianCalendar FFX = new GregorianCalendar(FechaF.get(Calendar.YEAR), FechaF.get(Calendar.MONTH), FechaF.get(Calendar.DATE)+1);
    //FFX.set(Calendar.DATE, (FechaF.get(Calendar.DATE))+1);
    GregorianCalendar FC = new GregorianCalendar(FechaI.get(Calendar.YEAR), FechaI.get(Calendar.MONTH), FechaI.get(Calendar.DATE));

//    System.out.println(FI);
//    System.out.println(FF);
    if (FI.equals(FF))
    {
        horas = FechaF.get(Calendar.HOUR_OF_DAY)-FechaI.get(Calendar.HOUR_OF_DAY);
    }
    else
    {
        while (!(FC.equals(FFX)))
        {
            //Calculamos y sumamos horas
            if (FC.equals(FI))
            {
                horas = horas + (19 - FechaI.get(Calendar.HOUR_OF_DAY));
            }
            else if (FC.equals(FF))
            {
                horas = horas + (FechaF.get(Calendar.HOUR_OF_DAY)-9);
            }
            else
            {
                horas = horas + 10;
            }

            //Incrementamos fecha
            if (FC.get(Calendar.DATE) == FC.getActualMaximum(Calendar.DATE))
            {
                FC.set(Calendar.DATE, 1);
                if (FC.get(Calendar.MONTH) == 11)
                {
                   FC.set(Calendar.MONTH, 0);
                   FC.set(Calendar.YEAR, FC.get(Calendar.YEAR)+1);
                }
                else
                {
                    FC.set(Calendar.MONTH, FC.get(Calendar.MONTH)+1);
                }
            }
            else
            {
                FC.set(Calendar.DATE, FC.get(Calendar.DATE)+1);
            }
//            System.out.println("horas------>"+horas);
        }
    }

    if (FechaF.get(Calendar.MINUTE)>0)
    {
        horas = horas + 1;
    }
    return horas;
}

public Factura Factura_Cliente (String Nif, GregorianCalendar Mes)
{
    Factura Fact = new Factura(LClientes.Nombre(Nif),Nif,LClientes.Direccion(Nif));
    int Horas;
    int coste;
    int n = 0;
    while (n < Lista_Reservas.size())
    {
        if (Nif.compareTo(Lista_Reservas.get(n).Nif) == 0)
        {
            String reuni = Lista_Reservas.get(n).Nombre_Reunion;
            String Sal = Lista_Reservas.get(n).Nombre_Sala;
//            System.out.println("nif coincide"+reuni);
            GregorianCalendar FechaI = LReuniones.Fechainicio(Lista_Reservas.get(n).Nombre_Reunion);
            GregorianCalendar FechaF = LReuniones.Fechafin(Lista_Reservas.get(n).Nombre_Reunion);
            Horas = Calcula_Horas (FechaI, FechaF, Mes);
            if (Horas != 0 && FechaF.get(Calendar.MONTH)==Mes.get(Calendar.MONTH))
            {
//                System.out.println("horas distibto de 0 -----> y son "+Horas+" horas");
                coste = Horas * LSalas.PrecioHora(Lista_Reservas.get(n).Nombre_Sala);
                Fact.AñadirCosteReunion(reuni, Sal, FechaI, Horas, coste);
            }
        }
        n = n + 1;
    }
    return Fact;
}
}