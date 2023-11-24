package com.example.packagert;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.column.ColumnService;
import com.example.packagert.common.DbConnector;
import com.example.packagert.feature.FeatureEntity;
import com.example.packagert.feature.FeatureService;
import com.example.packagert.generator.ScriptGenerator;
import com.example.packagert.generator.typegenerator.TypeGeneratorEnum;
import com.example.packagert.project.ProjectEntity;
import com.example.packagert.project.ProjectService;
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

import java.util.List;

public class HelloController {

    private final ScriptGenerator generator = new ScriptGenerator();
    private final ObjectProperty<ProjectEntity> selectedProject = new SimpleObjectProperty<>();
    private final ObjectProperty<FeatureEntity> selectedFeature = new SimpleObjectProperty<>();
    private final ListProperty<ColumnEntity> columns = new SimpleListProperty<>();
    private final ListProperty<ProjectEntity> projects = new SimpleListProperty<>();
    private final ListProperty<FeatureEntity> tables = new SimpleListProperty<>();
    private final ListProperty<TypeGeneratorEnum> types = new SimpleListProperty<>();
    private ColumnService columnService;
    private ProjectService projectService;
    private FeatureService featureService;
    private TypeService typeService;


    @FXML
    private TextField projectNameTextField;
    @FXML
    private TextField projectPathTextField;
    @FXML
    private ListView<ProjectEntity> projectListView;
    @FXML
    private TextField featureNameTextField;
    @FXML
    private ComboBox<FeatureEntity> featureParentComboBox;
    @FXML
    private ListView<FeatureEntity> featureListView;
    @FXML
    private Label projectTableLink;
    @FXML
    private TextField columnNameTextField;
    @FXML
    private ComboBox<TypeGeneratorEnum> columnTypeComboBox;
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
        if(! projectNameTextField.getText().isEmpty() && !projectPathTextField.getText().isEmpty()){
            projectService.addProject(projectNameTextField.getText(), projectPathTextField.getText());
            updateProjectListView();
        }

    }

    @FXML
    protected void featureAddAction() {
        if(this.selectedProject.isNotNull().get()){
            if(featureParentComboBox.selectionModelProperty().get().getSelectedItem() == null)
                featureService.addFeature(selectedProject.get().getId(), featureNameTextField.getText());
            else {
                long parentId = featureParentComboBox.selectionModelProperty().get().getSelectedItem().getId();
                featureService.addFeature(selectedProject.get().getId(), featureNameTextField.getText(), parentId);
            }

            updateTablesListView(selectedProject.get().getId());
        }

    }

    @FXML
    protected void columnAddAction(){
        if(this.selectedFeature.isNotNull().get() && !this.columnNameTextField.getText().isBlank() && this.columnTypeComboBox.getSelectionModel().getSelectedItem() != null){
            columnService.addColumn(selectedFeature.get().getId(), this.columnNameTextField.getText(), this.columnTypeComboBox.getSelectionModel().getSelectedItem().toString());
            updateColumnTable(selectedFeature.get());
        }

    }

    @FXML
    protected void sqliteCreateAction(){
//        final FeatureEntity table = selectedFeature.get();
//        table.setColumns(columns);
//        generator.generateSqlite(table);
    }

    @FXML
    protected void javaCreateAction(){
//        final FeatureEntity table = selectedFeature.get();
//        table.setColumns(columns);
//        generator.generateJavaEntity(table);
    }

    @FXML
    protected void dtoCreateAction(){
//        final FeatureEntity table = selectedFeature.get();
//        table.setColumns(columns);
//        generator.generateJavaDto(table);
    }

    @FXML
    protected void dtoMappingCreateAction() {
//        final FeatureEntity table = selectedFeature.get();
//        table.setColumns(columns);
//        generator.generateMappingService(table);
    }


    @FXML
    private void initialize() {
        final DbConnector connector = new DbConnector(DbConnector.DB_PATH);
        this.columnService = new ColumnService(connector);
        this.projectService = new ProjectService(connector);
        this.featureService = new FeatureService(connector);
        this.typeService = new TypeService(connector);

        // bindings

        projects.set(FXCollections.observableArrayList(projectService.getProjects()));

        this.projectListView.setItems(projects);
        this.projectListView.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> updateTablesListView(nv.getId()));
        this.projectTableLink.textProperty().bind(getProjectTableLink());
        this.selectedProject.bind(this.projectListView.getSelectionModel().selectedItemProperty());

        // feature
        this.featureParentComboBox.setItems(tables);
        this.featureListView.setItems(tables);
        this.selectedFeature.bind(this.featureListView.getSelectionModel().selectedItemProperty());
        this.selectedFeature.addListener((ob, ov, nv) -> updateColumnTable(nv));

        // column
        types.setValue(FXCollections.observableArrayList(TypeGeneratorEnum.values()));
        this.columnTypeComboBox.setItems(types);
        this.columnTableViewTable.setCellValueFactory(cellData -> cellData.getValue().getFeature().nameProperty());
        this.columnTableViewName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//        this.columnTableViewType.setCellValueFactory(cellData -> cellData.getValue().getType().nameProperty());
        this.columnTypeComboBox.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> manageTypeParametersDisplay(nv));



    }

    private void manageTypeParametersDisplay(final TypeGeneratorEnum type) {
        columnNameTextField.setText("");

        columnNameTextField.setDisable(TypeGeneratorEnum.ID.equals(type));
        if(TypeGeneratorEnum.ID.equals(type))
            columnNameTextField.setText("id");

 //       type.getType().getAvailableParameters().forEach(param -> System.out.println(param.getLabel()));
    }

    private void updateColumnTable(final FeatureEntity table) {
        if(table != null){
            List<ColumnEntity> col = columnService.getColumns(table.getId());
            columns.set(FXCollections.observableArrayList(col));
            this.columnTableView.setItems(columns);
        }

    }

    private StringExpression getProjectTableLink(){
        return Bindings.concat(selectedProject," > ", selectedFeature);
    }

    private void updateProjectListView() {
        this.projects.set(FXCollections.observableArrayList(projectService.getProjects()));
    }

    private void updateTablesListView(final long projectId) {
       tables.set(FXCollections.observableArrayList(featureService.getFeatures(projectId)));
    }

}
