package Server;

import java.util.ArrayList;
import java.util.List;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Guardarropa;

public class ControllerGuardarropas {

    public ModelAndView listarGuardarropas(Request req, Response res) {
        String idUsuario = req.attribute("idUsuario");
        List<Guardarropa> guardarropas = new ArrayList<Guardarropa>(); //@TODO: obtener guardarropas del usuario logueado
        return new ModelAndView(guardarropas, "misGuardarropas.hbs");
    }

    private Guardarropa obtenerGuardarropaSegunId(String idUsuario, String idGuardarropa){
        Guardarropa guardarropaActual = new Guardarropa(); //@TODO: obtener guardarropa idGuardarropa del usuario correspondiente
        return guardarropaActual; 
    }

    public ModelAndView detalleGuardarropa(Request req, Response res) {
        Guardarropa guardarropa = obtenerGuardarropaSegunId(req.attribute("idUsuario"), req.params("idGuardarropas"));
        return new ModelAndView(guardarropa, "detalleGuardarropas.hbs");
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
