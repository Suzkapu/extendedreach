package at.extendedreach.item;

import at.extendedreach.Config;
import at.extendedreach.ExtendedReach;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Optional;
import java.util.Map;

public class ReachAmulet extends Item implements ICurioItem {

    private static final ResourceLocation BLOCK_REACH_ID = ResourceLocation.fromNamespaceAndPath(ExtendedReach.MODID, "reach_amulet_block");
    private static final ResourceLocation ENTITY_REACH_ID = ResourceLocation.fromNamespaceAndPath(ExtendedReach.MODID, "reach_amulet_entity");

    public ReachAmulet() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation uuid, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();

        double bonus = 3.0;
        try {
            bonus = Config.REACH_BONUS.get();
        } catch (Exception e) {
            // Config not loaded yet
        }

        modifiers.put(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(
                BLOCK_REACH_ID,
                bonus,
                AttributeModifier.Operation.ADD_VALUE
        ));

        modifiers.put(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(
                ENTITY_REACH_ID,
                bonus,
                AttributeModifier.Operation.ADD_VALUE
        ));

        return modifiers;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        //(Shift + Right Click)
        if (player.isSecondaryUseActive()) {
            Optional<ICuriosItemHandler> inventoryOpt = CuriosApi.getCuriosInventory(player);

            if (inventoryOpt.isPresent()) {
                ICuriosItemHandler handler = inventoryOpt.get();
                Map<String, ICurioStacksHandler> curios = handler.getCurios();

                for (ICurioStacksHandler stacksHandler : curios.values()) {
                    IDynamicStackHandler dynamicStackHandler = stacksHandler.getStacks();

                    for (int i = 0; i < dynamicStackHandler.getSlots(); i++) {

                        if (dynamicStackHandler.isItemValid(i, stack) && dynamicStackHandler.getStackInSlot(i).isEmpty()) {
                            dynamicStackHandler.setStackInSlot(i, stack.copy());
                            stack.setCount(0);

                            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
                        }
                    }
                }
            }
        }

        return super.use(level, player, hand);
    }
}