package dssp.pkg;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class AdapterLvTopic  extends ArrayAdapter<ItemLvTopic>{
	
	List<ItemLvTopic> list;
    Context context;
	public AdapterLvTopic (Context context, int textViewResourceId, List<ItemLvTopic> list) {
		super(context, textViewResourceId, list);
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ModelLvTopic m = new ModelLvTopic(this.context);
		m.setListItem(this.list.get(position));
		return m;
	}

}
