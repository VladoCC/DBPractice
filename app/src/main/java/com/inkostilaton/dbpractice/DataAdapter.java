package com.inkostilaton.dbpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.tr4android.recyclerviewslideitem.SwipeAdapter;
import com.tr4android.recyclerviewslideitem.SwipeConfiguration;

public abstract class DataAdapter extends SwipeAdapter {
    protected Context mContext;
    protected RecyclerView mRecyclerView;
    protected int layoutId;

    public DataAdapter(Context context, RecyclerView recyclerView, int layoutId) {
        mContext = context;
        mRecyclerView = recyclerView;
        this.layoutId = layoutId;
        initData();
    }

    @Override
    public RecyclerView.ViewHolder onCreateSwipeViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, true);
        return onCreateCustomViewHolder(v);
    }

    @Override
    public SwipeConfiguration onCreateSwipeConfiguration(Context context, int position) {
        return new SwipeConfiguration.Builder(context)
                .setLeftBackgroundColorResource(R.color.colorAccent)
                .setRightBackgroundColorResource(R.color.colorPrimaryDark)
                .setDrawableResource(R.drawable.ic_delete_white_24dp)
                .setRightDrawableResource(R.drawable.ic_edit_white_24dp)
                .setLeftUndoable(true)
                .setLeftUndoDescription(R.string.data_delete)
                .setDescriptionTextColorResource(android.R.color.white)
                .setLeftSwipeBehaviour(SwipeConfiguration.SwipeBehaviour.NORMAL_SWIPE)
                .setRightSwipeBehaviour(SwipeConfiguration.SwipeBehaviour.NORMAL_SWIPE)
                .setRightDescription("Edit")
                .build();
    }

    @Override
    public void onSwipe(int position, int direction) {
        if (direction == SWIPE_LEFT) {
            delete(position);
            getData().removeVisible(position);
            notifyItemRemoved(position);
            Toast toast = Toast.makeText(mContext, "Deleted item at position " + position, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            ((DataActivity) mContext).openAdd(position);
        }
    }

    protected void delete(int index) {

    }

    protected abstract RecyclerView.ViewHolder onCreateCustomViewHolder(View view);

    public abstract VisibilityList getData();

    protected abstract void initData();

    public void setSearchQuery(String query) {
        getData().setQuery(query);
    }

    public void setSearchQuery() {
        getData().setVisibleAll();
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }
}
