import java.util.*;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args)
    {

//        Lista_Salas LS = new Lista_Salas();
//
//        LS.Agregar_Sala("Sala1", 1, 11);
//        System.out.println("agregada sala 1");
//        LS.Agregar_Sala("Sala2", 1, 11);
//        System.out.println("agregada sala 2");
//        LS.Agregar_Sala("Sala3", 3, 13);
//        System.out.println("agregada sala 3");
//        LS.Agregar_Sala("Sala4", 5, 15);
//        System.out.println("agregada sala 4");
//        LS.Agregar_Sala("Sala5", 5, 15);
//        System.out.println("agregada sala 5");
//        LS.Agregar_Sala("Sala6", 5, 15);
//        System.out.println("agregada sala 6");
//        LS.Agregar_Sala("Sala7", 7, 17);
//        System.out.println("agregada sala 7");
//
//        System.out.println(LS.Sala_Optima(0, 4));
//        System.out.println(LS.Sala_Optima(1, 4));
//        System.out.println(LS.Sala_Optima(2, 4));
//        System.out.println(LS.Sala_Optima(3, 4));
//        System.out.println(LS.Sala_Optima(0, 8));
//        System.out.println(LS.Sala_Optima(0, 1));
//        System.out.println(LS.Sala_Optima(1, 1));
//        System.out.println(LS.Sala_Optima(2, 1));
//        System.out.println(LS.Sala_Optima(3, 1));
//        System.out.println(LS.Sala_Optima(4, 1));
//        System.out.println(LS.Sala_Optima(5, 1));
//        System.out.println(LS.Sala_Optima(6, 1));
//        System.out.println(LS.Sala_Optima(7, 1));
//        System.out.println(LS.Sala_Optima(8, 1));

//          Lista_Clientes LC = new Lista_Clientes();
//
//        LC.Agregar_Clientes("Cli1", "CliUno", "Direccion1");
//        System.out.println("agregada cliente 1");
//        LC.Agregar_Clientes("Cli2", "CliDos", "Direccion2");
//        System.out.println("agregada cliente 2");
//
//        System.out.println(LC.Nombre("Cli1"));

////--------------------Eliminar clientes------------------------------
//        Reservas res = new Reservas();
//        res.Agregar_Sala_Reunion("Sala1", 1, 11);
//        System.out.println("agregada sala 1");
//        res.Agregar_Sala_Reunion("Sala2", 1, 11);
//        System.out.println("agregada sala 2");
//        res.Agregar_Sala_Reunion("Sala3", 3, 13);
//        System.out.println("agregada sala 3");
//        res.Agregar_Sala_Reunion("Sala4", 5, 15);
//        System.out.println("agregada sala 4");
//        res.Agregar_Sala_Reunion("Sala5", 5, 15);
//        System.out.println("agregada sala 5");
//        res.Agregar_Sala_Reunion("Sala6", 5, 15);
//        System.out.println("agregada sala 6");
//        res.Agregar_Sala_Reunion("Sala7", 7, 17);
//        System.out.println("agregada sala 7");
//
//        res.Agregar_Un_Cliente("Cli1", "Cli1Uno", "Direccion1");
//        System.out.println("agregada cliente 1");
//        res.Agregar_Un_Cliente("Cli2", "Cli2Dos", "Direccion2");
//        System.out.println("agregada cliente 2");
//
//        GregorianCalendar F1I = new GregorianCalendar(2010,6,2,10,00);
//        GregorianCalendar F1F = new GregorianCalendar(2010,6,2,12,00);
//        GregorianCalendar F2I = new GregorianCalendar(2010,4,15,13,00);
//        GregorianCalendar F2F = new GregorianCalendar(2010,4,16,17,00);
//
//
//        if (res.Agregar_Una_Reunion("Reu3", 1, F1I, F1F, "Cli1") == true)
//            System.out.println("agregada reunion 3");//En sala5
//        else
//            System.out.println("fallo reunion 3");
//
//        if (res.Agregar_Una_Reunion("Reu2", 4, F1I, F1F, "Cli1") == true)
//            System.out.println("agregada reunion 2");//En sala6
//
//        else
//            System.out.println("fallo reunion 2");
//
//        if (res.Agregar_Una_Reunion("Reu4", 4, F2I, F2F, "Cli2") == true)
//            System.out.println("agregada reunion 4");//En sala4
//        else
//            System.out.println("fallo reunion 4");
//
//        if (res.Agregar_Una_Reunion("Reu1", 2, F1I, F1F, "Cli1") == true)
//            System.out.println("agregada reunion 1");//En sala3
//        else
//            System.out.println("fallo reunion 1");
//
//
//        if (res.Agregar_Una_Reunion("Reu2", 1, F1I, F1F, "Cli1") == true) //mismo nombre!!!!
//        {
//            System.out.println("agregada reunion 2 b");
//            //System.out.println(res.Dar_Nom_Sala_Reu("Reu2"));
//        }
//        else
//            System.out.println("fallo reunion 2 b");
//
//
//        System.out.println(res.Dar_Nom_Sala_Reu("Reu1"));
//        System.out.println(res.Dar_Nom_Sala_Reu("Reu2"));
//        System.out.println(res.Dar_Nom_Sala_Reu("Reu3"));
//        System.out.println(res.Dar_Nom_Sala_Reu("Reu4"));
//        System.out.println("");
//        System.out.println(res.Eliminar_Un_Cliente("Cli1"));
//        System.out.println("");
//        System.out.println(res.Dar_Nom_Sala_Reu("Reu1"));
//        System.out.println(res.Dar_Nom_Sala_Reu("Reu2"));
//        System.out.println(res.Dar_Nom_Sala_Reu("Reu3"));
//        System.out.println(res.Dar_Nom_Sala_Reu("Reu4"));

//------------------mostrar reuniones dia--------------------------------------------------------------
//        Reservas res = new Reservas();
//        res.Agregar_Sala_Reunion("Sala1", 1, 11);
//        System.out.println("agregada sala 1");
//        res.Agregar_Sala_Reunion("Sala2", 1, 11);
//        System.out.println("agregada sala 2");
//        res.Agregar_Sala_Reunion("Sala3", 3, 13);
//        System.out.println("agregada sala 3");
//        res.Agregar_Sala_Reunion("Sala4", 5, 15);
//        System.out.println("agregada sala 4");
//        res.Agregar_Sala_Reunion("Sala5", 5, 15);
//        System.out.println("agregada sala 5");
//        res.Agregar_Sala_Reunion("Sala6", 5, 15);
//        System.out.println("agregada sala 6");
//        res.Agregar_Sala_Reunion("Sala7", 7, 17);
//        System.out.println("agregada sala 7");
//
//        res.Agregar_Un_Cliente("Cli1", "Cli1Uno", "Direccion1");
//        System.out.println("agregada cliente 1");
//        res.Agregar_Un_Cliente("Cli2", "Cli2Dos", "Direccion2");
//        System.out.println("agregada cliente 2");
//
//        GregorianCalendar F1I = new GregorianCalendar(2010,6,2,10,00);
//        GregorianCalendar F1F = new GregorianCalendar(2010,6,2,12,00);
//        GregorianCalendar F2I = new GregorianCalendar(2010,4,15,13,00);
//        GregorianCalendar F2F = new GregorianCalendar(2010,4,16,17,00);
//
//
//        if (res.Agregar_Una_Reunion("Reu3", 1, F1I, F1F, "Cli1") == true)
//            System.out.println("agregada reunion 3");//En sala5
//        else
//            System.out.println("fallo reunion 3");
//
//        if (res.Agregar_Una_Reunion("Reu2", 4, F1I, F1F, "Cli1") == true)
//            System.out.println("agregada reunion 2");//En sala6
//
//        else
//            System.out.println("fallo reunion 2");
//
//        if (res.Agregar_Una_Reunion("Reu4", 4, F2I, F2F, "Cli2") == true)
//            System.out.println("agregada reunion 4");//En sala4
//        else
//            System.out.println("fallo reunion 4");
//
//        if (res.Agregar_Una_Reunion("Reu1", 2, F1I, F1F, "Cli1") == true)
//            System.out.println("agregada reunion 1");//En sala3
//        else
//            System.out.println("fallo reunion 1");
//
//
//        if (res.Agregar_Una_Reunion("Reu2", 1, F1I, F1F, "Cli1") == true) //mismo nombre!!!!
//            System.out.println("agregada reunion 2 b");
//        else
//            System.out.println("fallo reunion 2 b");
//
//
//        GregorianCalendar Fe = new GregorianCalendar(2010,6,2);
//        ReunionesDia Reudia = new ReunionesDia();
//        Reudia = res.Mostrar_Reuniones_Dia(Fe);
//
//        System.out.println("REUNIONES DIA:"+Reudia.Dar_Long());
//        System.out.println("");
//        for (int i=0; i<Reudia.Dar_Long();i++)
//        {
//            System.out.println(Reudia.Dar_NomReunion(i));
//            System.out.println(Reudia.Dar_NomCliente(i));
//            System.out.println(Reudia.Dar_NomSala(i));
//            System.out.println(Reudia.Dar_HoraInicio(i).get(Calendar.HOUR_OF_DAY));
//            System.out.println(Reudia.Dar_HoraFin(i).get(Calendar.HOUR_OF_DAY));
//            System.out.println("");
//        }

////------------------------prueba ocupacion sala mes-----------------
//        Reservas res = new Reservas();
//        res.Agregar_Sala_Reunion("Sala1", 1, 11);
//        System.out.println("agregada sala 1");
//        res.Agregar_Sala_Reunion("Sala2", 1, 11);
//        System.out.println("agregada sala 2");
//        res.Agregar_Sala_Reunion("Sala3", 3, 13);
//        System.out.println("agregada sala 3");
//        res.Agregar_Sala_Reunion("Sala4", 5, 15);
//        System.out.println("agregada sala 4");
//        res.Agregar_Sala_Reunion("Sala5", 5, 15);
//        System.out.println("agregada sala 5");
//        res.Agregar_Sala_Reunion("Sala6", 5, 15);
//        System.out.println("agregada sala 6");
//        res.Agregar_Sala_Reunion("Sala7", 7, 17);
//        System.out.println("agregada sala 7");
//
//        res.Agregar_Un_Cliente("Cli1", "Cli1Uno", "Direccion1");
//        System.out.println("agregada cliente 1");
//        res.Agregar_Un_Cliente("Cli2", "Cli2Dos", "Direccion2");
//        System.out.println("agregada cliente 2");
//
//        GregorianCalendar F1I = new GregorianCalendar(2010,4,8,11,00);
//        GregorianCalendar F1F = new GregorianCalendar(2010,4,15,10,00);
//        GregorianCalendar F2I = new GregorianCalendar(2010,4,15,10,01);
//        GregorianCalendar F2F = new GregorianCalendar(2010,4,15,12,00);
//        GregorianCalendar F3I = new GregorianCalendar(2010,4,15,13,00);
//        GregorianCalendar F3F = new GregorianCalendar(2010,4,15,15,00);
//        GregorianCalendar F4I = new GregorianCalendar(2010,4,15,16,00);
//        GregorianCalendar F4F = new GregorianCalendar(2010,6,16,17,00);
//        GregorianCalendar F5I = new GregorianCalendar(2010,4,1,11,00);
//        GregorianCalendar F5F = new GregorianCalendar(2010,4,15,15,00);
//        GregorianCalendar F6I = new GregorianCalendar(2010,1,2,13,00);
//        GregorianCalendar F6F = new GregorianCalendar(2010,4,1,17,00);
//
//
//        if (res.Agregar_Una_Reunion("Reu3", 4, F3I, F3F, "Cli2") == true)
//            System.out.println("agregada reunion 3");//En sala6
//        else
//            System.out.println("fallo reunion 3");
//
//        if (res.Agregar_Una_Reunion("Reu2", 4, F2I, F2F, "Cli1") == true)
//            System.out.println("agregada reunion 2");//En sala6
//
//        else
//            System.out.println("fallo reunion 2");
//
//        if (res.Agregar_Una_Reunion("Reu1", 4, F1I, F1F, "Cli2") == true)
//            System.out.println("agregada reunion 1");//En sala6
//        else
//            System.out.println("fallo reunion 1");
//
//        if (res.Agregar_Una_Reunion("Reu4", 4, F4I, F4F, "Cli2") == true)
//            System.out.println("agregada reunion 4");//En sala5
//        else
//            System.out.println("fallo reunion 4");
//
//        if (res.Agregar_Una_Reunion("Reu5", 4, F5I, F5F, "Cli1") == true) //mismo nombre!!!!
//            System.out.println("agregada reunion 5");
//        else
//            System.out.println("fallo reunion 5");
//
//        if (res.Agregar_Una_Reunion("Reu6", 4, F6I, F6F, "Cli1") == true) //mismo nombre!!!!
//            System.out.println("agregada reunion 6");
//        else
//            System.out.println("fallo reunion 6");
//
//
//        GregorianCalendar Fe = new GregorianCalendar(2010,4,15);
//        String Sal = "Sala6";
//        SalasMes SM = new SalasMes("",0);
//        SM = res.Mostrar_Ocupacion_Sala_Mes(Sal, Fe);
//
//        System.out.println("Ocupacion salas mes:"+SM.Dar_Long_Salas());
//        System.out.println("");
//        System.out.println(SM.Dar_NomSala());
//        System.out.println(SM.Dar_Capacidad());
//        for (int i=0; i<SM.Dar_Long_Salas();i++)
//        {
//            System.out.println(SM.Dar_fecha(i).get(Calendar.DAY_OF_MONTH));
//            System.out.println(SM.Dar_NomReunion(i));
//            System.out.println(SM.Dar_NomCliente(i));
//            System.out.println("");
//        }


////------------------------prueba disponibilidad salas tiempo-----------------
//        Reservas res = new Reservas();
//        res.Agregar_Sala_Reunion("Sala1", 1, 11);
//        System.out.println("agregada sala 1");
//        res.Agregar_Sala_Reunion("Sala2", 1, 11);
//        System.out.println("agregada sala 2");
//        res.Agregar_Sala_Reunion("Sala3", 3, 13);
//        System.out.println("agregada sala 3");
//        res.Agregar_Sala_Reunion("Sala4", 5, 15);
//        System.out.println("agregada sala 4");
//        res.Agregar_Sala_Reunion("Sala5", 5, 15);
//        System.out.println("agregada sala 5");
//        res.Agregar_Sala_Reunion("Sala6", 5, 15);
//        System.out.println("agregada sala 6");
//        res.Agregar_Sala_Reunion("Sala7", 7, 17);
//        System.out.println("agregada sala 7");
//
//        res.Agregar_Un_Cliente("Cli1", "Cli1Uno", "Direccion1");
//        System.out.println("agregada cliente 1");
//        res.Agregar_Un_Cliente("Cli2", "Cli2Dos", "Direccion2");
//        System.out.println("agregada cliente 2");
//
//        GregorianCalendar F1I = new GregorianCalendar(2010,4,8,11,00);
//        GregorianCalendar F1F = new GregorianCalendar(2010,4,15,10,00);
//        GregorianCalendar F2I = new GregorianCalendar(2010,4,15,10,01);
//        GregorianCalendar F2F = new GregorianCalendar(2010,4,15,12,00);
//        GregorianCalendar F3I = new GregorianCalendar(2010,4,15,13,00);
//        GregorianCalendar F3F = new GregorianCalendar(2010,4,15,15,00);
//        GregorianCalendar F4I = new GregorianCalendar(2010,4,15,16,00);
//        GregorianCalendar F4F = new GregorianCalendar(2010,6,16,17,00);
//        GregorianCalendar F5I = new GregorianCalendar(2010,4,1,11,00);
//        GregorianCalendar F5F = new GregorianCalendar(2010,4,15,15,00);
//        GregorianCalendar F6I = new GregorianCalendar(2010,1,2,13,00);
//        GregorianCalendar F6F = new GregorianCalendar(2010,4,1,17,00);
//
//
//        if (res.Agregar_Una_Reunion("Reu3", 4, F3I, F3F, "Cli2") == true)
//            System.out.println("agregada reunion 3");//En sala6
//        else
//            System.out.println("fallo reunion 3");
//
//        if (res.Agregar_Una_Reunion("Reu2", 4, F2I, F2F, "Cli1") == true)
//            System.out.println("agregada reunion 2");//En sala6
//
//        else
//            System.out.println("fallo reunion 2");
//
//        if (res.Agregar_Una_Reunion("Reu1", 4, F1I, F1F, "Cli2") == true)
//            System.out.println("agregada reunion 1");//En sala6
//        else
//            System.out.println("fallo reunion 1");
//
//        if (res.Agregar_Una_Reunion("Reu4", 4, F4I, F4F, "Cli2") == true)
//            System.out.println("agregada reunion 4");//En sala5
//        else
//            System.out.println("fallo reunion 4");
//
//        if (res.Agregar_Una_Reunion("Reu5", 4, F5I, F5F, "Cli1") == true) //mismo nombre!!!!
//            System.out.println("agregada reunion 5");
//        else
//            System.out.println("fallo reunion 5");
//
//        if (res.Agregar_Una_Reunion("Reu6", 4, F6I, F6F, "Cli1") == true) //mismo nombre!!!!
//            System.out.println("agregada reunion 6");
//        else
//            System.out.println("fallo reunion 6");
//
//
//        GregorianCalendar FI = new GregorianCalendar(2010,4,15,11,0);
//        GregorianCalendar FF = new GregorianCalendar(2010,4,15,14,0);
//        SalasDisp SD = new SalasDisp();
//        SD = res.Mostrar_Disponibilidad_Salas_Tiempo(FI, FF);
//
//        System.out.println("Disponibilidad salas tiempo:"+SD.Long_Salas());
//        System.out.println("");
//        for (int i=0; i<SD.Long_Salas();i++)
//        {
//            System.out.println(SD.Dar_Sala(i));
//            System.out.println(SD.Dar_Capacidad(i));
//            System.out.println(SD.Dar_Precio(i));
//            System.out.println("");
//        }
//
//        System.out.println(res.Fechas_Solapadas(FI, FF, F5I, F5F));


////------------------------prueba factura-----------------
//        Reservas res = new Reservas();
//        res.Agregar_Sala_Reunion("Sala1", 1, 11);
//        System.out.println("agregada sala 1");
//        res.Agregar_Sala_Reunion("Sala2", 1, 11);
//        System.out.println("agregada sala 2");
//        res.Agregar_Sala_Reunion("Sala3", 3, 13);
//        System.out.println("agregada sala 3");
//        res.Agregar_Sala_Reunion("Sala4", 5, 15);
//        System.out.println("agregada sala 4");
//        res.Agregar_Sala_Reunion("Sala5", 5, 15);
//        System.out.println("agregada sala 5");
//        res.Agregar_Sala_Reunion("Sala6", 5, 15);
//        System.out.println("agregada sala 6");
//        res.Agregar_Sala_Reunion("Sala7", 7, 17);
//        System.out.println("agregada sala 7");
//
//        res.Agregar_Un_Cliente("Cli1", "Cli1Uno", "Direccion1");
//        System.out.println("agregada cliente 1");
//        res.Agregar_Un_Cliente("Cli2", "Cli2Dos", "Direccion2");
//        System.out.println("agregada cliente 2");
//
//        GregorianCalendar F1I = new GregorianCalendar(2010,4,8,11,00);
//        GregorianCalendar F1F = new GregorianCalendar(2010,4,15,10,00);
//        GregorianCalendar F2I = new GregorianCalendar(2010,4,15,10,01);
//        GregorianCalendar F2F = new GregorianCalendar(2010,4,15,12,00);
//        GregorianCalendar F3I = new GregorianCalendar(2010,4,15,13,00);
//        GregorianCalendar F3F = new GregorianCalendar(2010,4,15,15,00);
//        GregorianCalendar F4I = new GregorianCalendar(2010,4,15,16,00);
//        GregorianCalendar F4F = new GregorianCalendar(2010,6,16,17,00);
//        GregorianCalendar F5I = new GregorianCalendar(2010,4,1,11,00);
//        GregorianCalendar F5F = new GregorianCalendar(2010,4,15,15,00);
//        GregorianCalendar F6I = new GregorianCalendar(2010,1,2,13,00);
//        GregorianCalendar F6F = new GregorianCalendar(2010,4,1,17,00);
//
//        GregorianCalendar F = new GregorianCalendar(2010,4,1,0,0);
//        System.out.println(res.Calcula_Horas(F5I, F5F, F));
//
//
//        if (res.Agregar_Una_Reunion("Reu3", 4, F3I, F3F, "Cli2") == true)
//            System.out.println("agregada reunion 3");//En sala6
//        else
//            System.out.println("fallo reunion 3");
//
//        if (res.Agregar_Una_Reunion("Reu2", 4, F2I, F2F, "Cli1") == true)
//            System.out.println("agregada reunion 2");//En sala6
//
//        else
//            System.out.println("fallo reunion 2");
//
//        if (res.Agregar_Una_Reunion("Reu4", 4, F4I, F4F, "Cli2") == true)
//            System.out.println("agregada reunion 4");//En sala5
//        else
//            System.out.println("fallo reunion 4");
//
//        if (res.Agregar_Una_Reunion("Reu6", 4, F6I, F6F, "Cli1") == true) //mismo nombre!!!!
//            System.out.println("agregada reunion 6");
//        else
//            System.out.println("fallo reunion 6");
//
//        if (res.Agregar_Una_Reunion("Reu5", 4, F5I, F5F, "Cli1") == true) //mismo nombre!!!!
//            System.out.println("agregada reunion 5");
//        else
//            System.out.println("fallo reunion 5");
//
//        if (res.Agregar_Una_Reunion("Reu1", 4, F1I, F1F, "Cli2") == true)
//            System.out.println("agregada reunion 1");//En sala6
//        else
//            System.out.println("fallo reunion 1");
//
//
//
//        GregorianCalendar Fech = new GregorianCalendar(2010,4,15,14,0);
//        String NifCli = "Cli1";
//        Factura Fact = new Factura("","","");
//        Fact = res.Factura_Cliente(NifCli, Fech);
//
//        System.out.println("Factura:"+Fact.Dar_Long());
//        System.out.println("");
//        System.out.println(Fact.Dar_Cliente());
//        System.out.println(Fact.Dar_Nif());
//        System.out.println(Fact.Dar_Direccion());
//        System.out.println("");
//        for (int i=0; i<Fact.Dar_Long();i++)
//        {
//            System.out.println(Fact.Dar_NomReunion(i));
//            System.out.println(Fact.Dar_NomSala(i));
//            System.out.println(Fact.Dar_FechaInicio(i));
//            System.out.println(Fact.Dar_Horas(i));
//            System.out.println(Fact.Dar_Coste(i));
//            System.out.println("");
//        }
//        System.out.println("");
//        System.out.println(Fact.Dar_Total());




//----------------Prueba final (pruebaFI)

//    Reservas res = new Reservas();
//        res.Agregar_Sala_Reunion("Sala01", 10, 10);
//        System.out.println("agregada sala 01");
//        res.Agregar_Sala_Reunion("Sala02", 15, 15);
//        System.out.println("agregada sala 02");
//        res.Agregar_Sala_Reunion("Sala03", 15, 15);
//        System.out.println("agregada sala 03");
//        res.Agregar_Sala_Reunion("Sala04", 30, 30);
//        System.out.println("agregada sala 04");
//        res.Agregar_Sala_Reunion("Sala05", 50, 50);
//        System.out.println("agregada sala 05");
//        res.Agregar_Sala_Reunion("Sala06", 100, 99);
//        System.out.println("agregada sala 06");
//        res.Agregar_Sala_Reunion("Sala07", 100, 99);
//        System.out.println("agregada sala 07");
//
//        res.Agregar_Un_Cliente("11111111A", "Cliente01", "Direccion1");
//        System.out.println("agregada Cliente 1");
//        res.Agregar_Un_Cliente("22222222B", "Cliente02", "Direccion1");
//        System.out.println("agregada Cliente 2");
//        res.Agregar_Un_Cliente("33333333C", "Cliente03", "Direccion1");
//        System.out.println("agregada Cliente 3");
//        res.Agregar_Un_Cliente("44444444D", "Cliente04", "Direccion1");
//        System.out.println("agregada Cliente 4");
//        res.Agregar_Un_Cliente("55555555E", "Cliente05", "Direccion1");
//        System.out.println("agregada Cliente 5");
//        res.Agregar_Un_Cliente("66666666F", "Cliente06", "Direccion1");
//        System.out.println("agregada Cliente 6");
//        res.Agregar_Un_Cliente("77777777G", "Cliente07", "Direccion1");
//        System.out.println("agregada Cliente 7");
//        res.Agregar_Un_Cliente("88888888H", "Cliente08", "Direccion1");
//        System.out.println("agregada Cliente 8");
//        res.Agregar_Un_Cliente("99999999B", "Cliente09", "Direccion1");
//        System.out.println("agregada Cliente 9");
//
//        GregorianCalendar FI = new GregorianCalendar(2010,6,1,00,00);
//        GregorianCalendar FF = new GregorianCalendar(2010,6,30,00,00);
//
//        SalasDisp SD = new SalasDisp();
//        SD = res.Mostrar_Disponibilidad_Salas_Tiempo(FI, FF);
//
//        System.out.println("Disponibilidad salas tiempo:"+SD.Long_Salas());
//        System.out.println("");
//        for (int i=0; i<SD.Long_Salas();i++)
//        {
//            System.out.println(SD.Dar_Sala(i));
//            System.out.println(SD.Dar_Capacidad(i));
//            System.out.println(SD.Dar_Precio(i));
//            System.out.println("");
//        }
//
//
//        GregorianCalendar F1I = new GregorianCalendar(2010,6,2,10,00);
//        GregorianCalendar F1F = new GregorianCalendar(2010,6,2,12,00);
//        GregorianCalendar F2I = new GregorianCalendar(2010,6,2,10,00);
//        GregorianCalendar F2F = new GregorianCalendar(2010,6,3,15,00);
//        GregorianCalendar F3I = new GregorianCalendar(2010,6,2,10,00);
//        GregorianCalendar F3F = new GregorianCalendar(2010,6,4,16,00);
//        GregorianCalendar F4I = new GregorianCalendar(2010,6,3,11,00);
//        GregorianCalendar F4F = new GregorianCalendar(2010,6,5,18,00);
//        GregorianCalendar F5I = new GregorianCalendar(2010,6,7,9,00);
//        GregorianCalendar F5F = new GregorianCalendar(2010,6,7,11,00);
//        GregorianCalendar F6I = new GregorianCalendar(2010,6,7,9,00);
//        GregorianCalendar F6F = new GregorianCalendar(2010,6,7,11,00);
//
//
//        if (res.Agregar_Una_Reunion("Reunion01", 14, F1I, F1F, "11111111A") == true)
//            System.out.println("agregada reunion01");//En sala5
//        else
//            System.out.println("fallo reunion01");
//
//        if (res.Agregar_Una_Reunion("Reunion02", 14, F2I, F2F, "11111111A") == true)
//            System.out.println("agregada reunion02");//En sala5
//        else
//            System.out.println("fallo reunion02");
//
//        if (res.Agregar_Una_Reunion("Reunion03", 24, F3I, F3F, "11111111A") == true)
//            System.out.println("agregada reunion03");//En sala5
//        else
//            System.out.println("fallo reunion03");
//
//        if (res.Agregar_Una_Reunion("Reunion04", 99, F4I, F4F, "22222222B") == true)
//            System.out.println("agregada reunion04");//En sala5
//        else
//            System.out.println("fallo reunion04");
//
//
//        if (res.Agregar_Una_Reunion("Reunion05", 5, F5I, F5F, "33333333C") == true)
//            System.out.println("agregada reunion05");//En sala5
//        else
//            System.out.println("fallo reunion05");
//
//        if (res.Agregar_Una_Reunion("Reunion06", 10, F6I, F6F, "44444444D") == true)
//            System.out.println("agregada reunion06");//En sala5
//        else
//            System.out.println("fallo reunion06");
//
//
//        FI = new GregorianCalendar(2010,6,1,00,00);
//        FF = new GregorianCalendar(2010,6,30,00,00);
//
//        SD = res.Mostrar_Disponibilidad_Salas_Tiempo(FI, FF);
//
//        System.out.println("Disponibilidad salas tiempo:"+SD.Long_Salas());
//        System.out.println("");
//        for (int i=0; i<SD.Long_Salas();i++)
//        {
//            System.out.println(SD.Dar_Sala(i));
//            System.out.println(SD.Dar_Capacidad(i));
//            System.out.println(SD.Dar_Precio(i));
//            System.out.println("");
//        }
//
//
//        GregorianCalendar Fe = new GregorianCalendar(2010,6,3);
//        ReunionesDia Reudia = new ReunionesDia();
//        Reudia = res.Mostrar_Reuniones_Dia(Fe);
//
//        System.out.println("REUNIONES DIA:"+Reudia.Dar_Long());
//        System.out.println("");
//        for (int i=0; i<Reudia.Dar_Long();i++)
//        {
//            System.out.println(Reudia.Dar_NomReunion(i));
//            System.out.println(Reudia.Dar_NomCliente(i));
//            System.out.println(Reudia.Dar_NomSala(i));
//            System.out.println(Reudia.Dar_HoraInicio(i).get(Calendar.HOUR_OF_DAY));
//            System.out.println(Reudia.Dar_HoraFin(i).get(Calendar.HOUR_OF_DAY));
//            System.out.println("");
//        }
//
//
//        Fe = new GregorianCalendar(2010,6,7);
//        Reudia = res.Mostrar_Reuniones_Dia(Fe);
//
//        System.out.println("REUNIONES DIA:"+Reudia.Dar_Long());
//        System.out.println("");
//        for (int i=0; i<Reudia.Dar_Long();i++)
//        {
//            System.out.println(Reudia.Dar_NomReunion(i));
//            System.out.println(Reudia.Dar_NomCliente(i));
//            System.out.println(Reudia.Dar_NomSala(i));
//            System.out.println(Reudia.Dar_HoraInicio(i).get(Calendar.HOUR_OF_DAY));
//            System.out.println(Reudia.Dar_HoraFin(i).get(Calendar.HOUR_OF_DAY));
//            System.out.println("");
//        }
//
//
//        Fe = new GregorianCalendar(2010,6,1);
//        String Sal = "Sala02";
//        SalasMes SM = new SalasMes("",0);
//        SM = res.Mostrar_Ocupacion_Sala_Mes(Sal, Fe);
//
//        System.out.println("Ocupacion salas mes:"+SM.Dar_Long_Salas());
//        System.out.println("");
//        System.out.println(SM.Dar_NomSala());
//        System.out.println(SM.Dar_Capacidad());
//        for (int i=0; i<SM.Dar_Long_Salas();i++)
//        {
//            System.out.println(SM.Dar_fecha(i).get(Calendar.DAY_OF_MONTH));
//            System.out.println(SM.Dar_NomReunion(i));
//            System.out.println(SM.Dar_NomCliente(i));
//            System.out.println("");
//        }
//
//
//        GregorianCalendar Fech = new GregorianCalendar(2010,6,1,0,0);
//        String NifCli = "11111111A";
//        Factura Fact = new Factura("","","");
//        Fact = res.Factura_Cliente(NifCli, Fech);
//
//        System.out.println("Factura:"+Fact.Dar_Long());
//        System.out.println("");
//        System.out.println(Fact.Dar_Cliente());
//        System.out.println(Fact.Dar_Nif());
//        System.out.println(Fact.Dar_Direccion());
//        System.out.println("");
//        for (int i=0; i<Fact.Dar_Long();i++)
//        {
//            System.out.println(Fact.Dar_NomReunion(i));
//            System.out.println(Fact.Dar_NomSala(i));
//            System.out.println(Fact.Dar_FechaInicio(i));
//            System.out.println(Fact.Dar_Horas(i));
//            System.out.println(Fact.Dar_Coste(i));
//            System.out.println("");
//        }
//        System.out.println("");
//        System.out.println(Fact.Dar_Total());
//
//        System.out.println("Eliminar sala01"+res.Eliminar_Sala_Reunion("sala07"));
//
//        System.out.println("Eliminar cliente01"+res.Eliminar_Un_Cliente("11111111A"));
//
//        FI = new GregorianCalendar(2010,6,1,00,00);
//        FF = new GregorianCalendar(2010,6,30,00,00);
//
//        SD = res.Mostrar_Disponibilidad_Salas_Tiempo(FI, FF);
//
//        System.out.println("Disponibilidad salas tiempo:"+SD.Long_Salas());
//        System.out.println("");
//        for (int i=0; i<SD.Long_Salas();i++)
//        {
//            System.out.println(SD.Dar_Sala(i));
//            System.out.println(SD.Dar_Capacidad(i));
//            System.out.println(SD.Dar_Precio(i));
//            System.out.println("");
//        }
//
//
    }

}