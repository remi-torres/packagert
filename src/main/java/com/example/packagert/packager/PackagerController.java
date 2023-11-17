package com.example.packagert.packager;

import com.example.packagert.util.DialogUtil;
import com.example.packagert.util.FileUtil;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackagerController {

    private final ListProperty<String> files = new SimpleListProperty<>();

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField parentPackageTextField;
    @FXML
    private ListView<String> templatesListView;
    @FXML
    private TextField projectTagTextField;
    @FXML
    private TextField featureTagTextField;


    @FXML
    private void nextAction() {
        List<String> templateFileNames = templatesListView.getSelectionModel().getSelectedItems();
        if(parentPackageTextField.getText().isBlank()
                || projectTagTextField.getText().isBlank()
                || featureTagTextField.getText().isBlank())
            throw new RuntimeException("project or feature is empty");

        String copyParentPackage = parentPackageTextField.getText();

        final Map<String, String> tags = new HashMap<>();
        tags.put("feature", featureTagTextField.getText());
        tags.put("project",projectTagTextField.getText());

        PackagerService packageService = new PackagerService(copyParentPackage, tags);
        packageService.createPackage(templateFileNames);

        if(DialogUtil.isOk(DialogUtil.showInformation(rootPane.getScene(), "Files created", "The files are now created")))
            ((Stage) rootPane.getScene().getWindow()).close();

    }

    @FXML
    private void initialize() throws URISyntaxException {
        final String TEMPLATE_FILE = new File(getClass().getResource("/com/example/packagert/template").toURI()).getAbsolutePath();
        files.set(FXCollections.observableArrayList(FileUtil.getFilesInRepository(new File(TEMPLATE_FILE))));
        templatesListView.setItems(files);
        templatesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


}
