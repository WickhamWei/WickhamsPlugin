package wickhamsPlugin.API.shapedRecipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import wickhamsPlugin.WickhamsPlugin;

public class WShapedRecipe implements Listener{
	protected ItemStack[] shapedRecipeContents=new ItemStack[10];
	protected ShapedRecipe recipe;
	protected static final Plugin WICKHAMS_PLUGIN=WickhamsPlugin.MAIN;
	protected boolean loadingAtServerLoadBoolean;
	
	public WShapedRecipe(NamespacedKey recipeNamespacedKey,ItemStack itemResult,Boolean loadingAtServerLoadBoolean) {
		shapedRecipeContents[0]=itemResult;
		recipe=new ShapedRecipe(recipeNamespacedKey, itemResult);
		this.loadingAtServerLoadBoolean=loadingAtServerLoadBoolean;
	}
	
	public void shape(String line1,String line2,String line3) {
		recipe.shape(line1,line2,line3);
	}
	
	public void shape2(int content,ItemStack item) {
		shapedRecipeContents[content]=item;
	}
	
	public void setIngredient(Character keyCharacter,ItemStack item) {
		recipe.setIngredient(keyCharacter, item.getType());
	}
	
	public void setIngredient(Character keyCharacter,Material material) {
		recipe.setIngredient(keyCharacter, material);
	}
	
	public void addRecipe() {
		if(loadingAtServerLoadBoolean) {
			WLoadingRecipeListener listener2=new WLoadingRecipeListener(recipe);
			WICKHAMS_PLUGIN.getServer().getPluginManager().registerEvents(listener2, WICKHAMS_PLUGIN);
		}else {
			Bukkit.addRecipe(recipe);
		}
		WShapedRecipeListener listener= new WShapedRecipeListener(shapedRecipeContents);
		WICKHAMS_PLUGIN.getServer().getPluginManager().registerEvents(listener, WICKHAMS_PLUGIN);
	}

}
