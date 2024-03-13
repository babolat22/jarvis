package jarvis;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Crear una armadura de Iron Man CON : Resistencia 100 Y SALUD 100
        Bota botas[]={new Bota(1), new Bota(1)};
        Guante guantes[]= {new Guante((float)1.5), new Guante((float)1.5)};
        
        Armadura ironMan = new Armadura("Rojo", "Oro", 100, 100, new Generador(Float.MAX_VALUE));
        
        ironMan.vincularDispositivos(botas[0], botas[1], guantes[0], guantes[1]);
        // Mostrar el estado de la armadura ANTES
        ironMan.mostrarEstado();
        // IRON ES LO MAS
        // Realizar algunas acciones
        ironMan.caminar(10);
        System.out.println("");
     
        ironMan.volar(5);
        ironMan.usarGuantesComoArmas(3);

        // Mostrar el estado de la armadura LUEGO DE USO
        ironMan.mostrarEstado();

        // Informar el estado de la batería
        ironMan.informarEstadoBateria();

        // Informar el estado del reactor en otras unidades de medida
        ironMan.informarEstadoReactor();

        // Revisar y reparar dispositivos dañados
        ironMan.revisarDispositivos();

        // Mostrar nuevamente el estado de la armadura después de la revisión y reparación
        ironMan.mostrarEstado();

        // Simular el uso del radar
        Radar radar = new Radar();
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            ObjetoRadar objeto = new ObjetoRadar(random.nextDouble() * 100, random.nextDouble() * 100,
                    random.nextDouble() * 100, random.nextBoolean(), random.nextFloat() * 100);
            radar.agregarObjeto(objeto);
        }

        // Informar distancias de enemigos hostiles detectados por el radar
        radar.informarDistanciaEnemigos();

        // Destruir enemigos si son hostiles y están dentro del alcance
        ironMan.destruirEnemigos(radar);

        // Realizar acciones evasivas si la batería es menor al 10%
        ironMan.accionesEvasivas(10);

        // Mostrar el estado final de la armadura
        ironMan.mostrarEstado();
    }
}
