package notepad;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class NotepadApp extends Application implements MenuBuilder.Actions {

    private static final String APP_NAME = "Notepad";

    private Stage       stage;
    private EditorPane  editor;
    private StatusBar   statusBar;
    private FileManager fileManager;
    private EditorState editorState;

    
    @Override
    public void start(Stage primaryStage) {
        this.stage       = primaryStage;
        this.fileManager = new FileManager();
        this.editorState = new EditorState();

        editor    = new EditorPane();
        statusBar = new StatusBar();

        BorderPane root = new BorderPane();
        root.setTop(MenuBuilder.build(this));
        root.setCenter(editor);
        root.setBottom(statusBar);

       
        editor.onTextChanged(() -> {
            if (!editorState.isModified()) {
                editorState.markModified();
                refreshTitle();
            }
            statusBar.updateCounts(editor.getText());
        });
        editor.onCaretMoved(() ->
            statusBar.updateCaret(editor.getText(), editor.getCaretPosition())
        );

        Scene scene = new Scene(root, 900, 650);
        scene.getStylesheets().add(StyleSheet.asDataUri());

        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> { e.consume(); onExit(); });
        stage.show();
        editor.requestFocus();
    }

  

    @Override public void onNew()    { doNew();    }
    @Override public void onOpen()   { doOpen();   }
    @Override public void onSave()   { doSave();   }
    @Override public void onSaveAs() { doSaveAs(); }
    @Override public void onExit()   { doExit();   }

    @Override public void onUndo()      { editor.undo();      }
    @Override public void onRedo()      { editor.redo();      }
    @Override public void onCut()       { editor.cut();       }
    @Override public void onCopy()      { editor.copy();      }
    @Override public void onPaste()     { editor.paste();     }
    @Override public void onSelectAll() { editor.selectAll(); }

    @Override public void onInsertTimestamp() {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        editor.insertText(editor.getCaretPosition(), ts);
    }

    @Override public void onToggleWordWrap(boolean enabled) { editor.setWrapText(enabled); }
    @Override public void onFontSize(int size)              { editor.setFontSize(size);    }
    @Override public void onAbout()                         { Dialogs.showAbout();         }

  

    private void doNew() {
        if (editorState.isModified() && !Dialogs.confirmDiscard()) return;
        editor.clear();
        fileManager.setCurrentFile(null);
        editorState.markClean();
        refreshTitle();
        statusBar.setStatus("New file");
    }

    private void doOpen() {
        if (editorState.isModified() && !Dialogs.confirmDiscard()) return;
        File dir = fileManager.hasCurrentFile()
            ? fileManager.getCurrentFile().getParentFile() : null;
        File file = Dialogs.fileChooser("Open File", dir).showOpenDialog(stage);
        if (file == null) return;
        try {
            editor.setText(fileManager.readFile(file));
            fileManager.setCurrentFile(file);
            editorState.markClean();
            refreshTitle();
            statusBar.setStatus("Opened: " + file.getName());
        } catch (IOException ex) {
            Dialogs.showError("Could not open file:\n" + ex.getMessage());
        }
    }

    private void doSave() {
        if (!fileManager.hasCurrentFile()) { doSaveAs(); return; }
        writeCurrentFile(fileManager.getCurrentFile());
    }

    private void doSaveAs() {
        File dir = fileManager.hasCurrentFile()
            ? fileManager.getCurrentFile().getParentFile() : null;
        File file = Dialogs.fileChooser("Save File", dir).showSaveDialog(stage);
        if (file == null) return;
        writeCurrentFile(fileManager.ensureExtension(file));
    }

    private void writeCurrentFile(File file) {
        try {
            fileManager.writeFile(file, editor.getText());
            editorState.markClean();
            refreshTitle();
            statusBar.setStatus("Saved: " + file.getName());
        } catch (IOException ex) {
            Dialogs.showError("Could not save file:\n" + ex.getMessage());
        }
    }

    private void doExit() {
        if (editorState.isModified() && !Dialogs.confirmDiscard()) return;
        Platform.exit();
    }

    

    private void refreshTitle() {
        stage.setTitle(editorState.buildTitle(fileManager.getCurrentFileName(), APP_NAME));
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}
