package org.universal.tools.plugin;


import javafx.scene.layout.Pane;

public interface PluginView {
    /**
     * 启动该插件
     * @param data 初始化的数据
     * @return
     */
    Pane startPlugin(String data);

    /**
     * 获取需要保存或者同步的数据,由ViewFrame统一管控保存或者同步
     * @return json格式
     */
    String syncData();

    /**
     * 插件名称
     * @return
     */
    String name();
}
