package at.extendedreach.item;

import at.extendedreach.Config;
import at.extendedreach.ExtendedReach;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ReachAmulet extends Item implements ICurioItem {

    // Unique IDs for the modifiers
    private static final ResourceLocation BLOCK_REACH_ID = ResourceLocation.fromNamespaceAndPath(ExtendedReach.MODID, "reach_amulet_block");
    private static final ResourceLocation ENTITY_REACH_ID = ResourceLocation.fromNamespaceAndPath(ExtendedReach.MODID, "reach_amulet_entity");

    public ReachAmulet() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation uuid, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();

        double bonus = 3.0; // Default fallback
        try {
            bonus = Config.REACH_BONUS.get();
        } catch (Exception e) {
            // Config not loaded yet
        }

        // Apply the SAME bonus to both attributes
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
}