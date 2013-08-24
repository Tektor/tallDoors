package tektor.minecraft.talldoors.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import tektor.minecraft.talldoors.renderer.RenderFenceGate1;
import tektor.minecraft.talldoors.renderer.doors2x4.RenderDarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.renderer.doors2x4.RenderEntranceDoorSize4;
import tektor.minecraft.talldoors.renderer.doors2x4.RenderMetalEntranceDoor1;
import tektor.minecraft.talldoors.renderer.doors2x5.RenderDarkMetalEntranceDoor2;
import tektor.minecraft.talldoors.renderer.doors2x5.RenderEntranceDoor2;
import tektor.minecraft.talldoors.renderer.doors2x5.RenderMetalEntranceDoor2;
import tektor.minecraft.talldoors.renderer.doors2x6.RenderDarkMetalEntranceDoor3;
import tektor.minecraft.talldoors.renderer.doors2x6.RenderEntranceDoor3;
import tektor.minecraft.talldoors.renderer.doors2x6.RenderMetalEntranceDoor3;
import tektor.minecraft.talldoors.renderer.drawbridge.RenderDrawbridgeBase;
import tektor.minecraft.talldoors.renderer.drawbridge.RenderDrawbridgeMachine;
import tektor.minecraft.talldoors.renderer.drawbridge.RenderRopeConnector;
import tektor.minecraft.talldoors.renderer.drawbridge.TessRenderDrawbridgeMachine;
import tektor.minecraft.talldoors.TallDoorsCommonProxy;
import tektor.minecraft.talldoors.entities.FenceGate1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor3;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeMachine;
import tektor.minecraft.talldoors.entities.drawbridge.EntityConnector;

public class TallDoorsClientProxy extends TallDoorsCommonProxy{

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor1.class, new RenderEntranceDoorSize4());
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor2.class, new RenderEntranceDoor2());
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor3.class, new RenderEntranceDoor3());
		RenderingRegistry.registerEntityRenderingHandler(FenceGate1.class, new RenderFenceGate1());
		RenderingRegistry.registerEntityRenderingHandler(EntityConnector.class, new RenderRopeConnector());
		RenderingRegistry.registerEntityRenderingHandler(MetalEntranceDoor1.class, new RenderMetalEntranceDoor1());
		RenderingRegistry.registerEntityRenderingHandler(DarkMetalEntranceDoor1.class, new RenderDarkMetalEntranceDoor1());
		RenderingRegistry.registerEntityRenderingHandler(DrawbridgeBase.class, new RenderDrawbridgeBase());
		RenderingRegistry.registerEntityRenderingHandler(DrawbridgeMachine.class, new TessRenderDrawbridgeMachine());
		RenderingRegistry.registerEntityRenderingHandler(DarkMetalEntranceDoor2.class, new RenderDarkMetalEntranceDoor2());
		RenderingRegistry.registerEntityRenderingHandler(DarkMetalEntranceDoor3.class, new RenderDarkMetalEntranceDoor3());
		RenderingRegistry.registerEntityRenderingHandler(MetalEntranceDoor2.class, new RenderMetalEntranceDoor2());
		RenderingRegistry.registerEntityRenderingHandler(MetalEntranceDoor3.class, new RenderMetalEntranceDoor3());
		
		
	}
}
