package doctor4t.astronomical.common.item;

import doctor4t.astronomical.common.Astronomical;
import doctor4t.astronomical.common.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.awt.*;

public class NanoGiverItem extends Item {
	int giftMode = 0;

	public NanoGiverItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (user.isSneaking()) {
			if ((giftMode += 1) > 2) {
				giftMode = 0;
			}
		} else {
			ItemStack retItemStack = ItemStack.EMPTY;

			if (giftMode == 0) {
				retItemStack = new ItemStack(ModItems.NANO_PLANET);
				retItemStack.getOrCreateSubNbt(Astronomical.MOD_ID).putInt("color1", new Color(user.getRandom().nextFloat(), user.getRandom().nextFloat(), user.getRandom().nextFloat()).getRGB());
				retItemStack.getOrCreateSubNbt(Astronomical.MOD_ID).putInt("color2", new Color(user.getRandom().nextFloat(), user.getRandom().nextFloat(), user.getRandom().nextFloat()).getRGB());
				retItemStack.getOrCreateSubNbt(Astronomical.MOD_ID).putInt("size", 1 + user.getRandom().nextInt(10));
			} else if (giftMode == 1) {
				retItemStack = new ItemStack(ModItems.NANO_STAR);
				int temp = Astronomical.getRandomStarTemperature(user.getRandom());
				retItemStack.getOrCreateSubNbt(Astronomical.MOD_ID).putInt("temperature", temp);
				retItemStack.getOrCreateSubNbt(Astronomical.MOD_ID).putInt("size", 1 + user.getRandom().nextInt(10));
			} else if (giftMode == 2) {
				retItemStack = new ItemStack(ModItems.NANO_COSMOS);
				retItemStack.getOrCreateSubNbt(Astronomical.MOD_ID).putInt("size", 1 + user.getRandom().nextInt(100));
			}

			user.giveItemStack(retItemStack);
		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}
