package berries.mods.ts.mvapi;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class MVCreativeModeTab {
    protected ResourceLocation identifier;
    protected Supplier<ItemStack> itemIconSupplier;
    protected CreativeModeTab.DisplayItemsGenerator generator;
    protected CreativeModeTab instance;

    public MVCreativeModeTab(ResourceLocation identifier, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator generator) {
        this.identifier = identifier;
        this.itemIconSupplier = icon;
        this.generator = generator;
    }

    public void register() {
        var builder = FabricItemGroup.builder(this.identifier);
        builder.icon(this.itemIconSupplier);
        builder.title(MVComponent.translatable(String.format("itemGroup.%s.%s", this.identifier.getNamespace(), this.identifier.getPath())));
        builder.displayItems(this.generator);
        this.instance = builder.build();
    }

    public CreativeModeTab getTabInstance() {
        return instance;
    }
}
