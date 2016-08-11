package HoaDon.pkg;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class AdapterLvHD  extends ArrayAdapter<ItemHD>{
	
	List<ItemHD> list;
    Context context;
	public AdapterLvHD (Context context, int textViewResourceId, List<ItemHD> list) {
		super(context, textViewResourceId, list);
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ModelLvHD m = new ModelLvHD(this.context);
		m.setListItem(this.list.get(position));
		return m;
	}

}
