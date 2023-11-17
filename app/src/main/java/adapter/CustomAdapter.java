package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.OrderHistoryDetail;
import com.example.myapplication.R;
import com.example.myapplication.SplashScreen;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<Integer> orderNos;
    ArrayList<String> dates;
    ArrayList<Double> prices;
    ArrayList<Boolean> orderStatus;
    Context context;

    public CustomAdapter(Context context, ArrayList<Integer> orderNos,  ArrayList<String> dates,  ArrayList<Double> prices,  ArrayList<Boolean> orderStatus) {
        this.context = context;
        this.orderNos = orderNos;
        this.dates = dates;
        this.prices = prices;
        this.orderStatus = orderStatus;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // set the data in items
        holder.orderNos.setText(String.valueOf(orderNos.get(position)));
        holder.dates.setText(dates.get(position));
        holder.prices.setText(String.valueOf(prices.get(position)));
        holder.orderStatus.setText(String.valueOf(orderStatus.get(position)));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, orderNos.get(position).toString(), Toast.LENGTH_SHORT).show();
                int orderNoHistory = Integer.parseInt(orderNos.get(position).toString());
                String dateHistory = dates.get(position).toString();
                Double priceHistory = Double.valueOf(prices.get(position).toString());
                Boolean statusHistory = Boolean.valueOf(orderStatus.get(position).toString());

                Intent intent = new Intent(context, OrderHistoryDetail.class);
                intent.putExtra("id", orderNoHistory);
                intent.putExtra("date", dateHistory);
                intent.putExtra("price", priceHistory);
                intent.putExtra("status", statusHistory);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return orderNos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderNos, dates, prices, orderStatus;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            orderNos = (TextView) itemView.findViewById(R.id.orderNoJSON);
            dates = (TextView) itemView.findViewById(R.id.datesJSON);
            prices = (TextView) itemView.findViewById(R.id.pricesJSON);
            orderStatus = (TextView) itemView.findViewById(R.id.statusJSON);

        }
    }
}
