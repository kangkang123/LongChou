package cn.longchou.wholesale.dialog;

import java.util.List;

import cn.longchou.wholesale.R;
import cn.longchou.wholesale.adapter.ChoiceListAdapter;
import cn.longchou.wholesale.adapter.FinancePlanAdapter;
import cn.longchou.wholesale.base.BaseDialog;
import cn.longchou.wholesale.domain.CarsInfo;
import cn.longchou.wholesale.domain.FinancialPlan;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmListDialog extends BaseDialog {

	private String title;
	private List<FinancialPlan> items;
	private TextView tv_listdialog_title;
	private ListView lv_listdialog;
	
	//列表
	private OnListDialogLietener onListDialogLietener;
	
	private Button bt_dialog_cancel;
	private Button bt_dialog_confirm;
	
	private Context context;
	private FinancePlanAdapter myAdapter;
	private int p;
	private CarsInfo carsInfo;
	
	protected ConfirmListDialog(Context context, String title, List<FinancialPlan> items,int p,CarsInfo carsInfo,OnListDialogLietener onListDialogLietener) {
		super(context);
		this.context = context;
		this.title = title;
		this.items = items;
		this.p=p;
		this.carsInfo=carsInfo;
		this.onListDialogLietener = onListDialogLietener;
	}

	public static  void showListDialog(Context context, String title, List<FinancialPlan> items,int p, CarsInfo carsInfo, OnListDialogLietener onListDialogLietener){
		ConfirmListDialog dialog = new ConfirmListDialog(context, title, items, p,carsInfo, onListDialogLietener);
		dialog.show();
	}
	
	
	@Override
	public void initView() {
		setContentView(R.layout.confirm_list);
		tv_listdialog_title = (TextView) findViewById(R.id.tv_confirm_list_title);
		lv_listdialog = (ListView) findViewById(R.id.lv_confirm_list);
		
		bt_dialog_cancel = (Button) findViewById(R.id.bt_confirm_list_cancel);
		bt_dialog_confirm = (Button) findViewById(R.id.bt_confrim_list_confirm);
	}

	@Override
	public void initListener() {
		
		//确定取消按钮
		bt_dialog_cancel.setOnClickListener(this);
		bt_dialog_confirm.setOnClickListener(this);
		
		//给lv_listdialog设置条目侦听
		lv_listdialog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					if(onListDialogLietener != null){
						onListDialogLietener.onItemClick(parent, view, position, id, myAdapter);
					}
//				dismiss();
			}
		});

	}

	@Override
	public void initData() {
		tv_listdialog_title.setText(title);
		
		lv_listdialog.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
		myAdapter = new FinancePlanAdapter(context, items,p,carsInfo);
		lv_listdialog.setAdapter(myAdapter);

	}

	@Override
	public void processClick(View v) {
		int id = v.getId();
		if (id == R.id.bt_confirm_list_cancel) {
			if(onListDialogLietener != null){
				onListDialogLietener.onCancel();
			}
		} else if (id == R.id.bt_confrim_list_confirm) {
			if(onListDialogLietener != null){
				onListDialogLietener.onConfirm();
			}
		}
		//对话框消失
		dismiss();

	}
	

	//列表接口
	public interface OnListDialogLietener{
		void onItemClick(AdapterView<?> parent, View view, int position, long id,FinancePlanAdapter myAdapter);
		
		//确定取消
		void onCancel();
		void onConfirm();
	}
	
}
