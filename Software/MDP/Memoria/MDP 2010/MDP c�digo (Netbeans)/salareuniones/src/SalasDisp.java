import java.util.*;

/////////-------------> Xa Sala disp
//En la lista almacenamos de 0 a size()-1

public class SalasDisp
{
    private ArrayList<SalaDisp> Salas;

    private class SalaDisp
{
    String Sala;
    int Capac;
    int Precio;

    public SalaDisp (String S, int Cap, int Prec)
    {
        Sala = S;
        Capac = Cap;
        Precio = Prec;
    }
}

    public SalasDisp()
    {
        Salas = new ArrayList<SalaDisp>();
    }

    public int Long_Salas ()
    {
        return Salas.size();
    }

    public String Dar_Sala (int n)
    {
        return Salas.get(n).Sala;
    }

    public int Dar_Capacidad (int n)
    {
        return Salas.get(n).Capac;
    }

    public int Dar_Precio (int n)
    {
        return Salas.get(n).Precio;
    }

    public void AñadirSalaDispOrdenado (String Sal, int Cap, int Prec)
    {
        boolean agregada = false;
        SalaDisp nue = new SalaDisp (Sal,Cap,Prec);
        if (Salas.isEmpty())
        {
            Salas.add(nue);
        }
        else
        {
            int n=0;
            while (!agregada)
            {
                if (Sal.compareToIgnoreCase(Salas.get(n).Sala) <= 0)
                {
                    agregada = true;
                    Salas.add(n,nue);
                }
                else if (n == Salas.size()-1 &&
                        Sal.compareToIgnoreCase(Salas.get(n).Sala) > 0)
                {
                    agregada = true;
                    Salas.add(nue);
                }
                else
                {
                    agregada = false;
                    n = n + 1;
                }
            }
        }
    }
}