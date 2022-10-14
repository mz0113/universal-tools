package org.universal.tools.plugin;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jdesktop.swingx.plaf.DatePickerAddon;

/**
 * TODO待办事项插件
 */
public class ReminderPluginView implements PluginView{


    DatePickerAddon addon = new DatePickerAddon();

    @Data
    @AllArgsConstructor
    public class TableVO{
        public String time;
        public String item;
    }

    private class DateCell extends TableCell{
        DatePicker datePicker = new DatePicker();
        @Override
        protected void updateItem(Object item, boolean empty) {
            setGraphic(empty ? null : datePicker);
        }
    }

    private Pane initPanel() {
        TableView<TableVO> tableView = new TableView<>();

        javafx.scene.control.TableColumn<TableVO, String> time = new javafx.scene.control.TableColumn<>("time");
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        time.setCellFactory(param -> new DateCell());


        javafx.scene.control.TableColumn<TableVO, String> item = new TableColumn<>("item");
        item.setCellValueFactory(new PropertyValueFactory<>("item"));

        tableView.getColumns().add(time);
        tableView.getColumns().add(item);

        tableView.getItems().add(new TableVO("true", "haha"));
        tableView.getItems().add(new TableVO("true","haha2"));
        tableView.getItems().add(new TableVO("false","haha3"));
        tableView.getItems().add(new TableVO("true","haha4"));
        tableView.getItems().add(new TableVO("false","haha5"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.setEditable(true);


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
        return "Reminder";
    }
}
