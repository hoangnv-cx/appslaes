package example.hoanghh99.appbnhng.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import example.hoanghh99.appbnhng.DTO.ItemDTO;
import example.hoanghh99.appbnhng.R;

public class HomeAdapter extends BaseAdapter {
    Context context;
    List<ItemDTO> itemDTOS;
    int layout;

    public HomeAdapter(Context context, List<ItemDTO> itemDTOS, int layout) {
        this.context = context;
        this.itemDTOS = itemDTOS;
        this.layout = layout;
    }

    public HomeAdapter(Context context, List<ItemDTO> itemDTOS) {
        this.context = context;
        this.itemDTOS = itemDTOS;
    }

    @Override
    public int getCount() {
        return itemDTOS.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler=null;
        if(convertView==null){
            viewHoler=new ViewHoler();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.row_home,null);
           // convertView=inflater.inflate(layout,null);
            viewHoler.imageView=(ImageView)convertView.findViewById(R.id.imgHomeItem);
            viewHoler.name=(TextView)convertView.findViewById(R.id.txtNameItem);
            viewHoler.price=(TextView)convertView.findViewById((R.id.txtPriceItem));
            convertView.setTag(viewHoler);
        }else {
            viewHoler= (ViewHoler) convertView.getTag();
        }
        ItemDTO itemDTO=itemDTOS.get(position);

        viewHoler.name.setLines(2);
        viewHoler.name.setEllipsize(TextUtils.TruncateAt.END);
        viewHoler.name.setText(itemDTO.getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHoler.price.setText("Gia : "+decimalFormat.format(itemDTO.getPrice())+" D" );
//        Picasso.with(context).load(itemDTO.getThumbnail()).
//                placeholder(R.drawable.image_load_gif).
//                error(R.drawable.error_image).
//                into(viewHoler.imageView);
        return convertView;
    }
    class ViewHoler{
        ImageView imageView;
        TextView name,price;
    }
}
