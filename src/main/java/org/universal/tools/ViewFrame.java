package org.universal.tools;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.universal.tools.plugin.PluginView;
import org.universal.tools.plugin.ReminderPluginView;
import org.universal.tools.plugin.TodoListPluginView;
import java.awt.*;

/**
 * 主窗口
 */
public class ViewFrame extends Application {

    private static Stage primaryStage;
    /**
     * 初始化插件
     */
    private static void initPlugins() {
        addPlugin(new TodoListPluginView());
        addPlugin(new ReminderPluginView());
    }

    private static void addPlugin(PluginView pluginView) {
        final String name = pluginView.name();
        //query data
        final Pane pane = pluginView.startPlugin("");
        pane.setBorder(new Border(new BorderStroke(Color.DARKBLUE, null, null, null)));
        final TitledPane titledPane = new TitledPane(name, pane);
        final VBox vBox = (VBox) primaryStage.getScene().getRoot();
        vBox.getChildren().add(titledPane);

    }

    /**
     * 初始化窗口大小，位置，属性等
     */
    private static void initWindow(Stage primaryStage) {

        ViewFrame.primaryStage = primaryStage;
        final VBox vBox = new VBox();
        Scene scene = new Scene(vBox,400,600);
        ViewFrame.primaryStage.setScene(scene);

        primaryStage.setTitle("universalTools");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.setX(screenSize.width - 400);
        primaryStage.setY(200);
        primaryStage.setAlwaysOnTop(true);
    }

    public static void launch(){
        ViewFrame.launch(new String[0]);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initWindow(primaryStage);
        initPlugins();

        ViewFrame.primaryStage.show();
    }
}
