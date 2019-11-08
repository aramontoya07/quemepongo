package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import db.EntityManagerHelper;
import prenda.Categoria;
import prenda.Prenda;
import repositorios.RepositorioGuardarropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Guardarropa;
import usuario.GuardarropaVista;
import usuario.Usuario;

import java.util.HashMap;



public class ControllerGuardarropas {

    private Guardarropa obtenerGuardarropaSegunId(String idUsuario, String idGuardarropa){

        //Guardarropa guardarropaActual = EntityManagerHelper.getEntityManager().find(Guardarropa.class, Integer.parseInt(idGuardarropa)); //@TODO: obtener guardarropa idGuardarropa del usuario correspondiente
        Usuario usuario = (new SetUpUsuario()).setear();
        Guardarropa guardarropaActual = usuario.getGuardarropas().stream()
                .filter(guardarropa -> (Integer.parseInt(idGuardarropa)) == (new Integer(guardarropa.getId()))).findFirst().orElse(null);
        return guardarropaActual;
    }

    public ModelAndView detalleGuardarropa(Request req, Response res) {
        String id = req.params("idGuardarropas");
        Guardarropa guardarropa = RepositorioGuardarropa.obtenerGuardarropa(id);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("inferiores", guardarropa.getPrendasDeParte(Categoria.PARTE_INFERIOR));
        model.put("superiores", guardarropa.getPrendasDeParte(Categoria.PARTE_SUPERIOR));
        model.put("accesorios", guardarropa.getPrendasDeParte(Categoria.ACCESORIO));
        model.put("calazados", guardarropa.getPrendasDeParte(Categoria.CALZADO));
        return new ModelAndView(model, "detalleGuardarropas.hbs");
    }
    
    public ModelAndView wizardTipoPrenda(Request req, Response res) {
    	Guardarropa guardarropa = new Guardarropa();
        return new ModelAndView(guardarropa, "wizardTipoPrenda.hbs");
    }

    public ModelAndView wizardCaracteristicas(Request req, Response res) {
        Guardarropa guardarropa = new Guardarropa();
        return new ModelAndView(guardarropa, "wizardCaracteristicas.hbs");
    }

    public ModelAndView wizardAdjuntarImagen(Request req, Response res) {
        Guardarropa guardarropa = new Guardarropa();
        return new ModelAndView(guardarropa, "wizardAdjuntarImagen.hbs");
    }
    
    public ModelAndView agregarPrenda(Request req, Response res) {
        String tipoPrenda = req.queryParams("tipoPrenda");
        String material = req.queryParams("material");
        String trama = req.queryParams("trama");
        String colorPrimario = req.queryParams("colorPrimario");
        String colorSecundario = req.queryParams("colorSecundario");
        String rutaImagen = req.queryParams("imagen");
        String parteAbrigada = req.queryParams("parteAbrigada");
        String idGuardarropas = req.params("idGuardarropas");
        
        Prenda prenda = null;

        res.redirect("/guardarropas/" + idGuardarropas);
        return null;
    }
}
