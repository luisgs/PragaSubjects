import java.util.*;

//////////-----------> Xa factura

public class Factura
{
    private String Nombre;
    private String Nif;
    private String Direcc;
    private ArrayList<CosteReunion> ListaReuniones;
    private int Total;

    public Factura (String N, String Ni, String D)
    {
        Nombre = N;
        Nif = Ni;
        Direcc = D;
        ListaReuniones = new ArrayList<CosteReunion>();
        Total = 0;
    }

    private class CosteReunion
{
    String Reunion;
    String Sala;
    GregorianCalendar FechaIni;
    int Horas;
    int Coste;

    public CosteReunion (String R, String S, GregorianCalendar FI, int H, int P)
    {
        Reunion = R;
        Sala = S;
        FechaIni = FI;
        Horas = H;
        Coste = P;
    }
}


    public String Dar_Cliente ()
    {
        return Nombre;
    }

    public String Dar_Nif ()
    {
        return Nif;
    }

    public String Dar_Direccion ()
    {
        return Direcc;
    }

    public int Dar_Total ()
    {
        return Total;
    }

    public int Dar_Long ()
    {
        return ListaReuniones.size();
    }

    public String Dar_NomReunion (int n)
    {
        return ListaReuniones.get(n).Reunion;
    }

    public String Dar_NomSala (int n)
    {
        return ListaReuniones.get(n).Sala;
    }

    public GregorianCalendar Dar_FechaInicio (int n)
    {
        return ListaReuniones.get(n).FechaIni;
    }

    public int Dar_Horas (int n)
    {
        return ListaReuniones.get(n).Horas;
    }

    public int Dar_Coste (int n)
    {
        return ListaReuniones.get(n).Coste;
    }

    public void AñadirCosteReunion (String Reu, String Sal, GregorianCalendar FI, int H, int P)
    {
        CosteReunion Coste = new CosteReunion (Reu,Sal,FI,H,P);
        ListaReuniones.add(Coste);
        Total = Total + P;
    }

    public void AñadirCosteReunionOrdenado (String Reu, String Sal, GregorianCalendar FI, int H, int P)
    {
        System.out.println("Entramos en añadir coste");
        int n=0;
        CosteReunion Coste = new CosteReunion (Reu,Sal,FI,H,P);
        if (ListaReuniones.size() == 0)
        {
            ListaReuniones.add(n, Coste);
            Total = Total + P;
            //System.out.println("añadido------> size = "+ListaReuniones.size());
        }
        else
        {
            while (n < ListaReuniones.size())
            {
                if (FI.before(ListaReuniones.get(n).FechaIni) ||
                    FI.equals(ListaReuniones.get(n).FechaIni))
                {
                    ListaReuniones.add(n, Coste);
                    Total = Total + P;
                    //System.out.println("añadido------> size = "+ListaReuniones.size());
                    break;
                }
                else if (n == ListaReuniones.size()-1 &&
                        FI.after(ListaReuniones.get(n).FechaIni))
                {
                    ListaReuniones.add(Coste);
                    Total = Total + P;
                    //System.out.println("añadido------> size = "+ListaReuniones.size());
                    break;
                }
                else
                {
                    n = n + 1;
                }
            }
        }
    }
}
