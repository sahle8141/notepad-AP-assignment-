package notepad;

import javafx.scene.control.*;
import javafx.scene.input.*;


public class MenuBuilder {

   
    public interface Actions {
        void onNew();
        void onOpen();
        void onSave();
        void onSaveAs();
        void onExit();
        void onUndo();
        void onRedo();
        void onCut();
        void onCopy();
        void onPaste();
        void onSelectAll();
        void onInsertTimestamp();
        void onToggleWordWrap(boolean enabled);
        void onFontSize(int size);
        void onAbout();
    }

    public static MenuBar build(Actions a) {
        MenuBar bar = new MenuBar();
        bar.getMenus().addAll(
            buildFileMenu(a),
            buildEditMenu(a),
            buildFormatMenu(a),
            buildHelpMenu(a)
        );
        return bar;
    }

  

    private static Menu buildFileMenu(Actions a) {
        MenuItem newItem    = item("New",        KeyCode.N,  KeyCombination.CONTROL_DOWN);
        MenuItem openItem   = item("Open…",      KeyCode.O,  KeyCombination.CONTROL_DOWN);
        MenuItem saveItem   = item("Save",        KeyCode.S,  KeyCombination.CONTROL_DOWN);
        MenuItem saveAsItem = item("Save As…",   KeyCode.S,  KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
        MenuItem exitItem   = new MenuItem("Exit");

        newItem   .setOnAction(e -> a.onNew());
        openItem  .setOnAction(e -> a.onOpen());
        saveItem  .setOnAction(e -> a.onSave());
        saveAsItem.setOnAction(e -> a.onSaveAs());
        exitItem  .setOnAction(e -> a.onExit());

        Menu m = new Menu("File");
        m.getItems().addAll(newItem, openItem, saveItem, saveAsItem, new SeparatorMenuItem(), exitItem);
        return m;
    }

    

    private static Menu buildEditMenu(Actions a) {
        MenuItem undoItem      = item("Undo",             KeyCode.Z, KeyCombination.CONTROL_DOWN);
        MenuItem redoItem      = item("Redo",             KeyCode.Y, KeyCombination.CONTROL_DOWN);
        MenuItem cutItem       = item("Cut",              KeyCode.X, KeyCombination.CONTROL_DOWN);
        MenuItem copyItem      = item("Copy",             KeyCode.C, KeyCombination.CONTROL_DOWN);
        MenuItem pasteItem     = item("Paste",            KeyCode.V, KeyCombination.CONTROL_DOWN);
        MenuItem selectAllItem = item("Select All",       KeyCode.A, KeyCombination.CONTROL_DOWN);
        MenuItem tsItem        = item("Insert Timestamp", KeyCode.F5);

        undoItem     .setOnAction(e -> a.onUndo());
        redoItem     .setOnAction(e -> a.onRedo());
        cutItem      .setOnAction(e -> a.onCut());
        copyItem     .setOnAction(e -> a.onCopy());
        pasteItem    .setOnAction(e -> a.onPaste());
        selectAllItem.setOnAction(e -> a.onSelectAll());
        tsItem       .setOnAction(e -> a.onInsertTimestamp());

        Menu m = new Menu("Edit");
        m.getItems().addAll(
            undoItem, redoItem,
            new SeparatorMenuItem(),
            cutItem, copyItem, pasteItem, selectAllItem,
            new SeparatorMenuItem(),
            tsItem
        );
        return m;
    }

    

    private static Menu buildFormatMenu(Actions a) {
        CheckMenuItem wrapItem = new CheckMenuItem("Word Wrap");
        wrapItem.setSelected(true);
        wrapItem.setOnAction(e -> a.onToggleWordWrap(wrapItem.isSelected()));

        Menu fontMenu   = new Menu("Font Size");
        ToggleGroup tg  = new ToggleGroup();
        int[] sizes     = {11, 13, 15, 18, 22, 28, 36};
        for (int size : sizes) {
            RadioMenuItem ri = new RadioMenuItem(size + "px");
            ri.setToggleGroup(tg);
            if (size == 15) ri.setSelected(true);
            final int s = size;
            ri.setOnAction(e -> a.onFontSize(s));
            fontMenu.getItems().add(ri);
        }

        Menu m = new Menu("Format");
        m.getItems().addAll(wrapItem, fontMenu);
        return m;
    }

  

    private static Menu buildHelpMenu(Actions a) {
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> a.onAbout());
        Menu m = new Menu("Help");
        m.getItems().add(aboutItem);
        return m;
    }

    

    private static MenuItem item(String text, KeyCode key, KeyCombination.Modifier... mods) {
        MenuItem mi = new MenuItem(text);
        mi.setAccelerator(new KeyCodeCombination(key, mods));
        return mi;
    }
}
