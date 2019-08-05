package arenaUI;
import applicationModel.BuscadorEventos;
import eventos.AsistenciaEvento;
import eventos.Evento;
import org.uqbar.arena.bindings.DateTransformer;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.Button;

import java.awt.*;

@SuppressWarnings("serial")
public class BuscadorEventosWindow extends SimpleWindow<BuscadorEventos> {

    public BuscadorEventosWindow(WindowOwner parent) {
        super(parent, new BuscadorEventos());
        this.getModelObject().search();
    }

    @Override
    protected void createMainTemplate(Panel mainPanel) {
        this.setTitle("Buscador de Eventos");
        this.setTaskDescription("Ingrese los parámetros de búsqueda");

        super.createMainTemplate(mainPanel);

        this.createResultsGrid(mainPanel);
        this.createGridActions(mainPanel);
    }

    @Override
    protected void createFormPanel(Panel mainPanel) { //Panel que permite filtrar resultados entre dos fechas
        Panel searchFormPanel = new Panel(mainPanel);
        searchFormPanel.setLayout(new ColumnLayout(2));

        new Label(searchFormPanel).setText("Desde").setForeground(Color.BLUE);
        new TextBox(searchFormPanel).setWidth(150).bindValueToProperty("fechaDesde").setTransformer(new DateTransformer());

        new Label(searchFormPanel).setText("Hasta").setForeground(Color.BLUE);
        new TextBox(searchFormPanel).setWidth(150).bindValueToProperty("fechaHasta").setTransformer(new DateTransformer());
    }

    @Override
    protected void addActions(Panel actionsPanel) { //Acciones de la pantalla principal. Nosotros solo tenemos que buscar, no crear ni borrar
        new Button(actionsPanel)
                .setCaption("Buscar")
                .onClick(getModelObject()::search)
                .setAsDefault()
                .disableOnError();

        new Button(actionsPanel) //
                .setCaption("Limpiar")
                .onClick(getModelObject()::clear);

    }

    //Resultados de la búsqueda

    protected void createResultsGrid(Panel mainPanel) {
        Table<AsistenciaEvento> table = new Table<AsistenciaEvento>(mainPanel, AsistenciaEvento.class);
        table.setNumberVisibleRows(10);
        table.setWidth(450);

        table.bindItemsToProperty("resultados");

        this.describeResultsGrid(table);
    }

    //Define las columnas de la busqueda. Bindeo cada columna contra una propiedad del model (Buscador eventos), no de una AsistenciaEvento, por ende no sé que tan correcto es esto

    protected void describeResultsGrid(Table<AsistenciaEvento> table) {
        new Column<AsistenciaEvento>(table) //
                .setTitle("Nombre")
                .setFixedSize(100)
                .bindContentsToProperty("nombre");

        new Column<AsistenciaEvento>(table) //
                .setTitle("Fecha")
                .setFixedSize(100)
                .alignRight()
                .bindContentsToProperty("fecha");

        new Column<AsistenciaEvento>(table) //
                .setTitle("Lugar")
                .setFixedSize(100)
                .alignRight()
                .bindContentsToProperty("lugar");


        Column<AsistenciaEvento> sugerenciasColumn = new Column<AsistenciaEvento>(table);
        sugerenciasColumn.setTitle("Sugerencias");
        sugerenciasColumn.setFixedSize(50);
        sugerenciasColumn.bindContentsToProperty("tieneSugerencias").setTransformer(new BooleanTransformer());
    }

    protected void createGridActions(Panel mainPanel) {
        Panel actionsPanel = new Panel(mainPanel);
        actionsPanel.setLayout(new HorizontalLayout());

    }



}
