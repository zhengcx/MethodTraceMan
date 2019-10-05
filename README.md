# MethodTraceMan
用于快速找到高耗时方法，定位解决Android App卡顿问题。通过gradle plugin+ASM实现可配置范围的方法插桩来统计所有方法的耗时，并在浏览器提供友好的界面展示，支持耗时筛选、线程筛选、方法名筛选等。(A Tool for Discovering High Time-consuming Methods for Android App)


## Overview
<img src="methodtraceman.png" width:800 height:500/>

### 整个项目包括三部分：

1. 方法耗时数据收集部分：通过gradle plugin+ASM在编译时期对所有方法进行插桩收集方法耗时数据，并进行处理
2. 方法耗时数据展示部分：在浏览器上展示方法耗时数据，并支持耗时筛选、线程筛选、方法名搜索等功能
3. AndroidStduio插件：用于方便在AndroidStduio顶部栏上快速打开方法耗时数据展示部分【即上面说的第二部分】

## QuickStart

### step1 Dependencies

#### root project `build.gradle`

```groovy
buildscript {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
        maven { url "https://plugins.gradle.org/m2/" }
    }
    dependencies {
        classpath "gradle.plugin.cn.cxzheng.methodTracePlugin:tracemanplugin:1.0.1"
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
        maven { url "https://plugins.gradle.org/m2/" }
    }
}
```

#### app module project `build.gradle`

```groovy
dependencies {
  implementation 'com.github.zhengcx:MethodTraceMan:1.0.4'
}

apply plugin: "cn.cxzheng.asmtraceman"
traceMan {
    open = true
    traceConfigFile = "${project.projectDir}/traceconfig.txt"
}
```
#### Create a file called traceconfig.txt under the root directory of app module.(创建一个名叫'traceconfig.txt'的配置文件在app module的根目录下，并在里面对插桩范围进行配置)
下面是配置示例：
```txt
#配置需插桩的包,如果为空，则默认所有文件都进行插桩(config the package need to trace,If they are empty, all files are traced by default.)
-tracepackage cn/cxzheng/asmtraceman

#在需插桩的包下设置无需插桩的包(Setting up traceless packages under packages that require trace)
-keeppackage cn/cxzheng/asmtraceman/test

#在需插桩的包下设置无需插桩的类(Setting up traceless classes under packages that require trace)
#-keepclass cn/cxzheng/asmtraceman/MainActivity

#插桩代码所在类,这里固定配置为：cn/cxzheng/tracemanui/TraceMan(Fixed configuration here: cn/cxzheng/tracemanui/TraceMan)
-beatclass cn/cxzheng/tracemanui/TraceMan
```

