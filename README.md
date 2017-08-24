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

#3.判斷是否為系統ＡＰＰ
```
	public static boolean isSystemApp(ResolveInfo info){
		return ((info.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
	}
```

#4.開啟ＡＰＰ
```
		ActivityInfo activity=info.activityInfo;
		ComponentName name = new ComponentName(activity.applicationInfo.packageName,
				activity.name);
		Intent it=new Intent(Intent.ACTION_MAIN);

		it.addCategory(Intent.CATEGORY_LAUNCHER);
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
				Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		it.setComponent(name);

		context.startActivity(it);
```

#5.解除安裝ＡＰＰ
```
	Uri packageUri = Uri.parse("package:"+info.activityInfo.packageName);
	Intent uninstallIntent =
			new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
	context.startActivity(uninstallIntent);
```