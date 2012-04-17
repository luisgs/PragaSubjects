import java.util.*;

//--------------------> Xa Ocup Salas
public class SalasMes
{
    private String Sala;
    private int Capacidad;
    private ArrayList<SalaMes> Salas;

    public SalasMes (String S, int Cap)
    {
        Sala = S;
        Capacidad = Cap;
        Salas = new ArrayList<SalaMes>();
    }


private class SalaMes
{
    String Cliente;
    String Reunion;
    GregorianCalendar Fecha;

    public SalaMes (String C, String R, GregorianCalendar Fech)
    {
        Cliente = C;
        Reunion = R;
        Fecha = Fech;
    }
}

    public String Dar_NomSala ()
    {
        return Sala;
    }

    public int Dar_Capacidad ()
    {
        return Capacidad;
    }

    public int Dar_Long_Salas ()
    {
        return Salas.size();
    }

    public String Dar_NomCliente (int n)
    {
        return Salas.get(n).Cliente;
    }

    public String Dar_NomReunion (int n)
    {
        return Salas.get(n).Reunion;
    }

    public GregorianCalendar Dar_fecha (int n)
    {
        return Salas.get(n).Fecha;
    }

    public void AnadirSalaMes (String C, String R, GregorianCalendar F)
    {
        SalaMes SaMe = new SalaMes (C,R,F);
        Salas.add(SaMe);
    }

}
