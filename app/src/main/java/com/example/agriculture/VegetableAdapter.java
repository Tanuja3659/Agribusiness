package com.example.agriculture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.ViewHolder> {

    private List<VegetableModel> dataList;
    private Context context;

    public VegetableAdapter(Context context, List<VegetableModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public  void setFilterList(List<VegetableModel> filterList)
    {
        this.dataList=filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VegetableModel data = dataList.get(position);
        holder.Description.setText(data.getDescription());
        holder.Name.setText(data.getName());
        holder.Price.setText(data.getPrice());
        holder.Quantity.setText(data.getQuantity());
        holder.UPIID.setText(data.getUPIID());

        Picasso.get()
                .load(data.getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Description, Name, Price, Quantity, UPIID;
        ImageView image, next;

        ViewHolder(View itemView) {
            super(itemView);
            Description = itemView.findViewById(R.id.Description);
            Name = itemView.findViewById(R.id.Name);
            Price = itemView.findViewById(R.id.Price);
            Quantity = itemView.findViewById(R.id.Quantity);
            UPIID = itemView.findViewById(R.id.UPIID);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                VegetableModel selectedItem = dataList.get(position);

                String upiId = UPIID.getText().toString();
                String price = Price.getText().toString();
                String name = selectedItem.getName(); // Retrieve name from the selected item
                String quantity = selectedItem.getQuantity(); // Retrieve quantity from the selected item

                if (!upiId.isEmpty() && !price.isEmpty()) {
                    Intent intent = new Intent(context, PayActivity.class);
                    intent.putExtra("upi", upiId);
                    intent.putExtra("price", price);
                    intent.putExtra("product_name", name);
                    intent.putExtra("quantity", quantity);
                    context.startActivity(intent);
                } else {
                    // Handle null or empty values
                }
            }
        }
    }
}
