package Server;

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
                "https://http2.mlstatic.com/camiseta-remera-de-boca-juniors-con-pub-quilmes-D_NQ_NP_192615-MLA25293181174_012017-F.jpg");
        remeraDeportiva.setRutaImagen(
                "https://dexter.vteximg.com.br/arquivos/ids/491598-540-540/AJ5292739_1.jpg?v=637024472802500000");
        camperaGris.setRutaImagen(
                "https://d26lpennugtm8s.cloudfront.net/stores/062/559/products/jn_41-47e07d6db6b78bdcf715508634664412-1024-1024.jpg");
        jeanRojo.setRutaImagen(
                "https://electromenaje.net/6395-large_default/carro-de-la-compra-rolser-22-jean-rojo.jpg");
        jeanNegro.setRutaImagen(
                "https://i.ebayimg.com/images/g/KdkAAOSw2HxcmAo0/s-l300.jpg");
        ojotas.setRutaImagen(
                "https://http2.mlstatic.com/ojotas-de-ben-10-D_NQ_NP_424811-MLA20645149589_032016-F.jpg");
        zapatillasVerde.setRutaImagen(
                "https://http2.mlstatic.com/D_NQ_NP_709424-MLA31255200571_062019-V.jpg");
        anteojos.setRutaImagen(
                "https://loveartnotpeople.org/_dev/wp-content/uploads/2014/05/bnzhkoqieaau-po.jpg");
        anteojosDeSol.setRutaImagen(
                "https://http2.mlstatic.com/lentes-gafas-de-sol-thug-life-deal-with-it-pixeladas-disfraz-D_NQ_NP_835510-MLA31165124646_062019-F.jpg");
        gorroLana.setRutaImagen(
                "https://images-na.ssl-images-amazon.com/images/I/81TFS0vN2vL._UX569_.jpg");
        remeraDesabrigadaAzul.setRutaImagen(
                "https://dafitistaticar-a.akamaihd.net/p/andressa-2211-52088-1-product.jpg");
        remeraDe30Azul.setRutaImagen(
                "https://www.escamisetasbaloncestonba.com/images/Camisetas%20NBA%20Adidas/Golden%20State%20Warriors/Camiseta_Golden_State_Warriors_Stephen_Curry_NO_30_Azul2.jpg");
        pantalonDesabrigadoAzul.setRutaImagen(
                "https://mlstaticquic-a.akamaihd.net/penekini-azul-petroleo-lenceria-chiquita-hombre-ms035-D_NQ_NP_837240-MLU31429246785_072019-F.jpg");
        zapatillasDesabrigadasAzules.setRutaImagen(
                "https://www.podoactiva.com/sites/default/files/blogs.jpg");

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
        prendasOrdenables.add(remeraAzul);
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