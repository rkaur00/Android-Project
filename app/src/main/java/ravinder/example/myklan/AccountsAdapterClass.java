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


public class AccountsAdapterClass extends Adapter<AccountsAdapterClass.ProductViewHolder> {

    private Context mCtx;
    private List<Accounts> itemsList;

    public AccountsAdapterClass(Context mCtx, List<Accounts> itemsList) {
        this.mCtx = mCtx;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater  = LayoutInflater.from(mCtx);
        View view= inflater.inflate(R.layout.accounts,null);

        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        Accounts product = itemsList.get(i);
        productViewHolder.name.setText(product.getAccounts());


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textview);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                    String position = Integer.toString(pos);

                    Intent fp=new Intent(mCtx.getApplicationContext(), AccountDetails.class);
                    //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                  fp.putExtra("quantity",position);
                    mCtx.startActivity(fp);

                    //Log.e("position", position);

                    if (pos != RecyclerView.NO_POSITION) {
                        Accounts clickedDataItem = itemsList.get(pos);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getAccounts(), Toast.LENGTH_SHORT).show();
                    }




                }


            });

        }
    }


}
