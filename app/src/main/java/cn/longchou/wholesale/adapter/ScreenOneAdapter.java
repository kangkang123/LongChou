package cn.longchou.wholesale.adapter;

import java.util.List;

import org.w3c.dom.Text;

import cn.longchou.wholesale.R;
import cn.longchou.wholesale.domain.MyBuy;
import cn.longchou.wholesale.domain.MyScreenOne;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
* @Description: 筛选页面的第一个adapter
*
* @author kangkang 
*
* @date 2016年1月4日 上午10:33:34 
*
 */
public class ScreenOneAdapter extends BaseAdapter{

	
    private Context context;
    private List<MyScreenOne> list;
    
	public ScreenOneAdapter(Context context, List<MyScreenOne> buys) {
		this.context=context;
		this.list=buys;
	}

	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public MyScreenOne getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null)
		{
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.item_list_my_buy, null);
			
			holder.myBuy = (TextView) convertView.findViewById(R.id.tv_item_my_buy);
			holder.myChoose = (TextView) convertView.findViewById(R.id.tv_item_my_buy_choose);
			holder.mLine = (ImageView) convertView.findViewById(R.id.iv_my_line_buy);
			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		MyScreenOne item = getItem(position);
		if(position==list.size()-1)
		{
			holder.mLine.setVisibility(View.INVISIBLE);
		}else if(position==0)
		{
			holder.mLine.setVisibility(View.VISIBLE);
		}
		holder.myBuy.setText(item.buy);
		holder.myChoose.setText(item.choose);
		return convertView;
	}
	
	
	class ViewHolder{
		private TextView myBuy;
		private TextView myChoose;
		private ImageView mLine;
	}

}
