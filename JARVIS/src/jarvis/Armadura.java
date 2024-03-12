package jarvis;

class Armadura {
    private String colorPrimario;
    private String colorSecundario;
    private Bota botaIzquierda;
    private Bota botaDerecha;
    private Guante guanteIzquierdo;
    private Guante guanteDerecho;
    private Consola consola;
    private Sintetizador sintetizador;
    private float resistencia;
    private float salud;
    private Generador generador;

    // Constructor, getters y setters

    public Armadura(String colorPrimario, String colorSecundario, float resistencia, float salud, Generador generador) {
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.resistencia = resistencia;
        this.salud = salud;
        this.generador = generador;
    }
    
    public void vincularDispositivos(Bota bota1, Bota bota2, Guante guante1, Guante guante2){
        this.botaIzquierda = bota1;
        this.botaDerecha = bota2;
        this.guanteIzquierdo = guante1;
        this.guanteDerecho = guante2;
        this.consola = new Consola((float) 0.5);
        this.sintetizador = new Sintetizador((float) 0.5);
    }

    public void setGenerador(Generador generador) {
        this.generador = generador;
    }

    public void caminar(int tiempo) {
        consumirEnergia(botaIzquierda.usar(1, tiempo));
        consumirEnergia(botaDerecha.usar(1, tiempo));
    }

    public void correr(int tiempo) {
        consumirEnergia(botaIzquierda.usar(1, tiempo * 2)); // NIVEL DE INTENSIDAD 2
        consumirEnergia(botaDerecha.usar(1, tiempo * 2));
    }

    public void propulsar(int tiempo) {  
        consumirEnergia(botaIzquierda.usar(3, tiempo));   // NIVEL DE INTENSIDAD 3
        consumirEnergia(botaDerecha.usar(3, tiempo));     // NO ES CONSUMO. EN USAR MULTIPLICAMOS
    }

    public void volar(int tiempo) {  //  USA BOTAS Y GUANTES TODO JUNTO
        consumirEnergia(botaIzquierda.usar(3, tiempo));
        consumirEnergia(botaDerecha.usar(3, tiempo));
        consumirEnergia(guanteIzquierdo.usar(2, tiempo));
        consumirEnergia(guanteDerecho.usar(2, tiempo));
    }

    public void usarGuantesComoArmas(int tiempo) {
        consumirEnergia(guanteIzquierdo.disparar(2, tiempo));
        consumirEnergia(guanteDerecho.disparar(2, tiempo));
    }

    public void escribirEnConsola(int tiempo) {
        consumirEnergia(consola.usar(1, tiempo));
    }

    public void hablarConSintetizador(int tiempo) {
        consumirEnergia(sintetizador.usar(1, tiempo));
    }

    private void consumirEnergia(float cantidad) {
        float energiaConsumida = generador.consumirEnergia(cantidad);
        // Actualizar estado de la armadura
    }
    
    public void mostrarEstado() {
        System.out.println("Estado de la Armadura:");
        System.out.println("Color primario: " + colorPrimario);
        System.out.println("Color secundario: " + colorSecundario);
        System.out.println("Nivel de resistencia: " + resistencia);
        System.out.println("Nivel de salud: " + salud);
        System.out.println("Estado de la batería: " + generador.obtenerCargaMaxima());
        System.out.println("Dispositivos:");

        System.out.println("Bota izquierda - Dañada: " + botaIzquierda.estaDanado());
        System.out.println("Bota derecha - Dañada: " + botaDerecha.estaDanado());
        System.out.println("Guante izquierdo - Dañado: " + guanteIzquierdo.estaDanado());
        System.out.println("Guante derecho - Dañado: " + guanteDerecho.estaDanado());
        System.out.println("Consola - Dañada: " + consola.estaDanado());
        System.out.println("Sintetizador - Dañado: " + sintetizador.estaDanado());
        System.out.println();
    }

    public void informarEstadoBateria() {
        float porcentajeCarga = (generador.obtenerCargaMaxima() / Float.MAX_VALUE) * 100;
        System.out.println("Estado de la batería: " + porcentajeCarga + "%");
    }

    public void informarEstadoReactor() {
        float cargaMaxima = generador.obtenerCargaMaxima();
        System.out.println("Estado del reactor en otras unidades de medida:");
        System.out.println("Carga máxima del reactor (float): " + cargaMaxima);
        System.out.println("Carga máxima del reactor (double): " + (double) cargaMaxima);
        System.out.println("Carga máxima del reactor (int): " + (int) cargaMaxima);
        System.out.println();
    }

    public void revisarDispositivos() {
        System.out.println("Revisando dispositivos:");
        Dispositivo[] dispositivos = { botaIzquierda, botaDerecha, guanteIzquierdo, guanteDerecho, consola, sintetizador };

        for (Dispositivo dispositivo : dispositivos) {
            if (dispositivo.estaDanado()) {
                System.out.println("El dispositivo está dañado. Intentando reparar...");
                dispositivo.reparar();
                if (!dispositivo.estaDanado()) {
                    System.out.println("Reparación exitosa.");
                } else {
                    System.out.println("Reparación fallida. El dispositivo sigue dañado.");
                }
            } else {
                System.out.println("El dispositivo está en buen estado.");
            }
        }
        System.out.println();
    }
    
    public void destruirEnemigos(Radar radar) {
        System.out.println("Destruyendo enemigos:");
        Guante[] guantes = { guanteIzquierdo, guanteDerecho };
        for (ObjetoRadar objeto : radar.getObjetos()) {
            if (objeto.isHostil()) {
                for (Guante guante : guantes) {
                    if (!guante.estaDanado() && generador.obtenerCargaMaxima() > 0) {
                        double distancia = radar.calcularDistancia(objeto.getCoordenadaX(), objeto.getCoordenadaY(),
                                objeto.getCoordenadaZ());
                        if (distancia <= 5000) {
                            System.out.println("Disparando a enemigo a " + distancia + " metros.");
                            float potenciaDisparo = (float) (5000 / distancia); // Potencia inversamente proporcional a la distancia
                            objeto.recibirAtaque(potenciaDisparo);
                            float energiaConsumida = guante.disparar(2, 1); // Consumo de energía al disparar
                            generador.consumirEnergia(energiaConsumida);
                            if (objeto.estaDestruido()) {
                                System.out.println("Enemigo destruido.");
                            }
                        } else {
                            System.out.println("Enemigo fuera de alcance.");
                        }
                    } else {
                        System.out.println("Guante dañado o sin energía. No se puede disparar.");
                    }
                }
            }
        }
        System.out.println();
    }

    public void accionesEvasivas(float distanciaEvasion) {
        System.out.println("Realizando acciones evasivas:");
        if (generador.obtenerCargaMaxima() < generador.obtenerCargaMaxima() * 0.1) {
            System.out.println("Batería baja. Realizando acciones evasivas para alejarse de enemigos.");
            // Calcular tiempo necesario para alejarse 10 km a una velocidad promedio de 300 km/h
            int tiempoEvasion = (int) (distanciaEvasion / 300);
            System.out.println("Tiempo estimado de evasión: " + tiempoEvasion + " horas.");
            System.out.println("Realizando evasión...");
            // Simular acciones evasivas
            // ...
            // Actualizar estado de la armadura
            generador.consumirEnergia(10); // Consumo de energía por acciones evasivas
        } else {
            System.out.println("Batería suficiente. No se requieren acciones evasivas en este momento.");
        }
        System.out.println();
    }
}
