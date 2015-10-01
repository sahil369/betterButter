package greatfoodstartshere.betterbutter.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import greatfoodstartshere.betterbutter.R;
import greatfoodstartshere.betterbutter.models.CookBook;

/**
 * Created by jyot on 1/10/15.
 */
public class CookBookAdapter extends RecyclerView.Adapter<CookBookAdapter.CookBookViewHolder> {

    public static class CookBookViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView profilePic;
        TextView name, time, title;

        CookBookViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            time = (TextView)itemView.findViewById(R.id.time);
            title = (TextView)itemView.findViewById(R.id.title);
            profilePic = (ImageView)itemView.findViewById(R.id.profile_pic);
        }
    }

    List<CookBook> cookBooks;

    public CookBookAdapter(List<CookBook> cookBooks){
        this.cookBooks = cookBooks;
    }

    @Override
    public int getItemCount() {
        return cookBooks.size();
    }

    @Override
    public CookBookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cookbook_card, viewGroup, false);
        return new CookBookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CookBookViewHolder holder, int i) {
        holder.name.setText(cookBooks.get(i).getUser().getName());
        //holder.time.setText(cookBooks.get(i).getti);
        holder.title.setText(cookBooks.get(i).getTitle());
        //holder.profilePic.setImageResource(cookBooks.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
