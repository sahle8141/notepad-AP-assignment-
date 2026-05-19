package notepad;


public class StyleSheet {

    private static final String CSS =
        ".root { -fx-background-color: #1a1a2e; }" +

       
        ".menu-bar { -fx-background-color: #16213e; }" +
        ".menu .label { -fx-text-fill: #c0c8e8; }" +
        ".menu:hover .label, .menu:focused .label, .menu:showing .label { -fx-text-fill: #ffffff; }" +
        ".menu:hover, .menu:focused, .menu:showing { -fx-background-color: #0f3460; }" +

        
        ".context-menu { -fx-background-color: #16213e; -fx-border-color: #0f3460; -fx-border-width: 1; }" +
        ".menu-item { -fx-background-color: #16213e; }" +
        ".menu-item:focused { -fx-background-color: #0f3460; }" +
        ".menu-item .label { -fx-text-fill: #dce3f5; }" +
        ".menu-item:focused .label { -fx-text-fill: #ffffff; }" +
        ".radio-menu-item .label { -fx-text-fill: #dce3f5; }" +
        ".check-menu-item .label { -fx-text-fill: #dce3f5; }" +
        ".radio-menu-item:focused, .check-menu-item:focused { -fx-background-color: #0f3460; }" +
        ".separator .line { -fx-border-color: #0f3460; }" +

      
        ".text-area { -fx-background-color: #1a1a2e; -fx-border-color: transparent; }" +
        ".text-area .content { -fx-background-color: #1a1a2e; }" +
        ".text-area .scroll-pane { -fx-background-color: #1a1a2e; }" +
        ".text-area .scroll-pane .viewport { -fx-background-color: #1a1a2e; }" +

      
        ".scroll-bar:vertical   { -fx-background-color: #16213e; -fx-pref-width: 8; }" +
        ".scroll-bar:horizontal { -fx-background-color: #16213e; -fx-pref-height: 8; }" +
        ".scroll-bar .thumb { -fx-background-color: #2a4a8a; -fx-background-radius: 4; }" +
        ".scroll-bar .thumb:hover { -fx-background-color: #3a6aaa; }" +
        ".scroll-bar .increment-button, .scroll-bar .decrement-button " +
            "{ -fx-background-color: transparent; -fx-pref-width: 0; -fx-pref-height: 0; }" +
        ".scroll-pane { -fx-background-color: #1a1a2e; }" +
        ".scroll-pane .viewport { -fx-background-color: #1a1a2e; }" +

        
        ".dialog-pane { -fx-background-color: #16213e; -fx-border-color: #0f3460; -fx-border-width: 1; }" +
        ".dialog-pane .content.label { -fx-text-fill: #dce3f5; }" +
        ".dialog-pane .header-panel { -fx-background-color: #0f3460; }" +
        ".dialog-pane .header-panel .label { -fx-text-fill: #ffffff; -fx-font-weight: bold; }" +
        ".button { -fx-background-color: #0f3460; -fx-text-fill: #dce3f5; -fx-border-color: #1a5a9a; -fx-border-width: 1; }" +
        ".button:hover { -fx-background-color: #1a5a9a; }";

    
    public static String asDataUri() {
       
        String encoded = CSS
            .replace("%",  "%25")
            .replace(" ",  "%20")
            .replace("#",  "%23")
            .replace(":",  "%3A")
            .replace(";",  "%3B")
            .replace("{",  "%7B")
            .replace("}",  "%7D")
            .replace(".",  "%2E")
            .replace("(",  "%28")
            .replace(")",  "%29")
            .replace("'",  "%27")
            .replace(",",  "%2C")
            .replace(">",  "%3E")
            .replace("+",  "%2B");
        return "data:text/css," + encoded;
    }
}
