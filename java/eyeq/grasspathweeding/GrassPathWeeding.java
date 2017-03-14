package eyeq.grasspathweeding;

import eyeq.grasspathweeding.event.GrassPathWeedingEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static eyeq.grasspathweeding.GrassPathWeeding.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class GrassPathWeeding {
    public static final String MOD_ID = "eyeq_grasspathweeding";

    @Mod.Instance(MOD_ID)
    public static GrassPathWeeding instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new GrassPathWeedingEventHandler());
    }
}
