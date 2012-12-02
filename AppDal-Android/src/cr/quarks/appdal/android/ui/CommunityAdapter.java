package cr.quarks.appdal.android.ui;

import java.util.List;

import cr.quarks.appdal.android.AppDalApp;
import cr.quarks.appdal.android.R;
import cr.quarks.appdal.android.entity.Community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommunityAdapter extends BaseAdapter {

	private List<Community> communities;
	
	private long communityId;
	
	private OnCommunityLinkClickListener onCommunityLinkClickListener;
	
	public CommunityAdapter() {
		communityId = AppDalApp.getInstance().getUserCommunityId();
	}

	@Override
	public int getCount() {
		return communities.size();
	}

	@Override
	public Object getItem(int position) {
		return communities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return communities.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		CommunityViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) parent.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Community community = communities.get(position);
		if (convertView == null) {
			convertView = inflater
					.inflate(R.layout.community_item_layout, null);

			holder = new CommunityViewHolder();
			holder.position = (TextView) convertView
					.findViewById(R.id.community_item_position);
			holder.name = (TextView) convertView
					.findViewById(R.id.community_item_name);
			holder.location = (TextView) convertView
					.findViewById(R.id.community_item_location);
			holder.link = (ImageView) convertView
					.findViewById(R.id.community_item_link);

			convertView.setTag(holder);
		} else {
			holder = (CommunityViewHolder) convertView.getTag();
		}

		if (community != null) {
			holder.position.setText(String.valueOf(position + 1));
			holder.name.setText(community.getName());
			holder.location.setText(community.getLocation());
			holder.link.setOnClickListener(linkClickListener);
			holder.link.setTag(position);
			community.setPosition(position + 1);
			
			if(communityId == -1){
				holder.link.setImageDrawable(AppDalApp.getInstance().getResources().getDrawable(R.drawable.add_ico));
			}
		}

		return convertView;
	}

	public void setItems(List<Community> items) {
		this.communities = items;
		this.notifyDataSetChanged();
	}
	
	public OnCommunityLinkClickListener getOnCommunityLinkClickListener() {
		return onCommunityLinkClickListener;
	}

	public void setOnCommunityLinkClickListener(
			OnCommunityLinkClickListener onCommunityLinkClickListener) {
		this.onCommunityLinkClickListener = onCommunityLinkClickListener;
	}



	private class CommunityViewHolder {
		TextView position;
		TextView name;
		TextView location;
		ImageView link;
	}
	
	public interface OnCommunityLinkClickListener{
		public void onClick(View v, Community community);
	}

	private OnClickListener linkClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int position = (Integer) v.getTag();
			Community community = communities.get(position);
			community.setPosition(position + 1);
			if(onCommunityLinkClickListener != null){
				onCommunityLinkClickListener.onClick(v, community);
			}
		}
	};
}
