package notepad;

import javafx.scene.control.*;


public class Dialogs {

  
    public static boolean confirmDiscard() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unsaved Changes");
        alert.setHeaderText("You have unsaved changes.");
        alert.setContentText("Do you want to discard them?");

        ButtonType discard = new ButtonType("Discard");
        ButtonType cancel  = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(discard, cancel);

        return alert.showAndWait().map(b -> b == discard).orElse(false);
    }

   
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    public static void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Notepad");
        alert.setHeaderText("JavaFX Notepad v2");
        alert.setContentText(
            "A clean, modular text editor.\n\n" +
            "Shortcuts\n" +
            "  Ctrl+N   New\n" +
            "  Ctrl+O   Open\n" +
            "  Ctrl+S   Save\n" +
            "  Ctrl+Shift+S   Save As\n" +
            "  F5       Insert Timestamp\n" +
            "  Ctrl+Z/Y Undo / Redo"
        );
        alert.showAndWait();
    }

  
    public static javafx.stage.FileChooser fileChooser(String title, java.io.File initialDir) {
        javafx.stage.FileChooser fc = new javafx.stage.FileChooser();
        fc.setTitle(title);
        fc.getExtensionFilters().addAll(
            new javafx.stage.FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new javafx.stage.FileChooser.ExtensionFilter("All Files",  "*.*")
        );
        if (initialDir != null && initialDir.isDirectory()) {
            fc.setInitialDirectory(initialDir);
        }
        return fc;
    }
}
