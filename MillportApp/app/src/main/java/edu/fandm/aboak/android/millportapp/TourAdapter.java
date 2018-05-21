package edu.fandm.aboak.android.millportapp;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by boaki on 3/5/2018.
 */

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourAdapterViewHolder>
{
    private final String TAG = "Tour Adapter";

    public interface TourAdapterOnClickHandler{
        void onClick(double lat, double lon);
    }

    public ArrayList<Place> activeViews;

    public TourAdapter()
    {
        activeViews = new ArrayList<>(0);
    }

    public TourAdapter(ArrayList<Place> seenLocations)
    {
        activeViews = seenLocations;
    }

    public Place getItem(int index)
    {
        return activeViews.get(index);
    }

    @Override
    public TourAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        int layoutId = R.layout.landmark_object;

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(layoutId, viewGroup, false);
        return(new TourAdapterViewHolder(itemView));
    }

    @Override
    public void onBindViewHolder(TourAdapterViewHolder tourAdapterViewHolder, int position)
    {
        Place place = activeViews.get(position);
        tourAdapterViewHolder.latlongView.setText(place.getInfo()[1]);
        tourAdapterViewHolder.iconView.setImageResource(place.getLocImage());
        tourAdapterViewHolder.titleView.setText(place.getInfo()[0]);
        tourAdapterViewHolder.descriptionView.setText(place.getInfo()[2]);
    }

    @Override
    public int getItemCount() {
        if(activeViews == null)
            return 0;
        return(activeViews.size());
    }

    class TourAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        final ConstraintLayout itemView;
        final ImageView iconView;
        final TextView titleView;
        final TextView latlongView;
        final TextView descriptionView;

        TourAdapterViewHolder(View view)
        {
            super(view);
            itemView = (ConstraintLayout) view.findViewById(R.id.test_tidbit);
            iconView = (ImageView) view.findViewById(R.id.landmark_icon);
            titleView = (TextView) view.findViewById(R.id.landmark_title);
            latlongView = (TextView) view.findViewById(R.id.landmark_latlong);
            descriptionView = (TextView) view.findViewById(R.id.landmark_description);

            //Log.d(TAG, "Tour adapter viewholder initialized");
            view.setOnClickListener(this);
        }

        public ConstraintLayout getItemView()
        {
            return(itemView);
        }

        @Override
        public void onClick(View v)
        {
            int adapterPosition = getAdapterPosition();
        }
    }
}


