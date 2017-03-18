package in.silive.cokestudio.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by akriti on 18/3/17.
 */

public class CheckNetworkConnection {
    public Context context;

    public CheckNetworkConnection(Context context) {
        this.context = context;
    }

    public boolean check_connection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) {
            return false;
        } else {
            return true;
        }


    }

}
