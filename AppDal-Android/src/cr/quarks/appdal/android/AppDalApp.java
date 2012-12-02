package cr.quarks.appdal.android;

import cr.quarks.appdal.android.entity.User;
import cr.quarks.appdal.android.util.Constants;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppDalApp extends Application{
	
	private static AppDalApp instance;
	
	private SharedPreferences preferences;
	
	private User user;
	
	@Override
    public void onCreate() {

        super.onCreate();

        instance = this;
        
        preferences = PreferenceManager
                .getDefaultSharedPreferences(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static AppDalApp getInstance() {
        return instance;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int getUserCommunityId(){
		 int result = preferences.getInt(Constants.USER_COMMUNITY_ID, -1);
		 return result;
	}
	
	public void setUserCommunityId(int id){
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(Constants.USER_COMMUNITY_ID, id);
		editor.commit();
	}
    
    
}
