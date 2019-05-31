package test;

import prenda.*;
import usuario.Guardarropas;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public abstract class SetUp {
    TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR,
            new ArrayList<Material>(Arrays.asList(Material.ALGODON, Material.SEDA)), 12, TipoUso.PRIMARIA);
    TipoPrenda pantalon = new TipoPrenda(Categoria.PARTE_INFERIOR,
            new ArrayList<Material>(Arrays.asList(Material.JEAN, Material.CUERO, Material.ALGODON)), 5,
            TipoUso.PRIMARIA);
    TipoPrenda zapatilla = new TipoPrenda(Categoria.CALZADO, new ArrayList<Material>(Arrays.asList(Material.CUERO)), 3,
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

    Prenda remeraAzul;
    Prenda remeraDeportiva;
    Prenda camperaGris;
    Prenda jeanRojo;
    Prenda jeanNegro;
    Prenda ojotas;
    Prenda zapatillasVerde;
    Prenda anteojos;
    Prenda anteojosDeSol;

    Set<Prenda> prendasGlobales = new HashSet<Prenda>();

    Guardarropas guardarropa = new Guardarropas();
    Guardarropas otroGuardarropa = new Guardarropas();

    Usuario pedro = new Usuario();


    public void setear(){
        remera.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));

        borrador_remeraAzul.crearBorrador(new ColorRGB(255, 255, 0), remera, Material.ALGODON);
        borrador_jeanRojo.crearBorrador(new ColorRGB(255, 0, 0), pantalon, Material.JEAN);
        borrador_zapatilla.crearBorrador(new ColorRGB(55, 123, 60), zapatilla, Material.CUERO);
        borrador_remeraDeportiva.crearBorrador(new ColorRGB(77, 4, 10), remera, Material.ALGODON);
        borrador_jeanNegro.crearBorrador(new ColorRGB(255, 0, 255), pantalon, Material.JEAN);
        borrador_ojotas.crearBorrador(new ColorRGB(0, 0, 0), zapatilla, Material.CUERO);
        borrador_anteojos.crearBorrador(new ColorRGB(0, 0, 0), anteojo, Material.VIDRIO);
        borrador_anteojosDeSol.crearBorrador(new ColorRGB(0, 0, 0), anteojo, Material.PLASTICO);
        borrador_camperaGris.crearBorrador(new ColorRGB(245, 5, 128), campera, Material.LANA);

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

        prendasGlobales.add(remeraAzul);
        prendasGlobales.add(remeraDeportiva);
        prendasGlobales.add(jeanNegro);
        prendasGlobales.add(jeanRojo);
        prendasGlobales.add(ojotas);
        prendasGlobales.add(zapatillasVerde);
        prendasGlobales.add(anteojos);
        prendasGlobales.add(anteojosDeSol);
    }
}
