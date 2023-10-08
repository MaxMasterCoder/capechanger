package max.panda.capechanger;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CapeChangerClient implements ClientModInitializer {

    private static final KeyBinding CustomCapeToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding("Cape Toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R,"Toggle Custom Cape"));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CustomCapeToggle.wasPressed()) {
                CapeChanger.CAPE_ON = !CapeChanger.CAPE_ON;
            }
        });
    }
}
