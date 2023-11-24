package com.example.packagert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {

        // general
//        ProjectEntity general = new ProjectEntity();
//        general.nameProperty().set("General");
//
//        FeatureEntity aircraftType = new FeatureEntity();
//        aircraftType.nameProperty().set("aircraftType");
//        aircraftType.projectProperty().set(general);
//
//        // maintenance
//        ProjectEntity project = new ProjectEntity();
//        project.nameProperty().set("Maintenance");
//
//        FeatureEntity baseEntity = new FeatureEntity();
//        baseEntity.nameProperty().set("base");
//        baseEntity.projectProperty().set(project);
//
//        ColumnEntity baseId = new ColumnEntity();
//        baseId.typeProperty().set(TypeGeneratorEnum.ID);
//        baseId.nameProperty().set("id");
//        baseId.featureProperty().set(baseEntity);
//        baseEntity.getColumns().add(baseId);
//
//        ColumnEntity baseStudy = new ColumnEntity();
//        baseStudy.typeProperty().set(TypeGeneratorEnum.MANY_TO_ONE);
//        baseStudy.nameProperty().set("study");
//        baseStudy.set(TgParameterEnum.LINKED_ENTITY_TYPE, "MaintenanceStudy");
//        baseStudy.set(TgParameterEnum.CASCADE_DELETION, true);
//        baseStudy.featureProperty().set(baseEntity);
//        baseEntity.getColumns().add(baseStudy);
//
//        ColumnEntity baseAirport = new ColumnEntity();
//        baseAirport.typeProperty().set(TypeGeneratorEnum.STRING);
//        baseAirport.nameProperty().set("airport");
//        baseAirport.featureProperty().set(baseEntity);
//        baseAirport.set(TgParameterEnum.SIZE_MAX, 20);
//        baseAirport.set(TgParameterEnum.NULLABLE, false);
//        baseEntity.getColumns().add(baseAirport);
//
//        ColumnEntity baseAircraftTypes = new ColumnEntity();
//        baseAircraftTypes.typeProperty().set(TypeGeneratorEnum.MANY_TO_MANY);
//        baseAircraftTypes.nameProperty().set("aircraftTypes");
//        baseAircraftTypes.getParameters().setLinkedEntity(aircraftType);
//        baseAircraftTypes.getParameters().setCascadeDeletion(true);
//        baseAircraftTypes.featureProperty().set(baseEntity);
//        baseEntity.getColumns().add(baseAircraftTypes);
//
//
//        MariadbGenerator generator = new MariadbGenerator();
//        generator.generateSqlite(baseEntity);

        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}