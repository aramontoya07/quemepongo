package test;

import atuendo.Atuendo;
import clima.MockAgradable;
import clima.ServicioClimatico;
import eventos.AsistenciaEvento;
import eventos.Calendario;
import eventos.Evento;
import eventos.Frecuencia;
import notificaciones.Informante;
import prenda.*;
import usuario.Guardarropa;
import usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public abstract class SetUp {

    TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR,
            new ArrayList<Material>(Arrays.asList(Material.ALGODON, Material.SEDA)), 12, TipoUso.PRIMARIA);
    TipoPrenda remeraDesabrigada = new TipoPrenda(Categoria.PARTE_SUPERIOR,
            new ArrayList<Material>(Arrays.asList(Material.ALGODON, Material.SEDA)), 0, TipoUso.PRIMARIA);
    TipoPrenda remeraDe30 = new TipoPrenda(Categoria.PARTE_SUPERIOR,
            new ArrayList<Material>(Arrays.asList(Material.ALGODON, Material.SEDA)), 30, TipoUso.PRIMARIA);
    TipoPrenda gorro = new TipoPrenda(Categoria.ACCESORIO,
            new ArrayList<Material>(Arrays.asList(Material.LANA)), 2, TipoUso.SECUNDARIA);
    TipoPrenda pantalon = new TipoPrenda(Categoria.PARTE_INFERIOR,
            new ArrayList<Material>(Arrays.asList(Material.JEAN, Material.CUERO, Material.ALGODON)), 5,
            TipoUso.PRIMARIA);
    TipoPrenda pantalonDesabrigado = new TipoPrenda(Categoria.PARTE_INFERIOR,
            new ArrayList<Material>(Arrays.asList(Material.JEAN, Material.CUERO, Material.ALGODON)), 0,
            TipoUso.PRIMARIA);
    TipoPrenda zapatilla = new TipoPrenda(Categoria.CALZADO, new ArrayList<Material>(Arrays.asList(Material.CUERO)), 3,
            TipoUso.PRIMARIA);
    TipoPrenda zapatillaDesabrigada = new TipoPrenda(Categoria.CALZADO, new ArrayList<Material>(Arrays.asList(Material.CUERO)), 0,
            TipoUso.PRIMARIA);
    TipoPrenda ojota = new TipoPrenda(Categoria.CALZADO, new ArrayList<Material>(Arrays.asList(Material.CUERO)), 1,
            TipoUso.PRIMARIA);
    TipoPrenda anteojo = new TipoPrenda(Categoria.ACCESORIO,
            new ArrayList<Material>(Arrays.asList(Material.VIDRIO, Material.PLASTICO)), 0, TipoUso.SECUNDARIA);
    TipoPrenda campera = new TipoPrenda(Categoria.PARTE_SUPERIOR,
            new ArrayList<Material>(Arrays.asList(Material.LANA, Material.SEDA)), 20, TipoUso.SECUNDARIA);

    Borrador borrador_remeraAzul = new Borrador();
    Borrador borrador_remeraDeportiva = new Borrador();
    Borrador borrador_camperaGris = new Borrador();
    Borrador borrador_jeanRojo = new Borrador();
    Borrador borrador_zapatilla = new Borrador();
    Borrador borrador_jeanNegro = new Borrador();
    Borrador borrador_ojotas = new Borrador();
    Borrador borrador_anteojos = new Borrador();
    Borrador borrador_anteojosDeSol = new Borrador();
    Borrador borrador_gorroLana= new Borrador();
    Borrador borrador_remeraDesabrigada= new Borrador();
    Borrador borrador_remeraDe30= new Borrador();
    Borrador borrador_pantalonDesabrigado= new Borrador();
    Borrador borrador_zapatillasDesabrigadas= new Borrador();

    Prenda remeraAzul;
    Prenda remeraDeportiva;
    Prenda camperaGris;
    Prenda jeanRojo;
    Prenda jeanNegro;
    Prenda ojotas;
    Prenda zapatillasVerde;
    Prenda anteojos;
    Prenda anteojosDeSol;
    Prenda gorroLana;
    Prenda remeraDesabrigadaAzul;
    Prenda remeraDe30Azul;
    Prenda pantalonDesabrigadoAzul;
    Prenda zapatillasDesabrigadasAzules;

    Set<Prenda> prendasGlobales = new HashSet<Prenda>();
    Set<Prenda> prendasOrdenables = new HashSet<Prenda>();
    Set<Prenda> prendasJustito = new HashSet<Prenda>();

    Guardarropa guardarropa = new Guardarropa();
    Guardarropa otroGuardarropa = new Guardarropa();

    Usuario pedro = new Usuario();
    Usuario pedro2 = new Usuario();
    Usuario usuario1 = new Usuario();
    Usuario peter = new Usuario();

    Calendario calendario = new Calendario();
    Evento superclasico = new Evento("Superclasico", LocalDateTime.now(),"Cancha de Boca", Frecuencia.UNICO);
    Evento fiesta = new Evento("Fiesta",LocalDateTime.now(),"Capital",Frecuencia.UNICO);
    Evento finalDelMundial = new Evento("Final del mundial",LocalDateTime.now(),"China",Frecuencia.UNICO);
    Evento evento = new Evento("Prueba",LocalDateTime.now(),"UTN",Frecuencia.UNICO);
    AsistenciaEvento asistenciaMundial = new AsistenciaEvento(finalDelMundial);
    AsistenciaEvento asistenciaSuperclasico = new AsistenciaEvento(superclasico);
    AsistenciaEvento asistenciaFiesta = new AsistenciaEvento(fiesta);
    Atuendo atuendo1 = new Atuendo(remeraDe30Azul, pantalonDesabrigadoAzul, zapatillasVerde, guardarropa);
    //Atuendo atuendo2 = new Atuendo(remeraDesabrigadaAzul, pantalonDesabrigado, zapatillasDesabrigadasAzules, guardarropa);

    ColorRGB colorGenerico;

    public void setear(){
        ServicioClimatico.definirProvedor(new MockAgradable());
        remera.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));
        remeraDesabrigada.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));
        remeraDe30.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));
        campera.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));

        colorGenerico = new ColorRGB(255, 255, 0);

        borrador_remeraAzul.crearBorrador(colorGenerico, remera, Material.ALGODON);
        borrador_jeanRojo.crearBorrador(colorGenerico, pantalon, Material.JEAN);
        borrador_zapatilla.crearBorrador(colorGenerico, zapatilla, Material.CUERO);
        borrador_remeraDeportiva.crearBorrador(colorGenerico, remera, Material.ALGODON);
        borrador_jeanNegro.crearBorrador(colorGenerico, pantalon, Material.JEAN);
        borrador_ojotas.crearBorrador(colorGenerico, ojota, Material.CUERO);
        borrador_anteojos.crearBorrador(colorGenerico, anteojo, Material.VIDRIO);
        borrador_anteojosDeSol.crearBorrador(colorGenerico, anteojo, Material.PLASTICO);
        borrador_camperaGris.crearBorrador(colorGenerico, campera, Material.LANA);
        borrador_gorroLana.crearBorrador(colorGenerico, gorro, Material.LANA);
        borrador_remeraDesabrigada.crearBorrador(colorGenerico, remeraDesabrigada, Material.SEDA);
        borrador_remeraDe30.crearBorrador(colorGenerico, remeraDe30, Material.ALGODON);
        borrador_pantalonDesabrigado.crearBorrador(colorGenerico, pantalonDesabrigado, Material.JEAN);
        borrador_zapatillasDesabrigadas.crearBorrador(colorGenerico, zapatillaDesabrigada, Material.CUERO);



        borrador_remeraAzul.definirTrama(Trama.RAYADA);

        camperaGris = borrador_camperaGris.crearPrenda();
        remeraAzul = borrador_remeraAzul.crearPrenda();
        remeraDeportiva = borrador_remeraDeportiva.crearPrenda();
        jeanRojo = borrador_jeanRojo.crearPrenda();
        jeanNegro = borrador_jeanNegro.crearPrenda();
        ojotas = borrador_ojotas.crearPrenda();
        zapatillasVerde = borrador_zapatilla.crearPrenda();
        anteojos = borrador_anteojos.crearPrenda();
        anteojosDeSol = borrador_anteojosDeSol.crearPrenda();
        gorroLana = borrador_gorroLana.crearPrenda();
        remeraDesabrigadaAzul = borrador_remeraDesabrigada.crearPrenda();
        remeraDe30Azul = borrador_remeraDe30.crearPrenda();
        pantalonDesabrigadoAzul = borrador_pantalonDesabrigado.crearPrenda();
        zapatillasDesabrigadasAzules = borrador_zapatillasDesabrigadas.crearPrenda();

        prendasGlobales.add(remeraAzul);
        prendasGlobales.add(remeraDeportiva);
        prendasGlobales.add(jeanNegro);
        prendasGlobales.add(jeanRojo);
        prendasGlobales.add(ojotas);
        prendasGlobales.add(zapatillasVerde);
        prendasGlobales.add(anteojos);
        prendasGlobales.add(anteojosDeSol);
        prendasGlobales.add(camperaGris);
        //prendasGlobales.add(gorroLana);

        prendasOrdenables.add(remeraAzul);
        prendasOrdenables.add(remeraDe30Azul);
        prendasOrdenables.add(remeraDesabrigadaAzul);
        prendasOrdenables.add(pantalonDesabrigadoAzul);
        prendasOrdenables.add(zapatillasDesabrigadasAzules);

        prendasJustito.add(remeraAzul);
        prendasJustito.add(jeanRojo);
        prendasJustito.add(zapatillasVerde);
        usuario1.actualizarSubscripcionAPremium();
        usuario1.agregarGuardarropa(guardarropa);
        usuario1.agregarPrendas(guardarropa, prendasOrdenables);
        usuario1.setMail("usuario1@gmail.com");
        usuario1.setCalendarioEventos(calendario);
        usuario1.asistirAEvento(finalDelMundial);
        usuario1.asistirAEvento(superclasico);
        usuario1.asistirAEvento(fiesta);
        asistenciaMundial.generarSugerenciasParaEvento(usuario1);
        asistenciaSuperclasico.generarSugerenciasParaEvento(usuario1);
        asistenciaFiesta.generarSugerenciasParaEvento(usuario1);
        //usuario1.setPreferenciasDeAbrigo(abrigo);
        usuario1.agregarInformante(Informante.MockSMS);
        usuario1.agregarInformante(Informante.CasillaDeMails);
       // usuario1.aceptarAtuendo(atuendo1);
        usuario1.rechazarAtuendo(atuendo1);

        prendasJustito.add(remeraAzul);
        prendasJustito.add(jeanRojo);
        prendasJustito.add(zapatillasVerde);
        peter.actualizarSubscripcionAPremium();
        peter.agregarGuardarropa(guardarropa);
        peter.agregarPrendas(guardarropa, prendasOrdenables);
        peter.setMail("usuario1@gmail.com");
        peter.setCalendarioEventos(calendario);
        peter.asistirAEvento(finalDelMundial);
        peter.asistirAEvento(superclasico);
        peter.asistirAEvento(fiesta);
        asistenciaMundial.generarSugerenciasParaEvento(peter);
        asistenciaSuperclasico.generarSugerenciasParaEvento(peter);
        asistenciaFiesta.generarSugerenciasParaEvento(peter);
        //usuario1.setPreferenciasDeAbrigo(abrigo);
        peter.agregarInformante(Informante.MockSMS);
        peter.agregarInformante(Informante.CasillaDeMails);
        // usuario1.aceptarAtuendo(atuendo1);
        peter.rechazarAtuendo(atuendo1);
    }
}
