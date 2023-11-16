package com.example.packagert.util;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogUtil {

    public static Optional<ButtonType> showInformation(final Scene scene, final String title, final String message) {
        return showDialog(scene, title, message, Alert.AlertType.INFORMATION);
    }

    private static Optional<ButtonType> showDialog(final Scene scene, final String title, final String message, final Alert.AlertType type ) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(scene.getWindow());

        return alert.showAndWait();
    }

    public static boolean isOk(final Optional<ButtonType> buttonType) {
        return buttonType.isPresent() && ButtonType.OK.equals(buttonType.get());
    }
}
