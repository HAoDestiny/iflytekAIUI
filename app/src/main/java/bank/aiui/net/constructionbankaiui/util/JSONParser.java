package bank.aiui.net.constructionbankaiui.util;

import android.text.TextUtils;
import android.util.Log;

import com.iflytek.aiui.AIUIEvent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Destiny_hao on 2018/1/31.
 */

public class JSONParser {

    public static String getResultJSON(AIUIEvent event) {
        String resultStr = "";

        try {
            JSONObject bizParamJson = new JSONObject(event.info);
            JSONObject data = bizParamJson.getJSONArray("data").getJSONObject(0);
            JSONObject params = data.getJSONObject("params");
            JSONObject content = data.getJSONArray("content").getJSONObject(0);

            if (content.has("cnt_id")) {
                String cnt_id = content.getString("cnt_id");
                String cntStr = new String(event.data.getByteArray(cnt_id), "utf-8");

                // 获取该路会话的id，将其提供给支持人员，有助于问题排查
                // 也可以从Json结果中看到
                String sid = event.data.getString("sid");

                // 获取从数据发送完到获取结果的耗时，单位：ms
                // 也可以通过键名"bos_rslt"获取从开始发送数据到获取结果的耗时
                long eosRsltTime = event.data.getLong("eos_rslt", -1);
//                mNlpText.setText(eosRsltTime + "ms");

                if (TextUtils.isEmpty(cntStr)) {
                    return null;
                }

                JSONObject cntJson = new JSONObject(cntStr);

                String sub = params.optString("sub");
                if ("nlp".equals(sub)) {
                    // 解析得到语义结果
                    resultStr = cntJson.optString("intent");
                    Log.e("结果=======", resultStr );


                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return resultStr;
    }

    public static String getResult(String result) {
        String ret = "";
        try {
            JSONObject jsonObject = new JSONObject(result);

            JSONObject answerObject = jsonObject.getJSONObject("answer");

            ret = answerObject.getString("text");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;
    }

}
