package andy.evolutionlaunacher.vh;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import andy.evolutionlaunacher.R;
import andy.evolutionlaunacher.utils.AppUtils;

/**
 * Created by andyli on 2017/8/21.
 */

public class AppViewHolder extends RecyclerView.ViewHolder {
	public ImageView iconImage;
	public TextView appNameText;
	private ResolveInfo info;

	public AppViewHolder(View itemView) {
		super(itemView);
		iconImage = itemView.findViewById(R.id.icon);
		appNameText = itemView.findViewById(R.id.appName);
	}

	public void update(final Context context, final ResolveInfo info){
		this.info = info;
		iconImage.setImageDrawable(info.loadIcon(context.getPackageManager()));
		appNameText.setText(info.loadLabel(context.getPackageManager()));
		iconImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AppUtils.launchApp(context, info);
			}
		});
		iconImage.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				if(!AppUtils.isSystemApp(info)) {
					AppUtils.unInstallApp(context, info);
				} else {
					Toast.makeText(context, "system app can't uninstall", Toast.LENGTH_SHORT).show();
				}
				return true;
			}
		});
	}
}
