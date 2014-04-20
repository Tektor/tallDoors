package tektor.minecraft.talldoors.doorworkshop;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModularDoorPlacer extends Item {

	public ModularDoorPlacer() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(false);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		if (!par3World.isRemote) {

			int var24 = MathHelper
					.floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

			if(!checkFree(par3World,par4,par5+1,par6, 2, 4, false, var24)) {
				par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
				return false;
			}
			DoorBase door = new DoorBase(par3World);
			door.setOrientation(false, var24);
			door.setPosition(par4, par5 + 1, par6);
			String[][] cons = new String[10][10];
			for(int columns = 0; columns < cons.length; columns++)
			{
				for(int blocks = 0; blocks < cons[columns].length; blocks++)
				{
					cons[columns][blocks] = "plain";
				}
			}
			door.setConstructionPlan(cons);
			door.constructFromPlan();

			par3World.spawnEntityInWorld(door);
		}

		return true;

	}

	private boolean checkFree(World world, int posX, int posY, int posZ,
			int width, int height, boolean left, int orientation) {
		if (left) {
			for (int i = 0; i < width; i++) {
				for (int k = 0; k < height; k++) {
					switch (orientation) {
					case 0:
						if (!world.isAirBlock(posX - i, posY + k, posZ))
							return false;
						else
							break;
					case 1:
						if (!world.isAirBlock(posX, posY + k, posZ - i))
							return false;
						else
							break;
					case 2:
						if (!world.isAirBlock(posX + i, posY + k, posZ))
							return false;
						else
							break;
					case 3:
						if (!world.isAirBlock(posX, posY + k, posZ + i))
							return false;
						else
							break;

					}

				}
			}
		} else {
			for (int i = 0; i < width; i++) {
				for (int k = 0; k < height; k++) {
					switch (orientation) {
					case 0:
						if (!world.isAirBlock(posX + i, posY + k, posZ))
							return false;
						else
							break;
					case 1:
						if (!world.isAirBlock(posX, posY + k, posZ + i))
							return false;
						else
							break;
					case 2:
						if (!world.isAirBlock(posX - i, posY + k, posZ))
							return false;
						else
							break;
					case 3:
						if (!world.isAirBlock(posX, posY + k, posZ - i))
							return false;
						else
							break;

					}

				}
			}
		}
		return true;
	}

}