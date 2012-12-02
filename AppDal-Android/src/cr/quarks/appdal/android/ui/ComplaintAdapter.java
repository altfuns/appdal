package cr.quarks.appdal.android.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cr.quarks.appdal.android.R;
import cr.quarks.appdal.android.entity.Action;
import cr.quarks.appdal.android.entity.ActionType;
import cr.quarks.appdal.android.entity.Community;

public class ComplaintAdapter extends BaseAdapter {

	private List<Action> items;

	private OnCommunityLinkClickListener onCommunityLinkClickListener;

	private ActionType actionType;

	public ComplaintAdapter() {
		items = new ArrayList<Action>();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		CommunityViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) parent.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Action item = items.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.community_complaint_item,
					null);

			holder = new CommunityViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.community_complaint_name);
			holder.image = (ImageView) convertView
					.findViewById(R.id.community_complaint_image);

			convertView.setTag(holder);
		} else {
			holder = (CommunityViewHolder) convertView.getTag();
		}

		if (item != null) {
			holder.name.setText(item.getName());
			
			
			holder.image.setImageBitmap(item.getBitmap());
		}

		return convertView;
	}

	public void setItems(List<Action> items) {
		this.items = items;
		this.notifyDataSetChanged();
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public OnCommunityLinkClickListener getOnCommunityLinkClickListener() {
		return onCommunityLinkClickListener;
	}

	public void setOnCommunityLinkClickListener(
			OnCommunityLinkClickListener onCommunityLinkClickListener) {
		this.onCommunityLinkClickListener = onCommunityLinkClickListener;
	}

	private class CommunityViewHolder {
		TextView name;
		TextView availableText;
		ImageView availableImage;
		ImageView image;
	}

	public interface OnCommunityLinkClickListener {
		public void onClick(View v, Community community);
	}

	private OnClickListener linkClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int position = (Integer) v.getTag();
			// Community community = items.get(position);
			// community.setPosition(position + 1);
			// if (onCommunityLinkClickListener != null) {
			// onCommunityLinkClickListener.onClick(v, community);
			// }
		}
	};

}
