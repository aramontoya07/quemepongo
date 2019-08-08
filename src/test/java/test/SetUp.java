package test;

import clima.MockAgradable;
import clima.ServicioClimatico;
import prenda.*;
import usuario.Guardarropa;
import usuario.Usuario;

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

    Guardarropa guardarropa = new Guardarropa();
    Guardarropa otroGuardarropa = new Guardarropa();

    Usuario pedro = new Usuario();


    public void setear(){
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
    }
}
