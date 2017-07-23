package com.example.displayjokeandroidlib.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.displayjokeandroidlib.R;

import java.util.ArrayList;


public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.MyViewHolder>  {
    ArrayList<String> jokes;
    Context context;
    int  LastPosition=-1;
    RecyclerViewClickListener ClickListener ;
    public JokesAdapter(){}
    public JokesAdapter(ArrayList<String> jokes, Context context){
        this.jokes=new ArrayList<>();
        this.jokes=jokes;
        this.context=context;
    }

    public void setClickListener(RecyclerViewClickListener clickListener){
       this.ClickListener= clickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_joke,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.jokeTextView.setText(jokes.get(position));

        setAnimation(holder.jokeContainer,position);

    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView jokeTextView;
        RelativeLayout jokeContainer;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            jokeTextView=(TextView)itemView.findViewById(R.id.joke_text);
            jokeContainer=(RelativeLayout)itemView.findViewById(R.id.joke_container);
        }

        @Override
        public void onClick(View view) {
            if(ClickListener!=null)
            ClickListener.ItemClicked(view ,getAdapterPosition());
        }

        public void clearAnimation()
        {
            jokeContainer.clearAnimation();
        }
    }

    public interface RecyclerViewClickListener
    {

        public void ItemClicked(View v, int position);
    }

    private void setAnimation(View viewToAnimate, int position)
    {

        if (position > LastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            LastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
             holder.clearAnimation();
    }



}

