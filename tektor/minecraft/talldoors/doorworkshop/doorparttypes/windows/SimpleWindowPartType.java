package tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.AbstractDoorPartType;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.SimpleWindowPartEntity;

public class SimpleWindowPartType extends AbstractDoorPartType{

	@Override
	public void initialize() {
		depth = 0.25f;
		baseCost = new ArrayList<ItemStack>(2);
		baseCost.add(new ItemStack(Blocks.planks,2,0));
		baseCost.add(new ItemStack(TallDoorsBase.luiviteIngot,2,0));
		costPerSize = new ArrayList<ItemStack>(1);
		costPerSize.add(new ItemStack(Blocks.planks,1,0));
		entityClass = SimpleWindowPartEntity.class;
		textureCount = 1;
		
	}

	@Override
	public AbstractDoorPart getNewEntity(World world, int posX,
			int heightPosition, int posZ, int heightSize, int orientation) {
		AbstractDoorPart part = new SimpleWindowPartEntity(world, posX, heightPosition, posZ, heightSize, orientation,depth);
		return part;
	}

}
