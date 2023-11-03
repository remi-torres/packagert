package com.example.packagert;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.column.ColumnService;
import com.example.packagert.common.DbConnector;
import com.example.packagert.generator.ScriptGenerator;
import com.example.packagert.project.ProjectEntity;
import com.example.packagert.project.ProjectService;
import com.example.packagert.table.TableEntity;
import com.example.packagert.table.TableService;
import com.example.packagert.type.TypeEntity;
import com.example.packagert.type.TypeService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class HelloController {

    private final ScriptGenerator generator = new ScriptGenerator();
    private final ObjectProperty<ProjectEntity> selectedProject = new SimpleObjectProperty<>();
    private final ObjectProperty<TableEntity> selectedTable = new SimpleObjectProperty<>();
    private final ListProperty<ColumnEntity> columns = new SimpleListProperty<>();
    private final ListProperty<ProjectEntity> projects = new SimpleListProperty<>();
    private final ListProperty<TableEntity> tables = new SimpleListProperty<>();
    private final ListProperty<TypeEntity> types = new SimpleListProperty<>();
    private ColumnService columnService;
    private ProjectService projectService;
    private TableService tableService;
    private TypeService typeService;
    @FXML
    private TextField projectTextField;
    @FXML
    private ListView<ProjectEntity> projectListView;
    @FXML
    private TextField tableTextField;
    @FXML
    private ListView<TableEntity> tableListView;
    @FXML
    private Label projectTableLink;
    @FXML
    private TextField columnNameTextField;
    @FXML
    private ComboBox<TypeEntity> columnTypeComboBox;
    @FXML
    private TableView<ColumnEntity> columnTableView;
    @FXML
    private TableColumn<ColumnEntity, String> columnTableViewTable;
    @FXML
    private TableColumn<ColumnEntity, String> columnTableViewName;
    @FXML
    private TableColumn<ColumnEntity, String> columnTableViewType;


    @FXML
    protected void projectAddAction() {
        projectService.addProject(projectTextField.getText());
        updateProjectListView();
    }

    @FXML
    protected void tableAddAction() {
        if(this.selectedProject.isNotNull().get()){
            tableService.addTable(selectedProject.get().getId(), tableTextField.getText());
            updateTablesListView(selectedProject.get().getId());
        }

    }

    @FXML
    protected void columnAddAction(){
        if(this.selectedTable.isNotNull().get() && !this.columnNameTextField.getText().isBlank() && this.columnTypeComboBox.getSelectionModel().getSelectedItem() != null){
            columnService.addColumn(selectedTable.get().getId(), this.columnNameTextField.getText(), this.columnTypeComboBox.getSelectionModel().getSelectedItem().getId());
            updateColumnTable(selectedTable.get());
        }

    }

    @FXML
    protected void sqliteCreateAction(){
        final TableEntity table = selectedTable.get();
        table.setColumns(columns);
        generator.generateSqlite(table);
    }

    @FXML
    protected void javaCreateAction(){
        final TableEntity table = selectedTable.get();
        table.setColumns(columns);
        generator.generateJavaEntity(table);
    }

    @FXML
    protected void dtoCreateAction(){
        final TableEntity table = selectedTable.get();
        table.setColumns(columns);
        generator.generateJavaDto(table);
    }

    @FXML
    protected void dtoMappingCreateAction() throws IOException, URISyntaxException {
        final TableEntity table = selectedTable.get();
        table.setColumns(columns);
        generator.generateMappingService(table);
    }


    @FXML
    private void initialize() {
        final DbConnector connector = new DbConnector(DbConnector.DB_PATH);
        this.columnService = new ColumnService(connector);
        this.projectService = new ProjectService(connector);
        this.tableService = new TableService(connector);
        this.typeService = new TypeService(connector);

        // bindings

        projects.set(FXCollections.observableArrayList(projectService.getProjects()));

        this.projectListView.setItems(projects);
        this.selectedProject.bind(this.projectListView.getSelectionModel().selectedItemProperty());
        this.projectListView.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> updateTablesListView(nv.getId()));
        this.selectedTable.bind(this.tableListView.getSelectionModel().selectedItemProperty());
        this.projectTableLink.textProperty().bind(getProjectTableLink());

        this.tableListView.setItems(tables);

        this.columnTypeComboBox.setItems(types);

        this.selectedTable.addListener((ob, ov, nv) -> updateColumnTable(nv));

        this.columnTableViewTable.setCellValueFactory(cellData -> cellData.getValue().getTable().nameProperty());
        this.columnTableViewName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.columnTableViewType.setCellValueFactory(cellData -> cellData.getValue().getType().nameProperty());


    }

    private void updateColumnTable(final TableEntity table) {
        if(table != null){
            List<ColumnEntity> col = columnService.getColumns(table.getId());
            columns.set(FXCollections.observableArrayList(col));
            this.columnTableView.setItems(columns);
        }

    }

    private StringExpression getProjectTableLink(){
        return Bindings.concat(selectedProject," > ", selectedTable);
    }

    private void updateProjectListView() {
        this.projects.set(FXCollections.observableArrayList(projectService.getProjects()));
    }

    private void updateTablesListView(final long projectId) {
       tables.set(FXCollections.observableArrayList(tableService.getTables(projectId)));
    }

}
