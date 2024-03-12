package jarvis;

class Radar {
    private static final int MAX_OBJETOS = 10;
    private ObjetoRadar[] objetos;
    private int cantidadObjetos;

    public ObjetoRadar[] getObjetos() {
        return objetos;
    }

    public void setObjetos(ObjetoRadar[] objetos) {
        this.objetos = objetos;
    }

    public int getCantidadObjetos() {
        return cantidadObjetos;
    }

    public void setCantidadObjetos(int cantidadObjetos) {
        this.cantidadObjetos = cantidadObjetos;
    }

    public Radar() {
        this.objetos = new ObjetoRadar[MAX_OBJETOS];
        this.cantidadObjetos = 0;
    }

    public void agregarObjeto(ObjetoRadar objeto) {
        if (cantidadObjetos < MAX_OBJETOS) {
            objetos[cantidadObjetos++] = objeto;
        }
    }

    public void informarDistanciaEnemigos() {
        for (int i = 0; i < cantidadObjetos; i++) {
            if (objetos[i].isHostil()) {
                double distancia = calcularDistancia(objetos[i].getCoordenadaX(), objetos[i].getCoordenadaY(),
                        objetos[i].getCoordenadaZ());
                System.out.println("Enemigo " + (i + 1) + ": Distancia = " + distancia + " metros");
            }
        }
    }

    public double calcularDistancia(double x, double y, double z) {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Resto de la implementación de la clase ObjetoRadar y otros métodos necesarios...
}