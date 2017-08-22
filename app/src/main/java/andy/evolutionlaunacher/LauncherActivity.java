package andy.evolutionlaunacher;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

public class LauncherActivity extends AppCompatActivity {

    private final static int MSG_NON_UI_LOAD_APPS = 0;

	private final static int MSG_UI_LOAD_APPS_FINISH = 0;


	private RecyclerView appsRecyclerView;
	private HandlerThread workerThread;
	private Handler nonUIHandler;
	private LauncherHandler uiHandler;
	private AppAdapter mAppAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		appsRecyclerView = (RecyclerView) findViewById(R.id.appsRecyclerView);
		appsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
		mAppAdapter = new AppAdapter(this);
		appsRecyclerView.setAdapter(mAppAdapter);
		initWorker();
        nonUIHandler.sendEmptyMessage(MSG_NON_UI_LOAD_APPS);
	}


	@Override
	public void onBackPressed() {
		//super.onBackPressed();
	}

	@Override
	protected void onDestroy() {

		if(workerThread != null){
			workerThread.quit();
			uiHandler = null;
		}

		if(nonUIHandler != null){
			nonUIHandler.removeCallbacksAndMessages(null);
		}

		if(uiHandler != null){
			uiHandler.removeCallbacksAndMessages(null);
		}

		super.onDestroy();
	}

	private void initWorker(){
		workerThread = new HandlerThread("launcher_thread");
		workerThread.start();
		nonUIHandler = new Handler(workerThread.getLooper()){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what){
					case MSG_NON_UI_LOAD_APPS:
						PackageManager packageManager = LauncherActivity.this.getPackageManager();
						Intent intent = new Intent(Intent.ACTION_MAIN);
						intent.addCategory(Intent.CATEGORY_LAUNCHER);
						List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
						uiHandler.obtainMessage(MSG_UI_LOAD_APPS_FINISH, resolveInfoList).sendToTarget();
						break;
				}
			}
		};
		uiHandler = new LauncherHandler(this);
	}

	private static class LauncherHandler extends Handler{
		private WeakReference<LauncherActivity> weakReference;

		public LauncherHandler(LauncherActivity activity){
			weakReference = new WeakReference<LauncherActivity>(activity);
		}


		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if(weakReference == null){
				return;
			}
			LauncherActivity activity = weakReference.get();

			switch (msg.what){
				case MSG_UI_LOAD_APPS_FINISH :
					List<ResolveInfo> data = (List<ResolveInfo>) msg.obj;
					activity.mAppAdapter.setData(data);
					activity.mAppAdapter.notifyDataSetChanged();
					break;
			}

		}
	}


}
