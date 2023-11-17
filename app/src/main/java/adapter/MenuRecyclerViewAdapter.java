package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DisplayMenu;
import com.example.myapplication.Menu;
import com.example.myapplication.R;

import java.util.List;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder>{

    public List<Menu> menuList;
    private Context context;

    public MenuRecyclerViewAdapter(Context context, List<Menu> menuList) {
        this.context=context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View list_items = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,null);
        MenuViewHolder menuVH = new MenuViewHolder(list_items);
        return menuVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.menuName.setText(menuList.get(position).getName());
        holder.imageMenu.setImageResource(menuList.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, menuList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DisplayMenu.class);
//                intent.putExtra("imageMod", menuList.get(position).getName());
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        public TextView menuName;
        public ImageView imageMenu;

        public MenuViewHolder(View itemView) {

            super(itemView);
            menuName = itemView.findViewById(R.id.tv_name);
            imageMenu = itemView.findViewById(R.id.img_menu);

        }
    }

}
