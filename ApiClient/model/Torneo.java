package model;


public class Torneo {
    public String NameEquipoL;
    public String NameEquipoV;
    public String GolEquipoL;
    public String GolEquipoV;
    public String MejorJugador;
    public String Mingol;
    public String Estadio;
    public int PuntosEqL;
    public int PuntosEqV;



    public Torneo(String nameEquipoL,String nameEquipoV, String golEquipoL, String golEquipoV, String mejorJugador, String mingol, String estadio, int PuntoseqL, int PuntoseqV) {

        this.NameEquipoL = nameEquipoL;
        this.NameEquipoV = nameEquipoV;
        this.GolEquipoL = golEquipoL;
        this.GolEquipoL = golEquipoV;
        this.MejorJugador = mejorJugador;
        this.Mingol = mingol;
        this.Estadio = estadio;
        this.PuntosEqL = PuntoseqL;
        this.PuntosEqV = PuntoseqV;
    }
    // Getters y setters para los nuevos campos
    public int getPuntosEqL() {
        return PuntosEqL;
    }

    public void setPuntosEqL(int PuntosEqL) {
        this.PuntosEqL = PuntosEqL;
    }

    public int getPuntosEqV() {
        return PuntosEqV;
    }

    public void setPuntosEqV(int PuntosEqV) {
        this.PuntosEqV = PuntosEqV;
    }


    // Getters
    public String getNameEquipoL() {
        return NameEquipoL;
    }

    public String getNameEquipoV() {
        return NameEquipoV;
    }

    public String getGolEquipoL() {
        return GolEquipoL;
    }

    public String getGolEquipoV() {
        return GolEquipoV;
    }

    public String getMejorJugador() {
        return MejorJugador;
    }

    public String getMingol() {
        return Mingol;
    }

    public String getEstadio() {
        return Estadio;
    }
}
