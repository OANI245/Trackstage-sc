package cn.zbx1425.mtrsteamloco.client.render.integration;

import cn.zbx1425.sowcerext.model.RawModel;

import java.util.Map;

public class DynamicVehicleModelLoader {
    private static Map<String, RawModel> cachedModels;
    private static String cachedPath;
    private static long cachedPathMtime = 0;
}
