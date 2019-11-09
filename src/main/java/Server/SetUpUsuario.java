package server;

import atuendo.Atuendo;
import clima.MockAgradable;
import clima.ServicioClimatico;
import db.EntityManagerHelper;
import eventos.AsistenciaEvento;
import eventos.Calendario;
import eventos.Evento;
import eventos.Frecuencia;
import notificaciones.Informante;
import prenda.*;
import repositorios.RepositorioUsuarios;
import usuario.Guardarropa;
import usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetUpUsuario {
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

    Usuario usuario1 = new Usuario();


    Calendario calendario = new Calendario();
    Evento superclasico = new Evento("Superclasico", LocalDateTime.now(),"Cancha de Boca", Frecuencia.UNICO);
    Evento fiesta = new Evento("Fiesta",LocalDateTime.now().minusDays(5),"Capital",Frecuencia.UNICO);
    Evento finalDelMundial = new Evento("Final del mundial",LocalDateTime.now(),"China",Frecuencia.UNICO);
    Evento evento = new Evento("Prueba",LocalDateTime.now(),"UTN",Frecuencia.UNICO);
    AsistenciaEvento asistenciaMundial = new AsistenciaEvento(finalDelMundial);
    AsistenciaEvento asistenciaSuperclasico = new AsistenciaEvento(superclasico);
    AsistenciaEvento asistenciaFiesta = new AsistenciaEvento(fiesta);


    public static void main(String args[]){
        SetUpUsuario setUp = new SetUpUsuario();
        setUp.cargarUsuario();
        return;
    }

    private void cargarUsuario() {
        ServicioClimatico.definirProvedor(new MockAgradable());
        remera.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));
        remeraDesabrigada.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));
        remeraDe30.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));
        campera.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));

        borrador_remeraAzul.crearBorrador(new ColorRGB(255, 255, 0), remera, Material.ALGODON);
        borrador_jeanRojo.crearBorrador(new ColorRGB(255, 0, 0), pantalon, Material.JEAN);
        borrador_zapatilla.crearBorrador(new ColorRGB(55, 123, 60), zapatilla, Material.CUERO);
        borrador_remeraDeportiva.crearBorrador(new ColorRGB(77, 4, 10), remera, Material.ALGODON);
        borrador_jeanNegro.crearBorrador(new ColorRGB(255, 0, 255), pantalon, Material.JEAN);
        borrador_ojotas.crearBorrador(new ColorRGB(0, 0, 0), ojota, Material.CUERO);
        borrador_anteojos.crearBorrador(new ColorRGB(0, 0, 0), anteojo, Material.VIDRIO);
        borrador_anteojosDeSol.crearBorrador(new ColorRGB(0, 0, 0), anteojo, Material.PLASTICO);
        borrador_camperaGris.crearBorrador(new ColorRGB(245, 5, 128), campera, Material.LANA);
        borrador_gorroLana.crearBorrador(new ColorRGB(255, 0, 0), gorro, Material.LANA);
        borrador_remeraDesabrigada.crearBorrador(new ColorRGB(255, 0, 0), remeraDesabrigada, Material.SEDA);
        borrador_remeraDe30.crearBorrador(new ColorRGB(255, 0, 0), remeraDe30, Material.ALGODON);
        borrador_pantalonDesabrigado.crearBorrador(new ColorRGB(255, 0, 0), pantalonDesabrigado, Material.JEAN);
        borrador_zapatillasDesabrigadas.crearBorrador(new ColorRGB(255, 0, 0), zapatillaDesabrigada, Material.CUERO);



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

        remeraAzul.setRutaImagen(
                "https://http2.mlstatic.com/remera-de-nino-algodon-azul-francia-D_NQ_NP_721716-MLA31030549905_062019-Q.jpg");
        remeraDeportiva.setRutaImagen(
                "https://media.fotter.com.ar/media/catalog/product/cache/1/thumbnail/9df78eab33525d08d6e5fb8d27136e95/3/8/381i0011-bl0-1_1.jpg");
        camperaGris.setRutaImagen(
                "https://dafitistaticar-a.akamaihd.net/p/valkymia-2822-129633-1-product.jpg");
        jeanRojo.setRutaImagen(
                "https://static.modymarket.com/uploads/2016/09/109554-1.jpg");
        jeanNegro.setRutaImagen(
                "https://mlstaticquic-a.akamaihd.net/pantalon-de-jean-negro-para-hombre-identity-gas-oil-muy-cool-D_NQ_NP_768061-MLU26942693685_032018-F.webp");
        ojotas.setRutaImagen(
                "https://http2.mlstatic.com/zonazero-havaianas-ojotas-slim-acero-mujer-originales-D_NQ_NP_641590-MLA31100638725_062019-Q.jpg");
        zapatillasVerde.setRutaImagen(
                "https://http2.mlstatic.com/D_NQ_NP_709424-MLA31255200571_062019-V.jpg");
        anteojos.setRutaImagen(
                "https://www.dhresource.com/0x0/f2/albu/g7/M01/2C/33/rBVaSlvELDGADeK8AABj623bG1c255.jpg");
        anteojosDeSol.setRutaImagen(
                "https://http2.mlstatic.com/anteojos-lentes-de-sol-vulk-ezy-polarizados-redondos-gafas-D_NQ_NP_794294-MLA31043118113_062019-Q.jpg");
        gorroLana.setRutaImagen(
                "https://images-na.ssl-images-amazon.com/images/I/81TFS0vN2vL._UX569_.jpg");
        remeraDesabrigadaAzul.setRutaImagen(
                "https://http2.mlstatic.com/top-azul-para-salir-con-encaje-talle-s-D_NQ_NP_620521-MLA29905488094_042019-F.jpg");
        remeraDe30Azul.setRutaImagen(
                "https://http2.mlstatic.com/remera-de-nino-algodon-azul-marino-D_NQ_NP_868952-MLA28080545043_092018-F.jpg");
        pantalonDesabrigadoAzul.setRutaImagen(
                "https://s.fenicio.app/f/tht/catalogo/articulos/I19BB054-AZL-1_2000-2000_1554725444_5b5.jpg");
        zapatillasDesabrigadasAzules.setRutaImagen(
                "https://cdn.lookastic.es/zapatillas-azules/nike-original-886391.jpg");

        prendasGlobales.add(remeraAzul);
        prendasGlobales.add(remeraDeportiva);
        prendasGlobales.add(jeanNegro);
        prendasGlobales.add(jeanRojo);
        prendasGlobales.add(ojotas);
        prendasGlobales.add(zapatillasVerde);
        prendasGlobales.add(anteojos);
        prendasGlobales.add(anteojosDeSol);
        prendasGlobales.add(camperaGris);

        Atuendo atuendo1 = new Atuendo(remeraDe30Azul, pantalonDesabrigadoAzul, zapatillasVerde, guardarropa);
        Atuendo atuendo2 = new Atuendo(remeraDesabrigadaAzul, jeanRojo, zapatillasDesabrigadasAzules, guardarropa);

        prendasOrdenables.add(remeraAzul);
        prendasOrdenables.add(remeraDe30Azul);
        prendasOrdenables.add(remeraDesabrigadaAzul);
        prendasOrdenables.add(pantalonDesabrigadoAzul);
        prendasOrdenables.add(zapatillasDesabrigadasAzules);
        prendasOrdenables.add(jeanRojo);
        prendasOrdenables.add(zapatillasVerde);

        usuario1.actualizarSubscripcionAPremium();
        usuario1.agregarGuardarropa(guardarropa);
        usuario1.agregarPrendas(guardarropa, prendasOrdenables);
        usuario1.setCalendarioEventos(calendario);
        usuario1.asistirAEvento(finalDelMundial);
        usuario1.asistirAEvento(superclasico);
        usuario1.asistirAEvento(fiesta);
        usuario1.setMail("nico.gomez.mbc@gmail.com");
        usuario1.setContrasenia("469925199");
        usuario1.setNombre("NicoxxxKpo");
        asistenciaMundial.generarSugerenciasParaEvento(usuario1);
        asistenciaSuperclasico.generarSugerenciasParaEvento(usuario1);
        asistenciaFiesta.generarSugerenciasParaEvento(usuario1);
        usuario1.agregarInformante(Informante.MockSMS);
        usuario1.agregarInformante(Informante.CasillaDeMails);

        usuario1.aceptarAtuendo(atuendo2);
        usuario1.aceptarAtuendo(atuendo1);

        prendasJustito.add(remeraAzul);
        prendasJustito.add(jeanRojo);
        prendasJustito.add(zapatillasVerde);

        RepositorioUsuarios.persistirUsuario(usuario1);
        return;
    }

    public Usuario setear(){ //TODO: DEPRECATE. ya tenemos base de datos y el metodo cargarUsuario(), asi que hay que borrar este metodo que devuelve un usuario
        return new Usuario();
    }
}