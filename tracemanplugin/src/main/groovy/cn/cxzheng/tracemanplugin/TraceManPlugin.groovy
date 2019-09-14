package cn.cxzheng.tracemanplugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.AppExtension

class TraceManPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        println '*****************MethodTraceMan Plugin apply*********************'
        project.extensions.create("traceMan", TraceManConfig)

        def android = project.extensions.getByType(AppExtension)
        android.registerTransform(new TraceManTransform(project))
    }
}