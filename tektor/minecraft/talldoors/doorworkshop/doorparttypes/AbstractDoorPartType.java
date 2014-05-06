package tektor.minecraft.talldoors.doorworkshop.doorparttypes;

import java.util.LinkedList;
import java.util.List;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public abstract class AbstractDoorPartType{

	public static float depth;
	public static List<ItemStack> baseCost;
	public static List<ItemStack> costPerSize;
	public static Class<?> entityClass;
	
	public abstract void initialize(); 
	
	public abstract AbstractDoorPart getNewEntity(World world, int posX, int heightPosition, int posZ, int heightSize, int orientation);
	
	public List<String> getCostAsString() {
		List<String> a = new LinkedList<String>();
		for(ItemStack i : baseCost)
		{
			a.add(i.getDisplayName() + " " + i.stackSize);
		}
		return a;
	}
}
