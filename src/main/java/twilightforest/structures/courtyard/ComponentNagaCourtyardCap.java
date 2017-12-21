package twilightforest.structures.courtyard;

import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import twilightforest.TFFeature;
import twilightforest.TwilightForestMod;
import twilightforest.structures.StructureTFComponent;

import java.util.Random;

public class ComponentNagaCourtyardCap extends StructureTFComponent {
    private static final ResourceLocation HEDGE_CAP = new ResourceLocation(TwilightForestMod.ID, "courtyard/hedge_end");
    private static final ResourceLocation HEDGE_CAP_BIG = new ResourceLocation(TwilightForestMod.ID, "courtyard/hedge_end_big");

    @SuppressWarnings({"WeakerAccess", "unused"})
    public ComponentNagaCourtyardCap() {
        super();
    }

    @SuppressWarnings("WeakerAccess")
    public ComponentNagaCourtyardCap(TFFeature feature, int i, int x, int y, int z, Rotation rotation) {
        super(feature, i);
        this.rotation = rotation;
        this.boundingBox = new StructureBoundingBox(x, y, z, x + 5, y + 5, z + 5);
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
        BlockPos pos = new BlockPos(this.getBoundingBox().minX, this.getBoundingBox().minY, this.getBoundingBox().minZ);

        MinecraftServer server = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldIn.getSaveHandler().getStructureTemplateManager();

        PlacementSettings placementSettings = new PlacementSettings()
                .setRotation(this.rotation)
                .setReplacedBlock(Blocks.STRUCTURE_VOID)
                .setBoundingBox(this.getBoundingBox());

        Template template = templateManager.getTemplate(server, HEDGE_CAP);
        template.addBlocksToWorldChunk(worldIn, pos, placementSettings);

        Template templateBig = templateManager.getTemplate(server, HEDGE_CAP_BIG);
        templateBig.addBlocksToWorldChunk(worldIn, pos, placementSettings.setIntegrity(ComponentNagaCourtyardMain.HEDGE_INTEGRITY));

        return true;
    }
}