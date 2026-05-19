package notepad;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class EditorPane extends ScrollPane {

    private final TextArea textArea;

    public EditorPane() {
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setFont(Font.font("Consolas", 15));
        textArea.setPromptText("Start typing…");
        textArea.setStyle(
            "-fx-control-inner-background: #1a1a2e;" +
            "-fx-text-fill: #dce3f5;" +
            "-fx-background-color: #1a1a2e;" +
            "-fx-border-color: transparent;" +
            "-fx-highlight-fill: #0f3460;" +
            "-fx-highlight-text-fill: #e2e8f5;" +
            "-fx-prompt-text-fill: #4a5270;" +
            "-fx-padding: 16 20 16 20;"
        );

        setContent(textArea);
        setFitToWidth(true);
        setFitToHeight(true);
        setStyle("-fx-background-color: #1a1a2e; -fx-border-color: transparent;");
        BorderPane.setMargin(this, new Insets(0));
    }

  

    public String  getText()               { return textArea.getText();          }
    public void    setText(String t)       { textArea.setText(t);                }
    public void    clear()                 { textArea.clear();                   }
    public int     getCaretPosition()      { return textArea.getCaretPosition(); }
    public void    insertText(int i, String t) { textArea.insertText(i, t);      }
    public void    undo()                  { textArea.undo();                    }
    public void    redo()                  { textArea.redo();                    }
    public void    cut()                   { textArea.cut();                     }
    public void    copy()                  { textArea.copy();                    }
    public void    paste()                 { textArea.paste();                   }
    public void    selectAll()             { textArea.selectAll();               }
    public void    requestFocus()          { textArea.requestFocus();            }
    public void    setWrapText(boolean w)  { textArea.setWrapText(w);            }
    public void    setFontSize(int size)   { textArea.setFont(Font.font("Consolas", size)); }

   
    public void onTextChanged(Runnable r) {
        textArea.textProperty().addListener((obs, o, n) -> r.run());
    }

  
    public void onCaretMoved(Runnable r) {
        textArea.caretPositionProperty().addListener((obs, o, n) -> r.run());
    }
}
