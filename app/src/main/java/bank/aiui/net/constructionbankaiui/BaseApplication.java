package bank.aiui.net.constructionbankaiui;

import android.app.Application;
import android.util.Log;

import com.iflytek.cloud.SpeechUtility;

/**
 * Created by Destiny_hao on 2018/1/30.
 */

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        Log.e("初始化", "wocao");
        //SpeechUtility.createUtility(BaseApplication.this, SpeechConstant.APPID + "=" + getString(R.string.app_id));
        SpeechUtility.createUtility(BaseApplication.this, String.format("engine_mode=msc,delay_init=0,appid=%s", getString(R.string.app_id)));
        super.onCreate();
    }
}
