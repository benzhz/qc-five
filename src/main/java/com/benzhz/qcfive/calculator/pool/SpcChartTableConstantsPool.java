package com.benzhz.qcfive.calculator.pool;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 缓存计算公式中的常量，数量不多，故直接用常量池
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.pool
 * @Project：qc-five
 * @name：SpcChartTableConstantsPool
 * @Date：2025/2/16 22:15
 * @Filename：SpcChartTableConstantsPool
 */
public class SpcChartTableConstantsPool {

    protected static final Map<String, Map<String, String>> constantsMap;

    public static final String SPC_CHART_TABLE_CONSTANTS_JSON = "spc_chart_constants.json";

    static {
        String json = ResourceUtil.readUtf8Str(SPC_CHART_TABLE_CONSTANTS_JSON);
        constantsMap = JSONArray.parseObject(json, new TypeReference<Map<String, Map<String, String>>>() {
        });
    }

    /**
     * 获取计算公式常量
     * @param constant
     * @param subSize
     * @return
     */
    public static Double getConst(String constant, Integer subSize) {
        Map<String, String> constantNumberMap = constantsMap.get(constant);
        if (constantNumberMap == null) {
            return 0.0;
        }
        String constantValue = constantNumberMap.get(subSize.toString());
        if (StringUtils.isNoneBlank(constantValue)) {
            return Double.valueOf(constantValue);
        }
        return 0.0;
    }
}
