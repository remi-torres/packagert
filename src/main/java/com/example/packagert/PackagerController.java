package com.example.packagert;

import com.example.packagert.util.DialogUtil;
import com.example.packagert.util.FileUtil;
import com.example.packagert.util.StringUtil;
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

    private final String TEMPLATE_FILE = new File(Main.class.getResource("template").toURI()).getAbsolutePath();
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

    public PackagerController() throws URISyntaxException {
    }


    @FXML
    private void nextAction() {
        List<String> templateFileNames = templatesListView.getSelectionModel().getSelectedItems();
        if(parentPackageTextField.getText().isBlank()
                || projectTagTextField.getText().isBlank()
                || featureTagTextField.getText().isBlank())
            throw new RuntimeException("project or feature is empty");

        String copyParentPackage = parentPackageTextField.getText();

        for(String template : templateFileNames){
            final String[] templateData = template.split("-");
            final String copyPackageName = templateData[0].trim();
            final String copyFileName = templateData[1].trim();
            final File copyPackage = new File(copyParentPackage + File.separator + copyPackageName );
            if(! copyPackage.exists())
                if(!copyPackage.mkdirs())
                    throw new RuntimeException(String.format("Folder %s not created", copyPackageName));

            final String copyPath = copyParentPackage + File.separator + copyPackageName + File.separator + copyFileName;
            if(!new File(copyPath).exists()) {

                final Map<String, String> tags = new HashMap<>();
                tags.put("feature", featureTagTextField.getText());
                tags.put("project",projectTagTextField.getText());

                final String originalFileName = TEMPLATE_FILE + File.separator + template;
                final String content = FileUtil.getFileContent(originalFileName);
                final String newContent = replaceTags(content, tags);

                final String newCopyPath = replaceTags(copyPath, tags);
                FileUtil.writeInFile(newCopyPath, newContent);
            }
        }

        if(DialogUtil.isOk(DialogUtil.showInformation(rootPane.getScene(), "Files created", "The files are now created")))
            ((Stage) rootPane.getScene().getWindow()).close();

    }

    private String replaceTags(final String content, final Map<String, String> tags) {
        String newContent = content;
        for(String tag : tags.keySet()){
            while(newContent.contains("$" + tag+ "$"))
                newContent = newContent.replace("$" + tag+ "$", tags.get(tag));
            while(newContent.contains("$" + StringUtil.capitalize(tag) + "$" ))
                newContent = newContent.replace("$" + StringUtil.capitalize(tag) + "$", StringUtil.capitalize(tags.get(tag)));
            while(newContent.contains("$" + tag.toUpperCase() + "$" ))
                newContent = newContent.replaceAll("$" + tag.toUpperCase() + "$", tags.get(tag).toUpperCase());
        }
        return newContent;
    }

    @FXML
    private void initialize(){

        files.set(FXCollections.observableArrayList(FileUtil.getFilesInRepository(new File(TEMPLATE_FILE))));
        templatesListView.setItems(files);
        templatesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


}
