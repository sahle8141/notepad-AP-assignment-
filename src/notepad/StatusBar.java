package notepad;

import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class StatusBar extends HBox {

    private final Label statusLabel;
    private final Label caretLabel;
    private final Label charLabel;
    private final Label wordLabel;

    private static final String STYLE_BAR =
        "-fx-background-color: #16213e;" +
        "-fx-border-color: #0f3460 transparent transparent transparent;" +
        "-fx-border-width: 1 0 0 0;";

    private static final String STYLE_TEXT =
        "-fx-text-fill: #6b7db3; -fx-font-size: 12px; -fx-font-family: Consolas;";

    private static final String STYLE_SEP =
        "-fx-text-fill: #2a3a6e; -fx-font-size: 12px;";

    public StatusBar() {
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(12);
        setPadding(new Insets(5, 16, 5, 16));
        setStyle(STYLE_BAR);

        statusLabel = label("Ready");
        caretLabel  = label("Ln 1, Col 1");
        charLabel   = label("0 chars");
        wordLabel   = label("0 words");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        getChildren().addAll(
            statusLabel, spacer,
            caretLabel, sep(), charLabel, sep(), wordLabel
        );
    }

    

    public void setStatus(String msg) {
        statusLabel.setText(msg);
        new Thread(() -> {
            try { Thread.sleep(4000); } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                if (statusLabel.getText().equals(msg)) statusLabel.setText("Ready");
            });
        }).start();
    }

    public void updateCaret(String text, int caretPos) {
        caretLabel.setText(TextStats.caretInfo(text, caretPos));
    }

    public void updateCounts(String text) {
        charLabel.setText(TextStats.charCount(text));
        wordLabel.setText(TextStats.wordCount(text));
    }

   

    private Label label(String text) {
        Label l = new Label(text);
        l.setStyle(STYLE_TEXT);
        return l;
    }

    private Label sep() {
        Label l = new Label("|");
        l.setStyle(STYLE_SEP);
        return l;
    }
}
