package bank.aiui.net.constructionbankaiui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bank.aiui.net.constructionbankaiui.R;
import bank.aiui.net.constructionbankaiui.model.ChatMO;

/**
 * Created by Destiny_hao on 2018/1/31.
 */

public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ChatMO> mData;

    public ChatListAdapter(Context context, List<ChatMO> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_chat_item_send, parent, false);

            return new SendMessage(view);
        }

        else {
            View  view = LayoutInflater.from(context).inflate(R.layout.activity_chat_item_call, parent, false);

            return new CallResult(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof  SendMessage) {
            ((SendMessage)holder).sendContent.setText(mData.get(position).getContent());
        } else if (holder instanceof CallResult){
            ((CallResult)holder).resultContent.setText(mData.get(position).getContent());
        }

    }

    @Override
    public int getItemViewType(int position) {

        if (mData.get(position).getType() == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class SendMessage extends RecyclerView.ViewHolder {

        private TextView sendContent;

        public SendMessage(View itemView) {

            super(itemView);

            sendContent = (TextView) itemView.findViewById(R.id.tv_send_content);
        }
    }

    private class CallResult extends RecyclerView.ViewHolder {

        private TextView resultContent;

        public CallResult(View itemView) {

            super(itemView);

            resultContent = (TextView) itemView.findViewById(R.id.tv_call_content);
        }
    }

    //添加消息显示在RecyclerView中
    public void addItem(ChatMO msg, RecyclerView recyclerView, ChatListAdapter chatListAdapter) {
        mData.add(msg);
        recyclerView.scrollToPosition(chatListAdapter.getItemCount() - 1);
        notifyDataSetChanged();
    }
}
