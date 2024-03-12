
package jarvis;

class ObjetoRadar {
    private double coordenadaX;
    private double coordenadaY;
    private double coordenadaZ;
    private boolean hostil;
    private float resistencia;

    // Constructor, getters y setters

    public ObjetoRadar(double coordenadaX, double coordenadaY, double coordenadaZ, boolean hostil, float resistencia) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.coordenadaZ = coordenadaZ;
        this.hostil = hostil;
        this.resistencia = resistencia;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public double getCoordenadaZ() {
        return coordenadaZ;
    }

    public void setCoordenadaZ(double coordenadaZ) {
        this.coordenadaZ = coordenadaZ;
    }

    public boolean isHostil() {
        return hostil;
    }

    public void setHostil(boolean hostil) {
        this.hostil = hostil;
    }

    public float getResistencia() {
        return resistencia;
    }

    public void setResistencia(float resistencia) {
        this.resistencia = resistencia;
    }
    
     public void recibirAtaque(float potenciaDisparo) {
        if (resistencia > 0) {
            resistencia -= potenciaDisparo;
            if (resistencia < 0) {
                resistencia = 0;
            }
        }
    }

    public boolean estaDestruido() {
        return resistencia <= 0;
    }
}