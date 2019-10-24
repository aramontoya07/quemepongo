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
import usuario.Usuario;

public class ControllerGuardarropas {

    private Guardarropa obtenerGuardarropaSegunId(String idUsuario, String idGuardarropa){
        Guardarropa guardarropaActual = EntityManagerHelper.getEntityManager().find(Guardarropa.class, Integer.parseInt(idGuardarropa)); //@TODO: obtener guardarropa idGuardarropa del usuario correspondiente
        return guardarropaActual; 
    }

    public ModelAndView detalleGuardarropa(Request req, Response res) {
        Guardarropa guardarropa = obtenerGuardarropaSegunId(req.attribute("idUsuario"), req.params("idGuardarropas"));
        class GuardarropaVista{
            int id;
            Set<Prenda> superiores;
            Set<Prenda> inferiores;
            Set<Prenda> calzado;
            Set<Prenda> accesorios;
    
            public GuardarropaVista(Guardarropa guardarropa){
                id = guardarropa.getId();
                System.out.println(guardarropa.cantidadDePrendas());
                superiores = guardarropa.getPrendasDeParte(Categoria.PARTE_SUPERIOR);
                System.out.println(superiores.toString());
                inferiores = guardarropa.getPrendasDeParte(Categoria.PARTE_INFERIOR);
                calzado = guardarropa.getPrendasDeParte(Categoria.CALZADO);
                accesorios = guardarropa.getPrendasDeParte(Categoria.ACCESORIO);
            }
        }
        GuardarropaVista gv = new GuardarropaVista(guardarropa);
        return new ModelAndView(gv, "detalleGuardarropas.hbs");
    }
    
    public ModelAndView wizardTipoPrenda(Request req, Response res) {
    	Guardarropa guardarropa = new Guardarropa();
        return new ModelAndView(guardarropa, "wizardTipoPrenda.hbs");
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
