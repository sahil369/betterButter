package greatfoodstartshere.betterbutter.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import greatfoodstartshere.betterbutter.R;
import greatfoodstartshere.betterbutter.models.CookBook;
import greatfoodstartshere.betterbutter.models.Recipe;
import greatfoodstartshere.betterbutter.volley.AppController;

/**
 * Created by jyot on 1/10/15.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        NetworkImageView image;
        TextView name, userName;
        Button share, like;

        RecipeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv_recipe);
            name = (TextView) itemView.findViewById(R.id.name);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            image = (NetworkImageView) itemView.findViewById(R.id.image);
            share = (Button) itemView.findViewById(R.id.share_recipe);
            like = (Button) itemView.findViewById(R.id.like_recipe);
        }
    }

    List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_card, viewGroup, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int i) {
        holder.userName.setText(recipes.get(i).getUser().getName());
        holder.name.setText(recipes.get(i).getName());
        holder.image.setImageUrl(recipes.get(i).getImageUrl(), imageLoader);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
