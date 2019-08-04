package ravinder.example.myklan;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ContactsAdapterClass extends Adapter<ContactsAdapterClass.ProductViewHolder> {

    private Context mCtx;
    private List<Contacts> itemsList;

    public ContactsAdapterClass(Context mCtx, List<Contacts> itemsList) {
        this.mCtx = mCtx;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater  = LayoutInflater.from(mCtx);
        View view= inflater.inflate(R.layout.activity_contacts,null);





        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        Contacts product = itemsList.get(i);
        productViewHolder.name.setText(product.getName());
        productViewHolder.number.setText(product.getNumber());



    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView number;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.t1);
            number=itemView.findViewById(R.id.t2);





            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                    String position = Integer.toString(pos);

                    Intent fp=new Intent(mCtx.getApplicationContext(), ContactDetails.class);
                    //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                    fp.putExtra("quantity",position);
                    mCtx.startActivity(fp);

                    //Log.e("position", position);

                    if (pos != RecyclerView.NO_POSITION) {
                        Contacts clickedDataItem = itemsList.get(pos);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                    }




                }


            });



        }
    }


}
