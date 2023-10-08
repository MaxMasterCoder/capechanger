package max.panda.pandamod.item;

import max.panda.pandamod.PandaMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PandaMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PandaMod.LOGGER.info("Registering items for " + PandaMod.MOD_ID);
    }
}
