package cn.zbx1425.mtrsteamloco.mixin;

import cn.zbx1425.sowcerext.model.RawModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.Object2ObjectAVLTreeMap;
import org.mtr.mapping.holder.EntityAbstractMapping;
import org.mtr.mapping.mapper.EntityModelExtension;
import org.mtr.mapping.mapper.OptimizedModel;
import org.mtr.mod.render.DynamicVehicleModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Environment(EnvType.CLIENT)
@Mixin(DynamicVehicleModel.class)
public abstract class DynamicVehicleModelMixin extends EntityModelExtension<EntityAbstractMapping> {
    public DynamicVehicleModelMixin(Object2ObjectAVLTreeMap<String, RawModel> nameToObjModels) {
        super(0, 0);
    }
}
