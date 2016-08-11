package dssp.khachhang;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class AdapterLvTC  extends ArrayAdapter<ItemLvTC>{
	
	List<ItemLvTC> list;
    Context context;
	public AdapterLvTC (Context context, int textViewResourceId, List<ItemLvTC> list) {
		super(context, textViewResourceId, list);
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ModelLvTC m = new ModelLvTC(this.context);
		m.setListItem(this.list.get(position));
		return m;
	}

}
