package notepad;


public class EditorState {

    private boolean modified = false;

    public boolean isModified()       { return modified; }
    public void    markModified()     { modified = true; }
    public void    markClean()        { modified = false; }

    
    public String buildTitle(String filename, String appName) {
        return (modified ? "● " : "") + filename + " — " + appName;
    }
}
