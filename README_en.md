# MethodTraceMan

<p>
<a href="README.md">中文</a>&nbsp;&nbsp;&nbsp;
<a href="README_en.md">English</a>
</p>

A tool for discovering high-time-consuming methods for Android App.Used to quickly find high-time-consuming methods, locate and solve Android App jank problem. Through gradle plugin + ASM, instrumentation of all methods during compilation, and provide friendly interface display in browser, support time-consuming filtering, thread filtering, method name filtering, etc.


## Overview
<img src="methodtraceman.png" width:800 height:500/>


### The entire project consists of three parts：

1. Method time-consuming data collection part
2. Method time-consuming data display part
3. AndroidStduio auxiliary plugin

## QuickStart

### Step1 Dependencies and Config

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
        classpath "gradle.plugin.cn.cxzheng.methodTracePlugin:tracemanplugin:1.0.3"
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
  debugImplementation 'com.github.zhengcx:MethodTraceMan:1.0.7'
  releaseImplementation 'com.github.zhengcx:MethodTraceMan:1.0.5-noop'
}

apply plugin: "cn.cxzheng.asmtraceman"
traceMan {
    open = true
    traceConfigFile = "${project.projectDir}/traceconfig.txt"
}
```

#### Create a file called traceconfig.txt under the root directory of app module.
Configuration code instrumentation range，example:

```txt
#config the package need to trace,If they are empty, all files are traced by default.
-tracepackage cn/cxzheng/asmtraceman

#Setting up traceless packages under packages that require trace
-keeppackage cn/cxzheng/asmtraceman/test

#Setting up traceless classes under packages that require trace
-keepclass cn/cxzheng/asmtraceman/MainActivity

#Fixed configuration here: cn/cxzheng/tracemanui/TraceMan
-beatclass cn/cxzheng/tracemanui/TraceMan
```

#### then Rebuild project,all methods will be time-consuming trace.


### Step2 Install Android Stduio assist plugins.
The main function of this plugin is to quickly and easily open the UI display interface of methodtraceman.This plugin has been uploaded to Android Stduio plug-in repository. You can search for`MethodTraceMan`to install it. How to install Android Stduio plug-in is not detailed here, but you can search online.After installed plugin and restart AndroidStduio, you can see the yellow light bulb icon of the MethodTraceMan plugin in the top bar.


<img src="aspluginicon.png" width:400 height:250/>

### Step3 How to Use.
After completing the above two steps, make sure you have completed：

1. Rebuild&Run launches your app after integration as required.
2. MethodTraceMan plugin is installed on AndroidStduio.

Next, we'll show you how to use MethodTraceMan to easily troubleshoot time-consuming methods:

#### 1.Connect your phone to your computer via Usb to ensure a successful connection.

#### 2.After launching the app, click on the MethodTraceMan light bulb icon in the top bar of AndroidStduio, and the UI of MethodTraceMan will be opened in the browser as follows：

<img src="img/startui.png" width:600 height:350/>


#### 3.Click the "Start Method Time Statistics" button and start free to operate your app.
<img src="img/startwork.png" width:600 height:350/>

#### 4.After operating the app, click the “End method time-consuming statistics” button, and the time-consuming statistics of all methods will be output. You can perform time-consuming filtering, thread filtering, method name search, etc. for screening.
<img src="img/endwork.png" width:600 height:350/>

<img src="img/result.png" width:600 height:350/>


### End Welcome to Star/Fork/Issue.

## License

Apache2.0.


## About Me

- Github: [DavidSu](https://github.com/zhengcx)
- blog: [舒大飞](https://juejin.im/user/5a6d2293518825734a74ed4c/posts)
