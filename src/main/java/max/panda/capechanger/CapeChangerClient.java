package max.panda.capechanger;

import max.panda.capechanger.screen.CapeScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class CapeChangerClient implements ClientModInitializer {

    public static final KeyBinding CustomCapeToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding("Cape Toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R,"Toggle Custom Cape"));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CustomCapeToggle.wasPressed()) {
                if (!(client.currentScreen instanceof CapeScreen)) {
                    client.setScreen(new CapeScreen(client.currentScreen));
                }
            }
        });
    }
}
