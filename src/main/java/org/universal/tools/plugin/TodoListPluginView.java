package org.universal.tools.plugin;

import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import lombok.AllArgsConstructor;

/**
 * TODO待办事项插件
 */
public class TodoListPluginView implements PluginView{

    @AllArgsConstructor
    public class TableVO{
        public SimpleBooleanProperty status;
        public SimpleStringProperty item;

        public TableVO(String status,String item) {
            this.status = new SimpleBooleanProperty(Boolean.parseBoolean(status));
            this.item = new SimpleStringProperty(item);
        }

        public boolean isStatus() {
            return status.get();
        }

        public SimpleBooleanProperty statusProperty() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status.set(status);
        }

        public String getItem() {
            return item.get();
        }

        public SimpleStringProperty itemProperty() {
            return item;
        }

        public void setItem(String item) {
            this.item.set(item);
        }
    }

    private Pane initPanel() {
        TableView<TableVO> tableView = new TableView<>();

        javafx.scene.control.TableColumn<TableVO, Boolean> status = new javafx.scene.control.TableColumn<>("status");
        //双向绑定数据实体和UI展示
        status.setCellValueFactory(param -> param.getValue().statusProperty());
        status.setCellFactory(CheckBoxTableCell.forTableColumn(status));
        status.setMaxWidth(80);
        status.setMinWidth(80);

        javafx.scene.control.TableColumn<TableVO, String> item = new TableColumn<>("item");
        item.setCellValueFactory(param -> param.getValue().itemProperty());
        item.setCellFactory(new Callback<TableColumn<TableVO, String>, TableCell<TableVO, String>>() {
            @Override
            public TableCell<TableVO, String> call(TableColumn<TableVO, String> param) {
                Label label = new Label();
                return label;
            }
        });

        final TableVO tableVO = new TableVO("true", "haha");
        final TableVO tableVO2 = new TableVO("false", "haha2");

        //返回需要观测监听的属性
        ObservableList<TableVO> datProperties = FXCollections.observableArrayList(param -> new Observable[]{param.statusProperty(),param.itemProperty()});

        datProperties.add(tableVO);
        datProperties.add(tableVO2);

        tableView.getColumns().add(status);
        tableView.getColumns().add(item);

        tableView.setItems(datProperties);

        tableView.setEditable(true);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        BorderPane pane = new BorderPane();
        pane.setCenter(tableView);
        return pane;
    }

    /**
     * 启动插件
     * @param data 初始化的数据
     * @return
     */
    @Override
    public Pane startPlugin(String data) {
        return initPanel();
    }

    /**
     * 返回需要同步或者保存的数据
     * @return
     */
    @Override
    public String syncData() {
        return null;
    }

    /**
     * 插件名称
     * @return
     */
    @Override
    public String name() {
        return "TODO";
    }
}
