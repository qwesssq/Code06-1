# Code06
my anroid homework
# 实验总结

1.遇到如下代码编译器无法识别tvCount,final TextView tvCount = findViewById(R.id.tvCount);经过排查后原因为activity_main.xml中TextView的android:id没有设置.

2.在登录界面 项目中遇到多资源问题,排查后删除原res/mipmap-XXXXX中的初始资源即可正常启动app.

3.新建项目时总会自动下载gradle,费时费力,解决方案:在setting找到gradle设置
gradle projects
        |-gradle
            |-use Gradle from:specified location
选取下好的gradle版本即可.

4.Code06 运⾏仅显⽰新闻标题及作者 时样式为发生改变,排查后发现原代码未注释.新代码使⽤ List<Map<String, String> > dataList; 替换掉之前定义的titles、authors 数组。将数据源的构造操作放⼊initData()⽅法中。 如果不注释一并运行,则会显示原样式.

5.实验指导书中使用Android Support库，本次实验使用最新版Android Studio则需要将部分工件或包名进行替换，例如： 在实验指导书中第七个Android项目中使用了Android Support库中的卡片控件 android.support.v7.widget.CardView，如果在AndroidX库下卡片控件对应的包名为androidx.cardview.widget.CardView，使用 Android Studio 3.2 及更高版本，只需从菜单栏中依次选择 Refactor > Migrate to AndroidX，即可将现有项目迁移到 AndroidX。
重构命令使用两个标记。默认情况下，这两个标记在 gradle.properties 文件中都设为 true：
android.useAndroidX=true
Android 插件会使用对应的 AndroidX 库而非支持库。
android.enableJetifier=true
Android 插件会通过重写现有第三方库的二进制文件，自动将这些库迁移为使用 AndroidX。
[guet Android guide](https://github.com/xgqin/guet_android_development)

https://github.com/xgqin/guet_android_development