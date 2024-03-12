
package jarvis;

class Dispositivo {
    private float consumoEnergia;
    private boolean danado;

    public Dispositivo(float consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
        this.danado = false;
    }

    public float usar(int nivelIntensidad, int tiempo) {
        float energiaConsumida = nivelIntensidad * consumoEnergia * tiempo;
        // Simular probabilidad de daño al dispositivo
        if (Math.random() < 0.3) {
            danado = true;
        }
        return energiaConsumida;
    }

    public boolean estaDanado() {
        return danado;
    }

    public void reparar() {
        // Simular probabilidad de reparación
        if (Math.random() <= 0.4) {
            danado = false;
        }
    }
}