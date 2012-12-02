package cr.quarks.appdal.android.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cr.quarks.appdal.android.AppDalApp;
import cr.quarks.appdal.android.R;
import cr.quarks.appdal.android.entity.Community;

public class CommunityBar extends LinearLayout {

	private TextView communityPositionTextView;

	private TextView communityNameTextView;

	private View separator;

	private ImageView linkImageView;

	private Community community;

	public CommunityBar(Context context) {
		super(context);
		initComponents();
		updateContent();

	}

	public CommunityBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initComponents();
	}

	/**
	 * Inits the UI components from R.layout.community_bar.
	 */
	private void initComponents() {
		LayoutInflater inflater = LayoutInflater.from(this.getContext());
		inflater.inflate(R.layout.community_bar, this);

		communityPositionTextView = (TextView) findViewById(R.id.community_bar_position);
		communityNameTextView = (TextView) findViewById(R.id.community_bar_name);
		separator = (View) findViewById(R.id.community_separator);
		linkImageView = (ImageView) findViewById(R.id.community_bar_link);

	}

	/**
	 * Update the content from ExigoBusinessManager Community.
	 */
	public void updateContent() {
		AppDalApp app = AppDalApp.getInstance();
		community = app.getCommunity();

		if (community != null) {

			communityPositionTextView.setText(String.valueOf(community
					.getPosition()));
			communityNameTextView.setText(community.getName());
			int color = getResources().getColor(
					community.getRankingType().getColorId());
			communityPositionTextView.setBackgroundColor(color);
			separator.setBackgroundColor(color);
			linkImageView.setBackgroundColor(color);
		}
	}

	public Community getCommunity() {
		return community;
	}

}
