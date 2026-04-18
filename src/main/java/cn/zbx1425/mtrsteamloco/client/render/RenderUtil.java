package cn.zbx1425.mtrsteamloco.client.render;

import cn.zbx1425.mtrsteamloco.UFEInfo;
import cn.zbx1425.mtrsteamloco.client.MainClient;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;

public class RenderUtil {
    public static PoseStack commonPoseStack = null;
    public static MultiBufferSource commonVertexConsumers = null;

    public static double runningSeconds;
    public static double frameSeconds;
    private static float lastRenderedTick = 0;

    public static String getRenderStatusMessage() {
        return "\n=== " + UFEInfo.MOD_NAME + " Rendering Status ===\n"
                + "Draw Calls: " + MainClient.drawContext.drawCallCount
                + ", Batches: " + MainClient.drawContext.batchCount
                + "\n"
                + "Faces: " + MainClient.drawContext.singleFaceCount + " non-instanced"
                + ", " + MainClient.drawContext.instancedFaceCount + " instanced"
                + ", " + (MainClient.drawContext.singleFaceCount + MainClient.drawContext.instancedFaceCount) + " total"
                + "\n"
                + "Faces via Blaze3D: " + MainClient.drawContext.blazeFaceCount
                + "\n"
                + "Uploaded Models: " + MainClient.modelManager.uploadedVertArrays.size()
                + " (" + MainClient.modelManager.vaoCount + " VAOs, "
                + MainClient.modelManager.vboCount + " VBOs)"
                + "\n"
                + String.join("\n", MainClient.drawContext.debugInfo)
                ;
    }
}
