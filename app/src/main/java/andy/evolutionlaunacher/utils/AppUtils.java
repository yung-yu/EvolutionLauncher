package andy.evolutionlaunacher.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;

/**
 * Created by andyli on 2017/8/23.
 */

public class AppUtils {

	public static void launchApp(Context context, ResolveInfo info){
		ActivityInfo activity=info.activityInfo;
		ComponentName name=new ComponentName(activity.applicationInfo.packageName,
				activity.name);
		Intent it=new Intent(Intent.ACTION_MAIN);

		it.addCategory(Intent.CATEGORY_LAUNCHER);
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
				Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		it.setComponent(name);

		context.startActivity(it);
	}


	public static void unInstallApp(Context context, ResolveInfo info){

		Uri packageUri = Uri.parse("package:"+info.activityInfo.packageName);
		Intent uninstallIntent =
				new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
		context.startActivity(uninstallIntent);
	}

	public static boolean isSystemApp(ResolveInfo info){
		return ((info.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
	}
}
