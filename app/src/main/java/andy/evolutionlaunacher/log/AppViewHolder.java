package andy.evolutionlaunacher.log;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import andy.evolutionlaunacher.R;

/**
 * Created by andyli on 2017/8/21.
 */

public class AppViewHolder extends RecyclerView.ViewHolder {
	public ImageView iconImage;
	public TextView appNameText;

	public AppViewHolder(View itemView) {
		super(itemView);
		iconImage = itemView.findViewById(R.id.icon);
		appNameText = itemView.findViewById(R.id.appName);
	}
}
