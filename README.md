# notepad

A simple text editor built with Java and JavaFX.

---

## Features

- New, Open, Save, Save As
- Undo / Redo
- Word Wrap toggle and Font Size picker
- Live status bar — line, column, character and word count
- Insert timestamp with F5
- Warns you before closing or opening a file with unsaved changes

---

## Tech

- **Java 17+**
- **JavaFX 17+** for the UI
- No build tools — just `javac` and `java`

---

## How to Run

### Requirements

- JDK 17
- JavaFX sdk

### VS Code

1. Open the project folder in VS Code
2. Edit `.vscode/settings.json` and set your JavaFX path:
   ```json
   {
     "javafx.lib": "C:\\javafx-sdk-25.0.1\\lib"
   }
   ```
3. Press `Ctrl+Shift+B` and select **2 - Run Notepad**

### Command Line

```bat
javac --module-path "C:\javafx-sdk-25.0.1\lib" --add-modules javafx.controls -d out --source-path src src\notepad\NotepadApp.java
java  --module-path "C:\javafx-sdk-25.0.1\lib" --add-modules javafx.controls -cp out notepad.NotepadApp
```

---

## Keyboard Shortcuts

| Key            | Action             |
| -------------- | ------------------ |
| Ctrl+N         | New file           |
| Ctrl+O         | Open file          |
| Ctrl+S         | Save               |
| Ctrl+Shift+S   | Save As            |
| Ctrl+Z         | Undo               |
| Ctrl+Y         | Redo               |
| Ctrl+X / C / V | Cut / Copy / Paste |
| Ctrl+A         | Select All         |
| F5             | Insert timestamp   |
