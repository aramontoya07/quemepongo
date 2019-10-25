package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import db.EntityManagerHelper;
import prenda.Categoria;
import prenda.Prenda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Guardarropa;
import usuario.GuardarropaVista;
import usuario.Usuario;



public class ControllerGuardarropas {

    private Guardarropa obtenerGuardarropaSegunId(String idUsuario, String idGuardarropa){

        //Guardarropa guardarropaActual = EntityManagerHelper.getEntityManager().find(Guardarropa.class, Integer.parseInt(idGuardarropa)); //@TODO: obtener guardarropa idGuardarropa del usuario correspondiente
        Usuario usuario = (new SetUpUsuario()).setear();
        Guardarropa guardarropaActual = usuario.getGuardarropas().stream()
                .filter(guardarropa -> (Integer.parseInt(idGuardarropa)) == (new Integer(guardarropa.getId()))).findFirst().orElse(null);
        return guardarropaActual;
    }

    public ModelAndView detalleGuardarropa(Request req, Response res) {
        Guardarropa guardarropa = obtenerGuardarropaSegunId(req.attribute("idUsuario"), req.params("idGuardarropas"));

        GuardarropaVista gv = new GuardarropaVista(guardarropa);
        return new ModelAndView(gv, "detalleGuardarropas.hbs");
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
        String tipoPrenda = req.params("tipoPrenda");
        String material = req.params("material");
        String trama = req.params("trama");
        String colorPrimario = req.params("colorPrimario");
        String colorSecundario = req.params("colorSecundario");
        String imagen = req.params("imagen");
        String parteAbrigada = req.params("parteAbrigada");
        
        //@TODO generar request que guarde la nueva prenda

        res.redirect("/guardarropas/:" + req.params("idGuardarropas"));
        return null;
    }
}
