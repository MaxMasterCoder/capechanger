package max.panda.capechanger.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import max.panda.capechanger.CapeChanger;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value=AbstractClientPlayerEntity.class, priority=2000)
public abstract class CapeTextureChanger extends PlayerEntity {

    public CapeTextureChanger(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @ModifyReturnValue(method = "getSkinTextures", at = @At("TAIL"))
    protected SkinTextures useCapeIfPresent(SkinTextures original) {
        var cape = CapeChanger.getResourcePackCape();
        if (cape == null) {
            return original;
        }

        return new SkinTextures(original.texture(),null,cape,original.elytraTexture(),original.model(),true);
    }
}