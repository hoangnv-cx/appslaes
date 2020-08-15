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

import java.util.List;

import example.hoanghh99.appbnhng.DTO.ClassifyDTO;
import example.hoanghh99.appbnhng.R;

public class NavigationAdapter extends BaseAdapter {
    List<ClassifyDTO> classifyDTOS;
    Context context;

    public NavigationAdapter(List<ClassifyDTO> classifyDTOS, Context context) {
        this.classifyDTOS = classifyDTOS;
        this.context = context;
    }

    @Override
    public int getCount() {
        return classifyDTOS.size();
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
            convertView=inflater.inflate(R.layout.row_navigation_home,null);
            // convertView=inflater.inflate(layout,null);
            viewHoler.imageView=(ImageView)convertView.findViewById(R.id.imgNavigation);
            viewHoler.textView=(TextView)convertView.findViewById(R.id.txtNavigation);
            convertView.setTag(viewHoler);
        }else {
            viewHoler= (ViewHoler) convertView.getTag();
        }
        ClassifyDTO classifyDTO=classifyDTOS.get(position);
        viewHoler.textView.setLines(2);
        viewHoler.textView.setEllipsize(TextUtils.TruncateAt.END);
        viewHoler.textView.setText(classifyDTO.getName());
//                Picasso.with(context).load(classifyDTO.getImage()).
//                placeholder(R.drawable.image_load_gif).
//                error(R.drawable.error_image).
//                into(viewHoler.imageView);
        return convertView;
    }
    class ViewHoler{
        ImageView imageView;
        TextView textView;
    }
}
