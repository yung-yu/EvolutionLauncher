package andy.evolutionlaunacher;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import andy.evolutionlaunacher.log.AppViewHolder;

/**
 * Created by andyli on 2017/8/21.
 */

public class AppAdapter extends RecyclerView.Adapter<AppViewHolder> {
	private Context context;
	private List<ResolveInfo> data;

	public List<ResolveInfo> getData() {
		return data;
	}

	public void setData(List<ResolveInfo> data) {
		this.data = data;
	}

	public AppAdapter(Context context){
		this.context = context;
	}


	@Override
	public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new AppViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_app_item, null));
	}

	@Override
	public void onBindViewHolder(AppViewHolder holder, int position) {
		ResolveInfo info = getItem(position);
		if(info != null){
			holder.iconImage.setImageDrawable(info.loadIcon(context.getPackageManager()));
			holder.appNameText.setText(info.loadLabel(context.getPackageManager()));
		}

	}

	@Override
	public int getItemCount() {
		return data != null ? data.size():0;
	}

	private ResolveInfo getItem(int position){
		return data != null && position < data.size() ? data.get(position): null;
	}
}
