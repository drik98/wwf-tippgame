package Main;

import com.sun.webkit.dom.HTMLBodyElementImpl;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author sht
 */
public class Auswertung extends Application {

    private String url = "https://www.facebook.com/groups/WorldWrestlingFanbase/permalink/1513868468717542/";
    private WebView browser;
    private WebEngine webEngine;

    @Override
    public void start(final Stage stage) {

        this.browser = new WebView();
        this.webEngine = browser.getEngine();
        Parameters params = getParameters();
        this.url = params.getUnnamed().get(0);

        webEngine.load(url);

        Button reload = new Button("Seite neuladen");
        Button mehrAnzeigen = new Button("Mehr Anzeigen");
        Button vorherigeKommentare = new Button("Alle Kommentare zeigen");
        Button antwortenLoeschen = new Button("Antworten Löschen");
        Button tippsAuswerten = new Button("Auswerten");

        reload.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                webEngine.executeScript("window.location.href=\"" + url + "\"");
            }
        });
        antwortenLoeschen.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                webEngine.executeScript("var antwortListeLoeschen = document.getElementsByClassName('UFIReplyList');"
                        + "	while(antwortListeLoeschen!=null && antwortListeLoeschen.length > 0) { "
                        + "		for (var i = 0; i < antwortListeLoeschen.length; i++) {"
                        + "    		antwortListeLoeschen[i].remove();"
                        + "		}"
                        + "		antwortListeLoeschen = document.getElementsByClassName('UFIReplyList');"
                        + "    }");
            }
        });

        mehrAnzeigen.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                webEngine.executeScript("var mehrAnzeigen = document.getElementsByClassName('_5v47 fss');"
                        + "	while(mehrAnzeigen!=null && mehrAnzeigen.length > 0) { "
                        + "		for (var i = 0; i < mehrAnzeigen.length; i++) {"
                        + "    		mehrAnzeigen[i].click();"
                        + "		}"
                        + "		 mehrAnzeigen = document.getElementsByClassName('_5v47 fss');"
                        + "    }");
            }
        });
        vorherigeKommentare.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                webEngine.executeScript("var vorherigeKommentareAnzeigen = document.getElementsByClassName('UFIPagerLink');"
                        + "	while(vorherigeKommentareAnzeigen!=null && vorherigeKommentareAnzeigen.length > 0) {"
                        + "		for (var i = 0; i < vorherigeKommentareAnzeigen.length; i++) {"
                        + "    		vorherigeKommentareAnzeigen[i].click();"
                        + "		}"
                        + "		vorherigeKommentareAnzeigen = document.getElementById('UFIPagerLink');"
                        + "    }");

            }
        });

        tippsAuswerten.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // Load a page from remote url.
                webEngine.load(url);
                HTMLBodyElementImpl p = (HTMLBodyElementImpl) webEngine.executeScript("document.body");
                System.out.println("HUUUUUUUUHN");
                System.out.println(p.getInnerHTML());
            }
        });
        VBox root = new VBox();
//        root.setPadding(5);
        root.setSpacing(5);
        
        HBox hbox = new HBox();
        hbox.getChildren().addAll(reload, mehrAnzeigen, vorherigeKommentare, antwortenLoeschen, tippsAuswerten);
        root.getChildren().addAll(hbox, browser);

        Scene scene = new Scene(root);

        stage.setTitle("Facebook WWF Tippspiel");
        stage.setScene(scene);
        stage.setWidth(1920);
        stage.setHeight(1080);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WebView getBrowser() {
        return browser;
    }

    public void setBrowser(WebView browser) {
        this.browser = browser;
    }

    public WebEngine getWebEngine() {
        return webEngine;
    }

    public void setWebEngine(WebEngine webEngine) {
        this.webEngine = webEngine;
    }

}
