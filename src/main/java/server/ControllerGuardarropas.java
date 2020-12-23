package server;

import db.EntityManagerHelper;
import prenda.*;
import repositorios.RepositorioGuardarropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Guardarropa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ControllerGuardarropas {
    private static final String ID_USUARIO = "idUsuario";

    public ModelAndView detalleGuardarropa(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(idUsuario == null){
            return new ModelAndView(model, "error.hbs");
        }
        String id = req.params("idGuardarropas");
        Guardarropa guardarropa = RepositorioGuardarropa.obtenerGuardarropa(id);

        model.put(ID_USUARIO,idUsuario);
        model.put("inferiores", guardarropa.getPrendasDeParte(Categoria.PARTE_INFERIOR));
        model.put("superiores", guardarropa.getPrendasDeParte(Categoria.PARTE_SUPERIOR));
        model.put("accesorios", guardarropa.getPrendasDeParte(Categoria.ACCESORIO));
        model.put("calzados", guardarropa.getPrendasDeParte(Categoria.CALZADO));
        model.put("guardarropa", guardarropa);
        return new ModelAndView(model, "detalleGuardarropas.hbs");
    }
    
    public ModelAndView wizardTipoPrenda(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(idUsuario == null){
            return new ModelAndView(model, "error.hbs");
        }
    	Guardarropa guardarropa = new Guardarropa();
        return new ModelAndView(guardarropa, "wizardTipoPrenda.hbs");
    }

    public ModelAndView wizardCaracteristicas(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(idUsuario == null){
            return new ModelAndView(model, "error.hbs");
        }
        Guardarropa guardarropa = new Guardarropa();
        return new ModelAndView(guardarropa, "wizardCaracteristicas.hbs");
    }

    public ModelAndView wizardAdjuntarImagen(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(idUsuario == null){
            return new ModelAndView(model, "error.hbs");
        }
        Guardarropa guardarropa = new Guardarropa();
        return new ModelAndView(guardarropa, "wizardAdjuntarImagen.hbs");
    }
    
    public ModelAndView agregarPrenda(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(idUsuario == null){
            return new ModelAndView(model, "error.hbs");
        }
        int nivelDeAbrigo = Integer.parseInt(req.queryParams("nivelDeAbrigo"));
        TipoUso tipoUso = parsearTipoUso(req.queryParams("tipoUso"));
        Material material = parsearMaterial(req.queryParams("material"));
        List<Material> materialPermitido = new ArrayList<Material>(); 
        Categoria categoria = parsearCategoria(req.queryParams("categoria"));
        Trama trama = parsearTrama(req.queryParams("trama"));
        ColorRGB colorPrimario = parsearColor(req.queryParams("colorPrimario"));
        ColorRGB colorSecundario = parsearColor(req.queryParams("colorSecundario"));
        String rutaImagen = req.queryParams("rutaImagen");
        ParteAbrigada parteAbrigada = parsearParteAbrigada(req.queryParams("parteAbrigada"));
        String idGuardarropas = req.params("idGuardarropas");
        Guardarropa guardarropaActual = RepositorioGuardarropa.obtenerGuardarropa(idGuardarropas);
        
        TipoPrenda tipoPrenda = new TipoPrenda(categoria, materialPermitido, nivelDeAbrigo, tipoUso);
        Prenda prenda = new Prenda(tipoPrenda, material, trama, colorPrimario, colorSecundario);
        prenda.setPateAbrigada(parteAbrigada);
        prenda.setRutaImagen(rutaImagen);

        EntityManagerHelper.beginTransaction();
        guardarropaActual.agregarADisponibles(prenda);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    
        res.redirect("/misGuardarropas/" + idGuardarropas);
        return null;
    }

    public TipoUso parsearTipoUso(String material) {
        switch (material) {
        case "primaria":
            return TipoUso.PRIMARIA;
        case "secundaria":
            return TipoUso.SECUNDARIA;
        default: 
            return TipoUso.PRIMARIA;
        }
    }

    public Categoria parsearCategoria(String categoria){
        switch (categoria) {
        case "superior":
            return Categoria.PARTE_SUPERIOR;
        case "inferior":
            return Categoria.PARTE_INFERIOR;
        case "calzado":
            return Categoria.CALZADO;
        case "accesorio":
            return Categoria.ACCESORIO;
        default:
            return Categoria.PARTE_SUPERIOR;
        }
    }

    public Material parsearMaterial(String material){
        switch (material) {
            case "algodon":
                return Material.ALGODON;
            case "jean":
                return Material.JEAN;
            case "cuero":
                return Material.CUERO;
            case "lana":
                return Material.LANA;
            case "plastico":
                return Material.PLASTICO;
            case "vidrio":
                return Material.VIDRIO;
            case "seda":
                return Material.SEDA;
            default: 
                return Material.ALGODON;
        }
    }

    public Trama parsearTrama(String trama){
        switch (trama) {
            case "lisa":
                return Trama.LISA;
            case "rayada":
                return Trama.RAYADA;
            case "lunares":
                return Trama.LUNARES;
            case "cuadros":
                return Trama.CUADROS;
            case "estampado":
                return Trama.ESTAMPADO;
            default:
                return Trama.LISA;
        }
    }

    public ParteAbrigada parsearParteAbrigada(String parteAbrigada){
        switch (parteAbrigada) {
            case "cabeza":
                return ParteAbrigada.CABEZA;
            case "cuello":
                return ParteAbrigada.CUELLO;
            case "pecho":
                return ParteAbrigada.PECHO;
            case "manos":
                return ParteAbrigada.MANOS;
            case "piernas":
                return ParteAbrigada.PIERNAS;
            case "pies":
                return ParteAbrigada.PIES;
            default:
                return ParteAbrigada.CABEZA;
        }
    }

    public ColorRGB parsearColor(String colorHexa){
        return new ColorRGB(Integer.valueOf(colorHexa.substring(1, 3), 16), Integer.valueOf(colorHexa.substring(3, 5), 16),
                Integer.valueOf(colorHexa.substring(5, 7), 16));
    }
}

