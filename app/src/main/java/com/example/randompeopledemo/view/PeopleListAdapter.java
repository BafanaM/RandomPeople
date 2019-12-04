package com.example.randompeopledemo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.randompeopledemo.R;
import com.example.randompeopledemo.databinding.PeopleListItemBinding;
import com.example.randompeopledemo.model.Result;

import java.util.List;

public class PeopleListAdapter extends RecyclerView.Adapter<PeopleListAdapter.ViewHolder> {

    private List<Result> peopleListItems;
    private ItemOnclickListener itemOnclickListener;

    PeopleListAdapter(ItemOnclickListener itemOnclickListener) {
        this.itemOnclickListener = itemOnclickListener;
    }

    void setPeopleListItems(List<Result> peopleListItems) {
        this.peopleListItems = peopleListItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PeopleListItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.people_list_item, parent, false);
        return new ViewHolder(binding, itemOnclickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result resultItem = peopleListItems.get(position);
        holder.itemBinding.tvTitle.setText(String.format("%s %s", resultItem.getName().getFirst(), resultItem.getName().getLast()));
        holder.itemBinding.descriptionTextView.setText(resultItem.getLocation().getCity());

        Glide.with(holder.itemBinding.cvProfile.getContext())
                .load(resultItem.getPicture().getMedium()).dontAnimate().fitCenter().diskCacheStrategy(
                DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder).into(holder.itemBinding.cvProfile);
        holder.setPeopleListItem(resultItem);
    }

    public interface ItemOnclickListener {
        void onItemClicked(Result peopleResult);
    }

    @Override
    public int getItemCount() {
        return peopleListItems != null ? peopleListItems.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        PeopleListItemBinding itemBinding;
        ItemOnclickListener itemOnclickListener;
        Result peopleResult;

        public void setPeopleListItem(Result peopleResult) {
            this.peopleResult = peopleResult;
        }

        ViewHolder(@NonNull PeopleListItemBinding itemBinding, final ItemOnclickListener itemOnclickListener) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            this.itemOnclickListener = itemOnclickListener;
            itemBinding.contentContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemOnclickListener.onItemClicked(peopleResult);
                }
            });
        }
    }
}
