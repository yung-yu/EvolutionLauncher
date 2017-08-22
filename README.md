# 1.Launcher Activity 設定系統背景 Style
```
    <style name="AppTheme.Wallpaper" parent="@style/Theme.AppCompat">
        //設定透明背景
        <item name="android:windowBackground">@android:color/transparent</item>
        //顯示系統背景
        <item name="android:windowShowWallpaper">true</item>
        //設定no title bar
        <item name="windowNoTitle">true</item>
        <item name="windowActionBar">false</item>
        //設定status bar 透明
        <item name="android:windowTranslucentStatus">true</item>
        //設定below status bar
        <item name="android:fitsSystemWindows">true</item>
    </style>
```

#2.載入裝置中所有安裝的APP
```
    PackageManager packageManager = LauncherActivity.this.getPackageManager();
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
```
