package com.example.cs496finalproject.finalprojectstarwars;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cs496finalproject.finalprojectstarwars.utils.StarWarsUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by pranavramesh on 6/5/17.
 */

public class StarWarsAdapter extends RecyclerView.Adapter<StarWarsAdapter.SearchResultViewHolder> {
    private ArrayList<StarWarsUtils.SearchResult> searchResults;

    public void updateSearchResults(ArrayList<StarWarsUtils.SearchResult> searchResultsList) {
        searchResults = searchResultsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (searchResults != null) {
            return searchResults.size();
        } else {
            return 0;
        }
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_result_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.bind(searchResults.get(position));
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private TextView search_result;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            search_result = (TextView)itemView.findViewById(R.id.tv_search_result);
        }

        public void bind(StarWarsUtils.SearchResult searchResult) {
            Log.d("StarWarsAdapter", "Getting " + searchResult.name);
            search_result.setText(searchResult.name);
        }
    }
}
