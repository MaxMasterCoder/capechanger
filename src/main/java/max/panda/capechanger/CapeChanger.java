package max.panda.capechanger;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapeChanger implements ModInitializer {

	public static final String MOD_ID = "capechanger";
	public static boolean CAPE_ON = true;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
	}

	@Nullable
	public static Identifier getResourcePackCape() {
		try {
			var cape = new Identifier("capechanger", "cape.png");
			if (MinecraftClient.getInstance().getResourceManager().getResource(cape).isPresent() && CAPE_ON) {
				return cape;
			}
		} catch (Exception ignored) {

		}
		return null;
	}
}