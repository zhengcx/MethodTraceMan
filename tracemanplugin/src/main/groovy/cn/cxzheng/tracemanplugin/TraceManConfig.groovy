package cn.cxzheng.tracemanplugin

/**
 * 为TraceMan自定义的配置项extension
 */
class TraceManConfig {
    String output
    boolean open
    String traceConfigFile

    TraceManConfig() {
        open = true
        output = ""
    }
}