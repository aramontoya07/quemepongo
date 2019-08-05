package arenaUI.Runnable;
import arenaUI.BuscadorEventosWindow;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import applicationModel.BuscadorEventos;
import org.uqbar.commons.applicationContext.ApplicationContext;

public class QueMePongoApplication extends Application{
    public static void main(String[] args) {
        new QueMePongoApplication().start();
    }

    @Override
    protected Window<?> createMainWindow() {
        return new BuscadorEventosWindow(this);
    }
}
