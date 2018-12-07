package com.xwwx.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xwwx.design.R;
import com.xwwx.design.bean.Msg;

import java.util.ArrayList;
import java.util.List;

/**
 * @功能
 * @作者 Administrator
 * @创建日期 2018/12/7 0007
 */

public class MsgAdapter extends BaseAdapter {
    private List<Msg> items = new ArrayList<Msg>();
    private LayoutInflater inflater;
    private Context mContext;
    public MsgAdapter(Context context) {
        super();
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        final ViewHolder holder;
        View view = v;
        if (view == null) {
            view = inflater.inflate(R.layout.item_msg,viewGroup, false);
            holder = new ViewHolder();
            assert view != null;
            holder.msg = (TextView) view.findViewById(R.id.tv_item_msg);
            holder.msgTime = (TextView) view.findViewById(R.id.tv_item_time);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.msg.setText(items.get(i).getMsg());
        holder.msgTime.setText(items.get(i).getMsgtime());
        return view;
    }
    public void addItem(Msg item) {
        items.add(item);
    }

    public void addItemList(List<Msg> itemList){
        items.addAll(itemList);
    }

    public void clearItemList(){
        items.clear();
    }
    class ViewHolder {
        TextView msg;
        TextView msgTime;
    }
}
