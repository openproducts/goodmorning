package adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import classes.CityNameCode;

import com.example.goodmorning.R;

public class CitiesAdapter extends  ArrayAdapter<CityNameCode> {
	private Context context;
	private List<CityNameCode> items;

	public CitiesAdapter(Context context, List<CityNameCode> items) {
		super(context, R.layout.city_code, items);
		this.context = context;
		this.items = items;
		}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if (rowView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			rowView = inflater.inflate(R.layout.city_code, parent, false);
		}

		CityNameCode cityNameCode = getItem(position);
		if (cityNameCode != null) {
			TextView nameCity = (TextView) rowView
					.findViewById(R.id.cityName);
			nameCity.setText(items.get(position).getNameCity());
           
			TextView codeCity = (TextView) rowView
					.findViewById(R.id.cityCode);
			codeCity.setText(items.get(position).getCodeCountry());	
		}

		return rowView;
	}
}

