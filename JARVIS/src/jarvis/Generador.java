package jarvis;


class Generador {
    private float cargaMaxima;

    public Generador(float cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public float obtenerCargaMaxima() {
        return cargaMaxima;
    }

    public float consumirEnergia(float cantidad) {
        if (cantidad <= cargaMaxima) {
            cargaMaxima -= cantidad;
            return cantidad;
        } else {
            float energiaConsumida = cargaMaxima;
            cargaMaxima = 0;
            return energiaConsumida;
        }
    }
}














