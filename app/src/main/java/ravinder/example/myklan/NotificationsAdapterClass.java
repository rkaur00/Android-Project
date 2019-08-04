package ravinder.example.myklan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class NotificationsAdapterClass extends Adapter<NotificationsAdapterClass.ProductViewHolder> {

    private Context mCtx;
    private List<Notifications> itemsList;

    public NotificationsAdapterClass(Context mCtx, List<Notifications> itemsList) {
        this.mCtx = mCtx;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater  = LayoutInflater.from(mCtx);
        View view= inflater.inflate(R.layout.notifications,null);

        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        Notifications product = itemsList.get(i);
        productViewHolder.name.setText(product.getNotifications());


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textTask);


        }
    }


}
