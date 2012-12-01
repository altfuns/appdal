package cr.quarks.appdal.android.ui;

import java.util.List;

import cr.quarks.appdal.android.R;
import cr.quarks.appdal.android.entity.Community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommunityAdapter extends BaseAdapter{

	List<Community> communities;
	
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
            convertView = inflater.inflate(R.layout.community_item_layout, null);

            holder = new CommunityViewHolder();
            holder.position = (TextView) convertView
                    .findViewById(R.id.community_item_position);
            holder.name = (TextView) convertView
                    .findViewById(R.id.community_item_name);
            holder.location = (TextView) convertView
                    .findViewById(R.id.community_item_location);
            holder.add = (ImageView) convertView
                    .findViewById(R.id.community_item_link);

            convertView.setTag(holder);
        } else {
            holder = (CommunityViewHolder) convertView.getTag();
        }

        if(community != null){
        	holder.position.setText(String.valueOf(position + 1));
        	holder.name.setText(community.getName());
        	holder.location.setText(community.getLocation());
        }

        return convertView;
	}
	
	public void setItems(List<Community> items){
		this.communities = items;
		this.notifyDataSetChanged();
	}

	
	private class CommunityViewHolder {
        TextView position;

        TextView name;
        
        TextView location;

        ImageView add;
    }
}
